package com.fanerp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;

public class ModifyUserInfo extends Action {
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
		if (oasession == null) {
			oasession = new OASession();
			oasession.setLoginID(0);
			oasession.setUserName("guest");
		}		
		boService.setSession((OASession) oasession);
		boService.setParameterValues(RequestUtil.getParamValues(arg0));		
		boService.init("user", null, null);
		id = new Integer(boService.maxId() + 1).toString();
		boService.add(id,"user");
		
		
		//boService.getMainBeans().clear();
		
		boService.init(fid, null, null);							
		boService.add(id, fid);
		// strBuf.append("提交成功!");
		// arg1.getWriter().print(strBuf.toString());
		this.setForward(true);		
		// PageFlowConfig read = (PageFlowConfig) cxt.getBean("pageFlowConfig");
		// RequestUtil requestUtil = new RequestUtil();
		// String path = requestUtil.getPath(arg0);
		// ActionBean actionBean = (ActionBean) read.getActionMap().get(path);
		// String fo = actionBean.getForward();
		this.callURI("li-dish-buy-online.do?v=dish", arg0, arg1);
		System.out.println("====================");
		// this.callURI(arg0.getContextPath()+"/content/dlgsucess.jsp", arg0,
		// arg1);
		// VelocityContext context = new VelocityContext();
		// context.put("htmlFile", boService.getTemplFile());
		// context.put("req", arg0);
		// context.put("workItem", arg0.getAttribute("workItem"));
		// context.put("id", id);
		// VelocityUtil velocityUtil = new VelocityUtil(context);
		// arg1.getWriter().print(velocityUtil.code("form.vm"));
	}
}
