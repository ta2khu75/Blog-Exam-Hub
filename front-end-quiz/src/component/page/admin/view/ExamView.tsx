import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ExamService from "../../../../service/ExamService";
import ExamResponse from "../../../../response/ExamResponse";
import QuizResponse from "../../../../response/QuizResponse";
import QuizService from "../../../../service/QuizService";
import { Button, Popover } from "antd";
import ModalElement from "../../../element/ModalElement";
import QuizForm from "../form/QuizForm";
// import AnswerForm from "../form/AnswerForm";
// import AnswerService from "../../../../service/AnswerService";
import AnswerListElement from "../../../element/AnswerListElement";
import { toast } from "react-toastify";
import PopoverContentElement from "../../../element/PopoverContentElement";

const ExamView = () => {
    const { id } = useParams();
    const [openCreateQuiz, setOpenCreateQuiz] = useState(false);
    const [quizResponse, setQuizResponse] = useState<QuizResponse>()
    // const [openCreateAnswer, setOpenCreateAnswer] = useState(false);
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
            })
        }
    }

    const fetchListQuiz = () => {
        if (id && !isNaN(Number(id))) {
            QuizService.readByExamId(Number(id)).then((d) => {
                setQuizResponseList(d.data);
            })
        }
    }
    const handleEditClick = (data: QuizResponse) => {
        setQuizResponse(data);
        setOpenCreateQuiz(true);
    };
    const handleDeleteClick = (data: QuizResponse) => {
        QuizService.delete(data.id).then((d) => {
            if (d.status_code < 400) {
                toast.success("Successfully to delete");
                fetchListQuiz();
            } else {
                toast.error(d.message_error);
            }
        })
    };
    return (
        <>
            <h2>{examResponse?.title}</h2>
            <ol>
                {quizResponseList?.map(quizResponse => (<>

                    <Popover placement="left" title={"Action Quiz"} content={<PopoverContentElement data={quizResponse} handleDeleteClick={handleDeleteClick} handleEditClick={handleEditClick} />}>
                        <li>{quizResponse.question}</li>
                    </Popover>
                    <AnswerListElement quizResponse={quizResponse} />
                    {/* <Button onClick={() => setOpenCreateAnswer(true)}>Add Answer</Button><ModalElement children={<AnswerForm quizResponse={quizResponse} />} open={openCreateAnswer} setOpen={setOpenCreateAnswer} /> */}
                </>))}
                <div className="my-5"></div>
                <Button onClick={() => setOpenCreateQuiz(true)}>Add Quiz</Button>
            </ol>
            {examResponse && <ModalElement title="Modal Exam" open={openCreateQuiz} children={<QuizForm quizResponse={quizResponse} refresh={fetchListQuiz} examResponse={examResponse} />} setOpen={setOpenCreateQuiz} />}
        </>
    )
}

export default ExamView