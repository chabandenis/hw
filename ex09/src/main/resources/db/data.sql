INSERT INTO users (name)
VALUES('user 1'), ('user 2'), ('user 3');

INSERT INTO figuras (name)
VALUES('Белый'), ('Черный');

INSERT INTO public.chess_fairs
(id)
VALUES(1);

INSERT INTO public.games
(id, user_black_id, user_white_id, user_next_id, chess_fair_id)
VALUES(1, 1, 2, 2, 1);

INSERT INTO public.position_in_chess_fairs
(position_y, position_x, chess_fair_id, figura_id)
VALUES
(8, 5, 1, 2), (8, 6, 1, 2), (8, 7, 1, 2), (8, 8, 1, 2),
(7, 5, 1, 2), (7, 6, 1, 2), (7, 7, 1, 2), (7, 8, 1, 2),
(6, 5, 1, 2), (6, 6, 1, 2), (6, 7, 1, 2), (6, 8, 1, 2),

(3, 1, 1, 1), (3, 2, 1, 1), (3, 3, 1, 1), (3, 4, 1, 1),
(2, 1, 1, 1), (2, 2, 1, 1), (2, 3, 1, 1), (2, 4, 1, 1),
(1, 1, 1, 1), (1, 2, 1, 1), (1, 3, 1, 1), (1, 4, 1, 1);