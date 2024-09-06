import { Button, DatePicker, Form, FormProps, Input } from "antd";
import { toast } from "react-toastify";
import AccountService from "../../../../service/AccountService";
import AuthService from "../../../../service/AuthService";
import { useEffect, useState } from "react";
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
const dateFormat = 'YYYY-MM-DD';
dayjs.extend(customParseFormat);
const ChangeInfoChild = () => {
  const [form] = Form.useForm<AccountInfoRequest>();
  const [account, setAccount] = useState<AccountResponse>();
  useEffect(() => {
    fetchReadAccount();
  }, []);
  useEffect((
  ) => {
    if (account) {
      form.setFieldsValue({...account, birthday: dayjs(account.birthday, dateFormat)})
    }
  }, [account, form])
  const fetchReadAccount = () => {
    AuthService.myAccount().then((data) => {
      if (data.success) { setAccount(data.data); }
    });
  };
  const onFinish: FormProps<AccountInfoRequest>["onFinish"] = (values) => {
    AccountService.updateMyInfo(values).then((d) => {
      if (d.success) {
        setAccount(d.data)
        toast.success("Update info successfully");
      } else {
        toast.error(d.message_error);
      }
    });
  };

  const onFinishFailed: FormProps<AccountInfoRequest>["onFinishFailed"] = (
    errorInfo
  ) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <div className="d-flex justify-content-center">
      <Form className="w-75" layout="vertical" onFinish={onFinish} onFinishFailed={onFinishFailed} form={form}>
        <Form.Item<AccountInfoRequest>
          label="Username"
          name="username"
          rules={[{ required: true, message: "Please input your new username!" }]}
        >
          <Input />
        </Form.Item>

        <Form.Item<AccountInfoRequest>
          label="First Name"
          name="first_name"
          rules={[
            { required: true, message: "Please input your new first name!" },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item<AccountInfoRequest>
          label="Last Name"
          name="last_name"
          rules={[
            {
              required: true,
              message: "Please input your new last name!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item<AccountInfoRequest>
          label="Birthday"
          name="birthday"
          rules={[
            {
              required: true,
              message: "Please input your new birthday!",
            },
          ]}
        ><DatePicker className="w-100" format={dateFormat} />
          {/* <Input.Password /> */}
        </Form.Item>
        <Form.Item className="d-flex justify-content-center">
          <Button type="primary" htmlType="submit">
            Update
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default ChangeInfoChild;
