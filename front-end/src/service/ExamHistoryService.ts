import AnswerUserRequest from "../request/AnswerUserRequest";
import ApiResponse from "../response/ApiResponse";
import ExamHistoryResponse from "../response/ExamHistoryResponse";
import instance from "../util/apiInstance";
const basePath="exam-history"
export default class ExamHistoryService{
    static readByExamId(id: number):Promise<ApiResponse<ExamHistoryResponse>>{
        return instance.get(`${basePath}/exam/${id}`);
    }
    static scoreByExamHistoryId(id: number, examId: number, data:AnswerUserRequest[]):Promise<ApiResponse<ExamHistoryResponse>>{
        return instance.post(`${basePath}/${id}`,data,{params:{exam_id:examId}});
    }
}