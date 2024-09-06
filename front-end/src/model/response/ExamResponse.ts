import { AccessModifier } from "../AccessModifier";
import { ExamLevel } from "../ExamLevel";
import { ExamType } from "../ExamType";

export default interface ExamResponse{
    id: number;
    title:string;
    description:string;
    image_path:string,
    exam_type: ExamType,
    exam_level: ExamLevel,
    access_modifier: AccessModifier,
}