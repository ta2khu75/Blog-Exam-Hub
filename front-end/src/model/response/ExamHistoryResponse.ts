import AccountResponse from "./AccountResponse";
import ExamDetailsResponse from "./details/ExamDetailsResponse";

export default interface ExamHistoryResponse{
    id: number;
    point: number;
    correct_count:number;
    exam: ExamDetailsResponse;
    account: AccountResponse;
    end_time: Date;
    created_date: Date;
    last_modified_date?: Date;
}
