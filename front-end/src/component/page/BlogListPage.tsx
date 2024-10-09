import { Button, Checkbox, Form, FormProps, Input, Space } from "antd";
import { useEffect, useState } from "react";
import { useParams, useSearchParams } from "react-router-dom";
import { BlogService } from "../../service/BlogService";
import BlogPageElement from "../element/blog/BlogPageElement";
import { BlogTagService } from "../../service/BlogTagService";
import StringUtil from "../../util/StringUtil";
const BlogListPage = () => {
    const { authorId } = useParams()
    const [form] = Form.useForm<BlogSearchRequest>()
    const [searchParams, setSearchParams] = useSearchParams()
    const [blogPage, setBlogPage] = useState<PageResponse<BlogResponse>>();
    const [page, setPage] = useState(1);
    const [blogTags, setBlogTags] = useState<BlogTagResponse[]>([]);
    useEffect(() => {
        for (const [key, value] of searchParams.entries()) {
            form.setFieldValue(key, value);
        }
        fetchBlogPage();
    }, [searchParams])
    useEffect(() => {
        fetchBlogTags();
    }, []);
    const fetchBlogTags = () => {
        BlogTagService.readAll().then(response => {
            if (response.success) {
                setBlogTags(response.data)
            }
        })
    }
    const fetchBlogPage = () => {
        BlogService.search({ ...form.getFieldsValue(), authorId }).then(response => {
            if (response.success) {
                setBlogPage(response.data);
            }
        })
    }
    const onFinish: FormProps<BlogSearchRequest>['onFinish'] = (values) => {
        const params = new URLSearchParams();
        for (const [key, value] of Object.entries(values)) {
            if (value && !StringUtil.checkEmpty(value)) params.append(key, value);
        }
        setSearchParams(params);
    };
    return (
        <section >
            <div className="container">
                <Form layout="vertical" form={form} onFinish={onFinish} className="row">

                    {/* sidebar */}
                    <div className="col-lg-3">
                        {/* Toggle button */}
                        <button className="btn btn-outline-secondary mb-3 w-100 d-lg-none" type="button" data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span>Show filter</span>
                        </button>
                        {/* Collapsible wrapper */}
                        <div className="collapse card d-lg-block mb-5" id="navbarSupportedContent">
                            <div className="accordion" id="accordionPanelsStayOpenExample">
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingOne">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                            Related items
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne">
                                        <div className="accordion-body">
                                            <Form.Item<BlogSearchRequest> name="blogTagNames">
                                                <Checkbox.Group>
                                                    <Space direction="vertical" className='w-100'>
                                                        {blogTags?.map((blogTag) => (
                                                            <Checkbox key={`blog-tag-name-check-box-${blogTag.name}`} value={blogTag.name}>{blogTag.name}</Checkbox>
                                                        )
                                                        )}
                                                    </Space>
                                                </Checkbox.Group>
                                            </Form.Item>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingThree">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                                            View
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseThree" className="accordion-collapse collapse show" aria-labelledby="headingThree">
                                        <div className="accordion-body">
                                            <div className="row mb-3">
                                                <div className="col-6">
                                                    <Form.Item<BlogSearchRequest> label="Min" name={"minView"} >
                                                        <Input type="number" />
                                                    </Form.Item>
                                                </div>
                                                <div className="col-6">
                                                    <Form.Item<BlogSearchRequest> label="Max" name={"maxView"} >
                                                        <Input type="number" />
                                                    </Form.Item>
                                                </div>
                                            </div>
                                            <Form.Item>
                                                <Button htmlType="submit" className="w-100"  >Apply</Button>
                                            </Form.Item>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* sidebar */}
                    {/* content */}
                    <div className="col-lg-9">
                        <Form.Item<BlogSearchRequest> name="keyword" layout="horizontal" label="Search" >
                            <Space.Compact style={{ width: '100%' }}>
                                <Form.Item name="keyword" noStyle>
                                    <Input size="large" />
                                </Form.Item>
                                <Button type="primary" htmlType="submit" size="large">Submit</Button>
                            </Space.Compact>
                        </Form.Item>
                        <header className="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                            <strong className="d-block py-2">{blogPage?.total_elements} Items found </strong>
                            <div className="ms-auto">
                                <select className="form-select d-inline-block w-auto border pt-1">
                                    <option value={0}>Best match</option>
                                    <option value={1}>Recommended</option>
                                    <option value={2}>High rated</option>
                                    <option value={3}>Randomly</option>
                                </select>
                            </div>
                        </header>
                        <div className="row">
                            <BlogPageElement page={page} setPage={setPage} blogPage={blogPage} />
                            {/* {blogPage?.content?.map(blog => {
                                return <BlogItemElement key={blog.id} blog={blog} />
                            })}
                            <Form.Item<BlogSearchRequest> name="page">
                                <Pagination align="center" pageSize={10} defaultCurrent={1} total={blogPage?.total_elements} />
                            </Form.Item> */}
                        </div>
                    </div>
                </Form>
            </div>
        </section>

    )
}

export default BlogListPage