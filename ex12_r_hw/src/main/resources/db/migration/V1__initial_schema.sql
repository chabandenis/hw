DROP TABLE if EXISTS public.chess_fairs CASCADE;
DROP TABLE if EXISTS public.figuras CASCADE;
DROP TABLE if EXISTS public.games CASCADE;
DROP TABLE if EXISTS public.position_in_chess_fairs CASCADE;
DROP TABLE if EXISTS public.users CASCADE;

CREATE TABLE public.chess_fairs (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   CONSTRAINT pk_chess_fairs PRIMARY KEY (id)
);

CREATE TABLE public.figuras (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_figuras PRIMARY KEY (id)
);

CREATE TABLE public.games (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   user_black_id BIGINT,
   user_white_id BIGINT,
   user_next_id BIGINT,
   chess_fair_id BIGINT,
   date_game TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_games PRIMARY KEY (id)
);

CREATE TABLE public.position_in_chess_fairs (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   position_x INTEGER,
   position_y INTEGER,
   chess_fair_id BIGINT,
   figura_id BIGINT,
   CONSTRAINT pk_position_in_chess_fairs PRIMARY KEY (id)
);

CREATE TABLE public.users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   login VARCHAR(255),
   password VARCHAR,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE public.games ADD CONSTRAINT FK_GAMES_ON_CHESS_FAIR FOREIGN KEY (chess_fair_id) REFERENCES chess_fairs (id);

ALTER TABLE public.games ADD CONSTRAINT FK_GAMES_ON_USER_BLACK FOREIGN KEY (user_black_id) REFERENCES users (id);

ALTER TABLE public.games ADD CONSTRAINT FK_GAMES_ON_USER_NEXT FOREIGN KEY (user_next_id) REFERENCES users (id);

ALTER TABLE public.games ADD CONSTRAINT FK_GAMES_ON_USER_WHITE FOREIGN KEY (user_white_id) REFERENCES users (id);

ALTER TABLE public.position_in_chess_fairs ADD CONSTRAINT FK_POSITION_IN_CHESS_FAIRS_ON_CHESS_FAIR FOREIGN KEY (chess_fair_id) REFERENCES chess_fairs (id);

ALTER TABLE public.position_in_chess_fairs ADD CONSTRAINT FK_POSITION_IN_CHESS_FAIRS_ON_FIGURA FOREIGN KEY (figura_id) REFERENCES figuras (id);
