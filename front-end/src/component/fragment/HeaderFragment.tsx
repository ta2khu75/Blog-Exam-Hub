import { Link, NavLink, useLocation } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import AuthService from "../../service/AuthService";
import { resetAccount } from "../../redux/slice/accountSlice";
import { toast } from "react-toastify";
import { resetExam } from "../../redux/slice/examSlice";
import { resetUserExam } from "../../redux/slice/userExamSlice";
import { resetQuizExam } from "../../redux/slice/quizExamSlice";
import { useEffect } from "react";
import { resetQuiz } from "../../redux/slice/quizSlice";

const HeaderFragment = () => {
  const { pathname } = useLocation();
  const account = useAppSelector((state) => state.account);
  const dispatch = useAppDispatch();
  const quizzes = useAppSelector((state) => state.quiz.value)
  useEffect(() => {
    const element = document.getElementById('top');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
    if (quizzes.length > 0 && pathname !== "/manager-exam/create") {
      dispatch(resetQuiz())
    }
  }, [pathname]);
  const handleLogoutClick = () => {
    AuthService.logout().then((d) => {
      if (d.success) {
        dispatch(resetExam());
        dispatch(resetQuizExam());
        dispatch(resetUserExam());
        dispatch(resetAccount());
        toast.success("logout successful");
        window.location.reload();
      }
    });
  };
  return (
    <nav className="navbar navbar-expand-lg d-block" id="top" style={{ background: "#4f98a4" }}>
      <div className="container">
        <Link className="navbar-brand" to="/">
          <i className="bi-back" />
          <span>Exam Master</span>
        </Link>
        <div className="d-lg-none ms-auto me-4">
          <a href="#top" className="navbar-icon bi-person smoothscroll" />
        </div>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-lg-5 me-lg-auto">
            <li className="nav-item">
              <NavLink className="nav-link click-scroll" to="/home">
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link click-scroll" to="/blog">
                Blog
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link click-scroll" to="/exam">
                Exam
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link click-scroll" to="/about">
                About
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link click-scroll" to="/contact">
                Contact
              </NavLink>
            </li>
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                id="navbarLightDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Pages
              </a>
              <ul
                className="dropdown-menu dropdown-menu-light"
                aria-labelledby="navbarLightDropdownMenuLink"
              >
                <li>
                  <a className="dropdown-item" href="topics-listing.html">
                    Topics Listing
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="contact.html">
                    Contact Form
                  </a>
                </li>
              </ul>
            </li>
          </ul>
          <div className="d-none d-lg-block dropdown">
            <div className="d-flex align-items-center">
              <span
                className="navbar-icon bi-person smoothscroll"
                id="account-action"
              ></span><span className="ms-2 text-light">{account.account?.username ? `Hello ${account.account.username}` : ""}</span>
            </div>
            {!account.authenticated && (
              <ul
                className="dropdown-menu dropdown-menu-light"
                aria-labelledby="account-action"
              >
                <li>
                  <NavLink className="dropdown-item" to="login">
                    Login
                  </NavLink>
                </li>
                <li>
                  <NavLink className="dropdown-item" to="register">
                    Register
                  </NavLink>
                </li>
              </ul>
            )}
            {account.authenticated && (
              <ul
                className="dropdown-menu dropdown-menu-light"
                aria-labelledby="account-action"
              >
                <li>
                  <NavLink className="dropdown-item" to="/profile">
                    Profile
                  </NavLink>
                </li>
                <li>
                  <button
                    className="dropdown-item"
                    onClick={() => handleLogoutClick()}
                  >
                    Logout
                  </button>
                </li>
              </ul>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default HeaderFragment;
