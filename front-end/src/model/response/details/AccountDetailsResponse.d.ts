import AccountResponse from "../AccountResponse";
import RoleResponse from "../RoleResponse";

export default interface AccountDetailsResponse extends AccountResponse{
    enabled: boolean;
    nonLocked: boolean;
    role: RoleResponse;
}