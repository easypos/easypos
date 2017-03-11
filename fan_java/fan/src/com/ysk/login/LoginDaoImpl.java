package com.ysk.login;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class LoginDaoImpl {
	private int userId;
	private String pid;
	private String type;

	public LoginDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int load(String user) {
		int result = 0;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			Logger.getLogger(this.getClass().getName()).info("µÇÂ¼");
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "SELECT universalid,PASSWORD,USER_STATE FROM T_USER WHERE USER_NAME ='"
					+ user + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			List<?> types = d.queryForList(sql);
			// Map<?, ?> a = (Map<?, ?>) types.get(0);
			result = types.size();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		// JSONArray jsonArray = JSONArray.fromObject(types);
		// System.out.println(jsonArray.toString());
		return result;
	}

	public int load(String user, String pass) {
		int result = 0;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			Logger.getLogger(this.getClass().getName()).info("µÇÂ¼");

			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "SELECT universalid,PASSWORD,USER_STATE,pid,type FROM T_USER WHERE USER_NAME ='"
					+ user
					+ "' AND PASSWORD='"
					+ pass
					+ "'"
					+ " and delete_symbol='0'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			List<?> types = d.queryForList(sql);
			Map<?, ?> a = (Map<?, ?>) types.get(0);
			userId = (Integer) a.get("universalid");
			pid = (String) a.get("pid");
			type = (String) a.get("type");
			result = types.size();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		// JSONArray jsonArray = JSONArray.fromObject(types);
		// System.out.println(jsonArray.toString());
		return result;
	}

	public int loadShopId(String id) {
		int result = 0;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			Logger.getLogger(this.getClass().getName()).info("hi");
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "SELECT universalid  FROM fancompany WHERE universalid ='"
					+ id + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			List<?> types = d.queryForList(sql);
			result = types.size();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		// JSONArray jsonArray = JSONArray.fromObject(types);
		// System.out.println(jsonArray.toString());
		return result;
	}

	/**
	 * save company code!
	 * 
	 * @param pid
	 * @param userId
	 * @throws java.lang.Exception
	 */
	public void update(String pid, String userId) throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE t_user SET pid=? WHERE UniversalID=?";
		d.getJdbcTemplate().update(sql, new Object[] { pid, userId });
	}
}
