import { useEffect, useState } from "react"
import { BlogService } from "../../service/BlogService"
import { useAppDispatch, useAppSelector } from "../../redux/hooks"
import FunctionUtil from "../../util/FunctionUtil"
import BlogItemHistoryElement from "../element/blog/BlogItemHistoryElement"
import IntroductionElement from "../element/IntoductionElement"
import { deleteBlogHistory } from "../../redux/slice/blogHistorySlice"
import BlogListElement from "../element/blog/BlogPageElement"
import { Link, useNavigate } from "react-router-dom"

const BlogHomePage = () => {
  const [blogPage, setBlogPage] = useState<PageResponse<BlogResponse>>()
  const [page, setPage] = useState(1);
  const blogHistories = useAppSelector(state => state.blogHistory);
  const [keyword, setKeyword] = useState("");
  const navigate = useNavigate()
  const dispatch = useAppDispatch()
  useEffect(() => {
    fetchBlogPage()
  }, [])
  useEffect(() => {
    if (keyword)
      navigate("/blog/search?keyword=" + keyword)
  }, [keyword])
  const fetchBlogPage = () => {
    BlogService.search({ page: page, size: 10 }).then((data) => {
      if (data.success) {
        setBlogPage(data.data)
      }
    })
  }
  return (

    <section className="explore-section" id="section_2">
      <IntroductionElement keyword={keyword} setKeyword={setKeyword} into="Khám Phá Blog" content="Khám phá những bài viết hữu ích về nhiều chủ đề thú vị." />
      <div className="container">
        <div className="row mt-4">
          <div className="col-9">
            {/* <div className="tab-content" id="myTabContent"> */}
            <div className="d-flex justify-content-between">
              <h4 >Các blog mới nhất</h4>
              <Link className="btn btn-primary" to={"/manager-blog/create"}>Create Blog</Link>
            </div>
            <div className="tab-pane fade show active" id="design-tab-pane" role="tabpanel" aria-labelledby="design-tab" tabIndex={0}>
              {blogPage && <div className="row">
                <BlogListElement blogPage={blogPage} page={page} setPage={setPage} />
              </div>}
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
              <BlogItemHistoryElement handleDelete={() => dispatch(deleteBlogHistory(blogHistory.id))} key={`blog-history-${blogHistory.id}`} blog={blogHistory} />
            )}
          </div>
        </div>
      </div>
    </section>
  )
}

export default BlogHomePage