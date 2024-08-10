import type { FormProps } from "antd";
import { Button, Form, Input } from "antd";
import { toast } from "react-toastify";
import { useEffect } from "react";
import AccountService from "../../service/AccountService";
import { useNavigate } from "react-router-dom";
export type AccountRequest = {
  id: number;
  email?: string;
  password?: string;
  confirm_password?: string;
};
const AccountCrud = () => {
  const [form] = Form.useForm<AccountRequest>();
  const navigate=useNavigate()
  useEffect(() => {}, []);
  const onFinish: FormProps<AccountRequest>["onFinish"] = (values) => {
    AccountService.create(values).then((data) => {
      if (data.success) {
        toast.success("successfully");
        navigate("/login")
      }
    });
  };

  const onFinishFailed: FormProps<AccountRequest>["onFinishFailed"] = (
    errorInfo
  ) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <>
      <h1>Register</h1>
      <Form form={form} onFinish={onFinish} onFinishFailed={onFinishFailed}>
        <Form.Item label="ID" name={"id"} hidden>
          <Input disabled />
        </Form.Item>
        <Form.Item<AccountRequest>
          label="Email"
          name={"email"}
          rules={[
            { required: true, message: "Please input your email" },
            { type: "email", message: "Please input your email" },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item<AccountRequest>
          label="Password"
          name={"password"}
          rules={[
            { required: true, message: "Please input your password" },
            {
              min: 3,
              message: "Please input your password have at least 3 characters",
            },
          ]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item<AccountRequest>
          label="Confirm Password"
          name={"confirm_password"}
          rules={[
            { required: true, message: "Please input your password" },
            {
              min: 3,
              message: "Please input your password have at least 3 characters",
            },
          ]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
};

export default AccountCrud;
