import { useEffect, useState } from "react"
import RoleResponse from "../../../../model/response/RoleResponse"
import RoleService from "../../../../service/RoleService"

const ManagerRole = () => {
    const [roles, setRoles] = useState<RoleResponse[]>([])
    useEffect(() => {
        fetchAllRole();
    }, [])
    const fetchAllRole = () => {
        RoleService.readAll().then(d => {
            if (d.success) {
                setRoles(d.data);
            }
        })
    }
    return (
        <div>ManagerRole</div>
    )
}

export default ManagerRole