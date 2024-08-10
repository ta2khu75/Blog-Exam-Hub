import { Modal } from "antd"

type Props = {
    title?: string;
    open: boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;
    // handleOkClick?: () => void;
    // handleCancelClick?: () => void;
    children?: JSX.Element;
    width?:number
}
const ModalElement = ({ title, open, setOpen,  children, width }: Props) => {
    // const handleCancel = () => {
    //     if (handleCancelClick) {
    //         handleCancelClick();
    //     }
    //     setOpen(false);
    // }
    // const handleOk = () => {
    //     if (handleOkClick) handleOkClick()
    // }
    return (
        <Modal title={title} open={open}
        centered
        width={width}
            footer={[]}
        //  onOk={handleOk} 
        onCancel={()=>setOpen(false)}
        >
            <div className="m-5">
                {children}
            </div>
        </Modal>
    )
}

export default ModalElement