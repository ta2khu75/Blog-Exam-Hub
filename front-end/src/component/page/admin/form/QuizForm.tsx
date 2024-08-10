import { Button, Form, FormProps, Input, Select } from "antd";
import QuizService from "../../../../service/QuizService";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import ExamResponse from "../../../../response/ExamResponse";
import QuizResponse from "../../../../response/QuizResponse";
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
  const [form] = Form.useForm<QuizRequest>();
  const [quizTypes, setQuizTypes] = useState<string[]>([]);
  useEffect(() => {
    fetchReadAllQuizTypes();
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
  const fetchReadAllQuizTypes = () => {
    QuizService.readAllQuizType().then((data) => {
      if (data.success) {
        setQuizTypes(data.data);
      }
    });
  };
  const onFinish: FormProps<QuizRequest>["onFinish"] = (values) => {
    if (!values.id) {
      QuizService.create(values).then((data) => {
        if (data.success) {
          toast.success("Successfully to create");
          resetFields();
        } else {
          toast.error(data.message_error);
        }
      });
    } else {
      QuizService.update(values.id, values).then((data) => {
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
        label="Questiton"
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
          options={quizTypes.map((e) => ({
            value: e,
            label: <span>{e}</span>,
          }))}
        />
      </Form.Item>
      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default QuizForm;
