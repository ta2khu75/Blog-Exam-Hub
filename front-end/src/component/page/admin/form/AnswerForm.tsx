import { Button, Form, FormProps, Input, Radio } from "antd"
import AnswerService from "../../../../service/AnswerService";
import { useEffect } from "react";
import { toast } from "react-toastify";
import QuizResponse from "../../../../response/QuizResponse";
import AnswerResponse from "../../../../response/AnswerResponse";
export type AnswerRequest = {
    id?: number,
    answer?: string,
    correct?: boolean;
    quiz_id?: number
}
type Props = {
    quizResponse: QuizResponse
    answerResponse?: AnswerResponse 
    refresh: () => void
}
const AnswerForm = ({ answerResponse, quizResponse, refresh }: Props) => {
    const [form] = Form.useForm<AnswerRequest>()
    useEffect(() => {
        if (answerResponse) {
            form.setFieldsValue(answerResponse);
        } else {
            form.resetFields();
            form.setFieldValue("quiz_id", quizResponse.id)
        }
    }, [answerResponse])
    const resetFields=()=>{
        refresh();
        form.resetFields();
        form.setFieldValue("quiz_id",quizResponse.id);
    }
    const onFinish: FormProps<AnswerRequest>['onFinish'] = (values) => {
        if (!values.id) {
            AnswerService.create(values).then((data) => {
                if (data.success) {
                    toast.success("Successfully to create")
                    resetFields();
                } else {
                    toast.error(data.message_error)
                }
            })
        } else {
            AnswerService.update(values.id, values).then((data) => {
                if (data.success) {
                    toast.success("successfully")
                    resetFields();
                } else {
                    toast.error(data.message_error)
                }
            })
        }
    };

    const onFinishFailed: FormProps<AnswerRequest>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
    return (
        <Form
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            form={form}
        >
            <Form.Item<AnswerRequest> label="Id" name={"id"} hidden>
                <Input />
            </Form.Item>
            <Form.Item<AnswerRequest> label="Quiz Id" hidden name={"quiz_id"} rules={[
                { required: true, message: "please input quiz id" }
            ]}>
                <Input />
            </Form.Item>
            <Form.Item<AnswerRequest> label="Answer" name={"answer"} rules={[
                { required: true, message: "please input title" }
            ]} >
                <Input />
            </Form.Item>
            <Form.Item<AnswerRequest> label="Correct" name={"correct"}>
                <Radio.Group>
                    <Radio value={true}> True</Radio>
                    <Radio value={false}> False</Radio>
                </Radio.Group>
            </Form.Item>
            {/* <Form.Item<AnswerRequest> label="Quiz Type" name={"correct"} rules={[
                { required: true, message: "please select quiz type" }
            ]} >
                <Radio.Group onChange={onChange} value={value}>
                    <Radio value={1}>A</Radio>
                    <Radio value={2}>B</Radio>
                </Radio.Group>
            </Form.Item> */}
            <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    )
}

export default AnswerForm