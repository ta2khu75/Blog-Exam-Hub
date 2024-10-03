interface AccountDetailsResponse extends AccountResponse{
    enabled: boolean;
    non_locked: boolean;
    role: RoleResponse;
}