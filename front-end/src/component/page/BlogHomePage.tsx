import { useEffect, useState } from "react"
import { BlogService } from "../../service/BlogService"
import BlogItemElement from "../element/blog/BlogItemElement"
import { useAppSelector } from "../../redux/hooks"
import FunctionUtil from "../../util/FunctionUtil"
import BlogItemHistoryElement from "../element/blog/BlogItemHistoryElement"
import IntroductionElement from "../element/IntoductionElement"

const BlogHomePage = () => {
  const [blogPage, setBlogPage] = useState<PageResponse<BlogResponse>>()
  const blogHistories = useAppSelector(state => state.blogHistory);
  useEffect(() => {
    fetchBlogPage()
  }, [])
  const fetchBlogPage = () => {
    BlogService.readPage(1, 10).then((data) => {
      if (data.success) {
        setBlogPage(data.data)
      }
    })
  }
  return (

    <section className="explore-section" id="section_2">
      <IntroductionElement into="Khám Phá Blog" content="Khám phá những bài viết hữu ích về nhiều chủ đề thú vị." />
      <div className="container">
        <div className="row mt-4">
          <div className="col-9">
            {/* <div className="tab-content" id="myTabContent"> */}
            <h4 className="mb-3">Các blog mới nhất</h4>
            <div className="tab-pane fade show active" id="design-tab-pane" role="tabpanel" aria-labelledby="design-tab" tabIndex={0}>
              <div className="row">
                {blogPage?.content?.map(blog => (
                  <BlogItemElement blog={blog} key={`blog-item-${blog.id}`} />
                ))}
              </div>
            </div>

            {/* Gợi Ý Chủ Đề */}
            <div className="tab-pane fade" id="suggested-tab" role="tabpanel" aria-labelledby="suggested-tab" tabIndex={0}>
              <div className="row">
                <div className="col-lg-4 col-md-6 mb-4">
                  <div className="custom-block bg-light shadow-lg p-4 text-center">
                    <h5 className="mb-2">Sức Khỏe & Sắc Đẹp</h5>
                    <p>Lorem Ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    <a href="topics-detail.html" className="btn btn-outline-primary">Khám Phá</a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 mb-4">
                  <div className="custom-block bg-light shadow-lg p-4 text-center">
                    <h5 className="mb-2">Du Lịch & Phiêu Lưu</h5>
                    <p>Lorem Ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    <a href="topics-detail.html" className="btn btn-outline-primary">Khám Phá</a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 mb-4">
                  <div className="custom-block bg-light shadow-lg p-4 text-center">
                    <h5 className="mb-2">Xu Hướng Công Nghệ</h5>
                    <p>Lorem Ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    <a href="topics-detail.html" className="btn btn-outline-primary">Khám Phá</a>
                  </div>
                </div>
              </div>
              {/* </div> */}

              {/* Các Tab Nội Dung Khác */}
              {/* Bạn có thể thêm các tab nội dung khác ở đây */}
            </div>
          </div>
          <div className="col-3">
            <h4 className="mb-3">Các blog bạn đã xem</h4>
            {FunctionUtil.convertMaptoArray<BlogResponse>(blogHistories)?.map(blogHistory =>
              <BlogItemHistoryElement key={`blog-history-${blogHistory.id}`} blog={blogHistory} />
            )}
          </div>
        </div>
      </div>
    </section>
  )
}

export default BlogHomePage