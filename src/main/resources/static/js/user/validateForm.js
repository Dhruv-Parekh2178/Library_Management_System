$(document).ready(function() {

    $("#userForm").validate({

        onfocusout: function(element) {
            $(element).valid();
        },

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
                max: 120
            }
        },

        messages: {
            name: {
                required: "Please enter the name",
                minlength: "Name must be at least 2 characters",
                maxlength: "Name must be less than 30 characters"
            },
            age: {
                required: "Please enter the age",
                number: "Age must be a number",
                min: "Age must be greater than 7 years",
                max: "Age must be less than 120 years"
            }
        },

        errorElement: "span",
        errorClass: "validation-error",

        errorPlacement: function(error, element) {
            error.insertAfter(element);
        },

        highlight: function(element) {
            $(element).addClass("validation-error");
        },

        unhighlight: function(element) {
            $(element).removeClass("validation-error");
        }

    });

});
