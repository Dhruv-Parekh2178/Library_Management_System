$(document).ready(function () {

    $(".delete_btn").on("submit", function (e) {
        e.preventDefault();
        e.stopPropagation();

        const form = $(this);
          const categoryId = form.data("id");
        const card = form.closest(".card");

        $.ajax({
            url: `${contextPath}/category/delete/${categoryId}`,
            type: "GET",
            success: function () {card.fadeOut(300, function () {
                    $(this).remove();
                });
            },
            error: function (err) {
                console.error(err);
                alert("Delete failed");
            }
        });
    });

});
