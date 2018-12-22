JAVA 学习笔记
=============

### JDK8中流的理解
    《Java 8 实战》中给出的概念："从支持数据处理操作的源生成的元素序列"
    
    元素序列：就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序值。因为集合是数据结构，所以它的主要目的是以特定的时间/空间复杂度存
    储和访问元素（如ArrayList 与 LinkedList）。但流的目的在于表达计算，比如你前面见到的filter、sorted和map。集合讲的是数据，流讲的是计算。
    
    源：流会使用一个提供数据的源，如集合、数组或输入/输出资源。请注意，从有序集合成生成流时会保留原有的顺序。由列表生成的流，其元素顺序与列表一致。
    
    数据处理操作：流的数据处理功能支持类似于数据库的操作，以及函数式编程语言中的常用操作，如filter、map、reduce、find、match、sort等。流操
    作可以顺序执行，也可以并行执行。
    
    流水线：很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线。流水线的操作可以看作对数据源进行数据库式查询。
    
    内部迭代：与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的。
    
### buffer和cache区分
    A buffer is something that has yet to be "written" to disk. 
    A cache is something that has been "read" from the disk and stored for later use. 
    
### Java内存模型与线程
    从硬件架构上讲，指令重排序是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理。
    
### 微服务的优点
    
    复杂度可控
        在将应用分解的同时，规避了原本复杂度无止境的积累。每一个微服务专注于单一功能，并通过定义良好的接口清晰表述服务边界。
        
        由于体积小、复杂度低，每个微服务可由一个小规模开发团队完全掌控，易于保持高可维护性和开发效率。

    自治性：一个微服务就是一个独立的实体，它可以独立地部署在PAAS（Platform As A Service，平台即服务）上，也可以作为一个操作系统进程存在。
    
    技术异构性：在一个由多个服务相互协作的系统中，可以在不同的服务中使用最适合该服务的技术。
    
    弹性：如果系统中的一个组件不可用了，但并不没有导致级联故障，那么系统的其他部分还可以正常运行。
    
    扩展：庞大的单块服务只能作为一个整体进行扩展。
    
    简化部署：在有几百万代码行的单块应用程序中，即使只修改了一行代码，也需要重新部署整个应用程序才能够发布该变更。
    
### 微服务的缺点
    增加开发的复杂性
    增加运维的复杂性
    增加devops的复杂性
    
### 查看JVM正在使用的垃圾收集器
    命令方式：java -XX:+PrintFlagsFinal -version | grep :
    
    代码方式：
     List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();  
     for(GarbageCollectorMXBean b : l) {  
        System.out.println(b.getName());  
     }  
    
### jstat命令

    jstat [-命令选项] [vmid] [间隔时间/毫秒] [查询次数]
    
    选项 -gc 结果说明（jdk8）
    
    S0C：第一个幸存区的大小
    S1C：第二个幸存区的大小
    S0U：第一个幸存区的使用大小
    S1U：第二个幸存区的使用大小
    EC：伊甸园区的大小
    EU：伊甸园区的使用大小
    OC：老年代大小
    OU：老年代使用大小
    MC：方法区大小
    MU：方法区使用大小
    CCSC:压缩类空间大小
    CCSU:压缩类空间使用大小
    YGC：年轻代垃圾回收次数
    YGCT：年轻代垃圾回收消耗时间
    FGC：老年代垃圾回收次数
    FGCT：老年代垃圾回收消耗时间
    GCT：垃圾回收消耗总时间
    
    jstat -gcnew <pid> 新生代垃圾回收情况
    
### jstack命令
    
    定位到线程堆栈，根据堆栈信息可以定位到具体代码，所以它在JVM性能调优中使用得非常多。下面我们来一个实例找出某个Java进程中最耗费CPU的Java线程并定位堆栈信息。
    
    jstack -l <pid>
    
    一、找出该进程内最耗费CPU的线程 top -Hp <pid>
    
    二、printf "%x\n" <pid> 将步骤一得到的pid用十六进制表示 target
    
    三、jstack <pid> | grep <target> 找到对应的类
    
