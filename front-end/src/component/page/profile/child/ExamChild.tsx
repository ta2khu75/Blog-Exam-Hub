import { useParams } from "react-router-dom"
import { useAppSelector } from "../../../../redux/hooks"
import ManagerExamChild from "./ManagerExamChild"
import ExamListPage from "../../ExamListPage"

const ExamChild = () => {
    const { id } = useParams()
    const accountAuth = useAppSelector(state => state.account.account)
    if (accountAuth?.id === id)
        return <ManagerExamChild />
    return <ExamListPage />
}

export default ExamChild