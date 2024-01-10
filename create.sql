CREATE TABLE Author(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    biography VARCHAR(255),
    twitter VARCHAR(255),
    website VARCHAR(255),
    source_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Manga(
    id BIGINT NOT NULL AUTO_INCREMENT,
    source_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    original_title VARCHAR(255),
    status VARCHAR(20),
    is_locked BOOLEAN NOT NULL,
    year INTEGER,
    description VARCHAR(255),
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES Author(id)
);
CREATE TABLE Genre(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    source_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Manga_Genre(
    manga_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (manga_id, genre_id),
    FOREIGN KEY (manga_id) REFERENCES Manga(id),
    FOREIGN KEY (genre_id) REFERENCES Genre(id)
);