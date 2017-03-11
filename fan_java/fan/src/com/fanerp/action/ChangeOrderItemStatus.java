package com.fanerp.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;

public class ChangeOrderItemStatus extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		String id = arg0.getParameter("id");
		String orderid = arg0.getParameter("orderid");
		String status = arg0.getParameter("status");
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			boService.update("update fanorderitem set status='" + status
					+ "' where universalid='" + id + "'");
			// this.setForward(false);
			boService.init("fanorderlog", null, null);
			boService.setSession((OASession) oasession);
			HashMap<String, Object> a = new HashMap<String, Object>();
			LazyDynaBean o = boService.getDao().queryForObject(
					"select dishid from fanorderitem" + " where universalid='"
							+ id + "'");
			a.put("ordernu", orderid);
			a.put("dishid", o.get("dishid"));
			a.put("operator", oasession.getUserName());
			a.put("created", "");
			a.put("note", "-");
			a.put("type", status);
			boService.setParameterValues(a);
			boService.add(
					com.f1jeeframework.workflow.impl.BpmIDGenerator.getID(),
					"fanorderlog");
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}
}
