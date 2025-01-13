-- alatka.ALK_FIELD_DEFINITION definition

CREATE TABLE `ALK_FIELD_DEFINITION`
(
    `M_ID`                      int unsigned NOT NULL COMMENT 'ALK_MESSAGE_DEFINITION主键',
    `F_ID`                      int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `F_DOMAIN_NO`               int          NOT NULL COMMENT '域序号',
    `F_NAME`                    varchar(50)  NOT NULL COMMENT '域名称',
    `F_ALIAS_NAME`              varchar(10)  DEFAULT NULL COMMENT '域别名',
    `F_FIXED`                   tinyint(1) NOT NULL COMMENT '是否定长',
    `F_LENGTH`                  int          NOT NULL COMMENT '域字节长度',
    `F_MAX_LENGTH`              int          NOT NULL COMMENT '域最大长度',
    `F_CLAZZ`                   varchar(100) DEFAULT NULL COMMENT '域java类型',
    `F_PATTERN`                 varchar(20)  DEFAULT NULL COMMENT '域值格式，日期类型使用',
    `F_REMARK`                  varchar(100) NOT NULL COMMENT '域描述',
    `F_STATUS`                  varchar(10)  NOT NULL COMMENT '状态',
    `F_PAGE_SIZE_NAME`          varchar(50)  DEFAULT NULL COMMENT '分页记录数字段名称',
    `F_PARSE_TYPE`              varchar(10)  NOT NULL COMMENT '域解析类型',
    `F_LEN_PARSE_TYPE`          varchar(10)  DEFAULT NULL COMMENT '变长域解析类型',
    `F_EXIST_SUBDOMAIN`         tinyint(1) NOT NULL COMMENT '是否存在子域',
    `F_SUBDOMAIN_TYPE`          varchar(10)  DEFAULT NULL COMMENT '子域类型',
    `F_NON_SUBDOMAIN_EXCEPTION` tinyint(1) DEFAULT NULL COMMENT '未配置子域异常',
    PRIMARY KEY (`F_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=468 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报文域定义表';


-- alatka.ALK_MESSAGE_DEFINITION definition

CREATE TABLE `ALK_MESSAGE_DEFINITION`
(
    `M_ID`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `M_TYPE`        varchar(10)  NOT NULL COMMENT '报文类型 iso: 8583 fixed: 固定格式',
    `M_GROUP`       varchar(20)  NOT NULL COMMENT '报文分组',
    `M_CODE`        varchar(20)  NOT NULL COMMENT '报文交易码',
    `M_KIND`        varchar(20)  NOT NULL COMMENT '报文种类',
    `M_DOMAIN`      varchar(20)  DEFAULT NULL COMMENT '报文子域名称',
    `M_USAGE`       varchar(2)   DEFAULT NULL COMMENT '8583报文子域usage',
    `M_DOMAIN_TYPE` varchar(10)  DEFAULT NULL COMMENT '报文域类型',
    `M_HOLDER`      varchar(100) DEFAULT NULL COMMENT '报文实体类',
    `M_CHARSET`     varchar(100) DEFAULT NULL COMMENT '报文编码',
    `M_REMARK`      varchar(100) NOT NULL COMMENT '报文描述',
    PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报文定义表';