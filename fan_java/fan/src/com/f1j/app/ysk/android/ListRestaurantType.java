package com.f1j.app.ysk.android;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class ListRestaurantType extends Action {
	private String type;
	private String name;

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		type = arg0.getParameter("type");
		name = arg0.getParameter("name");
		System.out.println("=========================================");
		System.out.println(type);
		name = URLDecoder.decode(name, "UTF-8");
		System.out.println(type);
		arg1.setContentType("text/html;charset=GB2312");
		String a = type();
		arg1.getWriter().print(a);
		System.out.println("hi");
		System.out.println(a);
	}

	@Test
	public void jsonData() {
		ApplicationContext ctx = AppSession.getApplicationContext();
		if (ctx == null)
			ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		CommonDao d = (CommonDao) ctx.getBean("commonDao");
		List types = d
				.queryForList("select universalid ,wordvalue as name,count1 from T_INFORPUBDICT     a left join(select count(*) count1,cat from fandishinfo  group by cat) as b on a.universalid=b.cat where a.wordname='"
						+ type + "'");
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
	}

	public String type() throws Exception {
		// ApplicationContext cxt = AppSession.getApplicationContext();

		ApplicationContext ctx = AppSession.getApplicationContext();
		if (ctx == null)
			ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		CommonDao d = (CommonDao) ctx.getBean("commonDao");
		String sql;
		if (type.equals("1")) {
			sql = "select universalid ,wordvalue as name,count1 from T_INFORPUBDICT     a left join(select count(*) count1,cat from fandishinfo  group by cat) as b on a.universalid=b.cat where a.wordname='"
					+ name + "'";
		} else {
			sql = "select itemcode as universalid,itemvalue as name from T_form_item_dict a where itemid='"
					+ name + "'";
		}
		List types = d.queryForList(sql);
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
