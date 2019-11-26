# MyRabbit
一个高效，轻量级并且安全的ORM框架，你可以很轻松的从Mybatis迁移到MyRabbit。

[英文文档](README.md)

## 运行之前的准备
创建数据库
```sql
create database learn;
```
创建数据表
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
- 修改文件sqlMapperConfig.xml的密码改成你自己的数据库密码。
- 确保你在运行之前已经启动了你本地的MySQL服务。

## 运行
你可以先运行MyRabbitTest.java这个文件来保证这个框架可以实现你的基本的CRUD需求，而且使用起来非常像mybatis(或许更简单)。事实上我设计这个框架之前还在学习mybatis，但是当看过一些mybatis的源码，我感觉我也可以实现一个类似的框架（逃。。。

当我完成大部分需要实现的功能后，我可能会考虑将其部署到Maven的中心仓库。

## 关于正式生产
目前的版本并不支持正式生产环境，当时我会先使用这个框架在我的个人项目中来验证可行性。

## 如何做出贡献
- 直接开Issue
- 邮箱联系我来给我提建议 Yfc1004210191@gmail.com ayang818@qq.com