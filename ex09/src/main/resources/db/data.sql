INSERT INTO public.users ("name")
VALUES('user 1'), ('user 2'), ('user 3');

INSERT INTO public.figuras ("name")
VALUES('Белый'), ('Черный');

INSERT INTO public.chess_fairs
(id)
VALUES(1);

INSERT INTO public.games
(id, user_black_id, user_white_id, user_next_id, chess_fair_id)
VALUES(1, 1, 2, 2, 1);

INSERT INTO public.position_in_chess_fairs
(id, position_x, position_y, chess_fair_id, figura_id)
VALUES(1, 1, 1, 1, 1), (2, 1, 2, 1, 1), (3, 1, 3, 1, 2), (4, 1, 4, 1, 2);