const BlogListPage = () => {
    return (
        <section >
            <div className="container">
                <div className="row">
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
                                            <ul className="list-unstyled">
                                                <li><a href="#" className="text-dark">Electronics </a></li>
                                                <li><a href="#" className="text-dark">Home items </a></li>
                                                <li><a href="#" className="text-dark">Books, Magazines </a></li>
                                                <li><a href="#" className="text-dark">Men's clothing </a></li>
                                                <li><a href="#" className="text-dark">Interiors items </a></li>
                                                <li><a href="#" className="text-dark">Underwears </a></li>
                                                <li><a href="#" className="text-dark">Shoes for men </a></li>
                                                <li><a href="#" className="text-dark">Accessories </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingTwo">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseTwo" aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
                                            Brands
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseTwo" className="accordion-collapse collapse show" aria-labelledby="headingTwo">
                                        <div className="accordion-body">
                                            <div>
                                                {/* Checked checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckChecked1" defaultChecked />
                                                    <label className="form-check-label" htmlFor="flexCheckChecked1">Mercedes</label>
                                                    <span className="badge badge-secondary float-end">120</span>
                                                </div>
                                                {/* Checked checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckChecked2" defaultChecked />
                                                    <label className="form-check-label" htmlFor="flexCheckChecked2">Toyota</label>
                                                    <span className="badge badge-secondary float-end">15</span>
                                                </div>
                                                {/* Checked checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckChecked3" defaultChecked />
                                                    <label className="form-check-label" htmlFor="flexCheckChecked3">Mitsubishi</label>
                                                    <span className="badge badge-secondary float-end">35</span>
                                                </div>
                                                {/* Checked checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckChecked4" defaultChecked />
                                                    <label className="form-check-label" htmlFor="flexCheckChecked4">Nissan</label>
                                                    <span className="badge badge-secondary float-end">89</span>
                                                </div>
                                                {/* Default checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckDefault" />
                                                    <label className="form-check-label" htmlFor="flexCheckDefault">Honda</label>
                                                    <span className="badge badge-secondary float-end">30</span>
                                                </div>
                                                {/* Default checkbox */}
                                                <div className="form-check">
                                                    <input className="form-check-input" type="checkbox"   id="flexCheckDefault" />
                                                    <label className="form-check-label" htmlFor="flexCheckDefault">Suzuki</label>
                                                    <span className="badge badge-secondary float-end">30</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingThree">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                                            Price
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseThree" className="accordion-collapse collapse show" aria-labelledby="headingThree">
                                        <div className="accordion-body">
                                            <div className="range">
                                                <input type="range" className="form-range" id="customRange1" />
                                            </div>
                                            <div className="row mb-3">
                                                <div className="col-6">
                                                    <p className="mb-0">
                                                        Min
                                                    </p>
                                                    <div className="form-outline">
                                                        <input type="number" id="typeNumber" className="form-control" />
                                                        <label className="form-label" htmlFor="typeNumber">$0</label>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <p className="mb-0">
                                                        Max
                                                    </p>
                                                    <div className="form-outline">
                                                        <input type="number" id="typeNumber" className="form-control" />
                                                        <label className="form-label" htmlFor="typeNumber">$1,0000</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="button" className="btn btn-white w-100 border border-secondary">apply</button>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingThree">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseFour" aria-expanded="false" aria-controls="panelsStayOpen-collapseFour">
                                            Size
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseFour" className="accordion-collapse collapse show" aria-labelledby="headingThree">
                                        <div className="accordion-body">
                                            <input type="checkbox" className="btn-check border justify-content-center" id="btn-check1" defaultChecked autoComplete="off" />
                                            <label className="btn btn-white mb-1 px-1" style={{ width: 60 }} htmlFor="btn-check1">XS</label>
                                            <input type="checkbox" className="btn-check border justify-content-center" id="btn-check2" defaultChecked autoComplete="off" />
                                            <label className="btn btn-white mb-1 px-1" style={{ width: 60 }} htmlFor="btn-check2">SM</label>
                                            <input type="checkbox" className="btn-check border justify-content-center" id="btn-check3" defaultChecked autoComplete="off" />
                                            <label className="btn btn-white mb-1 px-1" style={{ width: 60 }} htmlFor="btn-check3">LG</label>
                                            <input type="checkbox" className="btn-check border justify-content-center" id="btn-check4" defaultChecked autoComplete="off" />
                                            <label className="btn btn-white mb-1 px-1" style={{ width: 60 }} htmlFor="btn-check4">XXL</label>
                                        </div>
                                    </div>
                                </div>
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingThree">
                                        <button className="accordion-button text-dark bg-light" type="button" data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseFive" aria-expanded="false" aria-controls="panelsStayOpen-collapseFive">
                                            Ratings
                                        </button>
                                    </h2>
                                    <div id="panelsStayOpen-collapseFive" className="accordion-collapse collapse show" aria-labelledby="headingThree">
                                        <div className="accordion-body">
                                            {/* Default checkbox */}
                                            <div className="form-check">
                                                <input className="form-check-input" type="checkbox"   id="flexCheckDefault" defaultChecked />
                                                <label className="form-check-label" htmlFor="flexCheckDefault">
                                                    <i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" />
                                                    <i className="fas fa-star text-warning" />
                                                </label>
                                            </div>
                                            {/* Default checkbox */}
                                            <div className="form-check">
                                                <input className="form-check-input" type="checkbox"   id="flexCheckDefault" defaultChecked />
                                                <label className="form-check-label" htmlFor="flexCheckDefault">
                                                    <i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" />
                                                    <i className="fas fa-star text-secondary" />
                                                </label>
                                            </div>
                                            {/* Default checkbox */}
                                            <div className="form-check">
                                                <input className="form-check-input" type="checkbox"   id="flexCheckDefault" defaultChecked />
                                                <label className="form-check-label" htmlFor="flexCheckDefault">
                                                    <i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-secondary" />
                                                    <i className="fas fa-star text-secondary" />
                                                </label>
                                            </div>
                                            {/* Default checkbox */}
                                            <div className="form-check">
                                                <input className="form-check-input" type="checkbox"   id="flexCheckDefault" defaultChecked />
                                                <label className="form-check-label" htmlFor="flexCheckDefault">
                                                    <i className="fas fa-star text-warning" /><i className="fas fa-star text-warning" /><i className="fas fa-star text-secondary" /><i className="fas fa-star text-secondary" />
                                                    <i className="fas fa-star text-secondary" />
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* sidebar */}
                    {/* content */}
                    <div className="col-lg-9">
                        <header className="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                            <strong className="d-block py-2">32 Items found </strong>
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
                        <div className="row justify-content-center mb-3">
                            <div className="col-md-12">
                                <div className="card shadow-0 border rounded-3">
                                    <div className="card-body">
                                        <div className="row g-0">
                                            <div className="col-xl-3 col-md-4 d-flex justify-content-center">
                                                <div className="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/8.webp" className="w-100" />
                                                    <a href="#!">
                                                        <div className="hover-overlay">
                                                            <div className="mask" style={{ backgroundColor: 'rgba(253, 253, 253, 0.15)' }} />
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-md-5 col-sm-7">
                                                <h5>Rucksack Backpack Jeans</h5>
                                                <div className="d-flex flex-row">
                                                    <div className="text-warning mb-1 me-2">
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fas fa-star-half-alt" />
                                                        <span className="ms-1">
                                                            4.5
                                                        </span>
                                                    </div>
                                                    <span className="text-muted">154 orders</span>
                                                </div>
                                                <p className="text mb-4 mb-md-0">
                                                    Short description about the product goes here, for ex its features. Lorem ipsum dolor sit amet with hapti you enter into any new area of science, you almost lorem ipsum is great text
                                                    consectetur adipisicing
                                                </p>
                                            </div>
                                            <div className="col-xl-3 col-md-3 col-sm-5">
                                                <div className="d-flex flex-row align-items-center mb-1">
                                                    <h4 className="mb-1 me-1">$34,50</h4>
                                                    <span className="text-danger"><s>$49.99</s></span>
                                                </div>
                                                <h6 className="text-success">Free shipping</h6>
                                                <div className="mt-4">
                                                    <button className="btn btn-primary shadow-0" type="button">Buy this</button>
                                                    <a href="#!" className="btn btn-light border px-2 pt-2 icon-hover"><i className="fas fa-heart fa-lg px-1" /></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row justify-content-center mb-3">
                            <div className="col-md-12">
                                <div className="card shadow-0 border rounded-3">
                                    <div className="card-body">
                                        <div className="row g-0">
                                            <div className="col-xl-3 col-md-4 d-flex justify-content-center">
                                                <div className="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/9.webp" className="w-100" />
                                                    <a href="#!">
                                                        <div className="hover-overlay">
                                                            <div className="mask" style={{ backgroundColor: 'rgba(253, 253, 253, 0.15)' }} />
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-md-5 col-sm-7">
                                                <h5>Men's Denim Jeans Shorts</h5>
                                                <div className="d-flex flex-row">
                                                    <div className="text-warning mb-1 me-2">
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="far fa-star" />
                                                        <i className="far fa-star" />
                                                        <span className="ms-1">
                                                            3
                                                        </span>
                                                    </div>
                                                    <span className="text-muted">73 orders</span>
                                                </div>
                                                <p className="text mb-4 mb-md-0">
                                                    Re-engineered Digital Crown with hapti Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua tempor incididunt ut
                                                    labore et dolore magna [...]
                                                </p>
                                            </div>
                                            <div className="col-xl-3 col-md-3 col-sm-5">
                                                <div className="d-flex flex-row align-items-center mb-1">
                                                    <h4 className="mb-1 me-1">$34,50</h4>
                                                    <span className="text-danger"><s>$49.99</s></span>
                                                </div>
                                                <h6 className="text-warning">Paid shipping</h6>
                                                <div className="mt-4">
                                                    <button className="btn btn-primary shadow-0" type="button">Buy this</button>
                                                    <a href="#!" className="btn btn-light border px-2 pt-2 icon-hover"><i className="fas fa-heart fa-lg px-1" /></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row justify-content-center mb-3">
                            <div className="col-md-12">
                                <div className="card shadow-0 border rounded-3">
                                    <div className="card-body">
                                        <div className="row g-0">
                                            <div className="col-xl-3 col-md-4 d-flex justify-content-center">
                                                <div className="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/10.webp" className="w-100" />
                                                    <a href="#!">
                                                        <div className="hover-overlay">
                                                            <div className="mask" style={{ backgroundColor: 'rgba(253, 253, 253, 0.15)' }} />
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-md-5 col-sm-7">
                                                <h5>T-shirt for Men Blue Cotton Base</h5>
                                                <div className="d-flex flex-row">
                                                    <div className="text-warning mb-1 me-2">
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fas fa-star-half-alt" />
                                                        <i className="far fa-star" />
                                                        <span className="ms-1">
                                                            3.5
                                                        </span>
                                                    </div>
                                                    <span className="text-muted">910 orders</span>
                                                </div>
                                                <p className="text mb-4 mb-md-0">
                                                    Short description about the product goes here, for ex its features. Lorem ipsum dolor sit amet with hapti you enter into any new area of science, you almost lorem ipsum is great text
                                                    consectetur adipisicing
                                                </p>
                                            </div>
                                            <div className="col-xl-3 col-md-3 col-sm-5">
                                                <div className="d-flex flex-row align-items-center mb-1">
                                                    <h4 className="mb-1 me-1">$99,50</h4>
                                                </div>
                                                <h6 className="text-success">Free shipping</h6>
                                                <div className="mt-4">
                                                    <button className="btn btn-primary shadow-0" type="button">Buy this</button>
                                                    <a href="#!" className="btn btn-light border px-2 pt-2 icon-hover"><i className="fas fa-heart fa-lg px-1" /></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row justify-content-center mb-3">
                            <div className="col-md-12">
                                <div className="card shadow-0 border rounded-3">
                                    <div className="card-body">
                                        <div className="row g-0">
                                            <div className="col-xl-3 col-md-4 d-flex justify-content-center">
                                                <div className="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/11.webp" className="w-100" />
                                                    <a href="#!">
                                                        <div className="hover-overlay">
                                                            <div className="mask" style={{ backgroundColor: 'rgba(253, 253, 253, 0.15)' }} />
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-md-5 col-sm-7">
                                                <h5>Winter Jacket for Men and Women</h5>
                                                <div className="d-flex flex-row">
                                                    <div className="text-warning mb-1 me-2">
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fas fa-star-half-alt" />
                                                        <span className="ms-1">
                                                            4.5
                                                        </span>
                                                    </div>
                                                    <span className="text-muted">154 orders</span>
                                                </div>
                                                <p className="text mb-4 mb-md-0">
                                                    Short description about the product goes here, for ex its features. Lorem ipsum dolor sit amet with hapti you enter into any new area of science, you almost lorem ipsum is great text
                                                </p>
                                            </div>
                                            <div className="col-xl-3 col-md-3 col-sm-5">
                                                <div className="d-flex flex-row align-items-center mb-1">
                                                    <h4 className="mb-1 me-1">$140</h4>
                                                    <span className="text-danger"><s>$190</s></span>
                                                </div>
                                                <h6 className="text-success">Free shipping</h6>
                                                <div className="mt-4">
                                                    <button className="btn btn-primary shadow-0" type="button">Buy this</button>
                                                    <a href="#!" className="btn btn-light border px-2 pt-2 icon-hover"><i className="fas fa-heart fa-lg px-1" /></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row justify-content-center mb-3">
                            <div className="col-md-12">
                                <div className="card shadow-0 border rounded-3">
                                    <div className="card-body">
                                        <div className="row g-0">
                                            <div className="col-xl-3 col-md-4 d-flex justify-content-center">
                                                <div className="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                    <img src="https://mdbootstrap.com/img/bootstrap-ecommerce/items/12.webp" className="w-100" />
                                                    <a href="#!">
                                                        <div className="hover-overlay">
                                                            <div className="mask" style={{ backgroundColor: 'rgba(253, 253, 253, 0.15)' }} />
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-md-5 col-sm-7">
                                                <h5>T-shirt for Men Blue Cotton Base</h5>
                                                <div className="d-flex flex-row">
                                                    <div className="text-warning mb-1 me-2">
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fa fa-star" />
                                                        <i className="fas fa-star-half-alt" />
                                                        <span className="ms-1">
                                                            4.5
                                                        </span>
                                                    </div>
                                                    <span className="text-muted">154 orders</span>
                                                </div>
                                                <p className="text mb-4 mb-md-0">
                                                    Short description about the product goes here, for ex its features. Lorem ipsum dolor sit amet with hapti you enter into any new area of science, you almost lorem ipsum is great text
                                                </p>
                                            </div>
                                            <div className="col-xl-3 col-md-3 col-sm-5">
                                                <div className="d-flex flex-row align-items-center mb-1">
                                                    <h4 className="mb-1 me-1">$99.50</h4>
                                                    <span className="text-danger"><s>$190</s></span>
                                                </div>
                                                <h6 className="text-success">Free shipping</h6>
                                                <div className="mt-4">
                                                    <button className="btn btn-primary shadow-0" type="button">Buy this</button>
                                                    <a href="#!" className="btn btn-light border px-2 pt-2 icon-hover"><i className="fas fa-heart fa-lg px-1" /></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />
                        {/* Pagination */}
                        <nav aria-label="Page navigation example" className="d-flex justify-content-center mt-3">
                            <ul className="pagination">
                                <li className="page-item disabled">
                                    <a className="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </li>
                                <li className="page-item active"><a className="page-link" href="#">1</a></li>
                                <li className="page-item"><a className="page-link" href="#">2</a></li>
                                <li className="page-item"><a className="page-link" href="#">3</a></li>
                                <li className="page-item"><a className="page-link" href="#">4</a></li>
                                <li className="page-item"><a className="page-link" href="#">5</a></li>
                                <li className="page-item">
                                    <a className="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        {/* Pagination */}
                    </div>
                </div>
            </div>
        </section>

    )
}

export default BlogListPage