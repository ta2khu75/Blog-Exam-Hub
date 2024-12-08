interface BlogResponse extends BlogBase {
    view_count: number;
    comment_count: number;
    image_path: string;
    author: AccountResponse;
    info:InfoResponse;
    exams: ExamResponse[];
}