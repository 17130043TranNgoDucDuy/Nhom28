<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <c:if test="${empty title}">
        <title>VTV | Admin - Thêm mới quảng cáo</title>
    </c:if>

    <c:if test="${not empty title}">
        <title>VTV | Admin - ${title}</title>
    </c:if>

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
                        <li class="breadcrumb-item"><a class="breadcrumb-link" href="list_banners"><i
                                class="tio-back-ui"></i>
                            Quản lý quảng cáo</a></li>
                    </ol>
                </nav>

                <c:if test="${empty title}">
                    <h1 class="page-header-title m-auto">Thêm quảng cáo</h1>
                </c:if>
                <c:if test="${not empty title}">
                    <h1 class="page-header-title m-auto">${title}</h1>
                </c:if>
            </div>
        </div>

        <c:if test="${not empty banner.id}">
        <form class="js-step-form py-md-5" action="update_banner" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${banner.id}"/>
            </c:if>

            <c:if test="${empty banner.id}">
            <form class="js-step-form py-md-5" action="create_banner" method="post" enctype="multipart/form-data">
                </c:if>
                <div class="row justify-content-lg-center">
                    <div class="col-lg-8">
                        <a class="step-content-wrapper"></a>

                        <div id="addUserStepFormContent">
                            <div id="addUserStepProfile" class="card card-lg active">
                                <div class="card-body">
                                    <div class="row form-group">
                                        <h1 class="m-auto">Thông tin quảng cáo</h1>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Ảnh đại diện:</label>
                                        <div class="col-sm-8">
                                            <input type="file" id="avatarUploader" class="mb-2" name="image"
                                            ${banner.id != null ? '' : 'required'}/>
                                            <c:choose>
                                                <c:when test="${not empty banner.imagePath}">
                                                    <img id="avatarImg"
                                                         src="../images/banner/${banner.id}/${banner.imagePath}"
                                                         alt="Photos preview" class="img-fluid" style="width: 40%"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img id="avatarImg" src="../images/employee/default.jpg"
                                                         alt="Photos preview" class="img-fluid" style="width: 40%"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="nameLabel"
                                               class="col-sm-3 col-form-label input-label">Tiêu đề</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="name" id="nameLabel"
                                                   value="${banner.name}" required>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <label class="col-sm-3 col-form-label input-label" for="enabled">
                                            Hoạt động
                                        </label>
                                        <div class="col-sm-9 pt-1">
                                            <c:choose>
                                                <c:when test="${not empty banner}">
                                                    <c:if test="${banner.enabled}">
                                                        <input type="checkbox" class="mt-2" name="enabled" id="enabled"
                                                               checked>
                                                    </c:if>
                                                    <c:if test="${not banner.enabled}">
                                                        <input type="checkbox" class="mt-2" name="enabled" id="enabled">
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" class="mt-2" name="enabled" id="enabled"
                                                           checked>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="card-footer d-sm-flex align-items-sm-center">
                                <div class="ml-auto">
                                    <button type="button" class="btn btn-white mr-2" id="btnCancel">Hủy</button>
                                    <button type="submit" class="btn btn-primary">Thêm</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
    </div>

    <jsp:include page="footer.jsp"/>
</main>

<jsp:include page="modal_dialog.jsp"/>

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

    function showModalDialog(title, message) {
        $("#modalTitle").text(title);
        $("#modalBody").text(message);
        $("#modalDialog").modal();
    }

    const message = "${message}";
    if (message !== "") {
        showModalDialog("Warning", message)
    }

    $(document).ready(function () {
        $("#btnCancel").on("click", function () {
            window.location = "list_banners";
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
