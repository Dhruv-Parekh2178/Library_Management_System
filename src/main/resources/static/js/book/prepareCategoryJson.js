

$(document).ready(function () {
    $("#categoryIdsInput").on("input", function () {
        const input = $(this).val();

        const categoryIds = input
            .split(",")
            .map(id => id.trim())
            .filter(id => id !== "" && !isNaN(id))
            .map(Number);

        $("#categoryIdsJson").val(JSON.stringify(categoryIds));
        return true;
    });
});

