drop table ym_photo;
CREATE TABLE `ym_photo`
(
    `id`     int(10) unsigned NOT NULL AUTO_INCREMENT,
    `fid`    int(10) unsigned DEFAULT NULL COMMENT '贫困户基本信息表id',
    `photo1` varchar(100)      DEFAULT '' COMMENT '图片1',
    `photo2` varchar(100)      DEFAULT '' COMMENT '图片2,预留字段',
    `sequence` int(10)         COMMENT '图片序号',
    `gxsj`   datetime,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='家庭影像化图片信息';