function addComment() {
    let request = new XMLHttpRequest();
    let commentForm = document.querySelector(".comment-form");
    let textArea = commentForm.text;
    let submitButton = document.getElementById("comment-form-submit");
    submitButton.disabled = true;

    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                let result = request.responseText;
                let newCommentList = JSON.parse(result);
                updateCommentList(newCommentList);
                textArea.value = "";
            } else if (this.status === 401) {
                document.location.href = controllerActionPath + "?command=loginPage";
            } else {
                let commentsBlock = document.getElementById("comments-block");
                let cloneAlert = addCommentAlertError.cloneNode(true);
                commentsBlock.append(cloneAlert);
            }
        }
    }

    request.open("POST", ajaxActionPath, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("command=addComment&id=" + movieId + "&text=" + textArea.value);
}

function updateCommentList(newCommentList) {

    let commentsBlock = document.getElementById("comments-block");
    let oldElementsList = commentsBlock.children;

    let oldCount = oldElementsList.length;
    let newCount = newCommentList.length;

    for (let i = 0; i < oldCount && i < newCount; i++) {
        let updated = buildComment(newCommentList[i]);
        oldElementsList[i].replaceWith(updated);
    }

    if (oldCount < newCount) {
        for (let i = oldCount; i < newCount; i++) {
            let updated = buildComment(newCommentList[i]);
            commentsBlock.append(updated);
        }
    } else if (oldCount > newCount) {
        while (oldElementsList[newCount] !== undefined) {
            oldElementsList[newCount].remove();
        }
    }
}

function buildComment(comment) {
    let id = comment.id;
    let newCommentLayout = newCommentContainerSample.cloneNode(true);
    newCommentLayout.dataset.id = id;
    let authorName = newCommentLayout.querySelector(".author-name");
    authorName.innerHTML = comment.author.userName;
    let commentText = newCommentLayout.querySelector(".comment-text");
    commentText.innerHTML = comment.text;
    let commentDateTime = newCommentLayout.querySelector(".comment-time");
    commentDateTime.innerHTML = comment.dateTime;
    return newCommentLayout;
}