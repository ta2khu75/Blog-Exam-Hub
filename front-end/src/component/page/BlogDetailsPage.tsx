import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { BlogService } from "../../service/BlogService";
import StringUtil from "../../util/StringUtil";

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
            <h1>{blog?.title}</h1>
            <div dangerouslySetInnerHTML={{ __html: StringUtil.replaceMarkdownWithImgTag(blog?.content ?? "") }}></div>
        </div>
    )
}

export default BlogDetailsPage