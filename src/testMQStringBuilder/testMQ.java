package testMQStringBuilder;

import MaQiao.MaQiaoStringBuilder.MQSBuilder;

public final class testMQ {
	static final int times = 100*10000;//2147483647
	static final String tempstr = "abcdefghijklmnopqrstuvwxyz";
	static final char[] temparray = tempstr.toCharArray();
	static int defaultlen=times*temparray.length;
	//MQArrayChar.subArrayRnd(temparray)
	public static long funBuffer() {
		long lstart2 = System.nanoTime();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		//System.out.println("StringBuffer\t\t\t:" + time2 + "\tns");
		//System.out.println("len:" + sb.length());
		//System.out.println("Capacity:" + sb.capacity());
		sb = null;
		return time2;
	}

	public static  long funBuilder() {
		long lstart2 = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		//System.out.println("StringBuilder\t\t\t:" + time2 + "\tns");
		//System.out.println("len:" + sb.length());
		//System.out.println("Capacity:" + sb.capacity());
		sb = null;
		return time2;
	}

	public static  long funSBuilder() {
		long lstart2 = System.nanoTime();
		MQSBuilder sb = new MQSBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		//System.out.println("MQSBuilder\t\t\t:" + time2 + "\tns");
		//System.out.println("len:" + sb.length());
		//System.out.println("Capacity:" + sb.capacity());
		sb.close();
		sb = null;
		return time2;
	}

	@SuppressWarnings("unused")
	public static  long funSBuilder1() {
		long lstart2 = System.nanoTime();
		MQSBuilder sb = new MQSBuilder();
		Object obj=null;
		for (int i = 0; i < times; i++) {
			//sb.appendDeprecated(tempstr);
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		//System.out.println("MQSBuilder(Object)\t\t:" + time2 + "\tns");
		sb.close();
		sb = null;
		return time2;
	}
	public static  long funSBuilder2() {
		long lstart2 = System.nanoTime();
		MQSBuilder sb = new MQSBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
			//sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		//System.out.println("MQSBuilder(toCharArray) \t:" + time2 + "\tns");
		sb.close();
		sb = null;
		return time2;
	}
	public static void main(String[] args) {
		final int rolecount = 20;
		double count = 0L;

		System.out.println("==================================================================");
		System.out.println("字符串 \""+ tempstr +"\" ["+times+"次]连加，无预估长度！(循环 "+rolecount+" 次平均时间)");
		System.out.println("==================================================================");
		

		count = 0L;
		for(int i=0;i<rolecount;i++)
			count+=funBuffer();
		System.out.println("StringBuffer\t\t\t:"+(count/rolecount)+"\tns");
		System.out.println("==================================================");
		
		

		count = 0L;
		for(int i=0;i<rolecount;i++)
			count+=funBuilder();
		System.out.println("StringBuilder\t\t\t:"+(count/rolecount)+"\tns");
		System.out.println("==================================================");
		
		
		
		
		
		
		
		
		count = 0L;
		for(int i=0;i<rolecount;i++)
			count+=funSBuilder();
		System.out.println("MQSBuilder\t\t\t:"+(count/rolecount)+"\tns");
		System.out.println("==================================================");
		/*
		count = 0L;
		for(int i=0;i<rolecount;i++)
			count+=funSBuilder1();
		System.out.println("MQSBuilder(Object)\t\t:"+(count/rolecount));
		System.out.println("==================================================");
		count = 0L;
		for(int i=0;i<rolecount;i++)
			count+=funSBuilder2();
		System.out.println("MQSBuilder(toCharArray) \t:"+(count/rolecount));
		System.out.println("==================================================");
		*/

	}

}
