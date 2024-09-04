import { AccessModifier } from "../AccessModifier";
import { ExamLevel } from "../ExamLevel";

export default interface ExamRequest  {
  title: string;
  duration: number;
  description: string;
  exam_level: ExamLevel;
  access_modifier: AccessModifier;
  exam_category_id: number;
}