import { Button, Form, FormProps, Input, InputNumber, Radio, Select } from 'antd'
import React, { useEffect, useRef, useState } from 'react'
import ExamRequest from '../../../../model/request/ExamRequest';
import ExamResponse from '../../../../model/response/ExamResponse';
import ExamService from '../../../../service/ExamService';
import { ExamLevel } from '../../../../model/ExamLevel';
import TextArea from 'antd/es/input/TextArea';
import { AccessModifier } from '../../../../model/AccessModifier';
import PageResponse from '../../../../model/response/PageResponse';
import { toast } from 'react-toastify';
type Props = {
    exam?: ExamResponse,
    examCategories?: ExamCategoryResponse[]
    setExamResponsePage: React.Dispatch<React.SetStateAction<PageResponse<ExamResponse> | undefined>>
}
const ExamForm = ({ exam, examCategories, setExamResponsePage }: Props) => {
    const fileInputRef = useRef<HTMLInputElement | null>(null)
    const [image, setImage] = useState<File>()
    const [imageUrl, setImageUrl] = useState<string>()
    const [errorImage, setErrorImage] = useState(false);
    const [form] = Form.useForm<ExamRequest>();
    useEffect(() => {
        if (exam) form.setFieldsValue(exam)
    }, [exam, form])
    const onFinish: FormProps<ExamRequest>['onFinish'] = (values) => {
        if (!exam?.id && image) {
            ExamService.create(values, image).then((data) => {
                if (data.success) {
                    // fetchReadPageExam();
                    handleResetClick();
                    toast.success("Successfully to create")
                } else {
                    toast.error(data.message_error)
                }
            })
        } else if (exam?.id) {
            ExamService.update(exam.id, values, image).then((data) => {
                if (data.success) {
                    // fetchReadPageExam();
                    handleResetClick();
                    toast.success("successfully")
                } else {
                    toast.error(data.message_error)
                }
            })
        } else {
            setErrorImage(true)
        }
    };

    const onFinishFailed: FormProps<ExamRequest>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
    const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setImage(e.target.files[0])
            setImageUrl(URL.createObjectURL(e.target.files[0]))
        }
    }
    const handleResetClick = () => {
        form.resetFields();
        setImage(undefined);
        setImageUrl(undefined);
        if (fileInputRef.current) {
            fileInputRef.current.value = '';
        }
    }
    return (
        <Form
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            form={form}
        >
            <Form.Item<ExamRequest> label="Title" name={"title"} rules={[
                { required: true, message: "please input title" }
            ]} >
                <Input />
            </Form.Item>
            <Form.Item<ExamRequest> label="Duration" name={"duration"}
                rules={[
                    { required: true, message: "please input time" }
                ]} >
                <InputNumber addonAfter="minutes" />
            </Form.Item>
            <Form.Item<ExamRequest> label="Exam Level" name={"exam_level"} rules={[
                { required: true, message: "please select exam level" }
            ]} >
                <Select options={Object.keys(ExamLevel).map((e) => ({ value: e, label: <span>{e}</span> }))} />
            </Form.Item>
            <Form.Item<ExamRequest> label="Description" name={"description"} rules={[
                { required: true, message: "please input description" }
            ]} >
                <TextArea rows={4} />
            </Form.Item>
            <Form.Item<ExamRequest> label="Access Modifier" name={"access_modifier"} rules={[
                { required: true, message: "please input access_modifier" }
            ]} >
                <Radio.Group>
                    {Object.keys(AccessModifier).map(access => <Radio key={`radio-${access}`} value={access}>{access}</Radio>)}
                </Radio.Group>
            </Form.Item>
            <Form.Item<ExamRequest> label="Exam Category" name={"exam_category_id"} rules={[
                { required: true, message: "please select exam category" }
            ]} >
                <Select showSearch optionFilterProp="label" options={examCategories?.map((examCategory) => ({ value: examCategory.id, label: examCategory.name }))} />
            </Form.Item>
            {errorImage && <p className="text-danger">Please upload image</p>}
            <input onChange={(e) => handleUploadChange(e)} ref={fileInputRef} type="file" /><br />
            {imageUrl &&
                <div className="d-flex justify-content-center">
                    <img src={imageUrl} alt="" width={"300px"} height={"200px"} />
                </div>
            }
            <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
                <Button onClick={handleResetClick} className="ml-2">
                    Reset
                </Button>
            </Form.Item>
        </Form>
    )
}

export default ExamForm