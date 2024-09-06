import ExamRequest from "../model/request/ExamRequest";
import ApiResponse from "../model/response/ApiResponse";
import ExamResponse from "../model/response/ExamResponse";
import PageResponse from "../model/response/PageResponse";
import instance from "../util/apiInstance";
const basePath = "exam";
export default class ExamService {
    static readPage(): Promise<ApiResponse<PageResponse<ExamResponse>>> {
        return instance.get(basePath);
    }
    static create(data: ExamRequest, image: File): Promise<ApiResponse<ExamResponse>> {
        const form = new FormData();
        form.append("image", image)
        form.append("exam_request", JSON.stringify(data));
        return instance.post(basePath, form);
    }
    static update(id: number, data: ExamRequest, image?: File): Promise<ApiResponse<ExamResponse>> {
        const form = new FormData();
        if (image) {
            form.append("image", image)
        }
        form.append("exam_request", JSON.stringify(data));
        return instance.put(`${basePath}/${id}`, form);
    }
    static delete(id: number): Promise<ApiResponse<void>> {
        return instance.delete(`${basePath}/${id}`);
    }
    static readById(id: number): Promise<ApiResponse<ExamResponse>> {
        return instance.get(`${basePath}/${id}`);
    }
}