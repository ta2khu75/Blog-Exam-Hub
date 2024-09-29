const ProfileNewPage = () => {
    return (
        <>
            <div className="container">
                <div className="card overflow-hidden">
                    <div className="card-body p-0">
                        <img src="https://www.bootdey.com/image/1352x300/FF7F50/000000" className="img-fluid" />
                        <div className="row align-items-center">
                            <div className="col-lg-4 order-lg-1 order-2">
                                <div className="d-flex align-items-center justify-content-around m-4">
                                    <div className="text-center">
                                        <i className="fa fa-file fs-6 d-block mb-2" />
                                        <h4 className="mb-0 fw-semibold lh-1">938</h4>
                                        <p className="mb-0 fs-4">Posts</p>
                                    </div>
                                    <div className="text-center">
                                        <i className="fa fa-user fs-6 d-block mb-2" />
                                        <h4 className="mb-0 fw-semibold lh-1">3,586</h4>
                                        <p className="mb-0 fs-4">Followers</p>
                                    </div>
                                    <div className="text-center">
                                        <i className="fa fa-check fs-6 d-block mb-2" />
                                        <h4 className="mb-0 fw-semibold lh-1">2,659</h4>
                                        <p className="mb-0 fs-4">Following</p>
                                    </div>
                                </div>
                            </div>
                            <div className="col-lg-4 mt-n3 order-lg-2 order-1">
                                <div className="mt-n5">
                                    <div className="d-flex align-items-center justify-content-center mb-2">
                                        <div className="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style={{ width: 110, height: 110 }} >
                                            <div className="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style={{ width: 100, height: 100 }} />
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="w-100 h-100" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="text-center">
                                <h5 className="fs-5 mb-0 fw-semibold">Mathew Anderson</h5>
                                <p className="mb-0 fs-4">Designer</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-4 order-last">
                        <ul className="list-unstyled d-flex align-items-center justify-content-center justify-content-lg-start my-3 gap-3">
                            <li className="position-relative">
                                <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" style={{ width: "30px", height: "30px" }}>
                                    <i className="fa fa-facebook" />
                                </a>
                            </li>
                            <li className="position-relative">
                                <a className="text-white bg-secondary d-flex align-items-center justify-content-center p-2 fs-4 rounded-circle " href="javascript:void(0)">
                                    <i className="fa fa-twitter" />
                                </a>
                            </li>
                            <li className="position-relative">
                                <a className="text-white bg-secondary d-flex align-items-center justify-content-center p-2 fs-4 rounded-circle " href="javascript:void(0)">
                                    <i className="fa fa-dribbble" />
                                </a>
                            </li>
                            <li className="position-relative">
                                <a className="text-white bg-danger d-flex align-items-center justify-content-center p-2 fs-4 rounded-circle " href="javascript:void(0)">
                                    <i className="fa fa-youtube" />
                                </a>
                            </li>
                            <li><button className="btn btn-primary">Add To Story</button></li>
                        </ul>
                    </div>
                </div>
                <ul className="nav nav-pills user-profile-tab justify-content-end mt-2 bg-light-info rounded-2" id="pills-tab" role="tablist">
                    <li className="nav-item" role="presentation">
                        <button className="nav-link position-relative rounded-0 active d-flex align-items-center justify-content-center bg-transparent fs-3 py-6" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile" aria-selected="true">
                            <i className="fa fa-user me-2 fs-6" />
                            <span className="d-none d-md-block">Profile</span>
                        </button>
                    </li>
                    <li className="nav-item" role="presentation">
                        <button className="nav-link position-relative rounded-0 d-flex align-items-center justify-content-center bg-transparent fs-3 py-6" id="pills-followers-tab">
                            <i className="fa fa-heart me-2 fs-6" />
                            <span className="d-none d-md-block">Followers</span>
                        </button>
                    </li>
                    <li className="nav-item" role="presentation">
                        <button className="nav-link position-relative rounded-0 d-flex align-items-center justify-content-center bg-transparent fs-3 py-6" id="pills-friends-tab">
                            <i className="fa fa-users me-2 fs-6" />
                            <span className="d-none d-md-block">Friends</span>
                        </button>
                    </li>
                    <li className="nav-item" role="presentation">
                        <button className="nav-link position-relative rounded-0 d-flex align-items-center justify-content-center bg-transparent fs-3 py-6" id="pills-gallery-tab">
                            <i className="fa fa-photo me-2 fs-6" />
                            <span className="d-none d-md-block">Gallery</span>
                        </button>
                    </li>
                </ul>
            </div>
            <div className="tab-content" id="pills-tabContent">
                <div className="tab-pane fade show active" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab" tabIndex={0}>
                    <div className="row">
                        <div className="col-lg-4">
                            <div className="card shadow-none border">
                                <div className="card-body">
                                    <h4 className="fw-semibold mb-3">Introduction</h4>
                                    <p>Hello, I am Mathew Anderson. I love making websites and graphics. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                    <ul className="list-unstyled mb-0">
                                        <li className="d-flex align-items-center gap-3 mb-4">
                                            <i className="fa fa-briefcase text-dark fs-6" />
                                            <h6 className="fs-4 fw-semibold mb-0">Sir, P P Institute Of Science</h6>
                                        </li>
                                        <li className="d-flex align-items-center gap-3 mb-4">
                                            <i className="fa fa-envelope text-dark fs-6" />
                                            <h6 className="fs-4 fw-semibold mb-0">xyzjonathan@gmail.com</h6>
                                        </li>
                                        <li className="d-flex align-items-center gap-3 mb-4">
                                            <i className="fa fa-desktop text-dark fs-6" />
                                            <h6 className="fs-4 fw-semibold mb-0">www.xyz.com</h6>
                                        </li>
                                        <li className="d-flex align-items-center gap-3 mb-2">
                                            <i className="fa fa-list text-dark fs-6" />
                                            <h6 className="fs-4 fw-semibold mb-0">Newyork, USA - 100001</h6>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div className="card shadow-none border">
                                <div className="card-body">
                                    <h4 className="fw-semibold mb-3">Photos</h4>
                                    <div className="row">
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" className="rounded-2 img-fluid mb-9" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" className="rounded-2 img-fluid mb-6" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" className="rounded-2 img-fluid mb-6" />
                                        </div>
                                        <div className="col-4">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-2 img-fluid mb-6" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-8">
                            <div className="card shadow-none border">
                                <div className="card-body">
                                    <div className="form-floating mb-3">
                                        <textarea className="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style={{ height: 137 }} defaultValue={""} />
                                        <label htmlFor="floatingTextarea2" className="p-7">Share your thoughts</label>
                                    </div>
                                    <div className="d-flex align-items-center gap-2">
                                        <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)">
                                            <i className="fa fa-photo" />
                                        </a>
                                        <a href="javascript:void(0)" className="text-dark px-3 py-2">Photo / Video</a>
                                        <a href="javascript:void(0)" className="d-flex align-items-center gap-2">
                                            <div className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle">
                                                <i className="fa fa-list" />
                                            </div>
                                            <span className="text-dark">Article</span>
                                        </a>
                                        <button className="btn btn-primary ms-auto">Post</button>
                                    </div>
                                </div>
                            </div>
                            <div className="card">
                                <div className="card-body border-bottom">
                                    <div className="d-flex align-items-center gap-3">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-circle" width={40} height={40} />
                                        <h6 className="fw-semibold mb-0 fs-4">Mathew Anderson</h6>
                                        <span className="fs-2"><span className="p-1 bg-light rounded-circle d-inline-block" /> 15 min ago</span>
                                    </div>
                                    <p className="text-dark my-3">
                                        Nu kek vuzkibsu mooruno ejepogojo uzjon gag fa ezik disan he nah. Wij wo pevhij tumbug rohsa ahpi ujisapse lo vap labkez eddu suk.
                                    </p>
                                    <img src="https://www.bootdey.com/image/680x380/FF7F50/000000" className="img-fluid rounded-4 w-100 object-fit-cover" style={{ height: 360 }} />
                                    <div className="d-flex align-items-center my-3">
                                        <div className="d-flex align-items-center gap-2">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                <i className="fa fa-thumbs-up" />
                                            </a>
                                            <span className="text-dark fw-semibold">67</span>
                                        </div>
                                        <div className="d-flex align-items-center gap-2 ms-4">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Comment">
                                                <i className="fa fa-comments" />
                                            </a>
                                            <span className="text-dark fw-semibold">2</span>
                                        </div>
                                        <a className="text-dark ms-auto d-flex align-items-center justify-content-center bg-transparent p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Share">
                                            <i className="fa fa-share" />
                                        </a>
                                    </div>
                                    <div className="position-relative">
                                        <div className="p-4 rounded-2 bg-light mb-3">
                                            <div className="d-flex align-items-center gap-3">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar3.png" className="rounded-circle" width={33} height={33} />
                                                <h6 className="fw-semibold mb-0 fs-4">Deran Mac</h6>
                                                <span className="fs-2"><span className="p-1 bg-muted rounded-circle d-inline-block" /> 8 min ago</span>
                                            </div>
                                            <p className="my-3">Lufo zizrap iwofapsuk pusar luc jodawbac zi op uvezojroj duwage vuhzoc ja vawdud le furhez siva
                                                fikavu ineloh. Zot afokoge si mucuve hoikpaf adzuk zileuda falohfek zoije fuka udune lub annajor gazo
                                                conis sufur gu.
                                            </p>
                                            <div className="d-flex align-items-center">
                                                <div className="d-flex align-items-center gap-2">
                                                    <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                        <i className="fa fa-thumbs-up" />
                                                    </a>
                                                    <span className="text-dark fw-semibold">55</span>
                                                </div>
                                                <div className="d-flex align-items-center gap-2 ms-4">
                                                    <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Reply">
                                                        <i className="fa fa-arrow-up" />
                                                    </a>
                                                    <span className="text-dark fw-semibold">0</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="p-4 rounded-2 bg-light mb-3">
                                            <div className="d-flex align-items-center gap-3">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar4.png" className="rounded-circle" width={33} height={33} />
                                                <h6 className="fw-semibold mb-0 fs-4">Jonathan Bg</h6>
                                                <span className="fs-2"><span className="p-1 bg-muted rounded-circle d-inline-block" /> 5 min ago</span>
                                            </div>
                                            <p className="my-3">
                                                Zumankeg ba lah lew ipep tino tugjekoj hosih fazjid wotmila durmuri buf hi sigapolu joit ebmi joge vo.
                                                Horemo vogo hat na ejednu sarta afaamraz zi cunidce peroido suvan podene igneve.
                                            </p>
                                            <div className="d-flex align-items-center">
                                                <div className="d-flex align-items-center gap-2">
                                                    <a className="text-dark d-flex align-items-center justify-content-center bg-light-dark p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                        <i className="fa fa-thumbs-up" />
                                                    </a>
                                                    <span className="text-dark fw-semibold">68</span>
                                                </div>
                                                <div className="d-flex align-items-center gap-2 ms-4">
                                                    <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Reply">
                                                        <i className="fa fa-arrow-up" />
                                                    </a>
                                                    <span className="text-dark fw-semibold">1</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="p-4 rounded-2 bg-light ms-7">
                                            <div className="d-flex align-items-center gap-3">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar5.png" className="rounded-circle" width={40} height={40} />
                                                <h6 className="fw-semibold mb-0 fs-4">Carry minati</h6>
                                                <span className="fs-2"><span className="p-1 bg-muted rounded-circle d-inline-block" /> just now</span>
                                            </div>
                                            <p className="my-3">
                                                Olte ni somvukab ugura ovaobeco hakgoc miha peztajo tawosu udbacas kismakin hi. Dej
                                                zetfamu cevufi sokbid bud mun soimeuha pokahram vehurpar keecris pepab voegmud
                                                zundafhef hej pe.
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center gap-3 p-3">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-circle" width={33} height={33} />
                                    <input type="text" className="form-control py-8" id="exampleInputtext" aria-describedby="textHelp" placeholder="Comment" />
                                    <button className="btn btn-primary">Comment</button>
                                </div>
                            </div>
                            <div className="card">
                                <div className="card-body border-bottom">
                                    <div className="d-flex align-items-center gap-3">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar5.png" className="rounded-circle" width={40} height={40} />
                                        <h6 className="fw-semibold mb-0 fs-4">Carry Minati</h6>
                                        <span className="fs-2"><span className="p-1 bg-light rounded-circle d-inline-block" /> now</span>
                                    </div>
                                    <p className="text-dark my-3">
                                        Pucnus taw set babu lasufot lawdebuw nem ig bopnub notavfe pe ranlu dijsan liwfekaj lo az. Dom giat gu
                                        sehiosi bikelu lo eb uwrerej bih woppoawi wijdiola iknem hih suzega gojmev kir rigoj.
                                    </p>
                                    <div className="d-flex align-items-center">
                                        <div className="d-flex align-items-center gap-2">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                <i className="fa fa-thumbs-up" />
                                            </a>
                                            <span className="text-dark fw-semibold">1</span>
                                        </div>
                                        <div className="d-flex align-items-center gap-2 ms-4">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Comment">
                                                <i className="fa fa-comments" />
                                            </a>
                                            <span className="text-dark fw-semibold">0</span>
                                        </div>
                                        <a className="text-dark ms-auto d-flex align-items-center justify-content-center bg-transparent p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Share">
                                            <i className="fa fa-share" />
                                        </a>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center gap-3 p-3">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar5.png" className="rounded-circle" width={33} height={33} />
                                    <input type="text" className="form-control py-8" id="exampleInputtext" aria-describedby="textHelp" placeholder="Comment" />
                                    <button className="btn btn-primary">Comment</button>
                                </div>
                            </div>
                            <div className="card">
                                <div className="card-body border-bottom">
                                    <div className="d-flex align-items-center gap-3">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar2.png" className="rounded-circle" width={40} height={40} />
                                        <h6 className="fw-semibold mb-0 fs-4">Genelia Desouza</h6>
                                        <span className="fs-2"><span className="p-1 bg-light rounded-circle d-inline-block" /> 15 min ago</span>
                                    </div>
                                    <p className="text-dark my-3">
                                        Faco kiswuoti mucurvi juokomo fobgi aze huweik zazjofefa kuujer talmoc li niczot lohejbo vozev zi huto. Ju
                                        tupma uwujate bevolkoh hob munuap lirec zak ja li hotlanu pigtunu.
                                    </p>
                                    <div className="row">
                                        <div className="col-sm-6">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" className="img-fluid rounded-4 mb-3 mb-sm-0" />
                                        </div>
                                        <div className="col-sm-6">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png" className="img-fluid rounded-4" />
                                        </div>
                                    </div>
                                    <div className="d-flex align-items-center my-3">
                                        <div className="d-flex align-items-center gap-2">
                                            <a className="text-dark d-flex align-items-center justify-content-center bg-light p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                <i className="fa fa-thumbs-up" />
                                            </a>
                                            <span className="text-dark fw-semibold">320</span>
                                        </div>
                                        <div className="d-flex align-items-center gap-2 ms-4">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Comment">
                                                <i className="fa fa-comments" />
                                            </a>
                                            <span className="text-dark fw-semibold">1</span>
                                        </div>
                                        <a className="text-dark ms-auto d-flex align-items-center justify-content-center bg-transparent p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Share">
                                            <i className="fa fa-share" />
                                        </a>
                                    </div>
                                    <div className="p-4 rounded-2 bg-light">
                                        <div className="d-flex align-items-center gap-3">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" className="rounded-circle" width={33} height={33} />
                                            <h6 className="fw-semibold mb-0 fs-4">Ritesh Deshmukh</h6>
                                            <span className="fs-2"><span className="p-1 bg-muted rounded-circle d-inline-block" /> 15 min ago</span>
                                        </div>
                                        <p className="my-3">
                                            Hintib cojno riv ze heb cipcep fij wo tufinpu bephekdab infule pajnaji. Jiran goetimip muovo go en
                                            gaga zeljomim hozlu lezuvi ehkapod dec bifoom hag dootasac odo luvgit ti ella.
                                        </p>
                                        <div className="d-flex align-items-center">
                                            <div className="d-flex align-items-center gap-2">
                                                <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                    <i className="fa fa-thumbs-up" />
                                                </a>
                                                <span className="text-dark fw-semibold">65</span>
                                            </div>
                                            <div className="d-flex align-items-center gap-2 ms-4">
                                                <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Reply">
                                                    <i className="fa fa-arrow-up" />
                                                </a>
                                                <span className="text-dark fw-semibold">0</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center gap-3 p-3">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar2.png" className="rounded-circle" width={33} height={33} />
                                    <input type="text" className="form-control py-8" id="exampleInputtext" aria-describedby="textHelp" placeholder="Comment" />
                                    <button className="btn btn-primary">Comment</button>
                                </div>
                            </div>
                            <div className="card">
                                <div className="card-body border-bottom">
                                    <div className="d-flex align-items-center gap-3">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-circle" width={40} height={40} />
                                        <h6 className="fw-semibold mb-0 fs-4">Mathew Anderson</h6>
                                        <span className="fs-2"><span className="p-1 bg-light rounded-circle d-inline-block" /> 15 min ago</span>
                                    </div>
                                    <p className="text-dark my-3">
                                        Faco kiswuoti mucurvi juokomo fobgi aze huweik zazjofefa kuujer talmoc li niczot lohejbo vozev zi huto. Ju
                                        tupma uwujate bevolkoh hob munuap lirec zak ja li hotlanu pigtunu.
                                    </p>
                                    <img src="https://www.bootdey.com/image/680x380/FF7F50/000000" className="img-fluid rounded-4 w-100 object-fit-cover mb-4" style={{ height: 360 }} />
                                    <div className="d-flex align-items-center">
                                        <div className="d-flex align-items-center gap-2">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-primary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Like">
                                                <i className="fa fa-thumbs-up" />
                                            </a>
                                            <span className="text-dark fw-semibold">129</span>
                                        </div>
                                        <div className="d-flex align-items-center gap-2 ms-4">
                                            <a className="text-white d-flex align-items-center justify-content-center bg-secondary p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Comment">
                                                <i className="fa fa-comments" />
                                            </a>
                                            <span className="text-dark fw-semibold">0</span>
                                        </div>
                                        <a className="text-dark ms-auto d-flex align-items-center justify-content-center bg-transparent p-2 fs-4 rounded-circle" href="javascript:void(0)" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Share">
                                            <i className="fa fa-share" />
                                        </a>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center gap-3 p-3">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" className="rounded-circle" width={33} height={33} />
                                    <input type="text" className="form-control py-8" id="exampleInputtext" aria-describedby="textHelp" placeholder="Comment" />
                                    <button className="btn btn-primary">Comment</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </>
    )
}

export default ProfileNewPage