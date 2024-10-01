import { useEffect, useState } from "react"
import RoleService from "../../../../service/RoleService"
import TableElement from "../../../element/TableElement"
import ModalElement from "../../../element/ModalElement"
import { Button, Checkbox, Form, FormProps, Input } from "antd"
import PermissionGroupService from "../../../../service/PermissionGroupService"
const ManagerRoleChild = () => {
    const [form] = Form.useForm<RoleRequest>();
    const [roles, setRoles] = useState<RoleDetailsResponse[]>([])
    const [permissionGroups, setPermissionGroups] = useState<PermissionGroupResponse[]>([])
    const [role, setRole] = useState<RoleDetailsResponse>()
    const [permissionGroupIds, setPermissionGroupIds] = useState<Array<number[]>>([]);
    // const [permissionIds, setPermissionIds] = useState<number[]>([])
    // const [testState, setTestState] = useState();
    const onFinish: FormProps<RoleRequest>['onFinish'] = (values) => {
        if (role) {
            console.log({ ...values, permission_ids: new Set([...values.permission_ids].flat()) });

            // RoleService.update(role.id, values).then(d => {
            //     if (d.success) {
            //         setRoles(
            //             roles.map(role => {
            //                 if (role.id == d.data.id) {
            //                     return d.data;
            //                 } return role;
            //             }))
            //         handleCancelClick()
            //     }
            // })
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
            // setPermissionIds([...role.permission_ids]);
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
    function getInnerArray(nestedArray: (number | number[])[]): number[] | undefined {
        // Iterate through the nested array
        for (const element of nestedArray) {
            // Check if the current element is an array
            if (Array.isArray(element)) {
                return element; // Return the inner array if found
            }
        }
        return undefined; // Return undefined if no inner array is found
    }
    function flattenNestedArray(nestedArray: (number | number[])[]): (number | number[])[] {
        const result: (number | number[])[] = [];

        // Iterate through each element in the nested array
        for (const element of nestedArray) {
            if (Array.isArray(element)) {
                // If the element is an array, spread its values into the result
                result.push(...element);
                result.push(element)
            } else {
                // If it's a number, just push it to the result
                result.push(element);
            }
        }

        return result;
    }
    const copyArrayNestedArray = (nestedArray: (number | number[])[]) => {
        const copiedArray: any[] = [];
        for (const item of nestedArray) {
            if (Array.isArray(item)) {
                copiedArray.push([...item]); // Shallow copy of nested array
            } else {
                copiedArray.push(item);
            }
        }
        return copiedArray;
    }
    const handlePermissionChange = (e: number[]) => {
        // console.log("change");
        // console.log(e);
        // setPermissionGroupsChecked(e);
        const groups = getInnerArray(copyArrayNestedArray(e));
        // const permissionGroup = [...permissionGroupIds]
        // console.log(groups);
        // console.log(permissionGroup);

        const permission_ids = flattenNestedArray(e)
        form.setFieldValue("permission_ids", permission_ids);

        if (groups != undefined) {
            setPermissionGroupIds([groups]);
        }
        // setPermissionIds(permission_ids);
    }

    return (
        <div>
            <h2>Manager Role</h2>
            <ModalElement width={1500} open={open} handleCancel={handleCancelClick} >
                <Form
                    form={form}
                    onFinish={onFinish}
                    layout="vertical"
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
                        <Checkbox.Group onChange={(e: number[]) => handlePermissionChange(e)} className="d-block mb-5">
                            <table className="w-100">
                                {permissionGroups?.map(permissionGroup => {
                                    return (<tr style={{ height: "50px" }} key={`row-${permissionGroup.name}`} ><th><Checkbox
                                        value={permissionGroup.permissions.map(permission => permission.id)}
                                    >{permissionGroup.name}</Checkbox></th>
                                        {permissionGroup.permissions.map(permission => <td key={`cell-${permission.id}`}><Checkbox value={permission.id}>{permission.name}</Checkbox></td>)}</tr>)
                                })}
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
        </div >
    )
}

export default ManagerRoleChild;