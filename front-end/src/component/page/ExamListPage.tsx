import { useEffect, useState } from "react"
import ExamCategoryService from "../../service/ExamCategoryService"
import { useSearchParams } from "react-router-dom"
import { Button, Checkbox, Form, FormProps, Input, Space } from "antd"
import ExamService from "../../service/ExamService"
import { ExamSearchRequest } from "../../model/request/search/ExamSearchRequest"
import { ExamLevel } from "../../model/ExamLevel"
import ExamPageElement from "../element/exam/ExamPageElements"
import StringUtil from "../../util/StringUtil"
type Props = {
    authorId?: string
}
const ExamListPage = ({ authorId }: Props) => {
    const [form] = Form.useForm<ExamSearchRequest>()
    const [searchParams, setSearchParams] = useSearchParams()
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([]);
    const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>();
    const [page, setPage] = useState(1);
    useEffect(() => {
        for (const [key, value] of searchParams.entries()) {
            form.setFieldValue(key, value);
        }
        fetchExamPage();
    }, [searchParams])
    useEffect(() => {
        fetchExamCategories();
    }, []);

    const fetchExamPage = () => {
        ExamService.search({ ...form.getFieldsValue(), authorId, page }).then(response => {
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
        const params = new URLSearchParams();
        for (const [key, value] of Object.entries(values)) {
            if (value && !StringUtil.checkEmpty(value)) params.append(key, value);
        }
        params.append("page", "1");
        setSearchParams(params);
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
                                            <Form.Item<ExamSearchRequest> name={"examLevels"}>
                                                <Checkbox.Group>
                                                    {Object.keys(ExamLevel).map(level => <Checkbox key={`checkbox-${level}`} value={level}>{level}</Checkbox>)}
                                                </Checkbox.Group>
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
                                                <Form.Item<ExamSearchRequest> name="examCategoryIds">
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
                                                    <Form.Item<ExamSearchRequest> label="Min" name={"minDuration"} >
                                                        <Input type="number" />
                                                    </Form.Item>
                                                </div>
                                                <div className="col-6">
                                                    <Form.Item<ExamSearchRequest> label="Max" name={"maxDuration"} >
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
                                <Form.Item<ExamSearchRequest> name="keyword" noStyle>
                                    <Input size="large" />
                                </Form.Item>
                                <Button type="primary" htmlType="submit" size="large">Submit</Button>
                            </Space.Compact>
                        </Form.Item>
                        <header className="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                            <strong className="d-block py-2">{examPage?.total_elements} Items found </strong>
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
                            <ExamPageElement page={page} setPage={setPage} examPage={examPage} />
                            {/* {examPage?.content?.map((exam) => {
                                return <ExamCartElement key={`exam-cart-${exam.id}`} exam={exam} />
                            })}
                            <Form.Item<ExamSearchRequest> name="page">
                                <Pagination align="center" pageSize={5} defaultCurrent={form.getFieldValue("page")} total={examPage?.total_elements} />
                            </Form.Item> */}
                        </div>
                    </div>
                </Form>
            </div>
        </section>
    )
}

export default ExamListPage