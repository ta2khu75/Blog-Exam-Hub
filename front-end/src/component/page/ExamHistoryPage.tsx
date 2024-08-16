import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import AnswerListElement from "../element/AnswerListElement";
import RandomUtil from "../../util/RandomUtil";
import ExamHistoryService from "../../service/ExamHistoryService";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { setExam } from "../../redux/slice/examSlice";
import { setQuizExam } from "../../redux/slice/quizExamSlice";
import AnswerUserRequest from "../../request/AnswerUserRequest";
import ExamHistoryDetailsResponse from "../../response/details/ExamHistoryDetailResponse";
const ExamHistoryPage = () => {
  const { examHistoryId } = useParams();
  const [showAnswer, setShowAnswer] = useState(false);
  const quizResponseList = useAppSelector(
    (state) => state.exams[Number(examHistoryId)] ?? []
  );
  const [answerListUser, setAnswerListUser]=useState<AnswerUserRequest[]>([])
  const [examHistoryResponse, setExamHistoryResponse] =
    useState<ExamHistoryDetailsResponse>();
  // const answerListUser = useAppSelector(
  //   (state) => state.userExams[Number(examHistoryId)]
  // );
  const quizExam = useAppSelector(
    (state) => state.quizExam?.[Number(examHistoryId)] ?? 0
  );
  const dispatch = useAppDispatch();
  useEffect(() => {
    fetchExamHistoryResponse();
  }, []);
  const fetchExamHistoryResponse = () => {
    ExamHistoryService.readDetailsById(Number(examHistoryId)).then((d) => {
      if (d.success) {
        setExamHistoryResponse(d.data);
        const quizzes = d.data.exam.quizzes.map((quiz) => {
          quiz.answers = RandomUtil.shuffleArray(quiz.answers);
          return quiz;
        });
        if (quizResponseList.length == 0) {
          dispatch(
            setExam({
              id: Number(examHistoryId),
              value: RandomUtil.shuffleArray(quizzes),
            })
          );
        }
      }
    });
  };
  return (
    <>
      <div style={{ height: "100px" }}></div>
      <div className="container">
        <div className="row">
          <div
            className="col-lg-7 p-4 mx-4 border border-4 rounded-4"
            style={{ height: "80vh" }}
          >
            <div className="col-4"></div>
            <div>
              <h4>
                {quizExam + 1}. {quizResponseList?.[quizExam]?.question}
              </h4>
              <div className="row">
                <AnswerListElement
                  examId={Number(examHistoryId)}
                  showAnswer={showAnswer}
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
                    setQuizExam({
                      id: Number(examHistoryId),
                      value: quizExam - 1,
                    })
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
                    setQuizExam({
                      id: Number(examHistoryId),
                      value: quizExam + 1,
                    })
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
              <button
                onClick={() => setShowAnswer(!showAnswer)}
                className={`btn btn-info m-2 ${showAnswer ? "active" : ""}`}
              >
                {showAnswer ? "Hide Answer" : "ShowAnswer"}
              </button>
            </div>
          </div>
          <div className="col-lg-4 border border-1 rounded-4 p-0 mx-2 ">
            <div className="px-5">
              {quizResponseList?.map((quiz, index) => (
                <button
                  onClick={() =>
                    dispatch(
                      setQuizExam({ id: Number(examHistoryId), value: index })
                    )
                  }
                  className={`
                  ${
                    answerListUser?.[quiz.id] !== undefined &&
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
    </>
  );
};

export default ExamHistoryPage;
