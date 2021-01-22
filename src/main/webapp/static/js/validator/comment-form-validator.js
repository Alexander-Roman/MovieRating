function validateCommentText() {
    let submitButton = document.getElementById("comment-form-submit");
    let text = document.getElementById("text");
    let value = text.value;
    submitButton.disabled = value.trim() === "";
}