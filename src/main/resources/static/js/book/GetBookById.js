$(document).ready(function (){
      $(".card").click(function (e){
            if ($(e.target).closest(".delete_btn").length) {
                  return;
            }
      const id = $(this).data("id");

      window.location = `${contextPath}/book/get/${id}`;
       $.ajax({
                    url: `${contextPath}/book/get/${id}/data`,
                    type: "GET",
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