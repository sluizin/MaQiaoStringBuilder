package MaQiao.Constants;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 常量池 <br/>
 * JsonMark_0 = '\"';<br/>
 * JsonMark_1 = '\'';<br/>
 * JsonMark_2 = ',';<br/>
 * JsonMark_3 = ':';<br/>
 * JsonMark_4 = '\\';<br/>
 * JsonMark_10 = '{';<br/>
 * JsonMark_11 = '}';<br/>
 * JsonMark_20 = '[';<br/>
 * JsonMark_21 = ']';<br/>
 * @author Sun.jian(孙.健) <br/>
 */
public final class Constants {
	/* java转义字符(不包括\ddd 三位八进制;\udddd 四位十六进制) */
	public static final char[] EscapeCharacter = { '\n', '\t', '\b', '\r', '\f', '\'', '\"', '\\' };
	/* Json 非""字符串 之外所有转义字符 */
	public static final char[] JsonEscapeCharacter = { ' ', '\n', '\t', '\b', '\r', '\f', '\\' };

	public static final char JsonMark_0 = '\"';
	public static final char JsonMark_1 = '\'';
	public static final char JsonMark_2 = ',';
	public static final char JsonMark_3 = ':';
	public static final char JsonMark_4 = '\\';
	public static final char JsonMark_10 = '{';
	public static final char JsonMark_11 = '}';
	public static final char JsonMark_20 = '[';
	public static final char JsonMark_21 = ']';
	public static final char JsonMark_50 = ' ';
	public static final char JsonMark_51 = '\n';
	public static final char JsonMark_52 = '\t';
	public static final char JsonMark_53 = '\b';
	public static final char JsonMark_54 = '\r';
	public static final char JsonMark_55 = '\f';

	public static final Unsafe UNSAFE;
	/**
	 * String对象中value(char[])地址偏移量
	 */
	public static long StringArrayOffset = 0L;
	static {
		try {
			final Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
			StringArrayOffset = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));/*得到String对象中数组的偏移量*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * char[]数组地址偏移量
	 */
	public static final long ArrayAddress = UNSAFE.arrayBaseOffset(char[].class);

	/**
	 * 初始化
	 */
	public Constants() {
	}

	/**
	 * 字段属性
	 * @author SunJian
	 */
	public static enum FieldTypeEnum {
		Object(new FieldType("java.lang.Object", "Ljava/lang/Object;", 0, null, "java.lang.Object", java.lang.Object.class, Unsafe.ARRAY_OBJECT_INDEX_SCALE, false)),

		Char(new FieldType("java.lang.Character", "C", 1, null, "char", java.lang.Character.class, Unsafe.ARRAY_CHAR_INDEX_SCALE, true)),

		Int(new FieldType("java.lang.Integer", "I", 0, 0, "int", java.lang.Integer.class, Unsafe.ARRAY_INT_INDEX_SCALE, true)), Integer(new FieldType("java.lang.Integer", "Ljava/lang/Integer;", 0, 0,
				"java.lang.Integer", java.lang.Integer.class, UNSAFE.arrayIndexScale(Integer[].class), false)),

		Boolean(new FieldType("java.lang.Boolean", "Z", 0, false, "boolean", java.lang.Boolean.class, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE, true)), BooleanObject(new FieldType("java.lang.Boolean",
				"Ljava/lang/Boolean;", 0, false, "java.lang.Boolean", java.lang.Boolean.class, UNSAFE.arrayIndexScale(Boolean[].class), false)),

		Float(new FieldType("java.lang.Float", "F", 0, null, "float", java.lang.Float.class, Unsafe.ARRAY_FLOAT_INDEX_SCALE, true)), FloatObject(new FieldType("java.lang.Float", "Ljava/lang/Float;",
				0, null, "java.lang.Float", java.lang.Float.class, UNSAFE.arrayIndexScale(Float[].class), false)),

		Long(new FieldType("java.lang.Long", "J", 0, 0, "long", java.lang.Long.class, Unsafe.ARRAY_LONG_INDEX_SCALE, true)), LongObject(new FieldType("java.lang.Long", "Ljava/lang/Long;", 0, 0,
				"java.lang.Long", java.lang.Long.class, UNSAFE.arrayIndexScale(Long[].class), false)),

		Double(new FieldType("java.lang.Double", "D", 0, null, "double", java.lang.Double.class, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, true)), DoubleObject(new FieldType("java.lang.Double",
				"Ljava/lang/Double;", 0, null, "java.lang.Double", java.lang.Double.class, UNSAFE.arrayIndexScale(Double[].class), false)),

