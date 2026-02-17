$(document).ready(function() {
            $.ajax({
                    url:`${contextPath}/author/data`,
                       type: 'GET',
                       dataType: 'json',
                       success: function(data) {
                               console.log('JSON Response:', data);
                                   },
                       error: function(xhr, status, error) {
                               console.error('Error:', error);
                               }
           });
        });