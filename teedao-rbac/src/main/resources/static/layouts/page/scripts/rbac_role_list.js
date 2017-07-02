var selectedRowId = -1;
var RoleList = function () {
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
                    "url": "/role",
                },
                "columns": [
                    {"data": "id"},
                    {"data": "siteNo"},
                    {"data": "role"},
                    {"data": "description"},
                    {"data": "available"},
                    {"data": "lastModifyTime"}
                ],
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false
                    },
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
            if(selectedRowId<=0){
                alert("请先选择行");
            }else{
                if (confirm("确定要删除数据吗？")) {
                    jQuery.post("/role/" + selectedRowId + "/delete", function (data) {
                        if (data.rspCode == "000000") {
                            grid.getDataTable().ajax.reload();
                        } else {
                            alert(data.rspMsg);
                        }
                    }, "json");
                }
            }
        });
        $("#update_button").on('click', function () {
            if(selectedRowId<=0){
                alert("请先选择行");
            }else{
                window.location.href = "/role/" + selectedRowId + "/update"
            }
        });

        $("#create_button").on('click', function () {
            var siteNo=$("#siteNo").val();
            if(siteNo==""){
                alert("请先查询选择站点");
            }else{
                window.location.href = "/role/" + siteNo + "/create"
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
    RoleList.init();

});