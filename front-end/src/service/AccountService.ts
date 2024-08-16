import { AccountRequest } from "../component/page/admin/crud/AccountCrud";
import AccountPasswordRequest from "../request/AccountPasswordRequest";
import AccountResponse from "../response/AccountResponse";
import ApiResponse from "../response/ApiResponse";
import PageResponse from "../response/PageResponse";
import instance from "../util/apiInstance";

export default class AccountService {
  static basePath = "account";
  static readPage(): Promise<ApiResponse<PageResponse<AccountResponse>>> {
    return instance.get(this.basePath);
  }
  static create(
    account: AccountRequest
  ): Promise<ApiResponse<AccountResponse>> {
    return instance.post(this.basePath, account);
  }
  static readById(id: number): Promise<ApiResponse<AccountResponse>> {
    return instance.get(`${this.basePath}/${id}`);
  }
  static changePassword(
    accountPassword: AccountPasswordRequest
  ): Promise<ApiResponse<AccountPasswordRequest>> {
    return instance.put(`${this.basePath}/change-password`, accountPassword);
  }
}
