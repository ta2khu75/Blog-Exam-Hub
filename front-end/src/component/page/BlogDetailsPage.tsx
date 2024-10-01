import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { BlogService } from "../../service/BlogService";
import StringUtil from "../../util/StringUtil";
import { useAppDispatch } from "../../redux/hooks";
import { setBlogHistory } from "../../redux/slice/blogHistorySlice";
import CommentForm from "../element/form/CommentForm";
import { CommentService } from "../../service/CommentService";
import CommentPageElement from "../element/comment/CommentPageElement";

const BlogDetailsPage = () => {
    const { blogId } = useParams()
    const dispatch = useAppDispatch();
    const [blog, setBlog] = useState<BlogDetailsResponse>();
    const [commentPage, setCommentPage] = useState<PageResponse<CommentResponse>>();
    const [page, setPage] = useState(1);
    useEffect(() => {
        if (blogId) {
            fetchBlogDetails(blogId);
            fetchCommentPage(blogId);
        }
    }, [blogId])
    useEffect(() => {
        if (blogId) fetchCommentPage(blogId);
    }, [page])
    const fetchBlogDetails = (blogId: string) => {
        BlogService.readDetails(blogId).then(response => {
            if (response.success) {
                setBlog(response.data)
                dispatch(setBlogHistory({ blogId: response.data.id, blog: response.data }));
            }
        })
    }
    const fetchCommentPage = (blogId: string) => {
        CommentService.readPageByBlog(blogId, page, 5).then(response => {
            if (response.success) {
                setCommentPage(response.data)
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
                            By <Link to={`/author/${blog?.author.id}`}>{blog?.author.username}</Link>
                        </p>
                    </div>

                    {/* Blog Tags */}
                    {blog?.blog_tags && blog?.blog_tags?.length > 0 && (
                        <div className="my-3 text-center">
                            <span className="badge bg-secondary me-1">Tags:</span>
                            {blog.blog_tags.map((tag, index) => (
                                <Link key={index} to={`/blog/search?blogTagNames=${tag}`}>
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
                                <Link to={`/blog/search?blogTagNames=${tag}`} key={`blog-tag-${tag}`}>
                                    <span className="badge bg-secondary me-1">
                                        {tag}
                                    </span>
                                </Link>
                            ))}
                        </div>
                    )}
                </div>
                {blogId && <CommentForm setCommentPage={setCommentPage} blogId={blogId} />}
                <div>
                    {/* Comment List */}
                    <h5 className="my-4">Comments ({commentPage?.total_elements ?? 0})</h5>
                    <CommentPageElement setPage={setPage} page={page} commentPage={commentPage} />
                </div>
            </div>
        </div >
    )
}

export default BlogDetailsPage