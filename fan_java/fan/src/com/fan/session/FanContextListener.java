package com.fan.session;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jframework.eform.CommonboService;

public final class FanContextListener implements ServletContextListener {
	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {
		try {
			init();
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Couldn't open." + ex.getMessage());
		}
		/*
		 * Counter counter = new Counter(); context.setAttribute("hitCounter",
		 * counter); context.log("Created hitCounter" + counter.getCounter());
		 * counter = new Counter(); context.setAttribute("orderCounter",
		 * counter); context.log("Created orderCounter" + counter.getCounter());
		 */
	}

	private void init() throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		System.out.println("欢迎使用云尚客（YSK）餐饮平台。。。");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		LazyDynaBean fanConfigPrint = boService.getDao().queryForObject(
				"select *   from fanconfig where universalid='1'");
		FanSession.setPrintipforcook((String) fanConfigPrint
				.get("printipforcook"));

		System.out.println((String) fanConfigPrint.get("printipforcook"));
		FanSession.setPrintipforbuy((String) fanConfigPrint
				.get("printipforbuy"));
		// printipforbuy
		// printipforbuy
		
		System.out.println((String) fanConfigPrint.get("printipforbuy"));
		FanSession.setPrinttitleforcook((String) fanConfigPrint
				.get("printtitleforcook"));
		FanSession.setPrinttitleforbuy((String) fanConfigPrint
				.get("printtitleforbuy"));
		FanSession.setPrintheadforcook((String) fanConfigPrint
				.get("printheadforcook"));
		FanSession.setPrintheadforbuy((String) fanConfigPrint
				.get("printheadforbuy"));
		FanSession.setPrintmore((String) fanConfigPrint.get("printmore")+"\n");
		List list = boService.getDao().queryForList(
				"select * from FANCONFIGPRINT ");
		FanSession.setPrintList(list);
		
		JSONArray jsonArray = JSONArray.fromObject(fanConfigPrint);
		System.out.println(jsonArray.toString());
		System.out.println("欢迎使用云尚客（YSK）餐饮平台。。。init OK.");
	}

	@Test
	public void test1() {
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		try {
			// System.out.println("bpm,Close...");
		} catch (Exception ex) {
			// System.out.println("Couldn't close." + ex.getMessage());
		}
	}
}
