import { Button, Form, FormProps, Input } from 'antd'
import ExamCategoryService from '../../../../service/ExamCategoryService';
import { toast } from 'react-toastify';
import { useEffect } from 'react';
type Props = {
    examCategory?: ExamCategoryResponse;
    setExamCategories: React.Dispatch<React.SetStateAction<ExamCategoryResponse[]>>
}
const ExamCategoryForm = ({ examCategory, setExamCategories }: Props) => {
    const [form] = Form.useForm<ExamCategoryRequest>()
    useEffect(() => {
        if (examCategory) {
            form.setFieldsValue(examCategory)
        }
        else {
            form.resetFields()
        }
    }, [examCategory])
    const onFinish: FormProps<ExamCategoryRequest>['onFinish'] = (values) => {
        if (examCategory) {
            ExamCategoryService.update(examCategory.id, values).then((response) => {
                if (response.success) {
                    setExamCategories(examCategories => examCategories.map(examCategory => { if (examCategory.id === response.data.id) return response.data; return examCategory }))
                    toast.success("Update successfully")
                }
                else {
                    toast.error(response.message_error)
                }
            });
        }
        ExamCategoryService.create(values).then((response) => {
            if (response.success) {
                setExamCategories(examCategories => {
                    return [...examCategories, response.data]
                })
                toast.success("Create successfully")
            }
            else {
                toast.error(response.message_error)
            }
        });
    };
    const onFinishFailed: FormProps<ExamCategoryRequest>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
    return (
        <Form
            onFinish={onFinish}
            form={form}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item<ExamCategoryRequest>
                label="Name"
                name="name"
                rules={[{ required: true, message: 'Please input name!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    )
}

export default ExamCategoryForm