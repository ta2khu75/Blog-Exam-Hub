import AnswerUserRequest from "../model/request/AnswerUserRequest";
import ApiResponse from "../model/response/ApiResponse";
import ExamHistoryDetailsResponse from "../model/response/details/ExamHistoryDetailResponse";
import ExamHistoryResponse from "../model/response/ExamHistoryResponse";
import PageResponse from "../model/response/PageResponse";
import instance from "../util/apiInstance";
const basePath = "exam-history";
export default class ExamHistoryService {
  static readByExamId(id: number): Promise<ApiResponse<ExamHistoryResponse>> {
    return instance.get(`${basePath}/exam/${id}`);
  }
  static readById(
    id: number,
    examId: number,
    data: AnswerUserRequest[]
  ): Promise<ApiResponse<ExamHistoryResponse>> {
    return instance.post(`${basePath}/${id}`, data, {
      params: { exam_id: examId },
    });
  }
  static readDetailsById(
    id: number
  ): Promise<ApiResponse<ExamHistoryDetailsResponse>> {
    return instance.get(`${basePath}/${id}`);
  }
  static readPage(): Promise<ApiResponse<PageResponse<ExamHistoryResponse>>> {
    return instance.get(`${basePath}`);
  }

}
