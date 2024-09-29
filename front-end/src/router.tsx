import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import AdminComponent from "./component/page/admin/AdminComponent";
import LoginPage from "./component/page/LoginPage";
import HomePage from "./component/page/HomePage";
import AccountCrud from "./component/page/admin/child/ManagerAccountChild";
import ExamCrud from "./component/page/admin/Manager/ManagerExam";
import RegisterPage from "./component/page/RegisterPage";
import ExamAboutPage from "./component/page/ExamAboutPage";
import ExamDetailPage from "./component/page/ExamDetailPage";
import PrivateRouterElement from "./component/element/PrivateRouterElement";
import ExamHistoryPage from "./component/page/ExamHistoryPage";
import ManagerRole from "./component/page/admin/child/ManagerRoleChild";
import ManagerExamCategory from "./component/page/admin/child/ManagerExamCategoryChild";
import ProfilePage from "./component/page/profile/ProfilePage";
import InfoChild from "./component/page/profile/child/InfoChild";
import ChangePasswordChild from "./component/page/profile/child/ChangePasswordChild";
import ChangeInfoChild from "./component/page/profile/child/ChangeInfoChild";
import ManagerExamChild from "./component/page/profile/child/ManagerExamChild";
import ExamPage from "./component/page/exam/ExamPage";
import ExamCreateChild from "./component/page/exam/child/ExamCreateChild";
import BlogPage from "./component/page/blog/BlogPage";
import ManagerBlogChild from "./component/page/profile/child/ManagerBlogChild";
import BlogHomePage from "./component/page/BlogHomePage";
import ExamHomePage from "./component/page/ExamHomePage";
import BlogDetailsPage from "./component/page/BlogDetailsPage";
import ContactPage from "./component/page/ContactPage";
import AboutPage from "./component/page/AboutPage";
import TestIntern from "./component/page/TestIntern";
import AuthorPage from "./component/page/AuthorPage";
import ExamResultChild from "./component/page/profile/child/ExamResultChild";
import Filter from "./component/page/Filter";
import BlogListPage from "./component/page/BlogListPage";
import ExamListPage from "./component/page/ExamListPage";
import DashboardChild from "./component/page/admin/child/DashboardChild";
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: "home",
        element: <HomePage />,
      }
      , {
        path: "blog",
        element: <BlogHomePage />
      }, {
        path: "exam",
        element: <ExamHomePage />
      },
      {
        path: "login",
        element: <LoginPage />,
      },
      {
        path: "register",
        element: <RegisterPage />,
      },
      {
        path: "exam-about/:id",
        element: <ExamAboutPage />,
      }, {
        path: "blog-details/:blogId",
        element: <BlogDetailsPage />
      }, {
        path: "contact",
        element: <ContactPage />
      }, {
        path: "about",
        element: <AboutPage />
      }, {
        path: "test",
        element: <TestIntern />
      }, {
        path: "author/:authorId",
        element: <AuthorPage />
      }, {
        path: "filter",
        element: <Filter />
      },
      {
        path: "blog/search",
        element: <BlogListPage />
      }, {
        path: "exam/search",
        element: <ExamListPage />
      },
      {
        path: "profile",
        element: (
          <PrivateRouterElement>
            <ProfilePage />
          </PrivateRouterElement>
        ),
        children: [
          {
            index: true,
            element: <InfoChild />,
          },
          {
            path: "exam-result",
            element: <ExamResultChild />
          },
          {
            path: "account",
            element: <InfoChild />,
          },
          {
            path: "change-password",
            element: <ChangePasswordChild />,
          },
          {
            path: "change-info",
            element: <ChangeInfoChild />,
          },
          {
            path: "manager-exam",
            element: <ManagerExamChild />,
          }, {
            path: "manager-blog",
            element: <ManagerBlogChild />
          }
        ],
      },
      {
        path: "manager-exam",
        element: (
          <PrivateRouterElement>
            <ExamPage />
          </PrivateRouterElement>
        ),
        children: [{
          path: "create",
          element: <ExamCreateChild />
        }, { path: ":examId", element: <ExamCreateChild /> }]
      }, {
        path: "manager-blog/create",
        element: (
          <PrivateRouterElement>
            <BlogPage />
          </PrivateRouterElement>
        ),
      }, {
        path: "manager-blog/:blogId",
        element: (
          <PrivateRouterElement>
            <BlogPage />
          </PrivateRouterElement>
        )
      },
      {
        path: "exam-details/:examId",
        element: (
          <PrivateRouterElement>
            <ExamDetailPage />
          </PrivateRouterElement>
        ),
      },
      {
        path: "exam-history/:examHistoryId",
        element: (
          <PrivateRouterElement>
            <ExamHistoryPage />
          </PrivateRouterElement>
        ),
      },
    ],
  },
  {
    path: "/admin",
    element: (
      <PrivateRouterElement>
        <AdminComponent />
      </PrivateRouterElement>
    ),
    children: [
      {
        index: true,
        element: <DashboardChild />,
      },
      {
        path: "dashboard",
        element: <DashboardChild />
      },
      {
        path: "account",
        element: <AccountCrud />,
      },
      {
        path: "exam",
        element: <ExamCrud />,
      },
      {
        path: "role",
        element: <ManagerRole />,
      },
      {
        path: "exam-category",
        element: <ManagerExamCategory />,
      },
    ],
  },
]);


export default router;
