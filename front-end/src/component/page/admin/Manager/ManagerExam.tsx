import ExamService from "../../../../service/ExamService";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import ExamCartElement from "../../../element/exam/ExamCartElement";
import ExamCategoryService from "../../../../service/ExamCategoryService";
import ExamForm from "../../../element/form/ExamForm";
const ExamCrud = () => {
  const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>()
  const [exam, setExam] = useState<ExamResponse>();
  const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>();
  useEffect(() => {
    fetchPageExam();
    fetchAllExamCategory();
  }, []);
  const fetchAllExamCategory = () => {
    ExamCategoryService.readAll().then((response) => {
      if (response.success) setExamCategories(response.data)
    });
  }
  const fetchPageExam = () => {
    ExamService.search({ page: 1, size: 10 }).then((data) => {
      if (data.success) setExamPage(data.data)
    });
  }
  const handleEditClick = (data: ExamResponse) => {
    const element = document.getElementById('form');
    console.log(element);

    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
    setExam(data);
  }
  const handleDeleteClick = (id: string) => {
    ExamService.delete(id).then((d) => {
      if (d.success) {
        toast.success("Successfully to delete");
        setExamPage(examPage => examPage ? { ...examPage, content: examPage.content.filter(exam => exam.id !== id) } : {
          content: [],
          total_pages: 1,
          total_elements: 1
        })
      } else {
        toast.error(d.message_error);
      }
    })
  }
  return (
    <>
      <ExamForm id="form" exam={exam} setExam={setExam} examCategories={examCategories} setExamPage={setExamPage} />
      <div className="row">
        {examPage?.content?.map((examResponse) => <ExamCartElement exam={examResponse} key={`exam-cart-${examResponse.id}`} handleDelete={() => handleDeleteClick(examResponse.id)} handleEdit={() => handleEditClick(examResponse)} />)}
      </div>
    </>
  )
}

export default ExamCrud