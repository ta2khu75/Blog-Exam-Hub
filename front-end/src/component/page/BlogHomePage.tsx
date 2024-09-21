import { useEffect, useState } from "react"
import { BlogService } from "../../service/BlogService"
import BlogItemElement from "../element/BlogItemElement"

const BlogHomePage = () => {
  const [pageBlog, setPageBlog] = useState<PageResponse<BlogResponse>>()
  useEffect(() => {
    fetchPageBlog()
  }, [])
  const fetchPageBlog = () => {
    BlogService.readPage(1, 10).then((data) => {
      if (data.success) {
        setPageBlog(data.data)
      }
    })
  }
  return (
    <div className="container">
      {pageBlog?.content?.map((blog) =>
        <BlogItemElement key={blog.id}  blog={blog} />
      )
      }
    </div>
  )
}

export default BlogHomePage