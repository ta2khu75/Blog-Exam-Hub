import AccountAuthResponse from "./AccountAuthResponse";

export default interface AuthResponse{
    account?:AccountAuthResponse;
    access_token?:string;
    refresh_token?:string;
    authenticated:boolean;
}