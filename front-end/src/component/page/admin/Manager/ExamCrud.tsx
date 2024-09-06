import { Button, Form, FormProps, Input, InputNumber, Radio, Select } from "antd";
import ExamService from "../../../../service/ExamService";
import { useEffect, useRef, useState } from "react";
import { toast } from "react-toastify";
import PageResponse from "../../../../model/response/PageResponse";
import ExamResponse from "../../../../model/response/ExamResponse";
import { useNavigate } from "react-router-dom";
import TextArea from "antd/es/input/TextArea";
import ExamCartElement from "../../../element/ExamCartElement";
import ExamRequest from "../../../../model/request/ExamRequest";
import { ExamLevel } from "../../../../model/ExamLevel";
import { AccessModifier } from "../../../../model/AccessModifier";
import ExamCategoryService from "../../../../service/ExamCategoryService";
import ExamForm from "../form/ExamForm";
const ExamCrud = () => {
  // const [image, setImage] = useState<File>()
  // const [imageUrl, setImageUrl] = useState<string>()
  // const fileInputRef = useRef<HTMLInputElement | null>(null)
  const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>()
  // const [errorImage, setErrorImage] = useState(false);
  const [exam, setExam] = useState<ExamResponse>();
  const [examResponsePage, setExamResponsePage] = useState<PageResponse<ExamResponse>>();
  // const [form] = Form.useForm<ExamRequest>();
  const navigate = useNavigate()
  useEffect(() => {
    fetchPageExam();
    fetchAllExamCategory();
  }, []);
  const fetchAllExamCategory = () => {
    ExamCategoryService.readAll().then((response) => {
      if (response.success) setExamCategories(response.data)
    });
  }
  const fetchPageExam = () => {
    ExamService.readPage().then((data) => {
      if (data.success) setExamResponsePage(data.data)
    });
  }
  // const resetForm = () => {
  //   form.resetFields();
  //   setImage(undefined);
  //   setImageUrl(undefined);
  //   if (fileInputRef.current) {
  //     fileInputRef.current.value = '';
  //   }
  // }
  // const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   if (e.target.files) {
  //     setImage(e.target.files[0])
  //     setImageUrl(URL.createObjectURL(e.target.files[0]))
  //   }
  // }
  const handleViewClick = (data: ExamResponse) => {
    navigate("/admin/exam-view/" + data.id)
  }
  const handleEditClick = (data: ExamResponse) => {
    setExam(data);
    // form.setFieldsValue(data);
  }
  const handleDeleteClick = (data: ExamResponse) => {
    ExamService.delete(data.id).then((d) => {
      if (d.status_code < 400) {
        toast.success("Successfully to delete");
        fetchPageExam();
        // resetForm();
      } else {
        toast.error(d.message_error);
      }
    })
  }
  // const onFinish: FormProps<ExamRequest>['onFinish'] = (values) => {
  //   if (!exam?.id && image) {
  //     ExamService.create(values, image).then((data) => {
  //       if (data.success) {
  //         fetchReadPageExam();
  //         toast.success("Successfully to create")
  //         resetForm();
  //       } else {
  //         toast.error(data.message_error)
  //       }
  //     })
  //   } else if (exam?.id) {
  //     ExamService.update(exam.id, values, image).then((data) => {
  //       if (data.success) {
  //         fetchReadPageExam();
  //         toast.success("successfully")
  //         form.setFieldsValue({})
  //       } else {
  //         toast.error(data.message_error)
  //       }
  //     })
  //   } else {
  //     setErrorImage(true)
  //   }
  // };

  // const onFinishFailed: FormProps<ExamRequest>['onFinishFailed'] = (errorInfo) => {
  //   console.log('Failed:', errorInfo);
  // };
  return (
    <>
      {/* <Form
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
          <Button onClick={resetForm} className="ml-2">
            Reset
          </Button>
        </Form.Item>
      </Form> */}
      <ExamForm exam={exam} examCategories={examCategories} setExamResponsePage={setExamResponsePage} />
      <div className="row">
        {examResponsePage?.content?.map((examResponse) => <ExamCartElement key={`exam-cart-${examResponse.id}`} handleDeleteClick={handleDeleteClick} handleEditClick={handleEditClick} handleViewClick={handleViewClick} examResponse={examResponse} className="mt-4" />)}
      </div>
    </>
  )
}

export default ExamCrud