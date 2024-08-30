import AnswerResponse from "../AnswerResponse";
import QuizResponse from "../QuizResponse";

export default interface QuizDetailsResponse extends QuizResponse{
    answers:AnswerResponse[]
}