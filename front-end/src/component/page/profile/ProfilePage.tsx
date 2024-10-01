import { useEffect, useMemo, useState } from "react"
import AuthService from "../../../service/AuthService";
import { BlogService } from "../../../service/BlogService";
import ExamService from "../../../service/ExamService";
import ModalElement from "../../element/ModalElement";
import ChangeInfoForm from "../../element/form/ChangeInfoForm";
import ChangePasswordForm from "../../element/form/ChangePasswordForm";
import { useLocation } from "react-router-dom";
import { Tabs, TabsProps } from "antd";
import BlogListPage from "../BlogListPage";
import ExamListPage from "../ExamListPage";
import ManagerBlogChild from "./child/ManagerBlogChild";
import ManagerExamChild from "./child/ManagerExamChild";
import ExamResultChild from "./child/ExamResultChild";
import AvatarElement from "../../element/AvatarElement";

const ProfilePage = () => {
    const { pathname } = useLocation()
    const [account, setAccount] = useState<AccountResponse>();
    const [countBlog, setCountBlog] = useState(0);
    const [countExam, setCountExam] = useState(0);
    const [openChangeInfo, setOpenChangeInfo] = useState(false);
    const [openChangePassword, setOpenChangePassword] = useState(false);
    useEffect(() => {
        fetchInit();
        fetchMyAccount();
    }, [])
    const fetchMyAccount = () => {
        AuthService.myAccount().then((data) => {
            if (data.success) {
                setAccount(data.data);
            }
        })
    }
    const fetchInit = () => {
        BlogService.myCount().then((data) => {
            if (data.success) {
                setCountBlog(data.data.total_element);
            }
        })
        ExamService.myCount().then((data) => {
            if (data.success) {
                setCountExam(data.data.total_element);
            }
        })
    }
    const tab = () => {
        const items: TabsProps['items'] = [
            {
                key: 'blog-tab',
                label: 'Blogs',
                children: pathname === "/profile" ? <ManagerBlogChild /> : <BlogListPage />,
            },
            {
                key: 'exam-tab',
                label: 'Exams',
                children: pathname === "/profile" ? <ManagerExamChild /> : <ExamListPage />,
            },
        ];
        if (pathname === "/profile") {
            items.push({
                key: 'exam-result-tab',
                label: 'Exam results',
                children: <ExamResultChild />
            })
        }
        return items;
    }
    const tabMemo = useMemo(() => {
        return tab();
    }, [pathname])
    const handleChangeInfoCancel = () => {
        setOpenChangeInfo(false);
    }
    const handleChangePasswordCancel = () => {
        setOpenChangePassword(false);
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
                                        {account && <AvatarElement username={account.username} />}
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
                        {pathname === "/profile" &&
                            <div className="col-lg-4 order-last">
                                <ul className="list-unstyled d-flex align-items-center justify-content-center justify-content-lg-start my-3 gap-3">
                                    <li><button className="btn btn-primary" onClick={() => setOpenChangeInfo(true)}>Change info</button></li>
                                    <li><button className="btn btn-primary" onClick={() => setOpenChangePassword(true)}>Change password</button></li>
                                </ul>
                            </div>
                        }
                    </div>
                </div>
                <Tabs defaultActiveKey="1" items={tabMemo} />
            </div>
            {account &&
                <ModalElement title="Change info" handleCancel={handleChangeInfoCancel} open={openChangeInfo}>
                    <ChangeInfoForm account={account} setAccount={setAccount} />
                </ModalElement>
            }
            {account &&
                <ModalElement title="Change password" handleCancel={handleChangePasswordCancel} open={openChangePassword}>
                    <ChangePasswordForm />
                </ModalElement>
            }
        </div>
    )
}

export default ProfilePage;