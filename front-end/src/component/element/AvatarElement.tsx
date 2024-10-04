type Props = {
    username: string,
    size?: number,
}
const AvatarElement = ({ username, size = 100 }: Props) => {
    return (
        <div className="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style={{ width: size, backgroundColor: "#4F98A4", height: size}} >
            <h3 className="text-light my-0">{username?.[0]?.toUpperCase()}</h3>
        </div>

    )
}

export default AvatarElement