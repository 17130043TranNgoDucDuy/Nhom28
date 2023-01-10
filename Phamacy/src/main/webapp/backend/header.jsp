<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="styleSwitcherDropdown" class="hs-unfold-content sidebar sidebar-bordered sidebar-box-shadow"
     style="width: 35rem;">
    <div class="card card-lg border-0 h-100">
        <div class="card-footer">
            <div class="row gx-2">
                <div class="col">
                    <button type="button" id="js-builder-reset" class="btn btn-block btn-lg btn-white">
                        <i class="tio-restore"></i> Reset
                    </button>
                </div>
                <div class="col">
                    <button type="button" id="js-builder-preview" class="btn btn-block btn-lg btn-primary">
                        <i class="tio-visible"></i> Preview
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- thanh top --%>
<div id="headerMain" class="d-none">
    <header id="header"
            class="navbar navbar-expand-lg navbar-fixed navbar-height navbar-flush navbar-container navbar-bordered">
        <div class="navbar-nav-wrap">
            <div class="navbar-brand-wrapper">
                <a class="navbar-brand" href="/motorbike_shop/backend/" aria-label="Front">
                    <img class="navbar-brand-logo" src="../images/logo/logo.png" alt="Logo">
                    <img class="navbar-brand-logo-mini" src="../images/logo/logo.png" alt="Logo">
                </a>
            </div>

            <div class="navbar-nav-wrap-content-left">
                <button type="button" class="js-navbar-vertical-aside-toggle-invoker close mr-3">
                    <i class="tio-first-page navbar-vertical-aside-toggle-short-align" data-toggle="tooltip"
                       data-placement="right" title="Thu nhỏ"></i>
                    <i class="tio-last-page navbar-vertical-aside-toggle-full-align"
                       data-template='<div class="tooltip d-none d-sm-block" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>'
                       data-toggle="tooltip" data-placement="right" title="Mở rộng"></i>
                </button>

                <div class="d-none d-md-block">
                    <form class="position-relative">
                        <div class="input-group input-group-merge input-group-borderless input-group-hover-light
                        navbar-input-group mt-3">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <i class="tio-search"></i>
                                </div>
                            </div>
                            <input type="search" class="js-form-search form-control" placeholder="Tìm kiếm...">
                        </div>
                    </form>
                </div>
            </div>

            <div class="navbar-nav-wrap-content-right">
                <ul class="navbar-nav align-items-center flex-row">
                    <li class="nav-item d-md-none">
                        <div class="hs-unfold">
                            <a class="js-hs-unfold-invoker btn btn-icon btn-ghost-secondary rounded-circle">
                                <i class="tio-search"></i>
                            </a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="hs-unfold">
                            <a class="js-hs-unfold-invoker navbar-dropdown-account-wrapper"
                               data-hs-unfold-options='{
                 "target": "#accountNavbarDropdown",
                 "type": "css-animation"
               }'>
                                <div class="avatar avatar-sm avatar-circle">
                                    <c:if test="${empty sessionScope.imagePath}">
                                        <div class="avatar avatar-soft-dark avatar-circle">
                                            <span class="avatar-initials">${fn:substring(sessionScope.firstName, 0, 1)}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.imagePath}">
                                        <img class="avatar-img"
                                             src="../images/employee/${sessionScope.id}/${sessionScope.imagePath}"
                                             alt="Image Description">
                                    </c:if>
                                    <span class="avatar-status avatar-sm-status avatar-status-success"></span>
                                </div>
                            </a>

                            <div id="accountNavbarDropdown"
                                 class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-right navbar-dropdown-menu navbar-dropdown-account"
                                 style="width: 16rem;">
                                <div class="dropdown-item-text">
                                    <div class="media align-items-center">
                                        <div class="avatar avatar-sm avatar-dark avatar-circle mr-2">
                                            <c:if test="${empty sessionScope.imagePath}">
                                                <div class="avatar avatar-soft-dark avatar-circle">
                                                    <span class="avatar-initials">${fn:substring(sessionScope.firstName, 0, 1)}</span>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty sessionScope.imagePath}">
                                                <img class="avatar-img"
                                                     src="../images/employee/${sessionScope.id}/${sessionScope.imagePath}"
                                                     alt="Image Description">
                                            </c:if>
                                        </div>

                                        <div class="media-body">
                                            <c:if test="${sessionScope.firstName == 'admin'}">
                                                <span class="card-title h5">
                                                    <c:out value="${sessionScope.lastName}"/>
                                                </span>
                                            </c:if>
                                            <c:if test="${sessionScope.firstName != 'admin'}">
                                                <c:out value="${sessionScope.lastName}"/> <c:out
                                                    value="${sessionScope.firstName}"/>
                                            </c:if>

                                            <span class="card-text"><c:out value="${sessionScope.email}"/></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">
                                    <span class="text-truncate pr-2"
                                          title="Profile &amp; account">Hồ Sơ Tài Khoản</span>
                                </a>
                                <a class="dropdown-item" href="logout">
                                    <span class="text-truncate pr-2" title="Sign out">Đăng Xuất</span>
                                </a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </header>
</div>

<div id="headerFluid" class="d-none"></div>
<div id="headerDouble" class="d-none"></div>

<!-- Thanh menu bên cạnh -->
<div id="sidebarMain" class="d-none">
    <aside
            class="js-navbar-vertical-aside navbar navbar-vertical-aside navbar-vertical navbar-vertical-fixed navbar-expand-xl navbar-bordered  ">
        <div class="navbar-vertical-container">
            <div class="navbar-vertical-footer-offset">
                <div class="navbar-brand-wrapper justify-content-between">
                    <a class="navbar-brand" href="/motorbike_shop/backend/" aria-label="Front">
                        <img class="navbar-brand-logo" src="../images/logo/logo.png" alt="Logo">
                    </a>

                    <button type="button"
                            class="js-navbar-vertical-aside-toggle-invoker navbar-vertical-aside-toggle btn btn-icon btn-xs btn-ghost-dark">
                        <i class="tio-clear tio-lg"></i>
                    </button>
                </div>

                <div class="navbar-vertical-content">
                    <ul class="navbar-nav navbar-nav-lg nav-tabs">
                        <li class="navbar-vertical-aside-has-menu">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="/motorbike_shop/backend/"
                               title="Dashboards">
                                <i class="tio-home-vs-1-outlined nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Trang
                    chủ</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_employees"
                               title="Nhân viên">
                                <i class="tio-users-switch nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Nhân
                    viên</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_customers"
                               title="Khách hàng">
                                <i class="tio-user-big-outlined nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Khách
                    hàng</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_banners"
                               title="Quảng cáo">
                                <i class="tio-panorama-image nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Quảng
                    cáo</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_products"
                               title="Quảng cáo">
                                <i class="tio-motocycle nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Sản phẩm</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_orders"
                               title="Đơn hàng">
                                <i class="tio-receipt-outlined nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Đơn
                    hàng</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_reviews"
                               title="Đánh giá">
                                <i class="tio-comment-vs-outlined nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Đánh
                    giá</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="list_statistics"
                               title="Thống kê">
                                <i class="tio-chart-bar-1 nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Thống
                    kê</span>
                            </a>
                        </li>

                        <li class="navbar-vertical-aside-has-menu ">
                            <a class="js-navbar-vertical-aside-menu-link nav-link" href="setting.jsp"
                               title="Cấu hình">
                                <i class="tio-settings nav-icon"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Cấu
                    hình</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </aside>
</div>

<div id="sidebarCompact" class="d-none"></div>
