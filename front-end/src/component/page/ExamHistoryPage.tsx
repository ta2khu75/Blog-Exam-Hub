import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import ExamHistoryService from "../../service/ExamHistoryService";
import UserAnswerResponse from "../../response/UserAnswerResponse";
import QuizDetailsResponse from "../../response/details/QuizDetailsResponse";
import AnswerListElement from "../element/AnswerListElement";
const ExamHistoryPage = () => {
  const { examHistoryId } = useParams();
  const [showAnswer, setShowAnswer] = useState(false);
  const [quizExam, setQuizExam] = useState(0);
  const [userAnswerResponses, setUserAnswerResponses] = useState<UserAnswerResponse[]>([])
  const [quizResponses, setQuizResponses] = useState<QuizDetailsResponse[]>([])
  useEffect(() => {
    fetchExamHistoryResponse();
  }, []);
  const fetchExamHistoryResponse = () => {
    ExamHistoryService.readDetailsById(Number(examHistoryId)).then((d) => {
      if (d.success) {
        setUserAnswerResponses(d.data.user_answers);
        setQuizResponses(d.data.exam.quizzes);
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
                {quizExam + 1}.
                {quizResponses?.[quizExam]?.question}
                {quizResponses?.[quizExam]?.file_path && <img className="d-block mx-auto" width={"400px"} height={"300px"} alt={`image-quiz-${quizExam}`} src={quizResponses?.[quizExam]?.file_path} />}
              </h4>

              <div className="row">
                <AnswerListElement
                  examId={0}
                  userAnswer={userAnswerResponses.filter((quiz) => quiz.quiz.id === quizResponses?.[quizExam]?.id
                  )?.flatMap(quiz => { return quiz.answers.map((answer) => answer.id) }) ?? []}
                  showAnswer={showAnswer}
                  answerResponseList={
                    quizResponses?.[quizExam]?.answers ?? []
                  }
                  quizResponse={quizResponses?.[quizExam] ?? {}}
                />
              </div>
            </div>
            <div className="my-4 d-flex justify-content-around">
              <button
                disabled={quizExam === 0}
                onClick={() => setQuizExam(q => q - 1)
                }
                className={`btn ${quizExam === 0 ? "btn-secondary" : "btn-info"
                  }`}
              >
                Back
              </button>
              <button
                disabled={quizExam === quizResponses.length - 1}
                onClick={() => setQuizExam(q => q + 1)
                }
                className={`btn ${quizExam === quizResponses.length - 1
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
              {quizResponses?.map((quiz, index) => (
                <button
                  onClick={() => setQuizExam(index)}
                  className={`
                  ${userAnswerResponses.filter((answerQuiz) => answerQuiz.quiz.id === quiz.id).length === 1 &&
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
      </div >
    </>
  );
};

export default ExamHistoryPage;
