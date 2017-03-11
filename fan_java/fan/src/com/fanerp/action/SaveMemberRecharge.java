package com.fanerp.action;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jeeframework.workflow.impl.BpmIDGenerator;
import com.f1jframework.eform.CommonboService;
import com.fanerp.member.MemberDao;

public class SaveMemberRecharge extends Action {
	@SuppressWarnings("unchecked")
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		// StringBuffer strBuf = new StringBuffer();
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		// CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		// String name = arg0.getParameter("name");
		String tel = arg0.getParameter("tel");
		String recharge = arg0.getParameter("recharge");
		// String sql = "select count(*)  from fanmember where  tel='" + tel +
		// "'";
		// System.out.println(sql);
		MemberDao memberDao = new MemberDao();
		int a = memberDao.decideMember(tel);
		@SuppressWarnings("rawtypes")
		HashMap result = new HashMap();
		if (a == 0) {
			String fid = "fanmember";
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
			// boService.getParameterValues().put("companyid",
			// oasession.getCompanyCode());
			HashMap base = boService.add(id, fid);
			this.setForward(false);
			result.put("name", "-");
			result.put("address", "-");
			result.put("type", "0");
			result.put("overage", "0.00");
			result.put("err", "ok");
			LazyDynaBean main = (LazyDynaBean) base.get("main");
			result.put("created", main.get("created"));
			result.put("lastdate", main.get("created"));
		} else {
			LazyDynaBean fanMember = memberDao.loadMember(tel);
			DecimalFormat df = new DecimalFormat("0.00");
			Float sum = (float) 0.00;
			String recharge_ = (String) fanMember.get("recharge");
			String name = (String) fanMember.get("name");
			String address = (String) fanMember.get("address");
			String created = (String) fanMember.get("created");
			sum = Float.valueOf(recharge_) + Float.valueOf(recharge);
			memberDao.add(tel, sum);
			result.put("name", name);
			result.put("address", address);
			result.put("type", "1");
			result.put("overage", df.format(sum));
			result.put("err", "ok");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result.put("created", sdf.format(sdf.parse(created)));
			String toDay = this.getCurrentDate() + " " + this.getCurrentTime();
			result.put("lastdate", sdf.format(sdf.parse(toDay)));
		}
		JSONArray jsonArray = JSONArray.fromObject(result);
		arg1.getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
	}

}
