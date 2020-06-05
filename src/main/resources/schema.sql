DROP TABLE IF EXISTS `GAMES_MASTER_LIST`;

CREATE TABLE `GAMES_MASTER_LIST` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `GAMES_DISPLAY_LIST`;

CREATE TABLE `GAMES_DISPLAY_LIST` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`id`)
);