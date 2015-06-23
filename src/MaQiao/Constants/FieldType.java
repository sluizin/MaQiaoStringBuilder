package MaQiao.Constants;

public final class FieldType {
	//JDBC RS Type
	private String JavaRsType = "";
	//ASM Type
	private String ASMType = "";
	//Json中 Value值状态，默认0(直接连接),1:加",2:集合[] 
	private int jsonType = 0;
	//ASM DefaultObject
	private Object ASMDefaultObject = null;
	// Fields Type
	private String ReflectFields = "";
	// Fields ClassType
	private Class<?> FieldsClass = null;
	//unsafe 类型元素 ARRAY_INDEX_SCALE偏移量
	private int ARRAY_INDEX_SCALE = 0;
	//可以通过unsafe的getXXXX提取
	private boolean isUNSAFEget = false;

	/**
	 * 构造函数
	 */
	public FieldType() {

	}

	/**
	 * 构造函数
	 * @param JavaRsType java得到的recordSet数据类型
	 * @param ASMType ASM数据类型
	 * @param JsonType Value值状态，默认0(直接连接),1:加",2:集合[]
	 * @param ASMDefaultObject ASM数据类型初始化
	 * @param ReflectFields 反射Fields所需要的类型(String)
	 * @param FieldsClass Fields所需要 Class<?> 类
	 * @param ARRAY_INDEX_SCALE unsafe 类型元素 数组偏移量
	 */
	public FieldType(final String JavaRsType, final String ASMType, final int JsonType, final Object ASMDefaultObject, final String ReflectFields, final Class<?> FieldsClass,
			final int ARRAY_INDEX_SCALE, final boolean isUNSAFEget) {
		this.JavaRsType = JavaRsType;
		this.ASMType = ASMType;
		this.jsonType = JsonType;
		this.ASMDefaultObject = ASMDefaultObject;
		this.ReflectFields = ReflectFields;
		this.FieldsClass = FieldsClass;
		this.ARRAY_INDEX_SCALE = ARRAY_INDEX_SCALE;
		this.isUNSAFEget = isUNSAFEget;
	}

	public final String getJavaRsType() {
		return JavaRsType;
	}

	public final void setJavaRsType(final String javaRsType) {
		this.JavaRsType = javaRsType;
	}

	public final String getASMType() {
		return ASMType;
	}

	public final void setASMType(final String aSMType) {
		this.ASMType = aSMType;
	}

	/**
	 * Json中 Value值状态，默认0(直接连接),1:加",2:集合[]
	 * @return int
	 */
	public final int getJsonType() {
		return jsonType;
	}

	public final void setJsonType(final int jsonType) {
		this.jsonType = jsonType;
	}

	public final Object getASMDefaultObject() {
		return ASMDefaultObject;
	}

	public final void setASMDefaultObject(final Object aSMDefaultObject) {
		this.ASMDefaultObject = aSMDefaultObject;
	}

	public final String getReflectFields() {
		return ReflectFields;
	}

	public final void setReflectFields(final String reflectFields) {
		this.ReflectFields = reflectFields;
	}

	public final Class<?> getFieldsClass() {
		return FieldsClass;
	}

	public final void setFieldsClass(final Class<?> fieldsClass) {
		this.FieldsClass = fieldsClass;
	}

	public final int getARRAY_INDEX_SCALE() {
		return ARRAY_INDEX_SCALE;
	}

	public final void setARRAY_INDEX_SCALE(final int ARRAY_INDEX_SCALE) {
		this.ARRAY_INDEX_SCALE = ARRAY_INDEX_SCALE;
	}

	public final boolean isUNSAFEget() {
		return isUNSAFEget;
	}

	public final void setUNSAFEget(boolean isUNSAFEget) {
		this.isUNSAFEget = isUNSAFEget;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ARRAY_INDEX_SCALE;
		result = prime * result + ((ASMDefaultObject == null) ? 0 : ASMDefaultObject.hashCode());
		result = prime * result + ((ASMType == null) ? 0 : ASMType.hashCode());
		result = prime * result + ((FieldsClass == null) ? 0 : FieldsClass.hashCode());
		result = prime * result + ((JavaRsType == null) ? 0 : JavaRsType.hashCode());
		result = prime * result + ((ReflectFields == null) ? 0 : ReflectFields.hashCode());
		result = prime * result + (isUNSAFEget ? 1231 : 1237);
		result = prime * result + jsonType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof FieldType)) return false;
		FieldType other = (FieldType) obj;
		if (ARRAY_INDEX_SCALE != other.ARRAY_INDEX_SCALE) return false;
		if (ASMDefaultObject == null) {
			if (other.ASMDefaultObject != null) return false;
		} else if (!ASMDefaultObject.equals(other.ASMDefaultObject)) return false;
		if (ASMType == null) {
			if (other.ASMType != null) return false;
		} else if (!ASMType.equals(other.ASMType)) return false;
		if (FieldsClass == null) {
			if (other.FieldsClass != null) return false;
		} else if (!FieldsClass.equals(other.FieldsClass)) return false;
		if (JavaRsType == null) {
			if (other.JavaRsType != null) return false;
		} else if (!JavaRsType.equals(other.JavaRsType)) return false;
		if (ReflectFields == null) {
			if (other.ReflectFields != null) return false;
		} else if (!ReflectFields.equals(other.ReflectFields)) return false;
		if (isUNSAFEget != other.isUNSAFEget) return false;
		if (jsonType != other.jsonType) return false;
		return true;
	}

	@Override
	public String toString() {
		return "FieldType [JavaRsType=" + JavaRsType + ", ASMType=" + ASMType + ", jsonType=" + jsonType + ", ASMDefaultObject=" + ASMDefaultObject + ", ReflectFields=" + ReflectFields
				+ ", FieldsClass=" + FieldsClass + ", ARRAY_INDEX_SCALE=" + ARRAY_INDEX_SCALE + ", isUNSAFEget=" + isUNSAFEget + "]";
	}

}
