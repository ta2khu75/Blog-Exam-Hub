import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import AccountService from "../../service/AccountService";
import { BlogService } from "../../service/BlogService";
import ExamService from "../../service/ExamService";

const AuthorPage = () => {
    const { authorId } = useParams();
    const [blogPage, setBlogPage] = useState<PageResponse<BlogResponse>>()
    const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>()
    useEffect(() => {
        if (authorId) {
            fetchAuthor(authorId);
            fetchBlogPageAuthorId(authorId);
            fetchExamPageAuthorId(authorId);
        }
    }, [authorId])
    const fetchAuthor = (authorId: string) => {
        AccountService.readById(authorId)
    }
    const fetchBlogPageAuthorId = (authorId: string) => {
        BlogService.readPageByAuthorId(authorId, 1, 10).then(response => {
            if (response.success) setBlogPage(response.data);
        })
    }
    const fetchExamPageAuthorId = (authorId: string) => {
        ExamService.readPageByAuthorId(authorId, 1, 10).then(response => {
            if (response.success) setExamPage(response.data);
        })
    }
    return (
        <div></div>
    )
}

export default AuthorPage;