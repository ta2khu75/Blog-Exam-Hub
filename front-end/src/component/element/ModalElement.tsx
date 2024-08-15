import { Modal } from "antd"

type Props = {
    title?: string;
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    children?: JSX.Element
    width?:number
}
const ModalElement = ({ title, open, setOpen,  children, width }: Props) => {
    return (
        <Modal title={title} open={open}
        centered
        width={width}
            footer={[]}
        onCancel={()=>setOpen(false)}
        >
            <div className="m-5">
                {children}
            </div>
        </Modal>
    )
}

export default ModalElement