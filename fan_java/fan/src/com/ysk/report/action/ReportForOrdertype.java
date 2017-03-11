package com.ysk.report.action;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.erp.chart.Bar;
import com.erp.chart.Pie;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class ReportForOrdertype extends Action {
	public void afterExcute(HttpServletRequest a, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/json; charset=utf-8");
		
		String type = a.getParameter("chart");
		String count = a.getParameter("count");
		String data[][] = a(a,count);
		if (count==null) count="10";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (type.equals("1")) {
			Bar bar = new Bar();
			// response.getWriter().print(bar.bar(a, data));
			map.put("img", bar.bar(a, data));
			map.put("data", data);
			JSONArray jsonArray = JSONArray.fromObject(map);
			response.getWriter().print(jsonArray.toString());
			//response.getWriter().print();
			System.out.println(jsonArray.toString());
			//response.getWriter().print(
			//		"{\"img\":\n" + pie.pie(a, data) + ",\"data\":\n"
			//				+ jsonArray + "\n}");
		} else {
			Pie pie = new Pie();
			map.put("img", pie.pie(a, data));
			map.put("data", data);
			JSONArray jsonArray = JSONArray.fromObject(map);
			response.getWriter().print(jsonArray.toString());
		}

	}

	public String[][] a(HttpServletRequest arg,String count) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		Integer yy = cal.get(GregorianCalendar.YEAR);
		String list1[][] = new String[12][2];
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String sql = "select top "+count+"  sum(dishcount) as sumdishcount,sum(price) as sumprice,name from (select type ,dishcount,a.price,NAME  from fanorderitem  as a join fandish  as b on a.dishid=b.universalid where 0=0 ) d group by name order by sumprice desc";
		List result = commonDao.queryForList(sql);
		String a[][] = new String[result.size()][2];
		for (int i = 0; i < result.size(); i++) {
			Map map = (Map) result.get(i);
			a[i][0] = (String) map.get("name");
			a[i][1] = ((Integer) map.get("sumprice")).toString();
		}
		return a;
	}

	@Test
	public void test1() {
		String[][] list1 = { { "2008", "12.0" }, { "2009", "6.0" },
				{ "2010", "15.0" }, { "2011", "16.0" }, { "2012", "17.0" },
				{ "2013", "100.0" } };
		JSONArray jsonArray = JSONArray.fromObject(list1);
		System.out.println(jsonArray.toString());
	}

}
