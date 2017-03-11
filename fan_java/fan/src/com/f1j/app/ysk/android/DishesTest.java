package com.f1j.app.ysk.android;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.impl.ViewImpl;

public class DishesTest {
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

	@Test
	public void testQuery() throws Exception {
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		List dishes = d
				.queryForList("select a.universalid,a1.universalid as a1universalid,"
						+ "a.name,a.price,a4.wordvalue,a.content  from FanDish a  left join  T_UPLOADPHOTO a1"
						+ " on a.universalid=a1.dataid and a1.filetype='0' left join T_InforPubDict a4"
						+ " on a.type=a4.universalid where 0=0");
		for (int i = 0; i < dishes.size(); i++) {
			// Map map = (Map) dishes.get(i);
			// Iterator keys = map.keySet().iterator();
			// Object key = keys.next();
			// Object value = map.get(key);
			// System.out.print(key + "=" + value + "\t");
			// key = keys.next();
			// value = map.get(key);
			// System.out.print(key + "=" + value + "\t");
			// System.out.println();
		}
		StringBuilder json = new StringBuilder("{'list' : [");
		for (int i = 0; i < dishes.size(); i++) {
			Map map = (Map) dishes.get(i);
			Integer id = (Integer) map.get("universalid");
			String img = (String) map.get("a1universalid");
			String name = (String) map.get("name");
			String price = (String) map.get("price");
			String content = (String) map.get("content");
			json.append(toJson(id + "/" + img, name, price, content));
			if (i < dishes.size() - 1)
				json.append(",");
		}
		json.append("]}");
		System.out.println(json.toString());
	}

	public static String toJson(String img, String name, String price,
			String content) {
		return "{ 'data' : { 'name' : '" + name + "', 'price':'" + price
				+ "', 'img':'" + img + "', 'content': '" + content + "'}}";
	}

}
