import { BasePath } from "../env/BasePath";
import instance from "../util/apiInstance";
const basePath = BasePath.BLOG_TAG
export class BlogTagService {
    static readAll(): Promise<ApiResponse<BlogTagResponse[]>> {
        return instance.get(basePath);
    }
}