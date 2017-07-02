var selectedRowId = -1;
var UserList = function () {
    var handleRecords = function () {
        var grid = new Datatable();
        grid.init({
            src: $("#my_table"),
            loadingMessage: "Loading...",
            dataTable: {
                "searching": false,
                "ordering": false,
                "lengthMenu": [[10, 20, 50, 100, -1], [10, 20, 50, 100, "All"]],
                "pageLength": 10,
                "ajax": {
                    "url": "/user",
                },
                "columns": [
                    {"data": "id"},
                    {"data": "username"},
                    {"data": "realname"},
                    {"data": "mobile"},
                    {"data": "email"},
                    {"data": "roleIds"},
                    {"data": "locked"},
                    {"data": "lastModifyTime"}
                ],
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false
                    },
                    {
                        "targets": [6],
                        "render": function (data, type, full, meta) {
                            if(data){
                                return "锁定";
                            }else{
                                return "正常";
                            }
                        }
                    },
                    {
                        "targets": [7],
                        "render": function (data, type, full, meta) {
                            return new Date(data).toLocaleString();
                        }
                    }

                ],
                "rowCallback": function (row, data) {
                    $(row).attr("id", data.id);
                }
            }
        });

        $("#query_button").on('click', function () {
            grid.submitFilter($("#filter_form"));
        });
        $("#clean_button").on('click', function () {
            grid.resetFilter($("#filter_form"));
        });
        $("#delete_button").on('click', function () {
            if (selectedRowId <= 0) {
                alert("请先选择行");
            } else {
                if (confirm("确定要删除数据吗？")) {
                    jQuery.post("/user/" + selectedRowId + "/delete", function (data) {
                        if (data.rspCode == "000000") {
                            grid.getDataTable().ajax.reload();
                        } else {
                            alert(data.rspMsg)
                        }
                    }, "json");
                }
            }
        });
        $("#update_button").on('click', function () {
            if (selectedRowId <= 0) {
                alert("请先选择行");
            } else {
                window.location.href = "/user/" + selectedRowId + "/update"
            }
        });
        $("#change_button").on('click', function () {
            if (selectedRowId <= 0) {
                alert("请先选择行");
            } else {
                window.location.href = "/user/" + selectedRowId + "/changePassword"
            }
        });

        $('#my_table tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                selectedRowId = 0;
            } else {
                grid.getDataTable().$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                selectedRowId = $(this).attr("id");
            }
        });
    }

    return {
        init: function () {
            handleRecords();
        }
    };

}();

jQuery(document).ready(function () {
    UserList.init();

});