$(document).ready(function() {

    $("#publisherForm").validate({

        onfocusout: function(element) {
            $(element).valid();
        },

        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 30
            }
        },

        messages: {
            name: {
                required: "Please enter the publisher name",
                minlength: "Publisher name must be at least 2 characters",
                maxlength: "Publisher name must be less than 30 characters"
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
