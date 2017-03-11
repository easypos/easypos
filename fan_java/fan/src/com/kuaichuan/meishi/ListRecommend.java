package com.kuaichuan.meishi;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class ListRecommend extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData(id);
		arg1.getWriter().print(a);
		System.out.println(a);
	}

	@Test
	public void hi() {
		try {
			jsonData("2");
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
		String sql = "select name from fandish where recommend='0' and companycode='"
				+ id + "'";
		List li = d.queryForList(sql);
		JSONArray jsonArray = JSONArray.fromObject(li);
		System.out.println(jsonArray.toString());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject childObject = jsonArray.getJSONObject(i);
			System.out.println("name===" + childObject.getString("name"));

		}
		return jsonArray.toString();

	}
}