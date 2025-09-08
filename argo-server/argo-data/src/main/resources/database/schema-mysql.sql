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
    id            int          not null auto_increment comment 'id',

    app_code      varchar(32)  not null default '' comment '应用编码',
    app_name      varchar(64)  not null default '' comment '应用名称',
    app_type      tinyint      not null default 0 comment '应用类型',
    icon          varchar(128) not null default '' comment '图标',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


--
-- 应用页面信息
--
create table if not exists ag_cms_app_page
(
    id            bigint       not null auto_increment comment 'id',

    app_id        int          not null default 0 comment '应用ID',
    page_code     varchar(16)  not null default '' comment '页面code',
    page_name     varchar(64)  not null default '' comment '页面名称',
    remark        varchar(128) not null default '' comment '备注',

    operator_code varchar(32)  not null default 0 comment '操作人ID',
    deleted       tinyint      not null default 0 comment '删除标记',
    create_time   datetime     not null default now() comment '创建时间',
    update_time   timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


--
-- 组件模型
--
create table if not exists ag_cms_fcm
(
    id             bigint       not null auto_increment comment 'id',

    name           varchar(64)  not null default '' comment '名称',
    icon           varchar(64)  not null default '' comment '图标',
    type           tinyint      not null default 0 comment '类型',
    app_id         int          not null default 0 comment '应用ID',
    scope          tinyint      not null default 0 comment '作用域',
    props          tinytext     not null default '[]' comment '属性',
    uniq_code      varchar(32)  not null default '' comment '唯一编码',
    remark         varchar(128) not null default '' comment '备注',

    operator_code  varchar(32)  not null default 0 comment '操作人ID',
    deleted        tinyint      not null default 0 comment '删除标记',
    create_time    datetime     not null default now() comment '创建时间',
    update_time    timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;



--
-- 组件实例
--
create table if not exists ag_cms_fci
(
    id                    bigint       not null auto_increment comment 'id',

    fcm_id                bigint       not null default 0 comment '组件模型ID',
    app_id                bigint       not null default 0 comment '应用ID',

    name                  varchar(64)  not null default '' comment '名称',
    fci_code              varchar(16)  not null default '' comment '组件实例编码',
    usage_scope           tinyint      not null default 2 comment '组件实例类型',
    effective_period_type tinyint      not null default 0 comment '生效周期类型',
    effective_start_time  datetime     not null default '1970-01-01 08:00:00.000' comment '生效开始时间',
    effective_end_time    datetime     not null default '1970-01-01 08:00:00.000' comment '生效结束时间',
    switch_flag           tinyint      not null default 0 comment '开关标记',
    remark                varchar(128) not null default '' comment '备注',

    operator_code         varchar(32)  not null default 0 comment '操作人ID',
    deleted               tinyint      not null default 0 comment '删除标记',
    create_time           datetime     not null default now() comment '创建时间',
    update_time           timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


--
-- 组件实例属性
--
create table if not exists ag_cms_fci_prop
(
    id                    bigint       not null auto_increment comment 'id',

    fci_id                bigint       not null default 0 comment '组件实例ID',

    prop_key              varchar(64)  not null default 0 comment '属性key',
    prop_value            varchar(256) not null default '' comment '属性值',
    data_bind_flag        tinyint      not null default 0 comment '是否绑定数据',

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
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


--
-- 组件实例属性
--
create table if not exists ag_cms_fci_request
(
    id                    bigint       not null auto_increment comment 'id',

    belong_id                bigint       not null default 0 comment '组件实例ID',
    bind_type             tinyint      not null default 0 comment '绑定类型: 1.组件实例，2.组件属性',

        data_url              varchar(128) not null default '' comment '数据URL',
        data_request_method   tinyint      not null default 0 comment '数据请求方式',
        data_request_params    varchar(128) not null default '' comment '数据请求参数',
        data_request_headers   varchar(128) not null default '' comment '数据请求头',

    operator_code         varchar(32)  not null default 0 comment '操作人ID',
    deleted               tinyint      not null default 0 comment '删除标记',
    create_time           datetime     not null default now() comment '创建时间',
    update_time           timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;




--
-- 页面组件实例关联表
--
create table if not exists ag_cms_page_fci
(
    id            bigint      not null auto_increment comment 'id',

    page_id       bigint      not null default 0 comment '页面ID',
    fci_id        bigint      not null default 0 comment '组件实例ID',
    seq_no        int         not null default 0 comment '排序号',

    operator_code varchar(32) not null default 0 comment '操作人ID',
    deleted       tinyint     not null default 0 comment '删除标记',
    create_time   datetime    not null default now() comment '创建时间',
    update_time   timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id),
    unique index idx_page_fci(page_id, fci_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;



--
-- 用户信息表
--
create table if not exists ag_adm_user
(
    id              int         not null auto_increment comment 'id',

    nick_name       varchar(32) not null default '' comment '昵称',
    username        varchar(32) not null default '' comment '用户名',
    password        varchar(32) not null default '' comment '密码',
    last_login_time datetime    not null default '1970-01-01 08:00:00.001' comment '上次登录时间',

    operator_code   varchar(32) not null default 0 comment '操作人ID',
    deleted         tinyint     not null default 0 comment '删除标记',
    create_time     datetime    not null default now() comment '创建时间',
    update_time     timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


--
-- 用户配置表
--
create table if not exists ag_adm_user_options
(
    id            bigint        not null auto_increment comment 'id',

    username      varchar(32)   not null default '' comment '用户名',
    option_key    varchar(32)   not null default '' comment '配置key',
    option_val    varchar(1024) not null default '' comment '配置值',

    operator_code varchar(32)   not null default 0 comment '操作人ID',
    deleted       tinyint       not null default 0 comment '删除标记',
    create_time   datetime      not null default now() comment '创建时间',
    update_time   timestamp     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    unique (username, option_key)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

