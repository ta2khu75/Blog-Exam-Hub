import { Navigate, useLocation } from "react-router-dom";
import { useAppSelector } from "../../redux/hooks"
import { useEffect, useState } from "react";
import AuthService from "../../service/AuthService";
type Props = {
    children: React.ReactNode;
}
const RootRouterElement = ({ children }: Props) => {
    const { pathname } = useLocation()
    // const role = useAppSelector(state => state.account.account?.role)
    const routerRedirect = useAppSelector(state => state.routerRedirect.value);
    console.log("pathname", pathname);
    console.log("routerRedirect", routerRedirect);
    const [isAdmin, setIsAdmin] = useState(false);
    useEffect(() => {
        checkAdmin()
    }, [])
    const checkAdmin = () => {
        AuthService.checkAdmin().then(response => {
            if (response.success) {
                setIsAdmin(response.data.result)
            }
        }).catch(error => {
            console.log(error);
        })
    }
    if (isAdmin) {
        return <>{children}</>;
    }
    return (
        <Navigate to={pathname == routerRedirect ? "/login" : routerRedirect} />
    )
}

export default RootRouterElement