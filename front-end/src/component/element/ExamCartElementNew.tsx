import { Button, Card } from 'antd'
import React from 'react'
const { Meta } = Card;
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
      title={exam.title}
      cover={<img style={{ height: "200px" }} alt="example" src={exam.image_path} />}
    >
      <Meta title={exam.title} description={exam.description} />
    </Card>
  )
}

export default ExamCartElementNew