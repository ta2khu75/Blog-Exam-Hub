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
import ManagerExamChild from "./component/page/profile/child/ManagerExamChild";
import ExamPage from "./component/page/exam/ExamPage";
import ExamCreateChild from "./component/page/exam/child/ExamCreateChild";
// const router = createBrowserRouter(
//   createRoutesFromElements(
//     <>
//       <Route path="/" element={<App />}>
//         <Route index element={<HomePage />} />
//         <Route path="login" element={<LoginPage />} />
//         {/* <Route path="change-password" element={<ChangePasswordPage />} /> */}
//         <Route path="register" element={<RegisterPage />} />
//         <Route path="exam-about/:id" element={<ExamAboutPage />} />
//         <Route path="profile" element={
//           <PrivateRouterElement>
//             <ProfilePage />
//           </PrivateRouterElement>
//         }>
//           <Route index element={<InfoChild />} />
//           <Route path="account" element={<InfoChild />} />
//           <Route path="change-password" element={<ChangePasswordChild />} />
//           <Route path="change-info" element={<ChangeInfoChild />} />
//           <Route path="manager-exam" element={<ManagerExamChild />} />
//         </Route>
//         <Route path="exam" element={
//           <PrivateRouterElement>
//             <ExamPage />
//           </PrivateRouterElement>
//         } />
//         <Route path="exam-details/:examId" element={
//           <PrivateRouterElement>
//             <ExamDetailPage />
//           </PrivateRouterElement>
//         } />
//         <Route path="exam-history/:examHistoryId" element={
//           <PrivateRouterElement>
//             <ExamHistoryPage />
//           </PrivateRouterElement>
//         } />
//       </Route>
//       <Route path="/admin" element={
//         <PrivateRouterElement>
//           <AdComponent />
//         </PrivateRouterElement>
//       }>
//         <Route path="account" element={<AccountCrud />} />
//         <Route path="exam" element={<ExamCrud />}>
//           <Route path=":id" element={<QuizList />} />
//         </Route>
//         <Route path="role" element={<ManagerRole />} />
//         <Route path="exam-category" element={<ManagerExamCategory />} />
//         <Route path="exam-view/:id" element={<QuizList />} />
//       </Route>
//     </>
//   )
// );
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
          },
        ],
      },
      {
        path: "exam",
        element: (
          <PrivateRouterElement>
            <ExamPage />
          </PrivateRouterElement>
        ),
        children:[{
          path: "create",
          element:<ExamCreateChild/>
        }]
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
        <AdComponent />
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
        path: "role",
        element: <ManagerRole />,
      },
      {
        path: "exam-category",
        element: <ManagerExamCategory />,
      },
      {
        path: "exam-view/:id",
        element: <QuizList />,
      },
    ],
  },
]);


export default router;
