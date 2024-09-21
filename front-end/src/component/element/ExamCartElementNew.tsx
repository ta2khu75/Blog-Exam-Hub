import { Button, Card } from 'antd'
import { AccessModifier } from '../../model/AccessModifier';
const { Meta } = Card;
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
type Props = {
  exam: ExamResponse,
  handleEdit?: () => void;
  handleDelete?: () => void;
}
const ExamCartElementNew = ({ exam, handleDelete, handleEdit }: Props) => {
  return (
    <Card
      extra={(handleDelete || handleEdit) && <><Button onClick={handleEdit}>Edit</Button><Button onClick={handleDelete}>Delete</Button></>}
      hoverable
      style={{ width: 300 }}
      title={<>{exam.access_modifier === AccessModifier.PRIVATE ? <LockOutlined /> : <UnlockOutlined />}</>}
      cover={<img style={{ height: "200px" }} alt="example" src={exam.image_path} />}
    >
      <Meta title={exam.title} description={exam.description} />
    </Card>
  )
}

export default ExamCartElementNew