import { Pagination } from "antd"
import BlogItemElement from "./BlogItemElement"

type Props = {
  blogPage?: PageResponse<BlogResponse>
  setPage: (page: number) => void;
  page: number;
}
const BlogPageElement= ({ blogPage, setPage, page }: Props) => {
  return (
    <>
      {blogPage?.content?.map(blog => {
        return <BlogItemElement key={blog.id} blog={blog} />
      })}
      <Pagination align="center" onChange={setPage} pageSize={10} defaultCurrent={page} total={blogPage?.total_elements} />
    </>
  )
}

export default BlogPageElement;