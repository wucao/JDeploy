CREATE TABLE `java_deploy` (
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `final_name` varchar(255) NOT NULL COMMENT 'final_name',
  `url` varchar(255) NOT NULL COMMENT 'svn/git地址',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