		Byte(new FieldType("java.lang.Byte", "B", 0, null, "byte", java.lang.Byte.class, Unsafe.ARRAY_BYTE_INDEX_SCALE, true)), ByteObject(new FieldType("java.lang.Byte", "Ljava/lang/Byte;", 0, null,
				"java.lang.Byte", java.lang.Byte.class, UNSAFE.arrayIndexScale(Byte[].class), false)),

		Short(new FieldType("java.lang.Short", "S", 0, null, "short", java.lang.Short.class, Unsafe.ARRAY_SHORT_INDEX_SCALE, true)), ShortObject(new FieldType("java.lang.Short", "Ljava/lang/Short;",
				0, null, "java.lang.Short", java.lang.Short.class, UNSAFE.arrayIndexScale(Short[].class), false)),

		String(new FieldType("java.lang.String", "Ljava/lang/String;", 1, null, "java.lang.String", java.lang.String.class, UNSAFE.arrayIndexScale(String[].class), false)),

		Timestamp(new FieldType("java.sql.Timestamp", "Ljava/sql/Timestamp;", 0, null, "java.sql.Timestamp", java.sql.Timestamp.class, UNSAFE.arrayIndexScale(java.sql.Timestamp[].class), false)),

		ListObject(new FieldType("java.util.List", "Ljava/util/List;", 2, null, "java.util.List", java.util.List.class, UNSAFE.arrayIndexScale(java.util.List[].class), false)),

		MapObject(new FieldType("java.util.Map", "Ljava/util/Map;", 2, null, "java.util.Map", java.util.Map.class, UNSAFE.arrayIndexScale(java.util.Map[].class), false)),

		SetObject(new FieldType("java.util.Set", "Ljava/util/Set;", 2, null, "java.util.Set", java.util.Set.class, UNSAFE.arrayIndexScale(java.util.Set[].class), false)),

		HashMapObject(new FieldType("java.util.HashMap", "Ljava/util/HashMap;", 2, null, "java.util.HashMap", java.util.HashMap.class, UNSAFE.arrayIndexScale(java.util.HashMap[].class), false)),

		BitSetObject(new FieldType("java.util.BitSet", "Ljava/util/BitSet;", 2, null, "java.util.BitSet", java.util.BitSet.class, UNSAFE.arrayIndexScale(java.util.BitSet[].class), false));
		/**
		 * String point
		 */
		private FieldType point;

		/**
		 * 初始化
		 * @param point
		 */
		FieldTypeEnum(final FieldType point) {
			this.point = point;
		}

		FieldTypeEnum() {
		}

		public final FieldType point() {
			return point;
		}

		/**
		 * 判断Field是否存在枚举对象
		 * @param Field
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByField(final Field field) {
			return getByReflectFields(field.getType().getName());
		}

		/**
		 * 判断JavaRsType是否存在枚举对象
		 * @param JavaRsType
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByRs(final String JavaRsType) {
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getJavaRsType().equals(JavaRsType)) return c;
			//assert false : "(Don't Find FieldTypeEnum from Constants.FieldTypeEnum)JavaRsType -- Error:" + JavaRsType;
			return null;
		}

		/**
		 * 判断 ASMType 是否存在枚举对象
		 * @param ASMType
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByASMType(final String ASMType) {
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getASMType().equals(ASMType)) return c;
			return null;
		}

		/**
		 * 判断 ASMType 是否存在枚举对象(把.转成/)。主要是拆除分导前导'['，后会产生'.'的类型。要转成'/'按asm查找<br/>
		 * Ltest.target2;<br/>
		 * Ltest/target2;<br/>
		 * java.lang.String;<br/>
		 * java/lang/String;<br/>
		 * @param ASMType
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByASMType2(String ASMType) {
			if (ASMType == null) return null;
			if (ASMType.charAt(0) == '[') ASMType = ASMType.substring(1, ASMType.length());
			//System.out.println("ASMType(before):"+ASMType);
			if (ASMType.indexOf('.') > 0) ASMType = ASMType.replace('.', '/');
			//System.out.println("ASMType(after):"+ASMType);
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getASMType().equals(ASMType)) return c;
			return null;
		}

		/**
		 * 判断ReflectFields是否存在枚举对象
		 * @param ReflectFields
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByReflectFields(final String ReflectFields) {
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getReflectFields().equals(ReflectFields)) return c;
			return null;
		}

		/**
		 * 判断FieldsClass Class< ? >是否存在枚举对象
		 * @param FieldsClass
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByFieldsClass(final Class<?> FieldsClass) {
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getFieldsClass() == FieldsClass) return c;
			return null;
		}

		/**
		 * 判断FuzzyString String是否存在枚举对象<br/>
		 * 模糊查找。只输入char、java.lang.Boolean、float、byte<br/>
		 * 只要能查到一个，即返回
		 * @param FuzzyString
		 * @return FieldTypeEnum
		 */
		public static final FieldTypeEnum getByFuzzyString(final String FuzzyString) {
			for (FieldTypeEnum c : FieldTypeEnum.values())
				if (c.point().getReflectFields().equals(FuzzyString) || c.point().getClass().getName().equals(FuzzyString)) return c;
			return null;
		}
	}

