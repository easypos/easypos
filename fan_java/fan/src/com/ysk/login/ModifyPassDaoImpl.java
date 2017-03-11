package com.ysk.login;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class ModifyPassDaoImpl {

	public ModifyPassDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean a(String userId, String pass) {
		boolean result = false;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			Logger.getLogger(this.getClass().getName()).info("µÇÂ¼");
			
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "update T_USER set  PASSWORD='" + pass + "'"
					+ " where universalid ='" + userId + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			d.update(sql);
			// List<?> types = d.queryForList(sql);
			// Map<?, ?> a = (Map<?, ?>) types.get(0);
			// userId = (Integer) a.get("universalid");
			// result = types.size();
			result = true;
		} catch (Exception ex) {
			result = false;
			ex.printStackTrace();

		}
		// JSONArray jsonArray = JSONArray.fromObject(types);
		// System.out.println(jsonArray.toString());
		return result;
	}
}
