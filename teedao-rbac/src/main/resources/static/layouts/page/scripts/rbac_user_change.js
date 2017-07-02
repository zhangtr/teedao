/**
 * Created by Zhang on 2016-9-11.
 */
var UserChange = function () {
    var handleValidation = function () {
        var form = $('#edit_form');
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error',  // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                newPassword: {
                    required: true,
                    minlength: 6
                },
                reNewPassword: {
                    required: true,
                    minlength: 6,
                    equalTo: "#newPassword"
                }
            },

            errorPlacement: function (error, element) { // render error placement for each input type
                if (element.parent(".input-group").size() > 0) {
                    error.insertAfter(element.parent(".input-group"));
                } else if (element.attr("data-error-container")) {
                    error.appendTo(element.attr("data-error-container"));
                } else if (element.parents('.radio-list').size() > 0) {
                    error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                } else if (element.parents('.radio-inline').size() > 0) {
                    error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                } else if (element.parents('.checkbox-list').size() > 0) {
                    error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                } else if (element.parents('.checkbox-inline').size() > 0) {
                    error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                } else {
                    error.insertAfter(element);
                }
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit(function (data) {
                    if (data.rspCode == '000000') {
                        window.location.href = "/user";
                    } else {
                        $('.alert-danger span', $(form)).html(data.rspMsg);
                        $('.alert-danger', $(form)).show();
                    }
                });
            }

        });
    };
    return {
        init: function () {
            handleValidation();
        }
    };

}();

jQuery(document).ready(function () {
    UserChange.init();

});