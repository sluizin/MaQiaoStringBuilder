package MaQiao.MaQiaoStringBuilder;

import java.io.Closeable;

import sun.misc.Unsafe;
import MaQiao.Constants.Constants;
import MaQiao.MaQiaoStringBuilder.Consts.booleanType;
/**
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
 * @since 1.7
 * @version 1.1
 */
public final class MQSBuilder implements AutoCloseable, Closeable, CharSequence {
	private static transient final Unsafe UNSAFE = Constants.UNSAFE;
	@SuppressWarnings("unused")
	private transient volatile boolean locked = booleanType.False.index;;
	/**
	 * 字符(char)数量
	 */
	private transient long size = 0L;
	/**
	 * 字符(char)最大容量(默认 defaultLen 同StringBuilder、StringBuffer同样的初始大小(16个字符))
	 */
	private transient long maxSize = Consts.defaultLen;
	/**
	 * 字符(char)长度
	 */
	//private static final long charLength = Unsafe.ARRAY_CHAR_INDEX_SCALE;//长度为2 == <<1
	/**
	 * 本对象的锁对象 locked 的地址偏移量
	 */
	private static transient long lockedOffset = 0L;
	static {
		try {
			lockedOffset = UNSAFE.objectFieldOffset(MQSBuilder.class.getDeclaredField("locked"));/*得到锁对象的偏移量*/
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 基本地址，用于转移，释放使用。因有前导字节
	 */
	private transient long mBasicAddress = 0L;
	private transient long mAddress = (mBasicAddress = UNSAFE.allocateMemory(Consts.defHeadlen + maxSize >> 1)) + Consts.defHeadlen;

	public MQSBuilder() {
		expandCapacity((int) maxSize);
	}

	/**
	 * 默认扩展 offLen 个 Byte 的内存<br/>
	 * 如果长度为单数，则舍去模数<br/>
	 * @param offLen long
	 */
	public MQSBuilder(final long offLen) {
		expandCapacity(offLen);
	}

	/**
	 * 默认扩展 len 个char字符的内存<br/>
	 * @param len int
	 */
	public MQSBuilder(final int len) {
		expandCapacity(len);
	}

	public MQSBuilder(final char[] Array) {
		append(Array);
	}

	public MQSBuilder(final MQSBuilder sb) {
		append(sb);
	}

	/**
	 * 锁定对象
	 */
	final void lock() {
		lockedOffsetCAS(booleanType.False, booleanType.True);
	}

	/**
	 * 释放对象锁
	 */
	final void unLock() {
		lockedOffsetCAS(booleanType.True, booleanType.False);
	}

	/**
	 * volatile int locked 主锁的CAS，循环设置锁<br/>
	 * @param from booleanType
	 * @param to booleanType
	 */
	private final void lockedOffsetCAS(final booleanType from, final booleanType to) {
		while (!UNSAFE.compareAndSwapObject(this, lockedOffset, from.index, to.index)) {
		}
	}

	/**
	 * 添加null
	 */
	public final void appendnull() {
		append(Consts.nullArray);
		//UNSAFE.putChar(mAddress + ((size++) << 1), 'n');
		//UNSAFE.putChar(mAddress + ((size++) << 1), 'u');
		//UNSAFE.putChar(mAddress + ((size++) << 1), 'l');
		//UNSAFE.putChar(mAddress + ((size++) << 1), 'l');
		//UNSAFE.copyMemory(nullArray, Constants.ArrayAddress, null, mAddress + size * charLength, 4 * charLength);
		//size += 4;
	}

	public final void append(final Double i) {
		append(Double.toString(i));
	}

	public final void append(final double i) {
		append(Double.toString(i));
	}

	public final void append(final Float i) {
		append(Float.toString(i));
	}

	public final void append(final float i) {
		append(Float.toString(i));
	}

	public final void append(final Long i) {
		append(Long.toString(i));
	}

	public final void append(final long i) {
		append(Long.toString(i));
	}

	public final void append(final Integer i) {
		append(Integer.toString(i));
	}

	public final void append(final int i) {
		append(Integer.toString(i));
	}

	public final void append(final Short i) {
		append(Short.toString(i));
	}

	public final void append(final short i) {
		append(Short.toString(i));
	}
	public final void append(final long Offset, final long offLen) {
		lock();
		try {
			//if (offLen <= 0L || (offLen % 2) != 0 || (offLen != (offLen >> 1 << 1))) return;
			if (offLen <= 0L || (offLen & 1) != 0) return;
			if ((maxSize - size) < (offLen >> 1)) expandCapacity(offLen);
			UNSAFE.copyMemory(null, Offset, null, mAddress + (size << 1), offLen);
			size += (offLen >> 1);
		} finally {
			unLock();
		}
	}

	public final void append(final Object obj, final long Offset, final long offLen) {
		lock();
		try {
			if (offLen <= 0L || (offLen & 1) != 0) return;
			if ((maxSize - size) < (offLen >> 1)) expandCapacity(offLen);
			UNSAFE.copyMemory(obj, Offset, null, mAddress + (size << 1), offLen);
			size += (offLen >> 1);
		} finally {
			unLock();
		}
	}

	public final void append(final long Offset, final int len) {
		lock();
		try {
			if ((maxSize - size) < len) expandCapacity(len);
			UNSAFE.copyMemory(null, Offset, null, mAddress + (size << 1), len << 1);
			size += len;
		} finally {
			unLock();
		}
	}

	public final void append(final Object obj, final long Offset, final int len) {
		lock();
		try {
			if ((maxSize - size) < len) expandCapacity(len);
			UNSAFE.copyMemory(obj, Offset, null, mAddress + (size << 1), len << 1);
			size += len;
		} finally {
			unLock();
		}
	}

	public final void append(final Boolean b) {
		if (b) {
			append(Consts.trueArray);
		} else {
			append(Consts.falseArray);
		}
	}

	public final void append(final boolean b) {
/*		lock();
		try {
			扩充内存
			if (b) {
				if ((maxSize - size) < 4) expandCapacity((int) (4 + (size >> 1)));//原长度1.5倍增长
				UNSAFE.putChar(mAddress + ((size++) << 1), 't');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'r');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'u');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'e');
			}else{
				if ((maxSize - size) < 5) expandCapacity((int) (5 + (size >> 1)));//原长度1.5倍增长
				UNSAFE.putChar(mAddress + ((size++) << 1), 'f');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'a');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'l');
				UNSAFE.putChar(mAddress + ((size++) << 1), 's');
				UNSAFE.putChar(mAddress + ((size++) << 1), 'e');
			}
		} finally {
			unLock();
		}*/		
		if (b) {
			append(Consts.trueArray);/*UNSAFE.putChar(mAddress + ((size++) * charLength), 'f');*/
		} else {
			append(Consts.falseArray);
		}
	}

	public final void append(final Object obj) {
		if (obj == null) {
			append("null");
			return;
		}
		if (obj instanceof String) {
			append(String.valueOf(obj));
			return;
		}
		if (obj instanceof Float) {
			append((Float) obj);
			return;
		}
		if (obj instanceof Long) {
			append((Long) obj);
			return;
		}
		if (obj instanceof Integer) {
			append((Integer) obj);
			return;
		}
		if (obj instanceof Boolean) {
			append((Boolean) obj);
			return;
		}
		append(obj.toString());
	}

	/**
	 * 添加String串<br/>
	 * 不使用String toCharArray方法，即<br/>
	 * <code>
	 * char result[] = new char[value.length];<br/>
	 * System.arraycopy(value, 0, result, 0, value.length);<br/>
	 * 直接使用String对象中的value字符数组的内存直接提取<br/>
	 * </code> <font color='red'>含有独占锁</font><br/>
	 * @param Str String
	 */
	public final void append(final String Str) {
		int len;
		if (Str == null || (len = Str.length()) == 0) return;//{appendnull();return;}
		lock();
		try {
			/*扩充内存*/
			if ((maxSize - size) < len) expandCapacity((int) (len + size));//原长度双倍增长
			/*拷贝数据*/
			UNSAFE.copyMemory(UNSAFE.getObject(Str, Consts.StringArrayOffset), Consts.ArrayAddress, null, mAddress + (size << 1), len << 1);
			size += len;
		} finally {
			unLock();
		}
	}

	/**
	 * 添加char[]数组<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @param Array char[]
	 */
	public final void append(final char[] Array) {
		int len;
		if ((len = Array.length) == 0) return;
		lock();
		try {
			/*扩充内存*/
			if ((maxSize - size) < len) expandCapacity((int) (len + (size >> 1)));//原长度1.5倍增长
			/* {
				if (len < (maxSize >> 1)) {
					expandCapacity();
				} else {
					expandCapacity(len << 1);
				}
			}*/
			UNSAFE.copyMemory(Array, Consts.ArrayAddress, null, mAddress + (size << 1), len << 1);
			size += len;
		} finally {
			unLock();
		}
	}

	/**
	 * 添加单独char字符<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @param c char
	 */
	public final void append(final char c) {
		lock();
		try {
			if (maxSize <= size) expandCapacity();//(maxSize << 1);
			UNSAFE.putChar(mAddress + ((size++) << 1), c);
		} finally {
			unLock();
		}
	}

	/**
	 * <font color='red'>含有独占锁</font><br/>
	 * 不释放资源<br/>
	 * @param MQ SBuilder
	 */
	public final void append(final MQSBuilder MQ) {
		append(MQ, false);
	}

	/**
	 * <font color='red'>含有独占锁</font><br/>
	 * 决定是否释放参数资源<br/>
	 * @param MQ MQSBuilder
	 * @param closed boolean
	 */
	public final void append(final MQSBuilder MQ, final boolean closed) {
		long len;
		if ((len = MQ.size) == 0L) return;
		lock();
		try {
			if ((len + size) > maxSize) expandCapacity((int) ((len + size) - maxSize));
			/*扩充内存*/
			UNSAFE.copyMemory(null, MQ.mAddress, null, mAddress + (size << 1), len << 1);
			size += len;
			if (closed) MQ.free();
		} finally {
			unLock();
		}
	}

	/*
	 * =======================================================================================================================
	 */
	/**
	 * 返回 char 数量
	 * @return long
	 */
	public final long Length() {
		return size;
	}

	@Override
	public final int length() {
		if (size > Consts.charArrayMaxLen) return Consts.charArrayMaxLen;
		return (int) size;
	}

	public final long capacity() {
		return maxSize;
	}

	public final String getString() {
		final char[] c = getArray();
		if (c == null || c.length == 0) return null;
		return new String(c);
	}

	/**
	 * 得到char数组<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @return char[]
	 */
	public final char[] getArray() {
		if (size == 0L) return null;
		lock();
		try {
			int len = (size > Consts.charArrayMaxLen) ? Consts.charArrayMaxLen : (int) size;
			final char[] c = new char[len];
			UNSAFE.copyMemory(null, mAddress, c, Consts.ArrayAddress, len << 1);
			return c;
		} finally {
			unLock();
		}
	}

	/**
	 * 得到指定位置的数组，如数组长度超过范围，则输出指定位置后面的所有<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @param startIndex long
	 * @param Size long
	 * @return char[]
	 */
	public final char[] getArray(final long startIndex, final long Size) {
		if (startIndex < 0 || startIndex >= size || Size > Consts.charArrayMaxLen) return null;
		//throw new StringIndexOutOfBoundsException((int)(startIndex+Size));
		lock();
		try {
			long len = (startIndex + Size) > size ? size - startIndex : Size;
			final char[] c = new char[(int) len];
			UNSAFE.copyMemory(null, mAddress + (startIndex << 1), c, Consts.ArrayAddress, len << 1);
			return c;
		} finally {
			unLock();
		}
	}

	/**
	 * 字符数组输出，最高支持 Integer.MAX_VALUE * Integer.MAX_VALUE
	 * @return char[][]
	 */
	public final char[][] getArrays() {
		if (size <= Consts.charArrayMaxLen) {
			final char[][] c = { getArray() /*含有独占锁*/};
			return c;
		}
		int Multiple = (int) ((size % Consts.charArrayMaxLen == 0) ? size / Consts.charArrayMaxLen : (size - size % Consts.charArrayMaxLen)
				/ Consts.charArrayMaxLen + 1);
		final char[][] Array = new char[Multiple][];
		//============================================================
		for (int i = 0; i < Multiple; i++) {
			if (i < (Multiple - 1)) {
				/*满格*/
				Array[i] = getArray(i * Consts.charArrayMaxLen, Consts.charArrayMaxLen);
			} else {
				/*不满格*/
				Array[i] = getArray(i * Consts.charArrayMaxLen, size - i * Consts.charArrayMaxLen);
			}
		}
		//============================================================
		return Array;
	}

	/**
	 * 得到指定位置的char<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @param index long
	 * @return char
	 */
	public final char charAt(final long index) {
		if (index < 0 || index >= size) return Consts.elementFill;
		lock();
		try {
			//throw new StringIndexOutOfBoundsException((int)index);
			final char c = UNSAFE.getChar(mAddress + (index << 1));
			return c;
		} finally {
			unLock();
		}
	}

	@Override
	public final char charAt(final int index) {
		return charAt((long) index);
	}

	/**
	 * 得到指定位置的char<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 * @param i
	 * @return Character
	 */
	public final Character getCharacter(final long i) {
		if (i < 0 || i >= size) return null;
		//throw new StringIndexOutOfBoundsException((int)i);
		lock();
		try {
			final char c = UNSAFE.getChar(mAddress + (i << 1));
			return c;
		} finally {
			unLock();
		}
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		if (start < 0 || start >= size || end <= start || end > size) return null;
		return new MQSBuilder(getArray(start, end - start + 1));
	}

	public final MQSBuilder clone() {
		return new MQSBuilder(this);
	}

	@Override
	public String toString() {
		return new String(getArray());
	}

	/*
	 * =======================================================================================================================
	 */
	/**
	 * 清空<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 */
	public final void clear() {
		lock();
		try {
			UNSAFE.freeMemory(mBasicAddress);
			mAddress = (mBasicAddress = UNSAFE.allocateMemory(Consts.defHeadlen + (Consts.defaultLen << 1))) + Consts.defHeadlen;
			size = 0L;
			maxSize = Consts.defaultLen;
		} finally {
			unLock();
		}
	}

	/*
	 * =======================================================================================================================
	 */
	/**
	 * 扩展总长的50%
	 */
	private final void expandCapacity() {
		expandCapacity((int) (maxSize >> 1));
	}

	/**
	 * 默认扩展 offLen 个 Byte 的内存<br/>
	 * 如果长度为单数，则舍去模数<br/>
	 * @param offLen long
	 */
	private final void expandCapacity(final long offLen) {
		if (offLen <= 1L) return;
		expandCapacity(offLen >> 1);
	}

	/**
	 * 默认扩展 len 个char字符的内存
	 * @param len int
	 */
	private final void expandCapacity(final int len) {
		if (len <= 0) return;
		mAddress = (mBasicAddress = UNSAFE.reallocateMemory(mBasicAddress, ((maxSize + len) << 1) + Consts.defHeadlen)) + Consts.defHeadlen;
		maxSize += len;
	}

	/*
	 * =======================================================================================================================
	 */
	/**
	 * 释放内存<br/>
	 * <font color='red'>含有独占锁</font><br/>
	 */
	public final void free() {
		lock();
		UNSAFE.freeMemory(mBasicAddress);
		unLock();
		//System.out.println("free()...........OK!!!");
	}

	public final void close() {
		free();
	}

}