import { Link, NavLink } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import AuthService from "../../service/AuthService";
import { resetAccount } from "../../redux/slice/accountSlice";
import { toast } from "react-toastify";
import { resetExam } from "../../redux/slice/examSlice";
import { resetUserExam } from "../../redux/slice/useExamSlice";
import { resetQuizExam } from "../../redux/slice/quizExamSlice";

const HeaderFragment = () => {
  const authenticated = useAppSelector((state) => state.account.authenticated);
  const dispatch = useAppDispatch();
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
    <nav className="navbar navbar-expand-lg" style={{ background: "#4f98a4" }}>
      <div className="container">
        <Link className="navbar-brand" to="/">
          <i className="bi-back" />
          <span>Topic</span>
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
              <NavLink className="nav-link click-scroll" to="/">
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <a className="nav-link click-scroll" href="#section_2">
                Browse Topics
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link click-scroll" href="#section_3">
                How it works
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link click-scroll" href="#section_4">
                FAQs
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link click-scroll" href="#section_5">
                Contact
              </a>
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
            <a
              href="#top"
              className="navbar-icon bi-person smoothscroll"
              id="account-action"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            />
            {!authenticated && (
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
            {authenticated && (
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
                  <NavLink className="dropdown-item" to="/change-password">
                    Change Password
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
