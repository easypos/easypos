package com.ysk.report.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.erp.chart.Bar;
import com.erp.chart.Pie;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class ReportForOperator extends Action {

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/json; charset=utf-8");
		String type = arg0.getParameter("chart");
		String count = arg0.getParameter("count");
		String data1[][] = reportBuyType(arg0);
		String data2[][] = reportType(arg0);
		if (count == null)
			count = "10";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (type.equals("1")) {
			Bar bar = new Bar();
			// response.getWriter().print(bar.bar(a, data));
			map.put("img", bar.bar(arg0, data1));
			// map.put("img2", bar.bar(arg0, data2));
			map.put("data1", data1);
			map.put("data2", data2);
			JSONArray jsonArray = JSONArray.fromObject(map);
			arg1.getWriter().print(jsonArray.toString());
			// response.getWriter().print();
			// System.out.println(jsonArray.toString());
			// response.getWriter().print(
			// "{\"img\":\n" + pie.pie(a, data) + ",\"data\":\n"
			// + jsonArray + "\n}");
		} else {
			Pie pie = new Pie();
			map.put("img", pie.pie(arg0, data1));
			// map.put("img2", pie.pie(arg0, data2));
			map.put("data1", data1);
			map.put("data2", data2);
			JSONArray jsonArray = JSONArray.fromObject(map);
			arg1.getWriter().print(jsonArray.toString());
		}
	}

	public String[][] reportType(HttpServletRequest arg) throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		String begin_ = arg.getParameter("begin_");
		String end_ = arg.getParameter("end_");
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select * from  T_INFORPUBDICT as list1 left join"
				+ " (select sum(a1.price*dishcount) as sum1,type from fanorderitem a1 join fanorder a2 on a1.pid=a2.universalid join fandish a3   on  a1.dishid =a3.universalid     where createddate>'"
				+ begin_ + "' and  createddate<'" + end_
				+ "'  group by type) as list2"
				+ " on list1.universalid= list2.type where wordname='²ËÆ·Àà±ð' ";
		List list = d.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			// String sum1;
			if (map.get("sum1") == null) {
				map.put("sum1", 0);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String a[][] = new String[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			a[i][0] = (String) map.get("wordvalue");
			a[i][1] = ((Integer) map.get("sum1")).toString();
		}
		return a;

	}

	public String[][] reportBuyType(HttpServletRequest arg) throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		String begin_ = arg.getParameter("begin_");
		String end_ = arg.getParameter("end_");
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql = "select * from T_FORM_ITEM_DICT as list1   left join"
		// +
		// " (select buytype,sum(money-change) as sum1    from fanorderbuy where createdate>'"
		// + begin_ + "' and  createdate<'" + end_
		// + "'  group by  buytype) as list2"
		// + " on list1.itemcode= list2.buytype   where itemid='3553'";
		String sql = "select operator,sum(money-change) as sum1    from fanorderbuy"
				+ " where createdate>'"
				+ begin_
				+ "' and  createdate<'"
				+ end_
				+ "'  group by  operator)";
		List list = d.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			// String sum1;
			if (map.get("sum1") == null) {
				map.put("sum1", 0);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		String a[][] = new String[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			a[i][0] = (String) map.get("itemvalue");
			a[i][1] = ((Integer) map.get("sum1")).toString();
		}
		return a;
	}
}
