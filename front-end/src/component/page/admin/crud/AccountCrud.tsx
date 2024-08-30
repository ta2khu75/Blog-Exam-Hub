import type { FormProps} from 'antd';
import { Button,  Form, Input} from 'antd';
import AccountService from '../../../../service/AccountService';
import { toast } from 'react-toastify';
import { useEffect, useState } from 'react';
import AccountResponse from '../../../../model/response/AccountResponse';
import PageResponse from '../../../../model/response/PageResponse';
import TableElement from '../../../element/TableElement';

export type AccountRequest = {
  id: number;
  email?: string;
  password?: string;
  confirm_password?: string;
};
const AccountCrud = () => {
  const [form] = Form.useForm<AccountRequest>();
  const [accountResponsePage, setAccountResponsePage]=useState<PageResponse<AccountResponse>>();
  useEffect(()=>{
    fetchReadPageAccount()
  },[])

  const onFinish: FormProps<AccountRequest>['onFinish'] = (values) => {
    AccountService.create(values).then((data) => {
      if(data.success){
      toast.info("successfully")
      fetchReadPageAccount();
      }
    })
  };

  const onFinishFailed: FormProps<AccountRequest>['onFinishFailed'] = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };
  const fetchReadPageAccount=()=>{
    AccountService.readPage().then((d) => {
      if(d.success){
        setAccountResponsePage(d.data)
      }
    })
  }
  const handleEditClick=(data:AccountResponse)=>{
    form.setFieldsValue(data);

  }
  return (
    <>
    <Form
    form={form}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <Form.Item label="ID" name={'id'} hidden>
        <Input disabled />
      </Form.Item>
      <Form.Item<AccountRequest>
        label="Email"
        name={'email'}
        rules={[{ required: true, message: "Please input your email" }, { type: 'email', message: 'Please input your email' }]}
      >
        <Input />
      </Form.Item>
      <Form.Item<AccountRequest>
        label="Password"
        name={'password'}
        rules={[{ required: true, message: "Please input your password" }, { min: 3, message: 'Please input your password have at least 3 characters' }]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item<AccountRequest>
        label="Confirm Password"
        name={'confirm_password'}
        rules={[{ required: true, message: "Please input your password" }, { min: 3, message: 'Please input your password have at least 3 characters' }]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
    {accountResponsePage?.content && <TableElement array={accountResponsePage.content} handleEditClick={handleEditClick}/>}
    </>
  )
}

export default AccountCrud