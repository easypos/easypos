package com.erp.chart;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class ProductSaleDividedMyShop extends Action {
	public void afterExcute(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=gb2312");
		String chartType = request.getParameter("chartType");
		String dataType = request.getParameter("dataType");
		String sql;
		if (dataType == null) {
			dataType = "a";
		}
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String top = "select  ";
		sql = top
				+ "a2.name as name,sum(convert(float, DISHCOUNT)*convert(float, a.price))  as sum1  from FANORDERITEM a"
				// + "  left"
				// + " join BEIGEBULU_ORDER a1 on a.pid=a1.universalid "
				+ " left join FANDISH a2 on "
				+ "	a.DISHID=a2.universalid  where a.COMPANYID = '"
				+ this.oasession.getCompanyCode() + "' group by  a2.name "
				+ "order by   sum1 desc";

		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		List types = commonDao.queryForList(sql);
		// String[][] types = this.getDataList(sql);
		// 查找客户类型
		HashMap hm = new HashMap();
		String results[][] = new String[types.size()][2];
		Double sum = (Double) 0.0;
		for (Integer i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i); // 查找客户列表
			// Logger.getLogger(this.getClass().getName()).info(sql);
			// results[i][0] = (String) map.get("name");
			Object a = map.get("sum1");
			if (a instanceof Integer) {
				Integer sum1 = (Integer) a;
				results[i][1] = sum1.toString();
			} else {
				Double sum1 = (Double) a;
				sum = sum + sum1;
				results[i][1] = sum1.toString();
			}
		}
		// this.setList(results);
		hm.put("data", results);
		request.setAttribute("hashMap", hm);
		ChartDao chartDao = new ChartDao();
		request.setAttribute("shopStatus",
				chartDao.loadShop(oasession.getCompanyCode()));
		request.setAttribute("allProductTop10",
				chartDao.getAllByTop10(oasession.getCompanyCode()));
		request.setAttribute("allTypeTop10",
				chartDao.getAllTypeByTop10(oasession.getCompanyCode()));
		//request.setAttribute("prdAll", chartDao.getPrdAllOK().toString());
		//request.setAttribute("prdMyShop",
		//		chartDao.getPrdMyShop(this.oasession.getCompanyCode())
		//				.toString());
		JSONArray jsonArray = JSONArray.fromObject(types);
		DecimalFormat df = new DecimalFormat("0.00");
		request.setAttribute("sum", df.format(sum));
		request.setAttribute("jsonData_", jsonArray.toString());
		// this.setPath("/chart/ProductSaleDivided.jsp");
		this.callURI("/chart/ProductSaleDivided.jsp", request, response);
	}

}
