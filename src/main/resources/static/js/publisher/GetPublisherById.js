$(document).ready(function (){
      $(".card").click(function (){
      const id = $(this).data("id");

      window.location.href = `${contextPath}/category/get/${id}`;
      })
});