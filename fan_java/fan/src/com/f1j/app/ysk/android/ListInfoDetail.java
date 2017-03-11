package com.f1j.app.ysk.android;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.model.FormItemBean;
import com.f1jeeframework.view.service.PagerService;
import com.f1jframework.eform.CommonDao;

public class ListInfoDetail extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = a(arg0.getParameter("id"));
		arg1.getWriter().print(a);
		System.out.println("hi");
		System.out.println(a);
	}

	@Test
	public void jsonData() throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List<FormItemBean> mainBeans = new ArrayList<FormItemBean>();
		FormItemBean formItemBean = new FormItemBean();
		formItemBean.setItemName("title");
		formItemBean.setType("0");
		formItemBean.setListValueType("0");
		mainBeans.add(formItemBean);
		formItemBean = new FormItemBean();
		formItemBean.setItemName("content");
		formItemBean.setType("3");
		formItemBean.setListValueType("1");
		mainBeans.add(formItemBean);
		LazyDynaBean a = d.load("universalid", "1", mainBeans, "fandishinfo",
				"");
		JSONArray jsonArray = JSONArray.fromObject(a);
		System.out.println(jsonArray.toString());
		// return jsonArray.toString();
	}

	public String a(String id) throws Exception {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List<FormItemBean> mainBeans = new ArrayList<FormItemBean>();
		FormItemBean formItemBean = new FormItemBean();
		formItemBean.setItemName("title");
		formItemBean.setType("0");
		formItemBean.setListValueType("0");
		mainBeans.add(formItemBean);
		formItemBean = new FormItemBean();
		formItemBean.setItemName("content");
		formItemBean.setType("3");
		formItemBean.setListValueType("1");
		mainBeans.add(formItemBean);
		LazyDynaBean a = d
				.load("universalid", id, mainBeans, "fandishinfo", "");
		JSONArray jsonArray = JSONArray.fromObject(a);
		System.out.println(jsonArray.toString());
		return (String)a.get("content");
	}

}
