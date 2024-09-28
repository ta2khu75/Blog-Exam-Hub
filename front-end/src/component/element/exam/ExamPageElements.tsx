import { Pagination } from "antd"
import ExamCartElement from "./ExamCartElement"

type Props = {
    examPage?: PageResponse<ExamResponse>
    setPage: (page: number) => void
    page: number
}
const ExamPageElement = ({ examPage, setPage, page }: Props) => {
    return (
        <>
            {examPage?.content?.map(exam => {
                return <ExamCartElement key={exam.id} exam={exam} />
            })}
            <Pagination align="center" onChange={setPage} pageSize={5} defaultCurrent={page} total={examPage?.total_elements} />
        </>
    )
}

export default ExamPageElement;