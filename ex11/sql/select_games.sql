select
	g.id,
	g.date_game,
	u_black.id black_id,
	u_black."name" black_name,
	u_black.login black_login,
	u_black."password" black_password,
	u_white.id white_id,
	u_white."name" white_name,
	u_white.login white_login,
	u_white."password" white_password,
	u_next.id next_id,
	u_next."name" next_name,
	u_next.login next_login,
	u_next."password" next_password,
	cf.id cf_id,
	-- для удобства обработки json объекта из строки создаю в нужном виде. Решается проблема. получения недостающих данных из бд.	
	JSON_AGG(picf3) picf_json
from
	games g
left join users u_black on
	g.user_black_id = u_black.id
left join users u_white on
	g.user_white_id = u_white.id
left join users u_next on
	g.user_next_id = u_next.id
inner join chess_fairs cf on
	g.chess_fair_id = cf.id
left join position_in_chess_fairs picf on
	picf.chess_fair_id = cf.id,
	lateral (
	select
		picf2.id as id,
		picf2.position_x as "positionX",
		picf2.position_y as "positionY",
		picf2.chess_fair_id as "chessFairId",
		picf2.figura_id as "figuraId",
		f."name" as "figuraName"
	from
		position_in_chess_fairs picf2
	inner join figuras f on
		f.id = picf2.figura_id		
		) picf3
where
	picf3.id = picf.id
	and g.user_black_id in (:main_user, :second_user)
	and g.user_white_id in (:main_user, :second_user)
group by
	g.id,
	g.date_game,
	u_black.id,
	u_black."name",
	u_black.login,
	u_black."password",
	u_white.id,
	u_white."name",
	u_white.login,
	u_white."password",
	u_next.id,
	u_next."name",
	u_next.login,
	u_next."password",
	cf.id