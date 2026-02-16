

$(document).ready(function () {
    $("#bookIdsInput").on("input", function () {
        const input = $(this).val();

        const bookIds = input
            .split(",")
            .map(id => id.trim())
            .filter(id => id !== "" && !isNaN(id))
            .map(Number);

        $("#bookIdsJson").val(JSON.stringify(bookIds));
    });
});

