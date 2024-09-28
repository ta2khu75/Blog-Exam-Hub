import { useEffect, useState } from "react"
import ExamService from "../../service/ExamService"
import ExamCartElementNew from "../element/exam/ExamCartElement"
import { BlogService } from "../../service/BlogService"
import BlogItemElement from "../element/blog/BlogItemElement"
import IntroductionElement from "../element/IntoductionElement"
import { Pagination } from "antd"

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
    BlogService.search({ page: pageBlog, size: 5 }).then((response) => {
      if (response.success) setBlogPage(response.data);
    })
  }
  return (
    <>
      <IntroductionElement into="Create and Take Exams Online" content="ExamMaster allows you to easily create and take exams online, offering customizable settings, instant feedback, and secure access from any device." />
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
                      <BlogItemElement blog={blog} key={`blog-item-${blog.id}`} />
                    ))}
                    <Pagination align="center" onChange={setPageBlog} pageSize={5} defaultCurrent={1} total={blogPage?.total_elements} />
                  </div>
                </div>
                <div className="tab-pane fade" id="marketing-tab-pane" role="tabpanel" aria-labelledby="marketing-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Advertising</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">30</span>
                          </div>
                          <img src="images/topics/undraw_online_ad_re_ol62.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Video Content</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">65</span>
                          </div>
                          <img src="images/topics/undraw_Group_video_re_btu7.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Viral Tweet</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">50</span>
                          </div>
                          <img src="images/topics/undraw_viral_tweet_gndb.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="tab-pane fade" id="finance-tab-pane" role="tabpanel" aria-labelledby="finance-tab" tabIndex={0}>   <div className="row">
                  <div className="col-lg-6 col-md-6 col-12 mb-4 mb-lg-0">
                    <div className="custom-block bg-white shadow-lg">
                      <a href="topics-detail.html">
                        <div className="d-flex">
                          <div>
                            <h5 className="mb-2">Investment</h5>
                            <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                          </div>
                          <span className="badge bg-finance rounded-pill ms-auto">30</span>
                        </div>
                        <img src="images/topics/undraw_Finance_re_gnv2.png" className="custom-block-image img-fluid" />
                      </a>
                    </div>
                  </div>
                  <div className="col-lg-6 col-md-6 col-12">
                    <div className="custom-block custom-block-overlay">
                      <div className="d-flex flex-column h-100">
                        <img src="images/businesswoman-using-tablet-analysis-graph-company-finance-strategy-statistics-success-concept-planning-future-office-room.jpg" className="custom-block-image img-fluid" />
                        <div className="custom-block-overlay-text d-flex">
                          <div>
                            <h5 className="text-white mb-2">Finance</h5>
                            <p className="text-white">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Sint animi necessitatibus aperiam repudiandae nam omnis</p>
                            <a href="topics-detail.html" className="btn custom-btn mt-2 mt-lg-3">Learn More</a>
                          </div>
                          <span className="badge bg-finance rounded-pill ms-auto">25</span>
                        </div>
                        <div className="social-share d-flex">
                          <p className="text-white me-4">Share:</p>
                          <ul className="social-icon">
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-twitter" />
                            </li>
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-facebook" />
                            </li>
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-pinterest" />
                            </li>
                          </ul>
                          <a href="#" className="custom-icon bi-bookmark ms-auto" />
                        </div>
                        <div className="section-overlay" />
                      </div>
                    </div>
                  </div>
                </div>
                </div>
                <div className="tab-pane fade" id="music-tab-pane" role="tabpanel" aria-labelledby="music-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Composing Song</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">45</span>
                          </div>
                          <img src="images/topics/undraw_Compose_music_re_wpiw.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Online Music</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">45</span>
                          </div>
                          <img src="images/topics/undraw_happy_music_g6wc.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Podcast</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">20</span>
                          </div>
                          <img src="images/topics/undraw_Podcast_audience_re_4i5q.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="tab-pane fade" id="education-tab-pane" role="tabpanel" aria-labelledby="education-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-6 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Graduation</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-education rounded-pill ms-auto">80</span>
                          </div>
                          <img src="images/topics/undraw_Graduation_re_gthn.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Educator</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-education rounded-pill ms-auto">75</span>
                          </div>
                          <img src="images/topics/undraw_Educator_re_ju47.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section className="explore-section section-padding" id="section_2">
        <div className="container">
          <div className="col-12 text-center">
            <h2 className="mb-4">Browse Exam</h2>
          </div>
        </div>
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="tab-content" id="myTabContent">
                <div className="tab-pane fade show active" id="design-tab-pane" role="tabpanel" aria-labelledby="design-tab" tabIndex={0}>
                  <div className="row">
                    {examPage?.content?.map(examResponse => (
                      <ExamCartElementNew key={`exam-cart-${examResponse.id}`} exam={examResponse} />
                    ))}
                    <Pagination align="center" onChange={setPageExam} pageSize={4} defaultCurrent={1} total={examPage?.total_elements} />
                  </div>
                </div>
                <div className="tab-pane fade" id="marketing-tab-pane" role="tabpanel" aria-labelledby="marketing-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Advertising</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">30</span>
                          </div>
                          <img src="images/topics/undraw_online_ad_re_ol62.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Video Content</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">65</span>
                          </div>
                          <img src="images/topics/undraw_Group_video_re_btu7.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Viral Tweet</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-advertising rounded-pill ms-auto">50</span>
                          </div>
                          <img src="images/topics/undraw_viral_tweet_gndb.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="tab-pane fade" id="finance-tab-pane" role="tabpanel" aria-labelledby="finance-tab" tabIndex={0}>   <div className="row">
                  <div className="col-lg-6 col-md-6 col-12 mb-4 mb-lg-0">
                    <div className="custom-block bg-white shadow-lg">
                      <a href="topics-detail.html">
                        <div className="d-flex">
                          <div>
                            <h5 className="mb-2">Investment</h5>
                            <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                          </div>
                          <span className="badge bg-finance rounded-pill ms-auto">30</span>
                        </div>
                        <img src="images/topics/undraw_Finance_re_gnv2.png" className="custom-block-image img-fluid" />
                      </a>
                    </div>
                  </div>
                  <div className="col-lg-6 col-md-6 col-12">
                    <div className="custom-block custom-block-overlay">
                      <div className="d-flex flex-column h-100">
                        <img src="images/businesswoman-using-tablet-analysis-graph-company-finance-strategy-statistics-success-concept-planning-future-office-room.jpg" className="custom-block-image img-fluid" />
                        <div className="custom-block-overlay-text d-flex">
                          <div>
                            <h5 className="text-white mb-2">Finance</h5>
                            <p className="text-white">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Sint animi necessitatibus aperiam repudiandae nam omnis</p>
                            <a href="topics-detail.html" className="btn custom-btn mt-2 mt-lg-3">Learn More</a>
                          </div>
                          <span className="badge bg-finance rounded-pill ms-auto">25</span>
                        </div>
                        <div className="social-share d-flex">
                          <p className="text-white me-4">Share:</p>
                          <ul className="social-icon">
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-twitter" />
                            </li>
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-facebook" />
                            </li>
                            <li className="social-icon-item">
                              <a href="#" className="social-icon-link bi-pinterest" />
                            </li>
                          </ul>
                          <a href="#" className="custom-icon bi-bookmark ms-auto" />
                        </div>
                        <div className="section-overlay" />
                      </div>
                    </div>
                  </div>
                </div>
                </div>
                <div className="tab-pane fade" id="music-tab-pane" role="tabpanel" aria-labelledby="music-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Composing Song</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">45</span>
                          </div>
                          <img src="images/topics/undraw_Compose_music_re_wpiw.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Online Music</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">45</span>
                          </div>
                          <img src="images/topics/undraw_happy_music_g6wc.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Podcast</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-music rounded-pill ms-auto">20</span>
                          </div>
                          <img src="images/topics/undraw_Podcast_audience_re_4i5q.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="tab-pane fade" id="education-tab-pane" role="tabpanel" aria-labelledby="education-tab" tabIndex={0}>
                  <div className="row">
                    <div className="col-lg-6 col-md-6 col-12 mb-4 mb-lg-3">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Graduation</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-education rounded-pill ms-auto">80</span>
                          </div>
                          <img src="images/topics/undraw_Graduation_re_gthn.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6 col-12">
                      <div className="custom-block bg-white shadow-lg">
                        <a href="topics-detail.html">
                          <div className="d-flex">
                            <div>
                              <h5 className="mb-2">Educator</h5>
                              <p className="mb-0">Lorem Ipsum dolor sit amet consectetur</p>
                            </div>
                            <span className="badge bg-education rounded-pill ms-auto">75</span>
                          </div>
                          <img src="images/topics/undraw_Educator_re_ju47.png" className="custom-block-image img-fluid" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  )
}

export default HomePage