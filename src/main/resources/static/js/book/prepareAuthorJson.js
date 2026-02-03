

$(document).ready(function () {
    $("#authorIdsInput").on("input", function () {
        const input = $(this).val();

        const authorIds = input
            .split(",")
            .map(id => id.trim())
            .filter(id => id !== "" && !isNaN(id))
            .map(Number);

        $("#authorIdsJson").val(JSON.stringify(authorIds));
        return true;
    });
});

