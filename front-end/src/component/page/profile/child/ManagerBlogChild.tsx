import { useEffect, useState } from 'react'
import ExamService from '../../../../service/ExamService';
import { Pagination } from 'antd';
import { Link, useNavigate } from 'react-router-dom';
import { AppstoreAddOutlined } from '@ant-design/icons';
import { BlogService } from '../../../../service/BlogService';
import BlogRowElement from '../../../element/BlogRowElement';
const ManagerBlogChild = () => {
  const navigate = useNavigate()
  const [pageExam, setPageExam] = useState<PageResponse<BlogResponse>>();
  const [page, setPage] = useState(1)
  useEffect(() => {
    fetchPageBlog();
  }, [page]);
  const fetchPageBlog = () => {
    BlogService.readPageMyBlog(page, 6).then((data) => {
      if (data.success) setPageExam(data.data);
    });
  }
  const handleDeleteClick = (id: string) => {
    ExamService.delete(id).then((d) => {
      if (d.success) {
        fetchPageBlog();
      }
    });
  };
  const handleEditClick = (id: string) => {
    navigate(`/manager-blog/${id}`)
  };
  return (
    <>
      <div className='d-flex justify-content-between align-items-center'>
        <h1>My blog</h1> <Link className='btn btn-primary' to={"/manager-blog/create"}><AppstoreAddOutlined className='me-2' />Create Blog</Link>
      </div>
      <div className="row">
        {pageExam?.content?.map(blog => <BlogRowElement blog={blog} handleDelete={() => handleDeleteClick(blog.id)} handleEdit={() => handleEditClick(blog.id)} />)}
      </div>
      {pageExam && <Pagination onChange={(e) => setPage(e)} align="center" defaultCurrent={page} pageSize={6} total={pageExam.total_elements} />}
    </>
  )
}

export default ManagerBlogChild;