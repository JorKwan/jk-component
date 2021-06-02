CREATE TABLE IF NOT EXISTS `file_info` (
`id` varchar(64) NOT NULL DEFAULT '' COMMENT 'id',
`group_code` varchar(100) NOT NULL DEFAULT '' COMMENT '集团编码：租户',
`file_name` varchar(128) NOT NULL DEFAULT '' COMMENT '文件名称:显示用',
`file_type` varchar(2) NOT NULL DEFAULT '0' COMMENT '文件类型：0-文件，1-目录',
`file_bucket` varchar(64) NOT NULL DEFAULT '0' COMMENT '文件分组:默认为应用名',
`file_path` varchar(512) COMMENT '文件路径：含文件名称 - 对应文件存储路径',
`file_size` int(11) NOT NULL DEFAULT '0' COMMENT '文件大小',
`business_id` varchar(32) DEFAULT NULL COMMENT '关联业务Id',
`expire_date` char(8) DEFAULT NULL COMMENT '保留期限',
`creator` varchar(32) DEFAULT NULL COMMENT '创建人',
`creation_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
`modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
`modified_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
`valid` int(11) DEFAULT NULL COMMENT '合法标识',
`ts` timestamp default current_timestamp on update current_timestamp NOT NULL COMMENT '乐观锁',
PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARSET = utf8mb4 COMMENT '文件信息';