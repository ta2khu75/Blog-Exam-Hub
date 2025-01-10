interface AuthResponse{
    account?:AccountResponse;
    access_token?:string;
    refresh_token?:string;
    authenticated:boolean;
}