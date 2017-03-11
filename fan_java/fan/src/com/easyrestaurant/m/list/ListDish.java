package com.easyrestaurant.m.list;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.view.action.ListV4R1;
import com.f1jframework.eform.CommonDao;

public class ListDish extends ListV4R1 {

	public void a(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		request.setAttribute("shop-name", load(id));
	}

	public String load(String id) {
		String result = "";
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			Logger.getLogger(this.getClass().getName()).info("µÇÂ¼");
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "SELECT name FROM fancompany WHERE universalid ='"
					+ id + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			LazyDynaBean a = d.queryForObject(sql);
			result = (String) a.get("name");
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		// JSONArray jsonArray = JSONArray.fromObject(types);
		// System.out.println(jsonArray.toString());
		return result;
	}
}
