create table if not exists business_launch
(
    id              INT        NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    business_detail varchar(255) not null,
    target_city     varchar(32),
    target_sex      varchar(8),
    target_product  varchar(32)
);


insert into business_launch
    (id, business_detail, target_city, target_sex, target_product)
values (1, '计算机投放业务', '', '', 'computer,phone'),
       (2, '某奢饰品投放业务', '', 'F', 'Female bag'),
       (3, '北方某店投放业务', 'bj,tj', '', ''),
       (4, '平台优惠券', '', '', '');