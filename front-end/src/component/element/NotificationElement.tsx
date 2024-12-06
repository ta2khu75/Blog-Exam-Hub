import { useEffect, useState } from "react"
import { useAppSelector } from "../../redux/hooks"
import { NotificationService } from "../../service/NotificationService"
import { List } from "antd"
import FunctionUtil from "../../util/FunctionUtil"
import { TargetType } from "../../types/TargetType"
import { toast } from "react-toastify"
import AvatarElement from "./AvatarElement"
import { Link } from "react-router-dom"

const NotificationElement = () => {
    const accountId = useAppSelector(state => state.account.account?.id)
    const [notificationPage, setNotificationPage] = useState<PageResponse<NotificationResponse>>()
    useEffect(() => {
        if (accountId)
            getNotificationPage(accountId)
    }, [accountId])
    const getNotificationPage = (accountId: string) => {
        NotificationService.readPageByAccountId(accountId).then((response) => {
            if (response.success) {
                setNotificationPage(response.data)
            } else {
                toast.error(response.message)
            }
        })
    }

    return (<div>
        <List style={{ width: "500px" }}
            itemLayout="horizontal"
            dataSource={
                notificationPage?.content?.map(notification => {
                    if (notification.target_type === TargetType.BLOG && FunctionUtil.isType<BlogResponse>(notification.target, "author")) {
                        return { title: notification.target.author.username, description: notification.target.title, link: "/blog-details/" + notification.target.info.id }
                    } else if (notification.target_type === TargetType.EXAM && FunctionUtil.isType<ExamResponse>(notification.target, "author")) {
                        return { title: notification.target.author.username, description: notification.target.title, link: "/exam-about/" + notification.target.info.id }
                    }
                    return { title: "default", notification: notification.target }
                })
            }
            renderItem={(item) => (
                <List.Item>
                    <List.Item.Meta
                        avatar={<AvatarElement username={item.title} size={50} />}
                        title={<Link to={item.link ?? ""}>{item?.title}</Link>}
                        description={item.description}
                    />
                </List.Item>
            )}
        />
    </div>
    )
}

export default NotificationElement