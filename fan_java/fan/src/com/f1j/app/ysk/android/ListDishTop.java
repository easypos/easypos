package com.f1j.app.ysk.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;

public class ListDishTop extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData(id);
		arg1.getWriter().print(a);
		System.out
				.println("======================================================================");
		System.out.println(a);
	}

	@Test
	public void hi() {
		jsonData("12");
	}

	public String jsonData(String companycode) {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		ArrayList<HashMap> types_ = new ArrayList<HashMap>();
		String sql = "select universalid, sumdishcount from fandish e  join"
				+ " (select  sum(dishcount) as sumdishcount,sum(price) as sumprice,dishid  from (select type ,dishcount,a.price,dishid  from fanorderitem  as a join fandish  as b on a.dishid=b.universalid where 0=0 ) c group by dishid )  d"
				+ "  on e.universalid=d.dishid where e.companycode='"
				+ companycode + "' order by sumdishcount desc";
		List dishes = d.queryForList(sql);
		JSONArray jsonArray = JSONArray.fromObject(dishes);
		System.out.println(jsonArray.toString());
		for (int j = 0; j < jsonArray.size(); j++) {
			JSONObject jo = jsonArray.getJSONObject(j);
			Object name = jo.get("sumdishcount");
			System.out.print("===\n");
			System.out.print(name.toString());

		}
		return jsonArray.toString();
	}
}
