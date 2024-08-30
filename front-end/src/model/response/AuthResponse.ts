import AccountResponse from "./AccountResponse";

export default interface AuthResponse{
    account?:AccountResponse;
    access_token?:string;
    refresh_token?:string;
    authenticated:boolean;
}