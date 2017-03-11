package com.kuaichuan.meishi;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class LoadRestaurantForShake extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData();
		arg1.getWriter().print(a);
		// System.out.println(a);
	}

	@Test
	public void hi() {
		try {
			jsonData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(a);
	}

	public String jsonData() throws SQLException {

		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select top 1 a.universalid,a1.universalid as a1universalid,a.name,a.address,"
				+ "a4.wordvalue,a.tel,a.content,a7.name as a7name      ,a.code,a.price  from fancompany"
				+ " a  left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='fancompany' left join T_InforPubDict a4 on "
				+ "a.restauranttype=a4."
				+ "universalid left join district a7 on a.district=a7.universalid  where 0=0 order by newId()";
		List l = d.queryForList(sql);
		
		JSONArray jsonArray = JSONArray.fromObject(l);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

	@Test
	public void a() {
		String a = "aaa(1)";
		System.out.println(a.substring(0, a.indexOf("(")));
		System.out.println(a.substring(a.indexOf("("), a.length()));
	}
}
