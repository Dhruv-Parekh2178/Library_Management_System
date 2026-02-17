 $(document).ready(function() {
            $("#userForm").on("submit" , function(event){
            event.preventDefault();
              if (!$("#userForm").valid()) {
                                    return;
                                }
            $.ajax({
                            url:`${contextPath}/user/put/${id}/data`,
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