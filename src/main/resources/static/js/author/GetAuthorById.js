$(document).ready(function (){
      $(".card").click(function (){
      const id = $(this).data("id");
      console.log("authorId :" , id);
      window.location.href = `${contextPath}/author/get/${id}`;
      })
});