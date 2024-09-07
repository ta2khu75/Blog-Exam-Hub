import avatar from "../../../../assets/EFjuFv.gif";
import { useEffect, useState } from "react";
import ExamHistoryService from "../../../../service/ExamResultService";
import TableElement from "../../../element/TableElement";
import { useNavigate } from "react-router-dom";
import AuthService from "../../../../service/AuthService";
const InfoChild = () => {
  const navigate = useNavigate()
  const [examHistoryPage, setExamHistoryPage] =
    useState<PageResponse<ExamResultResponse>>();
  const [account, setAccount] = useState<AccountResponse>();
  useEffect(() => {
    fetchExamHistoryPage();
    fetchReadAccount();
  }, []);
  const fetchReadAccount = () => {
    AuthService.myAccount().then((data) => {
      if (data.success) setAccount(data.data);
    });
  }
  const fetchExamHistoryPage = () => {
    ExamHistoryService.readPage().then((data) => {
      if (data.success) setExamHistoryPage(data.data);
    });
  };
  const handleViewClick = (data: ExamResultResponse) => {
    navigate(`/exam-history/${data.id}`)
  }
  return (
    <div className="row">
      <div className="col-lg-4">
        <div className="card mb-4">
          <div className="card-body text-center">
            <img
              src={avatar}
              alt="avatar"
              className=" img-fluid"
            />
            <h5 className="my-3">{account?.username}</h5>
            <div className="d-flex justify-content-center mb-2">
              <button
                type="button"
                data-mdb-button-init
                data-mdb-ripple-init
                className="btn btn-primary"
              >
                Follow
              </button>
              <button
                type="button"
                data-mdb-button-init
                data-mdb-ripple-init
                className="btn btn-outline-primary ms-1"
              >
                Message
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="col-lg-8">
        <div className="card mb-4">
          <div className="card-body">
            <div className="row">
              <div className="col-sm-3">
                <p className="mb-0">Username</p>
              </div>
              <div className="col-sm-9">
                <p className="text-muted mb-0">{account?.username}</p>
              </div>
            </div>
            <hr />
            <div className="row">
              <div className="col-sm-3">
                <p className="mb-0">Email</p>
              </div>
              <div className="col-sm-9">
                <p className="text-muted mb-0">{account?.email}</p>
              </div>
            </div>
            <hr />
            <div className="row">
              <div className="col-sm-3">
                <p className="mb-0">First Name</p>
              </div>
              <div className="col-sm-9">
                <p className="text-muted mb-0">{account?.first_name}</p>
              </div>
            </div>
            <hr />
            <div className="row">
              <div className="col-sm-3">
                <p className="mb-0">Last Name</p>
              </div>
              <div className="col-sm-9">
                <p className="text-muted mb-0">{account?.last_name}</p>
              </div>
            </div>
            <hr />
            <div className="row">
              <div className="col-sm-3">
                <p className="mb-0">Birthday</p>
              </div>
              <div className="col-sm-9">
                <p className="text-muted mb-0">{account?.birthday}</p>
              </div>
            </div>
          </div>
        </div>
        <div className="row">
          <TableElement visiableColumns={["id", "point", "created_date", "last_modified_date"]} handleViewClick={handleViewClick} array={examHistoryPage?.content ?? []} />
        </div>
      </div>
    </div>
  );
};

export default InfoChild;
