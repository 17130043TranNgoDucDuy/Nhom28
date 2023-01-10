<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <c:if test="${empty title}">
        <title>VTV | Admin - Thêm mới sản phẩm</title>
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
                        <li class="breadcrumb-item"><a class="breadcrumb-link" href="list_products"><i
                                class="tio-back-ui"></i>
                            Quản lý sản phẩm</a></li>
                    </ol>
                </nav>

                <c:if test="${empty title}">
                    <h1 class="page-header-title m-auto">Thêm sản phẩm</h1>
                </c:if>
                <c:if test="${not empty title}">
                    <h1 class="page-header-title m-auto">${title}</h1>
                </c:if>
            </div>
        </div>

        <c:if test="${not empty product.id}">
        <form class="js-step-form py-md-5" action="update_product" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}"/>
            </c:if>

            <c:if test="${empty product.id}">
            <form class="js-step-form py-md-5" action="create_product" method="post" enctype="multipart/form-data">
                </c:if>
                <div class="row justify-content-lg-center">
                    <div class="col-lg-8">
                        <a class="step-content-wrapper"></a>

                        <div id="addUserStepFormContent">
                            <div id="addUserStepProfile" class="card card-lg active">
                                <div class="card-body">
                                    <div class="row form-group">
                                        <h1 class="m-auto">Thông tin sản phẩm</h1>
                                    </div>

                                    <div class="row form-group">
                                        <label class="col-sm-3 col-form-label input-label">Thể loại</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="category">
                                                <c:forEach items="${categories}" var="c">
                                                    <option value="${c.id}" ${c.id == product.category.id ? 'selected' : ''}>
                                                            ${c.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label class="col-sm-3 col-form-label input-label">Thương hiệu</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="brand">
                                                <c:forEach items="${brands}" var="b">
                                                    <option value="${b.id}" ${b.id == product.brand.id ? 'selected' : ''}>
                                                            ${b.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="nameLabel"
                                               class="col-sm-3 col-form-label input-label">Tên</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="name" id="nameLabel"
                                                   value="${product.name}" required>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="aliasLabel"
                                               class="col-sm-3 col-form-label input-label">Bí danh</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="alias" id="aliasLabel"
                                                   value="${product.alias}"
                                                   placeholder="Mặc định khoảng cách được thay thế bằng dấu -">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Ảnh sản phẩm:</label>
                                        <div class="col-sm-8">
                                            <input type="file" id="avatarUploader" class="mb-2"
                                                   name="image" ${product.id != null ? '' : 'required'}/>
                                            <c:choose>
                                                <c:when test="${not empty product.mainImagePath}">
                                                    <img id="avatarImg"
                                                         src="../images/product/${product.id}/${product.mainImagePath}"
                                                         alt="Photos preview" class="img-fluid" style="width: 40%"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img id="avatarImg" src="../images/default.jpg"
                                                         alt="Photos preview" class="img-fluid" style="width: 40%"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label class="col-sm-3 col-form-label input-label">Mô tả</label>
                                        <div class="col-sm-9">
                                            <textarea name="description" rows="4" cols="55" required>
                                                ${product.description}
                                            </textarea>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="priceLabel"
                                               class="col-sm-3 col-form-label input-label">Giá</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="price" id="priceLabel"
                                                   value="${product.price}" required>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="discountLabel"
                                               class="col-sm-3 col-form-label input-label">Giảm giá</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="percentDiscount"
                                                   id="discountLabel"
                                                   value="${product.percentDiscount}"
                                                   placeholder="Nhập % giá giảm nếu có" required>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <label for="quantityLabel" class="col-sm-3 col-form-label input-label">Số
                                            lượng</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="quantity" id="quantityLabel"
                                                   value="${product.quantity}" required>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <label class="col-sm-3 col-form-label input-label">Trạng thái</label>
                                        <div class="col-sm-9 pt-1">
                                            <c:if test="${empty product.id}">
                                                <c:forEach var="s" items="${statuses}">
                                                    <input type="checkbox" class="mt-2" name="statuses"
                                                           value="${s.id}" ${s.id == 2 ? 'checked' : ''}> ${s.name}
                                                    <br>
                                                </c:forEach>
                                            </c:if>

                                            <c:if test="${not empty product.id}">
                                                <c:forEach var="status" items="${statusMap}">
                                                    <input type="checkbox" class="mt-2" name="statuses"
                                                           value="${status.key.id}"
                                                        ${status.value}> ${status.key.name}
                                                    <br>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card-footer d-sm-flex align-items-sm-center">
                            <div class="ml-auto">
                                <button type="button" class="btn btn-white mr-2" id="btnCancel">Hủy</button>
                                <button id="addUserFinishBtn" type="submit" class="btn btn-primary">Thêm</button>
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
            window.location = "list_products";
        });
    });

    function showImageThumbnail(fileInput) {
        let file = fileInput.files[0];

        let reader = new FileReader();

        reader.onload = function (e) {
            $("#avatarImg").attr("src", e.target.result);
        };

        reader.readAsDataURL(file);
    }

    function checkFileSize(fileInput) {
        fileSize = fileInput.files[0].size; // returns the selected file and can get the name or size
        let mb = 1024 * 1024; // 1mb = 1024 x 1024 kb

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
