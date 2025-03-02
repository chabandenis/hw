INSERT INTO users (name, login, password, role)
VALUES('Первый Иван Иваныч', 'user1', '1', 'ADMIN'),
('Второй Петр Петрович', 'user2', '1', 'USER'),
('Третий Великий Петр', 'user3', '1', 'USER');

INSERT INTO figuras (name)
VALUES('Белый'), ('Черный');

INSERT INTO public.chess_fairs DEFAULT VALUES;

INSERT INTO public.chess_fairs DEFAULT VALUES;

INSERT INTO public.chess_fairs DEFAULT VALUES;

INSERT INTO public.games
(user_black_id, user_white_id, user_next_id, chess_fair_id, date_game)
VALUES(1, 2, 2, 1, '2022-10-10 00:00:00.000');

INSERT INTO public.games
(user_black_id, user_white_id, user_next_id, chess_fair_id, date_game)
VALUES(2, 1, 2, 2, '2023-09-09 00:00:00.000');

INSERT INTO public.games
(user_black_id, user_white_id, user_next_id, chess_fair_id, date_game)
VALUES(1, 3, 2, 3, '2024-08-08 00:00:00.000');


INSERT INTO public.position_in_chess_fairs
(position_y, position_x, chess_fair_id, figura_id)
VALUES
(8, 5, 1, 2), (8, 6, 1, 2), (8, 7, 1, 2), (8, 8, 1, 2),
(7, 5, 1, 2), (7, 6, 1, 2), (7, 7, 1, 2), (7, 8, 1, 2),
(6, 5, 1, 2), (6, 6, 1, 2), (6, 7, 1, 2), (6, 8, 1, 2),

(3, 1, 1, 1), (3, 2, 1, 1), (3, 3, 1, 1), (3, 4, 1, 1),
(2, 1, 1, 1), (2, 2, 1, 1), (2, 3, 1, 1), (2, 4, 1, 1),
(1, 1, 1, 1), (1, 2, 1, 1), (1, 3, 1, 1), (1, 4, 1, 1);

INSERT INTO public.position_in_chess_fairs
(position_y, position_x, chess_fair_id, figura_id)
VALUES
(8, 5, 2, 2), (8, 6, 2, 2), (8, 7, 2, 2), (8, 8, 2, 2),
(7, 5, 2, 2), (7, 6, 2, 2), (7, 7, 2, 2), (7, 8, 2, 2),
(6, 5, 2, 2), (6, 6, 2, 2), (6, 7, 2, 2), (6, 8, 2, 2),

(3, 1, 2, 1), (3, 2, 2, 1), (3, 3, 2, 1), (3, 4, 2, 1),
(2, 1, 2, 1), (2, 2, 2, 1), (2, 3, 2, 1), (2, 4, 2, 1),
(1, 1, 2, 1), (1, 2, 2, 1), (1, 3, 2, 1), (1, 4, 2, 1);

INSERT INTO public.position_in_chess_fairs
(position_y, position_x, chess_fair_id, figura_id)
VALUES
(8, 5, 3, 2), (8, 6, 3, 2), (8, 7, 3, 2), (8, 8, 3, 2),
(7, 5, 3, 2), (7, 6, 3, 2), (7, 7, 3, 2), (7, 8, 3, 2),
(6, 5, 3, 2), (6, 6, 3, 2), (6, 7, 3, 2), (6, 8, 3, 2),

(3, 1, 3, 1), (3, 2, 3, 1), (3, 3, 3, 1), (3, 4, 3, 1),
(2, 1, 3, 1), (2, 2, 3, 1), (2, 3, 3, 1), (2, 4, 3, 1),
(1, 1, 3, 1), (1, 2, 3, 1), (1, 3, 3, 1), (1, 4, 3, 1);