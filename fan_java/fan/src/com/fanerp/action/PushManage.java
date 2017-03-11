package com.fanerp.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * ΢�����нӿ����
 * 
 * @author f1j 2013-7-26 ����11:01:08
 */
public class PushManage {

	public String PushManageXml(InputStream is) throws JDOMException {
		String returnStr = "";
		String toName = "";
		String FromName = "";
		String type = "";
		String content = "";
		String con = "";
		String event = "";// �Զ��尴ť�¼�����
		String eKey = "";// �¼�����keyֵ

		try {

			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(is);
			// ����ļ��ĸ�Ԫ��
			Element root = doc.getRootElement();

			// ��ø�Ԫ�صĵ�һ���ӽڵ�
			List list = root.getChildren();
			for (int j = 0; j < list.size(); j++) {
				// ��ý��
				Element first = (Element) list.get(j);

				if (first.getName().equals("ToUserName")) {
					toName = first.getValue().trim();
				} else if (first.getName().equals("FromUserName")) {
					FromName = first.getValue().trim();
				} else if (first.getName().equals("MsgType")) {
					type = first.getValue().trim();
				} else if (first.getName().equals("Content")) {
					con = first.getValue().trim();
				} else if (first.getName().equals("Event")) {
					event = first.getValue().trim();
				} else if (first.getName().equals("EventKey")) {
					eKey = first.getValue().trim();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (type.equals("event")) {
			if (event.equals("subscribe")) {// ��Ϊ��ע�¼�
				content = "�ظ�1�����Ӳ��ף�\n" + "�ظ�2����������\n" + "�ظ�3���ֻ���˱�\n"
						+ "�ظ�4�����ߴ�ӡ��\n" + "�ظ�5���˽�F1j��\n" + "�ظ�6������ƽ̨��\n"
						+ "�ظ�7��������ƽ̨��\n" + "�ظ�8������ƽ̨��\n" + "�ظ�9��΢�ŵ�ͣ�\n"
						+ "�ظ�10��΢���̳ǣ�\n" + "�ظ�11������\n";

			}
		} else if (type.equals("text")) {
			if (con.equals("help") || con.equals("����") || con.equals("?")
					|| con.equals("��")) {
				content = "�ظ�1�����Ӳ��ף�\n" + "�ظ�2����������\n" + "�ظ�3���ֻ���˱�\n"
						+ "�ظ�4�����ߴ�ӡ��\n" + "�ظ�5���˽�F1j��\n" + "�ظ�6������ƽ̨��\n"
						+ "�ظ�7��������ƽ̨��\n" + "�ظ�8������ƽ̨��\n" + "�ظ�9��΢�ŵ�ͣ�\n"
						+ "�ظ�10��΢���̳ǣ�\n" + "�ظ�11������\n";
			} else if (con.equals("1")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m.jsp'>��</a>";
			} else if (con.equals("2")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("3")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("4")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("5")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m/wxuser.html'>��</a>";
			} else if (con.equals("6")) {
				content = "http://210.16.187.119:81/crm";
			} else if (con.equals("7")) {
				content = "http://210.16.187.119:81/oa";
			} else if (con.equals("8")) {
				content = "http://210.16.187.119:81/retail";
			} else if (con.equals("9")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m'>ESSE����ϲ���ȣ�</a>";
			} else if (con.equals("10")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m'>ESSE����ϲ���ȣ�</a>";
			} else if (con.equals("11")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m/wxuser.html'>��</a>";
			} else if (con.equals("bind") || con.equals("��")) {
				content = "��� <A href=\"''\" target=_blank>���˻�</A>";
			} else if (con.startsWith("ck") || con.startsWith("CK")) {
				String order_id = "0";
				con.toLowerCase();
				if (con.startsWith("ck")) {
					order_id = con.substring(con.indexOf("ck") + 2);
				} else if (con.startsWith("CK")) {
					order_id = con.substring(con.indexOf("CK") + 2);
				}
				if (!"".equals(order_id)) {
					StringBuffer sb = new StringBuffer();

					sb.append("��������... ");
					content = sb.toString();
				} else {
					content = "δ��ѯ����Ӧ��Ϣ�����������룡";
				}

			}
		}
		// ����Ϊ�Զ��尴ť�¼�
		if (eKey.equals("music")) {// ��������
			returnStr = getBackXMLTypeMusic(toName, FromName,
					"http://view.online.zcom.com/full/16107/�L֮����.mp3");
		} else if (eKey.equals("img")) {// ͼƬ����
			content = "http://a.hiphotos.baidu.com/album/w%3D2048/sign=61aa038f622762d0803ea3bf94d409fa/d62a6059252dd42ae072bd07023b5bb5c9eab827.jpg";
			returnStr = getBackXMLTypeImg(toName, FromName, content);
		} else {// ���Զ��尴ť����������ı���Ϣ
			returnStr = getBackXMLTypeText(toName, FromName, content);
		}
		return returnStr;
	}

	public String getBackXMLTypeText(String toName, String FromName,
			String content) {

		String returnStr = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());

		Element rootXML = new Element("xml");

		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("text"));
		rootXML.addContent(new Element("Content").setText(content));

		Document doc = new Document(rootXML);

		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);

		return returnStr;
	}

	public String getBackXMLTypeImg(String toName, String FromName,
			String content) {

		String returnStr = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());

		Element rootXML = new Element("xml");

		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("news"));
		rootXML.addContent(new Element("ArticleCount").setText("3"));

		Element fXML = new Element("Articles");
		Element mXML = null;

		String url = "http://www.baidu.com";
		String ss = "";
		for (int i = 1; i <= 3; i++) {
			mXML = new Element("item");
			mXML.addContent(new Element("Title").setText("ͼƬ" + i));
			mXML.addContent(new Element("Description").setText("��Ů" + i));
			mXML.addContent(new Element("PicUrl").setText(ss));
			mXML.addContent(new Element("Url").setText(url));
			fXML.addContent(mXML);
		}
		rootXML.addContent(fXML);

		Document doc = new Document(rootXML);

		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);

		return returnStr;
	}

	public String getBackXMLTypeMusic(String toName, String FromName,
			String content) {

		String returnStr = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());

		Element rootXML = new Element("xml");

		rootXML.addContent(new Element("ToUserName").setText(FromName));
		rootXML.addContent(new Element("FromUserName").setText(toName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("music"));

		Element mXML = new Element("Music");

		mXML.addContent(new Element("Title").setText("����"));
		mXML.addContent(new Element("Description").setText("�������������泩��"));
		mXML.addContent(new Element("MusicUrl").setText(content));
		mXML.addContent(new Element("HQMusicUrl").setText(content));

		rootXML.addContent(mXML);

		Document doc = new Document(rootXML);

		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);

		return returnStr;
	}
}
