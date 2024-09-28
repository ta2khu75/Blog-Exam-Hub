import { useEffect, useState } from "react"
import ExamCategoryService from "../../service/ExamCategoryService"
import { useSearchParams } from "react-router-dom"
import { Button, Checkbox, Form, FormProps, Input, Radio, Space } from "antd"
import ExamService from "../../service/ExamService"
import ExamPageElement from "../element/exam/ExamPageElements"
import { ExamSearchRequest } from "../../model/request/search/ExamSearchRequest"
import { ExamLevel } from "../../model/ExamLevel"

const ExamListPage = () => {
    const [searchParams] = useSearchParams()
    const [keyword, setKeyword] = useState(searchParams.get("keyword") ?? undefined);
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([]);
    const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>();
    const [page, setPage] = useState(1);
    const [form] = Form.useForm<ExamSearchRequest>()
    useEffect(() => {
        fetchExamCategories();
        fetchExamPage();
    }, [])
    useEffect(() => {
        fetchExamPage();
    }, [page]);
    const fetchExamPage = () => {
        ExamService.search({ keyword, page, size: 10 }).then(response => {
            if (response.success) {
                setExamPage(response.data);
            }
        })
    }
    const fetchExamCategories = () => {
        ExamCategoryService.readAll().then(response => {
            if (response.success) {
                setExamCategories(response.data)
            }
        })
    }
    const onFinish: FormProps<ExamSearchRequest>['onFinish'] = (values) => {
        ExamService.search(values).then(response => {
            if (response.success) {
                setExamPage(response.data);
            }
        });
    };
    return (
        <section>
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
                                    <h2 className="accordion-header accordion-button text-dark bg-light" id="headingOne">
                                        Exam levels
                                    </h2>
                                    <div id="panelsStayOpen-collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne">
                                        <div className="accordion-body">
                                            <Form.Item<ExamSearchRequest> name={"exam_level"}>
                                                <Radio.Group>
                                                    {Object.keys(ExamLevel).map(level => <Radio key={`radio-${level}`} value={level}>{level}</Radio>)}
                                                </Radio.Group>
                                            </Form.Item>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header accordion-button text-dark bg-light" id="headingOne">
                                        Exam categories
                                    </h2>
                                    <div id="panelsStayOpen-collapseTwo" className="accordion-collapse collapse show" aria-labelledby="headingTwo">
                                        <div className="accordion-body">
                                            <div>
                                                <Form.Item<ExamSearchRequest> name="exam_category_ids">
                                                    <Checkbox.Group>
                                                        <Space direction="vertical" className='w-100'>
                                                            {examCategories?.map((category) => (
                                                                <Checkbox key={`category-exam-check-box-${category.id}`} value={category.id}>{category.name}</Checkbox>
                                                            )
                                                            )}
                                                        </Space>
                                                    </Checkbox.Group>
                                                </Form.Item>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header accordion-button text-dark bg-light" id="headingOne">
                                        Duration
                                    </h2>
                                    <div id="panelsStayOpen-collapseThree" className="accordion-collapse collapse show" aria-labelledby="headingThree">
                                        <div className="accordion-body">
                                            <div className="row mb-3">
                                                <div className="col-6">
                                                    <Form.Item<ExamSearchRequest> label="Min" name={"min_duration"} >
                                                        <Input type="number" />
                                                    </Form.Item>
                                                </div>
                                                <div className="col-6">
                                                    <Form.Item<ExamSearchRequest> label="Max" name={"max_duration"} >
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

                        <Form.Item<ExamSearchRequest> name="keyword" layout="horizontal" label="Search" >
                            <Space.Compact style={{ width: '100%' }}>
                                <Input size="large" />
                                <Button type="primary" htmlType="submit" size="large">Submit</Button>
                            </Space.Compact>
                        </Form.Item>
                        {/* <Form.Item<ExamSearchRequest> name="keyword" className="col-10 " layout="horizontal" label="Search" >
                                <Input placeholder="Keyword" size="large" />
                            </Form.Item>
                            <Form.Item className="col-2">
                                <Button htmlType="submit">Search</Button>
                            </Form.Item> */}
                        <header className="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                            <strong className="d-block py-2">{examPage?.total_elements} Items found </strong>
                            <div className="ms-auto">
                                <select className="form-select d-inline-block w-auto border pt-1">
                                    <option value={0}>Best match</option>
                                    <option value={1}>Recommended</option>
                                    <option value={2}>High rated</option>
                                    <option value={3}>Randomly</option>
                                </select>
                                <div className="btn-group shadow-0 border">
                                    <a href="#" className="btn btn-light" title="List view">
                                        <i className="fa fa-bars fa-lg" />
                                    </a>
                                    <a href="#" className="btn btn-light active" title="Grid view">
                                        <i className="fa fa-th fa-lg" />
                                    </a>
                                </div>
                            </div>
                        </header>
                        <div className="row">
                            <ExamPageElement examPage={examPage} page={page} setPage={setPage} />
                        </div>
                    </div>
                </Form>
            </div>
        </section>
    )
}

export default ExamListPage