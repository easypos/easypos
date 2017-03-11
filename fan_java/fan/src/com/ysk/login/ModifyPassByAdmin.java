package com.ysk.login;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f1jeeframework.http.Action;

public class ModifyPassByAdmin extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		PrintWriter out = arg1.getWriter();
		String err = "ok";
		String id = arg0.getParameter("id");
		String pass = arg0.getParameter("pass1");
		String pass_ = arg0.getParameter("pass2");

		if (!pass.equals(pass_)) {
			err = "两次输入的口令不同!";
			out.print(err);
		}
		ModifyPassDaoImpl modifyPassDaoImpl = new ModifyPassDaoImpl();
		boolean r = modifyPassDaoImpl.a(id, MD5impl.GetMD5Code(pass));
		if (r) {
			err = "ok";
		} else {
			err = "修改密码没有完成!";
		}
		this.setForward(false);
		out.print(err);
		// this.callURI(action + "?id=" + id, arg0, arg1);
	}
}
