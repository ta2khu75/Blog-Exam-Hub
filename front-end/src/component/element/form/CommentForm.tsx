import { Button, Form, FormProps } from "antd"
import ModalElement from "../ModalElement";
import BlogUploadImage from "../../page/blog/BlogUploadImage";
import { useState } from "react";
import ReactQuill from "react-quill";
import { CommentService } from "../../../service/CommentService";
type Props = {
    blogId: string,
    setCommentPage: React.Dispatch<React.SetStateAction<PageResponse<CommentResponse> | undefined>>
}
const CommentForm = ({ blogId, setCommentPage }: Props) => {
    const [form] = Form.useForm<CommentRequest>()
    const [content, setContent] = useState("")
    const [openImageContent, setOpenImageContent] = useState(false)
    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
        ['blockquote', 'code-block'],
        ['link', 'formula'],

        [{ 'header': 1 }, { 'header': 2 }],               // custom button values
        [{ 'list': 'ordered' }, { 'list': 'bullet' }, { 'list': 'check' }],
        [{ 'script': 'sub' }, { 'script': 'super' }],      // superscript/subscript
        [{ 'indent': '-1' }, { 'indent': '+1' }],          // outdent/indent
        [{ 'direction': 'rtl' }],                         // text direction

        [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

        [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
        [{ 'font': [] }],
        [{ 'align': [] }],
        ['clean']                                         // remove formatting button
    ];
    const modules = { toolbar: toolbarOptions }
    const handleCancelUploadImageClick = () => {
        setOpenImageContent(false)
    }
    const onFinish: FormProps<CommentRequest>['onFinish'] = () => {
        CommentService.create({ blog_id: blogId, content }).then(response => {
            if (response.success) {
                setCommentPage(commentPage => {
                    return { ...commentPage, content: [response.data, ...commentPage?.content ?? []], total_elements: commentPage?.total_elements ?? 0 + 1, total_pages: commentPage?.total_pages ?? 1 }
                })
                handleReset();
            }
        })
    };
    const handleReset = () => {
        setContent("")
    }
    return (
        <>
            <Form form={form} onFinish={onFinish} layout="vertical">
                <Form.Item label="Content">
                    <ReactQuill modules={modules} theme="snow" value={content} onChange={setContent} />
                </Form.Item>
                <Form.Item >
                    <Button onClick={() => setOpenImageContent(true)}>
                        Add Image
                    </Button>
                </Form.Item>
                <Button type="primary" htmlType="submit">Submit comment</Button>
            </Form>
            <ModalElement width={1500} open={openImageContent} handleCancel={handleCancelUploadImageClick}>
                <BlogUploadImage setOpen={setOpenImageContent} setContent={setContent} />
            </ModalElement>
        </>
    )
}

export default CommentForm