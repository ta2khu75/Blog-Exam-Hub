import PermissionResponse from "./PermissionResponse";

export default interface PermissionGroupResponse{
    name: string;
    permissions: PermissionResponse[];
}