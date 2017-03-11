package com.fanerp.action;

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
import com.f1jframework.eform.CommonDao;

public class ListSuitTypeDishs extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/json;charset=GB2312");
		String id = arg0.getParameter("id");
		String a = jsonData(id);
		arg1.getWriter().print(a);
		System.out
				.println("======================================================================");
		System.out.println(a);
	}

	@Test
	public void hi() {
		jsonData("445");
	}

	public String jsonData(String pid) {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		ArrayList<HashMap> types_ = new ArrayList<HashMap>();
		List types = d
				.queryForList("select a.suittypeid,b.name from FANDISHSUITTYPES a join FANDISHSUITTYPE b   on a.suittypeid =b.universalid    where pid='"
						+ pid + "'");
		for (int i = 0; i < types.size(); i++) {
			Map type = (Map) types.get(i);
			String typeId = (String) type.get("suittypeid");
			String typeName = (String) type.get("name");
			List dishes = d
					.queryForList("select a.dishid,b.name from  FANDISHSUITTYPEITEM a join fandish b on a.dishid =b.universalid  where a.pid='"
							+ typeId + "'");
			// String a =
			// "select a.dishid,c.count1,b.name from  FANDISHSUITTYPEITEM a  join fandish b "
			// +
			// "on a.dishid =b.universalid  left join  ( select * from FANorderitemdishitem where pid='"
			// + "session.getId()"
			// + "' ) c    on a.dishid=c.dishid  where a.pid='"
			// + typeId + "'";
			ArrayList<HashMap> childs = new ArrayList<HashMap>();
			for (int j = 0; j < dishes.size(); j++) {
				Map map = (Map) dishes.get(j);
				String id = (String) map.get("dishid");
				String name = (String) map.get("name");
				HashMap map_ = new HashMap();
				map_.put("dishid", id);
				map_.put("dishidforValue", name);
				childs.add((HashMap) map_);
			}
			HashMap typeHashMap = new HashMap();
			typeHashMap.put("type", typeName);
			typeHashMap.put("child", childs);
			types_.add(typeHashMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(types_);
		System.out.println(jsonArray.toString());
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
