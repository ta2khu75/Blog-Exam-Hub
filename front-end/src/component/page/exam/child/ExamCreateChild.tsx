import { Button, Card, Checkbox, Radio, Space } from 'antd'
import { useEffect, useState } from 'react'
import ModalElement from '../../../element/ModalElement'
import QuizFormNew from '../../../element/form/QuizFormNew';
import { QuizType } from '../../../../model/QuizType';

const ExamCreateChild = () => {
    const [open, setOpen] = useState(false);
    const [quizRequest, setQuizRequest] = useState<QuizRequest>();
    const [quizRequests, setQuizRequests] = useState<QuizRequest[]>([]);
    const handleCancel = () => {
        setOpen(false);
    }
    const handleAddClick = () => {
        setQuizRequest(undefined)
        setOpen(true);
    }
    const handleDeleteClick = (indexValue:number) => {
        setQuizRequests(quizRequests.filter((quizRequest, index) => indexValue!== index))
    }
    const handleEditClick = (quizRequest:QuizRequest) =>{
        setQuizRequest(quizRequest);
        setOpen(true);
    }
    useEffect(()=>{
        setOpen(false);
    },[quizRequests]);
    return (
        <div>
            {quizRequests.map((quizRequest, index) =>
                <Card key={`quiz-${index}`} extra={<div className='d-flex'><button onClick={()=>handleDeleteClick(index)} className='me-5 btn btn-danger'>Delete</button><button onClick={()=>handleEditClick(quizRequest)} className='btn btn-warning'>Edit</button></div>} title={`${index + 1}. ${quizRequest.question}`} className='w-100'>
                    {quizRequest.quiz_type == QuizType.SINGLE_CHOICE && <Radio.Group disabled defaultValue={true} className='w-100'>
                        <Space direction="vertical" className='w-100'>
                            {quizRequest.answers.map((answer, index) => (
                                <Radio key={`radio-${index}`} value={answer.correct}>{answer.answer}</Radio>
                            ))}</Space>
                    </Radio.Group>
                    }
                    {quizRequest.quiz_type == QuizType.MULTIPLE_CHOICE && <Checkbox.Group disabled
                        defaultValue={[true]}
                        className='w-100'>
                        <Space direction="vertical" className='w-100'>
                            {quizRequest.answers.map((answer, index) => (
                                <Checkbox key={`checkbox-${index}`} value={answer.correct}>{answer.answer}</Checkbox>
                            ))}
                        </Space>
                    </Checkbox.Group>
                    }
                </Card>)}
            <Button onClick={handleAddClick}>Add Question</Button>
            <ModalElement width={1000} handleCancel={handleCancel} open={open} >
                <QuizFormNew quiz={quizRequest}  setQuizRequests={setQuizRequests} />
            </ModalElement>
        </div>
    )
}

export default ExamCreateChild