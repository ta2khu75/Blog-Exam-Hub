import type { FormProps } from "antd";
import { Button, Form, Input } from "antd";
import AuthService from "../../service/AuthService";
import { toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";
import { useAppDispatch } from "../../redux/hooks";
import { setAccount } from "../../redux/slice/accountSlice";

export type AuthRequest = {
  email?: string;
  password?: string;
};

const LoginPage = () => {
  const dispatch = useAppDispatch()
  const navigate = useNavigate();
  const onFinish: FormProps<AuthRequest>["onFinish"] = (values) => {
    AuthService.login(values).then((d) => {
      if (d.success) {
        dispatch(setAccount(d.data))
        toast.success("login successful");
        navigate("/")
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
    <section className="vh-100" style={{ backgroundColor: '#eee' }}>
      <div className="container h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-lg-12 col-xl-11">
            <div className="card text-black" style={{ borderRadius: 25 }}>
              <div className="card-body p-md-5">
                <div className="row justify-content-center">
                  <div className="col-md-10 col-lg-6 col-xl-6 d-flex align-items-center">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" className="img-fluid" alt="Sample image" />
                  </div>
                  <div className="col-md-10 col-lg-6 col-xl-6">
                    <div className="text-center h1 fw-bold mx-1 mx-md-4">Login</div>
                    <Form
                      layout="vertical"
                      onFinish={onFinish}
                      onFinishFailed={onFinishFailed}
                    >
                      <Form.Item<AuthRequest>
                        label="Email"
                        name="email"
                        rules={[{ required: true, message: "Please input your username!" }]}
                      >
                        <Input size="large" placeholder="Enter your email" />
                      </Form.Item>

                      <Form.Item<AuthRequest>
                        label="Password"
                        name="password"
                        rules={[{ required: true, message: "Please input your password!" }]}
                      >
                        <Input.Password size="large" placeholder="Enter your password" />
                      </Form.Item>
                      <div className="d-flex justify-content-between">
                        <Form.Item>
                          <Button type="primary" size="large" htmlType="submit">
                            Login
                          </Button>
                        </Form.Item>
                        <Link to={"/forgot-password"} className="link-primary">Forgot password?</Link>
                      </div>
                      <p className="small fw-bold pt-1">Don't have an account? <Link to="/register" className="link-danger">Register</Link></p>
                    </Form>
                    <div>
                      <p className="text-center fw-bold mx-3 mb-0 text-muted">OR</p>
                      <div className="d-flex justify-content-between">
                        <a data-mdb-ripple-init className="btn btn-primary btn-lg btn-block" style={{ backgroundColor: '#3b5998' }} href="#!" role="button">
                          <i className="fab fa-facebook-f me-2" />Continue with Facebook
                        </a>
                        <a data-mdb-ripple-init className="btn btn-danger btn-lg btn-block" style={{ backgroundColor: '#dd4b39' }} href="#!" role="button">
                          <i className="fab fa-facebook-f me-2" />Continue with Google
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default LoginPage;
