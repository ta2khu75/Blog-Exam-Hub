import { useEffect, useState } from "react"
import { BlogService } from "../../service/BlogService";
import ExamService from "../../service/ExamService";
import { NavLink, Outlet, useLocation, useParams } from "react-router-dom";
import AccountService from "../../service/AccountService";

const AuthorPage = () => {
    const { pathname } = useLocation();
    const { authorId } = useParams();
    const [account, setAccount] = useState<AccountResponse>();
    const [countBlog, setCountBlog] = useState(0);
    const [countExam, setCountExam] = useState(0);
    useEffect(() => {
        if (authorId) {
            fetchReadAuthor(authorId);
            fetchInit(authorId);
        }

    }, [authorId])
    const fetchReadAuthor = (authorId: string) => {
        AccountService.readById(authorId).then((data) => {
            if (data.success) {
                setAccount(data.data);
            }
        })
    }
    const fetchInit = (authorId: string) => {
        BlogService.countByAuthor(authorId).then((data) => {
            if (data.success) {
                setCountBlog(data.data.total_element);
            }
        })
        ExamService.countByAuthor(authorId).then((data) => {
            if (data.success) {
                setCountExam(data.data.total_element);
            }
        })
    }
    return (
        <div className="container">
            <div className="card p-5 overflow-hidden">
                <div className="card-body p-0">
                    <div className="row align-items-center">
                        <div className="col-lg-4 order-lg-1 order-2">
                            <div className="d-flex align-items-center justify-content-around m-4">
                                <div className="text-center">
                                    <i className="fa fa-file fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{countBlog}</h4>
                                    <p className="mb-0 fs-4">Blogs</p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-user fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{countExam}</h4>
                                    <p className="mb-0 fs-4">Exams</p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-check fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">2,659</h4>
                                    <p className="mb-0 fs-4">Following</p>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 mt-n3 order-lg-2 order-1">
                            <div className="mt-n5">
                                <div className="d-flex align-items-center justify-content-center mb-2">
                                    <div className="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style={{ width: 110, height: 110 }} >
                                        <div className="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style={{ width: 100, backgroundColor: "#4F98A4", height: 100 }} >
                                            <h1 className="text-light">{account?.username?.[0]?.toUpperCase()}</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="text-center">
                                <h5 className="fs-5 mb-0 fw-semibold">{account?.username}</h5>
                            </div>
                            <ul className="text-center list-unstyled" >
                                <li><b>First name</b>: {account?.first_name}</li>
                                <li><b>Last name</b>: {account?.last_name}</li>
                                <li><b>Birth day</b>: {account?.birthday}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <nav className="navbar-expand-lg" style={{ backgroundColor: "#4B94A1" }}>
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <NavLink className={`nav-link ${`/author/${authorId}` === pathname ? "active" : ""}`} aria-current="page" to={`/author/${authorId}/blog`}>Blogs</NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink className="nav-link " to={`/author/${authorId}/exam`}>Exams</NavLink>
                    </li>
                </ul>
            </nav>
            <Outlet />
        </div>

    )
}

export default AuthorPage;