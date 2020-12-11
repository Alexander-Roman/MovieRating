CREATE DATABASE movie_rating CHARACTER SET UTF8 COLLATE utf8_bin;

USE movie_rating;

CREATE TABLE movies
(
    movie_id     BIGINT AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    director     VARCHAR(45),
    release_year YEAR,
    synopsis     VARCHAR(1000),
    poster_path  VARCHAR(255) UNIQUE,
    rating       DOUBLE(4, 2),
    PRIMARY KEY (movie_id)
);

CREATE TABLE accounts
(
    account_id BIGINT AUTO_INCREMENT,
    user_name  VARCHAR(45)                      NOT NULL UNIQUE,
    password   VARCHAR(40)                      NOT NULL,
    role       ENUM ('USER', 'EDITOR', 'ADMIN') NOT NULL,
    blocked    BOOLEAN                          NOT NULL,
    PRIMARY KEY (account_id)
);

CREATE TABLE comments
(
    comment_id BIGINT AUTO_INCREMENT,
    movie_id   BIGINT       NOT NULL,
    account_id BIGINT       NOT NULL,
    date_time  DATETIME     NOT NULL,
    text       VARCHAR(255) NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts (account_id) ON DELETE CASCADE
);

CREATE TABLE user_ratings
(
    rate_id    BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    movie_id   BIGINT,
    account_id BIGINT,
    assessment TINYINT(2) UNSIGNED NOT NULL,
    CONSTRAINT rate_pk PRIMARY KEY (movie_id, account_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts (account_id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX rate_index
    ON user_ratings (movie_id, account_id);