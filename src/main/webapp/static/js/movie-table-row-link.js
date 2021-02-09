let movieList = document.getElementById("movies");
let movies = movieList.querySelectorAll("tr");
for (let movie of movies) {
    movie.onclick = function () {
        if (movie.dataset.moviePage != null) {
            document.location.href = movie.dataset.moviePage;
        }
    }
}