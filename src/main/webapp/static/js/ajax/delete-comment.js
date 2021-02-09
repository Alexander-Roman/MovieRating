function deleteComment(submitButton) {
    let comment = submitButton.closest(".comment-container");
    let commentId = comment.dataset.id;

    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                let cloneAlert = deleteCommentAlertSuccess.cloneNode(true);
                comment.replaceWith(cloneAlert);
            } else if (this.status === 401) {
                document.location.href = controllerActionPath + "?command=loginPage";
            } else if (this.status === 404) {
                let cloneAlert = deleteCommentAlertNotFound.cloneNode(true);
                comment.replaceWith(cloneAlert);
            } else {
                let cloneAlert = deleteCommentAlertError.cloneNode(true);
                comment.before(cloneAlert)
            }
        }
    };
    request.open("POST", ajaxActionPath, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("command=deleteComment&id=" + commentId);
}