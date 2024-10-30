--
--
-- argo cms 数据库
--
--


--
-- 应用信息
--
create table if not exists ag_cms_app
(
    id            int          not null default 0 auto_increment comment 'id',

    app_code      varchar(32)  not null default '' comment '应用编码',
    app_name      varchar(64)  not null default '' comment '应用名称',
    icon          varchar(64)  not null default '' comment '图标',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);



--
-- 组件模型
--
create table if not exists ag_cms_fcm
(
    id             int          not null default 0 auto_increment comment 'id',

    name           varchar(64)  not null default '' comment '名称',
    icon           varchar(64)  not null default '' comment '图标',
    type           tinyint      not null default 0 comment '类型',
    app_code       varchar(32)  not null default '' comment '应用编码',
    scope          tinyint      not null default 0 comment '作用域',
    props          tinytext     not null default '[]' comment '属性',
    data_bind_flag tinyint      not null default 0 comment '是否绑定数据',
    uniq_code      varchar(32)  not null default '' comment '唯一编码',
    remark         varchar(128) not null default '' comment '备注',

    operator_code  varchar(32)  not null default 0 comment '操作人ID',
    deleted        tinyint      not null default 0 comment '删除标记',
    create_time    datetime     not null default now() comment '创建时间',
    update_time    timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);


--
-- 应用页面信息
--
create table if not exists ag_cms_app_page
(
    id            bigint       not null default 0 auto_increment comment 'id',

    app_id        int          not null default '' comment '应用ID',
    page_code     varchar(16)  not null default '' comment '页面code',
    page_name     varchar(64)  not null default '' comment '页面名称',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);



--
-- 组件实例
--
create table if not exists ag_cms_fci
(
    id                    bigint       not null default 0 auto_increment comment 'id',

    page_id               bigint       not null default 0 comment '页面ID',
    fcm_id                int          not null default 0 comment '组件模型ID',

    data_url              varchar(128) not null default '' comment '数据URL',
    effective_period_type tinyint      not null default 0 comment '生效周期类型',
    effective_start_time  datetime     not null default '1970-01-01 08:00:00.000' comment '生效开始时间',
    effective_end_time    datetime     not null default '1970-01-01 08:00:00.000' comment '生效结束时间',
    remark                varchar(128) not null default '' comment '备注',

    operator_code         varchar(32)  not null default 0 comment '操作人ID',
    deleted               tinyint      not null default 0 comment '删除标记',
    create_time           datetime     not null default now() comment '创建时间',
    update_time           timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);


--
-- 组件实例属性
--
create table if not exists ag_cms_fci_prop
(
    id                    int          not null default 0 auto_increment comment 'id',

    fci_id                int          not null default 0 comment '组件实例ID',

    prop_key              varchar(64)  not null default 0 comment '属性key',
    prop_value            varchar(256) not null default '' comment '属性值',
    prop_value_selector   varchar(64)  not null default '' comment '属性值选择器',
    effective_period_type tinyint      not null default 0 comment '生效周期类型',
    effective_start_time  datetime     not null default '1970-01-01 08:00:00.000' comment '生效开始时间',
    effective_end_time    datetime     not null default '1970-01-01 08:00:00.000' comment '生效结束时间',
    remark                varchar(128) not null default '' comment '备注',

    operator_code         varchar(32)  not null default 0 comment '操作人ID',
    deleted               tinyint      not null default 0 comment '删除标记',
    create_time           datetime     not null default now() comment '创建时间',
    update_time           timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);



--
-- 用户信息表
--
create table if not exists ag_adm_user
(
    id              int         not null default 0 auto_increment comment 'id',

    nick_name       varchar(32) not null default '' comment '昵称',
    username        varchar(32) not null default '' comment '用户名',
    password        varchar(32) not null default '' comment '密码',
    last_login_time datetime    not null default '1970-01-01 08:00:00.001' comment '上次登录时间',

    operator_code   varchar(32) not null default 0 comment '操作人ID',
    deleted         tinyint     not null default 0 comment '删除标记',
    create_time     datetime    not null default now() comment '创建时间',
    update_time     timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
);