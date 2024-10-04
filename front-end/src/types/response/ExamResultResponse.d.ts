interface ExamResultResponse {
    point: number;
    correct_count: number;
    exam: ExamDetailsResponse;
    account: AccountResponse;
    end_time: Date;
    info: InfoResponse;
}
