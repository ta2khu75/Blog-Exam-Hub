import { AccessModifier } from "../AccessModifier";
import { ExamLevel } from "../ExamLevel";

export default interface ExamResponse{
    id: number;
    title:string;
    description:string;
    image_path:string,
    exam_level: ExamLevel,
    access_modifier: AccessModifier,
    exam_category:ExamCategoryResponse
}