package com.f1j.app.ysk.android;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.view.service.PagerService;
import com.f1jframework.eform.CommonDao;

public class ListInfo1 extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = a(new Integer(arg0.getParameter("p")),
				arg0.getParameter("typeId"));
		arg1.getWriter().print(a);
		System.out.println("hi");
		System.out.println(a);
	}

	// @Test
	public String jsonData() {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List types = d
				.queryForList("select universalid,title,content,infodate from info1");
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

	@Test
	public void a() throws Exception {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		PagerService pagerService = (PagerService) cxt.getBean("pagerService");
		// List<LazyDynaBean> r = d.list(beanList,
		// "select universalid,title,content,infodate from info1");
		List r = pagerService.queryForList(1, 2,
				"select universalid,title,content,infodate from info1");

		// ArrayList<HashMap> datas = new ArrayList<HashMap>();
		JSONArray jsonArray = JSONArray.fromObject(r);
		System.out.println(jsonArray.toString());
		System.out.println("===" + pagerService.getAllCount());
		// return jsonArray.toString();
	}

	public String a(int p, String typeId) throws Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		PagerService pagerService = (PagerService) cxt.getBean("pagerService");
		// List<LazyDynaBean> r = d.list(beanList,
		// "select universalid,title,content,infodate from info1");
		// Integer count = pagerService.getAllCount();
		List r = null;
		if (typeId == null) {
			r = pagerService
					.queryForList(p, 10,
							"select universalid,title,author,infodate from info1");
		} else {
			r = pagerService
					.queryForList(p, 10,
							"select universalid,title,author,infodate from info1 where cat='"+typeId+"'");
		}
		// ArrayList<HashMap> datas = new ArrayList<HashMap>();
		System.out.println(pagerService.getPageCount() + "====" + p);
		if (pagerService.getPageCount() < p) {
			return "[]";
		}
		JSONArray jsonArray = JSONArray.fromObject(r);
		return jsonArray.toString();
	}

}
