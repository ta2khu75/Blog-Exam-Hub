interface ExamResponse extends ExamBase{
    image_path:string,
    author:AccountResponse
    exam_category:ExamCategoryResponse
    info:InfoResponse,
    blog?: BlogResponse
}