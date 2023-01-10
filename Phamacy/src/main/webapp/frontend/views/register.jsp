<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="../css/login.css" type="text/css">
</head>

<body>
    <section class="vh-100">
        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <a href="./index.html"><img src="../img/3557325.png" class="img-fluid" alt="logo"></a>
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <form>
                        <h2 class="text-center mb-4">
                            Đăng Ký
                        </h2>
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <input type="text" id="form3Example1" class="form-control form-control-lg"
                                placeholder="Họ" />
                        </div>
                        <div class="form-outline mb-4">
                            <input type="text" id="form3Example2" class="form-control form-control-lg"
                                placeholder="Tên" />
                        </div>
                        <div class="form-outline mb-4">
                            <input type="email" id="form3Example3" class="form-control form-control-lg"
                                placeholder="Email" />
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-3">
                            <input type="password" id="form3Example4" class="form-control form-control-lg"
                                placeholder="Mật khẩu" />
                        </div>
                        <div class="form-outline mb-4">
                            <input type="text" id="form3Example5" class="form-control form-control-lg"
                                placeholder="Số điện thoại" />
                        </div>
                        <div class="form-outline mb-4">
                            <input type="text" id="form3Example6" class="form-control form-control-lg"
                                placeholder="Địa chỉ" />
                        </div>

                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="button" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Đăng ký</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>

</html>