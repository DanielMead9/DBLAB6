use ArcadeGames;

delimiter $$
CREATE PROCEDURE GetScores () BEGIN
select * from Score;
END $$
delimiter ;