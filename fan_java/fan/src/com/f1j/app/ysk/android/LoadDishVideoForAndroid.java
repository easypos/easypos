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

public class LoadDishVideoForAndroid extends Action {
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
			jsonData("300");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(a);
	}

	public String jsonData(String id) throws SQLException {
		//ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a.name,a.price,a.content,a.piny  from fandish a  where a.universalid='"
				+ id + "'";
		LazyDynaBean bean = d.queryForObject(sql);
		JSONArray jsonArray = JSONArray.fromObject(bean);
		// System.out.println(jsonArray.toString());
		sql = "select universalid,dataid,tablename, filename  ftype,filetype from  t_uploadphoto a where dataid='"
				+ id
				+ "' and tablename='dish' and filetype='1'   and substring(filename,charindex('.',filename),10)='.flv'";

		List l = d.queryForList(sql);
		HashMap hm = new HashMap();
		hm.put("product", bean);
		hm.put("imgs", l);
		jsonArray = JSONArray.fromObject(hm);
		System.out.println(jsonArray.toString());
		// JSONArray array = new JSONArray();
		JSONObject aa = (JSONObject) jsonArray.get(0);
		JSONArray array1 = (JSONArray) aa.getJSONArray("imgs");
		JSONObject product = (JSONObject) aa.getJSONObject("product");
		System.out.println("==" + product.get("name"));
		System.out.println("==" + product.get("price"));
		System.out.println("==" + product.get("content"));
		for (int i = 0; i < array1.size(); i++) {
			JSONObject childObject = array1.getJSONObject(i);
			System.out.println("dataid===" + childObject.getString("dataid"));
			System.out.println("universalid==="
					+ childObject.getString("universalid"));
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
