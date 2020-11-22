/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
var dropdowns = document.querySelectorAll('.clickable-dropdown');

for (let dropdown of dropdowns) {
	dropdown.onclick = function() {
		dropdown.querySelector('.clickable-dropdown-content').classList.toggle("show");
	};
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.clickable-dropbtn')) {
		var dropdowns = document.getElementsByClassName("clickable-dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}