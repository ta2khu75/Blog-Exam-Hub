import { Button, Form, FormProps, Input, InputNumber, Radio, Select } from "antd"
import { useEffect, useRef, useState } from "react"
import ExamService from "../../../service/ExamService";
import { toast } from "react-toastify";
import { AccessModifier } from "../../../model/AccessModifier";
import { ExamLevel } from "../../../model/ExamLevel";
import TextArea from "antd/es/input/TextArea";
import { useAppDispatch } from "../../../redux/hooks";
import { resetQuiz } from "../../../redux/slice/quizSlice";
import { ExamStatus } from "../../../model/ExamStatus";
import { useNavigate } from "react-router-dom";
type Props = {
    id?: string;
    exam?: ExamResponse,
    quizzes: QuizRequest[],
    examCategories?: ExamCategoryResponse[]
}
const ExamFormNew = ({ id, exam, quizzes, examCategories }: Props) => {
    const dispatch = useAppDispatch()
    const navigate=useNavigate()
    const fileInputRef = useRef<HTMLInputElement | null>(null)
    const [image, setImage] = useState<File>()
    const [imageUrl, setImageUrl] = useState<string>()
    const [errorImage, setErrorImage] = useState(false);
    const [form] = Form.useForm<ExamRequest>();
    useEffect(() => {
        if (exam) {
            form.setFieldsValue(exam);
            form.setFieldValue("exam_category_id", exam.exam_category.id)
        }
    }, [exam, form])
    const onFinish: FormProps<ExamRequest>['onFinish'] = (values) => {
        if (!exam?.id && image) {
            ExamService.create({ ...values, quizzes: quizzes }, image).then((response) => {
                if (response.success) {
                    handleResetClick();
                    toast.success("Successfully to create")
                    dispatch(resetQuiz())
                    navigate("/profile/manager-exam")
                } else {
                    toast.error(response.message_error)
                }
            })
        } else if (exam?.id) {
            ExamService.update(exam.id, { ...values, quizzes: quizzes }, image).then((response) => {
                if (response.success) {
                    handleResetClick();
                    toast.success("successfully to update")
                    dispatch(resetQuiz())
                } else {
                    toast.error(response.message_error)
                }
            })
        } else {
            setErrorImage(true)
        }
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
        <div className="row">
            <Form id={id}
                onFinish={onFinish}
                form={form}
                layout='vertical'
                className="col-md-6"
            >
                <Form.Item<ExamRequest> label="Title" name={"title"} rules={[
                    { required: true, message: "please input title" }
                ]} >
                    <Input />
                </Form.Item>
                <div className="row">
                    <Form.Item<ExamRequest> label="Duration" className="col-md-6" name={"duration"}
                        rules={[
                            { required: true, message: "please input time" }
                        ]} >
                        <InputNumber className="w-100" addonAfter="minutes" />
                    </Form.Item>
                    <Form.Item<ExamRequest> label="Exam Category" className="col-md-6" name={"exam_category_id"} rules={[
                        { required: true, message: "please select exam category" }
                    ]} >
                        <Select showSearch optionFilterProp="label" options={examCategories?.map((examCategory) => ({ value: examCategory.id, label: examCategory.name }))} />
                    </Form.Item>
                    <Form.Item<ExamRequest> label="Access Modifier" className="col-md-6" name={"access_modifier"} rules={[
                        { required: true, message: "please input access_modifier" }
                    ]} >
                        <Radio.Group>
                            {Object.keys(AccessModifier).map(access => <Radio key={`radio-${access}`} value={access}>{access}</Radio>)}
                        </Radio.Group>
                    </Form.Item>
                    <Form.Item<ExamRequest> label="Exam status" className="col-md-6" name={"exam_status"}  >
                        <Radio.Group disabled={exam?.exam_status===ExamStatus.COMPLETED}>
                            {Object.keys(ExamStatus).map(status => <Radio key={`radio-${status}`} value={status}>{status}</Radio>)}
                        </Radio.Group>
                    </Form.Item>
                </div>
                <Form.Item<ExamRequest> label="Exam Level" name={"exam_level"} rules={[
                    { required: true, message: "please select exam level" }
                ]} >
                    <Radio.Group>
                        {Object.keys(ExamLevel).map(level => <Radio key={`radio-${level}`} value={level}>{level}</Radio>)}
                    </Radio.Group>
                </Form.Item>
                <Form.Item<ExamRequest> label="Description" name={"description"} rules={[
                    { required: true, message: "please input description" }
                ]} >
                    <TextArea rows={4} />
                </Form.Item>
                {errorImage && <p className="text-danger">Please upload image</p>}
                <input onChange={(e) => handleUploadChange(e)} ref={fileInputRef} type="file" /><br />
                <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                    <Button onClick={handleResetClick} className="ml-2">
                        Reset
                    </Button>
                </Form.Item>
            </Form>
            <div className="col-md-6">
                {imageUrl &&
                    <div className="d-flex h-100 justify-content-center align-items-center">
                        <img src={imageUrl} className="w-100" />
                    </div>
                }
                {
                    !imageUrl && <div></div>
                }
            </div>
        </div>
    )
}

export default ExamFormNew