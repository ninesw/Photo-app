CREATE DATABASE IF NOT EXISTS `photo_db`;
use `photo_db`;
set foreign_key_checks = 0;

DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album`(
	`id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS `photo`;
CREATE TABLE IF NOT EXISTS `photo`(
	`id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(255),
	`description` varchar(255),
    `url` varchar(255),
    `album_id` bigint,
    PRIMARY KEY (`id`),
    KEY `fk_album` (`album_id`),
    CONSTRAINT `fk_album` FOREIGN KEY (`album_id`) REFERENCES `album`(`id`)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS `comment`;

CREATE TABLE IF NOT EXISTS `comment` (
	`id` bigint NOT NULL auto_increment,
	`message` varchar(255),
    `photo_id` bigint,
    PRIMARY KEY (`id`),
    KEY `fk_photo` (`photo_id`),
    CONSTRAINT `fk_photo` FOREIGN KEY (`photo_id`) REFERENCES `photo`(`id`)
)ENGINE=InnoDB;

INSERT INTO `album` VALUES
(1, 'Random', 'random images');

INSERT INTO `album` VALUES
(1, 'Food', 'pictures of food');

INSERT INTO `photo` VALUES 
(0, 'Ice', 'Photos of ice', 'iceurl.com', 1),
(0, 'Bricks', 'Photos of bricks', 'bricksurl.com', 1),
(0, 'Lakes', 'Photos of lakes', 'lakesurl.com', 1),
(0, 'Sky', 'Photos of sky', 'skyurl.com', 1);

INSERT INTO `food` VALUES 
(0, 'Pizza', 'picture of pizza', 'pizza.food', 1),
(0, 'Ice cream', 'Photos of ice cream', 'icecream.food', 1),
(0, 'Cake', 'Photos of cake', 'cake.food', 1),
(0, 'Brownie', 'Photos of brownies', 'brownie.food', 1);


