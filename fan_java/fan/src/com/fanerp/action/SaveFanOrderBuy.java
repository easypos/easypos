package com.fanerp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.LazyDynaBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;
import com.fanerp.member.MemberDao;
import com.fanerp.util.StringAlign;

public class SaveFanOrderBuy extends Action {
	CommonboService boService;

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		try {
			String responseResult = "ok!";
			arg1.setContentType("text/html; charset=gbk");
			String id = arg0.getParameter("id");
			String tabNu = arg0.getParameter("tabNu");
			String buyType = arg0.getParameter("buytype");
			String memberTel = arg0.getParameter("memberTel");
			String phone_code = arg0.getParameter("telCode");
			String money = arg0.getParameter("money");
			// String userCount = arg0.getParameter("userCount");
			ApplicationContext cxt = AppSession.getApplicationContext();
			boService = (CommonboService) cxt.getBean("boService");
			List<String> hideVars = new ArrayList<String>();
			hideVars.add("ordernu");
			hideVars.add("operator");
			hideVars.add("createddate");
			hideVars.add("diningtableid");
			hideVars.add("content");
			hideVars.add("usercount");
			hideVars.add("tel");
			boService.init("fanOrder1", hideVars, null);
			boService.setSession((OASession) oasession);
			boService.setParameterValues(RequestUtil.getParamValues(arg0));
			MemberDao memberDao = new MemberDao();
			// 会员充值则扣款
			if (buyType.equals("4")) {
				HttpSession session = arg0.getSession(false);				
				String save_code = (String) session.getAttribute("phone_code");
				if (phone_code == null)
					phone_code = "-";
				if (!phone_code.equals(save_code)) {
					responseResult = " 短信验证码不对!";
					arg1.getWriter().print(responseResult);
					return;
				}
				LazyDynaBean fanMember = memberDao.loadMember(memberTel);
				Float sum = (float) 0.00;
				String recharge_ = (String) fanMember.get("recharge");
				sum = Float.valueOf(recharge_) - Float.valueOf(money);
				memberDao.reduce(memberTel, sum);
			}
			HashMap resultValue = boService.add(id, "fanOrder1");			
			boService
					.update("update FANDININGTABLE set status='0' where tablenu='"
							+ tabNu + "'");
			LazyDynaBean a = (LazyDynaBean) resultValue.get("detail1to1");
			// String money = (String) a.get("money");
			String change = (String) a.get("change");
			String createdate = "======结束:" + (String) a.get("createdate")
					+ "======\n";
			// String orderBase = loadOrderBase(id);
			// HashMap result = loadDataByType(id);
			// PrintImpl.printBuy(orderBase, (String) result.get("str"),
			// (Integer) result.get("count"), (Integer) result.get("sum"),
			// new Integer(money), new Integer(change), createdate,
			// FanSession.getPrintipforbuy(), FanSession.getPrintmore());
			// this.callURI("seeFanOrderBuy.do?id=" + id, arg0, arg1);
			this.setForward(false);
			arg1.getWriter().print(responseResult);
		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().print("error!");
		}
	}

	public String loadOrderBase(String id) throws SQLException {
		LazyDynaBean order_ = boService
				.getDao()
				.queryForObject(
						"select ordernu,createddate,usercount,diningtableid   from fanorder where universalid="
								+ "'" + id + "'");
		String ordernu = (String) order_.get("ordernu");
		String userCount = (String) order_.get("usercount");
		String createddate = (String) order_.get("createddate");
		String tabNu = (String) order_.get("diningtableid");
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("单号:");
		strBuff.append(ordernu);
		strBuff.append("-");
		strBuff.append("开始:");
		strBuff.append(createddate);
		strBuff.append("\n");
		strBuff.append("台位:");
		strBuff.append(tabNu);
		strBuff.append("-");
		strBuff.append("人数:");
		strBuff.append(userCount);
		strBuff.append("-");
		strBuff.append("服务:");
		strBuff.append(this.getOasession().getUserName());
		strBuff.append("\n");
		return strBuff.toString();
	}

	public HashMap loadData(String id) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		StringBuffer order = new StringBuffer();
		List dishes = boService
				.getDao()
				.queryForList(
						"select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status from fanorderitem a "
								+ "left join FANDISH a1"
								+ " on a.dishid=a1.universalid "
								+ " where a.pid=" + "'" + id + "'");
		int sum = 0;
		int count = 0;
		StringAlign formatter = new StringAlign(20, StringAlign.JUST_LEFT, true);
		for (int i = 0; i < dishes.size(); i++) {
			Map dish = (Map) dishes.get(i);
			String name = (String) dish.get("name");
			Integer dishcount = (Integer) dish.get("dishcount");
			Integer price = (Integer) dish.get("price");
			sum = sum + price * dishcount;
			count = count + dishcount;
			order.append(dishcount);
			order.append(" ");
			order.append(formatter.format(name.trim()));
			order.append(" ");
			order.append(price);
			order.append("\n");
			// System.out.println("=====" + object.getString("name"));
			// System.out.println(object.getString("universalid"));
		}
		HashMap resultValue = new HashMap();
		resultValue.put("str", order.toString());
		resultValue.put("count", count);
		resultValue.put("sum", sum);
		return resultValue;
	}

	@Test
	public void loadDataByTypeTest() {

		try {
			HashMap a = loadDataByType("1357200598735");
			System.out.print(a.get("str"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap loadDataByType(String id) throws SQLException {
		// ApplicationContext cxt = AppSession.getApplicationContext();
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a2.wordvalue,count(*)  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid left join T_INFORPUBDICT a2 on a1.type=a2.universalid   where a.pid='"
				+ id + "' group by  a2.wordvalue";
		List types = d.queryForList(sql);
		Float sum = (float) 0.00;
		;
		int count = 0;
		StringAlign formatter = new StringAlign(20, StringAlign.JUST_LEFT, true);
		StringBuffer order = new StringBuffer();
		for (int i = 0; i < types.size(); i++) {
			Map a = (Map) types.get(i);
			String type = (String) a.get("wordvalue");
			sql = "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='"
					+ id + "' and  a2.wordvalue='" + type + "'";
			List dishes = boService.getDao().queryForList(sql);
			order.append("======" + type + "======");
			order.append("\n");
			for (int j = 0; j < dishes.size(); j++) {
				Map dish = (Map) dishes.get(j);
				String name = (String) dish.get("name");
				Integer dishcount = (Integer) dish.get("dishcount");
				String price = (String) dish.get("price");
				sum = sum + Float.valueOf(price) * dishcount;
				count = count + dishcount;
				order.append(dishcount);
				order.append(" ");
				order.append(formatter.format(name.trim()));
				order.append(" ");
				order.append(price);
				order.append("\n");
				// System.out.println("=====" + object.getString("name"));
				// System.out.println(object.getString("universalid"));
			}
		}
		HashMap resultValue = new HashMap();
		resultValue.put("str", order.toString());
		resultValue.put("count", count);
		resultValue.put("sum", sum);
		return resultValue;
		// String sql1 =
		// "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='1357200598735'";

	}

}
