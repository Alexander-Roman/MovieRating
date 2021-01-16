function configureOptions(commentRemoveForm) {

    let optionsButton = commentRemoveForm.querySelector('.comment-remove-options');
    let confirmButton = commentRemoveForm.querySelector('.comment-remove-submit');
    let cancelButton = commentRemoveForm.querySelector('.comment-remove-cancel');
    optionsButton.onclick = function () {

        cancelButton.style.display = "initial";
        confirmButton.style.display = "initial";
        optionsButton.style.display = "none";
    }
    cancelButton.onclick = function () {

        optionsButton.style.display = "initial";
        cancelButton.style.display = "none";
        confirmButton.style.display = "none";
    }
}

let commentRemoveForms = document.querySelectorAll('.comment-remove-form');

for (let commentRemoveForm of commentRemoveForms) {
    configureOptions(commentRemoveForm);
}

