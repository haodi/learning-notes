Mysql 学习笔记
=============

### Mysql 查询时不区分大小写
    
    MySql默认查询是不区分大小写的，如果需要区分他，必须在建表的时用Binary标示敏感的属性。
   
    CREATE TABLE NAME(
        name VARCHAR(10) BINARY
    );