const ExamDetailPage = () => {
    return (
        <div>
            <header className="site-header d-flex flex-column justify-content-center align-items-center">
                <div className="container">
                    <div className="row justify-content-center align-items-center">
                        <div className="col-lg-5 col-12 mb-5">
                            <nav aria-label="breadcrumb">
                                <ol className="breadcrumb">
                                    <li className="breadcrumb-item"><a href="index.html">Homepage</a></li>
                                    <li className="breadcrumb-item active" aria-current="page">Web Design</li>
                                </ol>
                            </nav>
                            <h2 className="text-white">Introduction to <br /> Web Design 101</h2>
                            <div className="d-flex align-items-center mt-5">
                                <a href="#topics-detail" className="btn custom-btn custom-border-btn smoothscroll me-4">Read More</a>
                                <a href="#top" className="custom-icon bi-bookmark smoothscroll" />
                            </div>
                        </div>
                        <div className="col-lg-5 col-12">
                            <div className="topics-detail-block bg-white shadow-lg">
                                <img src="images/topics/undraw_Remote_design_team_re_urdx.png" className="topics-detail-block-image img-fluid" />
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <section className="topics-detail-section section-padding" id="topics-detail">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-8 col-12 m-auto">
                            <h3 className="mb-4">Introduction to Web Design</h3>
                            <p>So how can you stand out, do something unique and interesting, build an online business, and get paid enough to change life. Please visit TemplateMo website to download free website templates.</p>
                            <p><strong>There are so many ways to make money online</strong>. Below are several platforms you can use to find success. Keep in mind that there is no one path everyone can take. If that were the case, everyone would have a million dollars.</p>
                            <blockquote>
                                Freelancing your skills isn’t going to make you a millionaire overnight.
                            </blockquote>
                            <div className="row my-4">
                                <div className="col-lg-6 col-md-6 col-12">
                                    <img src="images/businesswoman-using-tablet-analysis.jpg" className="topics-detail-block-image img-fluid" />
                                </div>
                                <div className="col-lg-6 col-md-6 col-12 mt-4 mt-lg-0 mt-md-0">
                                    <img src="images/colleagues-working-cozy-office-medium-shot.jpg" className="topics-detail-block-image img-fluid" />
                                </div>
                            </div>
                            <p>Most people start with freelancing skills they already have as a side hustle to build up income. This extra cash can be used for a vacation, to boost up savings, investing, build business.</p>
                        </div>
                    </div>
                </div>
            </section>
            <section className="section-padding section-bg">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-lg-5 col-12">
                            <img src="images/rear-view-young-college-student.jpg" className="newsletter-image img-fluid" />
                        </div>
                        <div className="col-lg-5 col-12 subscribe-form-wrap d-flex justify-content-center align-items-center">
                            <form className="custom-form subscribe-form" action="#" method="post" role="form">
                                <h4 className="mb-4 pb-2">Get Newsletter</h4>
                                <input type="email" name="subscribe-email" id="subscribe-email" pattern="[^ @]*@[^ @]*" className="form-control" placeholder="Email Address" required />
                                <div className="col-lg-12 col-12">
                                    <button type="submit" className="form-control">Subscribe</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default ExamDetailPage