# MyRabbit
A efficient, lightweight and safe ORM framework which usage is similar to mybatis.

[中文文档](README_ZH.md)

## Prepare to run it!
create database;
```sql
create database learn;
```
create table;
```sql
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1090809813 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
```
- Change the file sqlMapperConfig.xml` password to your own password. 
- Make show you had already running your mysql server in your localhost.

## Run it
You can run the MyRabbitTest.java to make sure this framework can work this unit test include simple select, insert,
update, delete demo which is used really likes mybatis, actually I designed this framework to learn mybatis but after learning some source code from mybatis, I think may be i can do a similar one. hhh. After I finish more necessary
features. I will deploy it to maven central warehouse.

## About production environment
It did not support to use into production environment directly. But I will use this framework in my personly project to ensure the feasibility. But you can read the source code to know how I design it. Make fun!

## How to contribute   
- Open your issues directly !
- Email me Yfc1004210191@gmail.com or ayang818@qq.com to tell me your advice. 