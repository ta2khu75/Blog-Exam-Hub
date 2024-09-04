import { Button, Form, FormProps, Input, Select } from "antd";
import QuizService from "../../../../service/QuizService";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import ExamResponse from "../../../../model/response/ExamResponse";
import QuizResponse from "../../../../model/response/QuizResponse";
import { QuizType } from "../../../../model/QuizType";

export type QuizRequest = {
  id?: number;
  question: string;
  quiz_type: string;
  exam_id: number;
};
type Props = {
  examResponse: ExamResponse;
  refresh: () => void;
  quizResponse?: QuizResponse;
};
const QuizForm = ({ examResponse, refresh, quizResponse }: Props) => {
  const [file, setFile] = useState<File>();
  const [form] = Form.useForm<QuizRequest>();
  useEffect(() => {
    if (quizResponse) {
      console.log(quizResponse);
      form.setFieldsValue(quizResponse);
    } else {
      console.log(quizResponse);
      form.resetFields();
      form.setFieldValue("exam_id", examResponse.id);
    }
  }, [quizResponse]);
  const resetFields = () => {
    refresh();
    form.resetFields();
    form.setFieldValue("exam_id", examResponse.id);
  };
  const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };
  const onFinish: FormProps<QuizRequest>["onFinish"] = (values) => {
    if (!values.id) {
      QuizService.create(values, file).then((data) => {
        if (data.success) {
          toast.success("Successfully to create");
          resetFields();
        } else {
          toast.error(data.message_error);
        }
      });
    } else {
      QuizService.update(values.id, values, file).then((data) => {
        if (data.success) {
          toast.success("successfully");
          refresh();
          resetFields();
        } else {
          toast.error(data.message_error);
        }
      });
    }
  };

  const onFinishFailed: FormProps<QuizRequest>["onFinishFailed"] = (
    errorInfo
  ) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <Form onFinish={onFinish} onFinishFailed={onFinishFailed} form={form}>
      <Form.Item<QuizRequest> label="id" name={"id"} hidden>
        <Input />
      </Form.Item>
      <Form.Item<QuizRequest>
        label="Exam Id"
        hidden
        name={"exam_id"}
        rules={[{ required: true, message: "please input exam id" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item<QuizRequest>
        label="Question"
        name={"question"}
        rules={[{ required: true, message: "please input title" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item<QuizRequest>
        label="Quiz Type"
        name={"quiz_type"}
        rules={[{ required: true, message: "please select quiz type" }]}
      >
        <Select
          options={Object.keys(QuizType).map((e) => ({
            value: e,
            label: <span>{e}</span>,
          }))}
        />
      </Form.Item>
      <Input type="file" onChange={(e) => handleUploadChange(e)} />
      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default QuizForm;
