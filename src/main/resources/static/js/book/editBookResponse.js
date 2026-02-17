 $(document).ready(function() {
            $("#bookForm").on("submit" , function(event){
            event.preventDefault();
              if (!$("#bookForm").valid()) {
                                    return;
                                }
            $.ajax({
                            url:`${contextPath}/book/put/${id}/data`,
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