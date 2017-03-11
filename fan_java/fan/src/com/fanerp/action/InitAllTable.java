package com.fanerp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonboService;

public class InitAllTable extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");		
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			boService.update("update FANDININGTABLE set status='0'");
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}
}
