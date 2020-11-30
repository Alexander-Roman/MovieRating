package com.epam.movierating.view.tag;

import com.epam.movierating.entity.Movie;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class MovieListTag extends TagSupport {

    private List<Movie> movies;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int doStartTag() throws JspException {
        String movieTable = getTableMarkUp();
        try {
            pageContext.getOut().write(movieTable);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String getTableMarkUp() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<table class=\"table\" id=\"movies\">");

        markUpTableHeader(stringBuilder);
        markUpTableRows(stringBuilder);

        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private void markUpTableHeader(StringBuilder stringBuilder) {
        stringBuilder
                .append("<tr>")
                .append("<th>No.</th>")
                .append("<th>Movie</th>")
                .append("<th>Director</th>")
                .append("<th>Year</th>")
                .append("<th>Rating</th>")
                .append("</tr>");
    }

    private void markUpTableRows(StringBuilder stringBuilder) {

        for (Movie movie : movies) {
            stringBuilder
                    .append("<tr data-movie-page=\"controller?command=moviePage&movieId=")
                    .append(movie.getId())
                    .append("\">")
                    .append("<td>")
                    .append(movies.indexOf(movie) + 1)
                    .append("</td>")
                    .append("<td>")
                    .append(movie.getTitle())
                    .append("</td>")
                    .append("<td>")
                    .append(movie.getDirector())
                    .append("</td>")
                    .append("<td>")
                    .append(movie.getReleaseYear())
                    .append("</td>")
                    .append("<td>9/10</td>");
        }
    }
}
