import { Button, DatePicker, Form, FormProps, Input } from "antd";
import { toast } from "react-toastify";
import AccountService from "../../../service/AccountService";
import { useEffect } from "react";
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
const dateFormat = 'YYYY-MM-DD';
type Props = {
  account: AccountDetailsResponse;
  setAccount: React.Dispatch<React.SetStateAction<AccountDetailsResponse | undefined>>;
}
dayjs.extend(customParseFormat);
const ChangeInfoForm = ({ account, setAccount }: Props) => {
  const [form] = Form.useForm<AccountInfoRequest>();
  useEffect((
  ) => {
    if (account) {
      form.setFieldsValue({ ...account, birthday: dayjs(account.birthday, dateFormat) })
    }
  }, [account, form])
  const onFinish: FormProps<AccountInfoRequest>["onFinish"] = (values) => {
    AccountService.updateMyInfo(values).then((d) => {
      if (d.success) {
        setAccount(account => {
          return { ...account, ...d.data, blog_count: account?.blog_count ?? 0, exam_count: account?.exam_count ?? 0, follow_count: account?.follow_count ?? 0 }
        })
        toast.success("Update info successfully");
      } else {
        toast.error(d.message_error);
      }
    });
  };
  return (
    <Form layout="vertical" onFinish={onFinish} form={form}>
      <Form.Item<AccountInfoRequest>
        label="Username"
        name="username"
        rules={[{ required: true, message: "Please input your new username!" }]}
      >
        <Input />
      </Form.Item>
      <div className="row">
        <Form.Item<AccountInfoRequest>
          label="First Name"
          name="first_name"
          className="col-6"
          rules={[
            { required: true, message: "Please input your new first name!" },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item<AccountInfoRequest>
          label="Last Name"
          name="last_name"
          className="col-6"
          rules={[
            {
              required: true,
              message: "Please input your new last name!",
            },
          ]}
        >
          <Input />
        </Form.Item>
      </div>
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
  );
};

export default ChangeInfoForm;
