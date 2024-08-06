import { NavLink, Outlet } from "react-router-dom"

const AdminComponent = () => {
  return (
<div>
  {/*Main Navigation*/}
  <header className="row mt-5">
    {/* Sidebar */}
    <nav id="sidebarMenu" className="collapse col-3 d-lg-block sidebar collapse bg-white">
      <div className="position-sticky">
        <div className="list-group list-group-flush mx-3 mt-4">
          <NavLink to={"/admin"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init aria-current="true">
            <i className="fas fa-tachometer-alt fa-fw me-3" /><span>Main dashboard</span>
          </NavLink>
          <NavLink to={"/admin/exam"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
            <i className="fas fa-chart-area fa-fw me-3" /><span>Exam</span>
          </NavLink>
          <NavLink to={"/admin/account"} className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
            <i className="fas fa-chart-area fa-fw me-3" /><span>Account</span>
          </NavLink>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-lock fa-fw me-3" /><span>Password</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-chart-line fa-fw me-3" /><span>Analytics</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init>
            <i className="fas fa-chart-pie fa-fw me-3" /><span>SEO</span>
          </a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-chart-bar fa-fw me-3" /><span>Orders</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-globe fa-fw me-3" /><span>International</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-building fa-fw me-3" /><span>Partners</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-calendar fa-fw me-3" /><span>Calendar</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-users fa-fw me-3" /><span>Users</span></a>
          <a href="#" className="list-group-item list-group-item-action py-2" data-mdb-ripple-init><i className="fas fa-money-bill fa-fw me-3" /><span>Sales</span></a>
        </div>
      </div>
    </nav>
    {/* Sidebar */}
    {/* Navbar */}
    <nav id="main-navbar" className="navbar navbar-expand-lg navbar-light bg-white fixed-top">
      {/* Container wrapper */}
      <div className="container-fluid">
        {/* Toggle button */}
        <button className="navbar-toggler" type="button" data-mdb-collapse-init data-mdb-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
          <i className="fas fa-bars" />
        </button>
        {/* Brand */}
        <a className="navbar-brand" href="#">
          <img src="https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png" height={25} loading="lazy" />
        </a>
        {/* Search form */}
        <form className="d-none d-md-flex input-group w-auto my-auto">
          <input autoComplete="off" type="search" className="form-control rounded" placeholder="Search (ctrl + &quot;/&quot; to focus)" style={{minWidth: 225}} />
          <span className="input-group-text border-0"><i className="fas fa-search" /></span>
        </form>
        {/* Right links */}
        <ul className="navbar-nav ms-auto d-flex flex-row">
          {/* Notification dropdown */}
          <li className="nav-item dropdown">
            <a className="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#" id="navbarDropdownMenuLink" role="button" data-mdb-dropdown-init aria-expanded="false">
              <i className="fas fa-bell" />
              <span className="badge rounded-pill badge-notification bg-danger">1</span>
            </a>
            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
              <li><a className="dropdown-item" href="#">Some news</a></li>
              <li><a className="dropdown-item" href="#">Another news</a></li>
              <li>
                <a className="dropdown-item" href="#">Something else</a>
              </li>
            </ul>
          </li>
          {/* Icon */}
          <li className="nav-item">
            <a className="nav-link me-3 me-lg-0" href="#">
              <i className="fas fa-fill-drip" />
            </a>
          </li>
          {/* Icon */}
          <li className="nav-item me-3 me-lg-0">
            <a className="nav-link" href="#">
              <i className="fab fa-github" />
            </a>
          </li>
          {/* Icon dropdown */}
          <li className="nav-item dropdown">
            <a className="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#" id="navbarDropdown" role="button" data-mdb-dropdown-init aria-expanded="false">
              <i className="united kingdom flag m-0" />
            </a>
            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
              <li>
                <a className="dropdown-item" href="#"><i className="united kingdom flag" />English
                  <i className="fa fa-check text-success ms-2" /></a>
              </li>
              <li>
                <hr className="dropdown-divider" />
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="poland flag" />Polski</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="china flag" />中文</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="japan flag" />日本語</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="germany flag" />Deutsch</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="france flag" />Français</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="spain flag" />Español</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="russia flag" />Русский</a>
              </li>
              <li>
                <a className="dropdown-item" href="#"><i className="portugal flag" />Português</a>
              </li>
            </ul>
          </li>
          {/* Avatar */}
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle hidden-arrow d-flex align-items-center" href="#" id="navbarDropdownMenuLink" role="button" data-mdb-dropdown-init aria-expanded="false">
              <img src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg" className="rounded-circle" height={22} loading="lazy" />
            </a>
            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
              <li><a className="dropdown-item" href="#">My profile</a></li>
              <li><a className="dropdown-item" href="#">Settings</a></li>
              <li><a className="dropdown-item" href="#">Logout</a></li>
            </ul>
          </li>
        </ul>
      </div>
      {/* Container wrapper */}
    </nav>
    <main className="col-9">
      {/* Section: Main chart */}
      <section className="mb-4">
        <div className="card">
          <div className="card-header py-3">
            <h5 className="mb-0 text-center"><strong>Sales</strong></h5>
          </div>
          <div className="card-body">
            <Outlet/>
          </div>
        </div>
      </section>
  </main>
</header>
</div>


  )
}

export default AdminComponent