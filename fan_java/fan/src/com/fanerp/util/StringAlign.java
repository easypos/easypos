package com.fanerp.util;

import java.text.*;

/** �����ַ�����ʽ�������ַ������룩 */
public class StringAlign extends Format {
	/* ���������ĳ��� */
	public static final int JUST_LEFT = 'l';
	/* ���ھ��еĳ��� */
	public static final int JUST_CENTRE = 'c';
	/* ���г�������ʽӢ��ƴдΪ"centre"�� */
	public static final int JUST_CENTER = JUST_CENTRE;
	/** �����Ҷ���ĳ��� */
	public static final int JUST_RIGHT = 'r';
	/** ��ǰ����λ�� */
	private int just;
	private boolean cn;
	/** ��ǰ�����п����ַ�Ϊ��λ�� */
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

	/** ��ʽ���ַ��� */
	public StringBuffer format(Object obj, StringBuffer where,
			FieldPosition ignore) {
		String s = (String) obj;
		String wanted = s.substring(0, Math.min(s.length(), maxChars));
		// ���û�пռ����ڶ��룬��ֻ�����п�maxChars�������� */
		if (wanted.length() > maxChars) {
			where.append(wanted);
		}
		// ���򣬿ɵõ��ұ߿ռ�Ŀո���
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
				// ������ȡ����
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
			to.append("��");
			}
			else
			{
				to.append(" ");
			}
		    //to.append(' ');
	}

	/** �ӳ��� */
	String format(String s) {
		return format(s, new StringBuffer(), null).toString();
	}

	/** ParseObject�Ǳ���ģ��������ﲢû�ж���ô� */
	public Object parseObject(String source, ParsePosition pos) {
		return source;
	}

	public static void main(String[] args) {
		// ����һ����ʽ������formatter�������ַ�������
		StringAlign formatter = new StringAlign(8, StringAlign.JUST_RIGHT,true);
		// ���Ե�iҳ
		System.out.println(formatter.format("���Ϲ�ζѼѼѼ"));
		// ���Ե�4ҳ�����������ʽ����������ַ�����
		// �����Ǵ���ҳ�ţ�int�ͣ�
		// �������Ҫ������ת��Ϊ�ַ���
		System.out.println(formatter.format("���Ϲ�ζѼ"));
		String strLine = ("________________________________________________\n");
		System.out.println(strLine.length());
	}

}