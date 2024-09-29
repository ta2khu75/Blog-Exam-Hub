import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import AccountService from "../../service/AccountService";
// import { BlogService } from "../../service/BlogService";
// import ExamService from "../../service/ExamService";
import { Tabs, TabsProps } from "antd";
// import BlogPageElement from "../element/blog/BlogExamElement";
// import ExamPageElement from "../element/exam/ExamPageElements";
import BlogListPage from "./BlogListPage";
import ExamListPage from "./ExamListPage";
import ExamService from "../../service/ExamService";
import { BlogService } from "../../service/BlogService";

const AuthorPage = () => {
    const { authorId } = useParams();
    const [author, setAuthor] = useState<AccountResponse>()
    const [totalBlog, setTotalBlog] = useState<CountResponse>()
    const [totalExam, setTotalExam] = useState<CountResponse>()
    useEffect(() => {
        if (authorId) {
            fetchAuthor(authorId);
            fetchCountBlog(authorId);
            fetchCountExam(authorId);
        }
    }, [authorId])

    const fetchAuthor = (authorId: string) => {
        AccountService.readById(authorId).then(response => {
            if (response.success) {
                setAuthor(response.data)
            }
        })
    }
    const fetchCountExam = (authorId: string) => {
        ExamService.countByAuthor(authorId).then(response => {
            if (response.success) setTotalExam(response.data)
        })
    }
    const fetchCountBlog = (authorId: string) => {
        BlogService.countByAuthor(authorId).then(response => {
            if (response.success) setTotalBlog(response.data)
        })
    }
    const items: TabsProps['items'] = [
        {
            key: '1',
            label: 'Blogs',
            children: <BlogListPage authorId={authorId} />
        },
        {
            key: '2',
            label: 'Exams',
            children: <ExamListPage authorId={authorId} />
        }
    ];

    return (
        <div className="container">
            <div className="card overflow-hidden">
                <div className="card-body p-0">
                    <div className="row">
                        <div className="col-lg-4 order-lg-1 order-2"></div>
                        <div className="col-lg-4 mt-n3 order-lg-2 order-1">
                            <div className="mt-n5">
                                <div className="d-flex align-items-center justify-content-center mb-2">
                                    <div className="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style={{ width: 110, height: 110 }} >
                                        <div className="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style={{ width: 100, backgroundColor: "#2ECA6A", height: 100 }} >
                                            <h1 className="text-light">{author?.username?.[0]?.toUpperCase()}</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="text-center">
                                <h5 className="fs-5 mb-0 fw-semibold">{author?.username}</h5>
                            </div>
                        </div>
                        <div className="col-lg-4 order-last">
                            <div className="d-flex align-items-center justify-content-around m-4">
                                <div className="text-center">
                                    <i className="fa fa-file fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{totalBlog?.total_element ?? 0}</h4>
                                    <p className="mb-0 fs-4">Blogs</p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-user fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{totalExam?.total_element ?? 0}</h4>
                                    <p className="mb-0 fs-4">Exams</p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-check fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">2,659</h4>
                                    <p className="mb-0 fs-4">Followers</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <Tabs defaultActiveKey="1" items={items} />;

            </div>
        </div>
    )
}

export default AuthorPage;