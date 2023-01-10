<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
            <div class="row align-items-end">
                <div class="col-sm mb-2 mb-sm-0">

                    <h1 class="page-header-title">Quản lý nhân viên<span class="badge badge-soft-dark ml-2">
                        ${numberOfEmployees}</span></h1>
                </div>

                <div class="col-sm-auto">
                    <a class="btn btn-primary" href="create_employee">
                        <i class="tio-user-add mr-1"></i> Thêm nhân viên
                    </a>
                </div>
            </div>
        </div>

        <c:if test="${message != null}">
        <div class="alert alert-success text-center" id="hideMessage">${message}</div>
        </c:if>

        <div class="card">
            <div class="card-header">
                <div class="row justify-content-between align-items-center flex-grow-1">
                    <div class="col-sm-6 col-md-4 mb-3 mb-sm-0">
                        <form action="list_employees" method="get">
                            <!-- Search -->
                            <div class="input-group input-group-merge input-group-flush">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tio-search"></i>
                                    </div>
                                </div>
                                <input type="search" class="form-control" placeholder="Tìm kiếm nhân viên"
                                       name="keyword" value="${keyword}">
                            </div>
                        </form>
                    </div>

                    <div class="col-sm-6">
                        <div class="d-sm-flex justify-content-sm-end align-items-sm-center">
                            <!-- Unfold -->
                            <div class="hs-unfold mr-2">
                                <a class="js-hs-unfold-invoker btn btn-sm btn-white dropdown-toggle"
                                   href="" data-hs-unfold-options='{
                         "target": "#usersExportDropdown",
                         "type": "css-animation"
                       }'>
                                    <i class="tio-download-to mr-1"></i> Xuất file
                                </a>

                                <div id="usersExportDropdown"
                                     class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-sm-right">
                                    <span class="dropdown-header">Lựa chọn</span>
                                    <a id="export-copy" class="dropdown-item" href="">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/copy.svg" alt="Image Description">
                                        Sao chép
                                    </a>
                                    <a id="export-print" class="dropdown-item" href="">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/print.svg" alt="Image Description">
                                        In
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <span class="dropdown-header">Tải file</span>
                                    <a id="export-excel" class="dropdown-item" href="">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/excel.svg" alt="Image Description">
                                        Excel
                                    </a>
                                    <a id="export-csv" class="dropdown-item" href="">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/placeholder-csv-format.svg" alt="Image Description">
                                        .CSV
                                    </a>
                                    <a id="export-pdf" class="dropdown-item" href="">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2" src="../images/user/pdf.svg"
                                             alt="Image Description">
                                        PDF
                                    </a>
                                </div>
                            </div>

                            <div class="hs-unfold">
                                <a class="js-hs-unfold-invoker btn btn-sm btn-white" href=""
                                   data-hs-unfold-options='{
                         "target": "#usersFilterDropdown",
                         "type": "css-animation",
                         "smartPositionOff": true
                       }'>
                                    <i class="tio-filter-list mr-1"></i> Lọc
                                    <span class="badge badge-soft-dark rounded-circle ml-1">1</span>
                                </a>

                                <div id="usersFilterDropdown"
                                     class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-sm-right dropdown-card card-dropdown-filter-centered"
                                     style="min-width: 22rem;">
                                    <!-- Card -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-header-title">Lọc theo vai trò</h5>

                                            <!-- Toggle Button -->
                                            <a class="js-hs-unfold-invoker btn btn-icon btn-xs btn-ghost-secondary ml-2"
                                               href="" data-hs-unfold-options='{
                              "target": "#usersFilterDropdown",
                              "type": "css-animation",
                              "smartPositionOff": true
                             }'>
                                                <i class="tio-clear tio-lg"></i>
                                            </a>
                                        </div>

                                        <div class="card-body">
                                            <form>
                                                <div class="form-row">
                                                    <div class="col-sm form-group">
                                                        <small class="text-cap mb-2">Vai trò</small>
                                                        <select
                                                                class="js-select2-custom js-datatable-filter custom-select"
                                                                size="1" data-target-column-index="5"
                                                                data-hs-select2-options='{
                                          "minimumResultsForSearch": "Infinity"
                                        }'>
                                                            <option value="">Chọn vai trò</option>
                                                            <option value="Quản trị viên">Quản trị viên</option>
                                                            <option value="Nhân viên">Nhân viên</option>
                                                        </select>
                                                    </div>

                                                </div>

                                                <a class="js-hs-unfold-invoker btn btn-block btn-primary"
                                                   href="" data-hs-unfold-options='{
                                "target": "#usersFilterDropdown",
                                "type": "css-animation",
                                "smartPositionOff": true
                               }'>Áp dụng</a>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="table-responsive datatable-custom">
                <table id="datatable"
                       class="table table-lg table-borderless table-thead-bordered table-nowrap table-align-middle card-table"
                       data-hs-datatables-options='{
                     "columnDefs": [{
                        "targets": [0, 6],
                        "orderable": false
                      }],
                     "order": [],
                     "info": {
                       "totalQty": "#datatableWithPaginationInfoTotalQty"
                     },
                     "search": "#datatableSearch",
                     "entries": "#datatableEntries",
                     "pageLength": 5,
                     "isResponsive": false,
                     "isShowPaging": false,
                     "pagination": "datatablePagination"
                   }'>
                    <thead class="thead-light">
                    <tr>
                        <th>Tên</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Vai trò</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:if test="${empty result}">
                        <c:forEach var="employees" items="${listEmployees}">
                            <c:if test="${employees.id == 1}">
                                <tr>
                                    <td>
                                        <a class="d-flex align-items-center">
                                            <div class="avatar avatar-soft-dark avatar-circle">
                                                <span class="avatar-initials">O</span>
                                            </div>
                                            <div class="ml-3">
                                            <span class="d-block h5 text-hover-primary mb-0">
                                                 ${employees.firstName}
                                                <i class="tio-verified text-primary" data-toggle="tooltip"
                                                   data-placement="top"></i>
                                            </span>
                                                <span class="d-block font-size-sm text-body">${employees.email}</span>
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <span class="d-block font-size-sm">${employees.phoneNumber}</span>
                                    </td>
                                    <td>
                                        <span></span>
                                    </td>
                                    <td>${employees.roles}</td>
                                    <td>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${employees.id != 1}">
                                <tr>
                                    <td>
                                        <a class="d-flex align-items-center" href="edit_employee?id=${employees.id}">

                                            <c:if test="${empty employees.imagePath}">
                                                <div class="avatar avatar-soft-dark avatar-circle">
                                                <span class="avatar-initials">
                                                        ${fn:substring(employees.firstName, 0, 1)}
                                                </span>
                                                </div>
                                            </c:if>

                                            <c:if test="${not empty employees.imagePath}">
                                                <div class="avatar avatar-circle">
                                                    <img class="avatar-img"
                                                         src="../images/employee/${employees.id}/${employees.imagePath}"
                                                         alt="Image Description">
                                                </div>
                                            </c:if>

                                            <div class="ml-3">
                                            <span class="d-block h5 text-hover-primary mb-0">
                                                        ${employees.lastName} ${employees.firstName}
                                            </span>
                                                <span class="d-block font-size-sm text-body">${employees.email}</span>
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <span class="d-block font-size-sm">${employees.phoneNumber}</span>
                                    </td>
                                    <td>
                                    <span class="d-block font-size-sm">
                                        ${employees.address.street},
                                        ${employees.address.ward}, ${employees.address.district},
                                        ${employees.address.city}
                                    </span>
                                    </td>
                                    <td>${employees.roles}</td>
                                    <td>
                                        <c:if test="${employees.enabled}">
                                            <a class="fas fa-check-circle fa-2x icon-green"
                                               href="enable_employee?enabled=false&id=${employees.id}"
                                               title="Vô hiệu hoá">
                                            </a>
                                        </c:if>
                                        <c:if test="${!employees.enabled}">
                                            <a class="fas fa-check-circle fa-2x icon-gray"
                                               href="enable_employee?enabled=true&id=${employees.id}" title="Kích hoạt">
                                            </a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a class="btn btn-sm btn-white" href="edit_employee?id=${employees.id}">
                                                <i class="tio-edit"></i> Chỉnh sửa
                                            </a>
                                            <a class="btn btn-sm btn-white link-delete"
                                               href="delete_employee?id=${employees.id}" entityId="${employees.id}">
                                                <i class="tio-delete"></i> Xóa
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty result}">
                        <c:forEach var="employees" items="${result}">
                            <c:if test="${employees.id == 1}">
                                <tr>
                                    <td>
                                        <a class="d-flex align-items-center">
                                            <div class="avatar avatar-soft-dark avatar-circle">
                                                <span class="avatar-initials">O</span>
                                            </div>
                                            <div class="ml-3">
                                            <span class="d-block h5 text-hover-primary mb-0">
                                                 ${employees.firstName}
                                                <i class="tio-verified text-primary" data-toggle="tooltip"
                                                   data-placement="top"></i>
                                            </span>
                                                <span class="d-block font-size-sm text-body">${employees.email}</span>
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <span class="d-block font-size-sm">${employees.phoneNumber}</span>
                                    </td>
                                    <td>
                                        <span></span>
                                    </td>
                                    <td>${employees.roles}</td>
                                    <td>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${employees.id != 1}">
                                <tr>
                                    <td>
                                        <a class="d-flex align-items-center" href="edit_employee?id=${employees.id}">

                                            <c:if test="${empty employees.imagePath}">
                                                <div class="avatar avatar-soft-dark avatar-circle">
                                                <span class="avatar-initials">
                                                        ${fn:substring(employees.firstName, 0, 1)}
                                                </span>
                                                </div>
                                            </c:if>

                                            <c:if test="${not empty employees.imagePath}">
                                                <div class="avatar avatar-circle">
                                                    <img class="avatar-img"
                                                         src="../images/employee/${employees.id}/${employees.imagePath}"
                                                         alt="Image Description">
                                                </div>
                                            </c:if>

                                            <div class="ml-3">
                                            <span class="d-block h5 text-hover-primary mb-0">
                                                        ${employees.lastName} ${employees.firstName}
                                            </span>
                                                <span class="d-block font-size-sm text-body">${employees.email}</span>
                                            </div>
                                        </a>
                                    </td>
                                    <td>
                                        <span class="d-block font-size-sm">${employees.phoneNumber}</span>
                                    </td>
                                    <td>
                                    <span class="d-block font-size-sm">
                                        ${employees.address.street},
                                        ${employees.address.ward}, ${employees.address.district},
                                        ${employees.address.city}
                                    </span>
                                    </td>
                                    <td>${employees.roles}</td>
                                    <td>
                                        <c:if test="${employees.enabled}">
                                            <a class="fas fa-check-circle fa-2x icon-green"
                                               href="enable_employee?enabled=false&id=${employees.id}"
                                               title="Vô hiệu hoá">
                                            </a>
                                        </c:if>
                                        <c:if test="${!employees.enabled}">
                                            <a class="fas fa-check-circle fa-2x icon-gray"
                                               href="enable_employee?enabled=true&id=${employees.id}" title="Kích hoạt">
                                            </a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a class="btn btn-sm btn-white" href="edit_employee?id=${employees.id}">
                                                <i class="tio-edit"></i> Chỉnh sửa
                                            </a>
                                            <a class="btn btn-sm btn-white link-delete"
                                               href="delete_employee?id=${employees.id}" entityId="${employees.id}">
                                                <i class="tio-delete"></i> Xóa
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
            </div>

            <c:if test="${totalPages > 0}">
                <div class="card-footer">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination float-right">
                            <li class="${currentPage > 1 ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link"
                                   href="list_employees?pageNumber=${currentPage - 1}&keyword=${keyword}">Trước</a>
                            </li>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="${currentPage != i ? 'page-item' : 'page-item active'}">
                                    <a class="page-link"
                                       href="list_employees?pageNumber=${i}&keyword=${keyword}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="${currentPage < totalPages ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link"
                                   href="list_employees?pageNumber=${currentPage + 1}&keyword=${keyword}">Sau</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </c:if>
        </div>

        <jsp:include page="footer.jsp"/>

        <jsp:include page="confirm-modal.jsp"/>
</main>

<jsp:include page="js.jsp"/>

<script type="text/javascript">
    const message = document.getElementById('hideMessage');

    if (message !== null) {
        function hideMessage() {
            message.style.display = 'none';
        }

        setTimeout(hideMessage, 5000);
    }

    function showDeleteConfirmModal(link) {
        entityId = link.attr("entityId");
        $("#yesButton").attr("href", link.attr("href")); // set value of attribute
        $("#confirmText").text("Bạn có muốn xoá nhân viên không ?");
        $("#confirmModal").modal();
    }

    $(document).ready(function () {
        $(".link-delete").on("click", function (e) {
            e.preventDefault();
            showDeleteConfirmModal($(this));
        });
    });
</script>

</body>

</html>
