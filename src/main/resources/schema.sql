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

DROP TABLE IF EXISTS `USERS`;

--CREATE TABLE `USERS` (
--  `id` int NOT NULL AUTO_INCREMENT,
--  `username` varchar(45) NOT NULL,
--  `password` varchar(45) NOT NULL,
--  `role` varchar(45) NOT NULL,
--  PRIMARY KEY (`id`)
--);

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ;

--
-- Table structure for table `authorities`
--
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ;
