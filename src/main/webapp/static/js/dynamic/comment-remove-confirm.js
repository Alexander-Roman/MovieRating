function cancelCommentRemove(cancelButton) {
    let commentRemoveForm = cancelButton.closest(".comment-remove-form");
    let optionsButton = commentRemoveForm.querySelector('.comment-remove-options');
    let confirmButton = commentRemoveForm.querySelector('.comment-remove-submit');

    optionsButton.style.display = "initial";
    cancelButton.style.display = "none";
    confirmButton.style.display = "none";
}

function showCommentRemoveOptions(optionsButton) {
    let commentRemoveForm = optionsButton.closest(".comment-remove-form");
    let confirmButton = commentRemoveForm.querySelector('.comment-remove-submit');
    let cancelButton = commentRemoveForm.querySelector('.comment-remove-cancel');

    cancelButton.style.display = "initial";
    confirmButton.style.display = "initial";
    optionsButton.style.display = "none";
}