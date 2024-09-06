import RoleResponse from "../RoleResponse";

export default interface RoleDetailsResponse extends RoleResponse{
    permissionIds:number[];
}