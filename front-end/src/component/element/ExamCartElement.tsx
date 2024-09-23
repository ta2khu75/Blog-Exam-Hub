import { Button, Card } from 'antd'
import { AccessModifier } from '../../model/AccessModifier';
const { Meta } = Card;
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
import { ExamLevel } from '../../model/ExamLevel';
import { Link } from 'react-router-dom';
type Props = {
  exam: ExamResponse,
  handleEdit?: () => void;
  handleDelete?: () => void;
}
const ExamCartElement = ({ exam, handleDelete, handleEdit }: Props) => {
  const cssExamLevel = (examLevel: string): string => {
    switch (examLevel) {
      case ExamLevel.EASY:
        return "bg-success";
      case ExamLevel.NORMAL:
        return "bg-warning";
      case ExamLevel.HARD:
        return "bg-danger";
      default:
        return "";
    }
  }
  const cssExamDuration = (examDuration: number): string => {
    if (examDuration <= 15) {
      return "text-success";
    } else if (examDuration <= 45) {
      return "text-warning";
    } else {
      return "text-danger";
    }
  }
  return (
    <Card
      extra={(handleDelete || handleEdit) && <><Button onClick={handleEdit}>Edit</Button><Button onClick={handleDelete}>Delete</Button></>}
      hoverable
      style={{ width: 300 }}
      title={(handleDelete || handleEdit) && <>{exam.access_modifier === AccessModifier.PRIVATE ? <LockOutlined /> : <UnlockOutlined />}</>}
      cover={<Link to={"/exam-about/" + exam.id}><img style={{ height: "200px", width: "100%" }} alt="example" src={exam.image_path} /></Link>}
    >
      <Meta title={<div className=''><span>{exam.title}</span></div>}
        // avatar={<div>Author: {exam?.author?.username}</div>}
        description={<div>
          <div>
            Level: <span className={`text-light p-1 rounded  ${cssExamLevel(exam.exam_level)}`}>{exam.exam_level}</span>
          </div>
          <div>Category: {exam.exam_category.name}
          </div>
          <div>Duration: <span className={`${cssExamDuration(exam.duration)}`}>{exam.duration} Minutes</span></div>
        </div>} />
    </Card >
  )
}

export default ExamCartElement;