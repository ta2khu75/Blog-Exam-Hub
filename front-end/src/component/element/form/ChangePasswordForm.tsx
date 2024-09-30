import { Button, Form, FormProps, Input } from "antd";
import { toast } from "react-toastify";
import AccountService from "../../../service/AccountService";

const ChangePasswordForm = () => {
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

  return (
    <Form layout="vertical" onFinish={onFinish} form={form}>
      <Form.Item<AccountPasswordRequest>
        label="Password"
        name="password"
        rules={[{ required: true, message: "Please input your password!" }]}
      >
        <Input.Password size="large" />
      </Form.Item>

      <Form.Item<AccountPasswordRequest>
        label="New Password"
        name="new_password"
        rules={[
          { required: true, message: "Please input your new password!" },
        ]}
      >
        <Input.Password size="large" />
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
        <Input.Password size="large" />
      </Form.Item>
      <Form.Item className="d-flex justify-content-center">
        <Button type="primary" htmlType="submit">
          Change Password
        </Button>
      </Form.Item>
    </Form>
  );
};

export default ChangePasswordForm;
