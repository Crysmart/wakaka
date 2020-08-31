create table if not exists dept(
    id bigint(20) not null comment '主键',
    dept_name varchar(255) not null comment '部门名称',
    dept_manager varchar(255) not null comment '部门管理',
    micro_name varchar(255) not null comment '微服务名称'
)engine = InnoDB default charset=utf8 comment '部门表';