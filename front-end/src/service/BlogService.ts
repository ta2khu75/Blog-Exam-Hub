import instance from "../util/apiInstance";
const basePath = "blog";
export class BlogService {
    static search(blogSearchRequest: BlogSearchRequest): Promise<ApiResponse<PageResponse<BlogResponse>>> {
        return instance.get(`${basePath}`, { params: { ...blogSearchRequest } })
    }
    static mySearch(blogSearchRequest: BlogSearchRequest): Promise<ApiResponse<PageResponse<BlogResponse>>> {
        return instance.get(`${basePath}/my-blog`, { params: { ...blogSearchRequest } })
    }
    static create(blog: BlogRequest, file?: File): Promise<ApiResponse<BlogResponse>> {
        const form = new FormData();
        if (file) {
            form.append("image", file)
        }
        form.append("blog", JSON.stringify(blog));
        return instance.post(basePath, form);
    }
    static update(id: string, blog: BlogRequest, file?: File): Promise<ApiResponse<BlogResponse>> {
        const form = new FormData();
        if (file) {
            form.append("image", file)
        }
        form.append("blog", JSON.stringify(blog));
        return instance.put(`${basePath}/${id}`, form);
    }
    static delete(id: string): Promise<ApiResponse<void>> {
        return instance.delete(`${basePath}/${id}`);
    }
    static read(id: string): Promise<ApiResponse<BlogResponse>> {
        return instance.get(`${basePath}/${id}`);
    }
    static readDetails(id: string): Promise<ApiResponse<BlogDetailsResponse>> {
        return instance.get(`${basePath}/${id}/details`);
    }
}