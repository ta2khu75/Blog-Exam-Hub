import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
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
import ProfilePage from "./component/page/ProfilePage";
import ExamHistoryPage from "./component/page/ExamHistoryPage";
import ChangePasswordPage from "./component/page/ChangePasswordPage";
// const route=createBrowserRouter(
//   createRoutesFromElements(
//     <Route path="/" element={<Ap[]}>

//     </Route>
//   )
// )
// import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<App />}>
        <Route index element={<HomePage />} />
        <Route path="login" element={<LoginPage />} />
        <Route path="change-password" element={<ChangePasswordPage />} />
        <Route path="register" element={<RegisterPage />} />
        <Route path="exam-about/:id" element={<ExamAboutPage />} />
        <Route path="profile" element={
          <PrivateRouterElement>
            <ProfilePage />
          </PrivateRouterElement>
        } />
        <Route path="exam-details/:examId" element={
          <PrivateRouterElement>
            <ExamDetailPage />
          </PrivateRouterElement>
        } />
        <Route path="exam-history/:examHistoryId" element={
          <PrivateRouterElement>
            <ExamHistoryPage />
          </PrivateRouterElement>
        } />
      </Route>
      <Route path="/admin" element={
        <PrivateRouterElement>
          <AdminComponent />
        </PrivateRouterElement>
      }>
        <Route path="account" element={<AccountCrud />} />
        <Route path="exam" element={<ExamCrud />}>
          <Route path=":id" element={<QuizList />} />
        </Route>
        <Route path="exam-view/:id" element={<QuizList />} />
      </Route>
    </>
  )
);

export default router;
