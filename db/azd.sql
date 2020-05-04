/*
SQLyog v10.2 
MySQL - 5.5.5-10.2.14-MariaDB-log : Database - ym_new
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ym_new` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ym_new`;

/*Table structure for table `ym_azd_detail` */

DROP TABLE IF EXISTS `ym_azd_detail`;

CREATE TABLE `ym_azd_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aid` int(10) unsigned NOT NULL COMMENT '安置点id',
  `ld` varchar(4) NOT NULL DEFAULT '' COMMENT '楼栋',
  `dy` varchar(4) NOT NULL DEFAULT '' COMMENT '单元',
  `fh` varchar(8) NOT NULL DEFAULT '' COMMENT '房号',
  `mj` decimal(5,2) unsigned NOT NULL DEFAULT 0.00 COMMENT '房屋面积',
  `hx` varchar(10) NOT NULL DEFAULT '' COMMENT '户型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安置点-详情表，保存单元，房号，户型，面积等信息';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
