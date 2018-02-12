Linux 学习笔记
=============

### ln命令
    ln命令用来为文件创件连接，连接类型分为硬连接和符号连接两种，默认的连接类型是硬连接。如果要创建符号连接必须使用"-s"选项。
    注意：符号链接文件不是一个独立的文件，它的许多属性依赖于源文件，所以给符号链接文件设置存取权限是没有意义的。
    
    语法：ln(选项)(参数)
    
    选项：
    -b或--backup：删除，覆盖目标文件之前的备份；
    -d或-F或——directory：建立目录的硬连接；
    -f或——force：强行建立文件或目录的连接，不论文件或目录是否存在；
    -i或——interactive：覆盖既有文件之前先询问用户；
    -n或--no-dereference：把符号连接的目的目录视为一般文件；
    -s或——symbolic：对源文件建立符号连接，而非硬连接；
    -S<字尾备份字符串>或--suffix=<字尾备份字符串>：用"-b"参数备份目标文件后，备份文件的字尾会被加上一个备份字符串，预设的备份字符串是符号“~”，用户可通过“-S”参数来改变它；
    -v或——verbose：显示指令执行过程；
    -V<备份方式>或--version-control=<备份方式>：用“-b”参数备份目标文件后，备份文件的字尾会被加上一个备份字符串，这个字符串不仅可用“-S”参数变更，当使用“-V”参数<备份方式>指定不同备份方式时，也会产生不同字尾的备份字符串；
    --help：在线帮助；
    --version：显示版本信息。
    
    参数：
    源文件：指定连接的源文件。如果使用-s选项创建符号连接，则“源文件”可以是文件或者目录。创建硬连接时，则“源文件”参数只能是文件；
    目标文件：指定源文件的目标连接文件。
    
    ln -s /usr/local/nginx/sbin/nginx /usr/bin/nginx
    
### 查看CPU核数
    # 总核数 = 物理CPU个数 X 每颗物理CPU的核数 
    # 总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU的核数 X 超线程数
    
    # 查看物理CPU个数
    cat /proc/cpuinfo | grep "physical id" | sort | uniq | wc -l
    
    # 查看每个物理CPU中core的个数(即核数)
    cat /proc/cpuinfo | grep "cpu cores" | uniq
    
    # 查看逻辑CPU的个数
    cat /proc/cpuinfo | grep "processor" | wc -l
    
    # 查看CPU信息（型号）
    cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
    
    # 查看内存信息
    cat /proc/meminfo
    
### 查看指定文件夹里按文件大小排序的前三个文件
    du --max-depth=1 -h /data | sort -hr | head -3
    
    --max-depth=1，指定只统计第一层文件夹的大小
    sort -hr，按人类可读的数值、相反的顺序展示
    
### 使用tcpdump查看端口接收到的数据
     sudo tcpdump -s 0 -A port ${port}
     
### 查看设备、文件被哪个进程使用命令lsof

    在linux环境下，任何事物都以文件的形式存在，通过文件不仅仅可以访问常规数据，还可以访问网络连接和硬件。
    所以如传输控制协议 (TCP) 和用户数据报协议 (UDP) 套接字等，系统在后台都为该应用程序分配了一个文件描述符，无论这个文件
    的本质如何，该文件描述符为应用程序与基础操作系统之间的交互提供了通用接口。因为应用程序打开文件的描述符列表提供了大量关于
    这个应用程序本身的信息，因此通过lsof工具能够查看这个列表对系统监测以及排错将是很有帮助的。

    -a：列出打开文件存在的进程；
    -c<进程名>：列出指定进程所打开的文件；
    -g：列出GID号进程详情；
    -d<文件号>：列出占用该文件号的进程；
    +d<目录>：列出目录下被打开的文件；
    +D<目录>：递归列出目录下被打开的文件；
    -n<目录>：列出使用NFS的文件；
    -i<条件>：列出符合条件的进程。（4、6、协议、:端口、 @ip ）
    -p<进程号>：列出指定进程号所打开的文件；
    -u：列出UID号进程详情；
    -h：显示帮助信息；
    -v：显示版本信息。
    
    查看进程打开了多少文件
    cat /proc/${PID}/fd 或者 lsof -p ${PID}
    
    查看每个进程文件描述符打开的最大限制
    ulimit -a
    
    文件描述符不够时
    1、Check your application logic and make sure it is not opening too many files unnecessarily (for example, In a loop there is file open, but it is not getting closed anywhere)
    2、Increase the open files limit on your system.