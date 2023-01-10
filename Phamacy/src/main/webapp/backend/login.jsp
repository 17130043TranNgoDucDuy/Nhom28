<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>

    <title>VTV | Admin - Đăng nhập</title>

    <link rel="stylesheet" href="assets\css\bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="assets\css\login.css" type="text/css">

</head>

<body>

<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <span><img src="../images/logo/logo-login.png" class="img-fluid" alt="logo"></span>
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="login" method="post">
                    <h2 class="text-center mb-4">
                        Đăng Nhập
                    </h2>

                    <c:if test="${message != null}">
                        <span class="text-center text-danger">${message}</span>
                    </c:if>

                    <div class="form-outline mb-4">
                        <input type="email" class="form-control form-control-lg" placeholder="Email" name="email"
                               required/>
                    </div>

                    <div class="form-outline mb-3">
                        <input type="password" class="form-control form-control-lg" name="password"
                               placeholder="Mật khẩu" required/>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3"/>
                            <label class="form-check-label" for="form2Example3">
                                Nhớ mật khẩu
                            </label>
                        </div>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Đăng nhập
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script src="assets/js/jquery-3.3.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

</body>

</html>
