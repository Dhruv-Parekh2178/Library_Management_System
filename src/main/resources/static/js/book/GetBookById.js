$(document).ready(function (){
      $(".card").click(function (e){
            if ($(e.target).closest(".delete_btn").length) {
                  return;
            }
      const id = $(this).data("id");

      window.location = `${contextPath}/book/get/${id}`;
      })
});