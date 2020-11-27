CREATE DATABASE movie_rating CHARACTER SET UTF8 COLLATE utf8_bin;

USE movie_rating;

CREATE TABLE movies
(
    movie_id     BIGINT AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    director     VARCHAR(45),
    release_year YEAR,
    synopsis     TEXT,
    poster_path  VARCHAR(255) UNIQUE,
    PRIMARY KEY (movie_id)
);

CREATE TABLE roles
(
    role_id INT AUTO_INCREMENT,
    role    VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
);

CREATE TABLE accounts
(
    account_id BIGINT AUTO_INCREMENT,
    user_name  VARCHAR(45) NOT NULL UNIQUE,
    password   VARCHAR(40) NOT NULL,
    role_id    INT         NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE INDEX user_name_index
    ON accounts (user_name);

CREATE TABLE comments
(
    comment_id BIGINT AUTO_INCREMENT,
    movie_id   BIGINT       NOT NULL,
    account_id BIGINT       NOT NULL,
    date_time  DATETIME     NOT NULL,
    text       VARCHAR(255) NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);

CREATE TABLE user_rating
(
    movie_id   BIGINT,
    account_id BIGINT,
    assessment TINYINT(2) UNSIGNED NOT NULL,
    CONSTRAINT assessment_id PRIMARY KEY (movie_id, account_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);