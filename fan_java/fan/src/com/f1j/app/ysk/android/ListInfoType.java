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

public class ListInfoType extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String a = a();
		arg1.getWriter().print(a);
		System.out.println("hi");
		System.out.println(a);
	}

	@Test
	public void jsonData() {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List types = d
				.queryForList("select universalid ,wordvalue as name,count1 from T_INFORPUBDICT     a left join(select count(*) count1,cat from fandishinfo  group by cat) as b on a.universalid=b.cat where a.wordname='dish-info-type'");
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
	}

	public String a() throws Exception {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List types = d
				.queryForList("select universalid ,wordvalue as name,count1 from T_INFORPUBDICT     a left join(select count(*) count1,cat from fandishinfo  group by cat) as b on a.universalid=b.cat where a.wordname='dish-info-type'");
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

}
