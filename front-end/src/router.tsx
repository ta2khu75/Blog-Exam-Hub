import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import AdminComponent from "./component/AdminComponent";
import LoginPage from "./component/page/LoginPage";
import HomePage from "./component/page/HomePage";
import AccountCrud from "./component/page/admin/crud/AccountCrud";
import ExamCrud from "./component/page/admin/crud/ExamCrud";
import RegisterPage from "./component/page/RegisterPage";
import ExamAboutPage from "./component/page/ExamAboutPage";
import ExamDetailPage from "./component/page/ExamDetailPage";
import QuizList from "./component/page/admin/list/QuizList";
import PrivateRouterElement from "./component/element/PrivateRouterElement";

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
      },
      {
        path: "exam-details/:examId",
        element: (
          <PrivateRouterElement>
            <ExamDetailPage />
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
        path: "account",
        element: <AccountCrud />,
      },
      {
        path: "exam",
        element: <ExamCrud />,
        children: [
          {
            path: ":id",
            element: <QuizList />,
          },
        ],
      },
      {
        path: "exam-view/:id",
        element: <QuizList />,
      },
    ],
  },
]);
export default router;
