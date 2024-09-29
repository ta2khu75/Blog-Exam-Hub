import { useEffect, useState } from "react"
import RoleService from "../../../../service/RoleService"
import TableElement from "../../../element/TableElement"
import ModalElement from "../../../element/ModalElement"
import { Button, Checkbox, Form, FormProps, Input } from "antd"
import RoleRequest from "../../../../model/request/RoleRequest"
import PermissionGroupService from "../../../../service/PermissionGroupService"

const ManagerRoleChild= () => {
    const [form] = Form.useForm<RoleRequest>();
    const [roles, setRoles] = useState<RoleResponse[]>([])
    const [permissionGroups, setPermissionGroups] = useState<PermissionGroupResponse[]>([])
    const [role, setRole] = useState<RoleResponse>()
    const onFinish: FormProps<RoleRequest>['onFinish'] = (values) => {
        if (role) {
            RoleService.update(role.id, values).then(d => {
                if (d.success) {
                    setRoles(roles => {
                        roles.map(role => {
                            if (role.id == d.data.id) {
                                return d.data;
                            } return role;
                        }); return roles
                    })
                    handleCancelClick()
                }
            })
        } else {
            RoleService.create(values).then(d => {
                if (d.success) {
                    setRoles(roles => { roles.push(d.data); return roles; })
                    handleCancelClick()
                }
            })
        }
    };

    const onFinishFailed: FormProps<RoleRequest>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
    const [open, setOpen] = useState(false);
    useEffect(() => {
        fetchAllRole();
        fetchAllPermissionGroup();
    }, [])
    useEffect(() => {
        if (role) {
            form.setFieldsValue(role)
        } else {
            form.setFieldsValue({})
        }
    }, [role])
    const fetchAllRole = () => {
        RoleService.readAll().then(d => {
            if (d.success) {
                setRoles(d.data);
            }
        })
    }
    const fetchAllPermissionGroup = () => {
        PermissionGroupService.readAll().then(d => {
            if (d.success) {
                setPermissionGroups(d.data)
            }
        });
    }
    const handleEditClick = (d: RoleResponse) => {
        setRole(d);
        setOpen(true);
    }
    const handleCancelClick = () => {
        setOpen(false);
        setRole(undefined);
    }
    return (
        <div>
            <h2>Manager Role</h2>
            <ModalElement width={1500} open={open} handleCancel={handleCancelClick} >
                <Form
                    form={form}
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                >
                    <Form.Item<RoleRequest>
                        label="Name"
                        name={'name'}
                    >
                        <Input disabled />
                    </Form.Item>
                    <Form.Item<RoleRequest>
                        label="Permissions"
                        name={'permission_ids'}
                    >
                        <Checkbox.Group className="d-block mb-5">
                            <table className="w-100">
                                {permissionGroups?.map(permissionGroup => { return (<tr key={`row-${permissionGroup.name}`} ><th>{permissionGroup.name}</th>{permissionGroup.permissions.map(permission => <td key={`cell-${permission.id}`}><Checkbox value={permission.id}>{permission.name}</Checkbox></td>)}</tr>) })}
                            </table >
                        </Checkbox.Group>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            {role ? "Update" : "Create"}
                        </Button>
                    </Form.Item>
                </Form>
            </ModalElement>
            <TableElement visiableColumns={["name"]} showIndex={true} array={roles} handleEditClick={handleEditClick} />
        </div>
    )
}

export default ManagerRoleChild;