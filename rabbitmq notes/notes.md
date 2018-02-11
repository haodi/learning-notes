Rabbit MQ 学习笔记
=============

### erlang 安装
    sudo vim /etc/apt/sources.list 增加 deb http://packages.erlang-solutions.com/ubuntu precise contrib
    wget http://packages.erlang-solutions.com/ubuntu/erlang_solutions.asc
    sudo apt-key add erlang_solutions.asc
    sudo apt-get update
    
### rabbitmq 安装&启动WEB管理界面
    下载依赖包：https://launchpad.net/ubuntu/utopic/powerpc/init-system-helpers/1.18
    安装：sudo dpkg -i init-system-helpers_1.18_all.deb
     
    查看Ubuntu版本号
    lsb_release -a
     
    按官方文档指引安装：https://www.rabbitmq.com/install-debian.html
     
    启动WEB管理界面
    sudo rabbitmq-plugins enable rabbitmq_management
     
    增加用户
    sudo rabbitmqctl add_user {user} {password}
     
    授权用户
    sudo rabbitmqctl authenticate_user {user} {password}
     
    rabbitmqctl  set_permissions -p /  admin '.*' '.*' '.*' 
     
    启用远程访问
    sudo rabbitmqctl set_user_tags {user} administrator
     
    停止服务
    sudo rabbitmqctl stop_app
     
    启动服务
    sudo rabbitmqctl start_app
     
     