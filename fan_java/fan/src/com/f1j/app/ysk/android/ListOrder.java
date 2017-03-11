package com.f1j.app.ysk.android;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class ListOrder extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String status = arg0.getParameter("status");
		if (status == null) {
			status = "1";
		}
		String a = jsonData(status);
		arg1.getWriter().print(a);
		//System.out.println("hi");
		//System.out.println(a);
	}

	@Test
	public void hi() {
		try {
			jsonData("2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(a);
	}

	public String jsonData(String status) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String currentDate = getCurrentDate();
		String begin = "'" + currentDate + " " + "00:01:00" + "'";
		String end = "'" + currentDate + " " + "23:59:00" + "'";
		// List types = d
		// .queryForList("select universalid,ordernu,diningtableid,usercount,DATEPART(hh,createddate) as hh,DATEPART(mi,createddate) as mi,DATEPART(ss,createddate) as ss from fanorder   "
		// + ""
		// + ""
		// +
		// "where DAY(createddate)=DAY(GETDATE()) and  Month(createddate)=Month(GETDATE())  and year(createddate)=year(GETDATE())  ");
		String sql = "select universalid,ordernu,diningtableid,usercount,createddate from fanorder a  "
				+ "where  a.companyid='"
				+ this.oasession.getCompanyCode()
				+ "' and  a.createddate >"
				+ begin
				+ " and a.createddate<"
				+ end
				+ " and a.status='"
				+ status
				+ "'"
				+ " order by a.createddate desc";
		List types = d.queryForList(sql);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		for (int j = 0; j < types.size(); j++) {
			Map map = (Map) types.get(j);
			String createddate;
			try {
				java.sql.Timestamp createddate_ = (java.sql.Timestamp) map
						.get("createddate");
				Date date = new Date(createddate_.getTime());
				createddate = df.format(date);
			} catch (Exception ex) {
				createddate = "";
			}
			map.put("createddate", createddate);
		}
		JSONArray jsonArray = JSONArray.fromObject(types);
		//System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
}
