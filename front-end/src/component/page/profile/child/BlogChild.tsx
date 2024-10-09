import { useParams } from "react-router-dom"
import { useAppSelector } from "../../../../redux/hooks"
import ManagerBlogChild from "./ManagerBlogChild"
import BlogListPage from "../../BlogListPage"

const BlogChild = () => {
    const { id } = useParams()
    const accountAuth = useAppSelector(state => state.account.account)
    if (accountAuth?.id === id)
        return <ManagerBlogChild />
    return <BlogListPage />
}

export default BlogChild