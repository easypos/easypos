package com.ysk.report.action;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class ReportForBuyType extends Action {
	String begin_ = null;
	String end_ = null;

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/json; charset=utf-8");
		initDateParam(arg0);
		String from_ = arg0.getParameter("from_");
		if (from_ == null) {
			from_ = "";
		}
		web(arg0, arg1);
	}

	private void web(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		reportBuyType(arg0);
		this.callURI("a", arg0, arg1);
	}

	HashMap<String, Object> map = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public void reportBuyType(HttpServletRequest arg) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String type_sql = "select universalid ,name from fancompany where status='1'";
		if (oasession.getCompanyCode() != null) {
			type_sql = type_sql + " and universalid=" + "'"
					+ oasession.getCompanyCode() + "'";
		}
		List types = d.queryForList(type_sql);
		HashMap<String, List<?>> list_ = new HashMap<String, List<?>>();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i);
			String name = (String) map.get("name");
			String id = (String) map.get("universalid");
			String sql = "select * from T_FORM_ITEM_DICT as list1   left join"
					+ " (select buytype,sum(CONVERT(float,money)-CONVERT(float,change)) as sum1    from fanorderbuy where createdate>'"
					+ begin_ + "' and  createdate<'" + end_
					+ "' and companyid='" + id
					+ "'  group by  buytype) as list2"
					+ " on list1.itemcode= list2.buytype   where itemid='3553'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			String a = "select a.money from FANORDERBUYTYPE a  "
					+ "join  FANORDERBUY  a1 on  a.pid=a1.universalid  "
					+ "join  FANORDERBUYTYPEset  a2 on a.buytypeid=a2.universalid group by a2.buytype";
			List list = d.queryForList(sql);
			Double sum = (Double) 0.00;
			for (int j = 0; j < list.size(); j++) {
				Map type_ = (Map) list.get(j);
				Object sum1 = (Object) type_.get("sum1");
				if (sum1 == null)
					sum1 = "0.00";
				else {
					sum = sum + (Double) type_.get("sum1");
					type_.put("sum1", df.format((Double) type_.get("sum1")));
				}
			}
			sql = "select a2.buytype as itemvalue, sum(CONVERT(float,a.money)) as sum1  from FANORDERBUYTYPE a"
					+ "  join  FANORDER  a1 on  a.pid=a1.universalid   join  FANORDERBUYTYPEset  a2 "
					+ "on a.buytypeid=a2.universalid  where a1.COMPANYID='"
					+ id + "' group by a2.buytype";

			sql = "select a2.buytype as itemvalue, sum(CONVERT(float,a.money)) as sum1  from FANORDERBUYTYPE a"
					+ "  join  FANORDER  a1 on  a.pid=a1.universalid   join  FANORDERBUYTYPEset  a2 "
					+ "on a.buytypeid=a2.universalid  "
					+ " where a1.createddate>'"
					+ begin_
					+ "' and  a1.createddate<'"
					+ end_
					+ "' and a1.COMPANYID='"
					+ id + "' group by a2.buytype";
			List listOther = d.queryForList(sql);
			for (int j = 0; j < listOther.size(); j++) {
				Map type_ = (Map) listOther.get(j);
				list.add(type_);
				Object sum1 = (Object) type_.get("sum1");
				if (sum1 == null)
					sum1 = "0.00";
				else {
					sum = sum + (Double) type_.get("sum1");
					type_.put("sum1", df.format((Double) type_.get("sum1")));
				}
			}
			Map sum_ = new HashMap();
			sum_.put("itemvalue", "µ¥µêºÏ¼Æ");
			sum_.put("sum1", df.format(sum));
			list.add(sum_);
			Logger.getLogger(this.getClass().getName()).info(sql);
			list_.put(name, list);
		}
		arg.setAttribute("types", list_);
	}

	@Test
	public void testTop10() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int yy = cal.get(GregorianCalendar.YEAR);
		int mm = cal.get(GregorianCalendar.MONTH);
		mm = mm + 1;
		int dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
		System.out.println(begin_);
		System.out.println(end_);
		if (begin_ == null) {
			begin_ = yy + "-" + mm + "-" + dd;
			end_ = begin_ + " " + "23:00";
			System.out.println(begin_);
			System.out.println(end_);
		}
		// top10("10");
	}

	private void initDateParam(HttpServletRequest arg) {
		begin_ = arg.getParameter("begin_");
		end_ = arg.getParameter("end_");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int yy = cal.get(GregorianCalendar.YEAR);
		int mm = cal.get(GregorianCalendar.MONTH);
		mm = mm + 1;
		int dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
		Logger.getLogger(this.getClass().getName()).info("begin_" + begin_);
		Logger.getLogger(this.getClass().getName()).info("end_" + end_);
		// System.out.println(begin_);
		// System.out.println(end_);
		if (begin_ == null) {
			begin_ = yy + "-" + mm + "-" + dd;
			end_ = begin_ + " " + "23:00";
			System.out.println(begin_);
			System.out.println(end_);
		}
		arg.setAttribute("begin_", begin_);
		arg.setAttribute("end_", end_);

	}

	private void lastForYMD(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		String a = arg0.getParameter("begin_");
		Integer count = new Integer(arg0.getParameter("count"));
		String type = arg0.getParameter("ymd");
		if (type == null)
			type = "1";
		a.indexOf("-");
		a.lastIndexOf("-");
		GregorianCalendar cal = new GregorianCalendar();
		String a1 = a.substring(0, a.indexOf("-"));
		String a2 = a.substring(a.indexOf("-") + 1, a.lastIndexOf("-"));
		String a3 = a.substring(a.lastIndexOf("-") + 1, a.length());
		cal.set(new Integer(a1), new Integer(a2) - 1, new Integer(a3));
		int yy, mm, dd;
		for (Integer i = 0; i < count; i++) {
			yy = cal.get(GregorianCalendar.YEAR);
			mm = cal.get(GregorianCalendar.MONTH);
			dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
			if (type.equals("3")) {
				begin_ = yy + "-" + new Integer(mm + 1) + "-" + dd
						+ " 00:00:00";
				end_ = yy + "-" + new Integer(mm + 1) + "-" + dd + " 23:00:00";
				cal.set(GregorianCalendar.DAY_OF_MONTH, dd + 1);
				// System.out.println("================================================================");
				// System.out.println(begin_);
				// System.out.println(end_);
			} else if (type.equals("2")) {
				begin_ = yy + "-" + new Integer(mm + 1) + "-" + "1"
						+ " 00:00:00";
				Integer maxDay = cal
						.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
				end_ = yy + "-" + new Integer(mm + 1) + "-" + maxDay
						+ " 23:00:00";
				cal.set(GregorianCalendar.MONTH, mm + 1);

			} else {
				begin_ = yy + "-" + new Integer(1) + "-" + 1 + " 00:00:00";
				int yy_ = yy + 1;
				end_ = yy_ + "-1-1" + " 00:00:00";
				cal.set(GregorianCalendar.YEAR, yy + 1);
			}
			System.out
					.println("================================================================");
			System.out.println(begin_);
			System.out.println(end_);
			// this.android(arg0, arg1,i,begin_);
		}
	}

	@Test
	public void randomTest() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(r.nextInt(256));
		}
	}

}
