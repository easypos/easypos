package com.ysk.report.action;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.erp.chart.Bar;
import com.erp.chart.Pie;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class Report extends Action {
	public void afterExcute(HttpServletRequest a, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String data[][] = a(a);
		String type = a.getParameter("chart");
		if (type.equals("1")) {
			Bar bar = new Bar();
			response.getWriter().print(bar.bar(a, data));
		} else {
			Pie pie = new Pie();
			response.getWriter().print(pie.pie(a, data));
		}
	}

	public String[][] a(HttpServletRequest request) {
		String type = request.getParameter("type");
		String a = request.getParameter("begin_");
		Integer count = new Integer(request.getParameter("count"));
		a.indexOf("-");
		a.lastIndexOf("-");
		String a1 = a.substring(0, a.indexOf("-"));
		String a2 = a.substring(a.indexOf("-") + 1, a.lastIndexOf("-"));
		String a3 = a.substring(a.lastIndexOf("-") + 1, a.length());
		String list1[][] = new String[count][2];
		// ApplicationContext cxt = new ClassPathXmlApplicationContext(
		// "ApplicationContext.xml");
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(new Integer(a1), new Integer(a2) - 1, new Integer(a3));
		int yy, mm, dd;
		String sql;
		for (Integer i = 0; i < count; i++) {
			yy = cal.get(GregorianCalendar.YEAR);
			mm = cal.get(GregorianCalendar.MONTH);
			dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
			String begin;
			String end;
			if (type.equals("1")) {
				begin = yy + "-" + new Integer(mm + 1) + "-" + dd + " 00:00:00";
				end = yy + "-" + new Integer(mm + 1) + "-" + dd + " 23:00:00";

				cal.set(GregorianCalendar.DAY_OF_MONTH, dd + 1);
				list1[i][0] = yy + "-" + new Integer(mm + 1) + "-" + dd;
			} else if (type.equals("2")) {
				begin = yy + "-" + new Integer(mm + 1) + "-" + dd + " 00:00:00";
				Integer maxDay = cal
						.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
				end = yy + "-" + new Integer(mm + 1) + "-" + maxDay
						+ " 23:00:00";
				cal.set(GregorianCalendar.MONTH, mm + 1);
				list1[i][0] = yy + "-" + new Integer(mm + 1) + "-" + dd;
			} else {
				begin = yy + "-" + new Integer(1) + "-" + 1 + " 00:00:00";
				Integer maxDay = cal
						.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
				end = yy + "-" + 12 + "-" + maxDay + " 23:00:00";

				cal.set(GregorianCalendar.YEAR, yy + 1);
				list1[i][0] = new Integer(yy).toString();
			}
			sql = "select sum(money) from  FANORDERBUY where  CREATEDATE  >'"
					+ begin + "' and  CREATEDATE  <'" + end + "'";
			Integer r = commonDao.queryForInt(sql);
			list1[i][1] = r.toString();
			System.out.println(yy + "-" + mm + "-" + dd + "====="
					+ r.toString());
		}
		return list1;
	}

	public String[][] a_() {
		// String[][] list1 = { { "2008", "12.0" }, { "2009", "6.0" },
		// { "2010", "15.0" }, { "2011", "16.0" }, { "2012", "17.0" },
		// { "2013", "100.0" } };

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		Integer yy = cal.get(GregorianCalendar.YEAR);
		String list1[][] = new String[12][2];
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		for (Integer i = 1; i < 13; i++) {
			cal.set(yy, i - 1, 2);
			Integer maxDay = cal
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			String begin = yy + "-" + i + "-1" + " 00:00:00";
			String end = yy + "-" + i + "-" + maxDay + " 00:00:00";
			String sql = "select sum(money) from  FANORDERBUY where  CREATEDATE  >'"
					+ begin + "' and  CREATEDATE  <'" + end + "'";
			Integer a = commonDao.queryForInt(sql);
			list1[i - 1][0] = i.toString();
			list1[i - 1][1] = a.toString();
		}
		return list1;
	}

}
