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

public class ListDishSuit extends Action {
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
		jsonData("1");
	}

	public String jsonData(String companycode) {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		ArrayList<HashMap> types_ = new ArrayList<HashMap>();		

		List dishes = d
				.queryForList("select a.universalid,a1.universalid as a1universalid,"
						+ "a.name,statictype,a.piny,a.dishnu,a.price,a4.wordvalue,a.content,a.statictype  from FanDish a   left join  T_UPLOADPHOTO a1"
						+ " on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='dish'  join T_InforPubDict a4"
						+ " on a.type=a4.universalid where a.dishtype='1" + "'");
		ArrayList<HashMap> list = new ArrayList<HashMap>();
		for (int j = 0; j < dishes.size(); j++) {
			Map map = (Map) dishes.get(j);
			Integer id = (Integer) map.get("universalid");
			String img = (String) map.get("a1universalid");
			String name = (String) map.get("name");
			String piny = (String) map.get("piny");
			String dishnu = (String) map.get("dishnu");
			String price = (String) map.get("price");
			String content = (String) map.get("content");
			HashMap map_ = new HashMap();
			map_.put("id", id);
			map_.put("img", img);
			map_.put("name", name);
			map_.put("piny", piny);
			map_.put("dishnu", dishnu);
			map_.put("price", price);
			map_.put("content", content);
			map_.put("statictype", (String) map.get("statictype"));
			List list_ = d
					.queryForList("select a.dishid,a1.universalid as a1universalid,"
							+ "a4.name  from FanDishitem a   left join  T_UPLOADPHOTO a1"
							+ " on a.dishid=a1.dataid and a1.filetype='0' and a1.tablename='dish'  join fandish a4"
							+ " on a.dishid=a4.universalid where a.pid='"
							+ id
							+ "'");
			map_.put("childs", list_);
			list.add((HashMap) map_);
		}

		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());

		for (int j = 0; j < jsonArray.size(); j++) {
			JSONObject jo = jsonArray.getJSONObject(j);
			String name = (String) jo.get("name");
			System.out.print("===\n");
			System.out.print(name);
			
			JSONArray array1 = (JSONArray) jo.getJSONArray("childs");
			for (int a = 0; a < array1.size(); a++) {
				JSONObject jo_ = array1.getJSONObject(a);
				String name_ = (String) jo_.get("name");
				System.out.print("===");
				System.out.print(name_);
			}

		}
		return jsonArray.toString();
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
