import { Button } from 'antd'
import { LockOutlined, UnlockOutlined } from '@ant-design/icons';
import { AccessModifier } from '../../model/AccessModifier';
import { Link } from 'react-router-dom';
type Props = {
    blog: BlogResponse,
    handleEdit?: () => void,
    handleDelete?: () => void,
}
const BlogRowElement = ({ blog, handleDelete, handleEdit }: Props) => {
    return (
        <Link to={`/blog-details/${blog.id}`} className='row'>
            <div className="col-11">
                <h4 className='text-info'>{blog.access_modifier === AccessModifier.PRIVATE ? <LockOutlined /> : <UnlockOutlined />}{blog.title}</h4>
                <div>
                    {blog.blog_tags.map(tag => <span key={`blog-${tag}`} className=' bg-secondary p-1 me-2 text-light '>{tag}</span>)}
                </div>
                <div>
                    <span>Last edit:{blog.last_modified_at ?? blog.created_at}</span> <span>Views: {blog.view_count}</span>
                </div>
            </div>
            {
                (handleEdit || handleDelete) &&
                <div className="col-1">
                    <Button onClick={handleEdit}>Edit</Button><Button onClick={handleDelete}>Delete</Button>
                </div>
            }
            <hr />
        </Link>
    )
}

export default BlogRowElement