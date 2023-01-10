<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>VTV | Admin - Cấu hình</title>

    <jsp:include page="css.jsp"/>
</head>

<body>

<jsp:include page="header.jsp"/>

<main id="content" role="main" class="main">
    <div class="content container-fluid">
        <div class="page-header">
            <div class="row align-items-end mb-3">
                <div class="col-sm">
                    <h1 class="page-header-title">Cấu hình</h1>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-6">
                <div class="card card-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <div class="media">
                                    <i class="tio-poi nav-icon"></i>
                                    <div class="media-body">
                                        <h4 class="mb-1"><a href="setting_stores">Thông tin cửa hàng</a></h4>
                                        <span class="d-block">Quản lý thông tin liên hệ và
                                                địa chỉ của cửa hàng</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-6">
                <!-- Card -->
                <div class="card card-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <!-- Media -->
                                <div class="media">
                                    <i class="tio-group-senior nav-icon"></i>

                                    <div class="media-body">
                                        <h4 class="mb-1"><a href="list_roles">Phân quyền vai trò</a></h4>
                                        <span class="d-block">Tạo, phân quyền và quản lý các
                                                vai trò của cửa hàng</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-6">
                <!-- Card -->
                <div class="card card-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <!-- Media -->
                                <div class="media">
                                    <i class="tio-label nav-icon"></i>

                                    <div class="media-body">
                                        <h4 class="mb-1"><a href="list_brands">Thương hiệu</a></h4>
                                        <span class="d-block">Tạo và quản lý
                                                thương hiệu trong cửa hàng</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-6">
                <div class="card card-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <div class="media">
                                    <i class="tio-category nav-icon"></i>
                                    <div class="media-body">
                                        <h4 class="mb-1"><a href="list_categories">Thể loại</a></h4>
                                        <span class="d-block">Tạo và quản lý
                                                thể loại trong cửa hàng</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
</main>

<jsp:include page="js.jsp"/>

</body>

</html>
