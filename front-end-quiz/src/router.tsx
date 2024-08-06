import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import AdminComponent from "./component/AdminComponent";
import LoginPage from "./component/page/LoginPage";
import HomePage from "./component/page/HomePage";
import AccountCrud from "./component/page/admin/crud/AccountCrud";
import ExamCrud from "./component/page/admin/crud/ExamCrud";
import ExamView from "./component/page/admin/view/ExamView";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>,
    children:[{
      index: true,
      element: <HomePage/>
    },{
      path:"login",
      element: <LoginPage/>
    }]
  },{
    path: "/admin",
    element: <AdminComponent/>,
    children:[{
      path:"account",
      element: <AccountCrud/>
    },
    {
      path:"exam",
      element: <ExamCrud/>,
      children:[{
        path:":id",
        element: <ExamView/>
    }]
    },{
      path:"exam-view/:id",
      element: <ExamView/>
    }
  ]
  }
]);
export default router;