package com.fanerp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonboService;

public class SaveTableStatus extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		String id = arg0.getParameter("id");
		String status = arg0.getParameter("status");
		String orderId = arg0.getParameter("orderId");
		String tableNu = arg0.getParameter("tableNu");
		String targetNu = arg0.getParameter("targetNu");
		if (tableNu == null)
			tableNu = "";
		try {
			System.out.println("id," + id);
			System.out.println("status," + status);
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			if (tableNu.equals("")) {
				boService.update("update FANDININGTABLE set status='" + status
						+ "' where UNIVERSALID='" + id + "'");
			} else {
				boService.update("update FANDININGTABLE set status='" + "0"
						+ "' where tablenu='" + tableNu + "'");
				boService.update("update FANDININGTABLE set status='" + "2"
						+ "' where tablenu='" + targetNu + "'");
				boService.update("update fanorder set diningtableid='"
						+ targetNu + "' where UNIVERSALID='" + orderId + "'");
			}
			System.out.print("=============================" + orderId);
			// this.setForward(false);
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}
	
	@Test
	public void a() {
		for (int i=0;i<100;i++){
		System.out.println(i%2);		
		}
	}
}
