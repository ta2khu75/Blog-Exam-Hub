import { Button, Checkbox, Form, Input, Radio, Space } from 'antd'
import TextArea from 'antd/es/input/TextArea';
import { PlusOutlined } from '@ant-design/icons';
import { QuizType } from '../../../model/QuizType';
import { useImmer } from 'use-immer';
import { useEffect } from 'react';
type Prop = {
    setQuizRequests: React.Dispatch<React.SetStateAction<QuizRequest[]>>
    quiz?: QuizRequest
}
const QuizFormNew = ({ setQuizRequests, quiz }: Prop) => {
    const [quizRequest, setQuizRequest] = useImmer<QuizRequest>(quiz !== undefined ? quiz : { question: "", quiz_type: QuizType.SINGLE_CHOICE, answers: [{ answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }] })
    useEffect(() => {
        if (quiz) setQuizRequest(quiz)
    }, [quiz])
    const init = () => {
        setQuizRequest({ question: "", quiz_type: QuizType.SINGLE_CHOICE, answers: [{ answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }] })
    }
    const handleQuizTypeClick = (type: string) => {
        setQuizRequest(draft => {
            draft.quiz_type = type
        })
        if (type === QuizType.SINGLE_CHOICE) {
            const indexFirst = quizRequest.answers.findIndex((answer) => answer.correct)
            setQuizRequest(draft => {
                draft.answers = draft.answers.map((answer, index) => {
                    if (index !== indexFirst) {
                        answer.correct = false
                        return answer
                    } return answer
                })
            })
        }
    }
    const handleAddClick = () => {
        setQuizRequest(draft => {
            draft.answers.push({ answer: "", correct: false })
        })
    }
    const handleSaveClick = () => {
        setQuizRequests(quizRequests => {
            return [...quizRequests, quizRequest]
        })
        init()
    }
    return (
        <Form layout='vertical'>
            <Form.Item label="Question">
                <TextArea
                    onChange={(e) => setQuizRequest(draft => {
                        draft.question = e.target.value
                    })}
                    value={quizRequest.question} rows={4} />
            </Form.Item>
            <Form.Item label="Answer">
                {quizRequest.quiz_type == QuizType.SINGLE_CHOICE && <Radio.Group defaultValue={quizRequest.answers.findIndex((answer) => answer.correct)} onChange={(e) => setQuizRequest(draft => {
                    draft.answers.forEach(answer => answer.correct = false);
                    draft.answers[e.target.value].correct = true;
                })
                } className='w-100'>
                    <Space direction="vertical" className='w-100'>
                        {quizRequest.answers.map((answer, index) => (
                            <div className='d-flex' key={index}>
                                <Radio value={index} />
                                <Input className='w-100' onChange={(e) => setQuizRequest(draft => {
                                    draft.answers[index].answer = e.target.value
                                })} value={answer.answer} placeholder={`Answer ${index + 1}`} />
                            </div>
                        ))}</Space>
                </Radio.Group>
                }
                {quizRequest.quiz_type == QuizType.MULTIPLE_CHOICE && <Checkbox.Group
                    defaultValue={quizRequest.answers
                        .map((answer, index) => answer.correct ? index : null)
                        .filter(index => index !== null)}
                    onChange={(e: number[]) => setQuizRequest(draft => {
                        e.map(index => draft.answers[index].correct = true);
                    })
                    } className='w-100'>
                    <Space direction="vertical" className='w-100'>
                        {quizRequest.answers.map((answer, index) => (
                            <div className='d-flex' key={index}>
                                <Checkbox value={index} className='me-2' />
                                <Input onChange={(e) => setQuizRequest(draft => {
                                    draft.answers[index].answer = e.target.value
                                })} value={answer.answer} placeholder={`Answer ${index + 1}`} />
                            </div>
                        ))}
                    </Space>
                </Checkbox.Group>
                }
                <Form.Item>
                    <Button onClick={handleAddClick}>
                        <PlusOutlined />
                    </Button>
                </Form.Item>
            </Form.Item>
            <Form.Item>
                {Object.keys(QuizType).map(type => <Button onClick={() => handleQuizTypeClick(type)} type={quizRequest.quiz_type == type ? "primary" : "default"} key={`radio-${type}`} value={type}>{type}</Button>)}
            </Form.Item>
            <Form.Item>
                <Button onClick={handleSaveClick}>
                    Submit
                </Button>
            </Form.Item>
        </Form >
    )
}

export default QuizFormNew