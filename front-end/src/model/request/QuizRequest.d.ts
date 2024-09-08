interface QuizRequest extends QuizBase {
    id?: number;
    answers:AnswerRequest[];
}