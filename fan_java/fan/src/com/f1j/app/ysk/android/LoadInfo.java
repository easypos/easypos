package com.f1j.app.ysk.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class LoadInfo extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GB2312");
		String id = arg0.getParameter("id");
		//System.out.println(id);
		String a = jsonData(id);
		arg1.getWriter().print(a);
		//System.out
		//		.println("======================================================================");
		//System.out.println(a);
	}

	@Test
	public void hi() {
		jsonData("645");
	}

	public String jsonData(String companycode) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		ArrayList<HashMap> types_ = new ArrayList<HashMap>();
		// Á¬Ëø²ÍÌì

		List types = d
				.queryForList("select universalid ,wordvalue,count1 from T_INFORPUBDICT   a join(select count(*) count1,type from FanDish a"
						+ " join fancompany f on a.companycode=f.pid  where f.universalid='"
						// + " where a.companycode='"
						+ companycode
						+ "' group by type) as b on a.universalid=b.type");
		//
		for (int i = 0; i < types.size(); i++) {
			Map type = (Map) types.get(i);
			Integer typeId = (Integer) type.get("universalid");
			Integer count1 = (Integer) type.get("count1");
			String typeName = (String) type.get("wordvalue");
			String a = "select a.universalid,a1.universalid as a1universalid,"
					+ "a.name,statictype,howcooking,a.piny,a.dishnu,a.price,a.discount,a.memberprice,a4.wordvalue,a.content,a.statictype  from FanDish a   left join  T_UPLOADPHOTO a1"
					+ " on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='dish'  join T_InforPubDict a4"
					+ " on a.type=a4.universalid "
					+ " join fancompany f on a.companycode=f.pid  where f.universalid='"
					// " where   a.companycode='"
					+ companycode + "' and a.type='" + typeId + "'"
					+ " and a.status='1' " + " order by a.universalid ";
			//System.out.println(a);

			List dishes = d.queryForList(a);
			ArrayList<HashMap> childs = new ArrayList<HashMap>();
			for (int j = 0; j < dishes.size(); j++) {
				Map map = (Map) dishes.get(j);
				//String id = (Integer) map.get("universalid");
				String id = (String) map.get("universalid");
				String img = (String) map.get("a1universalid");
				String name = (String) map.get("name");
				String piny = (String) map.get("piny");
				String dishnu = (String) map.get("dishnu");
				String price = (String) map.get("price");
				String discount = (String) map.get("discount");
				String memberprice = (String) map.get("memberprice");
				String content = (String) map.get("content");
				HashMap map_ = new HashMap();
				map_.put("id", id);
				map_.put("img", img);
				map_.put("name", name);
				map_.put("piny", piny);
				map_.put("dishnu", dishnu);
				map_.put("price", price);
				map_.put("discount", discount);
				map_.put("memberprice", memberprice);
				map_.put("content", content);
				map_.put("statictype", (String) map.get("statictype"));
				map_.put("howcooking", (String) map.get("howcooking"));
				childs.add((HashMap) map_);
			}
			HashMap typeHashMap = new HashMap();
			typeHashMap.put("type", typeName + "(" + count1 + ")");
			typeHashMap.put("child", childs);
			types_.add(typeHashMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(types_);

		// System.out.println(jsonArray.toString());
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
