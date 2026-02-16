$(document).ready(function() {
    $.validator.addMethod("pattern", function(value, element, regex) {
        return this.optional(element) || regex.test(value);
    }, "Invalid format");

    $("#categoryForm").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxLength: 30
            },

            books: {
                pattern: /^\s*\d+(\s*,\s*\d+)*\s*$/
            }
        },
        messages: {
            name: {
                required: "Please enter the author's name",
                minlength: "Category name must be at least 2 characters",
                maxlength: "Category name must be less than 30 characters"
            },
            books: {
                pattern: "Book Id must be comma separated and not empty"
            }
        },
        errorElement: "label",
        errorClass: "validation-error",
        errorPlacement: function(error, element) {
            error.insertAfter(element);
        },
        submitHandler:function (form){
            prepareBooksJson();
            form.submit();
        }
    });
});