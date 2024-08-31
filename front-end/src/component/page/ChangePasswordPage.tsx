import { Button, Form, FormProps, Input } from "antd";
import { AuthRequest } from "./LoginPage";
import AccountPasswordRequest from "../../model/request/update/AccountPasswordRequest";
import { toast } from "react-toastify";
import AccountService from "../../service/AccountService";

const ChangePasswordPage = () => {
  const [form] = Form.useForm<AccountPasswordRequest>();
  const onFinish: FormProps<AccountPasswordRequest>["onFinish"] = (values) => {
    AccountService.changePassword(values).then((d) => {
      if (d.success) {
        toast.success("Changed password successfully");
        form.resetFields();
      } else {
        toast.error(d.message_error);
      }
    });
  };

  const onFinishFailed: FormProps<AuthRequest>["onFinishFailed"] = (
    errorInfo
  ) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <>
      <div style={{ height: "75px" }}></div>
      <div className="d-flex justify-content-center">
        <Form onFinish={onFinish} onFinishFailed={onFinishFailed} form={form}>
          <Form.Item<AccountPasswordRequest>
            label="Password"
            name="password"
            rules={[{ required: true, message: "Please input your password!" }]}
          >
            <Input.Password />
          </Form.Item>

          <Form.Item<AccountPasswordRequest>
            label="New Password"
            name="new_password"
            rules={[
              { required: true, message: "Please input your new password!" },
            ]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item<AccountPasswordRequest>
            label="Confirm Password"
            name="confirm_password"
            rules={[
              {
                required: true,
                message: "Please input your confirm password!",
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
      </div>
    </>
  );
};

export default ChangePasswordPage;
