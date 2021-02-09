function showDeleteModal() {
    movieDeleteConfirmModal.style.display="block";
}

function hideDeleteModal() {
    movieDeleteConfirmModal.style.display="none";
}

window.onclick = function(event) {
    if (event.target === movieDeleteConfirmModal) {
        movieDeleteConfirmModal.style.display = "none";
    }
}