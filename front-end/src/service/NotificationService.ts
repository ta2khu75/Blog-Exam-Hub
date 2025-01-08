import { BasePath } from "../env/BasePath";
import instance from "../util/apiInstance";
const basePath=BasePath.NOTIFICATION
export class NotificationService {
    static readPageByAccountId(accountId: string, page = 1, size = 5): Promise<ApiResponse<PageResponse<NotificationResponse>>> {
        return instance.get(`${basePath}/account/${accountId}`, { params: { page, size } });
    }
}