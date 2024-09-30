import { Alert, Button, Checkbox, Form, FormProps, Input, Radio, Space } from 'antd'
import TextArea from 'antd/es/input/TextArea';
import { PlusOutlined, MinusCircleOutlined } from '@ant-design/icons';
import { QuizType } from '../../../@types/QuizType';
import { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from '../../../redux/hooks';
import { addQuiz, updateQuiz } from '../../../redux/slice/quizSlice';
import IfElseElement from '../IfElseElement';
type Prop = {
    indexQuiz?: number
}
const QuizFormNew = ({ indexQuiz }: Prop) => {
    const dispatch = useAppDispatch();
    const quizzes = useAppSelector(state => state.quiz.value)
    const [form] = Form.useForm<QuizRequest>();
    const [corrects, setCorrects] = useState<number[]>([]);
    const [errorAnswer, setErrorAnswer] = useState(false);
    const [quizType, setQuizType] = useState<string>(QuizType.SINGLE_CHOICE);
    useEffect(() => {
        if (indexQuiz !== undefined) {
            const quiz = quizzes[indexQuiz];
            form.setFieldsValue(quiz);
            setErrorAnswer(false);
            setQuizType(quiz.quiz_type)
            if (quiz.quiz_type === QuizType.SINGLE_CHOICE) {
                setCorrects([quiz.answers.findIndex((answer) => answer.correct)])
            } else {
                setCorrects(quiz.answers
                    .map((answer, index) => answer.correct ? index : null)
                    .filter(index => index !== null))
            }
        }
        else handleResetClick();
    }, [indexQuiz, form, quizzes])
    const onFinish: FormProps<QuizRequest>['onFinish'] = (values) => {
        if (corrects.length > 0) {
            if (indexQuiz != undefined) {
                dispatch(updateQuiz({
                    indexQuiz, quiz: {
                        ...values, quiz_type: quizType, answers: values.answers.map((answer, index) => {
                            if (corrects.includes(index)) return { ...answer, correct: true }
                            return { ...answer, correct: false }
                        })
                    }
                }))
            } else {
                dispatch(addQuiz({
                    ...values, quiz_type: quizType, answers: values.answers.map((answer, index) => {
                        if (corrects.includes(index)) return { ...answer, correct: true }
                        return { ...answer, correct: false }
                    })
                }))
            }
            handleResetClick();
        } else {
            setErrorAnswer(true)
        }
    };
    const onFinishFailed: FormProps<QuizRequest>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
    const handleResetClick = () => {
        form.setFieldsValue({ question: "", quiz_type: QuizType.SINGLE_CHOICE, answers: [{ answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }, { answer: "", correct: false }] })
        setErrorAnswer(false);
        setCorrects([]);
        setQuizType(QuizType.SINGLE_CHOICE);
    }
    const handleQuizTypeClick = (type: string) => {
        setQuizType(type)
        if (type === QuizType.SINGLE_CHOICE && corrects.length > 1) {
            setCorrects([corrects[0]])
        }
    }
    return (
        <Form form={form} onFinish={onFinish} onFinishFailed={onFinishFailed} layout='vertical' >
            <Form.Item<QuizRequest> name="id">
                <Input hidden={true} placeholder="Image URL" />
            </Form.Item>
            <Form.Item<QuizRequest> name={'question'} rules={[{ required: true, message: 'Please input question!' }]} label="Question">
                <TextArea rows={4} />
            </Form.Item>
            <Form.Item label="Quiz type">
                <Radio.Group value={quizType} onChange={(e) => handleQuizTypeClick(e.target.value)}>
                    <Radio value={QuizType.SINGLE_CHOICE}>Single choice</Radio>
                    <Radio value={QuizType.MULTIPLE_CHOICE}>Multiple choice</Radio>
                </Radio.Group>
            </Form.Item>
            <Form.Item<QuizRequest> label="Answers" name={'answers'} rules={[{ required: true, message: "Must have answer" }]} >
                {errorAnswer && <Alert message="Please select a correct answer" type="error" />}
                <IfElseElement condition={QuizType.SINGLE_CHOICE === quizType}
                    caseFalse={
                        <Checkbox.Group className='w-100' value={corrects} onChange={(e) => setCorrects(e)}>
                            <Space direction="vertical" className='w-100'>
                                <Form.List name="answers" >
                                    {(fields, { add, remove }) => (
                                        <>
                                            {fields.map(({ key, name, ...restField }, index) => (
                                                <div className="d-flex" key={key}>
                                                    <Form.Item>
                                                        <Checkbox value={index} />
                                                    </Form.Item>
                                                    <Form.Item
                                                        {...restField}
                                                        name={[name, 'answer']}
                                                        className='w-100 mx-2'
                                                        rules={[{ required: true, message: 'Missing answer' }]}
                                                    >
                                                        <Input placeholder="Answer" />
                                                    </Form.Item>
                                                    <Button onClick={() => {
                                                        console.log(index);

                                                        if (corrects.includes(index)) {
                                                            setCorrects(corrects.filter(item => item !== index));
                                                        }
                                                        remove(name);

                                                    }}>
                                                        <MinusCircleOutlined />
                                                    </Button>
                                                </div>
                                            ))}
                                            <Form.Item>
                                                <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                                    Add field
                                                </Button>
                                            </Form.Item>
                                        </>
                                    )}
                                </Form.List>
                            </Space>
                        </Checkbox.Group>
                    } >
                    <Radio.Group className='w-100' value={corrects.length > 0 ? corrects[0] : undefined} onChange={(e) => {
                        if (e.target.value != undefined) setCorrects([e.target.value]);
                        else setCorrects([])
                    }}>
                        <Space direction="vertical" className='w-100'>
                            <Form.List name="answers" >
                                {(fields, { add, remove }) => (
                                    <>
                                        {fields.map(({ key, name, ...restField }, index) => (
                                            <div className="d-flex" key={key}>
                                                <Form.Item>
                                                    <Radio value={index} className='m-0' />
                                                </Form.Item>
                                                <Form.Item
                                                    {...restField}
                                                    name={[name, 'answer']}
                                                    className='w-100 mx-2'
                                                    rules={[{ required: true, message: 'Missing answer' }]}
                                                >
                                                    <Input className='w-100' placeholder="Answer" />
                                                </Form.Item>
                                                <Button onClick={() => {
                                                    remove(name);
                                                    if (corrects.includes(index)) {
                                                        setCorrects(corrects.filter(item => item !== index));
                                                    }
                                                }}>
                                                    <MinusCircleOutlined />
                                                </Button>
                                            </div>
                                        ))}
                                        <Form.Item>
                                            <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                                Add field
                                            </Button>
                                        </Form.Item>
                                    </>
                                )}
                            </Form.List>
                        </Space>
                    </Radio.Group>
                </IfElseElement>
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Save
                </Button>
                <Button type='default' onClick={handleResetClick}>
                    Reset
                </Button>
            </Form.Item>
        </Form >
    )
}

export default QuizFormNew