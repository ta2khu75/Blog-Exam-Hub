import type { FormProps } from "antd";
import { Button, DatePicker, Form, Input } from "antd";
import { toast } from "react-toastify";
import { useEffect } from "react";
import AccountService from "../../service/AccountService";
import { Link, useNavigate } from "react-router-dom";
import { AccountRequest } from "../../model/request/AccountRequest";

const AccountCrud = () => {
  const [form] = Form.useForm<AccountRequest>();
  const navigate = useNavigate()
  useEffect(() => { }, []);
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
    <section className="vh-100" style={{ backgroundColor: '#eee' }}>
      <div className="container h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-lg-12 col-xl-11">
            <div className="card text-black" style={{ borderRadius: 25 }}>
              <div className="card-body p-md-5">
                <div className="row justify-content-center">
                  <div className="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                    <div className="text-center h1 fw-bold mx-1 mx-md-4">Register</div>
                    <Form form={form} layout="vertical" onFinish={onFinish} onFinishFailed={onFinishFailed}>
                      <Form.Item<AccountRequest>
                        label="Email"
                        name={"email"}
                        rules={[
                          { required: true, message: "Please enter your email" },
                          { type: "email", message: "Please enter a valid email address" },
                        ]}
                      >
                        <Input size="large" />
                      </Form.Item>
                      <div className="row">
                        <div className="col-md-6 mb-2">
                          <Form.Item<AccountRequest>
                            label="First Name"
                            name={"first_name"}
                            rules={[
                              { required: true, message: "Please enter your first name" },
                            ]}
                          >
                            <Input size="large" />
                          </Form.Item>
                        </div>
                        <div className="col-md-6 mb-2">
                          <Form.Item<AccountRequest>
                            label="Last Name"
                            name={"last_name"}
                            rules={[
                              { required: true, message: "Please enter your last name" },
                            ]}
                          >
                            <Input size="large" />
                          </Form.Item>
                        </div>
                        <div className="col-md-6 mb-2">
                          <Form.Item<AccountRequest>
                            label="Password"
                            name={"password"}
                            rules={[
                              { required: true, message: "Please input your password" },
                              {
                                min: 3,
                                message: "Password must be at least 3 characters long",
                              },
                            ]}
                          >
                            <Input.Password size="large" />
                          </Form.Item>
                        </div>
                        <div className="col-md-6 mb-2">
                          <Form.Item<AccountRequest>
                            label="Confirm Password"
                            name={"confirm_password"}
                            dependencies={['password']}
                            rules={[
                              { required: true, message: "Please confirm your password" },
                              {
                                min: 3,
                                message: "Confirm Password must be at least 3 characters long",
                              },
                              ({ getFieldValue }) => ({
                                validator(_, value) {
                                  if (!value || getFieldValue('password') === value) {
                                    return Promise.resolve();
                                  }
                                  return Promise.reject(new Error('Passwords do not match'));
                                },
                              }),
                            ]}
                          >
                            <Input.Password size="large" />
                          </Form.Item>
                        </div>
                      </div>
                      <Form.Item<AccountRequest>
                        label="Birthday"
                        name={"birthday"}
                        rules={[
                          { required: true, message: "Please select your birthday" }
                        ]}
                      >
                        <DatePicker size="large" className="w-100" />
                      </Form.Item>
                      <div className="d-flex justify-content-between">
                        <Form.Item>
                          <Button type="primary" htmlType="submit">
                            Register
                          </Button>
                        </Form.Item>
                        <p className="small fw-bold pt-1">Already have an account? <Link to="/login" className="link-danger">Login</Link></p>
                      </div>
                    </Form>

                  </div>
                  <div className="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp" className="img-fluid" alt="Sample image" />
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

export default AccountCrud;
