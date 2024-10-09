import { Link } from "react-router-dom"
import AvatarElement from "../AvatarElement"
import { FollowService } from "../../../service/FollowService"
import { toast } from "react-toastify"
import { useState } from "react"
type Props = {
    follow: FollowResponse,
    accountAuth?: AccountAuthResponse
}
const FollowerItemElement = ({ follow, accountAuth }: Props) => {
    const [followed, setFollowed] = useState(false);
    const handleFollowClick = (authorId: string) => {
        if (followed)
            FollowService.unFollow(authorId).then((response) => {
                if (response.success) {
                    setFollowed(false)
                    toast.success("UnFollowing successfully")
                }
            })
        else
            FollowService.follow(authorId).then((response) => {
                console.log(response);
                if (response.success) {
                    setFollowed(true)
                    toast.success("Following successfully")
                } else if (response.status_code === 400) setFollowed(true)
            });
    }
    return (
        <div className="d-flex justify-content-between">
            <div className="d-flex align-items-center">
                <AvatarElement size={50} username={follow.follower.username} />
                <Link className="me-2" to={`/author/${follow.follower.info.id}`}>
                    <strong>{follow.follower.username}</strong>
                </Link>
            </div>
            {accountAuth?.id !== follow.follower.info.id &&
                <button className="btn btn-primary" onClick={() => handleFollowClick(follow.follower.info.id)}>{followed ? "Un Follow" : "Follow"}</button>
            }
        </div>
    )
}

export default FollowerItemElement