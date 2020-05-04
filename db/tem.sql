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

/*Table structure for table `ym_azd` */

DROP TABLE IF EXISTS `ym_azd`;

CREATE TABLE `ym_azd` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mc` varchar(60) NOT NULL DEFAULT '' COMMENT '名称',
  `jc` varchar(30) NOT NULL DEFAULT '' COMMENT '简称',
  `dz` varchar(200) DEFAULT '',
  `lrrq` date DEFAULT NULL COMMENT '录入日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安置点';

/*Table structure for table `ym_bfr` */

DROP TABLE IF EXISTS `ym_bfr`;

CREATE TABLE `ym_bfr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `xm` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `xb` char(2) NOT NULL DEFAULT '' COMMENT '性别',
  `gzdw` varchar(100) NOT NULL DEFAULT '' COMMENT '工作单位',
  `zw` varchar(20) NOT NULL DEFAULT '' COMMENT '职务',
  `lxdh` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `lrrq` date DEFAULT NULL COMMENT '录入日期',
  `bgrq` date DEFAULT NULL COMMENT '变更日期',
  `zx` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '状态，1正常，0删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮扶人信息';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
