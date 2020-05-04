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

/*Table structure for table `ym_jtjbqk_jtcy` */

DROP TABLE IF EXISTS `ym_jtjbqk_jtcy`;

CREATE TABLE `ym_jtjbqk_jtcy` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fid` int(10) unsigned NOT NULL COMMENT '贫困户基本信息表id',
  `xm` varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',
  `xb` char(1) NOT NULL DEFAULT '' COMMENT '性别1男0女',
  `zjhm` varchar(25) NOT NULL DEFAULT '' COMMENT '证件号码',
  `yhzgx` char(2) NOT NULL DEFAULT '' COMMENT '与户主关系，1户主2配偶3之子4之女5之父6之母7之儿媳8之孙子9之孙女10之外孙11之外孙女12之兄弟姐妹13之兄弟媳妇14之岳母15之侄子16之侄女17之祖母99其他',
  `mz` char(2) NOT NULL DEFAULT '' COMMENT '民族',
  `zzmm` char(2) NOT NULL DEFAULT '' COMMENT '政治面貌',
  `whcd` char(2) NOT NULL DEFAULT '' COMMENT '文化程度，1在读、2文盲或半文盲、3小学、4初中、5高中、6中专、7大专、8大学本科及以上',
  `zxszk` tinyint(2) unsigned NOT NULL DEFAULT 0 COMMENT '在校生状况',
  `sxyy` tinyint(2) unsigned NOT NULL DEFAULT 0 COMMENT '失学原因',
  `jkzk` char(2) NOT NULL DEFAULT '' COMMENT '健康状况，1健康、2残疾、3慢性病、4残疾且慢性病、5患有大病',
  `ldjn` tinyint(2) unsigned NOT NULL DEFAULT 0 COMMENT '劳动技能',
  `sfhjpth` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否会讲普通话',
  `sfcjcxjmjbytlbx` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否参加城乡居民基本医疗保险',
  `sfcjsybcylbx` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否参加商业补充医疗保险',
  `sfxsncjmzjshbz` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否享受农村居民最低生活保障',
  `sfcjcxjmybyanglbx` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否参加城乡居民基本养老保险',
  `sfxsrsywbxbt` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否享受人身意外保险补贴',
  `lxdh` varchar(15) NOT NULL DEFAULT '' COMMENT '联系电话',
  `bz` varchar(600) NOT NULL DEFAULT '' COMMENT '备注',
  `scbz` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '删除标志',
  `shzt` char(2) NOT NULL DEFAULT '' COMMENT '生活状态，01正常生活02已故03不共同生活',
  `sgny` date DEFAULT NULL COMMENT '身故年月',
  `rklx` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '人口类型，1受益人口2非受益人口',
  `zdqk` tinyint(2) unsigned DEFAULT NULL COMMENT '在读情况，1学龄前儿童、2幼儿园、3小学、4初中、5高中、6中专、7大专、8大学本科及以上',
  `zxjdxx` varchar(100) NOT NULL DEFAULT '' COMMENT '在校就读学校',
  `cjzh` varchar(25) NOT NULL DEFAULT '' COMMENT '残疾证号',
  `cjdj` tinyint(2) unsigned DEFAULT NULL COMMENT '残疾等级',
  `djbhdmxbk` varchar(25) NOT NULL DEFAULT '' COMMENT '登记并获得慢性病卡',
  `mxbsm` varchar(100) NOT NULL DEFAULT '' COMMENT '慢性病说明',
  `dbjz` char(1) NOT NULL DEFAULT '' COMMENT '大病救助，1有0无',
  `sfxszcdd` char(1) NOT NULL DEFAULT '' COMMENT '是否享受政策兜底1是、2否，社会兜底包含五保和低保',
  `jyyy` char(1) NOT NULL DEFAULT '' COMMENT '就业意愿，1有0无',
  `jyqk` char(2) NOT NULL DEFAULT '' COMMENT '就业情况，1稳定就业；2无稳定就业，无就业意愿可不填，其中单位工作半年以上为稳定就业；3创业',
  `jycydw` varchar(80) NOT NULL DEFAULT '' COMMENT '就业、创业单位',
  `wdjycydy` tinyint(1) unsigned DEFAULT NULL COMMENT '稳定就业、创业地域，1县内、2县外区内、3区外',
  `ylbxlx` char(2) NOT NULL DEFAULT '' COMMENT '养老保险类型，01城乡居民养老保险02城镇职工养老保险',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2423 DEFAULT CHARSET=utf8 COMMENT='贫困户家庭基本情况-家庭成员表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
