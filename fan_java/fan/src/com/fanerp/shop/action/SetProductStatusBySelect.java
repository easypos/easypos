package com.fanerp.shop.action;

import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f1jeeframework.http.Action;
import com.fanerp.shop.ProductDaoImpl;

/**
 * 从产品列表选择删除
 * 
 * @author easyn+
 * 
 */
public class SetProductStatusBySelect extends Action {

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=GBK");
		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		String id = arg0.getParameter("id");
		String status = arg0.getParameter("status");
		String[] r = id.split(",");
		for (int i = 0; i < r.length; i++) {
			productDaoImpl.updateStatus(status, r[i]);
			// System.out.println(r[i]);
			Logger.getLogger(this.getClass().getName()).info(r[i]);
		}
		// this.callURI("a", arg0, arg1);
		PrintWriter out = arg1.getWriter();
		out.print("SUCCESS");
	}

}
