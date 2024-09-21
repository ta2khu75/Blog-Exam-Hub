import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { BlogService } from "../../service/BlogService";

const BlogDetailsPage = () => {
    const { blogId } = useParams()
    const [blog, setBlog] = useState<BlogDetailsResponse>();
    useEffect(() => {
        if (blogId) fetchBlogDetails(blogId);
    }, [blogId])
    const fetchBlogDetails = (blogId: string) => {
        BlogService.readDetails(blogId).then(response => {
            if (response.success) {
                setBlog(response.data)
            }
        })
    }
    return (
        <div className="container">
            {blog?.content}
        </div>
    )
}

export default BlogDetailsPage