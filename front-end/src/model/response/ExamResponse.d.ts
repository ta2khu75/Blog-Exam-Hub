interface ExamResponse extends ExamBase{
    id: number;
    image_path:string,
    author:AccountResponse
    exam_category:ExamCategoryResponse
}