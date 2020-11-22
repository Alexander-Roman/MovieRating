var openButton = document.getElementById("loginOpenButton");
var closeButton = document.getElementById("loginCloseButton");
var form = document.getElementById("loginForm");

openButton.onclick = function() {
	if (form.style.display === "block") {
		form.style.display = "none";
	} else {
		form.style.display = "block";
	}
};

closeButton.onclick = function() {
	form.style.display = "none";
};