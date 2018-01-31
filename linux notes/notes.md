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
    cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l
    
    # 查看每个物理CPU中core的个数(即核数)
    cat /proc/cpuinfo| grep "cpu cores"| uniq
    
    # 查看逻辑CPU的个数
    cat /proc/cpuinfo| grep "processor"| wc -l
    
    # 查看CPU信息（型号）
    cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
    
    # 查看内存信息
    cat /proc/meminfo