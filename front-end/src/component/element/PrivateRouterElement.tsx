import React from "react";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { Navigate, useLocation } from "react-router-dom";
import { setRouterRedirect } from "../../redux/slice/routerRedirect";
type Props = {
  children: React.ReactNode;
};
const PrivateRouterElement = ({ children }: Props) => {
  const { pathname } = useLocation()
  const dispatch = useAppDispatch()
  const authenticated = useAppSelector((state) => state.account.authenticated);
  if (!authenticated) {
    dispatch(setRouterRedirect(pathname))
    return <Navigate to={"/login"} />;
  }
  return <>{children}</>;
};

export default PrivateRouterElement;
