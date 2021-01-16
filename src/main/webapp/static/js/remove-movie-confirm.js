let deleteButton = document.querySelector('.movie-delete-button');
let modal = document.querySelector('.delete-movie-modal');
let closeButton = document.querySelector('.delete-movie-close');
let cancelButton = document.querySelector('.delete-movie-cancel');

deleteButton.onclick = function () {
    modal.style.display="block";
}

closeButton.onclick = function () {
    modal.style.display="none";
}

cancelButton.onclick = function () {
    modal.style.display="none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}