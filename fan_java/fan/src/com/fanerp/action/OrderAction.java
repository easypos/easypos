package com.fanerp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;
import com.fanerp.util.StringAlign;

public class OrderAction extends Action {

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public HashMap loadDataByType(String id) throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a2.wordvalue,count(*)  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid left join T_INFORPUBDICT a2 on a1.type=a2.universalid   where a.pid='"
				+ id + "' group by  a2.wordvalue";
		List types = d.queryForList(sql);
		int sum = 0;
		int count = 0;
		StringAlign formatter = new StringAlign(20, StringAlign.JUST_LEFT, true);
		StringBuffer order = new StringBuffer();
		for (int i = 0; i < types.size(); i++) {
			Map a = (Map) types.get(i);
			String type = (String) a.get("wordvalue");
			sql = "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='"
					+ id + "' and  a2.wordvalue='" + type + "'";
			List dishes = boService.getDao().queryForList(sql);
			order.append(type);
			order.append("\n");
			for (int j = 0; j < dishes.size(); j++) {
				Map dish = (Map) dishes.get(j);
				String name = (String) dish.get("name");
				Integer dishcount = (Integer) dish.get("dishcount");
				Integer price = (Integer) dish.get("price");
				sum = sum + price * dishcount;
				count = count + dishcount;
				order.append(dishcount);
				order.append(" ");
				order.append(formatter.format(name.trim()));
				order.append(" ");
				order.append(price);
				order.append("\n");
				// System.out.println("=====" + object.getString("name"));
				// System.out.println(object.getString("universalid"));
			}
		}

		HashMap resultValue = new HashMap();
		resultValue.put("str", order.toString());
		resultValue.put("count", count);
		resultValue.put("sum", sum);
		return resultValue;
		// String sql1 =
		// "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='1357200598735'";

	}

}
