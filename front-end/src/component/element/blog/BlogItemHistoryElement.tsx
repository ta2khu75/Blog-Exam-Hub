
import { Link } from 'react-router-dom';
type Props = {
    blog: BlogResponse
}
const BlogItemHistoryElement = ({ blog }: Props) => {
    const { id, title, blog_tags, last_modified_at, created_at, view_count, author } = blog;
    return (
        <div className="blog-item-element mb-3">
            <Link to={`/blog-details/${id}`} className="row align-items-center small" style={{ maxWidth: "600px", margin: "0 auto" }}>
                <div className="col-11">
                    <h6 className="text-info d-flex mb-0 align-items-center">
                        {title}
                    </h6>
                    <span className="text-muted">
                        by <Link to={`/author/${author.id}`}>{author.username}</Link>
                    </span>
                    <div className="blog-tags mt-1">
                        {blog_tags.map(tag => (
                            <Link to={`/tag/${tag}`} key={`blog-tag-${tag}`}>
                                <span className="badge bg-secondary me-1">{tag}</span>
                            </Link>
                        ))}
                    </div>
                    <div className="blog-meta mt-1">
                        <span className="me-2">Last edit: {last_modified_at ?? created_at}</span>
                        <span>Views: {view_count}</span>
                    </div>
                </div>
            </Link>
            <hr className="my-2" />
        </div>

    );
}

export default BlogItemHistoryElement