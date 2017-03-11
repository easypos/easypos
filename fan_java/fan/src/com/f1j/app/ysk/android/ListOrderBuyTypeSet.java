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

public class ListOrderBuyTypeSet extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = jsonData();
		arg1.getWriter().print(a);
		System.out.println(a);
	}

	@Test
	public void a() {
		jsonData();
	}

	public String jsonData() {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String id = "40";
		List dishes = d
				.queryForList("select universalid,buytype as name,money as price from Fanorderbuytypeset where status='1'");
		JSONArray jsonArray = JSONArray.fromObject(dishes);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
}
