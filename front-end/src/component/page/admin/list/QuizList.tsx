import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ExamService from "../../../../service/ExamService";
import ExamResponse from "../../../../model/response/ExamResponse";
import QuizResponse from "../../../../model/response/QuizResponse";
import QuizService from "../../../../service/QuizService";
import { Button } from "antd";
import ModalElement from "../../../element/ModalElement";
import QuizForm from "../form/QuizForm";
import { toast } from "react-toastify";
import PopoverActionElement from "../../../element/PopoverActionElement";
import AnswerList from "./AnswerList";

const QuizList = () => {
  const { id } = useParams();
  const [open, setOpen] = useState(false);
  const [quizResponse, setQuizResponse] = useState<QuizResponse>();
  const [examResponse, setExamResponse] = useState<ExamResponse>();
  const [quizResponseList, setQuizResponseList] = useState<QuizResponse[]>([]);
  useEffect(() => {
    fetchExam();
    fetchListQuiz();
  }, []);
  const fetchExam = () => {
    if (id && !isNaN(Number(id))) {
      ExamService.readById(Number(id)).then((d) => {
        setExamResponse(d.data);
      });
    }
  };

  const fetchListQuiz = () => {
    if (id && !isNaN(Number(id))) {
      QuizService.readByExamId(Number(id)).then((d) => {
        setQuizResponseList(d.data);
        setOpen(false);
      });
    }
  };
  const handleEditClick = (data: QuizResponse) => {
    setQuizResponse(data);
    setOpen(true);
  };
  const handleDeleteClick = (data: QuizResponse) => {
    QuizService.delete(data.id).then((d) => {
      if (d.status_code < 400) {
        toast.success("Successfully to delete");
        fetchListQuiz();
      } else {
        toast.error(d.message_error);
      }
    });
  };
  const handleAddClick = () => {
    setOpen(true);
    setQuizResponse(undefined);
  };
  return (
    <>
      <h2>{examResponse?.title}</h2>
      <ol>
        {quizResponseList?.map((quizResponse) => (
          <>
            <PopoverActionElement
              data={quizResponse}
              title="Action Quiz"
              handleDeleteClick={handleDeleteClick}
              handleEditClick={handleEditClick}
            >
              <>
                <li>{quizResponse.question}</li>
                {quizResponse.file_path && (
                  <img
                    className="d-block"
                    width={"300px"}
                    height={"200px"}
                    src={quizResponse.file_path}
                  />
                )}
              </>
            </PopoverActionElement>
            <AnswerList quizResponse={quizResponse} />
          </>
        ))}
        <div className="my-5"></div>
        <Button onClick={() => handleAddClick()}>Add Quiz</Button>
      </ol>
      {examResponse && (
        <ModalElement title="Modal Quiz" open={open} setOpen={setOpen}>
          <QuizForm
            quizResponse={quizResponse}
            refresh={fetchListQuiz}
            examResponse={examResponse}
          />
        </ModalElement>
      )}
    </>
  );
};

export default QuizList;
