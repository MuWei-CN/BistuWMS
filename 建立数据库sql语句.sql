/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.16 : Database - wm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wm`;

/*Table structure for table `administratoruser` */

DROP TABLE IF EXISTS `administratoruser`;

CREATE TABLE `administratoruser` (
  `name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administratoruser` */

insert  into `administratoruser`(`name`,`password`) values ('root','12345678');

/*Table structure for table `commonuser` */

DROP TABLE IF EXISTS `commonuser`;

CREATE TABLE `commonuser` (
  `name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `commonuser` */

insert  into `commonuser`(`name`,`password`) values ('muwei','88888888'),('root','12345678');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `inventory_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `price` double unsigned NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Inventory_id` (`inventory_id`),
  CONSTRAINT `goods_of_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

insert  into `goods`(`inventory_id`,`id`,`name`,`quantity`,`price`,`description`) values (0,1,'篮球',10,20,'更新message2'),(0,2,'足球',20,19.8,'call'),(0,3,'网球',42,8,NULL),(0,4,'乒乓球',20,2,NULL),(0,5,'乒乓球拍',10,20,''),(0,6,'网球拍',10,30,''),(2,7,'羽毛球',16,3,''),(0,9,'足球鞋',90,120,''),(0,10,'手套',200,30,''),(2,11,'手套',15,20.1,'第二批购买手套'),(0,12,'毛衣',10,50,'');

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `creator_name` varchar(10) DEFAULT NULL,
  `id` int(10) unsigned NOT NULL,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CreateInventory` (`creator_name`),
  CONSTRAINT `CreateInventory` FOREIGN KEY (`creator_name`) REFERENCES `commonuser` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `inventory` */

insert  into `inventory`(`creator_name`,`id`,`message`) values ('root',0,'非仓库，一切购入但未入库的商品都装在该仓库'),('root',2,'测试的仓库');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
