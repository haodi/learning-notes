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
    
    