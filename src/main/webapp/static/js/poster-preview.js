let fileInput = document.getElementById("edit-poster");
let image = document.querySelector('.poster');

fileInput.addEventListener("change", handleImage, false);

function handleImage() {
    let file = this.files[0]

    if (!file.type.startsWith('image/')) {
        return;
    }

    let reader = new FileReader();
    reader.onload = (function (aImg) {
        return function (e) {
            aImg.src = e.target.result;
        };
    })(image);
    reader.readAsDataURL(file);
}