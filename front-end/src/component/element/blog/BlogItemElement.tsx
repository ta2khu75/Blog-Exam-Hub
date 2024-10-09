import { Button } from 'antd'
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
import { AccessModifier } from '../../../types/AccessModifier';
import { Link } from 'react-router-dom';
import AvatarElement from '../AvatarElement';
type Props = {
    blog: BlogResponse,
    handleEdit?: () => void,
    handleDelete?: () => void,
}
const BlogItemElement = ({ blog, handleDelete, handleEdit }: Props) => {
    const { info, title, blog_tags, access_modifier, comment_count, view_count, author } = blog;
    return (
        <div className="blog-item-element mb-3">
            <div className='row align-items-center small'>
                <div className={`col-${handleEdit || handleDelete ? "11" : "12"}`}>
                    <div className='d-flex align-items-center'>
                        <Link to={`/profile/${author.info.id}/blog`}>
                            <AvatarElement username={blog.author.username} size={50} />
                        </Link>
                        <div className='d-flex align-items-center'>
                            <Link to={`/profile/${author.info.id}/blog`}>
                                <div className='d-flex align-items-center'>
                                    <b>{blog.author.username}</b>
                                </div>
                            </Link>
                            <span className='text-muted ms-2'>{info.created_at}</span>
                        </div>
                    </div>
                    <h6 className="text-info d-flex align-items-center">
                        {(handleEdit || handleDelete) && (
                            <>
                                {access_modifier === AccessModifier.PRIVATE ? <LockOutlined className="me-1" /> : <UnlockOutlined className="me-1" />}
                            </>
                        )}
                        <Link to={`/blog-details/${info.id}`}>
                            {title}
                        </Link>
                    </h6>
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
                        <span>Views: {view_count}</span>
                        <span className='ms-2'>Comments: {comment_count}</span>
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
        </div >
    );
}

export default BlogItemElement;