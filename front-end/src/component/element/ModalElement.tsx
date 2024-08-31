import { Button, Modal } from "antd"

type Props = {
    title?: string;
    open: boolean;
    handleCancel: () => void;
    children?: JSX.Element
    width?: number
}
const ModalElement = ({ title, open, handleCancel, children, width }: Props) => {
    return (
        <Modal title={title} open={open}
            centered
            width={width}
            footer={[<Button key="cancel" onClick={handleCancel}>
                Cancel
            </Button>]}
            onCancel={() => handleCancel()}
        >
            <div className="m-5">
                {children}
            </div>
        </Modal>
    )
}

export default ModalElement