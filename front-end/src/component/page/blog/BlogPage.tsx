import { Button, Form, FormProps, Input, Radio, Space } from 'antd';
import { useEffect, useState } from 'react'
import ReactQuill from 'react-quill'
import { PlusOutlined, MinusCircleOutlined } from '@ant-design/icons';
import 'react-quill/dist/quill.snow.css';
import { BlogRequest } from '../../../model/request/BlogRequest';
import { AccessModifier } from '../../../model/AccessModifier';
import { useParams } from 'react-router-dom';
const BlogPage = () => {
    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
        ['blockquote', 'code-block'],
        ['link', 'image', 'video', 'formula'],

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
    const modules = { toolbar: toolbarOptions }
    const [form] = Form.useForm<BlogRequest>();
    const [errorImage, setErrorImage] = useState(false);
    const [image, setImage] = useState<File>()
    const [value, setValue] = useState('');
    const onFinish: FormProps<BlogRequest>["onFinish"] = (values) => {
        // if (!values.id) {
        //   QuizService.create(values, file).then((data) => {
        //     if (data.success) {
        //       toast.success("Successfully to create");
        //       resetFields();
        //     } else {
        //       toast.error(data.message_error);
        //     }
        //   });
        // } else {
        //   QuizService.update(values.id, values, file).then((data) => {
        //     if (data.success) {
        //       toast.success("successfully");
        //       refresh();
        //       resetFields();
        //     } else {
        //       toast.error(data.message_error);
        //     }
        //   });
        // }
    };
    useEffect(() => {
        handleResetClick()
    }, [])
    const handleUploadChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setImage(e.target.files[0])
        }
    }
    const blogRequest: BlogRequest = { title: "", content: "", access_modifier: AccessModifier.PRIVATE, blog_tags: [""] }
    const handleResetClick = () => {
        form.setFieldsValue({ title: "", content: "", access_modifier: AccessModifier.PRIVATE, blog_tags: [""] })
        // setErrorAnswer(false);
        // setCorrects([]);
        // setQuizType(QuizType.SINGLE_CHOICE);
    }
    return <div className='container'>
        <Form
            onFinish={onFinish}
            form={form}
            layout='vertical'
        >
            <Form.Item<BlogRequest> label="Title" name={"title"} rules={[
                { required: true, message: "please input title" }
            ]} >
                <Input />
            </Form.Item>
            <div className='d-flex'>
                <Form.Item label="Blog tags">
                    <div className='d-flex'>
                        <Form.List name="blog_tags" >
                            {(fields, { add, remove }) => (
                                <>
                                    {fields.map(({ key, name, ...restField }) => (
                                        <Space key={key} className='d-flex' align="baseline">
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
                                    <Form.Item hidden={form.getFieldValue("blog_tags").length===5}>
                                        <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                            Add field
                                        </Button>
                                    </Form.Item>
                                </>
                            )}
                        </Form.List>
                    </div>
                </Form.Item>
                {errorImage && <p className="text-danger">Please upload content</p>}
                <Form.Item<BlogRequest> label="Access Modifier" name={"access_modifier"} rules={[
                    { required: true, message: "please choose access_modifier" }]} >
                    <Radio.Group>
                        {Object.keys(AccessModifier).map(access => <Radio key={`radio-${access}`} value={access}>{access}</Radio>)}
                    </Radio.Group>
                </Form.Item>
                <Form.Item<BlogRequest> label="Image" rules={[
                    { required: true, message: "please input access_modifier" }
                ]} >
                    <input onChange={(e) => handleUploadChange(e)} type="file" />
                </Form.Item>
            </div>
            {/* <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
                <Button onClick={handleResetClick} className="ml-2">
                    Reset
                </Button>
            </Form.Item> */}

            <ReactQuill modules={modules} className='vh-100' theme="snow" value={value} onChange={setValue} />
        </Form >
    </div >
}

export default BlogPage