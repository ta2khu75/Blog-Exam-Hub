import { useEffect, useState } from "react"
import ExamService from "../../service/ExamService"
import ExamCartElementNew from "../element/exam/ExamCartElement"
import { BlogService } from "../../service/BlogService"
import BlogItemElement from "../element/blog/BlogItemElement"
import IntroductionElement from "../element/IntoductionElement"
import { Pagination } from "antd"
import { Link } from "react-router-dom"

const HomePage = () => {
  const [examPage, setExamPage] = useState<PageResponse<ExamResponse>>()
  const [blogPage, setBlogPage] = useState<PageResponse<BlogResponse>>()
  const [pageBlog, setPageBlog] = useState(1)
  const [pageExam, setPageExam] = useState(1)
  useEffect(() => {
    fetchReadBlogPage();
    fetchReadExamPage();
  }, [])
  useEffect(() => {
    fetchReadExamPage()
  }, [pageExam])
  useEffect(() => {
    fetchReadBlogPage()
  }, [pageBlog])
  const fetchReadExamPage = () => {
    ExamService.search({ page: pageExam, size: 4 }).then((data) => {
      if (data.success) setExamPage(data.data);
    })
  }
  const fetchReadBlogPage = () => {
    BlogService.search({ page: pageBlog, size: 10 }).then((response) => {
      if (response.success) setBlogPage(response.data);
    })
  }
  return (
    <>
      <IntroductionElement
        into="Khám phá, học hỏi, và thử thách bản thân!"
        content={`Chào mừng bạn đến với BlogTestHub! Tại đây, bạn có thể viết và chia sẻ blog, khám phá nội dung phong phú từ cộng đồng, cũng như tạo và tham gia các bài thi thú vị. <a href="#to_get_started">Tham gia ngay hôm nay</a> để kết nối, học hỏi và trải nghiệm kiến thức mới!`}
      />

      <section className="explore-section section-padding" id="section_2">
        <div className="container">
          <div className="col-12 text-center">
            <h2 className="mb-4">Browse Blog</h2>
          </div>
        </div>
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="tab-content" id="myTabContent">
                <div className="tab-pane fade show active" id="design-tab-pane" role="tabpanel" aria-labelledby="design-tab" tabIndex={0}>
                  <div className="row">
                    {blogPage?.content?.map(blog => (
                      <BlogItemElement blog={blog} key={`blog-item-${blog.info.id}`} />
                    ))}
                    <Pagination align="center" onChange={setPageBlog} pageSize={5} defaultCurrent={1} total={blogPage?.total_elements} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section className="explore-section" id="section_2">
        <div className="container">
          <div className="col-12 text-center">
            <h2 className="mb-4">Browse Exam</h2>
          </div>
        </div>
        <div className="container mb-5">
          <div className="row">
            <div className="col-12">
              <div className="tab-content" id="myTabContent">
                <div className="tab-pane fade show active" id="design-tab-pane" role="tabpanel" aria-labelledby="design-tab" tabIndex={0}>
                  <div className="row">
                    {examPage?.content?.map(examResponse => (
                      <ExamCartElementNew key={`exam-cart-${examResponse.info.id}`} exam={examResponse} />
                    ))}
                    <Pagination align="center" onChange={setPageExam} pageSize={4} defaultCurrent={1} total={examPage?.total_elements} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <section className="timeline-section section-padding" id="to_get_started">
          <div className="section-overlay" />
          <div className="container">
            <div className="row">
              <div className="col-12 text-center">
                <h2 className="text-white mb-4">To get started</h2>
              </div>
              <div className="col-lg-10 col-12 mx-auto">
                <div className="timeline-container">
                  <ul className="vertical-scrollable-timeline" id="vertical-scrollable-timeline">
                    <div className="list-progress">
                      <div className="inner" />
                    </div>
                    <li>
                      <h4 className="text-white">Đăng ký tài khoản</h4>
                      <p className="text-white">
                        Nhấp vào nút "<Link to="/register">Đăng ký</Link>" và tạo tài khoản miễn phí chỉ với vài bước đơn giản.
                      </p>
                      <div className="icon-holder">
                        <i className="bi-search" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Hoàn thiện hồ sơ của bạn</h4>
                      <p className="text-white">
                        <Link to={"/profile/change-info"}>Cập nhật thông tin cá nhân</Link> để mọi người có thể dễ dàng nhận diện bạn trong cộng đồng.
                      </p>
                      <div className="icon-holder">
                        <i className="bi-search" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Bắt đầu viết blog</h4>
                      <p className="text-white">
                        Nhấp vào "<Link to={"/manager-blog/create"}>Viết blog</Link>", sử dụng trình chỉnh sửa của chúng tôi để sáng tạo nội dung và chia sẻ ý tưởng của bạn với mọi người.
                      </p>
                      <div className="icon-holder">
                        <i className="bi-search" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Khám phá blog</h4>
                      <p className="text-white">
                        <Link to={"/blog"}>Khám phá nhiều bài blog</Link> được viết bởi người dùng khác. Đọc, bình luận và tương tác với nội dung mà bạn quan tâm.
                      </p>
                      <div className="icon-holder">
                        <i className="bi-search" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Tạo bài thi</h4>
                      <p className="text-white">Để bắt đầu tạo bài thi của riêng bạn, chỉ cần vào mục "<Link to="/manager-exam/create">Tạo bài thi</Link>", sau đó nhập câu hỏi và tùy chỉnh theo sở thích của bạn.</p>
                      <div className="icon-holder">
                        <i className="bi-bookmark" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Tham gia bài thi</h4>
                      <p className="text-white">
                        Kiểm tra kiến thức của bạn bằng cách <Link to={"/exam"}>làm các bài thi do người dùng khác tạo</Link>. Thử thách bản thân và học hỏi những điều mới một cách thú vị!
                      </p>
                      <div className="icon-holder">
                        <i className="bi-book" />
                      </div>
                    </li>
                    <li>
                      <h4 className="text-white">Kết nối với người khác</h4>
                      <p className="text-white">
                        Tương tác với các người dùng khác bằng cách bình luận trên blog và theo dõi. Xây dựng mạng lưới và chia sẻ hiểu biết với một cộng đồng đa dạng.
                      </p>
                      <div className="icon-holder">
                        <i className="bi-book" />
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </section>
      </section>
    </>
  )
}

export default HomePage