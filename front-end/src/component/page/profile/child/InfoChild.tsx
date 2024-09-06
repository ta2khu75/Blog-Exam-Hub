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
              className="rounded-circle img-fluid"
            />
            <h5 className="my-3">{account?.email}</h5>
            <p className="text-muted mb-1">Full Stack Developer</p>
            <p className="text-muted mb-4">Bay Area, San Francisco, CA</p>
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
        <div className="card mb-4 mb-lg-0">
          <div className="card-body p-0">
            <ul className="list-group list-group-flush rounded-3">
              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                <i className="fas fa-globe fa-lg text-warning" />
                <p className="mb-0">https://mdbootstrap.com</p>
              </li>
              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                <i className="fab fa-github fa-lg text-body" />
                <p className="mb-0">mdbootstrap</p>
              </li>
              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                <i
                  className="fab fa-twitter fa-lg"
                  style={{ color: "#55acee" }}
                />
                <p className="mb-0">@mdbootstrap</p>
              </li>
              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                <i
                  className="fab fa-instagram fa-lg"
                  style={{ color: "#ac2bac" }}
                />
                <p className="mb-0">mdbootstrap</p>
              </li>
              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                <i
                  className="fab fa-facebook-f fa-lg"
                  style={{ color: "#3b5998" }}
                />
                <p className="mb-0">mdbootstrap</p>
              </li>
            </ul>
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
