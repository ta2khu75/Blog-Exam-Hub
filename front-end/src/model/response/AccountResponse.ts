import AccountInfoRequest from "../request/update/AccountInfoRequest";

export default interface AccountResponse extends AccountInfoRequest{
    id:string;
    email:string;
}
