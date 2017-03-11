package com.f1j.app.ysk.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class ListDishTable extends Action {
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
		ArrayList<HashMap> types_ = new ArrayList<HashMap>();
		List types = d
				.queryForList("select universalid ,wordvalue,count1 from T_INFORPUBDICT   a join(select count(*) count1,type from  FANDININGTABLE group by type) as b on a.universalid=b.type");
		for (int i = 0; i < types.size(); i++) {
			Map type = (Map) types.get(i);
			Integer typeId = (Integer) type.get("universalid");
			Integer count1 = (Integer) type.get("count1");
			String typeName = (String) type.get("wordvalue");
			// List dishes = d.queryForList("select a.universalid,"
			// + "a.tablenu,a.status from  FANDININGTABLE a"
			// + " where a.type='" + typeId + "'");
			String a = "select  a.universalid,tablenu ,a.seatnu,a.status,b.createddate,b.usercount from FANDININGTABLE a"
					+ " left join (select  * from   fanorder  where DAY(createddate)=DAY(GETDATE()) and "
					+ " Month(createddate)=Month(GETDATE())"
					+ " and year(createddate)=year(GETDATE())"
					+ " and status='1') b on a.tablenu=b.DININGTABLEID"
					+ " where   a.type='" + typeId + "'";

			List dishes = d.queryForList(a);
			ArrayList<HashMap> childs = new ArrayList<HashMap>();
			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
			for (int j = 0; j < dishes.size(); j++) {
				Map map = (Map) dishes.get(j);
				Integer id = (Integer) map.get("universalid");
				Integer tabNu = (Integer) map.get("tablenu");
				Integer seatNu = (Integer) map.get("seatnu");

				Integer userCount = (Integer) map.get("usercount");
				String status = (String) map.get("status");
				String createddate;
				try {
					java.sql.Timestamp createddate_ = (java.sql.Timestamp) map
							.get("createddate");
					createddate = createddate_.getHours() + "-"
							+ createddate_.getMinutes() + "-"
							+ createddate_.getSeconds();
				} catch (Exception ex) {
					createddate = "";
				}
				HashMap map_ = new HashMap();
				map_.put("id", id);
				map_.put("tabNu", tabNu);
				map_.put("seatNu", seatNu);
				if (userCount == null) {
					map_.put("userCount", 0);
				} else {
					map_.put("userCount", userCount);
				}
				map_.put("status", status);
				map_.put("createddate", createddate);

				childs.add((HashMap) map_);
			}
			HashMap typeHashMap = new HashMap();
			typeHashMap.put("type", typeName + "(" + count1 + ")");
			typeHashMap.put("child", childs);
			types_.add(typeHashMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(types_);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

	public void citys() {

		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List pys = d
				.queryForList("select citypy_,count(*)  as count from aiyou_city group by citypy_");
		for (int i = 0; i < pys.size(); i++) {
			Map city = (Map) pys.get(i);
			String citypy_ = (String) city.get("citypy_");
			Integer count = (Integer) city.get("count");
			// System.out.println(citypy_ + "____" + count);
			System.out.println("<dl class=choose_>");
			System.out.println("<dt>" + citypy_ + "£¨" + count + "£©" + "</dt>");
			System.out.println("<dd>");
			List types = d
					.queryForList("select universalid,cityname from aiyou_city where  citypy_="
							+ "'" + citypy_ + "'");
			for (int j = 0; j < types.size(); j++) {
				Map type = (Map) types.get(j);
				Long typeId = (Long) type.get("universalid");
				String name = (String) type.get("cityname");
				String typeName = (String) type.get("wordvalue");
				System.out.println("<a href="
						+ "/o2o/li-company-buy-online.f1j?v=fancompany&cityid="
						+ typeId + ">" + name + "</a>");
			}
			System.out.println("</dd>");
			System.out.println("</dl>");

		}

	}

	@Test
	public void test___() {
		citys();
	}

	public void a() {
		ArrayList<HashMap> types = new ArrayList<HashMap>();
		for (int i = 0; i < 10; i++) {
			HashMap type = new HashMap();
			type.put("type", new Integer(i).toString());
			ArrayList<HashMap> childs = new ArrayList<HashMap>();
			for (int j = 0; j < 2; j++) {
				HashMap data = new HashMap();
				data.put("type", "123");
				data.put("name", "123");
				data.put("price", "123");
				childs.add(data);
			}
			type.put("child", childs);
			types.add(type);
		}
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
	}
}
