<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>VTV | Admin - Danh sách nhân viên</title>

    <jsp:include page="css.jsp"/>
</head>

<body class="footer-offset">

<jsp:include page="header.jsp"/>

<main id="content" role="main" class="main">
    <div class="content container-fluid">
        <div class="page-header">
            <div class="col-sm mb-2 mb-sm-0">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb breadcrumb-no-gutter">
                        <li class="breadcrumb-item">
                            <a class="breadcrumb-link" href="setting_stores">
                                <i class="tio-back-ui"></i> Cấu hình</a>
                        </li>
                    </ol>
                </nav>
            </div>
            <h1 class="page-header-title mt-2 ml-3">Thông tin cửa hàng</h1>
        </div>

        <form action="setting_stores" method="post" enctype="multipart/form-data">
            <div class="row justify-content-lg-center">
                <div class="col-lg-9">
                    <div class="card card-lg mb-3 mb-lg-5">
                        <div class="card-body">
                            <div class="form-group">
                                <label class="input-label">Logo</label>

                                <div class="d-flex align-items-center">
                                    <input type="file" id="avatarUploader" class="mb-2" name="image"/>
                                    <img id="avatarImg"
                                         src="../images/setting/logo/${SITE_LOGO}"
                                         alt="Photos preview" class="img-fluid" style="width: 30%"/>
                                </div>
                            </div>
                            <!-- End Form Group -->

                            <div class="form-group">
                                <label class="input-label">
                                    Tên công ty
                                    <i class="tio-help-outlined text-body ml-1" data-toggle="tooltip"
                                       data-placement="top"
                                       title="Hiển thị tên công ty ở shop."></i>
                                </label>

                                <div class="input-group input-group-merge">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="tio-briefcase-outlined"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="SITE_NAME"
                                           placeholder="Nhập vào tên công ty" value="${SITE_NAME}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="input-label">
                                    Địa chỉ
                                    <i class="tio-help-outlined text-body ml-1" data-toggle="tooltip"
                                       data-placement="top" title="Hiển thị địa chỉ ở shop."></i>
                                </label>

                                <div class="input-group input-group-merge">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="tio-briefcase-outlined"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="SITE_ADDRESS"
                                           placeholder="Nhập vào địa chỉ" value="${SITE_ADDRESS}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="input-label">
                                    Số điện thoại
                                    <i class="tio-help-outlined text-body ml-1" data-toggle="tooltip"
                                       data-placement="top" title="Hiển thị số điện thoại ở shop."></i>
                                </label>

                                <div class="input-group input-group-merge">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="tio-briefcase-outlined"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="SITE_PHONE"
                                           placeholder="Nhập vào số điện thoại"
                                           value="${SITE_PHONE}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="input-label">Email cskh
                                    <i class="tio-help-outlined text-body ml-1" data-toggle="tooltip"
                                       data-placement="top" title="Hiển thị email cskh ở shop."></i>
                                </label>

                                <div class="input-group input-group-merge">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">
                                            <i class="tio-briefcase-outlined"></i>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" name="SITE_EMAIL"
                                           placeholder="Nhập vào email cskh"
                                           value="${SITE_EMAIL}">
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-end align-items-center">
                                <button type="submit" class="btn btn-primary mr-2">Lưu</button>
                                <button id="btnCancel" type="button" class="btn btn-danger">Hủy</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <jsp:include page="footer.jsp"/>
</main>

<jsp:include page="js.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        // check image file size
        $("#avatarUploader").change(function () { // is executed every time the event is fired
            if (!checkFileSize(this)) {
                return;
            }

            showImageThumbnail(this);
        });
    });

    $(document).ready(function () {
        $("#btnCancel").on("click", function () {
            window.location = "setting.jsp";
        });
    });

    function showImageThumbnail(fileInput) {
        var file = fileInput.files[0];

        var reader = new FileReader();

        reader.onload = function (e) {
            $("#avatarImg").attr("src", e.target.result);
        };

        reader.readAsDataURL(file);
    }

    function checkFileSize(fileInput) {
        fileSize = fileInput.files[0].size; // returns the selected file and can get the name or size
        var mb = 1024 * 1024; // 1mb = 1024 x 1024 kb

        if (fileSize > mb) {
            fileInput.setCustomValidity("Bạn phải chọn ảnh nhỏ hơn 1MB !");
            fileInput.reportValidity();

            return false;
        } else {
            fileInput.setCustomValidity("");

            return true;
        }
    }
</script>

</body>

</html>
