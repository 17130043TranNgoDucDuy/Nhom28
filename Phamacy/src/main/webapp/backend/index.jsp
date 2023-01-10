<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>VTV | Admin - Trang chủ</title>

    <jsp:include page="css.jsp"/>
</head>

<body class="footer-offset">

<jsp:include page="header.jsp"/>

<main id="content" role="main" class="main pointer-event">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <c:if test="${sessionScope.firstName == 'admin'}">
                        <h1 class="page-header-title">Chào mừng <c:out value="${sessionScope.firstName}"/> trở lại trang
                            quản lý VTV</h1>
                    </c:if>
                    <c:if test="${sessionScope.firstName != 'admin'}">
                        <h1 class="page-header-title">Chào mừng <c:out value="${sessionScope.lastName}"/> <c:out
                                value="${sessionScope.firstName}"/> trở lại trang quản lý VTV</h1>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row gx-2 gx-lg-3">
            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                <a class="card card-hover-shadow h-100" href="list_employees">
                    <div class="card-body">
                        <h6 class="card-subtitle text-center">Nhân viên</h6>
                        <div class="card-title h2 text-center">${totalEmployees}</div>
                    </div>
                </a>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                <a class="card card-hover-shadow h-100" href="list_customers">
                    <div class="card-body">
                        <h6 class="card-subtitle text-center">Khách hàng</h6>
                        <div class="card-title h2 text-center">${totalCustomers}</div>
                    </div>
                </a>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                <a class="card card-hover-shadow h-100" href="list_products">
                    <div class="card-body">
                        <h6 class="card-subtitle text-center">Sản phẩm</h6>
                        <div class="card-title h2 text-center">${totalProducts}</div>
                    </div>
                </a>
            </div>

            <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                <a class="card card-hover-shadow h-100" href="list_orders">
                    <div class="card-body">
                        <h6 class="card-subtitle text-center">Đơn hàng</h6>
                        <div class="card-title h2 text-center">${totalOrders}</div>
                    </div>
                </a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
</main>

<jsp:include page="js.jsp"/>

</body>

</html>
