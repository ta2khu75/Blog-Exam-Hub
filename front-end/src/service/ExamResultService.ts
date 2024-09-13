import AnswerUserRequest from "../model/request/AnswerUserRequest";
import instance from "../util/apiInstance";
const basePath = "exam-result";
export default class ExamHistoryService {
  static readByExamId(id: string): Promise<ApiResponse<ExamResultResponse>> {
    return instance.get(`${basePath}/exam/${id}`);
  }
  static readById(
    id: number,
    data: AnswerUserRequest[]
  ): Promise<ApiResponse<ExamResultResponse>> {
    return instance.post(`${basePath}/${id}`, data);
  }
  static readDetailsById(
    id: number
  ): Promise<ApiResponse<ExamResultDetailsResponse>> {
    return instance.get(`${basePath}/${id}`);
  }
  static readPage(): Promise<ApiResponse<PageResponse<ExamResultResponse>>> {
    return instance.get(`${basePath}`);
  }

}
