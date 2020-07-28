/*
SQLyog Community Edition- MySQL GUI v6.07
Host - 5.7.23-log : Database - springbootdb
*********************************************************************
Server version : 5.7.23-log
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `springbootdb`;

USE `springbootdb`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) DEFAULT NULL,
  `version` varchar(512) DEFAULT NULL,
  `name` varchar(512) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `stock_num` int(11) DEFAULT NULL,
  KEY `id` (`id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`version`,`name`,`price`,`product_id`,`stock_num`) values (1,NULL,'PC-VG24S2Z47',210,'2',98),(1,NULL,'PC-VG24S2Z47',210,'2',98),(1,NULL,'PC-VG24S2Z47',210,'2',98),(2,NULL,'PC-LG10JLFJB',307,'5',105),(2,NULL,'PC-LG10JLFJB',307,'5',105);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
