import type { FormProps } from 'antd';
import { Button, Form, Radio } from 'antd';
import AccountService from '../../../../service/AccountService';
import { toast } from 'react-toastify';
import { useEffect, useState } from 'react';
import PageResponse from '../../../../model/response/PageResponse';
import TableElement from '../../../element/TableElement';
import ModalElement from '../../../element/ModalElement';
import AccountDetailsResponse from '../../../../model/response/details/AccountDetailsResponse';
import { AccountStatusRequest } from '../../../../model/request/update/AccountStatusRequest';

export type AccountRequest = {
  id: number;
  email?: string;
  password?: string;
  confirm_password?: string;
};
const AccountCrud = () => {
  const [form] = Form.useForm<AccountStatusRequest>();
  const [openEdit, setOpenEdit] = useState(false);
  const [account, setAccount] = useState<AccountDetailsResponse>()
  const [accountResponsePage, setAccountResponsePage] = useState<PageResponse<AccountDetailsResponse>>();
  useEffect(() => {
    fetchReadPageAccount()
  }, [])
  useEffect(() => {
    if (account) {
      form.setFieldsValue(account)
    }
  }, [account]);
  const onFinish: FormProps<AccountStatusRequest>['onFinish'] = (values) => {
    if (account) {
      AccountService.update(account.id, values).then((data) => {
        if (data.success) {
          accountResponsePage?.content.map((account) => {
            if (account.id === data.data.id) {
              return data.data
            } return account;
          })
          toast.info("successfully")
        }
      })
    }
  };

  const onFinishFailed: FormProps<AccountStatusRequest>['onFinishFailed'] = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const fetchReadPageAccount = () => {
    AccountService.readPage().then((d) => {
      if (d.success) {
        setAccountResponsePage(d.data)
      }
    })
  }
  const handleEditClick = (data: AccountDetailsResponse) => {
    setAccount(data)
    setOpenEdit(true);
  }
  const handleCancelClick = () => {
    setOpenEdit(false);
  }
  return (
    <>

      <ModalElement handleCancel={handleCancelClick} open={openEdit}>
        <Form
          form={form}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        >
          <Form.Item<AccountStatusRequest>
            label="Enabled"
            name={'enabled'}
          >
            <Radio.Group>
              <Radio value={true}>True</Radio>
              <Radio value={false}>False</Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item<AccountStatusRequest>
            label="Non Locked"
            name={'non_locked'}
          >
            <Radio.Group>
              <Radio value={true}>True</Radio>
              <Radio value={false}>False</Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Update
            </Button>
          </Form.Item>
        </Form>
      </ModalElement>
      {accountResponsePage?.content && <TableElement visiableColumns={["username", "email", "enabled", "non_locked", "role"]} showIndex={true} array={accountResponsePage.content} handleEditClick={handleEditClick} />}
    </>
  )
}

export default AccountCrud