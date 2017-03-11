package com.fanerp.action;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.LazyDynaBean;

import com.f1jeeframework.http.Action;
import com.f1jframework.eform.CommonboService;
import com.fanerp.member.MemberDao;
import com.ysk.sms.PhoneApi;

public class SaveFanOrderBuyInit extends Action {
	CommonboService boService;

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		try {
			String responseResult = "ok";
			arg1.setContentType("text/html; charset=gbk");
			HttpSession session = arg0.getSession(false);
			String tel = arg0.getParameter("tel");
			String money = arg0.getParameter("money");
			MemberDao memberDao = new MemberDao();
			LazyDynaBean fanMember = memberDao.loadMember(tel);
			String recharge_ = (String) fanMember.get("recharge");
			// ÅÐ¶ÏÓà¶îÊÇ·ñ×ã¹»
			if (Float.valueOf(recharge_) > Float.valueOf(money)) {
				PhoneApi phoneApi = new PhoneApi();
				phoneApi.send(tel);
				Logger.getLogger(this.getClass().getName()).info(
						"send sms..." + tel);
				if (phoneApi.isResultOk()) {
					responseResult = "ok";
					session.setAttribute("phone_code",
							phoneApi.getMobile_code());
				} else {
					responseResult = "error!";
				}
			}
			this.setForward(false);
			arg1.getWriter().print(responseResult);
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().print("error!");
		}
	}
}
