import React from "react";
import { useAppSelector } from "../../redux/hooks";
import { Navigate } from "react-router-dom";
type Props = {
  children: React.ReactNode;
};
const PrivateRouterElement = ({ children }: Props) => {
  const authenticated = useAppSelector((state) => state.account.authenticated);
  if (!authenticated) {
    return <Navigate to={"/login"} />;
  }
  return <>{children}</>;
};

export default PrivateRouterElement;
