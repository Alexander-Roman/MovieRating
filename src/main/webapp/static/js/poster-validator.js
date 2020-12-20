let maxSize = 1024 * 1024 // 1MB
let fileTypes = [
    'image/jpeg',
    'image/png'
]

function validFileType(file) {
    for (let i = 0; i < fileTypes.length; i++) {
        if (file.type === fileTypes[i]) {
            return true;
        }
    }
    return false;
}

function validSize(file) {
    return file.size <= maxSize;
}

function validFile(file) {
    return validFileType(file) && validSize(file);
}

let fileInput = document.getElementById("edit-poster");
let image = document.querySelector('.poster');
let alert = document.querySelector('.poster-validation-alert');
let posterCurrentPath = image.src;

function imagePreview(file) {
    let reader = new FileReader();
    reader.onload = (function (aImg) {
        return function (e) {
            aImg.src = e.target.result;
        };
    })(image);
    reader.readAsDataURL(file);
}

fileInput.addEventListener("change", handleImage, false);

function handleImage() {
    let file = this.files[0];

    if (file === undefined) {
        alert.style.color = "black";
        image.src = posterCurrentPath;
    } else if (validFile(file)) {
        alert.style.color = "green";
        imagePreview(file);
    } else {
        fileInput.value = "";
        alert.style.color = "red";
        image.src = posterCurrentPath;
    }
}