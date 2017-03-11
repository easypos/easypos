package com.f1j.app.ysk.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class ListOrderItemForCook extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = jsonData();
		arg1.getWriter().print(a);
		System.out.println(a);
	}

	public String jsonData() {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String id = "40";
		String currentDate = getCurrentDate();
		String begin = "'" + currentDate + " " + "08:00:00" + "'";
		String end = "'" + currentDate + " " + "23:00:00" + "'";
		String sql = "select a.universalid,a1.ordernu, a1.operator, a1.createddate, a1.diningtableid, a1.status, a1.content, a1.member, a1.clienttype,"
				+ "a1.tel, a1.address, a1.usercount,a.dishcount,a14.img, a14.name, a14.price, a14.type, a14.status, a.status,a.price  from FANORDERITEM a  left join "
				+ "FANORDER a1 on a.pid=a1.universalid left join FanDish a14 on a.dishid=a14.universalid  where  a1.createddate >"
				+ begin + " and a1.createddate<" + end;
		List dishes = d.queryForList(sql);
		System.out.println(sql);
		// System.out.println(jsonArray.toString());
		//ArrayList<HashMap> list = new ArrayList<HashMap>();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		for (int j = 0; j < dishes.size(); j++) {
			Map map = (Map) dishes.get(j);
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
		JSONArray jsonArray = JSONArray.fromObject(dishes);
		return jsonArray.toString();
	}

	@Test
	public void test() {
		//System.out.println(this.getCurrentDate());
		//String a = jsonData();
		//System.out.println(a);
		SimpleDateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df4.format(new Date()));

	}

}
