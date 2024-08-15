import ExamResponse from "../ExamResponse";
import QuizDetailsResponse from "./QuizDetailsResponse";

export default interface ExamDetailsResponse extends ExamResponse{
    quizzes: QuizDetailsResponse[];
}