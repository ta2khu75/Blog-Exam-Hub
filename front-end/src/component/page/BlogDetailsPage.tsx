import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { BlogService } from "../../service/BlogService";
import StringUtil from "../../util/StringUtil";
import { useAppDispatch } from "../../redux/hooks";
import { setBlogHistory } from "../../redux/slice/blogHistorySlice";

const BlogDetailsPage = () => {
    const { blogId } = useParams()
    const dispatch = useAppDispatch();
    const [blog, setBlog] = useState<BlogDetailsResponse>();
    useEffect(() => {
        if (blogId) fetchBlogDetails(blogId);
    }, [blogId])
    const fetchBlogDetails = (blogId: string) => {
        BlogService.readDetails(blogId).then(response => {
            if (response.success) {
                setBlog(response.data)
                dispatch(setBlogHistory({ blogId: response.data.id, blog: response.data }));
            }
        })
    }
    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-lg-8">
                    {/* Blog Header */}
                    <div className="text-center">
                        <h1>{blog?.title}</h1>
                        <p className="text-muted">
                            By <Link to="">{blog?.author.email}</Link>
                        </p>
                    </div>

                    {/* Blog Tags */}
                    {blog?.blog_tags && blog?.blog_tags?.length > 0 && (
                        <div className="my-3 text-center">
                            <span className="badge bg-secondary me-1">Tags:</span>
                            {blog.blog_tags.map((tag, index) => (
                                <Link key={index} to={`/blog-tag/${tag}`}>
                                    <span className="badge bg-primary me-1">{tag}</span>
                                </Link>

                            ))}
                        </div>
                    )}

                    {/* Blog Image */}
                    {blog?.image_path && (
                        <div className="my-4 text-center">
                            <img src={blog?.image_path} alt={blog?.title} className="img-fluid rounded" style={{ maxHeight: '400px', objectFit: 'cover', width: '100%' }} />
                        </div>
                    )}

                    {/* Blog Content */}
                    <div className="my-4" dangerouslySetInnerHTML={{ __html: StringUtil.replaceMarkdownWithImgTag(blog?.content ?? "") }}></div>

                    {/* Share Buttons */}
                    <div className="mt-4 text-center">
                        <h5>Share this article:</h5>
                        <div className="d-flex justify-content-center gap-3">
                            <button className="btn btn-primary">Facebook</button>
                            <button className="btn btn-info">Twitter</button>
                            <button className="btn btn-danger">LinkedIn</button>
                        </div>
                    </div>

                    {/* Additional Tags */}
                    {blog?.blog_tags && blog.blog_tags.length > 0 && (
                        <div className="my-3 text-center">
                            {blog.blog_tags.map(tag => (
                                <Link to={""} key={`blog-tag-${tag}`}>
                                    <span className="badge bg-secondary me-1">
                                        {tag}
                                    </span>
                                </Link>
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div >
    )
}

export default BlogDetailsPage