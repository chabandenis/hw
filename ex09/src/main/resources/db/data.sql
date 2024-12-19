INSERT INTO users (name, login)
VALUES('Первый пользователь', 'user1'), ('Второй Пользователь', 'user2'), ('Третий пользователь', 'user3');

INSERT INTO figuras (name)
VALUES('Белый'), ('Черный');

INSERT INTO public.chess_fairs
(id)
VALUES(1);

INSERT INTO public.chess_fairs
(id)
VALUES(2);

INSERT INTO public.chess_fairs
(id)
VALUES(3);

INSERT INTO public.games
(id, user_black_id, user_white_id, user_next_id, chess_fair_id)
VALUES(1, 1, 2, 2, 1);

INSERT INTO public.games
(id, user_black_id, user_white_id, user_next_id, chess_fair_id)
VALUES(2, 2, 1, 2, 2);

INSERT INTO public.games
(id, user_black_id, user_white_id, user_next_id, chess_fair_id)
VALUES(3, 1, 3, 2, 3);


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