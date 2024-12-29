import { Navigate, useLocation } from "react-router-dom";
import { useAppSelector } from "../../redux/hooks"
type Props = {
    children: React.ReactNode;
}
const RootRouterElement = ({ children }: Props) => {
    const { pathname } = useLocation()
    const role = useAppSelector(state => state.account.account?.role)
    const routerRedirect = useAppSelector(state => state.routerRedirect.value);
    console.log("pathname", pathname);
    console.log("routerRedirect", routerRedirect);


    if (role === "ROOT") {
        return <>{children}</>;
    }
    return (
        <Navigate to={pathname == routerRedirect ? "/login" : routerRedirect} />
    )
}

export default RootRouterElement