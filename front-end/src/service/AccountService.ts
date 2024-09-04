import { AccountRequest } from "../model/request/AccountRequest";
import AccountPasswordRequest from "../model/request/update/AccountPasswordRequest";
import { AccountStatusRequest } from "../model/request/update/AccountStatusRequest";
import AccountResponse from "../model/response/AccountResponse";
import ApiResponse from "../model/response/ApiResponse";
import AccountDetailsResponse from "../model/response/details/AccountDetailsResponse";
import PageResponse from "../model/response/PageResponse";
import instance from "../util/apiInstance";

const basePath = "account";
export default class AccountService {
  static readPage(search = "", page = 0, size = 10): Promise<ApiResponse<PageResponse<AccountDetailsResponse>>> {
    return instance.get(`${basePath}`, { params: { search, page, size } });
  }
  static create(
    account: AccountRequest
  ): Promise<ApiResponse<AccountResponse>> {
    return instance.post(basePath, account);
  }
  static updateStatus(id: string, account: AccountStatusRequest): Promise<ApiResponse<AccountDetailsResponse>> {
    return instance.put(`${basePath}/${id}`, account);
  }

  static updatePermission(id: string, permissionIds:number[]): Promise<ApiResponse<AccountDetailsResponse>> {
    return instance.put(`${basePath}/${id}/permission`, { permission_ids: permissionIds });
  }
  static readById(id: number): Promise<ApiResponse<AccountResponse>> {
    return instance.get(`${basePath}/${id}`);
  }
  static changePassword(
    accountPassword: AccountPasswordRequest
  ): Promise<ApiResponse<AccountPasswordRequest>> {
    return instance.put(`${basePath}/change-password`, accountPassword);
  }
}
