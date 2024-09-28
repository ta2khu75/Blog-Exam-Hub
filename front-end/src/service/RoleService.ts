import RoleRequest from "../model/request/RoleRequest";
import instance from "../util/apiInstance";
const basePath = "role";
export default class RoleService{
    static readAll():Promise<ApiResponse<RoleResponse[]>>{
        return instance.get(basePath);
    }
    static create(role:RoleRequest):Promise<ApiResponse<RoleResponse>>{
        return instance.post(basePath, role);
    }
    static update(id:number, role:RoleRequest):Promise<ApiResponse<RoleResponse>>{
        return instance.put(`${basePath}/${id}`, role);
    }
    static delete(id:number):Promise<ApiResponse<RoleResponse>>{
        return instance.delete(`${basePath}/${id}`);
    }
    static readById(id:number):Promise<ApiResponse<RoleResponse>>{
        return instance.get(`${basePath}/${id}`);
    }
}