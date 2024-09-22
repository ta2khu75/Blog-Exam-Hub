import React from 'react'
import { Link } from 'react-router-dom'
type Props = {
    blog: BlogResponse
}
const BlogItemElement = ({ blog }: Props) => {
    return (
        <div className="custom-block custom-block-topics-listing bg-white shadow-lg mb-5">
            <div className="d-flex">
                <div className="custom-block-topics-listing-info d-flex">
                    <div>
                        <div>{blog.author.username}</div>
                        <h5 className="mb-2">{blog.title}</h5>
                        <p>{blog.blog_tags?.map((tag, index) => <span className='me-4' key={"span-tag-" + index}>{tag}</span>)}</p>
                        <p className="mb-0">{blog.created_at}</p>
                        <p className="mb-0">{blog.view_count}</p>
                        {/* <p className="mb-0">Topic Listing includes home, listing, detail and contact pages. Feel free to modify this template for your custom websites.</p> */}
                        <Link to={`/blog-details/${blog.id}`} className="btn custom-btn mt-3 mt-lg-4">Learn More</Link>
                    </div>
                    <span className="badge bg-design rounded-pill ms-auto">14</span>
                </div>
            </div>
        </div>
    )
}


export default BlogItemElement