package com.fanerp.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class InitDishImgs extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			CommonboService boService = (CommonboService) cxt
					.getBean("boService");
			boService.update("update FANDININGTABLE set status='0'");
			arg1.getWriter().println("sucess!");
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}

	@Test
	public void a() throws SQLException {
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonDao dao = (CommonDao) cxt.getBean("commonDao");
		Vector ids = new Vector();
		dao.update("update T_UPLOADPHOTO set filetype='1' where tablename='dish' ");
		List dishs = dao
				.queryForList("select * from T_UPLOADPHOTO where tablename='dish'");
		for (int j = 0; j < dishs.size(); j++) {
			Map map = (Map) dishs.get(j);
			String dataId = (String) map.get("dataid");
			String universalid = (String) map.get("universalid");

			System.out.println(universalid);
			if (!ids.contains(dataId)) {
				ids.add(dataId);
				dao.update("update T_UPLOADPHOTO set filetype='0' where tablename='dish' and universalid="
						+ "'" + universalid + "'");
			}
		}
	}

}
