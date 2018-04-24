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
     
 ### find命令
    -amin<分钟>：查找在指定时间曾被存取过的文件或目录，单位以分钟计算；
    -anewer<参考文件或目录>：查找其存取时间较指定文件或目录的存取时间更接近现在的文件或目录；
    -atime<24小时数>：查找在指定时间曾被存取过的文件或目录，单位以24小时计算；
    -cmin<分钟>：查找在指定时间之时被更改过的文件或目录；
    -cnewer<参考文件或目录>查找其更改时间较指定文件或目录的更改时间更接近现在的文件或目录；
    -ctime<24小时数>：查找在指定时间之时被更改的文件或目录，单位以24小时计算；
    -daystart：从本日开始计算时间；
    -depth：从指定目录下最深层的子目录开始查找；
    -expty：寻找文件大小为0 Byte的文件，或目录下没有任何子目录或文件的空目录；
    -exec<执行指令>：假设find指令的回传值为True，就执行该指令；
    -false：将find指令的回传值皆设为False；
    -fls<列表文件>：此参数的效果和指定“-ls”参数类似，但会把结果保存为指定的列表文件；
    -follow：排除符号连接；
    -fprint<列表文件>：此参数的效果和指定“-print”参数类似，但会把结果保存成指定的列表文件；
    -fprint0<列表文件>：此参数的效果和指定“-print0”参数类似，但会把结果保存成指定的列表文件；
    -fprintf<列表文件><输出格式>：此参数的效果和指定“-printf”参数类似，但会把结果保存成指定的列表文件；
    -fstype<文件系统类型>：只寻找该文件系统类型下的文件或目录；
    -gid<群组识别码>：查找符合指定之群组识别码的文件或目录；
    -group<群组名称>：查找符合指定之群组名称的文件或目录；
    -help或——help：在线帮助；
    -ilname<范本样式>：此参数的效果和指定“-lname”参数类似，但忽略字符大小写的差别；
    -iname<范本样式>：此参数的效果和指定“-name”参数类似，但忽略字符大小写的差别；
    -inum<inode编号>：查找符合指定的inode编号的文件或目录；
    -ipath<范本样式>：此参数的效果和指定“-path”参数类似，但忽略字符大小写的差别；
    -iregex<范本样式>：此参数的效果和指定“-regexe”参数类似，但忽略字符大小写的差别；
    -links<连接数目>：查找符合指定的硬连接数目的文件或目录；
    -iname<范本样式>：指定字符串作为寻找符号连接的范本样式；
    -ls：假设find指令的回传值为Ture，就将文件或目录名称列出到标准输出；
    -maxdepth<目录层级>：设置最大目录层级；
    -mindepth<目录层级>：设置最小目录层级；
    -mmin<分钟>：查找在指定时间曾被更改过的文件或目录，单位以分钟计算；
    -mount：此参数的效果和指定“-xdev”相同；
    -mtime<24小时数>：查找在指定时间曾被更改过的文件或目录，单位以24小时计算；
    -name<范本样式>：指定字符串作为寻找文件或目录的范本样式；
    -newer<参考文件或目录>：查找其更改时间较指定文件或目录的更改时间更接近现在的文件或目录；
    -nogroup：找出不属于本地主机群组识别码的文件或目录；
    -noleaf：不去考虑目录至少需拥有两个硬连接存在；
    -nouser：找出不属于本地主机用户识别码的文件或目录；
    -ok<执行指令>：此参数的效果和指定“-exec”类似，但在执行指令之前会先询问用户，若回答“y”或“Y”，则放弃执行命令；
    -path<范本样式>：指定字符串作为寻找目录的范本样式；
    -perm<权限数值>：查找符合指定的权限数值的文件或目录；
    -print：假设find指令的回传值为Ture，就将文件或目录名称列出到标准输出。格式为每列一个名称，每个名称前皆有“./”字符串；
    -print0：假设find指令的回传值为Ture，就将文件或目录名称列出到标准输出。格式为全部的名称皆在同一行；
    -printf<输出格式>：假设find指令的回传值为Ture，就将文件或目录名称列出到标准输出。格式可以自行指定；
    -prune：不寻找字符串作为寻找文件或目录的范本样式;
    -regex<范本样式>：指定字符串作为寻找文件或目录的范本样式；
    -size<文件大小>：查找符合指定的文件大小的文件；
    -true：将find指令的回传值皆设为True；
    -typ<文件类型>：只寻找符合指定的文件类型的文件；
    -uid<用户识别码>：查找符合指定的用户识别码的文件或目录；
    -used<日数>：查找文件或目录被更改之后在指定时间曾被存取过的文件或目录，单位以日计算；
    -user<拥有者名称>：查找符和指定的拥有者名称的文件或目录；
    -version或——version：显示版本信息；
    -xdev：将范围局限在先行的文件系统中；
    -xtype<文件类型>：此参数的效果和指定“-type”参数类似，差别在于它针对符号连接检查。
    
    eg.在/home目录下查找以.txt结尾的文件名
    
    find /home -name "*.txt"
     
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
    -t  This option specifies that lsof should produce terse output with process identifiers only and no header - e.g., so that the  output  may  be
                    piped to kill(1).  This option selects the -w option.
    
    查看进程的线程数
    ps -Lf <pid>
    
    查看进程PID
    lsof -ti:${port}
    
    查看进程打开了多少文件
    cat /proc/${PID}/fd 或者 lsof -p ${PID}
    
    查看每个进程文件描述符打开的最大限制
    ulimit -a
    
    文件描述符不够时
    1、Check your application logic and make sure it is not opening too many files unnecessarily (for example, In a loop there is file open, but it is not getting closed anywhere)
    2、Increase the open files limit on your system.
    
### 直接查看压缩文件里的内容
    
    zgrep ${keyWord} ${target .gz file}
    zcat ${keyWord} ${target .gz file}
    
    zcat  解压文件并将内容输出到标准输出
    zcmp  解压文件并且 byte by byte 比较两个文件
    zdiff 解压文件并且 line by line 比较两个文件
    zgrep 解压文件并且根据正则搜索文件内容
    ztest - Tests integrity of compressed files.
    zupdate - Recompresses files to lzip format.
    
    支持 bzip2, gzip, lzip and xz 格式。
    
### sed命令

    sed是一个流编辑器，是另外一种文本处理和转换工具，类似于awk。
    
### 查询进程下所有线程的运行情况
    top -Hp <pid> 查询进程下所有线程的运行情况（shift+p 按cpu排序，shift+m 按内存排序）