<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <c:if test="${title == null}">
        <title>VTV | Admin - Thêm mới vai trò</title>
    </c:if>

    <c:if test="${title != null}">
        <title>VTV | Admin - ${title}</title>
    </c:if>

    <jsp:include page="css.jsp"/>
</head>

<body class="footer-offset">

<jsp:include page="header.jsp"/>

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <div class="page-header">
            <div class="row align-items-end">
                <div class="col-sm mb-2 mb-sm-0">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb breadcrumb-no-gutter">
                            <li class="breadcrumb-item">
                                <a class="breadcrumb-link" href="list_roles">
                                    <i class="tio-back-ui"></i> Danh sách vai trò</a>
                            </li>
                        </ol>
                    </nav>

                    <c:if test="${title == null}">
                        <h1 class="page-header-title mt-2 ml-3">Thêm mới vai trò</h1>
                    </c:if>
                    <c:if test="${title != null}">
                        <h1 class="page-header-title mt-2 ml-3">${title}</h1>
                    </c:if>

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4 mb-3 mb-lg-0">
                <h4>Chi tiết vai trò</h4>
                <p>Thông tin chi tiết của vai trò để phục vụ cho việc quản lý sau này.</p>
            </div>

            <div class="col-lg-8">

                <c:if test="${role.id != null}">
                <form action="update_role" method="post">
                    <input type="hidden" name="id" value="${role.id}"/>
                    </c:if>

                    <c:if test="${role.id == null}">
                    <form action="create_role" method="post">
                        </c:if>

                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="roleName" class="input-label">Tên vai trò</label>
                                            <input type="text" class="form-control" name="name" id="roleName"
                                                   placeholder="Nhập tên vai trò" value="${role.name}">
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="description" class="input-label">Chú thích</label>
                                            <textarea id="description" cols="30" rows="3" class="form-control"
                                                      name="description"
                                                      id="lastNameAddressLabel">${role.description}</textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary mr-2">Lưu</button>
                                    <a href="list_roles" class="btn btn-danger" role="button"
                                       aria-pressed="true">Hủy</a>
                                </div>
                            </div>
                        </div>
                    </form>
            </div>
        </div>
    </div>

    <jsp:include page="modal_dialog.jsp"/>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
</main>

<jsp:include page="js.jsp"/>

<script>
    function showModalDialog(title, message) {
        $("#modalTitle").text(title);
        $("#modalBody").text(message);
        $("#modalDialog").modal();
    }

    const message = "${message}";
    if (message !== "") {
        showModalDialog("Cảnh báo", message)
    }
</script>

</body>

</html>
