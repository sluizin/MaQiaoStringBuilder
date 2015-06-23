Constants 通用常量<br/>
 * <font color='red'>jvm的大便!!!</font><br/>
 * 此工具先期适用于(++=)模式，暂不提供(+-=)模式<br/>
 * 系统初始化 有前导Byte<br/>
 * <font color='red'>用完后必须使用free()方法释放内存，因为UNSAFE不参与jvm回收<br/>
 * 因为使用AutoCloseable接口，最好把对象定义放在try资源中，try会自动关闭<br/>
 * 注意：如不在try资源中定义，则需要显式调用对象的free()或close()方法，以释放内存</font><br/>
 * 为了防止死锁，二次开发时新增方法的嵌套调时注意锁的位置(<font color='red'>独占锁</font>)<br/>
 * <br/>
 * <br/>
 * @author Sunjian
 * @QQ 75583378
 * @Email sluizin@sohu.com
 * @version 1.0
 * @since 1.7
 * @Datetime 2015-4-9
 */<br/>