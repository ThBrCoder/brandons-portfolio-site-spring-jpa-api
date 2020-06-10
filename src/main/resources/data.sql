
INSERT INTO `GAMES_MASTER_LIST` VALUES 
	(1,'The Legend of Zelda: Ocarina of Time',1998),
	(2,'Mystical Ninja Starring Goemon',1997),
	(3,'Doom 64',1997),
	(4,'Super Mario 64',1996),
	(5,'007 Goldeneye',1997),
	(6,'Resident Evil 2',1999),
	(7,'Quest 64',1998),
	(8,'Pilotwings 64',1996),
	(9,'Mario Kart 64',1996),
	(10,'Diddy Kong Racing',1997),
	(11,'Yoshi''s Story',1997),
	(12,'Road Rash 64',1999),
	(13,'Star Fox 64',1997),
	(14,'Super Smash Bros.',1999),
	(15,'Bomberman 64',1997),
	(16,'Cruis''n USA',1996),
	(17,'Banjo-Kazooie',1998),
	(18,'Perfect Dark',2000),
	(19,'Mega Man 64',2001),
	(20,'Mischief Makers',1997);
	
INSERT INTO `GAMES_DISPLAY_LIST` VALUES 
	(1,'The Legend of Zelda: Ocarina of Time',1998),
	(2,'Mystical Ninja Starring Goemon',1997),
	(3,'Doom 64',1997),
	(4,'Super Mario 64',1996),
	(5,'007 Goldeneye',1997),
	(6,'Resident Evil 2',1999),
	(7,'Quest 64',1998),
	(8,'Pilotwings 64',1996),
	(9,'Mario Kart 64',1996);
	
--INSERT INTO `USERS` VALUES 
	--(1,'Admin','{noop}abc123','Role_Administrator'),
	--(2,'User','{noop}abc123','Role_User');
	
	

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('admin','{noop}pass',1),
('user','{noop}pass2',1);


--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('admin','ROLE_ADMINISTRATOR'),
('user','ROLE_USER');


	