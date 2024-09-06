import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import App from "./App";
import AdminComponent from "./component/page/admin/AdminComponent";
import LoginPage from "./component/page/LoginPage";
import HomePage from "./component/page/HomePage";
import AccountCrud from "./component/page/admin/Manager/ManagerAccount";
import ExamCrud from "./component/page/admin/Manager/ManagerExam";
import RegisterPage from "./component/page/RegisterPage";
import ExamAboutPage from "./component/page/ExamAboutPage";
import ExamDetailPage from "./component/page/ExamDetailPage";
import QuizList from "./component/page/admin/list/QuizList";
import PrivateRouterElement from "./component/element/PrivateRouterElement";
import ExamHistoryPage from "./component/page/ExamHistoryPage";
import AdComponent from "./component/page/admin/ADComponent";
import ManagerRole from "./component/page/admin/Manager/ManagerRole";
import ManagerExamCategory from "./component/page/admin/Manager/ManagerExamCategory";
import ProfilePage from "./component/page/profile/ProfilePage";
import InfoChild from "./component/page/profile/child/InfoChild";
import ChangePasswordChild from "./component/page/profile/child/ChangePasswordChild";
import ChangeInfoChild from "./component/page/profile/child/ChangeInfoChild";
const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<App />}>
        <Route index element={<HomePage />} />
        <Route path="login" element={<LoginPage />} />
        {/* <Route path="change-password" element={<ChangePasswordPage />} /> */}
        <Route path="register" element={<RegisterPage />} />
        <Route path="exam-about/:id" element={<ExamAboutPage />} />
        <Route path="profile" element={
          <PrivateRouterElement>
            <ProfilePage />
          </PrivateRouterElement>
        }>
          <Route index element={<InfoChild />} />
          <Route path="account" element={<InfoChild />} />
          <Route path="change-password" element={<ChangePasswordChild />} />
          <Route path="change-info" element={<ChangeInfoChild />} />
        </Route>
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
          <AdComponent />
        </PrivateRouterElement>
      }>
        <Route path="account" element={<AccountCrud />} />
        <Route path="exam" element={<ExamCrud />}>
          <Route path=":id" element={<QuizList />} />
        </Route>
        <Route path="role" element={<ManagerRole />} />
        <Route path="exam-category" element={<ManagerExamCategory />} />
        <Route path="exam-view/:id" element={<QuizList />} />
      </Route>
    </>
  )
);

export default router;
