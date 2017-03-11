package com.fanerp.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jframework.eform.CommonboService;

public class AppendOrderByAndroid extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		String id = arg0.getParameter("id");
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			boService.setAppend(true);
			List<String> hideVars = new ArrayList<String>();
			hideVars.add("ordernu");
			hideVars.add("operator");
			hideVars.add("createddate");
			hideVars.add("diningtableid");
			hideVars.add("content");
			boService.init("fanOrder", hideVars, null);
			boService.setParameterValues(RequestUtil.getParamValues(arg0));
			int count = new Integer((String) boService.getParameterValues()
					.get("Count"));
			for (int i = 1; i <= count; i++) {
				boService.getParameterValues().put("status" + i, "0");
				System.out.print("count===========");
			}
			boService.add(id,  "fanOrder");
			this.setForward(false);
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			arg1.getWriter().println("error!");
		}
	}
}
