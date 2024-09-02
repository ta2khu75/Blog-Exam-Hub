import ApiResponse from "../model/response/ApiResponse";
import PermissionGroupResponse from "../model/response/PermissionGroupResponse";
import instance from "../util/apiInstance";
const basePath = "permission-group";
export default class PermissionGroupService{
    static readAll():Promise<ApiResponse<PermissionGroupResponse[]>>{
        return instance.get(basePath);
    }
}