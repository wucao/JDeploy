CREATE TABLE `java_deploy` (
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `url` varchar(255) NOT NULL COMMENT 'svn/git地址',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `java_web_deploy` (
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `url` varchar(255) NOT NULL COMMENT 'svn/git地址',
  `context_path` varchar(255) NOT NULL COMMENT 'Web项目contextPath',
  `port` int(11) NOT NULL COMMENT '端口号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;