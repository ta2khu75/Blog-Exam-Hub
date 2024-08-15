import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import QuizResponse from "../../response/QuizResponse";
import AnswerListElement from "../element/AnswerListElement";
import RandomUtil from "../../util/RandomUtil";
import ExamHistoryService from "../../service/ExamHistoryService";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { deleteExam, setExam } from "../../redux/slice/examSlice";
import { deleteUserExam, setUserExam } from "../../redux/slice/useExamSlice";
import { deleteQuizExam, setQuizExam } from "../../redux/slice/quizExamSlice";
import ExamHistoryResponse from "../../response/ExamHistoryResponse";
import { toast } from "react-toastify";
import ModalElement from "../element/ModalElement";

const ExamDetailPage = () => {
  const { examId } = useParams();
  const [openResult, setOpenResult] = useState(false);
  const navigate=useNavigate()
  const [timeLeft, setTimeLeft] = useState({
    minutes: 0,
    seconds: 0,
  });
  const quizResponseList = useAppSelector(
    (state) => state.exams[Number(examId)] ?? []
  );
  const [examHistoryResponse, setExamHistoryResponse] =
    useState<ExamHistoryResponse>();
  const answerListUser = useAppSelector(
    (state) => state.userExams[Number(examId)]
  );
  const quizExam = useAppSelector(
    (state) => state.quizExam?.[Number(examId)] ?? 0
  );
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
    ExamHistoryService.readByExamId(Number(examId)).then((d) => {
      if (d.success) {
        setExamHistoryResponse(d.data);
        calculatorTime(new Date(d.data.end_time));
        const quizzes = d.data.exam.quizzes.map((quiz) => {
          quiz.answers = RandomUtil.shuffleArray(quiz.answers);
          return quiz;
        });
        if (quizResponseList.length == 0) {
          dispatch(
            setExam({
              id: Number(examId),
              value: RandomUtil.shuffleArray(quizzes),
            })
          );
        }
      }
    });
  };
  const handleAnswerClick = (
    quizResponse: QuizResponse,
    answerIds: number[]
  ) => {
    dispatch(
      setUserExam({
        examId: Number(examId),
        answerIds: answerIds,
        quizId: quizResponse.id,
      })
    );
  };
  const handleSubmitAnswer = () => {
    const answerUser = Object.entries(answerListUser).map(
      ([quiz_id, answer_ids]) => ({
        quiz_id: Number(quiz_id),
        answer_ids,
      })
    );
    if (examHistoryResponse?.id && examId)
      ExamHistoryService.scoreByExamHistoryId(
        examHistoryResponse?.id,
        Number(examId),
        answerUser
      ).then((d) => {
        console.log(d.data);

        if (d.success) {
          setExamHistoryResponse(d.data);
          toast.success(d.data.point);
          setOpenResult(true);
        }
      });
  };
  const handleFinishClick = () => {
    dispatch(deleteUserExam(Number(examId)));
    dispatch(deleteQuizExam(Number(examId)));
    dispatch(deleteExam(Number(examId)));
    navigate("/")
    toast.success("Successfully finished the exam");
  };
  return (
    <>
      <div style={{ height: "100px" }}></div>
      <div className="container">
        <div className="row">
          <div className="col-lg-8">
            <div className="col-4"></div>
            <div>
              <h2>
                {quizExam + 1}. {quizResponseList?.[quizExam]?.question}
              </h2>
              <div className="row">
                <AnswerListElement
                  handleAnswerClick={handleAnswerClick}
                  examId={Number(examId)}
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
                  dispatch(
                    setQuizExam({ id: Number(examId), value: quizExam - 1 })
                  )
                }
                className={`btn ${
                  quizExam === 0 ? "btn-secondary" : "btn-info"
                }`}
              >
                Back
              </button>
              <button
                disabled={quizExam === quizResponseList.length - 1}
                onClick={() =>
                  dispatch(
                    setQuizExam({ id: Number(examId), value: quizExam + 1 })
                  )
                }
                className={`btn ${
                  quizExam === quizResponseList.length - 1
                    ? "btn-secondary"
                    : "btn-info"
                }`}
              >
                Next
              </button>
              {!examHistoryResponse?.last_modified_date && (
                <button
                  className="btn btn-primary"
                  onClick={() => handleSubmitAnswer()}
                >
                  Submit
                </button>
              )}
              {examHistoryResponse?.last_modified_date && (
                <button
                  className="btn btn-primary"
                  onClick={() => handleFinishClick()}
                >
                  Finish
                </button>
              )}
            </div>
          </div>
          <div className="col-lg-4" style={{ marginTop: "40px" }}>
            <h3>
              {timeLeft.minutes} phút {timeLeft.seconds} giây
            </h3>
            <div>
              {quizResponseList?.map((quiz, index) => (
                <button
                  onClick={() =>
                    dispatch(setQuizExam({ id: Number(examId), value: index }))
                  }
                  className={`d-inline-block btn 
                  ${
                    answerListUser?.[quiz.id] !== undefined &&
                    quizExam !== index
                      ? "btn-success"
                      : ""
                  } 
                  ${quizExam === index ? "btn-primary" : ""} 
                  border py-2 px-3 m-2 border-3 rounded-circle`}
                  key={quiz.id}
                >
                  {index + 1}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>
      <ModalElement open={openResult} setOpen={setOpenResult}>
        <ul>
          <li>
            Tổng điểm: <span className="">{examHistoryResponse?.point}</span>
          </li>
          <li>
            Đúng sai: <span className="">0/{quizResponseList.length}</span>
          </li>
          <li></li>
          <li></li>
        </ul>
      </ModalElement>
    </>
  );
};

export default ExamDetailPage;
