 $(document).ready(function() {
            $("#signupForm").on("submit" , function(event){
            event.preventDefault();
            $.ajax({
                            url:`${contextPath}/signUp/data`,
                            type: 'GET',
                            dataType: 'json',
                            success: function(data) {
                                console.log('JSON Response:', data);
                            },
                            error: function(xhr, status, error) {
                                console.error('Error:', error);
                            }
                });
            })
        });