	/*	switch (FieldTypeEnum) {
		case Char:
			break;
		case Int:
			break;
		case Integer:
			break;
		case Boolean:
			break;
		case BooleanObject:
			break;
		case Float:
			break;
		case FloatObject:
			break;
		case Long:
			break;
		case LongObject:
			break;
		case Double:
			break;
		case DoubleObject:
			break;
		case Byte:
			break;
		case ByteObject:
			break;
		case Short:
			break;
		case ShortObject:
			break;
		case String:
			break;
		case Timestamp:
			break;
		case MapObject:
			break;
		case SetObject:
			break;
		case HashMapObject:
			break;
		case BitSetObject:
			break;
		case Object:
			break;
		}*/
	/**
	 * 通过unsafe得到对象偏移位置的对象，类型已经通过FieldTypeEnum确认<br/>
	 * 在获取identity时，注意：Short,Float,Long,Double对象，需要提取值，则不是地址<br/>
	 * 暂时无法提取这几种类型的地址<br/>
	 * @param obj Object
	 * @param offSet long
	 * @param fte FieldTypeEnum
	 * @return double
	 */
	public static final double getUNSAFEObjectidentityHashCode(final Object obj, final long offSet, final FieldTypeEnum fte) {
		if (fte == null) return 0;
		switch (fte) {
		case Char:
			return System.identityHashCode(UNSAFE.getChar(obj, offSet));
		case Int:
			return System.identityHashCode(UNSAFE.getInt(obj, offSet));
		case Boolean:
			return System.identityHashCode(UNSAFE.getBoolean(obj, offSet));
		case Float:
			return UNSAFE.getFloat(obj, offSet);
		case Long:
			return UNSAFE.getLong(obj, offSet);
		case Double:
			return UNSAFE.getDouble(obj, offSet);
		case Byte:
			return System.identityHashCode(UNSAFE.getByte(obj, offSet));
		case Short:
			return UNSAFE.getShort(obj, offSet);
		default:
			return 0;
		}
	}

	/**
	 * 调查private/public/protected 非静态变量的IdentityHashCode
	 * @param obj Object
	 * @param field Field
	 * @return double
	 */
	public static final double getIdentityHashCode(final Object obj, final Field field) {
		if (field == null) return 0;
		int identityHashCode = 0;
		if (!((field.getModifiers() & java.lang.reflect.Modifier.STATIC) == java.lang.reflect.Modifier.STATIC)) {
			FieldTypeEnum fte = null;
			if ((fte = FieldTypeEnum.getByField(field)) != null && fte.point.isUNSAFEget()) return getUNSAFEObjectidentityHashCode(obj, UNSAFE.objectFieldOffset(field), fte);
			try {
				/*public变量，直接提出值*/
				if ((field.getModifiers() & java.lang.reflect.Modifier.PUBLIC) == java.lang.reflect.Modifier.PUBLIC) return System.identityHashCode(field.get(obj));
				/*private变量，直接setAccessible(true)提出值*/
				if ((field.getModifiers() & java.lang.reflect.Modifier.PRIVATE) == java.lang.reflect.Modifier.PRIVATE) {
					field.setAccessible(true);
					return System.identityHashCode(field.get(obj));
				}
				/*protected变量，直接setAccessible(true)提出值*/
				if ((field.getModifiers() & java.lang.reflect.Modifier.PROTECTED) == java.lang.reflect.Modifier.PROTECTED) {
					field.setAccessible(true);
					return System.identityHashCode(field.get(obj));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return identityHashCode;
	}
}
