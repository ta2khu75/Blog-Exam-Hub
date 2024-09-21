import { Button, Form, FormProps, Input, Radio, Space } from 'antd';
import { useEffect, useState } from 'react'
import ReactQuill from 'react-quill'
import { PlusOutlined, MinusCircleOutlined } from '@ant-design/icons';
import 'react-quill/dist/quill.snow.css';
import { AccessModifier } from '../../../model/AccessModifier';
import { useNavigate, useParams } from 'react-router-dom';
import { BlogService } from '../../../service/BlogService';
import { toast } from 'react-toastify';
import BlogUploadImage from './BlogUploadImage';
import ModalElement from '../../element/ModalElement';
const BlogPage = () => {
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
    const { blogId } = useParams()
    const navigate = useNavigate()
    const modules = { toolbar: toolbarOptions }
    const [openImageContent, setOpenImageContent] = useState(false)
    const [form] = Form.useForm<BlogRequest>();
    const [errorContent, setErrorContent] = useState(false);
    const [image, setImage] = useState<File>()
    const [content, setContent] = useState("");
    const onFinish: FormProps<BlogRequest>["onFinish"] = (values) => {
        if (!(/^\s*$/.test(content))) {
            if (blogId) {
                BlogService.update(blogId, { ...values, content }, image).then((data) => {
                    if (data.success) {
                        toast.success("Successfully");
                        navigate(`/profile/manager-blog`)
                    } else {
                        toast.error(data.message_error);
                    }
                });
            } else {
                BlogService.create({ ...values, content }, image).then((data) => {
                    if (data.success) {
                        toast.success("Successfully to create");
                        navigate(`/profile/manager-blog`)
                    } else {
                        toast.error(data.message_error);
                    }
                });
            }
        } else {
            setErrorContent(true);
        }
    };
    useEffect(() => {
        if (blogId) fetchBlog(blogId);
        handleResetClick()
    }, [blogId])
    const fetchBlog = (blogId: string) => {
        BlogService.readDetails(blogId).then((response) => {
            if (response.success) {
                form.setFieldsValue({ title: response.data.title, access_modifier: response.data.access_modifier, blog_tags: response.data.blog_tags })
                setContent(response.data.content)
            }
        })
    }
    const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setImage(e.target.files[0])
        }
    }
    const handleResetClick = () => {
        form.setFieldsValue({ title: "", content: "", access_modifier: AccessModifier.PRIVATE, blog_tags: [""] })
        setErrorContent(false);
    }
    const handleCancelUploadImageClick = () => {
        setOpenImageContent(false)
    }
    const handleShowUploadImageClick = () => {
        setOpenImageContent(true)
    }
    return <div className='container'>
        <Form
            onFinish={onFinish}
            form={form}
            layout='vertical'
        >
            <div className='d-flex align-items-center'>
                <Form.Item<BlogRequest> label="Title" className='w-100' name={"title"} rules={[
                    { required: true, message: "please input title" }
                ]} >
                    <Input />
                </Form.Item>
                <Form.Item className='mt-4' >
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </div>
            <div className='d-flex'>
                <Form.Item label="Blog tags">
                    <div className='d-flex'>
                        <Form.List name="blog_tags" >
                            {(fields, { add, remove }) => (
                                <>
                                    {fields.map(({ key, name, ...restField }) => (
                                        <Space key={key} className='d-flex me-4' align="baseline">
                                            <Form.Item
                                                {...restField}
                                                name={[name]}
                                                rules={[{ required: true, message: 'Missing blog tag' }]}
                                            >
                                                <Input placeholder="" />
                                            </Form.Item>
                                            <MinusCircleOutlined onClick={() => remove(name)} />
                                        </Space>
                                    ))}
                                    <Form.Item hidden={form.getFieldValue("blog_tags")?.length === 5} className='ms-5' >
                                        <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                            Add field
                                        </Button>
                                    </Form.Item>
                                </>
                            )}
                        </Form.List>
                    </div>
                </Form.Item>
            </div>
            <div className='d-flex'>
                <Form.Item<BlogRequest> label="Access Modifier" name={"access_modifier"} rules={[
                    { required: true, message: "please choose access_modifier" }]} >
                    <Radio.Group>
                        {Object.keys(AccessModifier).map(access => <Radio key={`radio-${access}`} value={access}>{access}</Radio>)}
                    </Radio.Group>
                </Form.Item>
                <Form.Item<BlogRequest> label="Image blog" rules={[
                    { required: true, message: "please input access_modifier" }
                ]} >
                    <input onChange={(e) => handleUploadChange(e)} type="file" />
                </Form.Item>
            </div>
            <ModalElement width={1500} open={openImageContent} handleCancel={handleCancelUploadImageClick}>
                <BlogUploadImage setOpen={setOpenImageContent} setContent={setContent} />
            </ModalElement>
            <Form.Item>
                <Button onClick={() => handleShowUploadImageClick()}>Upload image content</Button>
            </Form.Item>
            {errorContent && <p className="text-danger">Please input Content</p>}
            <ReactQuill modules={modules} className='vh-100' theme="snow" value={content} onChange={setContent} />
        </Form >
    </div >
}

export default BlogPage