
import { Button } from 'antd';
import { Link } from 'react-router-dom';
type Props = {
    blog: BlogResponse,
    handleEdit?: () => void,
    handleDelete?: () => void,
}
const BlogItemHistoryElement = ({ blog, handleDelete, handleEdit }: Props) => {
    const {info, title, blog_tags,  view_count, author } = blog;
    return (
        <div className="blog-item-element mb-3">
            <div className='row align-items-center small'>
                <div className="col-11">
                    <Link to={`/blog-details/${info.id}`} style={{ maxWidth: "600px", margin: "0 auto" }}>
                        <h6 className="text-info d-flex mb-0 align-items-center">
                            {title}
                        </h6>
                        <span className="text-muted">
                            by <Link to={`/author/${author.info.id}`}>{author.username}</Link>
                        </span>
                        <div className="blog-tags mt-1">
                            {blog_tags.map(tag => (
                                <Link to={`/tag/${tag}`} key={`blog-tag-${tag}`}>
                                    <span className="badge bg-secondary me-1">{tag}</span>
                                </Link>
                            ))}
                        </div>
                        <div className="blog-meta mt-1">
                            <span className="me-2">Last edit: {info.id?? info.createdAt}</span>
                            <span>Views: {view_count}</span>
                        </div>
                    </Link>
                </div>
                {(handleEdit || handleDelete) && (
                    <div className="col-1">
                        <Button size="small" danger onClick={handleDelete}>Delete</Button>
                    </div>
                )}
            </div>
            <hr className="my-2" />
        </div >

    );
}

export default BlogItemHistoryElement