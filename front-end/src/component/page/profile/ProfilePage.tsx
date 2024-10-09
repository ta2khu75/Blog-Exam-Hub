import { useEffect, useState } from "react"
import { NavLink, Outlet, useLocation, useNavigate, useParams } from "react-router-dom";
import AccountService from "../../../service/AccountService";
import { FollowService } from "../../../service/FollowService";
import { toast } from "react-toastify";
import { useAppSelector } from "../../../redux/hooks";
import ModalElement from "../../element/ModalElement";
import ChangeInfoForm from "../../element/form/ChangeInfoForm";
import ChangePasswordForm from "../../element/form/ChangePasswordForm";

const ProfilePage = () => {
    const { id } = useParams();
    const navigate = useNavigate()
    const { pathname } = useLocation();
    const accountAuth = useAppSelector((state) => state.account.account)
    const [account, setAccount] = useState<AccountDetailsResponse>();
    const [follow, setFollow] = useState<FollowResponse>()
    const [openChangeInfo, setOpenChangeInfo] = useState(false);
    const [openChangePassword, setOpenChangePassword] = useState(false);
    useEffect(() => {
        if (id) {
            fetchReadAuthor(id);
            fetchInit(id);
        }
    }, [id])
    const fetchReadAuthor = (id: string) => {
        AccountService.readDetailsById(id).then((data) => {
            if (data.success) {
                setAccount(data.data);
            }
        })
    }
    const fetchInit = (id: string) => {
        if (accountAuth?.id !== id)
            FollowService.checkFollowing(id).then((response) => {
                if (response.success) {
                    setFollow(response.data)
                }
            })
    }
    const handleFollowClick = () => {
        if (id) {
            if (accountAuth?.id) {
                if (!follow)
                    FollowService.follow(id).then((response) => {
                        if (response.success) {
                            setFollow(response.data)
                            toast.success("Following successfully")
                        }
                    });
                else
                    FollowService.unFollow(id).then((response) => {
                        if (response.success) {
                            setFollow(undefined)
                            toast.success("UnFollowing successfully")
                        }
                    })
            } else {
                navigate("/login")
            }
        }
    }
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
                                    <h4 className="mb-0 fw-semibold lh-1">{account?.blog_count}</h4>
                                    <p className="mb-0 fs-4"><NavLink className={`${`/profile/${id}` === pathname ? "active" : ""}`} aria-current="page" to={`/profile/${id}/blog`}>Blogs</NavLink></p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-user fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{account?.exam_count}</h4>
                                    <p className="mb-0 fs-4"><NavLink to={`/profile/${id}/exam`}>Exams</NavLink></p>
                                </div>
                                <div className="text-center">
                                    <i className="fa fa-check fs-6 d-block mb-2" />
                                    <h4 className="mb-0 fw-semibold lh-1">{account?.follow_count}</h4>
                                    <p className="mb-0 fs-4"><NavLink to={`/profile/${id}/follower`}>Followers</NavLink></p>
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
                        <div className="col-lg-4 order-last">
                            <ul className="list-unstyled d-flex align-items-center justify-content-center justify-content-lg-start my-3 gap-3">
                                {accountAuth?.id === id && <>
                                    <li><button className="btn btn-primary" onClick={() => setOpenChangeInfo(true)}>Change info</button></li>
                                    <li><button className="btn btn-primary" onClick={() => setOpenChangePassword(true)}>Change password</button></li>
                                </>}
                                {accountAuth?.id !== id &&
                                    <li><button className="btn btn-primary" onClick={() => handleFollowClick()}>{!follow ? "Follow" : "Un Follow"}</button></li>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <Outlet />
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