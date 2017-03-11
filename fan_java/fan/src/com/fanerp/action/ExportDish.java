package com.fanerp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;

public class ExportDish extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		// StringBuffer strBuf = new StringBuffer();
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String id = arg0.getParameter("id");
		String fid = arg0.getParameter("fid");
		String action = arg0.getParameter("action");
		String name = arg0.getParameter("txt");
		boService.init(fid, null, null);
		boService.setSession((OASession) oasession);
		boService.setParameterValues(RequestUtil.getParamValues(arg0));
		String a[] = name.split("-");
		for (Integer i = 0; i < a.length; i++) {
			id = "";
			if (id.equals(""))
				id = new Integer(boService.maxId() + 1).toString();
			String ai[] = a[i].split("a");
			System.out.println("name:===" + a[0].trim());
			System.out.println("price:===" + a[1].trim());
			boService.getParameterValues().put("name", ai[0].trim());
			boService.getParameterValues().put("price", ai[1].trim());
			boService.add(id, fid);
		}

		// strBuf.append("Ìá½»³É¹¦!");
		// arg1.getWriter().print(strBuf.toString());
		this.setForward(false);
		// PageFlowConfig read = (PageFlowConfig) cxt.getBean("pageFlowConfig");
		// RequestUtil requestUtil = new RequestUtil();
		// String path = requestUtil.getPath(arg0);
		// ActionBean actionBean = (ActionBean) read.getActionMap().get(path);
		// String fo = actionBean.getForward();
		// this.callURI("/content/dlgsucess.jsp", arg0, arg1);
		this.callURI(arg0.getContextPath() + "/content/dlgsucess.jsp", arg0,
				arg1);
		// VelocityContext context = new VelocityContext();
		// context.put("htmlFile", boService.getTemplFile());
		// context.put("req", arg0);
		// context.put("workItem", arg0.getAttribute("workItem"));
		// context.put("id", id);
		// VelocityUtil velocityUtil = new VelocityUtil(context);
		// arg1.getWriter().print(velocityUtil.code("form.vm"));
	}

	@Test
	public void a() {
		String name = "½­ÄÏ¹ÖÎ¶Ñ¼   &98    -              Æ®ÏãÆ¡¾ÆÊ¯¼¦  &a68"
				+ "-Ã÷Â¯²ÝÓã    &58    -             ÎýÖ½öÔÓã      &a68";

		String a1[] = name.split("-");
		for (Integer i = 0; i < a1.length; i++) {
			String a2[] = a1[i].split("&");
			System.out.println("name:===" + a2[0].trim());
			System.out.println("price:===" + a2[1].trim());
		}
	}
}
