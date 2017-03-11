package com.fanerp.impl;

import com.f1jeeframework.view.IViewSqlCreate;
import com.f1jeeframework.view.ViewContext;

public class ReportSqlImpl implements IViewSqlCreate {
	@Override
	public String getSql(ViewContext arg0) throws Exception {

		String begin_ = arg0.getHttpReq().getParameter("begin_");
		String end_ = arg0.getHttpReq().getParameter("end_");        
		return " where CREATEDATE<'" +end_+"' and CREATEDATE>'"+begin_+"'";
	}

}
