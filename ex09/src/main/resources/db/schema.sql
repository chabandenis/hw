DROP TABLE if EXISTS public.chess_fairs CASCADE;
DROP TABLE if EXISTS public.figuras CASCADE;
DROP TABLE if EXISTS public.games CASCADE;
DROP TABLE if EXISTS public.position_in_chess_fair CASCADE;
DROP TABLE if EXISTS public.users CASCADE;

CREATE TABLE chess_fairs (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   CONSTRAINT pk_chess_fairs PRIMARY KEY (id)
);

CREATE TABLE figuras (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_figuras PRIMARY KEY (id)
);

CREATE TABLE games (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   user_black_id BIGINT,
   user_white_id BIGINT,
   chess_fair_id BIGINT,
   CONSTRAINT pk_games PRIMARY KEY (id)
);

CREATE TABLE position_in_chess_fair (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   position_x INTEGER,
   position_y INTEGER,
   chess_fair_id BIGINT,
   figura_id BIGINT,
   CONSTRAINT pk_position_in_chess_fair PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE games ADD CONSTRAINT FK_GAMES_ON_CHESS_FAIR FOREIGN KEY (chess_fair_id) REFERENCES chess_fairs (id);

ALTER TABLE games ADD CONSTRAINT FK_GAMES_ON_USER_BLACK FOREIGN KEY (user_black_id) REFERENCES users (id);

ALTER TABLE games ADD CONSTRAINT FK_GAMES_ON_USER_WHITE FOREIGN KEY (user_white_id) REFERENCES users (id);

ALTER TABLE position_in_chess_fair ADD CONSTRAINT FK_POSITION_IN_CHESS_FAIR_ON_CHESS_FAIR FOREIGN KEY (chess_fair_id) REFERENCES chess_fairs (id);

ALTER TABLE position_in_chess_fair ADD CONSTRAINT FK_POSITION_IN_CHESS_FAIR_ON_FIGURA FOREIGN KEY (figura_id) REFERENCES figuras (id);