import instance from "../util/apiInstance";

export class NotificationService {
    static readPageByAccountId(accountId: string, page = 1, size = 5): Promise<ApiResponse<PageResponse<NotificationResponse>>> {
        return instance.get(`/notification/account/${accountId}`, { params: { page, size } });
    }
}