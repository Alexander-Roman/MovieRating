let text = document.getElementById("text");
let submitButton = document.getElementById("comment-form-submit");

text.oninput = function () {
    let value = text.value;
    submitButton.disabled = value.trim() === "";
}