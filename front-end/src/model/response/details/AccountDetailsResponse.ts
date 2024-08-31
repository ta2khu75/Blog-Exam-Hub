import AccountResponse from "../AccountResponse";

export default interface AccountDetailsResponse extends AccountResponse{
    enabled: boolean;
    nonLocked: boolean;
    role: string;
}