

$(document).ready(function () {
    $("#userIdsInput").on("input", function () {
        const input = $(this).val();

        const userIds = input
            .split(",")
            .map(id => id.trim())
            .filter(id => id !== "" && !isNaN(id))
            .map(Number);

        $("#userIdsJson").val(JSON.stringify(userIds));
        return true;
    });
});

