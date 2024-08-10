import { useParams } from "react-router-dom";
import ExamService from "../../service/ExamService";
import { useEffect, useState } from "react";
import ExamResponse from "../../response/ExamResponse";
import QuizResponse from "../../response/QuizResponse";
import QuizService from "../../service/QuizService";
import AnswerListElement from "../element/AnswerListElement";
import { Button } from "antd";
import { toast } from "react-toastify";
import AnswerUserRequest from "../../request/AnswerUserRequest";
import RandomUtil from "../../util/RandomUtil";
import ProfessionService from "../../service/ProfessionService";

const ExamDetailPage = () => {
  const { examId } = useParams();
  const [examResponse, setExamResponse] = useState<ExamResponse>();
  const [quizResponseList, setQuizResponseList] = useState<QuizResponse[]>([]);
  const [answerUsers, setAnswerUsers] = useState<AnswerUserRequest[]>([]);
  useEffect(() => {
    fetchExamResponses();
    fetchQuizResponseList();
  }, []);
  const fetchExamResponses = () => {
    if (examId && !isNaN(Number(examId))) {
      ExamService.readById(Number(examId)).then((d) => {
        if (d.success) setExamResponse(d.data);
      });
    }
  };
  const fetchQuizResponseList = () => {
    if (examId && !isNaN(Number(examId))) {
      QuizService.readByExamId(Number(examId)).then((d) => {
        if (d.success) setQuizResponseList(RandomUtil.shuffleArray(d.data));
      });
    }
  };
  const handleAnswerClick = (quizResponse: QuizResponse, answerId: number) => {
    setAnswerUsers((prev) => {
      if (quizResponse.quiz_type === "SINGLE_CHOICE") {
        return [
          ...prev.filter((q) => q.quiz_id !== quizResponse.id),
          { quiz_id: quizResponse.id, answers: [answerId] },
        ];
      } else {
        // Map over the previous state, modify the entry if quiz_id matches, and filter out any empty entries
        const updatedPrev = prev.map((q) => {
          if (q.quiz_id === quizResponse.id) {
            if (q.answers.includes(answerId)) {
              return {
                ...q,
                answers: q.answers.filter((a) => a !== answerId),
              };
            } else {
              return { ...q, answers: [...q.answers, answerId] };
            }
          }
          return q; // Return the item unchanged if it doesn't match the quiz_id
        });

        // If the quizResponse doesn't exist in the previous state, add it
        const quizExists = prev.some((q) => q.quiz_id === quizResponse.id);

        if (!quizExists) {
          return [
            ...updatedPrev,
            { quiz_id: quizResponse.id, answers: [answerId] },
          ];
        }

        // Filter out any entries with an empty answer_id array
        return updatedPrev.filter((q) => q.answers.length > 0);
      }
    });
  };
  const handleSubmitAnswer = () => {
    toast.info("submit success");
    console.log("Submit Answer", answerUsers);
    if (examResponse?.id) {
      ProfessionService.submitAnswer(examResponse?.id, answerUsers).then(
        (d) => {
          console.log(d);
        }
      );
    }
  };
  return (
    <div className="container-fluid" style={{ marginTop: "25px" }}>
      <div className="row">
        <div className="col-lg-8">
          <div className="row">
            <h1 className="display-4">Exam Detail</h1>
            {examResponse && (
              <div>
                <p>Title: {examResponse.title}</p>
                <p>Description: {examResponse.description}</p>
                <p>Image Path: {examResponse.image_path}</p>
              </div>
            )}
          </div>
          <ol>
            {quizResponseList.map((quizResponse) => {
              return (
                <div key={`quiz-${quizResponse.id}`}>
                  <li key={quizResponse.id}>{quizResponse.question}</li>
                  <AnswerListElement
                    handleAnswerClick={handleAnswerClick}
                    quizResponse={quizResponse}
                  />
                </div>
              );
            })}
          </ol>
          <Button onClick={() => handleSubmitAnswer()}>Submit</Button>
        </div>
        <div className="col-lg-4"></div>
      </div>
    </div>
  );
};

export default ExamDetailPage;
