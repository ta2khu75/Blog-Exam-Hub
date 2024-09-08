import React, { useEffect, useState } from 'react'
import ExamService from '../../../../service/ExamService';
import ExamCartElement from '../../../element/ExamCartElement';
import { Pagination } from 'antd';
import { Link } from 'react-router-dom';
import {AppstoreAddOutlined} from '@ant-design/icons';
const ManagerExamChild = () => {
  const [pageExam, setPageExam] = useState<PageResponse<ExamResponse>>();
  const [page, setPage] = useState(1)
  useEffect(() => {
    fetchPageExam();
  }, [page]);
  const fetchPageExam = () => {
    ExamService.readPageMyExam(page, 5).then((data) => {
      if (data.success) setPageExam(data.data);
    });
  }
  return (
    <>
      <div className='d-flex justify-content-between align-items-center'>
        <h1>My Exam</h1> <Link className='btn btn-primary' to={"/exam/create"}><AppstoreAddOutlined className='me-2' />Create Exam</Link>
      </div>
      <div className="row">
        {pageExam && pageExam.content?.map(exam => <ExamCartElement key={`exam-cart-${exam.id}`} examResponse={exam} />)}
      </div>
      {pageExam && <Pagination onChange={(e) => setPage(e)} align="center" defaultCurrent={page} pageSize={6} total={pageExam.total_elements} />}
    </>
  )
}

export default ManagerExamChild;