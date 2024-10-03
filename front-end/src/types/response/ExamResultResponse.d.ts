interface ExamResultResponse {
    id: string;
    point: number;
    correct_count: number;
    exam: ExamDetailsResponse;
    account: AccountResponse;
    end_time: Date;
    created_date: Date;
    last_modified_date?: Date;
}
