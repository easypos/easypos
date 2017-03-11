package com.fanerp.impl;

import com.f1jframework.eform.CommonboService;
import com.f1jframework.eform.IBoservceInerceptor;

public class OrderInterceptorImpl implements IBoservceInerceptor {
	@Override
	public boolean a(CommonboService arg0) {
		String id = arg0.getId();
		if (id == null)
			id = "";
		if (id.equals("")) return true;
		String sql = "select count(*) from fanorder a  where a.status='1' and  universalid='"
				+ arg0.getId() + "'";
		arg0.getDao().queryForList(sql);
		int count = arg0.getDao().queryForInt(sql);
		if (count == 0)
			return false;
		else
			return true;
	}

}
