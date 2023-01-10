<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Title -->
  <c:if test="${empty title}">
    <title>VTV | Admin - Thêm mới thể loại</title>
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
            <li class="breadcrumb-item"><a class="breadcrumb-link" href="list_categories"><i
                    class="tio-back-ui"></i>
              Quản lý thể loại</a></li>
          </ol>
        </nav>

        <c:if test="${empty title}">
          <h1 class="page-header-title m-auto">Thêm thể loại</h1>
        </c:if>
        <c:if test="${not empty title}">
          <h1 class="page-header-title m-auto">${title}</h1>
        </c:if>
      </div>
    </div>

    <c:if test="${not empty category.id}">
    <form class="js-step-form py-md-5" action="update_category" method="post">
      <input type="hidden" name="id" value="${category.id}"/>
      </c:if>

      <c:if test="${empty category.id}">
      <form class="js-step-form py-md-5" action="create_category" method="post">
        </c:if>
        <div class="row justify-content-lg-center">
          <div class="col-lg-8">
            <a class="step-content-wrapper"></a>

            <div id="addUserStepFormContent">
              <div id="addUserStepProfile" class="card card-lg active">
                <div class="card-body">
                  <div class="row form-group">
                    <h1 class="m-auto">Thông tin thể loại</h1>
                  </div>

                  <div class="row form-group">
                    <label for="nameLabel"
                           class="col-sm-3 col-form-label input-label">Tên thể loại</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="name" id="nameLabel"
                             value="${category.name}" required>
                    </div>
                  </div>

                  <div class="row form-group">
                    <label for="aliasLabel"
                           class="col-sm-3 col-form-label input-label">Tên bí danh</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="alias" id="aliasLabel"
                             value="${category.alias}"
                             placeholder="Mặc định khoảng cách được thay thế bằng dấu -">
                    </div>
                  </div>

                  <div class="row form-group">
                    <label for="aliasLabel"
                           class="col-sm-3 col-form-label input-label">Thể loại</label>

                    <div class="col-sm-9">
                      <select class="form-control" name="parent">
                        <option value="0" ${category.parentId == 0 ? 'selected' : ''}>
                          [Root]
                        </option>
                        <c:forEach items="${allCategory}" var="c">
                          <c:if test="${c.parentId == 0}">
                            <option value="${c.id}" ${c.id == category.parentId ? 'selected' : ''}>
                                ${c.name}
                            </option>
                          </c:if>
                          <c:if test="${c.parentId != 0}">
                            <option value="${c.id}" ${c.id == category.parentId ? 'selected' : ''}>
                              &nbsp;--${c.name}
                            </option>
                          </c:if>
                        </c:forEach>
                      </select>
                    </div>
                  </div>

                  <div class="row">
                    <label class="col-sm-3 col-form-label input-label" for="enabled">
                      Hoạt động
                    </label>
                    <div class="col-sm-9 pt-1">
                      <c:choose>
                        <c:when test="${not empty category}">
                          <c:if test="${category.enabled}">
                            <input type="checkbox" class="mt-2" name="enabled" id="enabled"
                                   checked>
                          </c:if>
                          <c:if test="${not category.enabled}">
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
      window.location = "list_categories";
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
