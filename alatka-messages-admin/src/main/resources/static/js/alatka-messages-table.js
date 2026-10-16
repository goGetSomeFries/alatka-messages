function initTable() {
    $("#resetButton").off('click').on('click', function () {
        const $searchForm = $("#searchForm");
        $searchForm[0].reset();
        $searchForm.find('select').each(function () {
            if (this.tomselect) {
                this.tomselect.clear();
                const initValue = $(this).data('alk-init-value');
                if (initValue) {
                    this.tomselect.setValue(initValue);
                }
            }
        });
    });

    $("#searchButton").click(function () {
        refresh();
    })

    interact('.modal-dialog').draggable({
        allowFrom: '.modal-header',
        modifiers: [
            interact.modifiers.restrictRect({
                restriction: 'parent'
            })
        ],
        listeners: {
            move: function (event) {
                const target = event.target;
                const x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx;
                const y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;

                target.style.transform = `translate(${x}px, ${y}px)`;
                target.setAttribute('data-x', x);
                target.setAttribute('data-y', y);
            }
        }
    });

    $("#dataTable").bootstrapTable({
        undefinedText: '',
        pageList: [10, 25, 50, 100],
        onLoadError: function (status) {
            showErrorToast(`接口请求失败, http code: ${status}`)
        },
        iconSize: 'sm',
        buttonsClass: 'sm',
        buttons: buttonsFunction,
        buttonsOrder: buttonsOrderFunction(),

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

            $('#searchForm').find('select').each(function () {
                if (this.tomselect) {
                    const fieldName = $(this).attr('name');
                    const tsVal = this.tomselect.getValue();

                    if (fieldName) {
                        if (Array.isArray(tsVal)) {
                            if (tsVal.length) searchData[fieldName] = tsVal;
                            else delete searchData[fieldName];
                        } else if (tsVal !== null && tsVal !== undefined && tsVal !== '') {
                            searchData[fieldName] = tsVal;
                        } else {
                            delete searchData[fieldName];
                        }
                    }
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
    const $form = $('#editForm');
    const $modal = $('#editModal');
    const formEl = $form[0];
    if (created) {
        formEl?.reset();
        $form.find('input[type="hidden"]').val('');
        $form.find('select').each(function () {
            if (!this.tomselect) return;
            this.tomselect.clear();

            const initValue = $(this).data('alk-init-value');
            if (initValue !== undefined && initValue !== null && initValue !== '') {
                this.tomselect.setValue(initValue);
            }
        });
    } else {
        const selection = $('#dataTable').bootstrapTable('getSelections');
        if (selection.length !== 1) {
            showWarningToast("请选择一条记录进行编辑");
            return;
        }

        const row = selection[0];
        Object.keys(row).forEach(field => {
            const $field = $form.find(`[name="${field}"]`);
            if (!$field.length) return;

            let value = row[field];

            if (typeof value === 'boolean') {
                value = value ? 'true' : 'false';
            }
            if (value === null || value === undefined) {
                value = '';
            }
            if ($field[0].tomselect) {
                $field[0].tomselect.setValue(value);
            } else {
                $field.val(value);
            }
        });
    }
    $form.removeClass('was-validated');
    $form.find('select').each(function () {
        if (this.tomselect) {
            $(this.tomselect.wrapper).removeClass('is-invalid is-valid');
        }
    });

    $modal.modal('show');
    $('#saveEditBtn').off('click').on('click', function (event) {
        event.preventDefault();
        event.stopPropagation();
        if (!formEl.checkValidity()) {
            $form.addClass('was-validated');
            $form.find('select').each(function () {
                if (!this.tomselect) return;
                if (this.checkValidity()) {
                    $(this.tomselect.wrapper).removeClass('is-invalid').addClass('is-valid');
                } else {
                    $(this.tomselect.wrapper).removeClass('is-valid').addClass('is-invalid');
                }
            });
            return;
        }
        const formData = {};
        $form.serializeArray().forEach(item => {
            formData[item.name] = item.value === '' ? null : item.value;
        });

        const urlParams = new URLSearchParams(window.location.search);
        for (let [key, value] of urlParams) {
            formData[key] = value;
        }
        submitFunction(url, created ? 'POST' : 'PUT', formData, created ? '新增' : '更新');
        $modal.modal('hide');
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
    window.location.href = `/field?messageId=${messageId}`;
}

function refresh() {
    $('#dataTable').bootstrapTable('refreshOptions', {
        pageNumber: 1,
        sortOrder: "desc",
        sortName: "id"
    });
}

function kindFormatter(key) {
    switch (key) {
        case 'header':
            return `<span class="badge bg-primary">${key}</span>`;
        case 'payload':
            return `<span class="badge bg-success">${key}</span>`
        case 'request':
            return `<span class="badge bg-secondary">${key}</span>`
        case 'response':
            return `<span class="badge bg-info">${key}</span>`
        default:
            return `<span class="badge bg-secondary">/</span>`;
    }
}

function initKindSelect() {
    $(".alk-select-kind").each(function () {
        let tomSelect = new TomSelect(this, {
            plugins: ['remove_button'],
            placeholder: '选择种类',
            options: [{value: 'header'}, {value: 'payload'}, {value: 'request'}, {value: 'response'}],
            render: {
                option: function (data, escape) {
                    return `<div>${kindFormatter(data.value)}</div>`;
                },
                item: function (data, escape) {
                    return `<div>${kindFormatter(data.value)}</div>`;
                }
            }
        });
        if (this.id !== 'kind') {
            tomSelect.setValue([Object.keys(tomSelect.options)[0]]);
            $(this).data('alk-init-value', Object.keys(tomSelect.options)[0]);
        }
    });
}

function typeFormatter(key) {
    switch (key) {
        case 'iso':
            return `<span class="badge bg-success">8583</span>`;
        case 'fixed':
            return `<span class="badge bg-primary">固定格式</span>`
        default:
            return `<span class="badge bg-secondary">/</span>`;
    }
}

function initTypeSelect() {
    $(".alk-select-type").each(function () {
        let tomSelect = new TomSelect(this, {
            plugins: ['remove_button'],
            placeholder: '选择类型',
            options: [{value: 'iso', text: '8583'}, {value: 'fixed', text: '固定格式'}],
            render: {
                option: function (data, escape) {
                    return `<div>${typeFormatter(data.value)}</div>`;
                },
                item: function (data, escape) {
                    return `<div>${typeFormatter(data.value)}</div>`;
                }
            }
        });
        if (this.id !== 'type') {
            tomSelect.setValue([Object.keys(tomSelect.options)[0]]);
            $(this).data('alk-init-value', Object.keys(tomSelect.options)[0]);
        }
    });
}

function enabledFormatter(arg) {
    if (arg === true || arg === 'true') {
        return `<span class="badge bg-success">正常</span>`;
    }
    return `<span class="badge bg-danger">禁用</span>`;
}

function initEnabledSelect() {
    $(".alk-select-enabled").each(function () {
        let tomSelect = new TomSelect(this, {
            plugins: ['remove_button'],
            placeholder: '选择状态',
            options: [{value: 'true', text: '正常'}, {value: 'false', text: '禁用'}],
            items: ['true'],
            render: {
                option: function (data, escape) {
                    return `<div>${enabledFormatter(data.value)}</div>`;
                },
                item: function (data, escape) {
                    return `<div>${enabledFormatter(data.value)}</div>`;
                }
            }
        });
        $(this).data('alk-init-value', tomSelect.items[0]);
    });
}
