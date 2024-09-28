import { ExamSearchRequest } from "../model/request/search/ExamSearchRequest";
import instance from "../util/apiInstance";
const basePath = "exam";
export default class ExamService {
    static search(examSearchRequest: ExamSearchRequest): Promise<ApiResponse<PageResponse<ExamResponse>>> {
        return instance.get(basePath, { params: { ...examSearchRequest } })
    }
    static mySearch(examSearch: ExamSearchRequest): Promise<ApiResponse<PageResponse<ExamResponse>>> {
        return instance.get(`${basePath}/my-exam`, { params: { ...examSearch } })
    }
    static create(data: ExamRequest, image: File): Promise<ApiResponse<ExamResponse>> {
        const form = new FormData();
        form.append("image", image)
        form.append("exam_request", JSON.stringify(data));
        return instance.post(basePath, form);
    }
    static update(id: string, data: ExamRequest, image?: File): Promise<ApiResponse<ExamResponse>> {
        const form = new FormData();
        if (image) {
            form.append("image", image)
        }
        form.append("exam_request", JSON.stringify(data));
        return instance.put(`${basePath}/${id}`, form);
    }
    static delete(id: string): Promise<ApiResponse<void>> {
        return instance.delete(`${basePath}/${id}`);
    }
    static readDetailsById(id: string): Promise<ApiResponse<ExamDetailsResponse>> {
        return instance.get(`${basePath}/${id}/details`);
    }
    static readById(id: string): Promise<ApiResponse<ExamResponse>> {
        return instance.get(`${basePath}/${id}`);
    }
}