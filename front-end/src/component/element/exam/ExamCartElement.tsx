import { Button, Card } from 'antd'
import { AccessModifier } from '../../../types/AccessModifier';
const { Meta } = Card;
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
import { ExamLevel } from '../../../types/ExamLevel';
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
      cover={<Link to={"/exam-about/" + exam.info.id}><img style={{ height: "200px", width: "100%" }} alt="example" src={exam.image_path} /></Link>}
    >
      <Meta title={<div className=''><h6>{exam.title}</h6><span className='text-muted'>by <Link to={`/author/${exam.author.info.id}`}>{exam.author.username}</Link></span></div>}
        description={<div>
          <div>
            Level: <span className={`text-light p-1 rounded  ${cssExamLevel(exam.exam_level)}`}>{exam.exam_level}</span>
          </div>
          <div>Category: <Link to={`/exam/search?examCategoryIds=${exam.exam_category.id}`}>{exam.exam_category.name}</Link>
          </div>
          <div>Duration: <span className={`${cssExamDuration(exam.duration)}`}>{exam.duration} Minutes</span></div>
        </div>} />
    </Card >
  )
}

export default ExamCartElement;