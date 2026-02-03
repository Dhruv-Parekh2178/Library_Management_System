$(document).ready(function (){
      $(".card").click(function (){
      const id = $(this).data("id");
      console.log("BookId :" , id);
      window.location.href = `${contextPath}/book/get/${id}`;
      })
});