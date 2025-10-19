CREATE DATABASE IF NOT EXISTS bank_card;

USE bank_card;

CREATE TABLE `users`
(
    `id`          bigint    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(255)   DEFAULT NULL COMMENT '姓名',
    `password`    varchar(255)   DEFAULT NULL COMMENT '密码',
    `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改日期',
    `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息表';

CREATE TABLE `device_info`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint NOT NULL COMMENT '用户ID',
    `brand`       varchar(255) DEFAULT NULL COMMENT '品牌',
    `model`       varchar(255) DEFAULT NULL COMMENT '型号',
    `uuid`        varchar(255) DEFAULT NULL COMMENT '设备UUID',
    `modify_date` datetime     DEFAULT NULL COMMENT '修改日期',
    `create_date` datetime     DEFAULT NULL COMMENT '创建日期',
    PRIMARY KEY (`id`),
    KEY `fk_device_info_user` (`user_id`),
    CONSTRAINT `fk_device_info_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备信息表';