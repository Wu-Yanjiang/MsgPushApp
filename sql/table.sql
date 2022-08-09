create database msg_push_service;

use msg_push_service;

-- 消息配置表
drop table if exists message_config;
create table message_config(
    id         int unsigned auto_increment primary key,
    user_id    int unsigned not null comment '用户Id',
    company_id char(18)     not null default '' comment '企业微信企业Id',
    agent_id   char(8)      not null default '' comment '企业微信应用Id',
    app_secret char(50) comment '企业微信应用Secret',
    push_uid   varchar(50)  not null default '@all' comment '企业微信推送的用户id,默认@all'
);

-- 用户信息表
drop table if exists user_info;
create table user_info(
    id       int unsigned auto_increment primary key,
    name     varchar(20) not null default '' comment '用户名',
    email    varchar(30) not null default '' comment '邮箱',
    send_key char(35)    not null default '' comment '用户密钥'
);

drop table if exists msg_record;
create table msg_record (

)