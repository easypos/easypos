package com.ysk.sms;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class PhoneApiTest {

	public PhoneApiTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String mobile_code;
	private boolean resultOk = false;

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	@Test
	public void test() {
		PhoneApiTest sendSms = new PhoneApiTest();
		sendSms.send("-");
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

	public void setResultOk(boolean result) {
		this.resultOk = result;
	}

	public void send(String phone) {
		Logger.getLogger(this.getClass().getName()).info("send sms init!");
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		// client.getParams().setContentCharset("GBK");
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		this.setMobile_code(Integer.valueOf(mobile_code).toString());
		// System.out.println(mobile);

		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		System.out.println(content);
		NameValuePair[] data = {// 提交短信
				new NameValuePair("account", "cf_kylinml"),
				new NameValuePair("password", "111111"), // 密码可以使用明文密码或使用32位MD5加密				
				// 密码可以使用明文密码或使用32位MD5加密
				// new NameValuePair("password",
				// util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", phone),
				new NameValuePair("content", content), };

		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			// System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if ("2".equals(code)) {
				System.out.println("短信提交成功");
				this.setResultOk(true);
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}