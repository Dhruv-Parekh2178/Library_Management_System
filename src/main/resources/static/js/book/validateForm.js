$(document).ready(function() {
    $.validator.addMethod("pattern", function(value, element, regex) {
        return this.optional(element) || regex.test(value);
    }, "Invalid format");

    $("#bookForm").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxLength: 30
            },

            authors: {
                pattern: /^\s*\d+(\s*,\s*\d+)*\s*$/
            },
            categories: {
                pattern: /^\s*\d+(\s*,\s*\d+)*\s*$/
            },
            users: {
                pattern: /^\s*\d+(\s*,\s*\d+)*\s*$/
            }
        },
        messages: {
            name: {
                required: "Please enter the author's name",
                minlength: "Book name must be at least 2 characters",
                maxlength: "Book name must be less than 30 characters"
            },
            authors: {
                pattern: "Author Id must be comma separated and not empty"
            },
            categories: {
                pattern: "Category Id must be comma separated and not empty"
            },
            users: {
                pattern: "User Id must be comma separated and not empty"
            }
        },
        errorElement: "label",
        errorClass: "validation-error",
        errorPlacement: function(error, element) {
            error.insertAfter(element);
        },
        submitHandler:function (form){
            prepareAuthorJson(); prepareCategoryJson(); prepareUserJson();
            form.submit();
        }
    });
});