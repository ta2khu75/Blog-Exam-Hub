const AboutPage = () => {
    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <h1 className="mb-4 text-center">About Us</h1>
                    <p className="lead">
                        Welcome to our website! We are committed to providing the best content and services to our users.
                        Our mission is to help you achieve your goals and create value through our platform.
                    </p>

                    <h3 className="mt-5">Our Team</h3>
                    <p>
                        Our team consists of passionate and experienced individuals dedicated to creating
                        a positive impact in the community. We work hard to ensure that our services are reliable,
                        user-friendly, and innovative.
                    </p>

                    <div className="row mt-4">
                        <div className="col-md-4 text-center">
                            <img
                                src="https://via.placeholder.com/150"
                                className="rounded-circle mb-3"
                                alt="Team Member 1"
                            />
                            <h5>John Doe</h5>
                            <p className="text-muted">Founder & CEO</p>
                        </div>
                        <div className="col-md-4 text-center">
                            <img
                                src="https://via.placeholder.com/150"
                                className="rounded-circle mb-3"
                                alt="Team Member 2"
                            />
                            <h5>Jane Smith</h5>
                            <p className="text-muted">Chief Technology Officer</p>
                        </div>
                        <div className="col-md-4 text-center">
                            <img
                                src="https://via.placeholder.com/150"
                                className="rounded-circle mb-3"
                                alt="Team Member 3"
                            />
                            <h5>Mike Johnson</h5>
                            <p className="text-muted">Lead Developer</p>
                        </div>
                    </div>

                    <h3 className="mt-5">Our Values</h3>
                    <ul className="list-unstyled">
                        <li><i className="bi bi-check-lg text-primary me-2"></i> Innovation</li>
                        <li><i className="bi bi-check-lg text-primary me-2"></i> Customer Satisfaction</li>
                        <li><i className="bi bi-check-lg text-primary me-2"></i> Teamwork</li>
                    </ul>

                    <h3 className="mt-5">Contact Us</h3>
                    <p>If you have any questions or would like to get in touch, please contact us at:</p>
                    <ul>
                        <li>Email: contact@ourwebsite.com</li>
                        <li>Phone: +123-456-7890</li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default AboutPage