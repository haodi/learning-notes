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
    
    