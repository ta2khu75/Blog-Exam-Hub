import instance from "../util/apiInstance";
const basePath = "quiz";
export default class QuizService {
  static readByExamId(id: number): Promise<ApiResponse<QuizResponse[]>> {
    return instance.get(`${basePath}/exam/${id}`);
  }
  static readById(id: number): Promise<ApiResponse<QuizResponse>> {
    return instance.get(`${basePath}/${id}`);
  }
  static create(
    quiz: QuizRequest,
    file?: File
  ): Promise<ApiResponse<QuizResponse>> {
    const form = new FormData();
    form.append("quiz_request", JSON.stringify(quiz));
    if (file) form.append("file", file);
    return instance.post(basePath, form);
  }
  static update(
    id: number,
    quiz: QuizRequest,
    file?: File
  ): Promise<ApiResponse<QuizResponse>> {
    const form = new FormData();
    form.append("quiz_request", JSON.stringify(quiz));
    if (file) form.append("file", file);
    return instance.put(`${basePath}/${id}`, form);
  }
  static delete(id: number): Promise<ApiResponse<void>> {
    return instance.delete(`${basePath}/${id}`);
  }
}
