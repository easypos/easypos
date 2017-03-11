package com.fanerp.action;

import java.io.InputStream;
import java.io.PrintWriter;

import org.jdom.JDOMException;

import com.f1jeeframework.http.Action;

/**
 * 视图控制器,一般采用JSP显示DATA，这里采用的是XML。
 */
public class WeiXin extends Action {
	private String viewApp;
	private String catgory;
	//private ViewBulider builder = new ViewBulider();

	public void afterExcute(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		// request.getParameter("")
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// request.setCharacterEncoding("UTF-8");
		String echoStr = request.getParameter("echostr");// 随机字符串
		PrintWriter out = response.getWriter();
		// String signature = request.getParameter("signature");//SHA1加密字符串
		// String timestamp = request.getParameter("timestamp");//时间
		// String nonce = request.getParameter("nonce");//随机数
		// String echoStr = request.getParameter("echostr");//随机字符串
		if (echoStr != null) {
			// String[] a = {TOKEN,timestamp,nonce};
			// Arrays.sort(a);//数组排序
			// String str = "";
			// for(int i=0;i<a.length;i++){
			// str += a[i];
			// }
			// String echo = new
			// SHA1().getDigestOfString(str.getBytes());//SHA1加密

			// if(echo.equals(signature)){
			// out.print(echoStr);
			// }else{
			// out.print("123");
			// }
			response.getWriter().print(echoStr);
		}
		{
			try {
				InputStream is = request.getInputStream();
				PushManage push = new PushManage();
				String getXml = push.PushManageXml(is);
				System.out.println("getXml:" + getXml);
				out.print(getXml);
			} catch (JDOMException e) {
				out.print("");
			}
		}
	}

	private String nullDo(String s) {
		if (s == null)
			s = "";
		return s;
	}
}