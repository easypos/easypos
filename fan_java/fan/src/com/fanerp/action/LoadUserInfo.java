package com.fanerp.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.ActionBean;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.PageFlowConfig;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;
import com.f1jframework.eform.common.VelocityUtil;

public class LoadUserInfo extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GBK");
		ApplicationContext cxt = AppSession.getApplicationContext();
		ServletContext servletContext = arg0.getSession().getServletContext();
		String contextPath = servletContext
				.getInitParameter("f1jeeContextPath");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		// String fid;
		// String id = oasession.getLoginID().toString();
		if (oasession == null) {
			oasession = new OASession();
			oasession.setLoginID(0);
			oasession.setUserName("guest");
		}		
		
		PageFlowConfig read = (PageFlowConfig) cxt.getBean("pageFlowConfig");
		RequestUtil requestUtil = new RequestUtil();
		String path = requestUtil.getPath(arg0);
		ActionBean actionBean = (ActionBean) read.getActionMap().get(path);
		String fid = actionBean.getForward();
		boService.init(fid, null, null);
		boService.setSession((OASession) oasession);
		VelocityContext context = new VelocityContext();
		// HashMap<String,?> Varables=new HashMap();
		// arg0.getp
		boService.setId(null);

		if (boService.loadInterceptor()) {
			boService.load(null, arg0, context, fid);
			context.put("htmlFile", "/eform/"
					+ this.getActionBean().getParam1());
			context.put("fid", fid);
			context.put("id", "");
			context.put("req", arg0);
			context.put("USER", "");
			context.put("PASSWORD", "");
			// context.put("workItem", arg0.getAttribute("workItem"));
			VelocityUtil velocityUtil = new VelocityUtil(context);
			arg1.getWriter().print(
					velocityUtil.code("/eform/form-user.vm", contextPath));
		} else {
			arg0.setAttribute("actionForward_", null);
			this.callURI("/msg/msgorder.jsp", arg0, arg1);
		}
	}
}
