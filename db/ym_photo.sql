drop table ym_photo;
CREATE TABLE `ym_photo`
(
    `id`     int(10) unsigned NOT NULL AUTO_INCREMENT,
    `fid`    int(10) unsigned DEFAULT NULL COMMENT '贫困户基本信息表id',
    `photo1` varchar(50)      DEFAULT '' COMMENT '图片1',
    `photo2` varchar(50)      DEFAULT '' COMMENT '图片2',
    `photo3` varchar(50)      DEFAULT '' COMMENT '图片3',
    `photo4` varchar(50)      DEFAULT '' COMMENT '图片4',
    `photo5` varchar(50)      DEFAULT '' COMMENT '图片5',
    `photo6` varchar(50)      DEFAULT '' COMMENT '图片6',
    `gxsj`   datetime,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='家庭影像化图片信息';