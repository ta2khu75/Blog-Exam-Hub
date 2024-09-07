import type { FormProps } from 'antd';
import { Button, Form, Input, Pagination, Radio } from 'antd';
import AccountService from '../../../../service/AccountService';
import { toast } from 'react-toastify';
import { useEffect, useState } from 'react';
import TableElement from '../../../element/TableElement';
import ModalElement from '../../../element/ModalElement';
import useDebounce from '../../../../hook/useDebounce';
import RoleService from '../../../../service/RoleService';

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
  const [page, setPage] = useState(1);
  const [search, setSearch] = useState("");
  const debouncedSearch = useDebounce(search);
  const [accountPage, setAccountPage] = useState<PageResponse<AccountDetailsResponse>>();
  const [roles, setRoles] = useState<RoleResponse[]>([]);
  useEffect(() => {
    fetchPageAccount()
  }, [page, debouncedSearch])
  useEffect(() => {
    fetchAllRole()
  }, [])
  useEffect(() => {
    if (account) {
      form.setFieldsValue(account)
      form.setFieldValue("role_id",account.role.id)
    }
  }, [account, form]);
  const fetchPageAccount = () => {
    AccountService.readPage(search, page).then((d) => {
      if (d.success) {
        setAccountPage(d.data)
      }
    })
  }
  const fetchAllRole = () => {
    RoleService.readAll().then(d => {
      if (d.success) {
        setRoles(d.data)
      }
    })
  }
  const onFinish: FormProps<AccountStatusRequest>['onFinish'] = (values) => {
    if (account) {
      AccountService.updateStatus(account.id, values).then((data) => {
        if (data.success) {
          const updatedAccounts = accountPage?.content.map((account) => {
            if (account.id === data.data.id) {
              return data.data
            } return account;
          })
          setAccountPage((prev) => {
            if (!prev) return prev;
            return { ...prev, content: updatedAccounts || [] };
          });
          toast.info("successfully")
        }
      })
    }
  };

  const onFinishFailed: FormProps<AccountStatusRequest>['onFinishFailed'] = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const handleEditClick = (data: AccountDetailsResponse) => {
    setAccount(data)
    setOpenEdit(true);
  }
  const handleCancelClick = () => {
    setOpenEdit(false);
  }
  const handlePageChange = (page: number) => {
    setPage(page)
    fetchPageAccount();
  }
  const handleSearchChange = (e: string) => {
    setSearch(e);
    setPage(1)
  }
  return (
    <>
      <Input size='large' className='mb-5' placeholder='Search' value={search} type='text' onChange={(e) => handleSearchChange(e.target.value)} />
      <ModalElement width={1500} handleCancel={handleCancelClick} open={openEdit}>
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
          <Form.Item<AccountStatusRequest>
            label="Role"
            name={'role_id'}
          >
            <Radio.Group>
              {roles.map(role => (
                <Radio key={`radio-role-${role.id}`} value={role.id}>{role.name}</Radio>
              ))}
            </Radio.Group>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Update
            </Button>
          </Form.Item>
        </Form>
      </ModalElement>
      {accountPage?.content && <>
        <TableElement visiableColumns={["username", "email", "enabled", "non_locked", "role"]} showIndex={true} array={accountPage.content} handleEditClick={handleEditClick} />
        <Pagination align="center" onChange={(e) => handlePageChange(e)} defaultCurrent={page} defaultPageSize={10} total={accountPage.total_elements} /></>}
    </>
  )
}

export default AccountCrud