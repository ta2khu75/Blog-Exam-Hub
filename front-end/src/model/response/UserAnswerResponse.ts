export default interface UserAnswerResponse{
    id:number;
    quiz: QuizResponse;
    answers: AnswerResponse[]
}