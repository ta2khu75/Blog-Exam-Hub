interface ExamResponse extends ExamBase{
    id: string;
    image_path:string,
    author:AccountResponse
    exam_category:ExamCategoryResponse
}