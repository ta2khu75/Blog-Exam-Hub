import { useEffect, useState } from "react";
import QuizResponse from "../../response/QuizResponse"
import AnswerService from "../../service/AnswerService";
import AnswerResponse from "../../response/AnswerResponse";
import { Button, Checkbox, Radio, Space, Popover } from "antd";
import ModalElement from "./ModalElement";
import AnswerForm from "../page/admin/form/AnswerForm";
import PopoverContentElement from "./PopoverContentElement";
import { toast } from "react-toastify";
type Props = {
    quizResponse: QuizResponse;
    handleAddAnswerClick?: () => void
}
const AnswerListElement = ({ quizResponse }: Props) => {
    const [answerResponseList, setAnswerResponseList] = useState<AnswerResponse[]>([])
    const [open, setOpen] = useState(false);
    const [answerResponse, setAnswerResponse] = useState<AnswerResponse>()
    useEffect(() => {
        fetchAnswerByQuizId();
    }, []);
    const fetchAnswerByQuizId = () => {
        AnswerService.readByQuizId(quizResponse.id).then((d) => {
            setAnswerResponseList(d.data);
        })
    }
    const handleDeleteClick = (data: AnswerResponse) => {
        AnswerService.delete(data.id).then((d) => {
            if (d.success) {
                toast.success("Successfully to delete");
                fetchAnswerByQuizId();
            } else {
                toast.error(d.message_error);
            }
        })
    }
    const handleEditClick = (data: AnswerResponse) => {
        setOpen(true);
        setAnswerResponse(data);
    }
    return (
        <>
            {quizResponse.quiz_type == "SINGLE_CHOICE" &&
                <Radio.Group value={true}>
                    <Space direction="vertical">
                        {answerResponseList.map((answer, index) => (
                            <Popover placement="left" title={"Action Answer"} content={<PopoverContentElement data={answer} handleDeleteClick={handleDeleteClick} handleEditClick={handleEditClick} />}>
                                <Radio className="d-block" key={`answer-radio-${index}`} defaultChecked={answer.correct} value={answer.correct}>{answer.answer}</Radio>
                            </Popover>
                        ))}
                    </Space>
                </Radio.Group >
            }
            {
                quizResponse.quiz_type == "MULTIPLE_CHOICE" &&
                <Space direction="vertical">
                    {/* <CheckboxGroup options={answerResponseList.map(answer => answer.answer)} value={checkList} /> */}
                    {answerResponseList.map((answer, index) => (
                        <Popover placement="left" title={"Action Answer"} content={<PopoverContentElement data={answer} handleDeleteClick={handleDeleteClick} handleEditClick={handleEditClick} />}>
                            <Checkbox
                                defaultChecked={answer.correct}
                                value={answer.id}
                                key={`answer-checkbox-${index}`}
                            >
                                {answer.answer}
                            </Checkbox>
                        </Popover>
                    ))}
                </Space>
            }
            <Button className="d-block" onClick={() => setOpen(true)}>Add Answer</Button>
            <ModalElement open={open} setOpen={setOpen} children={<AnswerForm answerResponse={answerResponse} quizResponse={quizResponse} refresh={fetchAnswerByQuizId} />} />
        </>
    )
}

export default AnswerListElement