--
--
-- argo cms 数据库
--
--

--
-- 组件
--
create table if not exists cms_component_define
(
    id            int          not null default 0 auto_increment comment 'id',


    name          varchar(64)  not null default '' comment '名称',
    icon          varchar(64)  not null default '' comment '图标',
    type          int          not null default 0 comment '类型',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);



create table if not exists cms_component_prop
(
    id            int          not null default 0 auto_increment comment 'id',

    component_id  int          not null default 0 comment '组件ID',


    name          varchar(64)  not null default '' comment '名称',
    remark        varchar(128) not null default '' comment '说明',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);