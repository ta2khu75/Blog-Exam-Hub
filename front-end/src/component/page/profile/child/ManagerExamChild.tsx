import { useEffect, useState } from 'react'
import ExamService from '../../../../service/ExamService';
import { Pagination } from 'antd';
import { Link, useNavigate } from 'react-router-dom';
import { AppstoreAddOutlined } from '@ant-design/icons';
import ExamCartElementNew from '../../../element/exam/ExamCartElement';
const ManagerExamChild = () => {
  const navigate = useNavigate()
  const [pageExam, setPageExam] = useState<PageResponse<ExamResponse>>();
  const [page, setPage] = useState(1)
  useEffect(() => {
    fetchPageExam();
  }, [page]);
  const fetchPageExam = () => {
    ExamService.search({ page, size: 6 }).then((data) => {
      if (data.success) setPageExam(data.data);
    });
  }
  const handleDeleteClick = (id: string) => {
    ExamService.delete(id).then((d) => {
      if (d.success) {
        fetchPageExam();
      }
    });
  };
  const handleEditClick = (id: string) => {
    navigate(`/manager-exam/${id}`)
  };
  return (
    <>
      <div className='d-flex justify-content-between align-items-center'>
        <h1>My Exam</h1> <Link className='btn btn-primary' to={"/manager-exam/create"}><AppstoreAddOutlined className='me-2' />Create Exam</Link>
      </div>
      <div className="row">
        {pageExam?.content?.map(exam => <ExamCartElementNew handleDelete={() => handleDeleteClick(exam.id)} handleEdit={() => handleEditClick(exam.id)} key={`exam-cart-${exam.id}`} exam={exam} />)}
      </div>
      {pageExam && <Pagination onChange={(e) => setPage(e)} align="center" defaultCurrent={page} pageSize={6} total={pageExam.total_elements} />}
    </>
  )
}

export default ManagerExamChild;