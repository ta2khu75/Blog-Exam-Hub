import { Link } from "react-router-dom"
import AvatarElement from "../AvatarElement"
import StringUtil from "../../../util/StringUtil"

type Props = {
    comment: CommentResponse
}
const CommentItemElement = ({ comment }: Props) => {
    return (
        <div className="border p-3 rounded mb-3">
            <div className="d-flex align-items-center">
                <Link className="me-2" to={`/author/${comment.author?.id}`}>
                    <div className="d-flex align-items-center">
                        <AvatarElement username={comment?.author?.username ?? " U"} size={50} />
                        <strong>{comment?.author?.username}</strong>
                    </div>
                </Link>
                <span className="text-muted">{comment?.created_at}</span>
            </div>
            <div dangerouslySetInnerHTML={{ __html: StringUtil.replaceMarkdownWithImgTag(comment.content ?? "") }}></div>
        </div>
    )
}

export default CommentItemElement