import { Button, Form, FormProps, Input, Radio, Select, Space } from 'antd';
import { useEffect, useMemo, useState } from 'react'
import ReactQuill from 'react-quill'
import { PlusOutlined, MinusCircleOutlined } from '@ant-design/icons';
import 'react-quill/dist/quill.snow.css';
import { AccessModifier } from '../../../types/AccessModifier';
import { useNavigate, useParams } from 'react-router-dom';
import { BlogService } from '../../../service/BlogService';
import { toast } from 'react-toastify';
import BlogUploadImage from './BlogUploadImage';
import ModalElement from '../../element/ModalElement';
import useDebounce from '../../../hook/useDebounce';
import ExamService from '../../../service/ExamService';
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
    const [image, setImage] = useState<File>()
    // const [content, setContent] = useState("");
    const [keyword, setKeyword] = useState("")
    const search = useDebounce(keyword);
    const [examList, setExamList] = useState<ExamResponse[]>([])
    // const [examSelected, setExamSelected] = useState<{ label: string, value: string }[]>([])
    const onFinish: FormProps<BlogRequest>["onFinish"] = (values) => {
        console.log(values);
        if (blogId) {
            BlogService.update(blogId, values, image).then((data) => {
                if (data.success) {
                    toast.success("Successfully");
                    navigate(`/profile`)
                } else {
                    toast.error(data.message_error);
                }
            });
        } else {
            BlogService.create(values, image).then((data) => {
                if (data.success) {
                    toast.success("Successfully to create");
                    navigate(`/profile`)
                } else {
                    toast.error(data.message_error);
                }
            });
        }
    };
    useEffect(() => {
        if (blogId) fetchBlog(blogId);
        handleResetClick()
    }, [blogId])
    useEffect(() => {
        if (examList.length > 0) fetchMyExam()
    }, [keyword])
    const fetchBlog = (blogId: string) => {
        BlogService.readDetails(blogId).then((response) => {
            if (response.success) {
                form.setFieldsValue({ ...response.data, exam_ids: response.data.exams.map(exam => exam.info.id) })
                if (response.data.exams.length > 0) {
                    setExamList(response.data.exams)
                }
            }
        })
    }
    const fetchMyExam = () => {
        ExamService.mySearchBlogNull(search).then(response => {
            if (response.success && response.data.content) {
                setExamList(response.data.content)
            }
        })
    }
    const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setImage(e.target.files[0])
        }
    }
    const handleResetClick = () => {
        form.resetFields() //setFieldsValue({ title: "", content: "", access_modifier: AccessModifier.PRIVATE, blog_tags: [""] })
    }
    const handleCancelUploadImageClick = () => {
        setOpenImageContent(false)
    }
    const handleShowUploadImageClick = () => {
        setOpenImageContent(true)
    }
    const handleExamClick = () => {
        if (examList.length == 0) fetchMyExam()
    }
    const optionExam = useMemo(() => {
        return examList.map((exam) => ({ label: exam.title, value: exam.info.id }))
    }, [examList])
    const handleAddImageToContent = (image: string) => {
        form.setFieldValue("content", `${form.getFieldValue("content")}${image}`)
    }
    return <div className='container'>
        <h2>Create Blog</h2>
        <Form
            onFinish={onFinish}
            form={form}
            layout='vertical'
        >
            <div className='d-flex align-items-center'>
                <Space.Compact style={{ width: '100%' }}>
                    <Form.Item<BlogRequest> label="Title" className='w-100' layout='horizontal' name={"title"}>
                        <Input />
                    </Form.Item>
                    <Button type="primary" htmlType='submit'>Submit</Button>
                </Space.Compact>
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
                <Form.Item<BlogRequest> label="Exams" name={"exam_ids"}>
                    <Select
                        mode="multiple"
                        onClick={() => handleExamClick()}
                        allowClear
                        style={{ width: '300px', }}
                        placeholder="Please select"
                        onSearch={(value) => setKeyword(value)}
                        options={optionExam}
                    />
                </Form.Item>
                <Form.Item<BlogRequest> label="Image blog" rules={[
                    { required: true, message: "please input access_modifier" }
                ]} >
                    <input onChange={(e) => handleUploadChange(e)} type="file" />
                </Form.Item>
            </div>
            <ModalElement width={1500} open={openImageContent} handleCancel={handleCancelUploadImageClick}>
                <BlogUploadImage setOpen={setOpenImageContent} handleAddImage={handleAddImageToContent} />
            </ModalElement>
            <Form.Item>
                <Button onClick={() => handleShowUploadImageClick()}>Upload image content</Button>
            </Form.Item>
            <Form.Item<BlogRequest> name="content" label="Content" rules={[{ required: true }]}>
                <ReactQuill modules={modules} className='vh-100' theme="snow" />
            </Form.Item>
        </Form >
    </div >
}

export default BlogPage