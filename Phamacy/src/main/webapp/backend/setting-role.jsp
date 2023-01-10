<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <!-- Required Meta Tags Always Come First -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>VTV | Admin - Dánh sách vai trò</title>

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
                                <a class="breadcrumb-link" href="setting.jsp">
                                    <i class="tio-back-ui"></i> Cấu hình</a>
                            </li>
                        </ol>
                    </nav>
                    <h1 class="page-header-title mt-2 ml-3">Danh sách vai trò</h1>
                </div>

                <div class="col-sm-auto">
                    <a class="btn btn-primary" href="setting-role-form.jsp">
                        <i class="tio-user-add mr-1"></i> Thêm mới vai trò
                    </a>
                </div>
            </div>
        </div>

        <c:if test="${message != null}">
            <div class="alert alert-success text-center" id="hideMessage">${message}</div>
        </c:if>

        <!-- Card -->
        <div class="card">
            <!-- Header -->
            <div class="card-header">
                <div class="row justify-content-between align-items-center flex-grow-1">
                    <div class="col-sm-6 col-md-4 mb-3 mb-sm-0">
                        <form>
                            <!-- Search -->
                            <div class="input-group input-group-merge input-group-flush">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tio-search"></i>
                                    </div>
                                </div>
                                <input id="datatableSearch" type="search" class="form-control"
                                       placeholder="Tìm kiếm" aria-label="Tìm kiếm">
                            </div>
                            <!-- End Search -->
                        </form>
                    </div>

                    <div class="col-sm-6">
                        <div class="d-sm-flex justify-content-sm-end align-items-sm-center">
                            <!-- Datatable Info -->
                            <div id="datatableCounterInfo" class="mr-2 mb-2 mb-sm-0" style="display: none;">
                                <div class="d-flex align-items-center">
                                        <span class="font-size-sm mr-3">
                                            <span id="datatableCounter">0</span>
                                            Đã chọn
                                        </span>
                                    <a class="btn btn-sm btn-outline-danger" href="javascript:;">
                                        <i class="tio-delete-outlined"></i> Xóa
                                    </a>
                                </div>
                            </div>
                            <!-- End Datatable Info -->

                            <!-- Unfold -->
                            <div class="hs-unfold mr-2">
                                <a class="js-hs-unfold-invoker btn btn-sm btn-white dropdown-toggle"
                                   href="javascript:;" data-hs-unfold-options='{
                               "target": "#usersExportDropdown",
                               "type": "css-animation"
                             }'>
                                    <i class="tio-download-to mr-1"></i> Xuất
                                </a>

                                <div id="usersExportDropdown"
                                     class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-sm-right">
                                    <span class="dropdown-header">Lựa chọn</span>
                                    <a id="export-copy" class="dropdown-item" href="javascript:;">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/copy.svg" alt="Image Description">
                                        Copy
                                    </a>
                                    <a id="export-print" class="dropdown-item" href="javascript:;">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/print.svg" alt="Image Description">
                                        In
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <span class="dropdown-header">Tải file</span>
                                    <a id="export-excel" class="dropdown-item" href="javascript:;">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/excel.svg" alt="Image Description">
                                        Excel
                                    </a>
                                    <a id="export-csv" class="dropdown-item" href="javascript:;">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2"
                                             src="../images/user/placeholder-csv-format.svg" alt="Image Description">
                                        .CSV
                                    </a>
                                    <a id="export-pdf" class="dropdown-item" href="javascript:;">
                                        <img class="avatar avatar-xss avatar-4by3 mr-2" src="../images/user/pdf.svg"
                                             alt="Image Description">
                                        PDF
                                    </a>
                                </div>
                            </div>

                            <!-- Unfold -->
                            <div class="hs-unfold">
                                <a class="js-hs-unfold-invoker btn btn-sm btn-white" href="javascript:;"
                                   data-hs-unfold-options='{"target": "#usersFilterDropdown","type": "css-animation","smartPositionOff": true}'>
                                    <i class="tio-filter-list mr-1"></i> Lọc <span
                                        class="badge badge-soft-dark rounded-circle ml-1">1</span>
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
                                               href="javascript:;"
                                               data-hs-unfold-options='{"target": "#usersFilterDropdown","type": "css-animation","smartPositionOff": true}'>
                                                <i class="tio-clear tio-lg"></i>
                                            </a>
                                            <!-- End Toggle Button -->
                                        </div>

                                        <div class="card-body">
                                            <form>
                                                <div class="form-row">
                                                    <div class="col-12 form-group">
                                                        <small class="text-cap mb-2">Vai trò</small>

                                                        <!-- Select -->
                                                        <select
                                                                class="js-select2-custom js-datatable-filter custom-select"
                                                                size="1" style="opacity: 0;"
                                                                data-target-column-index="3"
                                                                data-hs-select2-options='{"searchInputPlaceholder": "Tìm kiếm vai trò"}'>
                                                            <option label="empty"></option>
                                                            <option value="">Quản trị viên</option>
                                                            <option value="" selected>Nhân viên bán hàng</option>
                                                            <option value="">Biên tập viên</option>
                                                            <option value="">Nhân viên giao hàng</option>
                                                        </select>
                                                        <!-- End Select -->
                                                    </div>
                                                </div>
                                                <!-- End Row -->

                                                <a class="js-hs-unfold-invoker btn btn-block btn-primary"
                                                   href="javascript:;"
                                                   data-hs-unfold-options='{"target": "#usersFilterDropdown","type": "css-animation","smartPositionOff": true}'>Áp
                                                    dụng</a>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Table -->
            <div class="table-responsive datatable-custom">
                <table id="datatable"
                       class="table table-lg table-borderless table-thead-bordered table-nowrap table-align-middle card-table"
                       data-hs-datatables-options='{
                           "columnDefs": [{
                              "targets": [0, 3],
                              "orderable": false
                            }],
                           "order": [],
                           "info": {
                             "totalQty": "#datatableWithPaginationInfoTotalQty"
                           },
                           "search": "#datatableSearch",
                           "entries": "#datatableEntries",
                           "pageLength": 3,
                           "isResponsive": false,
                           "isShowPaging": false,
                           "pagination": "datatablePagination"
                         }'>
                    <thead class="thead-light">
                    <tr>
                        <th class="table-column-pr-0">
                            <div class="custom-control custom-checkbox">
                                <input id="datatableCheckAll" type="checkbox" class="custom-control-input">
                                <label class="custom-control-label" for="datatableCheckAll"></label>
                            </div>
                        </th>
                        <th>Vai trò</th>
                        <th>Chú thích</th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="roles" items="${listRoles}">
                        <tr>
                            <td class="table-column-pr-0">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="usersDataCheck${roles.id}">
                                    <label class="custom-control-label" for="usersDataCheck${roles.id}"></label>
                                </div>
                            </td>
                            <td class="table-column-pl-0">
                                <a class="d-flex align-items-center" href="edit_role?id=${roles.id}">
                                    <div class="ml-3">
                                        <span class="d-block h5 text-hover-primary mb-0">${roles.name}</span>
                                    </div>
                                </a>
                            </td>
                            <td>${roles.description}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a class="btn btn-sm btn-white" href="edit_role?id=${roles.id}">
                                        <i class="tio-edit"></i> Chỉnh sửa
                                    </a>
                                    <div class="hs-unfold btn-group">
                                        <a class="js-hs-unfold-invoker btn btn-icon btn-sm btn-white dropdown-toggle dropdown-toggle-empty"
                                           href="javascript:;"
                                           data-hs-unfold-options='{ "target": "#productsEditDropdown${roles.id}","type": "css-animation","smartPositionOffEl": "#datatable"}'></a>

                                        <div id="productsEditDropdown${roles.id}"
                                             class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-right mt-1">
                                            <a class="dropdown-item" href="#">
                                                <i class="tio-checkmark-circle-outlined dropdown-item-icon"></i>
                                                Kích hoạt
                                            </a>
                                            <a class="dropdown-item" href="#">
                                                <i class="tio-checkmark-circle dropdown-item-icon"></i> Vô hiệu hóa
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <!-- Footer -->
            <div class="card-footer">
                <!-- Pagination -->
                <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
                    <div class="col-sm mb-2 mb-sm-0">
                        <div class="d-flex justify-content-center justify-content-sm-start align-items-center">
                            <span class="mr-2">Hiển thị:</span>

                            <!-- Select -->
                            <select id="datatableEntries" class="js-select2-custom" data-hs-select2-options='{
                                  "minimumResultsForSearch": "Infinity",
                                  "customClass": "custom-select custom-select-sm custom-select-borderless",
                                  "dropdownAutoWidth": true,
                                  "width": true
                                }'>
                                <option value="1">1</option>
                                <option value="3" selected="">3</option>
                                <option value="5">5</option>
                            </select>
                            <!-- End Select -->

                            <span class="text-secondary mr-2">của</span>

                            <!-- Pagination Quantity -->
                            <span id="datatableWithPaginationInfoTotalQty"></span>
                        </div>
                    </div>

                    <div class="col-sm-auto">
                        <div class="d-flex justify-content-center justify-content-sm-end">
                            <!-- Pagination -->
                            <nav id="datatablePagination" aria-label="Activity pagination"></nav>
                        </div>
                    </div>
                </div>
                <!-- End Pagination -->
            </div>
            <!-- End Footer -->
        </div>
        <!-- End Card -->
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
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
</script>

</body>

</html>
