package testMQStringBuilder;
import org.junit.Test;
import MaQiao.MaQiaoStringBuilder.MQSBuilder;
public class MQStringBuilderTest {
	final int times = 100*10000;//2147483647
	final String tempstr = "abcdefghijklmnopqrstuvwxyz";
	final char[] temparray = tempstr.toCharArray();
	int defaultlen=times*temparray.length;
	private void funBuffer() {
		long lstart2 = System.nanoTime();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		System.out.println("StringBuffer\t:" + time2 + "\tns");
		System.out.println("len:" + sb.length()+"/" + sb.capacity());
	}

	private void funBuilder() {
		long lstart2 = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(tempstr);
		}
		long lend2 = System.nanoTime();
		long time2 = (lend2 - lstart2);
		System.out.println("StringBuilder\t:" + time2 + "\tns");
		System.out.println("len:" + sb.length()+"/" + sb.capacity());
	}

	private void funSBuilder() {
		long lstart2 = System.nanoTime();
		//MQSBuilder sb = new MQSBuilder(30000000);
		try(MQSBuilder sb = new MQSBuilder();){
			for (int i = 0; i < times; i++) {
				//sb.appendDeprecated(tempstr);
				//System.out.println(sb.length()+"/"+sb.capacity());
				sb.append(tempstr);
				//sb.append(true);
			}
			long lend2 = System.nanoTime();
			long time2 = (lend2 - lstart2);
			System.out.println("MQSBuilder\t:" + time2 + "\tns");
			System.out.println("len:" + sb.length()+"/" + sb.capacity());
		}		
		//for(int i=0;i<15;i++)
		//System.out.println((172000011+i)+":" + sb.getChar(172000011+i));
		//sb.free();
	}



	@Test
	public void aa() {
		MQStringBuilderTest test = new MQStringBuilderTest();
		test.funBuilder();
		test.funBuffer();
		test.funSBuilder();
	}

}