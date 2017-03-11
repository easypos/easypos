package com.ysk.report.action;
import java.sql.SQLException;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.erp.chart.Bar;
import com.erp.chart.Pie;
import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;
public class ReportForDay extends Action {
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
		if (from_.equals("android")) {
			String count = arg0.getParameter("count");
			if (count == null) {
				count = "0";
			}
			if (count.equals("0")) {
				android(arg0, arg1,0,"-");

			} else {
				lastForYMD(arg0, arg1);
			}
			JSONArray jsonArray = JSONArray.fromObject(map);
			arg1.getWriter().print(jsonArray.toString());
			System.out.println(jsonArray.toString());

		} else {
			web(arg0, arg1);
		}
		
	}

	private void web(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		String type = arg0.getParameter("chart");
		if (type == null) {
			type = "1";
		}
		String count = arg0.getParameter("count");
		String data1[][] = reportBuyType(arg0);
		String data2[][] = reportType(arg0);
		String data3[][] = reportBuyOperator(arg0);
		if (count == null)
			count = "10";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (type.equals("1")) {
			//Bar bar = new Bar();
			// response.getWriter().print(bar.bar(a, data));
			//map.put("img", bar.bar(arg0, data1));
			// map.put("img2", bar.bar(arg0, data2));
			map.put("data1", data1);
			map.put("data2", data2);
			map.put("data3", data3);
			JSONArray jsonArray = JSONArray.fromObject(map);
			arg1.getWriter().print(jsonArray.toString());
			// response.getWriter().print();
			// System.out.println(jsonArray.toString());
			// response.getWriter().print(
			// "{\"img\":\n" + pie.pie(a, data) + ",\"data\":\n"
			// + jsonArray + "\n}");
			System.out.println(jsonArray.toString());
			// JSONArray array = new JSONArray(str);
			JSONObject jo = (JSONObject) jsonArray.get(0);
			JSONArray a = (JSONArray) jo.get("data1");
			for (int i = 0; i < a.size(); i++) {
				JSONArray jai = (JSONArray) a.get(i);
				// Map map = a.get(i);
				// System.out.println(a.get(i));
				System.out.println(jai.get(0));
				System.out.println(jai.get(1));
			}

		} else {
			//Pie pie = new Pie();
			//map.put("img", pie.pie(arg0, data1));
			// map.put("img2", pie.pie(arg0, data2));
			map.put("data1", data1);
			map.put("data2", data2);
			map.put("data3", data3);
			JSONArray jsonArray = JSONArray.fromObject(map);
			arg1.getWriter().print(jsonArray.toString());
		}

	}

	HashMap<String, Object> map = new HashMap<String, Object>();

	private void android(HttpServletRequest arg0, HttpServletResponse arg1,int count,String key)
			throws Exception {
		String type = arg0.getParameter("type");
		if (type == null) {
			type = "1";
		}
		// String count = arg0.getParameter("count");
		String data[][] = null;
		if (type.equals("1")) {
			data = reportBuyType(arg0);
		} else if (type.equals("2")) {
			data = reportType(arg0);
		} else if (type.equals("3")) {
			data = reportBuyOperator(arg0);
		} else if (type.equals("4")) {
			data = top10("10");
		}
		// response.getWriter().print(bar.bar(a, data));
		// map.put("img", bar.bar(arg0, data1));
		// map.put("img2", bar.bar(arg0, data2));
		map.put("data" + count, data);
		//map.put("key" + count, data);
		JSONArray jsonArray = JSONArray.fromObject(map);
		// arg1.getWriter().print(jsonArray.toString());
		// response.getWriter().print();
		// System.out.println(jsonArray.toString());
		// response.getWriter().print(
		// "{\"img\":\n" + pie.pie(a, data) + ",\"data\":\n"
		// + jsonArray + "\n}");
		System.out.println("==========================================="+count);
		//System.out.println(jsonArray.toString());
		// JSONArray array = new JSONArray(str);
		JSONObject jo = (JSONObject) jsonArray.get(0);
		JSONArray a = (JSONArray) jo.get("data"+count);
		for (int i = 0; i < a.size(); i++) {
			JSONArray jai = (JSONArray) a.get(i);
			// Map map = a.get(i);
			// System.out.println(a.get(i));
			//System.out.println(jai.get(0));
			//System.out.println(jai.get(1));
		}

	}

	public String[][] reportType(HttpServletRequest arg) throws SQLException {
		 ApplicationContext cxt = AppSession.getApplicationContext();

		//ApplicationContext cxt = new ClassPathXmlApplicationContext(
		//		"ApplicationContext.xml");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select universalid,wordvalue,sum1 from  T_INFORPUBDICT as list1  join"
				+ " (select sum(a1.price*dishcount) as sum1,type from fanorderitem a1 join fanorder a2 on a1.pid=a2.universalid join fandish a3   on  a1.dishid =a3.universalid     where createddate>'"
				+ begin_
				+ "' and  createddate<'"
				+ end_
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
		 ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select * from T_FORM_ITEM_DICT as list1   left join"
				+ " (select buytype,sum(money-change) as sum1    from fanorderbuy where createdate>'"
				+ begin_ + "' and  createdate<'" + end_
				+ "'  group by  buytype) as list2"
				+ " on list1.itemcode= list2.buytype   where itemid='3553'";
		Logger.getLogger(this.getClass().getName()).info(sql);
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

	public String[][] reportBuyOperator(HttpServletRequest arg)
			throws SQLException {
		 ApplicationContext cxt = AppSession.getApplicationContext();
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
				+ "'  group by  operator";
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
			a[i][0] = (String) map.get("operator");
			a[i][1] = ((Integer) map.get("sum1")).toString();
		}
		return a;
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
		top10("10");
	}

	public String[][] top10(String count) {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		// String sql = "select top 10"
		// +
		// "  sum(dishcount) as sumdishcount,sum(price) as sumprice,name from (select type ,dishcount,a.price,NAME  from fanorderitem  as a join fandish  as b on a.dishid=b.universalid where 0=0 ) d group by name order by sumprice desc";

		String sql = "select top 10 sum(a1.price*dishcount) as sum1,name "
				+ "from fanorderitem a1 join fanorder a2  on a1.pid=a2.universalid join fandish a3   on  a1.dishid =a3.universalid"
				+ " where createddate>'" + begin_ + "' and  createddate <'"
				+ end_ + "'group by name   order by sum1 desc";
		List result = commonDao.queryForList(sql);
		String a[][] = new String[result.size()][2];
		for (int i = 0; i < result.size(); i++) {
			Map map = (Map) result.get(i);
			a[i][0] = (String) map.get("name");
			a[i][1] = ((Integer) map.get("sum1")).toString();
			System.out.print(a[i][1]);
		}
		return a;
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
		Logger.getLogger(this.getClass().getName()).info("begin_"+begin_);
		Logger.getLogger(this.getClass().getName()).info("end_"+end_);
		//System.out.println(begin_);
		//System.out.println(end_);		
		if (begin_ == null) {
			begin_ = yy + "-" + mm + "-" + dd;
			end_ = begin_ + " " + "23:00";			
			System.out.println(begin_);
			System.out.println(end_);
		}
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
				//System.out.println("================================================================");
				//System.out.println(begin_);
				//System.out.println(end_);
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
				int yy_=yy+1;
				end_ = yy_ + "-1-1"+" 00:00:00" ;
				cal.set(GregorianCalendar.YEAR, yy + 1);
			}			
			System.out.println("================================================================");
			System.out.println(begin_);
			System.out.println(end_);
			this.android(arg0, arg1,i,begin_);
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
