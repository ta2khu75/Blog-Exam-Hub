import React from 'react'
import CommentItemElement from './CommentItemElement'
import { Pagination } from 'antd'
type Props = {
  commentPage?: PageResponse<CommentResponse>
  setPage: (page: number) => void
  page: number
}
const CommentPageElement = ({ commentPage, setPage, page }: Props) => {
  return (
    <>
      {commentPage?.content?.map(comment => {
        return <CommentItemElement key={comment.id} comment={comment} />
      })}
      <Pagination align="center" onChange={setPage} pageSize={5} defaultCurrent={page} total={commentPage?.total_elements} />
    </>
  )
}

export default CommentPageElement;