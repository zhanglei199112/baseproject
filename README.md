# baseproject
基础项目

数据库为本地mysql8.0以上版本  数据库名为testsql 

初始化脚本为：

```sql
DROP TABLE IF EXISTS `tb_user_`;
CREATE TABLE `tb_user_`  (
  `id_` bigint(0) NOT NULL,
  `name_` varchar(32) DEFAULT NULL,
  `age_` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
);
INSERT INTO `tb_user_` VALUES (1, '11', 22);
```

