drop database shopping;
create database if not exists shopping default charset utf8 collate utf8_general_ci;
use shopping;
# 用户表
create table `user`
(
    `id`          int(11)     not null auto_increment,
    `username`    varchar(50) not null,
    `password`    varchar(50) not null,
    `email`       varchar(50)  default null,
    `mobile`      varchar(20)  default null,
    `question`    varchar(100) default null comment '密码问题',
    `answer`      varchar(100) default null comment '密码答案',
    `role`        int(4)      not null comment '0-管理员 1-消费者 2-商家',
    `status`      int(4)       default '1',
    `create_time` datetime     default null comment '创建时间',
    `modify_time` datetime     default null comment '更新时间',
    primary key (`id`),
    unique key `username_unique` (`username`) using btree
) engine = InnoDB
  default charset = utf8;

insert into `user`(username, password, role)
VALUES ('root', 'e2e088c27648a29e2a265e1516532826', 0);

# 分类表 递归式设计
create table `category`
(
    `id`          int(11)     not null auto_increment,
    `parent_id`   int(11)     not null,
    `name`        varchar(50) not null comment '类别名称',
    `status`      int(4)   default '1',
    `sort_order`  int(4)   default null comment '排序',
    `create_time` datetime default null comment '创建时间',
    `modify_time` datetime default null comment '更新时间',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8;

insert into `category`(parent_id, name)
VALUES (0, '衣服');
insert into `category`(parent_id, name)
VALUES (0, '数码');
insert into `category`(parent_id, name)
VALUES (0, '食品');
insert into `category` (parent_id, name)
values (11, '男装');
insert into `category` (parent_id, name)
values (11, '女装');
insert into `category` (parent_id, name)
values (11, '童装');
update `category`
set parent_id = (select * from (select `id` from `category` where name = '衣服') `tmp`)
where parent_id = 11;
insert into `category` (parent_id, name)
values (11, '手机');
insert into `category` (parent_id, name)
values (11, '相机');
insert into `category` (parent_id, name)
values (11, '电脑');
update `category`
set parent_id = (select * from (select `id` from `category` where name = '数码') `tmp`)
where parent_id = 11;
insert into `category` (parent_id, name)
values (11, '水果');
insert into `category` (parent_id, name)
values (11, '生鲜');
insert into `category` (parent_id, name)
values (11, '零食');
update `category`
set parent_id = (select * from (select `id` from `category` where name = '食品') `tmp`)
where parent_id = 11;

# 产品表
create table `product`
(
    `id`          int(11)        not null auto_increment,
    `category_id` int(11)        not null,
    `name`        varchar(100)   not null,
    `subname`     varchar(200) default null,
    `main_image`  varchar(500) default null comment '主图地址',
    `sub_images`  text comment '图片地址 json',
    `detail`      text comment '详情',
    `price`       decimal(20, 2) not null comment '价格',
    `stock`       int(11)        not null comment '库存',
    `status`      int(4)       default '1' comment '1 在售 2 下架 0 删除',
    `create_time` datetime     default null comment '创建时间',
    `modify_time` datetime     default null comment '更新时间',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8;

# 购物车
create table `cart`
(
    `id`          int(11) not null auto_increment,
    `user_id`     int(11) not null,
    `product_id`  int(11) not null comment '商品id',
    `quantity`    int(11) not null comment '数量',
    `checked`     int(11) not null comment '是否勾选 1已勾选 0未勾选',
    `status`      int(4)   default '1' comment '1 有效 2 完成购买 0 删除',
    `create_time` datetime default null comment '创建时间',
    `modify_time` datetime default null comment '更新时间',
    primary key (`id`),
    key `user_id_index` (`user_id`) using btree
) engine = InnoDB
  default charset = utf8;

# 产品表
create table `order`
(
    `id`           int(11) not null auto_increment,
    `order_no`     bigint(20)     default null comment '订单号',
    `buyer_id`     int(11)        default null comment '买家id',
    `saler_id`     int(11)        default null comment '卖家id',
    `address_id`   int(11)        default null comment '地址id',
    `payment`      decimal(20, 2) default null comment '付款金额',
    `postage`      int(10)        default null comment '运费',
    `status`       int(4)         default '1' comment '0 删除 1 未付款 2 已付款 3 已发货 4 交易成功 5 交易关闭 6 取消',
    `create_time`  datetime       default null comment '创建时间',
    `payment_time` datetime       default null comment '支付时间',
    `send_time`    datetime       default null comment '发货时间',
    `end_time`     datetime       default null comment '收货时间/交易完成时间',
    `close_time`   datetime       default null comment '关闭时间',
    `modify_time`  datetime       default null comment '更新时间/交易关闭时间',
    primary key (`id`),
    unique key `order_no_index` (`order_no`) using btree
) engine = InnoDB
  default charset = utf8;

# 产品表
create table `order_item`
(
    `id`            int(11) not null auto_increment,
    `order_no`      bigint(20)     default null comment '订单号',
    `buyer_id`      int(11)        default null comment '买家id',
    `product_id`    int(11)        default null comment '货物id',
    `product_name`  varchar(100)   default null comment '商品名称',
    `product_image` varchar(500)   default null comment '图片url',
    `price`         decimal(20, 2) default null comment '单价',
    `quantity`      int(11)        default null comment '数量',
    `total_price`   decimal(20, 2) default null comment '总价',
    `status`        int(4)         default '1' comment '0 删除 1 有效',
    `create_time`   datetime       default null comment '创建时间',
    `modify_time`   datetime       default null comment '更新时间',
    primary key (`id`),
    key `order_no_index` (`order_no`) using btree,
    key `order_no_user_id_index` (`buyer_id`, `order_no`) using btree
) engine = InnoDB
  default charset = utf8;

create table `address`
(
    `id`                int(11) not null auto_increment,
    `user_id`           int(11)      default null comment '用户id',
    `receiver_name`     varchar(20)  default null comment '姓名',
    `receiver_mobile`   varchar(20)  default null comment '电话',
    `receiver_province` varchar(20)  default null comment '省份',
    `receiver_city`     varchar(20)  default null comment '城市',
    `receiver_district` varchar(20)  default null comment '区/县',
    `receiver_address`  varchar(200) default null comment '详细地址',
    `receiver_zip`      varchar(6)   default null comment '邮编',
    `status`            int(4)       default '1' comment '0 删除 1 有效',
    `create_time`       datetime     default null comment '创建时间',
    `modify_time`       datetime     default null comment '更新时间',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8;