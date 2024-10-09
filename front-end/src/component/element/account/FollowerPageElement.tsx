import { Pagination } from "antd";
import FollowerItemElement from "./FollowerItemElement";
import { useEffect, useState } from "react";
import { FollowService } from "../../../service/FollowService";
import { useParams } from "react-router-dom";
import { useAppSelector } from "../../../redux/hooks";

const FollowerPageElement = () => {
    const { id } = useParams()
    const [page, setPage] = useState(1)
    const [followPage, setFollowPage] = useState<PageResponse<FollowResponse>>();
    const account = useAppSelector((state) => state.account.account)
    useEffect(() => {
        if (id)
            fetchFollowerPage(id)
    }, [page])
    const fetchFollowerPage = (id: string) => {
        FollowService.readPageFollower(id, page, 10).then((response) => {
            if (response.success) setFollowPage(response.data)
        })
    }
    return (
        <>
            {followPage?.content?.map(follow => {
                return <FollowerItemElement accountAuth={account} key={follow.follower.info.id} follow={follow} />
            })}
            <Pagination align="center" onChange={setPage} pageSize={10} defaultCurrent={page} total={followPage?.total_elements} />
        </>
    )
}

export default FollowerPageElement