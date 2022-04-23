1、JVM  
JVM内存结构主要有三大块：堆内存、栈和非堆。方法中使用的原生数据类型和对象引用地址在栈上存储；对象、对象成员与类定义、静态变量在堆上。堆内存是所有线程共用的内存空间，JVM将Heap 内存分为年轻代（Young generation）和 老年代（Old generation, 也叫 Tenured）两部分。年轻代还划分为 3 个内存池，新生代（Edenspace）和存活区（Survivor space），在大部分GC算法中有 2 个存活区（S0, S1）。Non-Heap 本质上还是 Heap，只是一般不归 GC管理，里面划分为 3 个内存池。Metaspace，CCS，Compressed Class Space，存放class信息的，和Metaspace有交叉。Code Cache存放 JIT 编译器编译后的本地机器代码。可通过启动参数对JVM和GC算法进行设置。  
2、GC  
标记清除算法（Mark and Sweep）、标记-清除-整理算法(Mark-Sweep-Compact)、标记-复制算法(Mark-Copy)  
串行GC、并行GC、CMSGC、G1GC、
