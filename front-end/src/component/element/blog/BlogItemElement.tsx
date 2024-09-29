import { Button } from 'antd'
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
import { AccessModifier } from '../../../model/AccessModifier';
import { Link } from 'react-router-dom';
type Props = {
    blog: BlogResponse,
    handleEdit?: () => void,
    handleDelete?: () => void,
}
const BlogItemElement = ({ blog, handleDelete, handleEdit }: Props) => {
    const { id, title, blog_tags, access_modifier, last_modified_at, created_at, view_count, author } = blog;
    return (
        <div className="blog-item-element mb-3">
            <div className='row align-items-center small'>
                <div className="col-11">
                    <h6 className="text-info d-flex align-items-center">
                        {(handleEdit || handleDelete) && (
                            <>
                                {access_modifier === AccessModifier.PRIVATE ? <LockOutlined className="me-1" /> : <UnlockOutlined className="me-1" />}
                            </>
                        )}
                        <Link to={`/blog-details/${id}`}>
                            {title}

                        </Link>
                    </h6>
                    <span className="text-muted">by <Link to={`/author/${author.id}`}>{author.username}</Link></span>
                    <div className="blog-tags mt-1">
                        {blog_tags.map((tag, index) => (
                            <Link to={`/blog/search?blogTagNames=${tag}`} key={`blog-tag-${tag}-${index}`}>
                                <span className="badge bg-secondary me-1">
                                    {tag}
                                </span>
                            </Link>
                        ))}
                    </div>
                    <div className="blog-meta mt-1">
                        <span className="me-2">Last edit: {last_modified_at ?? created_at}</span>
                        <span>Views: {view_count}</span>
                    </div>
                </div>
                {(handleEdit || handleDelete) && (
                    <div className="col-1 d-flex flex-column">
                        <Button size="small" type="primary" className="mb-1" onClick={handleEdit}>Edit</Button>
                        <Button size="small" danger onClick={handleDelete}>Delete</Button>
                    </div>
                )}
            </div>
            <hr className="my-2" />
        </div>
    );
}

export default BlogItemElement;