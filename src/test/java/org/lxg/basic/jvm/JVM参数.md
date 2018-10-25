# jvm参数
## JVM 类型以及编译器模式
- 类型：-server and -client
- 版本信息：-version and -showversion
- 编译器模式：-Xint（解释模式 执行所有【慢】）, -Xcomp（JIT 编译器少部分功能）, 和 -	Xmixed（混合模式）
- 参数分类和即时（JIT）编译器诊断
- 标准参数
    - X参数
    - XX参数
        - -XX:+PrintCompilation 输出一些关于从字节码转化成本地代码的编译过程
        - -XX:+CITime JVM 关闭时得到各种编译的统计信息
        - -XX:+PrintFlagsFinal and -XX:+PrintFlagsInitial 打印所有 XX 参数及值
        - -XX:+PrintCommandLineFlags打印出那些已经被用户或者 JVM 设置过的详细的 XX 参数的名称和值 
        - -XX:+HeapDumpOnOutOfMemoryError and -XX:HeapDumpPath 堆内存快照
        - -XX:OnOutOfMemoryError 当内存溢发生时，我们甚至可以可以执行一些指令
        - -XX:PermSize and -XX:MaxPermSize 永久代堆内存
        - -XX:InitialCodeCacheSize and -XX:ReservedCodeCacheSize代码缓存
        - -XX:+UseCodeCacheFlushing当代码缓存被填满时让 JVM 放弃一些编译代码
## 内存调优
所有已制定的 HotSpot 内存管理和垃圾回收算法都基于一个相同的堆内存划分：新生代（young generation）里存储着新分配的和较年轻的对象，老年代（old generation）里存储着长寿的对象。在此之外，永久代（permanent generation）存储着那些需要伴随整个 JVM 生命周期的对象，比如，已加载的对象的类定义或者 String 对象内部 Cache。接下来，我们将假设堆内存是按照新生代、老年代和永久代这一经典策略划分的。然而，其他的一些堆内存划分策略也是可行的，一个突出的例子就是新的 G1 垃圾回收器，它模糊了新生代和老年代之间的区别。此外，目前的开发进程似乎表明在未来的 HotSpot JVM 版本中，将不会区分老年代和永久代。
- -Xms and -Xmx (or: -XX:InitialHeapSize and -XX:MaxHeapSize)指定 JVM 的初始和最大堆内存大小新生代垃圾回收
- -XX:NewSize and -XX:MaxNewSize新生代只是整个堆的一部分，新生代设置的越大，老年代区域就会减少
- -XX:NewRatio动态分配新生代与老生代比例
- -XX:SurvivorRatio 动态分配新生代的伊甸园区与幸存区（伊甸园区大于幸存区【俩个幸存区大小相同】）
- -XX:+PrintTenuringDistribution
- -XX:InitialTenuringThreshold， -XX:MaxTenuringThreshold and -XX:TargetSurvivorRatio
- -XX:+NeverTenure and -XX:+AlwaysTenure
 
## GC 日志（垃圾收集）
作用：记录了每一次的 GC 的执行时间和执行结果，通过分析 GC 日志可以优化堆设置和 GC 设置，或者改进应用程序的对象分配模式
- -XX:+PrintGC（- verbose:gc） 简单日志模式
```
每一次新生代（young generation）的 GC 和每一次的 Full GC 打印一行信息。
[GC 246656K->243120K(376320K), 0.0929090 secs]
gc类型 gc使用的前的堆空间 gc使用后的堆大小 当前堆容量 gc持续时间
```
- XX:PrintGCDetails 详细 GC 日志模式
```
[GC[PSYoungGen: 142816K->10752K(142848K)] 246648K->243136K(375296K), 0.0935090 secs]
[Times: user=0.55 sys=0.10, real=0.09 secs]

这是一次在 young generation 中的 GC，它将已使用的堆空间从 246648K 减少到了 243136K，用时 0.0935090 秒。此外我们还可以得到更多的信息：所使用的垃圾收集器（即 PSYoungGen）、young generation 的大小和使用情况（在这个例子中 “PSYoungGen” 垃圾收集器将 young generation 所使用的堆空间从 142816K 减少到 10752K）。
CPU 时间信息 =用户空间+系统空间
real gc运行真实时间
```
- -XX:+PrintGCTimeStamps 和 - XX:+PrintGCDateStamps【绝对时间】将时间和日期也加到 GC 日志中
- -Xloggc（- XX:+PrintGC - XX:+PrintGCTimeStamps）输出到指定的文件