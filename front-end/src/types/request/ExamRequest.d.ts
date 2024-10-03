
interface ExamRequest extends ExamBase {
  exam_category_id: number;
  quizzes: QuizRequest[];
}