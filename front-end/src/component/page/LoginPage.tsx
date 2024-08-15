import type { FormProps } from "antd";
import { Button, Form, Input } from "antd";
import AuthService from "../../service/AuthService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useAppDispatch } from "../../redux/hooks";
import { setAccount } from "../../redux/slice/accountSlice";

export type AuthRequest = {
  email?: string;
  password?: string;
};

const LoginPage = () => {
  const dispatch=useAppDispatch()
  const navigate=useNavigate();
  const onFinish: FormProps<AuthRequest>["onFinish"] = (values) => {
    AuthService.login(values).then((d) => {
        if(d.success){
          dispatch(setAccount(d.data))
            toast.success("login successful");
            navigate("/")
        }else{
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
    <div
      className="d-flex justify-content-center"
      style={{ marginTop: "40px" }}
    >
      <Form
        className="mt-5"
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600 }}
        initialValues={{ remember: true }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item<AuthRequest>
          label="Email"
          name="email"
          rules={[{ required: true, message: "Please input your username!" }]}
        >
          <Input />
        </Form.Item>

        <Form.Item<AuthRequest>
          label="Password"
          name="password"
          rules={[{ required: true, message: "Please input your password!" }]}
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
  );
};

export default LoginPage;
