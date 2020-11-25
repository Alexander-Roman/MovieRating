package com.epam.movierating.logic;

import com.epam.movierating.entity.Movie;

import java.util.Arrays;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Override
    public List<Movie> getAll() {
        return Arrays.asList(
                new Movie(1, "The Hitchhiker's Guide to the Galaxy", "Garth Jennings", 2005),
                new Movie(2, "Pulp Fiction", "Quentin Tarantino", 1994),
                new Movie(3, "Il buono, il brutto, il cattivo", "Sergio Leone", 1966),
                new Movie(4, "Fight Club", "David Fincher", 1999),
                new Movie(5, "Forrest Gump", "Robert Zemeckis", 1994),
                new Movie(6, "Shichinin no samurai", "Akira Kurosawa", 1954),
                new Movie(7, "Léon", "Luc Besson", 1994),
                new Movie(8, "The Usual Suspects", "Bryan Singer", 1995),
                new Movie(9, "Apocalypse Now", "Francis Ford Coppola", 1979),
                new Movie(10, "Memento", "Christopher Nolan", 2000),
                new Movie(11, "Eternal Sunshine of the Spotless Mind", "Michel Gondry", 2004),
                new Movie(12, "Snatch", "Guy Ritchie", 2000),
                new Movie(13, "Scarface", "Brian De Palma", 1983)
        );
    }
}
