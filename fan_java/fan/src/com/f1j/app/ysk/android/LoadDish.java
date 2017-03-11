package com.f1j.app.ysk.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class LoadDish extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		StringBuilder json = new StringBuilder("{'list' : [");
		List types = d
				.queryForList("select universalid ,wordvalue,count1 from T_INFORPUBDICT   a join(select count(*) count1,type from FanDish group by type) as b on a.universalid=b.type");
		for (int i = 0; i < types.size(); i++) {
			Map type = (Map) types.get(i);
			Integer typeId = (Integer) type.get("universalid");
			String count1 = (String) type.get("count1");
			String typeName = (String) type.get("wordvalue");
			List dishes = d
					.queryForList("select a.universalid,a1.universalid as a1universalid,"
							+ "a.name,a.price,a4.wordvalue,a.content  from FanDish a  left join  T_UPLOADPHOTO a1"
							+ " on a.universalid=a1.dataid and a1.filetype='0' left join T_InforPubDict a4"
							+ " on a.type=a4.universalid where a.type='"
							+ typeId + "'");
			json.append("{'" + typeName + "':[");
			for (int j = 0; j < dishes.size(); j++) {
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

			for (int j = 0; j < dishes.size(); j++) {
				Map map = (Map) dishes.get(j);
				Integer id = (Integer) map.get("universalid");
				String img = (String) map.get("a1universalid");
				String name = (String) map.get("name");
				String price = (String) map.get("price");
				String content = (String) map.get("content");
				json.append(toJson(typeName, id + "/" + img, name, price,
						content));
				if (j < dishes.size() - 1)
					json.append(",");
			}
			json.append("]}");
			if (i < types.size() - 1)
				json.append(",");
		}
		json.append("]}");
		arg1.getWriter().print(json.toString());
	}

	public static String toJson(String type, String img, String name,
			String price, String content) {
		return "{ 'data' : { 'name' : '" + name + "', 'price':'" + price
				+ "', 'img':'" + img + "', 'content': '" + content + "'}}";
	}

	@Test
	public void a_() {	
	//number=Math.floor(Math.random());
	Random r=new Random();
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	System.out.print(r.nextInt(10));
	}

	public void a() {
		ArrayList<HashMap> types = new ArrayList<HashMap>();
		for (int i = 0; i < 10; i++) {
			HashMap type = new HashMap();
			type.put("type", new Integer(i).toString());
			ArrayList<HashMap> childs = new ArrayList<HashMap>();
			for (int j = 0; j < 2; j++) {
				HashMap data = new HashMap();
				data.put("type", "123");
				data.put("name", "123");
				data.put("price", "123");
				childs.add(data);
			}
			type.put("child", childs);
			types.add(type);
		}
		JSONArray jsonArray = JSONArray.fromObject(types);
		System.out.println(jsonArray.toString());
	}

}
