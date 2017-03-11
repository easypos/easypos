package com.fanerp.util;

import java.text.*;

/** 袖珍字符串格式化器（字符串对齐） */
public class StringAlign extends Format {
	/* 用于左对齐的常量 */
	public static final int JUST_LEFT = 'l';
	/* 用于居中的常量 */
	public static final int JUST_CENTRE = 'c';
	/* 居中常量（美式英语拼写为"centre"） */
	public static final int JUST_CENTER = JUST_CENTRE;
	/** 用于右对齐的常量 */
	public static final int JUST_RIGHT = 'r';
	/** 当前对齐位置 */
	private int just;
	private boolean cn;
	/** 当前最大的行宽（以字符为单位） */
	private int maxChars;

	public StringAlign(int maxChars, int just,boolean cn) {
		switch (just) {
		case JUST_LEFT:
		case JUST_CENTRE:
		case JUST_RIGHT:
			this.just = just;
			break;
		default:
			throw new IllegalArgumentException("invalid justification arg.");
		}
		if (maxChars < 0) {
			throw new IllegalArgumentException("maxChars must be positive.");
		}
		this.maxChars = maxChars;
		this.cn=cn;
	}

	/** 格式化字符串 */
	public StringBuffer format(Object obj, StringBuffer where,
			FieldPosition ignore) {
		String s = (String) obj;
		String wanted = s.substring(0, Math.min(s.length(), maxChars));
		// 如果没有空间用于对齐，则分会最大行宽（maxChars）的内容 */
		if (wanted.length() > maxChars) {
			where.append(wanted);
		}
		// 否则，可得到右边空间的空格数
		else
			switch (just) {
			case JUST_RIGHT:
				pad(where, maxChars - wanted.length());
				where.append(wanted);
				break;
			case JUST_CENTRE:
				int startPos = where.length();
				pad(where, (maxChars - wanted.length()) / 2);
				where.append(wanted);
				pad(where, (maxChars - wanted.length()) / 2);
				// 调整“取整误差”
				pad(where, maxChars - (where.length() - startPos));
				break;
			case JUST_LEFT:
				where.append(wanted);
				pad(where, maxChars - wanted.length());
				break;
			}
		return where;
	}

	protected final void pad(StringBuffer to, int howMany) {
		for (int i = 0; i < howMany; i++)
			if (cn){
			to.append("　");
			}
			else
			{
				to.append(" ");
			}
		    //to.append(' ');
	}

	/** 子程序 */
	String format(String s) {
		return format(s, new StringBuffer(), null).toString();
	}

	/** ParseObject是必需的，但在这里并没有多大用处 */
	public Object parseObject(String source, ParsePosition pos) {
		return source;
	}

	public static void main(String[] args) {
		// 构造一个格式化器（formatter）用于字符串居中
		StringAlign formatter = new StringAlign(8, StringAlign.JUST_RIGHT,true);
		// 测试第i页
		System.out.println(formatter.format("江南怪味鸭鸭鸭"));
		// 测试第4页，由于这个格式化器是针对字符串的
		// 而不是处理页号（int型）
		// 因而，需要将数字转化为字符串
		System.out.println(formatter.format("江南怪味鸭"));
		String strLine = ("________________________________________________\n");
		System.out.println(strLine.length());
	}

}