let titleInput = document.getElementById("edit-title");
let submitButton = document.getElementById("save-movie-button");

titleInput.oninput = function () {
    let value = titleInput.value;
    if (value.trim() === "") {
        submitButton.disabled = true;
        titleInput.style.borderColor = "red";
    } else {
        submitButton.disabled = false;
        titleInput.style.borderColor = "#ccc";
    }
}