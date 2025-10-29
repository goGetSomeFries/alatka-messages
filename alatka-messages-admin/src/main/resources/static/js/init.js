function initTable() {
    $("#resetButton").click(function () {
        $("#searchForm")[0].reset();
    })

    $("#searchButton").click(function () {
        refresh();
    })

    return $("#dataTable").bootstrapTable({
        onLoadError: function (status) {
            showErrorToast(`接口请求失败, http code: ${status}`)
        },

        queryParams: function (params) {
            const formData = new FormData($('#searchForm')[0]);
            const searchData = {};
            formData.forEach((value, key) => {
                if (searchData.hasOwnProperty(key)) {
                    if (!Array.isArray(searchData[key])) {
                        searchData[key] = [searchData[key]];
                    }
                    searchData[key].push(value);
                } else if (value) {
                    searchData[key] = value;
                }
            });

            const urlParams = new URLSearchParams(window.location.search);
            for (let [key, value] of urlParams) {
                searchData[key] = value;
            }

            return {
                pageNo: params.offset / params.limit + 1,
                pageSize: params.limit,
                orderBy: params.sort,
                direction: params.order,
                ...searchData
            }
        },

        responseHandler: function (res) {
            if (res.code !== "0000") {
                showErrorToast(`接口响应失败: ${res.msg}`);
                return {
                    total: 0,
                    rows: []
                };
            }
            return {
                total: res.totalRecords,
                rows: res.data
            };
        }
    })
}

function showEditModal(url, created) {
    if (created) {
        $('#editForm')[0]?.reset();
        $('#editForm input[type="hidden"]').val('');
    } else {
        const selection = $('#dataTable').bootstrapTable('getSelections');
        if (selection.length !== 1) {
            showWarningToast("请选择一条记录进行编辑");
            return;
        }

        const row = selection[0];
        Object.keys(row).forEach(field => {
            const $input = $(`#editForm [name="${field}"], #editForm #${field}`);
            if ($input.length) {
                let value = row[field];
                if (typeof value === 'boolean') {
                    value = value ? 'true' : 'false';
                }
                $input.val(value);
            }
        });
    }

    $('#editModal').modal('show');
    $('#saveEditBtn').off('click').on('click', function (event) {
        const formData = {};
        const $editForm = $('#editForm');
        if (!$editForm[0].checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
            $editForm.addClass('was-validated');
        } else {
            $editForm.serializeArray().forEach(item => {
                formData[item.name] = item.value;
            });

            const urlParams = new URLSearchParams(window.location.search);
            for (let [key, value] of urlParams) {
                formData[key] = value;
            }

            submitFunction(url, created ? 'POST' : 'PUT', formData, created ? '新增' : '更新');
            $('#editModal').modal('hide');
        }
    });
}

function showDeleteModal(url) {
    const selections = $('#dataTable').bootstrapTable('getSelections');
    if (selections.length !== 1) {
        showWarningToast("请选择一条记录");
        return;
    }

    $('#deleteModal').modal('show');
    $('#saveDeleteBtn').off('click').on('click', function () {
        const ids = selections.map(row => row.id).join(",");
        submitFunction(`${url}?ids=${ids}`, 'DELETE', null, '删除');
        $('#deleteModal').modal('hide');
    });
}

function submitFunction(url, methodType, data, actionName) {
    httpClient(url, methodType, data, function (data) {
        showSuccessToast(`${actionName}成功`);
        refresh();
    });
}

function redirectToFieldPage(messageId) {
    window.location.href = `field?messageId=${messageId}`;
}

function httpClient(url, methodType, data, success, error = function (msg) {
    showErrorToast(`接口响应失败: ${msg}`);
}) {
    $.ajax({
        url: url,
        type: methodType,
        contentType: 'application/json',
        data: data && JSON.stringify(data),
        success: function (response) {
            if (response.code === "0000") {
                success(response.data);
            } else {
                error(response.msg);
            }
        },
        error: function (xhr) {
            showErrorToast(`接口请求失败: ${xhr.responseJSON?.message || "未知错误"}`);
        }
    });
}

function refresh() {
    $('#dataTable').bootstrapTable('refreshOptions', {
        pageNumber: 1,
        sortOrder: "desc",
        sortName: "id"
    });
}

function showSuccessToast(message) {
    showToast(message, 'bg-success');
}

function showErrorToast(message) {
    showToast(message, 'bg-danger');
}

function showWarningToast(message) {
    showToast(message, 'bg-warning');
}

function showToast(message, bgClass) {
    $('.toast').remove();

    const toastHtml = `
        <div class="toast align-items-center text-white ${bgClass} border-0 position-fixed translate-middle-y end-0 top-50 m-3" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;

    const $toast = $(toastHtml).appendTo('body');

    // 初始化并显示 toast
    $toast.toast({
        autohide: true,
        delay: 3000
    }).toast('show');

    // 自动移除 toast 当隐藏时
    $toast.on('hidden.bs.toast', function () {
        $(this).remove();
    });
}

function enabledFormatter(arg) {
    if (arg === true) {
        return "正常";
    }
    if (arg === false) {
        return "禁用";
    }
    return "/";
}