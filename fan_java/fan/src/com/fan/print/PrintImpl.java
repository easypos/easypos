package com.fan.print;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.fanerp.util.StringAlign;

public class PrintImpl {
	private static ExecutorService executorService = Executors
			.newSingleThreadExecutor();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final PrintImpl printTest = new PrintImpl();
		for (int i = 0; i < 3; i++) {
			// for ()
			Runnable r = new Runnable() {
				public void run() {
					try {
						printTest.print("", "", 1, 1, "192.168.1.168", "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			executorService.execute(r);
		}
		for (int i = 0; i < 3; i++) {
			try {
				printTest.print("", "", 1, 1, "192.168.1.168", "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// PrintJob job =getToolkit().getPrintJob(this,"OK",null);

	}

	public static void printOrder(final String title, final String order,
			final int count, final int sum, final String ip, final String more) {
		Runnable r = new Runnable() {
			public void run() {
				try {
					// final PrintImpl printTest = new PrintImpl();
					print(title, order, count, sum, ip, more);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 将打印失败信息写入数据库;
				}
			}
		};
		executorService.execute(r);
	}

	public static void printBuy(final String title, final String order,
			final int count, final int sum, final int money, final int change,
			final String end, final String ip, final String more) {
		Runnable r = new Runnable() {
			public void run() {
				try {
					// final PrintImpl printTest = new PrintImpl();
					print(title, order, count, sum, money, change, end, ip,
							more);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 将打印失败信息写入数据库;
				}
			}
		};
		executorService.execute(r);
	}

	@Test
	public void printTest() throws IOException {
		final PrintImpl printTest = new PrintImpl();
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();
		map.put("name", "江南怪味鸭");
		map.put("price", 98);
		map.put("dishcount", 1);
		list.add(map);
		map = new HashMap();
		map.put("name", "江南怪味鸭");
		map.put("price", 98);
		map.put("dishcount", 1);
		list.add(map);
		map = new HashMap();
		map.put("name", "江南怪味鸭\n");
		map.put("price", 98);
		map.put("dishcount", 1);
		list.add(map);
		map = new HashMap();
		map.put("name", "江南怪味鸭\n");
		map.put("price", 98);
		map.put("dishcount", 1);
		list.add(map);
		// printTest.print("人数  _8,12:30:30 , 服务员(0001)\n", list, 2, 1280);
		printTest.print("人数  _8,12:30:30 , 服务员(0001)\n", "aaa 1\n", 2, 1280,
				"192.168.1.168", "");
		printTest.print("人数  _8,12:30:30 , 服务员(0001)\n", "aaa 1\n", 2, 1280,
				2000, 100, "2012-12-31 08:30:30\n", "192.168.1.168", "aaa");
	}

	public void print(String title, List list, int count, int sum)
			throws IOException {
		Socket client = new java.net.Socket();
		// PrintWriter socketWriter;
		client.connect(new InetSocketAddress("192.168.1.168", 9100), 1000);
		OutputStream out = client.getOutputStream();
		byte[] codeInit = { 0x1b, 0x40 };
		// byte[] codeAlignLeft = { 27, 97, 48 };
		// byte[] codeAlignRight = { 27, 97, 50 };
		byte[] fontAlign = new byte[] { 0x1b, 0x61, 0x00 };
		byte[] init = new byte[] { 0x1b, 0x3c };
		byte[] abPosition = new byte[] { 0x1b, 0x24, 0x60, 0x00 };
		byte[] cutPaper = new byte[] { 0x1D, 0x56, 0x42, 0x00 };
		out.write(codeInit);
		// fontAlign[2] = 0x02;
		String strLine = ("_________________________________________________________\n");
		out.write(title.getBytes());
		out.write(strLine.toString().getBytes());
		// out.write(fontAlign);
		StringBuffer strOrder = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map a = (Map) list.get(i);
			// String
			String name = (String) a.get("name");
			Integer dishcount = (Integer) a.get("dishcount");
			Integer price = (Integer) a.get("price");
			fontAlign[2] = 0x02;
			out.write(fontAlign);
			out.write(price.toString().getBytes());
			out.write(abPosition);
			out.write(name.toString().getBytes());
			abPosition[2] = 0x0;
			out.write(abPosition);
		}
		// out.write(order.getBytes());
		StringAlign formatter = new StringAlign(6, StringAlign.JUST_RIGHT,
				false);
		StringBuffer strSum = new StringBuffer();
		fontAlign[2] = 0x02;
		strSum.append("数量:");
		strSum.append(formatter.format(new Integer(count).toString()));
		strSum.append("\n");
		strSum.append("合计:");
		strSum.append(formatter.format(new Integer(sum).toString()));
		strSum.append("\n");
		out.write(fontAlign);
		out.write(strSum.toString().getBytes());
		fontAlign[2] = 0x00;
		out.write(fontAlign);
		StringBuffer strYsk = new StringBuffer();
		out.write(strLine.toString().getBytes());
		// strYsk.append("云尚客 :中国移动餐饮第一品牌(13023119819)\n");
		out.write(strYsk.toString().getBytes());
		out.write(cutPaper);
		out.close();
		client.close();
		// String cutpaper = "\035V\101\000\n";
		// out.write(cutpaper.getBytes());
		// socketWriter.println(str);
		// 创建输入输出数据流
	}

	public static void print(String title, String order, int count, int sum,
			String ip, String more) throws IOException {
		System.out.print("print test...!");
		Socket client = new java.net.Socket();
		// PrintWriter socketWriter;
		client.connect(new InetSocketAddress(ip, 9100), 1000);
		// client.sen
		// 创建一个 socket
		// socketWriter = new PrintWriter(client.getOutputStream());
		OutputStream out = client.getOutputStream();
		byte[] codeInit = { 0x1b, 0x40 };
		// byte[] codeAlignLeft = { 27, 97, 48 };
		// byte[] codeAlignRight = { 27, 97, 50 };
		byte[] fontAlign = new byte[] { 0x1b, 0x61, 0x00 };
		byte[] fontSize = new byte[] { 0x1D, 0x21, 0x00 };
		byte[] init = new byte[] { 0x1b, 0x3c };
		byte[] abPosition = new byte[] { 0x1b, 0x24, 0x60, 0x00 };
		byte[] cutPaper = new byte[] { 0x1D, 0x56, 0x42, 0x00 };
		out.write(codeInit);
		fontAlign[2] = 0x02;
		fontSize[2] = 0x10;
		out.write(fontSize);
		fontSize[2] = 0x01;
		out.write(fontSize);
		fontAlign[2] = 0x01;
		out.write(fontAlign);
		String header = "服务单\n";
		out.write(header.getBytes());
		fontAlign[2] = 0x00;
		fontSize[2] = 0x00;
		out.write(fontSize);
		out.write(fontAlign);
		fontAlign[2] = 0x02;
		String strLine = ("________________________________________________\n");
		out.write(title.getBytes());
		out.write(strLine.toString().getBytes());
		// out.write(fontAlign);
		out.write(order.getBytes());
		StringAlign formatter = new StringAlign(6, StringAlign.JUST_LEFT, false);
		StringBuffer strSum = new StringBuffer();
		fontAlign[2] = 0x02;
		strSum.append("数量:");
		strSum.append(formatter.format(new Integer(count).toString()));
		strSum.append("\n");
		strSum.append("合计:");
		strSum.append(formatter.format(new Integer(sum).toString()));
		strSum.append("\n");
		out.write(fontAlign);
		out.write(strSum.toString().getBytes());
		fontAlign[2] = 0x00;
		out.write(fontAlign);
		// StringBuffer strYsk = new StringBuffer();

		out.write(strLine.toString().getBytes());
		if (more == null)
			more = "";
		if (!more.equals("")) {
			out.write(more.getBytes());
		}
		// strYsk.append("云尚客 :上海市东方路738号裕安大厦主楼1506号\n");
		// out.write(strYsk.toString().getBytes());
		out.write(YSK().getBytes());
		out.write(cutPaper);
		out.close();
		client.close();
		// String cutpaper = "\035V\101\000\n";
		// out.write(cutpaper.getBytes());
		// socketWriter.println(str);
		// 创建输入输出数据流
	}

	public static void print(String title, String order, int count, int sum,
			int money, int change, String end, String ip, String more)
			throws IOException {
		System.out.print("print ...!");
		Socket client = new java.net.Socket();
		client.connect(new InetSocketAddress(ip, 9100), 1000);
		OutputStream out = client.getOutputStream();
		byte[] codeInit = { 0x1b, 0x40 };
		// byte[] codeAlignLeft = { 27, 97, 48 };
		// byte[] codeAlignRight = { 27, 97, 50 };
		byte[] fontSize = new byte[] { 0x1D, 0x21, 0x00 };
		byte[] fontAlign = new byte[] { 0x1b, 0x61, 0x00 };
		byte[] init = new byte[] { 0x1b, 0x3c };
		byte[] abPosition = new byte[] { 0x1b, 0x24, 0x60, 0x00 };
		byte[] cutPaper = new byte[] { 0x1D, 0x56, 0x42, 0x00 };
		out.write(codeInit);
		fontAlign[2] = 0x02;
		fontSize[2] = 0x10;
		out.write(fontSize);
		fontSize[2] = 0x01;
		out.write(fontSize);
		fontAlign[2] = 0x01;
		out.write(fontAlign);
		String header = "对账单\n";
		out.write(header.getBytes());
		fontAlign[2] = 0x00;
		fontSize[2] = 0x00;
		out.write(fontSize);
		out.write(fontAlign);
		String strLine = ("________________________________________________\n");
		out.write(strLine.toString().getBytes());
		out.write(title.getBytes());
		out.write(strLine.toString().getBytes());
		// out.write(fontAlign);
		out.write(order.getBytes());
		StringAlign formatter = new StringAlign(6, StringAlign.JUST_LEFT, false);
		StringBuffer strSum = new StringBuffer();
		fontAlign[2] = 0x02;
		strSum.append("数量:");
		strSum.append(formatter.format(new Integer(count).toString()));
		strSum.append("\n");
		strSum.append("合计:");
		strSum.append(formatter.format(new Integer(sum).toString()));
		strSum.append("\n");
		strSum.append("收款:");
		strSum.append(formatter.format(new Integer(money).toString()));
		strSum.append("\n");
		strSum.append("找钱:");
		strSum.append(formatter.format(new Integer(change).toString()));
		// strSum.append(change);
		strSum.append("\n");
		out.write(fontAlign);
		out.write(strSum.toString().getBytes());
		fontAlign[2] = 0x00;
		out.write(fontAlign);
		out.write(strLine.toString().getBytes());
		fontAlign[2] = 0x01;
		out.write(fontAlign);
		out.write(end.getBytes());
		fontAlign[2] = 0x00;
		out.write(fontAlign);
		out.write(strLine.toString().getBytes());
		if (more == null)
			more = "";
		if (!more.equals("")) {
			out.write(more.getBytes());
		}
		out.write(YSK().getBytes());
		out.write(cutPaper);
		out.close();
		client.close();
		// String cutpaper = "\035V\101\000\n";
		// out.write(cutpaper.getBytes());
		// socketWriter.println(str);
		// 创建输入输出数据流
	}

	private static String YSK() {
		StringBuffer strYsk = new StringBuffer();
		strYsk.append("设计 :YSK网络科技有限公司\n");
		// strYsk.append("电话 :13611172109（北京）13023119819（上海）\n ");
		// strYsk.append("地址 :上海市东方路裕安大厦1608\n");
		return strYsk.toString();
	}

	public void printByString(String title, String order, int count, int sum) {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("\033@\n");
		strbuf.append(title);
		strbuf.append("\n");
		strbuf.append("________________________________________\n");
		strbuf.append("\035!\001\035!\020\n");
		strbuf.append(order);
		strbuf.append("\n");
		strbuf.append("数量:");
		strbuf.append(count);
		strbuf.append("\n");
		strbuf.append("合计:");
		strbuf.append(sum);
		strbuf.append("\n");
		strbuf.append("\035!\000\n");
		strbuf.append("________________________________________\n");
		// strbuf.append("快船科技 :中国移动餐饮第一品牌(13023119819)\n");
		strbuf.append("\012\n");
		strbuf.append("\035V\101\000\n");
	}

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	// @Test
	public void align() {
		// String a[] = { "一 二三 ", "一 二三 四", "一 二三 四", "一 二三 四" };
		String a_ = "\033@";
		// str2ascii(a_);
		System.out.println(a_);
		// System.out.print(a_.getBytes());
		for (int i = 0; i < a_.getBytes().length; i++) {
			System.out.println(a_.getBytes()[i]);
		}
		printHexString(a_.getBytes());
		System.out.println("\n===============================================");
		byte[] byte_ = hexStringToBytes("1B69");
		System.out.println("16转byte");
		for (int i = 0; i < byte_.length; i++) {
			System.out.println(byte_[i]);
		}
		System.out.println("byte转串");
		System.out.println(BytesToStr(byte_));
		System.out.println(new String(byte_));
		;
		// System.out.println("\033@");

	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}

	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String BytesToStr(byte[] target) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0, j = target.length; i < j; i++) {
			buf.append((char) target[i]);
		}
		return buf.toString();
	}

	public static void str2ascii(String s) {// 字符串转换为ASCII码
		char[] chars = s.toCharArray(); // 把字符中转换为字符数组
		// System.out.println("\n\n汉字 ASCII\n----------------------");
		for (int i = 0; i < chars.length; i++) {// 输出结果
			System.out.println(" " + chars[i] + " " + (int) chars[i]);
		}
	}

	private byte[] getBytes(char[] chars) {
		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	@Test
	public void testgetBytes() {
		char[] c = { 27, 'p', 0, 60, 240 };
		byte[] r = getBytes(c);
		for (int i = 0; i < r.length; i++) {
			System.out.print("\n");
			System.out.print(r[i]);
		}
		printHexString(r);
	}

}
