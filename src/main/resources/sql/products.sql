create table if not exists products
(
    id           int auto_increment primary key not null,
    product_id   varchar(8)                     not null,
    send_red_bag int                            not null # 0:不发放红包,1:发放红包
);

insert into products
values (1, '100', 0),
       (2, '101', 1)
