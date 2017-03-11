package com.f1j.app.ysk.android;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class ListHi extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData("");
		arg1.getWriter().print(a);
		System.out.println(a);
	}

	@Test
	public void hi() {
		try {
			jsonData("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(a);
	}

	public String jsonData(String id) throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select hiinfo from fanconfig where universalid='1'";
		LazyDynaBean bean = d.queryForObject(sql);
		HashMap hm = new HashMap();
		hm.put("hiinfo", bean);
		sql = "select universalid ,wordvalue as name,count1 from T_INFORPUBDICT"
				+ " a left join(select count(*) count1,cat from fandishinfo  group by cat) as b"
				+ " on a.universalid=b.cat where a.wordname='fandishtaste'";
		List l = d.queryForList(sql);
		hm.put("dishtaste", l);
		sql = "select universalid ,wordvalue as name,count1 from T_INFORPUBDICT"
				+ " a left join(select count(*) count1,cat from fandishinfo  group by cat) as b "
				+ " on a.universalid=b.cat where a.wordname='fandishwayofcooking'";
		l = d.queryForList(sql);
		hm.put("dishhowcooking", l);		
		sql = "select universalid,dictid as typeid  from fandish a1  join FANDISHTASTES a2 on  a1.universalid =a2.pid";
		l = d.queryForList(sql);
		hm.put("dishtasteindex", l);		
		JSONArray jsonArray = JSONArray.fromObject(hm);
		JSONObject aa = (JSONObject) jsonArray.get(0);
		JSONArray array1 = (JSONArray) aa.getJSONArray("dishtaste");
		JSONObject product = (JSONObject) aa.getJSONObject("hiinfo");
		System.out.println("==" + product.get("hiinfo"));
		for (int i = 0; i < array1.size(); i++) {
			JSONObject childObject = array1.getJSONObject(i);
			System.out.println("name===" + childObject.getString("name"));
			System.out.println("universalid==="
					+ childObject.getString("universalid"));
		}
		// System.out.println(jsonArray.toString());
		// System.out.println(bean.get("hiinfo"));
		return jsonArray.toString();
	}
}
