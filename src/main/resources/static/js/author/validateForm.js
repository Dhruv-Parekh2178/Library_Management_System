$(document).ready(function () {

    $("#authorForm").validate({
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
                            digits: true,
                            min: 18,
                            max: 120
                        }
        },

        messages: {
            name: {
                required: "Please enter the author name",
                minlength: "Author name must be at least 2 characters",
                maxlength: "Author name must be less than 30 characters"
            },
             age: {
                            required: "Please enter the author age",
                            min: "Age must be at least 18",
                            max: "Age must be less than 120"
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
