import ApiResponse from "../model/response/ApiResponse";
import instance from "../util/apiInstance";
const basePath = "exam-category";
export default class ExamCategoryService {
    static readAll(): Promise<ApiResponse<ExamCategoryResponse[]>> {
        return instance.get(basePath);
    }
    static create(category: ExamCategoryRequest): Promise<ApiResponse<ExamCategoryResponse>> {
        return instance.post(basePath, category);
    }
    static delete(id: number): Promise<ApiResponse<void>> {
        return instance.delete(`${basePath}/${id}`);
    }
    static update(id: number, category: ExamCategoryRequest): Promise<ApiResponse<ExamCategoryResponse>> {
        return instance.put(`${basePath}/${id}`, category);
    }
}