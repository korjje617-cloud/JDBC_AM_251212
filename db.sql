DROP DATABASE IF EXISTS `JDBC_AM_25_12`;
CREATE DATABASE `JDBC_AM_25_12`;
USE `JDBC_AM_25_12`;

# 게시글 테이블
CREATE TABLE article (
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         title CHAR(100) NOT NULL,
                         `body` TEXT NOT NULL
);

# 회원 테이블
CREATE TABLE `member` (
                          id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          regDate DATETIME NOT NULL,
                          updateDate DATETIME NOT NULL,
                          loginId CHAR(30) NOT NULL,
                          loginPw CHAR(200) NOT NULL,
                          `name` CHAR(100) NOT NULL
);

# 게시글 TD
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

# 회원 TD
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2';

# 게시글 테이블에 memberId 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# memberId 값 추가
UPDATE article
SET memberId = 1
WHERE id IN (1,2);

UPDATE article
SET memberId = 2
WHERE id = 3;


DESC `member`;

SELECT *
FROM `member`;

DESC article;

SELECT *
FROM article;

##===============================###################### 테스트

SELECT * FROM articl ORDER BY id DESC


    # 대량생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
    `body` = CONCAT('내용', SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

# 대량생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = CONCAT('loginId', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
loginPw = CONCAT('loginPw', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`name` = CONCAT('name', SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

SELECT * FROM `member` WHERE loginId = 'test1';

SELECT 1 + 1;
SELECT 1 >= 1;

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test1';

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test3';

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목','2');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

UPDATE article
SET updateDate = NOW(),
    title = '',
    `body` = 'test1'
WHERE id = 1;

SELECT COUNT(*)
FROM article
WHERE id = 5;


UPDATE article
SET updateDate = NOW(),
    `body` = 'test3'
WHERE id = 3;


SELECT *
FROM article;