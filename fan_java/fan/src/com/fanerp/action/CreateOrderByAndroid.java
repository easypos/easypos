package com.fanerp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;
import com.fan.print.PrintImpl;
import com.fan.session.FanSession;
import com.fanerp.util.StringAlign;

public class CreateOrderByAndroid extends Action {
	CommonboService boService;

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html; charset=gbk");
		String id = arg0.getParameter("id");
		String msg = "sucess!";
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			boService = (CommonboService) cxt.getBean("boService");
			HashMap base;
			if (id == null)
				id = "";
			if (id.equals("")) {
				base = createOrder(arg0);
				id = (String) base.get("id");
				String err = (String) base.get("err");
				Float discount = (float) 0.00;
				;
				if (err.equals("ok")) {
					// arg1.getWriter().println("抱歉!table is used");
					HashMap resultForDetail = loadOrderDetail((String) base
							.get("id"));
					// PrintImpl.printOrder((String) base.get("str"),
					// (String) resultForDetail.get("str"),
					// (Integer) resultForDetail.get("count"),
					// (Integer) resultForDetail.get("sum"),
					// FanSession.getPrintipforcook(),
					// FanSession.getPrintmore());
					// 折扣方案,买满送
					List discountSet = boService
							.getDao()
							.queryForList(
									"select money,discount from  fanorderbuydiscountset where status='1' order by money desc");
					Float sum = (Float) resultForDetail.get("sum");
					Float sum_ = (Float) resultForDetail.get("sum_");
					// discount = sum - sum_;??
					discount = sum - sum_;
					for (int i = 0; i < discountSet.size(); i++) {
						Map map = (Map) discountSet.get(i);
						int money_ = (Integer) map.get("money");
						int discount_ = (Integer) map.get("discount");
						if (sum >= money_) {
							discount = Float.valueOf(discount_);
							break;
						}
					}
				}
				base.put("discount", discount.toString());
				JSONArray jsonArray = JSONArray.fromObject(base);
				arg1.getWriter().println(jsonArray.toString());
				System.out.println(jsonArray.toString());
			} else {
				// 重打操作!!!
				String a = loadOrderBase(id);
				HashMap resultForDetail = loadOrderDetail(id);
				PrintImpl.printOrder(a, (String) resultForDetail.get("str"),
						(Integer) resultForDetail.get("count"),
						(Integer) resultForDetail.get("sum"),
						FanSession.getPrintipforcook(),
						FanSession.getPrintmore());
				arg1.getWriter().println("sucess!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			arg1.getWriter().println("error!");
		}
	}

	private HashMap createOrder(HttpServletRequest arg0) throws Exception {
		HashMap resultValue_ = new HashMap();
		String diningTableId = arg0.getParameter("diningtableid");
		String userCount = arg0.getParameter("usercount");
		// String sql = "select count(*) from fanhandover where operater='"
		// + this.oasession.getLoginID() + "' and type='0' and end_>";
		//
		if (decideSign() == 0) {
			resultValue_.put("str", "");
			resultValue_.put("id", "");			
			resultValue_.put("err", "请签到并提交预备金");
			return resultValue_;
		}
		String sql = "select count(*)   from FANDININGTABLE where tablenu='"
				+ diningTableId + "' and status='1' ";
		// CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		int result;
		if (diningTableId.equals("-")) {
			result = 1;
		} else {
			result = boService.getDao().queryForInt(sql);
		}
		if (result == 0) {
			// arg1.getWriter().println("抱歉!table is used");
			resultValue_.put("str", "");
			resultValue_.put("id", "");
			resultValue_.put("err", "抱歉!table is used");
			return resultValue_;
		} else {
			List<String> hideVars = new ArrayList<String>();
			boService.init("fanOrder", hideVars, null);
			boService.setSession((OASession) oasession);
			// if (id == null)
			// id = "";
			// if (id.equals(""))
			// id = new Integer(boService.maxId() + 1).toString();
			// boService.setParameterValues(RequestUtil.getParamValues(arg0));
			boService.setParameterValues(RequestUtil
					.getParamValuesByUtf_8(arg0));
			String tel = (String) boService.getParameterValues().get("tel");
			sql = "select count(*)   from fanmember where tel='" + tel + "'";
			result = boService.getDao().queryForInt(sql);
			if (result == 0) {
				boService.getParameterValues().put("tel", "-");
			}
			int count = new Integer((String) boService.getParameterValues()
					.get("Count"));
			for (int i = 1; i <= count; i++) {
				boService.getParameterValues().put("status" + i, "0");
				// System.out.print("count===========");
			}
			boService.getParameterValues();
			boService.getParameterValues().put("content", "");
			String id_ = com.f1jeeframework.workflow.impl.BpmIDGenerator
					.getID();
			HashMap resultValue = boService.add(id_, "fanOrder");
			// 台桌
			boService
					.update("update FANDININGTABLE set status='2' where tablenu='"
							+ diningTableId + "'");
			// 会员
			// this.setForward(false);
			LazyDynaBean main = (LazyDynaBean) resultValue.get("main");
			StringBuffer strBuff = new StringBuffer();
			strBuff.append("单号:");
			strBuff.append(main.get("ordernu"));
			strBuff.append("-");
			strBuff.append("开始:");
			String createddate = (String) main.get("createddate");
			strBuff.append(createddate);
			strBuff.append("\n");
			strBuff.append("台位:");
			strBuff.append(diningTableId);
			strBuff.append("-");
			strBuff.append("人数:");
			strBuff.append(userCount);
			strBuff.append("-");
			strBuff.append("服务:");
			strBuff.append(this.getOasession().getUserName());
			strBuff.append("\n");
			resultValue_.put("str", strBuff.toString());
			resultValue_.put("id", id_);
			resultValue_.put("err", "ok");
			resultValue_.put("createdDate", createddate);
			resultValue_.put("orderNu", main.get("ordernu"));
			resultValue_.put("tel", main.get("tel"));
			// 满n减?
		}
		return resultValue_;
	}