### Initial Heap Size and Maximum Heap Size Changed for Parallel Garbage Collector

    On server-class machines running either VM (client or server) with the parallel garbage collector (-XX:+UseParallelGC) the initial heap size and maximum heap size have changed as follows.
    
    initial heap size
    Larger of 1/64th of the machine's physical memory on the machine or some reasonable minimum. Before Java SE 5.0, the default initial heap size was a reasonable minimum, which varies by platform. You can override this default using the -Xms command-line option.
    
    maximum heap size
    Smaller of 1/4th of the physical memory or 1GB. Before Java SE 5.0, the default maximum heap size was 64MB. You can override this default using the -Xmx command-line option.
    
    Note: The boundaries and fractions given for the heap size are correct for Java SE 5.0. They are likely to be different in subsequent releases as computers get more powerful.
    
### JVM中稳定与震荡的堆

    一般而言，Java虚拟机的堆都是稳定的，开发者可以通过参数-Xms和-Xmx两个参数设置为同一个值（即堆得初始值和最大值一致），获得一个稳定的堆。这样做的好处是在程序运行的过程中，Java虚拟机的堆空间始终是一个恒定的值，从而可以减少Java虚拟机中垃圾回收次数（相对于不稳定的堆）。对于那些要求尽量避免较多次数的GC的程序或应用来说，稳定的堆是它们必然的选择。例如，一些服务器端的应用都会将堆的初始值和最大值设置为同一值，从而保证较少的GC次数。
    
    但是，震荡的堆（即不稳定的堆）也不是一无是处的。稳定的堆保证了堆的大小在应用程序运行中不变，虽然减少了GC的次数；但是同时也带来了另一个问题，由于堆总是以最大的值来设定的，无形中会使每次GC的时间会增加。而震荡的堆却可以根据程序运行的状态，动态的调整堆的大小。具体来说就是在程序运行时让堆的大小在一个区间震荡，在程序不需要较大内存时，压缩对空间，使得Java虚拟机进行垃圾回收时，面对的是一个较小的堆，从而使得GC时间较少了。因此震荡的堆虽然增加了GC的次数，但是每次GC的时间相对于稳定的堆却有所下降。
    
    为了得到一个震荡的堆，这里需要使用Java虚拟机提供的两个参数：
    
    -XX:MinHeapFreeRatio，主要用来进行堆扩展，当堆的空闲比例低于这个值，Java虚拟机就会扩展堆空间。默认值为40。
    
    -XX:MaxHeapFreeRatio，主要用于进行堆压缩，当堆的空闲比例大于这个值时，Java虚拟机便会对堆进行压缩，得到一个相对小一点的堆。默认值为70。
    
    为了使以上两个参数生效，这里还需要将堆得初始值和最大值设置为不同的值，因为如果堆得初始大小和最大值一致，那么必然是一个稳定的堆，以上两个参数便会失效。
    
### Java中使用相对路径读取文件

    简单粗暴的 File file = new File(“src/test.txt”)
    使用类的相对路径
    使用当前线程的类加载器
    读取web工程下的文件 使用getRealPath()读取
    
    File file = new File("src/test.txt");
    File file = new File(TestRelativePath.class.getResource("/test.txt").getFile());
    File file = new File(Thread.currentThread().getContextClassLoader().getResource("test.txt").getFile());
    File file = new File(getServletContext().getRealPath("/WEB-INF/classes/test.txt"));
  
### How to configure Maven Tomcat Plugin to use HTTPS (SSL/TLS)

    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <path>/</path>
            <httpsPort>443</httpsPort>
            <keystoreFile>C:\Users\lihaodi\Desktop\certificate.p12</keystoreFile>
            <keystorePass>test</keystorePass>
        </configuration>
    </plugin>

### 将CRT HTTPS证书转换成 p12 证书

    openssl pkcs12 -export -out certificate.p12 -inkey C:\Users\lihaodi\Desktop\sha2.key /
    -in C:\Users\lihaodi\Desktop\sha2.crt -certfile C:\Users\lihaodi\Desktop\sha2.crt
    
### Spring @Transactional 声明式使用需要注意的地方
    1、@Transactional只能应用到public方法上
    2、同个service里，调用了@Transactional注解的方法，不会被事务包裹（spring aop代理下，只有目标方法由外部调用才生效，可以使用指定@EnableTransactionManagement中的model = AdviceModel.ASPECTJ切换代理方式）
    3、线程中使用@Transactionalz注解不生效