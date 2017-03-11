package com.fanerp.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f1jeeframework.http.Action;
import com.ysk.login.MD5impl;

public class LoadShop extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		String id = this.oasession.getCompanyCode();
		String action = arg0.getParameter("action");
		String md5Code = MD5impl.GetMD5Code(id + "easyn+");
		this.setForward(false);
		this.callURI(action + ".do" + "?id=" + id + "&code=" + md5Code, arg0,
				arg1);
	}
}
