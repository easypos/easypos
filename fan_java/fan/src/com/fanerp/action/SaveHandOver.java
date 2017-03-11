package com.fanerp.action;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jeeframework.workflow.impl.BpmIDGenerator;
import com.f1jframework.eform.CommonboService;

public class SaveHandOver extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		// StringBuffer strBuf = new StringBuffer();
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		this.setForward(false);
		// CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int yy = cal.get(GregorianCalendar.YEAR);
		int mm = cal.get(GregorianCalendar.MONTH);
		mm = mm + 1;
		int dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
		String begin_ = yy + "-" + mm + "-" + dd;
		String end_ = begin_ + " " + "23:00";
		String type = arg0.getParameter("type");
		String handoverto = arg0.getParameter("handoverto");
		String sql = "select count(*) from t_user where user_name='"
				+ handoverto + "'";
		int result = boService.getDao().queryForInt(sql);
		if (result == 0) {
			arg1.getWriter().print("交接工号不存在!");
			return;
		}
		sql = "select count(*)   from fanhandover" + " where end_>'" + begin_
				+ "' and  end_<'" + end_ + "' and operater=" + "'"
				+ oasession.getUserName() + "' and type='" + type + "'";
		System.out.println(sql);
		result = boService.getDao().queryForInt(sql);
		if (result == 0) {
			String fid = "fanhandover";
			String action = arg0.getParameter("action");
			boService.init(fid, null, null);
			String keyType = boService.getFormBean().getKeyType();
			if (keyType == null)
				keyType = "";
			if (keyType.equals("null"))
				keyType = "";
			// if boService.getFormBean().getKeyType()
			boService.setSession((OASession) oasession);
			String id = BpmIDGenerator.getID();
			boService.setParameterValues(RequestUtil
					.getParamValuesByUtf_8(arg0));
			boService.getParameterValues().put("companyid",
					oasession.getCompanyCode());
			boService.add(id, fid);
			this.setForward(false);
			arg1.getWriter().print("ok");
		} else {
			arg1.getWriter().print("重复交接或签到!");
		}
	}
}
