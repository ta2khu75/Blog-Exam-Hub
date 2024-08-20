import ExamHistoryResponse from "../ExamHistoryResponse";
import UserAnswerResponse from "../UserAnswerResponse";

export default interface ExamHistoryDetailsResponse extends ExamHistoryResponse{
    user_answers:UserAnswerResponse[]
}