package com.fanerp.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;

public class SaveFanDianPing extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=utf-8");
		String id = arg0.getParameter("id");
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			boService.init("fandishdianping", null, null);
			if (oasession == null) {
				oasession = new OASession();
				oasession.setUserName("user");
				oasession.setLoginID(0);
			}
			boService.setSession((OASession) oasession);
			boService.setParameterValues(RequestUtil.getParamValues(arg0));
			String content = arg0.getParameter("content");
			boService.getParameterValues().put("content",
					new String(content.getBytes("gbk"), "utf-8"));
			boService.add(new Integer(boService.maxId() + 1).toString(),
					"fandishdianping");
			this.setForward(false);
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}

	@Test
	public void code() throws UnsupportedEncodingException {
		String content = "ÄãºÃ";
		System.out.println("=========================================1"
				+ URLDecoder.decode(content, "gbk"));
		System.out.println("=========================================2"
				+ URLDecoder.decode(content, "utf-8"));
		System.out.println("========================================3"
				+ new String(content.getBytes("gbk"), "utf-8"));		
		System.out.println("========================================4"
				+ new String(content.getBytes("utf-8"), "gbk"));

		
		
		
		
		System.out.println("========================================5"
				+ new String(content.getBytes("gbk"), "ISO-8859-1"));
		String a = new String(content.getBytes("gbk"), "ISO-8859-1");
		System.out.println("=========================================6"
				+ new String(a.getBytes("gbk"), "ISO-8859-1"));

	}
}
