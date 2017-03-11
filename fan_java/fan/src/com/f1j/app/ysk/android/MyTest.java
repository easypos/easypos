package com.f1j.app.ysk.android;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.model.FormItemBean;
import com.f1jeeframework.view.ViewBuilder;
import com.f1jeeframework.view.ViewHandler;
import com.f1jeeframework.view.service.PagerService;
import com.f1jframework.eform.CommonDao;

public class MyTest {
	static ApplicationContext cxt;

	// 初始化ApplicationContext容器
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 使用ClassPathXmlApplicationContext方式初始化ApplicationContext容器
		cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		AppSession.setApplicationContext(cxt);

		// wfService.getPdf().refresh();
		// wfService.getTaskDf().refresh();
	}

	public void testQuery() throws Exception {
		// ViewImpl impl = new ViewImpl("dish", "");
		// impl.setFindKey("");
		// String a = impl.getSql();
		// System.out.println(a);
		ViewBuilder builder = new ViewBuilder();
		builder.initView("dish", "", "");
		ViewHandler viewHandler = new ViewHandler(builder.getSql(), 1);
		viewHandler.setPageRows(new Integer(1000));
		viewHandler.setPageNum(new Integer(1));
		viewHandler.setViewApp("dish");
		viewHandler.setViewBean(builder.getViewBean());
		viewHandler.setViewItems(builder.getViewItems());
		viewHandler.setArg(null);
		viewHandler.handleView(false);
		System.out.println(builder.getSql());
		System.out.println(viewHandler.html());
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		int a = commonDao
				.queryForInt("select count(*) from eform a join usereform  a1 on  a.universalid=a1.pid  where id='4'");
		System.out.print(a);
	}

	public void testA() throws Exception {
		Class actionClass = Class.forName("com.f1jframework.eform.A");
		Object instanse = actionClass.newInstance();
		com.f1jframework.eform.A a = (com.f1jframework.eform.A) instanse;
		a.a();
	}

	public void test11() throws Exception {
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List<FormItemBean> beanList = new ArrayList<FormItemBean>();
		FormItemBean a = new FormItemBean();
		a.setItemName("title");
		a.setType("1");
		a.setListValueType("0");
		beanList.add(a);
		a = new FormItemBean();
		a.setItemName("content");
		a.setType("3");
		a.setListValueType("0");
		beanList.add(a);
		List<LazyDynaBean> r = d.list(beanList,
				"select universalid,title,content,infodate from fandishinfo");
		// ArrayList<HashMap> datas = new ArrayList<HashMap>();
		JSONArray jsonArray = JSONArray.fromObject(r);
		System.out.println(jsonArray.toString());
	}

	public void test1() throws Exception {
		PagerService pagerService = (PagerService) cxt.getBean("pagerService");
		// List<LazyDynaBean> r = d.list(beanList,
		// "select universalid,title,content,infodate from fandishinfo");
		List r = pagerService.queryForList(0, 20,
				"select universalid,title,content,infodate from fandishinfo");
		// ArrayList<HashMap> datas = new ArrayList<HashMap>();
		JSONArray jsonArray = JSONArray.fromObject(r);
		System.out.println(pagerService.getAllCount());
		System.out.println(jsonArray.toString());
	}

	public void test_() {
		List<Integer> hides = new ArrayList<Integer>();
		hides.add(1);
		hides.add(2);
		hides.add(3);
		if (hides.contains(1)) {
			System.out.println("=====" + 1);
		}
		if (hides.contains(2)) {
			System.out.println("=====" + 2);
		}

		if (hides.contains(6)) {
			System.out.println("=====" + 1);
		}
	}

	@Test
	public void test() {
		for (int i =0; i < 100; i++) {
			System.out.println(i+"______"+i % 4);
		}
	}
}
