import { ExamLevel } from "../../ExamLevel";

interface ExamSearchRequest extends SearchRequestBase {
    exam_level?: ExamLevel,
    exam_category_ids?: number[],
    min_duration?: number,
    max_duration?: number
}