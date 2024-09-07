import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom"
import ExamService from "../../service/ExamService";

const ExamAboutPage = () => {
    const { id } = useParams();
    const [examResponse, setExamResponse] = useState<ExamResponse>();
    useEffect(() => {
        fetchExam();
    }, [])
    const fetchExam = () => {
        if (id && !isNaN(Number(id))) {
            ExamService.readById(Number(id)).then((d) => {
                setExamResponse(d.data);
            })
        }
    }
    return (
        <div>
            <header className="site-header d-flex flex-column justify-content-center align-items-center">
                <div className="container">
                    <div className="row justify-content-center align-items-center">
                        <div className="col-lg-5 col-12 mb-5">
                            <nav aria-label="breadcrumb">
                                <ol className="breadcrumb">
                                    <li className="breadcrumb-item"><a href="index.html">Homepage</a></li>
                                    <li className="breadcrumb-item active" aria-current="page">{examResponse?.title}</li>
                                </ol>
                            </nav>
                            <h2 className="text-white">Introduction to <br />{examResponse?.title}</h2>
                            <div className="d-flex align-items-center mt-5">
                                <Link to={"/exam-details/" + examResponse?.id} className="btn custom-btn custom-border-btn smoothscroll me-4">Test</Link>
                                <a href="#top" className="custom-icon bi-bookmark smoothscroll" />
                            </div>
                        </div>
                        <div className="col-lg-5 col-12">
                            <div className="topics-detail-block bg-white shadow-lg">
                                <img src={examResponse?.image_path} className="topics-detail-block-image img-fluid" />
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <section className="topics-detail-section section-padding" id="topics-detail">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-8 col-12 m-auto">
                            <h3 className="mb-4">Introduction to {examResponse?.title}</h3>
                            <p>{examResponse?.description}</p>
                            <div className="row my-4">
                                <div className="col-lg-6 col-md-6 col-12">
                                    <img src="images/businesswoman-using-tablet-analysis.jpg" className="topics-detail-block-image img-fluid" />
                                </div>
                                <div className="col-lg-6 col-md-6 col-12 mt-4 mt-lg-0 mt-md-0">
                                    <img src="images/colleagues-working-cozy-office-medium-shot.jpg" className="topics-detail-block-image img-fluid" />
                                </div>
                            </div>
                            <p>Most people start with freelancing skills they already have as a side hustle to build up income. This extra cash can be used for a vacation, to boost up savings, investing, build business.</p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default ExamAboutPage