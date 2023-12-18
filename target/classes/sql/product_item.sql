create table if not exists product_item
(
    id   INT PRIMARY KEY,
    name varchar(8) not null,
    pid  INT        not null
);

insert into product_item(id, name, pid)
values (1, '商场', 0),
       (2, '电脑', 1),
       (3, '书籍', 1),
       (4, '台式电脑', 2),
       (5, '笔记本电脑', 2),
       (6, '游戏电脑', 4),
       (7, '办公电脑', 4),
       (8, '教育类', 3),
       (9, '科普类', 3),
       (10, '九年义务教育书籍', 8)
