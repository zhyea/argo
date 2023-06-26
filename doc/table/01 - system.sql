--
-- 系统用户信息
--

create table if not exists s_user
(
    id          int                not null auto_increment primary key comment 'ID',

    username    varchar(32) unique not null comment '用户名',
    password    varchar(64)        not null comment '密码',

    name        varchar(32)                 default '' comment '姓名',
    email       varchar(64)                 default '' comment '邮箱',
    phone       varchar(16)                 default '' comment '电话',

    state       tinyint                     default 1 comment '用户状态',
    type        tinyint                     default 1 comment '用户类型',

    deleted     tinyint                     default 0,
    update_time timestamp          not null default current_timestamp on update current_timestamp,
    create_time datetime                    default current_date
);


--
-- 媒体素材
--
create table if not exists s_material
(
    id          int         not null auto_increment primary key comment 'ID',

    type        tinyint              default 2 comment '素材类型：1.图片, 2.音频, 3.视频',
    url         varchar(256)         default '' comment '素材链接',
    upload_user varchar(32) not null default '' comment '上传用户',

    deleted     tinyint              default 0,
    update_time timestamp   not null default current_timestamp on update current_timestamp,
    create_time datetime             default current_date
);



--
-- 模板信息
--
create table if not exists s_template
(
    id            int       not null auto_increment primary key comment 'ID',

    type          tinyint            default 2 comment '素材类型：1.定制, ',
    platform_code tinyint            default 1 comment '平台码',
    material_id   tinyint   not null default 0 comment '素材ID，指向模板screenshot',
    jump_url      varchar(64)        default '' comment '跳转链接',

    deleted       tinyint            default 0,
    update_time   timestamp not null default current_timestamp on update current_timestamp,
    create_time   datetime           default current_date
);
