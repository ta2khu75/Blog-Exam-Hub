import { useEffect, useState } from "react"
import { Checkbox } from "antd";
import PermissionGroupResponse from "../../../../model/response/PermissionGroupResponse";
import PermissionGroupService from "../../../../service/PermissionGroupService";
import AccountDetailsResponse from "../../../../model/response/details/AccountDetailsResponse";
import AccountService from "../../../../service/AccountService";
type Props = {
    account?: AccountDetailsResponse
}
const ManagerPermission = ({ account }: Props) => {
    const [permissionIds, setPermissionIds] = useState<number[]>([])
    const [permissionGroup, setPermissionGroup] = useState<PermissionGroupResponse[]>([])
    useEffect(() => {
        // if (account) setPermissionIds(account.role.permissions.map(permission => permission.id))
        fetchAllPermissionGroup();
    }, [])
    const fetchAllPermissionGroup = () => {
        PermissionGroupService.readAll().then(d => {
            if (d.success) {
                setPermissionGroup(d.data)
            }
        });
    }
    const handleSaveClick = () => {
        if (account)
            AccountService.updatePermission(account.id, permissionIds).then(d => console.log(d.data))
    }

    return (
        <Checkbox.Group className="d-block mb-5"
        //  value={permissionIds} onChange={(e) => setPermissionIds(e)}
         >
            <table className="w-100">
                {permissionGroup?.map(permissionGroup => { return (<tr key={`row-${permissionGroup.name}`} ><th>{permissionGroup.name}</th>{permissionGroup.permissions.map(permission => <td key={`cell-${permission.id}`}><Checkbox value={permission.id}>{permission.name}</Checkbox></td>)}</tr>) })}
            </table >
        </Checkbox.Group>
    )
}

export default ManagerPermission