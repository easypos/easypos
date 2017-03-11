package com.ysk.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;

/**
 * 系统登陆
 */
public class LoginMyRestaurant extends com.f1jeeframework.http.Action {

	public void afterExcute(javax.servlet.http.HttpServletRequest arg0,
			javax.servlet.http.HttpServletResponse arg1) throws IOException,
			ServletException {
		PrintWriter out = arg1.getWriter();
		try {
			arg1.setContentType("text/html; charset=utf-8");
			this.setForward(false);
			System.out.println("Login Servlet ok");
			// request.setCharacterEncoding("GB2312");
			// String ent_code = arg0.getParameter("ent_code");
			HashMap<String, Object> paramValues = RequestUtil
					.getParamValuesByUtf_8(arg0);
			// System.out.println("Login Servlet ok");
			// request.setCharacterEncoding("GB2312");
			// String ent_code = arg0.getParameter("ent_code");
			// String user = arg0.getParameter("user");
			String user = (String) paramValues.get("user");
			System.out.print(user);
			String pass = arg0.getParameter("pass");
			// String lang = arg0.getParameter("lang");
			// String random_login_key = arg0.getParameter("verify_code");
			// String errorCode = "";
			OASession oasession = new OASession();
			oasession.setLoginIP(arg0.getRemoteHost());
			String cssIndex = arg0.getParameter("mySel");
			oasession.setCssFile(cssIndex);
			HttpSession session = arg0.getSession(false);
			System.out.println("RANDOM_LOGIN_KEY:"
					+ session.getAttribute("RANDOM_LOGIN_KEY"));
			String random_login_key_ = (String) session
					.getAttribute("RANDOM_LOGIN_KEY");
			// 验证码
			/*
			 * if (!random_login_key.toLowerCase().equals(
			 * random_login_key_.toLowerCase())) { errorCode = "00010012";
			 * request.setAttribute("errorCode", errorCode);
			 * this.callURI("index.jsp", request, response); return; }
			 */
			// --------enCode by MD5----------------------
			// MD5 md5 = new MD5();
			// pass = md5.enCodeByMD5(pass, user);
			pass = MD5impl.GetMD5Code(pass);
			// -------------------------------------------
			LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
			int result = loginDaoImpl.load(user, pass);
			if (result > 0) {
				if (loginDaoImpl.getType().equals("1856")) {
					oasession.setLoginID(new Integer(loginDaoImpl.getUserId()));
					oasession.setUserName(user);
					oasession.setCompanyCode(loginDaoImpl.getPid());
					session.setAttribute("oasession", oasession);
					// out.print("ok");
					String forward = arg0.getParameter("forward");
					// this.callURI(forward, arg0, arg1);
					Logger.getLogger(this.getClass().getName()).info(
							"login," + "ok");
					int a = loginDaoImpl.loadShopId(loginDaoImpl.getPid());
					if (a == 0) {
						oasession.setCompanyCode("");
						out.print("error_no_shop");
					} else {
						out.print("ok");
					}
				} else {
					out.print("error code:0002");
				}
			} else {
				out.print("error code:0001");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}
	}

}