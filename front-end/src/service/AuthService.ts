import { AuthRequest } from "../component/page/LoginPage";
import ApiResponse from "../response/ApiResponse";
import AuthResponse from "../response/AuthResponse";
import instance from "../util/apiInstance";
const basePath="/auth";
export default class AuthService{
    static login(authRequest: AuthRequest):Promise<ApiResponse<AuthResponse>>{
        return instance.post(`${basePath}/login`, authRequest);
    }
}