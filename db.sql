DROP DATABASE IF EXISTS `JDBC_AM_25_12`;
CREATE DATABASE `JDBC_AM_25_12`;
USE `JDBC_AM_25_12`;

CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

DESC article;

SELECT * FROM article;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`body` = CONCAT('내용', SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

#####

CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(10) NOT NULL,
    loginPw CHAR(10) NOT NULL,
    `name` CHAR(10) NOT NULL
);

DESC `member`;

SELECT * FROM `member`;

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = 'test1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = 'test2';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = 'test3';