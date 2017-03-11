package com.f1j.app.ysk.android;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.f1jeeframework.view.service.PagerService;
import com.f1jframework.eform.CommonDao;

public class LoadDishDianPing extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String typeId = arg0.getParameter("typeId");
		
		String p = arg0.getParameter("p");
		if (p == null)
			p = "0";
		if (typeId == null) {
			typeId = "5.0";
		}
		String a = jsonData(new Integer(p), id, typeId);

		arg1.getWriter().print(a);
		// System.out.println(a);
	}

	@Test
	public void hi() throws Exception {
		try {
			jsonData(0, "59", "4");
			System.out.println(new Float("1.0"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(a);
	}

	public String jsonData(int p, String id, String typeId) throws Exception {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		PagerService pagerService = (PagerService) cxt.getBean("pagerService");
		LazyDynaBean bean;
		String sql = "select sum(type)/count(*) as fen ,count(*) as count from fandishdianping a  where a.pid='"
				+ id + "'";
		bean = d.queryForObject(sql);
		JSONArray jsonArray = JSONArray.fromObject(bean);
		List l = null;
		if (typeId == null) {
			sql = "select type as fen ,content  from  fandishdianping where pid='"
					+ id + "'";
			l = pagerService.queryForList(p, 10, sql);
		} else {
			sql = "select type as fen ,content  from  fandishdianping where pid='"
					+ id + "'" + " and type='" + typeId + "'";
			l = pagerService.queryForList(p, 10, sql);
		}
		HashMap hm = new HashMap();
		int count = new Integer((String) bean.get("count"));
		if (count == 0) {
			bean.set("fen", "0");
		}

		hm.put("bean", bean);
		if (pagerService.getPageCount() < p) {
			List a = new ArrayList();
			hm.put("dianpings", a);
		} else {
			hm.put("dianpings", l);
		}
		jsonArray = JSONArray.fromObject(hm);
		System.out.println(jsonArray.toString());
		// JSONArray array = new JSONArray();
		JSONObject aa = (JSONObject) jsonArray.get(0);
		JSONArray array1 = (JSONArray) aa.getJSONArray("dianpings");
		JSONObject product = (JSONObject) aa.getJSONObject("bean");
		System.out.println("==" + product.get("fen"));
		System.out.println("==" + product.get("count"));
		for (int i = 0; i < array1.size(); i++) {
			JSONObject childObject = array1.getJSONObject(i);
			System.out.println("type===" + childObject.getString("fen"));
			System.out.println("content===" + childObject.getString("content"));
		}
		return jsonArray.toString();
	}

}
