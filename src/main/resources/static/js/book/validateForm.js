$(document).ready(function() {
    $("#bookForm").validate({

        onfocusout: function(element) {
            $(element).valid();
        },

        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 30
            },

            publisher:{
               digits: true,
                      min: 1,
                      max: 9223372036854775807
            }
        },

        messages: {
            name: {
                required: "Please enter the author's name",
                minlength: "Book name must be at least 2 characters",
                maxlength: "Book name must be less than 30 characters"
            },
             publisher: {
                    digits: "Publisher ID must be a single number only — no letters, commas, or spaces",
                    min: "Publisher ID must be greater than 0"
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