	private int decideSign() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int yy = cal.get(GregorianCalendar.YEAR);
		int mm = cal.get(GregorianCalendar.MONTH);
		mm = mm + 1;
		int dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
		// System.out.println(begin_);
		// System.out.println(end_);
		String begin_ = yy + "-" + mm + "-" + dd;
		String end_ = begin_ + " " + "23:00";
		String sql = "select count(*) from fanhandover where operater='"
				+ this.oasession.getUserName() + "' and type='0' and end_>'"
				+ begin_ + "' and end_<'" + end_ + "'";
		Logger.getLogger(this.getClass().getName()).info(sql);
		int result;
		result = boService.getDao().queryForInt(sql);		
		return result;
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

	public HashMap loadOrderDetail(String id) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a2.wordvalue,count(*)  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid left join T_INFORPUBDICT a2 on a1.type=a2.universalid   where a.pid='"
				+ id + "' group by  a2.wordvalue";
		List types = d.queryForList(sql);
		Float sum = (float) 0.00;
		Float sum_ = (float) 0.00;
		Float count = (float) 0.0;
		StringAlign formatter = new StringAlign(20, StringAlign.JUST_LEFT, true);
		StringBuffer order = new StringBuffer();
		for (int i = 0; i < types.size(); i++) {
			Map a = (Map) types.get(i);
			String type = (String) a.get("wordvalue");
			sql = "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a1.discount,a.status,a.note,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='"
					+ id + "' and  a2.wordvalue='" + type + "'";
			List dishes = boService.getDao().queryForList(sql);
			// order.append(type);
			order.append("======" + type + "======");
			order.append("\n");
			for (int j = 0; j < dishes.size(); j++) {
				Map dish = (Map) dishes.get(j);
				String name = (String) dish.get("name");
				Float dishcount = Float.valueOf((String) dish.get("dishcount"));
				String price = (String) dish.get("price");
				Float discount = Float.valueOf((String) dish.get("discount"));
				String note = (String) dish.get("note");
				sum = sum + Float.valueOf(price) * dishcount;
				sum_ = sum_ + discount * dishcount;
				count = count + dishcount;
				order.append(dishcount);
				order.append(" ");
				order.append(formatter.format(name.trim()));
				order.append(" ");
				order.append(price);
				order.append("\n");
				order.append(note);
				order.append("\n");
				// System.out.println("=====" + object.getString("name"));
				// System.out.println(object.getString("universalid"));
			}
		}

		HashMap resultValue = new HashMap();
		resultValue.put("str", order.toString());
		resultValue.put("count", count);
		resultValue.put("sum", sum);
		resultValue.put("sum_", sum_);
		return resultValue;
		// String sql1 =
		// "select a.universalid,a.dishid,a1.name as name,dishcount,a.price,a.status,a2.wordvalue  from fanorderitem a left join FANDISH a1 on a.dishid=a1.universalid  left join T_INFORPUBDICT a2 on a1.type=a2.universalid where a.pid='1357200598735'";

	}
}
