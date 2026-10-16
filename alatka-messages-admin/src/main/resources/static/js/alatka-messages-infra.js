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
            showErrorToast(`接口请求失败: ${xhr.responseJSON?.message || '未知错误'}`);
        }
    });
}

function showInfoToast(message) {
    showToast(message, 'bg-white text-black');
}

function showSuccessToast(message) {
    showToast(message, 'bg-success text-white');
}

function showErrorToast(message) {
    showToast(message, 'bg-danger text-white');
}

function showWarningToast(message) {
    showToast(message, 'bg-warning text-white');
}

function showToast(message, bgClass) {
    $('.toast').remove();

    const toastHtml = `
        <div class="toast align-items-center ${bgClass} border-0 position-fixed translate-middle-y end-0 top-50 m-3" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;

    const $toast = $(toastHtml).appendTo('body');

    $toast.toast({
        autohide: true,
        delay: 3000
    }).toast('show');

    $toast.on('hidden.bs.toast', function () {
        $(this).remove();
    });
}

