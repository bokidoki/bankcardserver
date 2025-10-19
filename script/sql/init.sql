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