Mysql 学习笔记
=============

### Mysql 查询时不区分大小写
    
    MySql默认查询是不区分大小写的，如果需要区分他，必须在建表的时用Binary标示敏感的属性。
   
    CREATE TABLE NAME(
        name VARCHAR(10) BINARY
    );
    
### 主从同步

    主从同步事件有3种形式：statement、row、mixed
    
    statement：会将对数据库操作的sql语句写入到binlog中。
    
    row：会将每一条数据的变化写入到binlog中。
    
    mixed：statement与row的混合。Mysql决定什么时候写statement格式的，什么时候写row格式的binlog。
    
    Q：如何保证主从复制数据一致性？
    
    在MySQL中，一次事务提交后，需要写undo、写redo、写binlog，写数据文件等等。在这个过程中，可能在某个步骤发生crash，就有可能导致主从数据的不一致。
    为了避免这种情况，我们需要调整主从上面相关选项配置，确保即便发生crash了，也不能发生主从复制的数据丢失。
    
    1、在master上修改配置
        innodb_flush_log_at_trx_commit = 1
        sync_binlog = 1
    上述两个选项的作用是：保证每次事务提交后，都能实时刷新到磁盘中，尤其是确保每次事务对应的binlog都能及时刷新到磁盘中，只要有了binlog，InnoDB就有办法做数据恢复，不至于导致主从复制的数据丢失。
    
    2、在slave上修改配置
        master_info_repository = "TABLE"
        relay_log_info_repository = "TABLE"
        relay_log_recovery = 1
    上述前两个选项的作用是：确保在slave上和复制相关的元数据表也采用InnoDB引擎，受到InnoDB事务安全的保护，而后一个选项的作用是开启relay log自动修复机制，发生crash时，会自动判断哪些relay log需要重新从master上抓取回来再次应用，以此避免部分数据丢失的可能性。
    
    通过上面几个选项的调整，就可以确保主从复制数据不会发生丢失了。但是，这并不能保证主从数据的绝对一致性，因为，有可能设置了ignore\do\rewrite等replication规则，或者某些SQL本身存在不确定因素，或者人为在slave上修改数据，最终导致主从数据不一致。这种情况下，可以采用 pt-table-checksum 和 pt-table-sync 工具来进行数据的校验和修复。