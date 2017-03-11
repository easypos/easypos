package com.fanerp.member;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class MemberDao {
	public int decideMember(String tel) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select count(*)  from fanmember where  tel='" + tel + "'";
		//
		int a = d.queryForInt(sql);
		return a;
	}

	public LazyDynaBean loadMember(String tel) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select name,address,recharge,created  from fanmember where  tel='"
				+ tel + "'";
		//
		LazyDynaBean fanMember = d.queryForObject(sql);
		return fanMember;
	}

	public void add(String tel, Float sum) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		DecimalFormat df = new DecimalFormat("0.00");
		d.update("update fanmember set  recharge='" + df.format(sum) + "'"
				+ " where tel='" + tel + "'");
	}

	public void reduce(String tel, Float sum) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		DecimalFormat df = new DecimalFormat("0.00");
		d.update("update fanmember set  recharge='" + df.format(sum) + "'"
				+ " where tel='" + tel + "'");
	}

	@Test
	public void test() throws ParseException {
		String sql = "select *  from fanmember where  tel='13598889564'";
		System.out.println(sql);
		// int a = boService.getDao().queryForInt(sql);
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		LazyDynaBean fanMember = null;
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		try {
			fanMember = boService.getDao().queryForObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		if (fanMember == null) {
			System.out.println("test is null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(sdf.parse("2016-1-1 1:1:1.0")));
		DecimalFormat df = new DecimalFormat("0.00");
		String test = "100";
		System.out.println(df.format(Float.valueOf(test)));
	}

}
