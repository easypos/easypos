package com.ysk.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;

public class SendSms {

	private String mobile_code;
	private boolean resultOk = false;
	
	static{
		System.out.println("123");
	}

	public String getMobile_code() {
		return mobile_code;
	}

	public void setMobile_code(String mobile_code) {
		this.mobile_code = mobile_code;
	}

	public boolean isResultOk() {
		return resultOk;
	}

	public void setResultOk(boolean resultOk) {
		this.resultOk = resultOk;
	}

	@Test
	public void test() {
		SendSms sendSms = new SendSms();
		sendSms.send("13321951790");
	}

	public void send(String mobile) {
		Logger.getLogger(this.getClass().getName()).info("send sms init!");
		
		String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		this.setMobile_code(Integer.valueOf(mobile_code).toString());
		String account = "cf_kylinml";
		String password = "111111";
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		try {
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);// 允许连接提交信息
			connection.setRequestMethod("POST");// 网页提交方式“GET”、“POST”
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("account=" + account);
			sb.append("&password=" + password);
			sb.append("&mobile=" + mobile);
			sb.append("&content=" + content);
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();

			SAXBuilder builder;
			Document doc = null;
			System.out.println(result);
			// Element root;
			// builder = new SAXBuilder();
			builder = new SAXBuilder("org.apache.crimson.parser.XMLReaderImpl");
			// 创建一个新的字符串
			StringReader read = new StringReader(result);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			//InputSource source = new InputSource(read);
			doc = builder.build("");
			// Document doc = DocumentHelper.parseText(result);
			// Element root = doc.getRootElement();
			System.out.println(doc.getRootElement().getName());
			String code = (String) doc.getRootElement().getChildText("code");
			String msg = (String) doc.getRootElement().getAttributeValue("msg");
			String smsid = (String) doc.getRootElement().getAttributeValue(
					"smsid");
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if ("2".equals(code)) {
				System.out.println("短信提交成功");
				this.setResultOk(true);
			}

			// out.println(result);

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
