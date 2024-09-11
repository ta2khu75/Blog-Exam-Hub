import { Card } from 'antd'
import React from 'react'
const { Meta } = Card;
type Props = {
  exam: ExamResponse
  isEdit?: boolean
}
const ExamCartElementNew = ({ exam, isEdit }: Props) => {
  return (
    <Card
      extra={<a href="#">More</a>}
      hoverable
      style={{ width: 300 }}
      cover={<img style={{ height: "200px" }} alt="example" src={exam.image_path} />}
    >
      <Meta title={exam.title} description={exam.description} />
    </Card>
  )
}

export default ExamCartElementNew