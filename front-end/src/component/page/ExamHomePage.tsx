import { useEffect, useState } from "react"
import ExamCategoryService from "../../service/ExamCategoryService"
import ExamService from "../../service/ExamService";
import IntroductionElement from "../element/IntoductionElement";
import ExamPageElement from "../element/exam/ExamPageElements";
import { Link, useNavigate } from "react-router-dom";

const ExamHomePage = () => {
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([]);
    const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>();
    const [page, setPage] = useState(1);
    const [keyword, setKeyword] = useState("");
    const navigate = useNavigate()
    useEffect(() => {
        fetchExamCategories();
        fetchExamPage();
    }, [])
    useEffect(() => {
        fetchExamPage();
    }, [page]);
    useEffect(() => {
        if (keyword)
            navigate("/exam/search?keyword=" + keyword)
    }, [keyword])
    const fetchExamPage = () => {
        ExamService.search({ page, size: 10 }).then(response => {
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
            <IntroductionElement keyword={keyword} setKeyword={setKeyword} into="Khám Phá Bài Thi" content="Bước Vào Thế Giới Đề Thi, Khám Phá Tiềm Năng Của Bạn!" />
            <div className="container">
                <div className="d-flex justify-content-between">
                    <h4 >Các exam mới nhất</h4>
                    <Link className="btn btn-primary" to={"/manager-exam/create"}>Create Exam</Link>
                </div>
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
                            {examPage && <div className="row">
                                <ExamPageElement examPage={examPage} page={page} setPage={setPage} />
                            </div>}
                        </div>
                    ))}
                </div>
            </div>
        </section>
    )
}

export default ExamHomePage