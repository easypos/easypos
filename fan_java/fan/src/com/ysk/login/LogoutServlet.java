package com.ysk.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.util.OASession;

/**
* 系统重新登陆
*/
public class LogoutServlet extends Action  {

/*****************************************************************************
  * Process incoming requests for information
  *
  * @param request Object that encapsulates the request to the servlet
  * @param response Object that encapsulates the response from the servlet
  */
public void afterExcute(
	javax.servlet.http.HttpServletRequest request,
	javax.servlet.http.HttpServletResponse response)
	throws IOException, ServletException {
	try
	{   
		
		
		String lang = "en";
		String ent_code="";
		String login_no="";
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			OASession oasession =
				(OASession) session.getAttribute("oasession");
			if (oasession != null)
			{
				lang = oasession.getLanguage();
				ent_code=oasession.getEntCode();
				login_no=oasession.getLoginNO();                
				session.invalidate();
			}
		}
		request.setAttribute("lang",lang);
		request.setAttribute("ent_code",ent_code);
		request.setAttribute("login_no",login_no);
		//callPageNamed(lang + "_LogoutInfo", request, response);
		//callDefaultPage(request,response);
		String forward=request.getParameter("forward");
        if (forward==null)
        	forward="/index.jsp";
        callURI(forward, request, response);
	}

	catch (Exception e)
	{
		request.setAttribute("e", e);
		//callURI("/ibsales/error.jsp", request, response);
	}
}
/*****************************************************************************
* Process incoming HTTP GET requests
*
* @param request Object that encapsulates the request to the servlet
* @param response Object that encapsulates the response from the servlet
*/
public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {
	afterExcute(request, response);
}
  public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)throws IOException,ServletException
  {
	afterExcute(request,response);
  }
}