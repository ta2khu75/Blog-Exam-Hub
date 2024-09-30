import { ExamLevel } from "../../ExamLevel";

interface ExamSearchRequest extends SearchRequestBase {
    examLevels?: ExamLevel[],
    examCategoryIds?: number[],
    minDuration?: number,
    maxDuration?: number
}