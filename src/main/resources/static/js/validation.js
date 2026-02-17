$(document).ready(function () {

    if ($("#loginForm").length) {
        $("#loginForm").validate({
            onfocusout: function (element) {
                $(element).valid();
            },

            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },
                password: {
                    required: true,
                    minlength: 8
                }
            },

            messages: {
                username: {
                    required: "Please enter your username",
                    minlength: "Username must be at least 2 characters",
                    maxlength: "Username must be less than 30 characters"
                },
                password: {
                    required: "Please enter your password",
                    minlength: "Password must be at least 8 characters"
                }
            },

            errorElement: "span",
            errorClass: "validation-error",

            errorPlacement: function (error, element) {
                error.insertAfter(element);
            },

            highlight: function (element) {
                $(element).addClass("validation-error");
            },

            unhighlight: function (element) {
                $(element).removeClass("validation-error");
            }
        });
    }
    if ($("#signupForm").length) {
        $("#signupForm").validate({
            onfocusout: function (element) {
                $(element).valid();
            },

            rules: {
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },
                password: {
                    required: true,
                    minlength: 8
                },
                role: {
                    required: true
                }
            },

            messages: {
                name: {
                    required: "Please enter a username",
                    minlength: "Username must be at least 2 characters",
                    maxlength: "Username must be less than 30 characters"
                },
                password: {
                    required: "Please enter a password",
                    minlength: "Password must be at least 8 characters"
                },
                role: {
                    required: "Please select a role"
                }
            },

            errorElement: "span",
            errorClass: "validation-error",

            errorPlacement: function (error, element) {
                error.insertAfter(element);
            },

            highlight: function (element) {
                $(element).addClass("validation-error");
            },

            unhighlight: function (element) {
                $(element).removeClass("validation-error");
            }
        });
    }

});