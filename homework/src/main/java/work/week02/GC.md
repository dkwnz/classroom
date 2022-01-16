1、串行GC(-Xms:1g -Xmx:1g)  
NewSize:341.3125M(1/3HeapSize) -> Eden(307)+From(34)+To(34)  
OldSize:682.6875M  
MetaspaceSize:20.796875M  
使用单个线程进行垃圾收集，暂停的时间最长。(YoungGC 0.092,FullGC 0.177)
YoungGC内存减少值可能低于堆内存减少值，会有部门内存被放到老年代，FullGC后年轻代也会被GC，并有部分内存会转移至老年代。
      
2、并行GC(-Xms:1g -Xmx:1g)  
NewSize:341M(1/3HeapSize) -> Eden(256)+From(42)+To(42)  
OldSize:683M  
MetaspaceSize:20.796875M  
 使用多个线程进行垃圾收集，充分利用多核CPU的性能，能缩短暂停时间。(YoungGC 0.085,FullGC 0.123)  
    
3、CMS(-Xms:1g -Xmx:1g)  
NewSize:332.75M -> Eden(299.5)+From(33.25)+To(33.25)  
OldSize:691M  
MetaspaceSize:20.796875M
 并发垃圾收集，一共有5个阶段：初始标记，并发标记，并发预清理，最终标记，并发清除，并发重置。只有在初始标记和最终标记的阶段才会进行暂停，初始标记和并发标记一共用时0.126，其他阶段与业务代码并行运行，可以减少GC导致的业务延迟。  
    
4、G1GC(-Xms:1g -Xmx:1g)  
G1HeapRegionSize:1M  
基于CMS的升级版，年轻代和老年代没有固定的大小，而是划分为多个小的region，每次只收集一部分的内存。有一个特殊的参数-XX：MaxGCPauseMills可以控制希望GC消耗的时间。
