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
 * 微信所有接口入口
 * 
 * @author f1j 2013-7-26 上午11:01:08
 */
public class PushManage {

	public String PushManageXml(InputStream is) throws JDOMException {
		String returnStr = "";
		String toName = "";
		String FromName = "";
		String type = "";
		String content = "";
		String con = "";
		String event = "";// 自定义按钮事件请求
		String eKey = "";// 事件请求key值

		try {

			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(is);
			// 获得文件的根元素
			Element root = doc.getRootElement();

			// 获得根元素的第一级子节点
			List list = root.getChildren();
			for (int j = 0; j < list.size(); j++) {
				// 获得结点
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
			if (event.equals("subscribe")) {// 此为关注事件
				content = "回复1，电子菜谱；\n" + "回复2，收银管理；\n" + "回复3，手机点菜宝\n"
						+ "回复4，无线打印；\n" + "回复5，了解F1j；\n" + "回复6，销售平台；\n"
						+ "回复7，工作流平台；\n" + "回复8，零售平台；\n" + "回复9，微信点餐；\n"
						+ "回复10，微信商城；\n" + "回复11，商务。\n";

			}
		} else if (type.equals("text")) {
			if (con.equals("help") || con.equals("帮助") || con.equals("?")
					|| con.equals("？")) {
				content = "回复1，电子菜谱；\n" + "回复2，收银管理；\n" + "回复3，手机点菜宝\n"
						+ "回复4，无线打印；\n" + "回复5，了解F1j；\n" + "回复6，销售平台；\n"
						+ "回复7，工作流平台；\n" + "回复8，零售平台；\n" + "回复9，微信点餐；\n"
						+ "回复10，微信商城；\n" + "回复11，商务。\n";
			} else if (con.equals("1")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m.jsp'>打开</a>";
			} else if (con.equals("2")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("3")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("4")) {
				content = "http://210.16.187.119:8080/o2o/m.jsp";
			} else if (con.equals("5")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m/wxuser.html'>打开</a>";
			} else if (con.equals("6")) {
				content = "http://210.16.187.119:81/crm";
			} else if (con.equals("7")) {
				content = "http://210.16.187.119:81/oa";
			} else if (con.equals("8")) {
				content = "http://210.16.187.119:81/retail";
			} else if (con.equals("9")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m'>ESSE（爱喜咖啡）</a>";
			} else if (con.equals("10")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m'>ESSE（爱喜咖啡）</a>";
			} else if (con.equals("11")) {
				content = "<a href='http://210.16.187.119:8080/o2o/m/wxuser.html'>打开</a>";
			} else if (con.equals("bind") || con.equals("绑定")) {
				content = "点击 <A href=\"''\" target=_blank>绑定账户</A>";
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

					sb.append("订单详情... ");
					content = sb.toString();
				} else {
					content = "未查询到相应信息，请重新输入！";
				}

			}
		}
		// 以下为自定义按钮事件
		if (eKey.equals("music")) {// 音乐请求
			returnStr = getBackXMLTypeMusic(toName, FromName,
					"http://view.online.zcom.com/full/16107/風之誓言.mp3");
		} else if (eKey.equals("img")) {// 图片请求
			content = "http://a.hiphotos.baidu.com/album/w%3D2048/sign=61aa038f622762d0803ea3bf94d409fa/d62a6059252dd42ae072bd07023b5bb5c9eab827.jpg";
			returnStr = getBackXMLTypeImg(toName, FromName, content);
		} else {// 无自定义按钮返回上面的文本信息
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
			mXML.addContent(new Element("Title").setText("图片" + i));
			mXML.addContent(new Element("Description").setText("美女" + i));
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

		mXML.addContent(new Element("Title").setText("音乐"));
		mXML.addContent(new Element("Description").setText("音乐让人心情舒畅！"));
		mXML.addContent(new Element("MusicUrl").setText(content));
		mXML.addContent(new Element("HQMusicUrl").setText(content));

		rootXML.addContent(mXML);

		Document doc = new Document(rootXML);

		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);

		return returnStr;
	}
}
