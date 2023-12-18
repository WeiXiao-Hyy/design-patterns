DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id        BIGINT      NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    user_name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    password  VARCHAR(30) NULL DEFAULT NULL COMMENT '密码',
    age       INT         NULL DEFAULT NULL COMMENT '年龄',
    email     VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DELETE
FROM `user`;

INSERT INTO `user` (id, user_name, password, age, email)
VALUES (1, 'Jone', '991011', 18, 'test1@baomidou.com'),
       (2, 'Jack', '991011', 20, 'test2@baomidou.com'),
       (3, 'Tom', '991011', 28, 'test3@baomidou.com'),
       (4, 'Sandy', '991011', 21, 'test4@baomidou.com'),
       (5, 'Billie', '991011', 24, 'test5@baomidou.com');