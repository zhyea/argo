--
--
-- argo cms 数据库
--
--

--
-- 组件
--
create table if not exists cms_component_info
(
    id            int          not null default 0 auto_increment comment 'id',

    name          varchar(64)  not null default '' comment '名称',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);