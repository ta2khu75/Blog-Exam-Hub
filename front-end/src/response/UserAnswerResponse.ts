import AnswerResponse from "./AnswerResponse";
import QuizResponse from "./QuizResponse";

export default interface UserAnswerResponse{
    id:number;
    quiz: QuizResponse;
    answers: AnswerResponse[]
}