use msg_push_service;

drop table if exists message_config;
create table message_config
(
    id         int unsigned auto_increment primary key,
    user_id    int unsigned not null comment '用户Id',
    company_id char(18)     not null default '' comment '企业微信企业Id',
    agent_id   char(8)      not null default '' comment '企业微信应用Id',
    app_secret char(50) comment '企业微信应用Secret',
    push_uid   varchar(50)  not null default '@all' comment '企业微信推送的用户id,默认@all'
) engine = InnoDB
  default character set utf8mb4;



