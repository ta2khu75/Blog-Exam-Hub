import instance from "../util/apiInstance";
const basePath = "blog-tag"
export class BlogTagService {
    static readAll(): Promise<ApiResponse<BlogTagResponse[]>> {
        return instance.get(basePath);
    }
}