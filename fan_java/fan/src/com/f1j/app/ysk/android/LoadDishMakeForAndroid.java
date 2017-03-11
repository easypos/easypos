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

public class LoadDishMakeForAndroid extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData(id);
		arg1.getWriter().print(a);
		// System.out.println(a);
	}

	@Test
	public void hi() {
		try {
			jsonData("274");
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
		String sql = "select a.name,a.price,a.content,a.piny  from fandish a  where a.universalid='"
				+ id + "'";
		LazyDynaBean bean = d.queryForObject(sql);
		JSONArray jsonArray = JSONArray.fromObject(bean);
		// System.out.println(jsonArray.toString());
		sql = "select universalid,dishstep from  fandishmakestep a where pid='"
				+ id + "'";

		List l = d.queryForList(sql);
		HashMap hm = new HashMap();
		hm.put("product", bean);
		hm.put("make", l);
		sql = "select a.universalid ,a.name as id,b.name as name from  fandishmaterials a join fandishmaterial b on a.name=b.universalid   where a.pid='"
				+ id + "'";
		l = d.queryForList(sql);
		hm.put("material", l);
		sql = "select a.dictid as id ,b.wordvalue  as name from  FANDISHTASTES a join  T_INFORPUBDICT b on a.dictid=b.universalid   where (a.pid='"
				+ id + "' and b.wordname='fandishtaste')";
		l = d.queryForList(sql);
		hm.put("taste", l);
		jsonArray = JSONArray.fromObject(hm);
		System.out.println(jsonArray.toString());
		// JSONArray array = new JSONArray();
		JSONObject aa = (JSONObject) jsonArray.get(0);
		JSONArray array1 = (JSONArray) aa.getJSONArray("make");
		JSONArray array2 = (JSONArray) aa.getJSONArray("material");
		JSONArray array3 = (JSONArray) aa.getJSONArray("taste");
		JSONObject product = (JSONObject) aa.getJSONObject("product");
		// System.out.println("==" + product.get("name"));
		// System.out.println("==" + product.get("price"));
		// System.out.println("==" + product.get("content"));
		for (int i = 0; i < array1.size(); i++) {
			JSONObject childObject = array1.getJSONObject(i);
			// System.out.println("dataid===" +
			// childObject.getString("dishstep"));
			// System.out.println("universalid==="
			// + childObject.getString("universalid"));
		}
		return jsonArray.toString();
	}

	@Test
	public void a() {
		String a = "aaa(1)";
		System.out.println(a.substring(0, a.indexOf("(")));
		System.out.println(a.substring(a.indexOf("("), a.length()));
	}
}
