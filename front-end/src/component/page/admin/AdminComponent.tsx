import { Link, NavLink, Outlet, useLocation, useNavigate } from "react-router-dom"
import { useAppDispatch } from "../../../redux/hooks";
import AuthService from "../../../service/AuthService";
import { resetExam } from "../../../redux/slice/examSlice";
import { resetQuizExam } from "../../../redux/slice/quizExamSlice";
import { resetUserExam } from "../../../redux/slice/userExamSlice";
import { resetAccount } from "../../../redux/slice/accountSlice";
import { toast } from "react-toastify";
import { resetImages } from "../../../redux/slice/imageSlice";
import { resetBlogHistory } from "../../../redux/slice/blogHistorySlice";

const AdminComponent = () => {
  const { pathname } = useLocation()
  const dispatch = useAppDispatch()
  const navigate = useNavigate()
  const handleLogoutClick = () => {
    AuthService.logout().then((d) => {
      if (d.success) {
        dispatch(resetExam());
        dispatch(resetQuizExam());
        dispatch(resetUserExam());
        dispatch(resetAccount());
        dispatch(resetImages());
        dispatch(resetBlogHistory());
        toast.success("logout successful");
        navigate("/login");
      }
    });
  };
  return (
    <div>
      {/*Main Navigation*/}
      <div style={{ height: "50px" }}></div>
      <header className="row mt-5">
        {/* Sidebar */}
        <nav id="sidebarMenu" className="collapse col-2 d-lg-block sidebar collapse bg-white">
          <div className="position-sticky">
            <div className="list-group list-group-flush mx-3 mt-4">
              <NavLink to={"/admin/dashboard"} className={`list-group-item list-group-item-action py-2 ${pathname === "/admin" ? "active" : ""}`} data-mdb-ripple-init aria-current="true">
                <i className="fas fa-tachometer-alt fa-fw me-3" /><span>Main dashboard</span>
              </NavLink>
              <NavLink to={"/admin/exam"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
                <i className="fas fa-chart-area fa-fw me-3" /><span>Exam</span>
              </NavLink>
              <NavLink to={"/admin/account"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
                <i className="fas fa-chart-area fa-fw me-3" /><span>Account</span>
              </NavLink>
              <NavLink to={"/admin/role"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
                <i className="fas fa-chart-area fa-fw me-3" /><span>Role</span>
              </NavLink>
              <NavLink to={"/admin/exam-category"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
                <i className="fas fa-chart-area fa-fw me-3" /><span>Exam category</span>
              </NavLink>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-lock fa-fw me-3" /><span>Password</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-chart-line fa-fw me-3" /><span>Analytics</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
                <i className="fas fa-chart-pie fa-fw me-3" /><span>SEO</span>
              </a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-chart-bar fa-fw me-3" /><span>Orders</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-globe fa-fw me-3" /><span>International</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-building fa-fw me-3" /><span>Partners</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-calendar fa-fw me-3" /><span>Calendar</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-users fa-fw me-3" /><span>Users</span></a>
              <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-money-bill fa-fw me-3" /><span>Sales</span></a>
            </div>
          </div>
        </nav>
        {/* Sidebar */}
        {/* Navbar */}
        <nav id="main-navbar" className="navbar navbar-expand-lg navbar-light bg-white fixed-top">
          {/* Container wrapper */}
          <div className="container-fluid">
            {/* Toggle button */}
            <button className="navbar-toggler" type="button" data-mdb-collapse-init data-mdb-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
              <i className="fas fa-bars" />
            </button>
            {/* Brand */}
            <Link className="navbar-brand text-primary" to="/admin">
              Blog Exam Hub Admin
            </Link>
            {/* Right links */}
            <ul className="navbar-nav me-5 d-flex flex-row">
              {/* Avatar */}
              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle hidden-arrow d-flex align-items-center" href="#" id="navbarDropdownMenuLink" role="button" data-mdb-dropdown-init aria-expanded="false">
                  <img src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg" className="rounded-circle" height={22} loading="lazy" />
                </a>
                <ul className="dropdown-menu dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  <li><Link className="dropdown-item" to="/profile">Profile</Link></li>
                  <li><button className="dropdown-item" onClick={handleLogoutClick} >Logout</button></li>
                </ul>
              </li>
            </ul>
          </div>
          {/* Container wrapper */}
        </nav>
        <main className="col-10">
          {/* Section: Main chart */}
          <section className="mb-4">
            <div className="card">
              <div className="card-header py-3">
                <h5 className="mb-0 text-center"><strong>Sales</strong></h5>
              </div>
              <div className="card-body">
                <Outlet />
              </div>
            </div>
          </section>
        </main>
      </header>
    </div>


  )
}

export default AdminComponent