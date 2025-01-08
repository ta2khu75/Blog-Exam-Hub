import { BasePath } from "../env/BasePath";
import instance from "../util/apiInstance";
const basePath = BasePath.PERMISSION_GROUP;
export default class PermissionGroupService{
    static readAll():Promise<ApiResponse<PermissionGroupResponse[]>>{
        return instance.get(basePath);
    }
}