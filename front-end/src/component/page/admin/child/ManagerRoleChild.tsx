import { useEffect, useState } from "react"
import RoleService from "../../../../service/RoleService"
import TableElement from "../../../element/TableElement"
import ModalElement from "../../../element/ModalElement"
import { Button, Checkbox, CheckboxProps, Form, FormProps, Input } from "antd"
import PermissionGroupService from "../../../../service/PermissionGroupService"
import { CheckboxChangeEvent } from "antd/es/checkbox"
const ManagerRoleChild = () => {
    const [form] = Form.useForm<RoleRequest>();
    const [roles, setRoles] = useState<RoleDetailsResponse[]>([])
    const [permissionGroups, setPermissionGroups] = useState<PermissionGroupResponse[]>([])
    const [role, setRole] = useState<RoleDetailsResponse>()
    const [permission_ids, setPermission_ids] = useState<number[]>([]);
    const plainOptions = permissionGroups.flatMap(permissionGroup => permissionGroup.permissions.map(permission => permission.id));
    const checkAll = plainOptions.length === permission_ids.length;
    const indeterminate = permission_ids.length > 0 && permission_ids.length < plainOptions.length;

    const onFinish: FormProps<RoleRequest>['onFinish'] = (values) => {
        if (role) {
            RoleService.update(role.id, values).then(d => {
                if (d.success) {
                    setRoles(
                        roles.map(role => {
                            if (role.id == d.data.id) {
                                return d.data;
                            } return role;
                        }))
                    handleCancelClick()
                }
            })
        }
    };
    const [open, setOpen] = useState(false);
    useEffect(() => {
        fetchAllRole();
        fetchAllPermissionGroup();
    }, [])
    useEffect(() => {
        if (role) {
            form.setFieldsValue(role)
            setPermission_ids(role.permission_ids)
        } else {
            form.setFieldsValue({})
        }
    }, [role, form])
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
    const handleEditClick = (d: RoleDetailsResponse) => {
        setRole(d);
        setOpen(true);
    }
    const handleCancelClick = () => {
        setOpen(false);
        setRole(undefined);
    }
    const handlePermissionGroupNameClick = (permissionIds: number[]) => {
        const permission_ids: number[] = form.getFieldValue('permission_ids')
        if (permissionIds.every(permissionId => permission_ids.includes(permissionId))) {
            form.setFieldValue('permission_ids', permission_ids.filter(permission_id => !permissionIds.includes(permission_id)));
            setPermission_ids(form.getFieldValue("permission_ids"));
        } else {
            form.setFieldValue('permission_ids', [...new Set([...permission_ids, ...permissionIds])]);
            setPermission_ids(form.getFieldValue("permission_ids"));
        }
    }
    const onCheckAllChange: CheckboxProps['onChange'] = (e: CheckboxChangeEvent) => {
        form.setFieldValue("permission_ids", e.target.checked ? plainOptions : []);
        setPermission_ids(e.target.checked ? plainOptions : [])
    };
    return (
        <div>
            <h2>Manager Role</h2>
            <ModalElement width={1500} open={open} handleCancel={handleCancelClick} >
                <Form
                    form={form}
                    onFinish={onFinish}
                    layout="vertical"
                >
                    <Form.Item<RoleRequest>
                        label="Name"
                        name={'name'}
                    >
                        <Input disabled />
                    </Form.Item>
                    <Form.Item
                        label="Permissions"
                    >
                        <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}>Check all</Checkbox>
                        <Form.Item<RoleRequest> name={'permission_ids'}>
                            <Checkbox.Group className="d-block mb-5">
                                <table className="w-100">
                                    {permissionGroups?.map(permissionGroup => {
                                        const permissionIds = permissionGroup.permissions.map(permission => permission.id);
                                        return (<tr style={{ height: "50px" }} key={`row-${permissionGroup.name}`} ><th><Button type={permissionIds.every(permissionId => permission_ids.includes(permissionId)) ? "primary" : "default"} onClick={() => handlePermissionGroupNameClick(permissionIds)}
                                        >{permissionGroup.name} Group</Button></th>
                                            {permissionGroup.permissions.map(permission => <td key={`cell-${permission.id}`}><Checkbox value={permission.id}>{permission.name}</Checkbox></td>)}</tr>)
                                    })}
                                </table >
                            </Checkbox.Group>
                        </Form.Item>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            {role ? "Update" : "Create"}
                        </Button>
                    </Form.Item>
                </Form>
            </ModalElement>
            <TableElement visiableColumns={["name"]} showIndex={true} array={roles} handleEditClick={handleEditClick} />
        </div >
    )
}

export default ManagerRoleChild;