/**
 * Created by Zhang on 2016-9-11.
 */
var UserEdit = function () {
    var handleValidation = function() {
        var form = $('#edit_form');
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error',  // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                username: {
                    minlength: 5,
                    required: true
                },
                realname: {
                    required: true
                },
                password: {
                    required: true
                },
                locked: {
                    required: true
                },
                roleIds: {
                    required: true,
                    minlength: 1
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                locked: {
                    required: "Please select a locked type"
                },
                roleIds: {
                    required: "Please select  at least 1 role",
                    minlength: jQuery.validator.format("Please select  at least {0} role")
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
            submitHandler:function(form){
                $(form).ajaxSubmit(function(data){
                    if(data.rspCode=='000000'){
                        window.location.href="/user";
                    }else{
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
    UserEdit.init();

});