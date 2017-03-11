package com.ysk.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.f1jeeframework.util.OASession;

/**
 * ÏµÍ³µÇÂ½
 */
public class LoginFromCAS extends com.f1jeeframework.http.SourceAction {

	public void afterSourceAction(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws IOException, ServletException {
		try {
			this.setForward(false);
			OASession oasession = new OASession();
			oasession.setLoginIP(request.getRemoteHost());
			String cssIndex = request.getParameter("mySel");
			oasession.setCssFile(cssIndex);
			HttpSession session = request.getSession(false);
			String user = (String) session
					.getAttribute("edu.yale.its.tp.cas.client.filter.user");
			oasession.setUserName(user);
			String sql = "select  user_id from t_user where user_name='" + user
					+ "'";
			String id = getDataList(sql)[0][0];
			oasession.setLoginID(new Integer(id));
			session.setAttribute("oasession", oasession);
			callURI("oasystem/system_frame.jsp", request, response);
		} catch (Exception e) {
			e.printStackTrace();
			this.setForward(true);
			this.callURI("/oasystem/syserror.jsp", request, response);
		}
	}
}