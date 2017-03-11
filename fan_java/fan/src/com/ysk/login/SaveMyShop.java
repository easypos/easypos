package com.ysk.login;

import java.util.logging.Logger;

import com.f1jframework.eform.CommonboService;
import com.f1jframework.eform.action.ModifyForCommbo;

public class SaveMyShop extends ModifyForCommbo {

	@Override
	public boolean before() {
		// TODO Auto-generated method stub
		return super.before();		
	}

	@Override
	public void after(CommonboService boService, String id) {
		// TODO Auto-generated method stub
		// super.after(boService, id);
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
		try {
			loginDaoImpl.update(id, this.oasession.getLoginID().toString());
			Logger.getLogger(this.getClass().getName()).info(
					"pid," + id + "," + "userid,"
							+ this.oasession.getLoginID().toString());
			this.oasession.setCompanyCode(id);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

}
