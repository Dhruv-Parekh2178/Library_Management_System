$(document).ready(function() {
    $.validator.addMethod("pattern", function(value, element, regex) {
        return this.optional(element) || regex.test(value);
    }, "Invalid format");

    $("#userForm").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 30
            },
            age: {
                required: true,
                number: true,
                min: 7,
                max : 120
            },
            books: {
                pattern: /^\s*\d+(\s*,\s*\d+)*\s*$/
            }
        },
        messages: {
            name: {
                required: "Please enter the author's name",
                minlength: "Name must be at least 2 characters",
                maxlength: "Name must be less than 30 characters"
            },
            age: {
                required: "Please enter the author's age",
                number: "Age must be a number",
                min: "Age must be Greater than 7 years",
                max : "Age must bee less that 120 years"
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