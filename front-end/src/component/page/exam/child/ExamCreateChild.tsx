import { Button, Card, Checkbox, Radio, Space } from 'antd'
import { useEffect, useState } from 'react'
import ModalElement from '../../../element/ModalElement'
import QuizFormNew from '../../../element/form/QuizFormNew';
import { QuizType } from '../../../../model/QuizType';
import ExamFormNew from '../../../element/form/ExamFormNew';
import ExamCategoryService from '../../../../service/ExamCategoryService';
import { useAppDispatch, useAppSelector } from '../../../../redux/hooks';
import { deleteQuiz, setQuizzes } from '../../../../redux/slice/quizSlice';
import IfElseElement from '../../../element/IfElseElement';
import { useParams } from 'react-router-dom';
import ExamService from '../../../../service/ExamService';
import { ExamStatus } from '../../../../model/ExamStatus';

const ExamCreateChild = () => {
    const { examId } = useParams();
    const dispatch = useAppDispatch();
    const quizzes = useAppSelector(state => state.quiz.value)
    const [openQuiz, setOpenQuiz] = useState(false);
    const [openExam, setOpenExam] = useState(false);
    const [indexQuiz, setIndexQuiz] = useState<number>();
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([])
    const [exam, setExam] = useState<ExamResponse>();
    useEffect(() => {
        fetchExam();
    }, [examId]);
    useEffect(() => {
        setOpenQuiz(false);
    }, [quizzes]);
    useEffect(() => {
        if (quizzes.length == 0) {
            setOpenQuiz(true);
        }
        fetchExamCategories();
    }, []);
    const fetchExam = () => {
        if (examId) {
            ExamService.readDetailsById(examId).then((d) => {
                console.log(d);
                if (d.success) {
                    setExam(d.data)
                    dispatch(setQuizzes(d.data.quizzes));
                }
            })
        }
    }
    const fetchExamCategories = () => {
        ExamCategoryService.readAll().then(response => {
            if (response.success) {
                setExamCategories(response.data)
            }
        })
    }
    const handleCancelQuizClick = () => {
        setIndexQuiz(undefined)
        setOpenQuiz(false);
    }
    const handleCancelExamClick = () => {
        setOpenExam(false);
    };
    const handleAddClick = () => {
        setIndexQuiz(undefined)
        setOpenQuiz(true);
    }
    const handleDeleteClick = (indexValue: number) => {
        dispatch(deleteQuiz(indexValue))
    }
    const handleEditClick = (indexValue: number) => {
        setIndexQuiz(indexValue)
        setOpenQuiz(true);
    }
    const handleCreateExamClick = () => {
        setOpenExam(true)
    }
    return (
        <>
            <h3>{exam?.title}</h3>
            <div className='d-flex justify-content-between'>
                <div className='d-flex  align-items-center'>
                    <h6>{quizzes.length === 0 ? "" : quizzes.length === 1 ? `${quizzes.length} Question` : `${quizzes.length} Questions`}</h6>
                    <Button className='ms-3' onClick={handleAddClick}>Add Question</Button>
                </div>
                <Button type='primary' onClick={handleCreateExamClick} >Save</Button>
            </div>
            <div>
                {quizzes.map((quizRequest, index) =>
                    <Card key={`quiz-${index}`} extra={<div className='d-flex'><IfElseElement condition={exam?.exam_status !== ExamStatus.COMPLETED}><button onClick={() => handleDeleteClick(index)} className='me-5 btn btn-danger'>Delete</button></IfElseElement><button onClick={() => handleEditClick(index)} className='btn btn-warning'>Edit</button></div>} title={`${index + 1}. ${quizRequest.question}`} className='w-100'>
                        <IfElseElement condition={quizRequest.quiz_type == QuizType.SINGLE_CHOICE}
                            caseFalse={
                                <Checkbox.Group disabled
                                    defaultValue={[true]}
                                    className='w-100'>
                                    <Space direction="vertical" className='w-100'>
                                        {quizRequest.answers.map((answer, index) => (
                                            <Checkbox key={`checkbox-${index}`} value={answer.correct}>{answer.answer}</Checkbox>
                                        ))}
                                    </Space>
                                </Checkbox.Group>
                            }
                        >
                            <Radio.Group disabled defaultValue={true} className='w-100'>
                                <Space direction="vertical" className='w-100'>
                                    {quizRequest.answers.map((answer, index) => (
                                        <Radio key={`radio-${index}`} value={answer.correct}>{answer.answer}</Radio>
                                    ))}</Space>
                            </Radio.Group>
                        </IfElseElement>
                    </Card>)}
                {quizzes.length > 0 && <Button onClick={handleAddClick}>Add Question</Button>}
                <ModalElement width={1000} handleCancel={handleCancelQuizClick} open={openQuiz} >
                    <QuizFormNew indexQuiz={indexQuiz} />
                </ModalElement>
                <ModalElement width={1000} handleCancel={handleCancelExamClick} open={openExam}>
                    <ExamFormNew exam={exam} quizzes={quizzes} examCategories={examCategories} />
                </ModalElement>
            </div >
        </>
    )
}

export default ExamCreateChild