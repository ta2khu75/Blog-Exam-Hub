import { useNavigate, useParams } from "react-router-dom";
import { MutableRefObject, useEffect, useRef, useState } from "react";
import AnswerListElement from "../element/AnswerListElement";
import RandomUtil from "../../util/RandomUtil";
import ExamResultService from "../../service/ExamResultService";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { deleteExam, setExam } from "../../redux/slice/examSlice";
import { deleteUserExam, setUserExam } from "../../redux/slice/userExamSlice";
import { deleteQuizExam, setQuizExam } from "../../redux/slice/quizExamSlice";
import { toast } from "react-toastify";
import ModalElement from "../element/ModalElement";
type Params = {
  examId: string;
}
const ExamDetailPage = () => {
  const { examId } = useParams<Params>();
  const navigate = useNavigate();
  const [openResult, setOpenResult] = useState(false);
  const quizResponseList = useAppSelector((state) => state.exams?.[examId ?? ""]) ?? [];
  const answerListUser = useAppSelector((state) => state.userExams?.[examId ?? ""]) ?? [];
  const quizExam = useAppSelector((state) => state.quizExam?.[examId ?? ""]) ?? 0;
  const [examHistoryResponse, setExamHistoryResponse] = useState<ExamResultResponse>();
  const stopTimerRef = useRef<(() => void) | null>(null) as MutableRefObject<
    (() => void) | null
  >;
  const [timeLeft, setTimeLeft] = useState({
    minutes: 0,
    seconds: 0,
  });

  const dispatch = useAppDispatch();
  useEffect(() => {
    fetchExamHistoryResponse();
  }, []);
  const calculatorTime = (time: Date) => {
    const intervalId = setInterval(() => {
      const now = new Date().getTime();
      const differenceInMs = time.getTime() - now;
      if (differenceInMs <= 0 || openResult) {
        clearInterval(intervalId);
        handleSubmitAnswer();
      } else {
        const minutes = Math.floor(differenceInMs / (1000 * 60));
        const seconds = Math.floor((differenceInMs % (1000 * 60)) / 1000);
        setTimeLeft({ minutes, seconds });
      }
    }, 1000);
    return () => clearInterval(intervalId);
  };
  const fetchExamHistoryResponse = () => {
    if (examId)
      ExamResultService.readByExamId(examId).then((d) => {
        if (d.success) {
          setExamHistoryResponse(d.data);
          stopTimerRef.current = calculatorTime(
            new Date(d.data.end_time)
          );
          if (d.status_code === 201 || quizResponseList.length == 0) {
            const quizzes = d.data.exam.quizzes.map((quiz: QuizDetailsResponse) => {
              quiz.answers = RandomUtil.shuffleArray(quiz.answers);
              return quiz;
            });
            if (examId) {
              dispatch(
                setExam({
                  examId: examId,
                  quizzes: RandomUtil.shuffleArray(quizzes),
                })
              );
              dispatch(deleteUserExam(examId));
            }
          }
        }
      });
  };
  const handleAnswerClick = (
    quizResponse: QuizResponse,
    answerIds: number[]
  ) => {
    if (examId)
      dispatch(
        setUserExam({
          examId: examId,
          answerIds: answerIds,
          quizId: quizResponse.id,
        })
      );
  };
  const handleSubmitAnswer = () => {
    const answerUser =
      answerListUser === undefined
        ? []
        : Object.entries(answerListUser).map(([quiz_id, answer_ids]) => ({
          quiz_id: Number(quiz_id),
          answer_ids,
        }));

    if (examHistoryResponse?.id && examId)
      ExamResultService.submitExam(
        examHistoryResponse?.id, { user_answers: answerUser }
      ).then((d) => {
        if (d.success) {
          if (stopTimerRef.current) stopTimerRef.current();
          setExamHistoryResponse(d.data);
          toast.success(d.data.point);
          setOpenResult(true);
        }
      });
  };
  const handleCancel = () => {
    if (examId) {
      dispatch(deleteUserExam(examId));
      dispatch(deleteQuizExam(examId));
      dispatch(deleteExam(examId));
    }
    navigate("/profile");
    toast.success("Successfully finished the exam");
  };
  return (
    <>
      <div className="container">
        <div className="row">
          <div className="col-lg-7 p-4 mx-4 border border-4 rounded-4" style={{ height: "80vh" }}>
            <div className="col-4"></div>
            <div>
              <h4>
                {quizExam + 1}. {quizResponseList?.[quizExam]?.question}
              </h4>
              {quizResponseList?.[quizExam]?.file_path && <img className="d-block mx-auto" width={"400px"} height={"300px"} alt={`image-quiz-${quizExam}`} src={quizResponseList?.[quizExam]?.file_path} />}
              <div className="row">
                <AnswerListElement
                  handleAnswerClick={handleAnswerClick}
                  examId={examId ?? ""}
                  answerResponseList={
                    quizResponseList?.[quizExam]?.answers ?? []
                  }
                  quizResponse={quizResponseList?.[quizExam] ?? {}}
                />
              </div>
            </div>
            <div className="my-4 d-flex justify-content-around">
              <button
                disabled={quizExam === 0}
                onClick={() =>

                  examId && dispatch(
                    setQuizExam({ examId: examId, quizIndex: quizExam - 1 })
                  )
                }
                className={`btn ${quizExam === 0 ? "btn-secondary" : "btn-info"
                  }`}
              >
                Back
              </button>
              <button
                disabled={quizExam === quizResponseList.length - 1}
                onClick={() =>
                  examId &&
                  dispatch(
                    setQuizExam({ examId: examId, quizIndex: quizExam + 1 })
                  )
                }
                className={`btn ${quizExam === quizResponseList.length - 1
                  ? "btn-secondary"
                  : "btn-info"
                  }`}
              >
                Next
              </button>
            </div>
          </div>
          <div className="col-lg-4 border border-1 rounded-4 p-0 mx-2 ">
            <div className="d-flex justify-content-between align-items-center bg-primary rounded-2">
              <button className="btn btn-primary" onClick={() => handleSubmitAnswer()}>Submit</button>
              <div className="text-light">
                {timeLeft.minutes} phút {timeLeft.seconds} giây
              </div>
            </div>
            <div className="px-5">
              {quizResponseList?.map((quiz, index) => (
                <button
                  onClick={() =>
                    examId && dispatch(setQuizExam({ examId: examId, quizIndex: index }))
                  }
                  className={`
                  ${answerListUser?.[quiz.id] !== undefined &&
                      quizExam !== index
                      ? "btn-success"
                      : ""
                    }
                  ${quizExam === index ? "btn-primary" : ""}
                  d-inline-block btn
                  border m-2 border-2 rounded-circle`}
                  key={quiz.id}
                  style={{ width: "45px", height: "45px" }}
                >
                  {index + 1}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>
      <ModalElement open={openResult} handleCancel={handleCancel}>
        <ul>
          <li>
            Tổng điểm: <span className="">{examHistoryResponse?.point}</span>
          </li>
          <li>
            Đúng sai: <span className="">{examHistoryResponse?.correct_count}/{quizResponseList.length}</span>
          </li>
        </ul>
      </ModalElement>
    </>
  );
};

export default ExamDetailPage;
