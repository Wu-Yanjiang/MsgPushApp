use msg_push_service;

drop table if exists user_info;
create table user_info
(
    id       int unsigned auto_increment primary key,
    name     varchar(20) not null default '' comment '用户名',
    email    varchar(30) not null default '' comment '邮箱',
    send_key char(35)    not null default '' comment '用户密钥'
) engine = InnoDB
  default character set utf8mb4;

