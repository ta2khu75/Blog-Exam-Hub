import PermissionResponse from "./PermissionResponse";

export default interface RoleResponse{
    id: number;
    name: string;
    permissions: PermissionResponse[];
}