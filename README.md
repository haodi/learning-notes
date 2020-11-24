# learning-notes

## 常用算法
    工作窃取算法

## 实际面试笔试题
### 海量数据处理
    特点：资源有限，无法一次性处理
    思路：分而治之
    
   1、JVM内存受限为5M，现在需要统计一份文件（文件大小为2G）里出现频次较高的top100的单词，请写出代码，并给出最优解
   
   解法思路，统计每个单词出现的次数（创建以单词命名的文件，把出现次数写到文件中），再维护一个长度为k的小顶堆。
   [解法示例](https://github.com/haodi/learning-notes/blob/master/src/main/java/learning/leetcode/TopK.java)
   
    2、a、b两个文件，每个文件有5亿条url（每条url大小为64k），机器内存为4G，找出a、b两个文件中重复的url
### 线程调度
    1、有3个线程，一个线程负责输出A，一个线程输出B，一个线程C，请设计一个代码，向一个文件中循环写入10次AABBCCCAABBCCC
    
4、红包算法
    问题：红包算法，给定一个红包总金额和分红包的人数，输出每个人随机抢到的红包数量。
    要求：
    1. 每个人都要抢到红包，并且金额随机
    2. 每个人抢到的金额数不小于1
    3. 每个人抢到的金额数不超过总金额的30%
    例如总金额100，人数10，输出【19 20 15 1 25 14 2 2 1 1】

### 5、给出一个区间的集合，请合并所有重叠的区间
    示例 1:
    输入: [[1,3],[2,6],[8,10],[15,18]]
    输出: [[1,6],[8,10],[15,18]]
    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    
    示例 2:
    输入: [[1,4],[4,5]]
    输出: [[1,5]]
    解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    */
    
### 6、实现一个LRU Cache

## 现实生活中常用算法
    背景：

### 拆红包算法

### 抽奖算法