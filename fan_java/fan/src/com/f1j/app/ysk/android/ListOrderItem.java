package com.f1j.app.ysk.android;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class ListOrderItem extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = jsonData(arg0.getParameter("id"));
		arg1.getWriter().print(a);
		System.out.println(a);
	}

	@Test
	public void a() {
		jsonData("1367505343503");
	}

	public String jsonData(String id) {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String id = "40";
		List dishes = d
				.queryForList("select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a.note from fanorderitem a "
						+ "left join FANDISH a1"
						+ " on a.dishid=a1.universalid "
						+ " where a.pid="
						+ "'" + id + "'");
		System.out
				.println("select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a.note from fanorderitem a "
						+ "left join FANDISH a1"
						+ " on a.dishid=a1.universalid "
						+ " where a.pid="
						+ "'" + id + "'");
		JSONArray jsonArray = JSONArray.fromObject(dishes);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
}
