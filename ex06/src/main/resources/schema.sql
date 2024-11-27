drop table if exists books_genres CASCADE;
drop table if exists genres CASCADE;
drop table if exists books CASCADE;
drop table if exists authors CASCADE;
drop table if exists comments CASCADE;

CREATE TABLE authors (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   full_name VARCHAR(255),
   CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE books (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   title VARCHAR(255),
   author_id BIGINT,
   CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE books_genres (
  book_id BIGINT NOT NULL,
   genre_id BIGINT NOT NULL
);

CREATE TABLE comments (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   text VARCHAR(255),
   book_id BIGINT,
   CONSTRAINT pk_comments PRIMARY KEY (id)
);

CREATE TABLE genres (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_genres PRIMARY KEY (id)
);

ALTER TABLE books ADD CONSTRAINT FK_BOOKS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE comments ADD CONSTRAINT FK_COMMENTS_ON_BOOK FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE books_genres ADD CONSTRAINT fk_boogen_on_book FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE books_genres ADD CONSTRAINT fk_boogen_on_genre FOREIGN KEY (genre_id) REFERENCES genres (id);