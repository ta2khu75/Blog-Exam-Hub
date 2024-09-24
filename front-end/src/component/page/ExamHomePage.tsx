import { useEffect, useState } from "react"
import ExamCategoryService from "../../service/ExamCategoryService"
import ExamService from "../../service/ExamService";
import ExamCartElement from "../element/exam/ExamCartElement";
import IntroductionElement from "../element/IntoductionElement";
import { Pagination } from "antd";

const ExamHomePage = () => {
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([]);
    const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>();
    const [page, setPage] = useState(1);
    useEffect(() => {
        fetchExamCategories();
        fetchExamPage();
    }, [])
    useEffect(() => {
        fetchExamPage();
    }, [page]);
    const fetchExamPage = () => {
        ExamService.readPage().then(response => {
            if (response.success) {
                setExamPage(response.data);
            }
        })
    }
    const fetchExamCategories = () => {
        ExamCategoryService.readAll().then(response => {
            if (response.success) {
                setExamCategories(response.data)
            }
        })
    }
    return (
        <section className="explore-section" id="section_2">
            <IntroductionElement into="Khám Phá Bài Thi" content="Bước Vào Thế Giới Đề Thi, Khám Phá Tiềm Năng Của Bạn!" />
            <div className="container">
                <ul className="nav nav-tabs justify-content-center mb-4" id="myTab" role="tablist">
                    {examCategories.map((examCategory, index) => (
                        <li key={examCategory.id} className="nav-item" role="presentation">
                            <button
                                className={`nav-link ${index === 0 ? 'active' : ''}`}
                                id={`tab-${examCategory.id}`}
                                data-bs-toggle="tab"
                                data-bs-target={`#tab-pane-${examCategory.id}`}
                                type="button"
                                role="tab"
                                aria-controls={`tab-pane-${examCategory.id}`}
                                aria-selected={index === 0}
                            >
                                {examCategory.name}
                            </button>
                        </li>
                    ))}
                </ul>

                <div className="tab-content" id="myTabContent">
                    {examCategories.map((examCategory, index) => (
                        <div
                            key={examCategory.id}
                            className={`tab-pane fade ${index === 0 ? 'show active' : ''}`}
                            id={`tab-pane-${examCategory.id}`}
                            role="tabpanel"
                            aria-labelledby={`tab-${examCategory.id}`}
                            tabIndex={0}
                        >
                            <div className="row">
                                {examPage?.content?.map(examResponse => (
                                    <ExamCartElement key={`exam-cart-${examResponse.id}`} exam={examResponse} />
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <Pagination align="center" onChange={setPage} pageSize={10} defaultCurrent={page} total={examPage?.total_elements} />
        </section>
    )
}

export default ExamHomePage