import { Button, Form, FormProps, Input, Select } from "antd";
import ExamService from "../../../../service/ExamService";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import PageResponse from "../../../../response/PageResponse";
import ExamResponse from "../../../../response/ExamResponse";
import { useNavigate } from "react-router-dom";
import TextArea from "antd/es/input/TextArea";
import ExamCartElement from "../../../element/ExamCartElement";

export type ExamRequest = {
  id?: number;
  title?: string;
  description?: string;
  exam_type?: string;
  exam_level?: string;
}

const ExamCrud = () => {
  const [image, setImage] = useState<File>()
  const [errorImage, setErrorImage] = useState(false);
  const [imageUrl, setImageUrl] = useState<string>()
  const [examTypes, setExamTypes] = useState<string[]>([])
  const [examLevels, setExamLevels] = useState<string[]>([])
  const [examResponsePage, setExamResponsePage] = useState<PageResponse<ExamResponse>>();
  const [form] = Form.useForm<ExamRequest>();
  const navigate = useNavigate()
  useEffect(() => {
    fetchInitSelections()
    fetchReadPageExam()
  }, []);
  const fetchReadPageExam = () => {
    ExamService.readPage().then((data) => {
      if (data.success) setExamResponsePage(data.data)
    });
  }
  const fetchInitSelections = () => {
    ExamService.readAllExamLevel().then((d) => { setExamLevels(d.data) });
    ExamService.readAllExamType().then((d) => { setExamTypes(d.data) });
  }
  const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setImage(e.target.files[0])
      setImageUrl(URL.createObjectURL(e.target.files[0]))
    }
  }
  const handleViewClick = (data: ExamResponse) => {
    navigate("/admin/exam-view/" + data.id)
  }
  const handleEditClick = (data: ExamResponse) => {
    form.setFieldsValue(data);
  }
  const handleDeleteClick = (data: ExamResponse) => {
    ExamService.delete(data.id).then((d) => {
      if (d.status_code < 400) {
        toast.success("Successfully to delete");
        fetchReadPageExam();
      } else {
        toast.error(d.message_error);
      }
    })
  }
  const onFinish: FormProps<ExamRequest>['onFinish'] = (values) => {
    if (!values.id && image) {
      ExamService.create(values, image).then((data) => {
        if (data.success) {
          fetchReadPageExam();
          toast.success("Successfully to create")
        } else {
          toast.error(data.message_error)
        }
      })
    } else if (values.id) {
      ExamService.update(values.id, values, image).then((data) => {
        if (data.success) {
          fetchReadPageExam();
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
  return (
    <>
      <Form
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        form={form}
      >
        <Form.Item label="ID" name={'id'} hidden>
          <Input disabled />
        </Form.Item>
        <Form.Item<ExamRequest> label="Title" name={"title"} rules={[
          { required: true, message: "please input title" }
        ]} >
          <Input />
        </Form.Item>
        <Form.Item<ExamRequest> label="Exam Level" name={"exam_level"} rules={[
          { required: true, message: "please select exam level" }
        ]} >
          <Select options={examLevels.map((e) => ({ value: e, label: <span>{e}</span> }))} />
        </Form.Item>
        <Form.Item<ExamRequest> label="Exam Type" name={"exam_type"} rules={[
          { required: true, message: "please select exam type" }
        ]} >
          <Select options={examTypes.map((e) => ({ value: e, label: <span>{e}</span> }))} />
        </Form.Item>
        <Form.Item<ExamRequest> label="Description" name={"description"} rules={[
          { required: true, message: "please input description" }
        ]} >
          <TextArea rows={4} />
        </Form.Item>
        {errorImage && <p className="text-danger">Please upload image</p>}
        <input onChange={(e) => handleUploadChange(e)} type="file" /><br />
        <div className="d-flex justify-content-center">
          <img src={imageUrl} alt="" width={"300px"} height={"200px"} />
        </div>
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
        <div className="row">
          {examResponsePage?.content?.map((examResponse) => <ExamCartElement handleDeleteClick={handleDeleteClick} handleEditClick={handleEditClick} handleViewClick={handleViewClick} examResponse={examResponse} className="mt-4" />)}
        </div>
    </>
  )
}

export default ExamCrud