DataPagination
===================


项目名 ：**DataPagination**
接口： **INewsDao**
全局对象：**NewsData**
第三方库：**C3P0**、**DBUtil**
**项目相关配置文件** 

----------


说明文档
-------------
**DDL_for_mysql**


    create table `ycf_db`.`news`(
        `newsTitle` VARCHAR(128) not null,
       `newsDescription` VARCHAR(512) not null,
       `newsDate` DATETIME not null,
       `newsURL` VARCHAR(256) not null
    );
