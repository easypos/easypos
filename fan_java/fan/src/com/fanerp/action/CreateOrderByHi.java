package com.fanerp.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class CreateOrderByHi extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		String id = arg0.getParameter("id");
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");		
			List<String> hideVars = new ArrayList<String>();
			CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
			boService.init("fanOrder", hideVars, null);
			boService.setSession((OASession) oasession);
			if (id == null)
				id = "";
			if (id.equals(""))
				id = new Integer(boService.maxId() + 1).toString();
			boService.setParameterValues(RequestUtil.getParamValues(arg0));
			boService.getParameterValues().put("content", "");
			boService.getParameterValues().put("diningtableid", "0");
			boService.getParameterValues().put("clienttype", "2");			
			boService.add(id, "fanOrder");
			// this.setForward(false);
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}
}
