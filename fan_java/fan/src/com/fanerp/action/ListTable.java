package com.fanerp.action;

import java.util.ArrayList;
import java.util.List;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.model.FormItemBean;
import com.f1jeeframework.view.ViewBuilder;
import com.f1jeeframework.view.ViewHandler;
import com.f1jeeframework.view.adapter.ViewAdpater;

/**
 * 视图控制器,一般采用JSP显示DATA，这里采用的是XML。
 */
public class ListTable extends Action {
	private String viewApp;
	private String catgory;
	private ViewBuilder builder = new ViewBuilder();

	public void afterExcute(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		String action = request.getServletPath();
		// get param
		// 判断是否在pageflow里面定义了list
		String list_ = (String) request.getAttribute("list_");
		if (list_ == null)
			list_ = "";
		if (!list_.equals("")) {
			viewApp = list_;
		} else {
			// 判断是否在form里面定义了list
			list_ = (String) request.getParameter("appName");
			if (list_ == null)
				list_ = "";
			if (!list_.equals("")) {
				viewApp = list_;
			} else {
				viewApp = request.getParameter("v");
			}
		}
		catgory = nullDo(request.getParameter("type"));
		builder.setHttpReq(request);
		String find = nullDo(request.getParameter("k"));
		builder.initView(viewApp, catgory, find);
		String page = request.getParameter("p");
		String rows = request.getParameter("r");
		if (page == null) {
			page = "1";
		}
		if (rows == null)
			rows = "20";
		// String sql =
		// "select  tablenu ,a.status,b.createddate from FANDININGTABLE a left join fanorder b on a.tablenu=b.DININGTABLEID and (b.status='2') where "
		// +
		// "DAY(createddate)=DAY(GETDATE()) and  Month(createddate)=Month(GETDATE())  and year(createddate)=year(GETDATE())"
		// + " and a.type='" + catgory + "'";
		String sql = "select a.universalid,tablenu ,a.status,b.createddate from FANDININGTABLE a "
				+ " left join (select  * from   fanorder  where DAY(createddate)=DAY(GETDATE()) and "
				+ " Month(createddate)=Month(GETDATE())"
				+ " and year(createddate)=year(GETDATE())"
				+ " and status='1') b on a.tablenu=b.DININGTABLEID where a.type='"+catgory+"'";
		// String sql =
		// "select  tablenu ,a.status,b.createddate from FANDININGTABLE a  where a.type='"
		// + catgory + "'";
		ViewHandler viewHandler = new ViewHandler(sql, oasession.getLoginID());
		List<FormItemBean> viewItems = new ArrayList<FormItemBean>();
		FormItemBean formItemBean = new FormItemBean();		
		formItemBean.setItemName("universalid");
		formItemBean.setTitle("id");
		formItemBean.setInList("1");
		viewItems.add(0, formItemBean);		
		formItemBean.setItemName("tablenu");
		formItemBean.setTitle("tablenu");
		formItemBean.setInList("1");
		viewItems.add(formItemBean);
		formItemBean = new FormItemBean();
		formItemBean.setItemName("status");
		formItemBean.setTitle("status");
		formItemBean.setInList("1");
		viewItems.add(formItemBean);
		formItemBean = new FormItemBean();
		formItemBean.setItemName("createddate");
		formItemBean.setTitle("createddate");
		formItemBean.setInList("1");
		formItemBean.setFormatType("4");
		viewItems.add(formItemBean);
		viewHandler.setLiName(action.substring(1, action.length()));
		viewHandler.setFindKey(find);
		viewHandler.setPageRows(new Integer(rows));
		viewHandler.setPageNum(new Integer(page));
		viewHandler.setViewApp(viewApp);
		viewHandler.setViewBean(builder.getViewBean());
		viewHandler.setViewItems(viewItems);
		viewHandler.setArg(request);
		viewHandler.handleView(false);
		// viewHandler.setViewAdpater(new ViewAdpater(viewHandler.getMap1(),
		// null));
		viewHandler.setViewAdpater(new ViewAdpater(viewHandler.getMap1(),
				viewHandler.getPagerService().getColumnCount(),null,null, this.getActionBean()));
		request.setAttribute("html", viewHandler.html());
		request.setAttribute("hashMap", viewHandler.getMap1());
		String forward = (String) request.getAttribute("actionForward_");
		if (forward == null)
			forward = "";
		if (forward.equals("")) {
			forward = "/include/list.jsp";
		}
		this.callURI(forward, request, response);

	}

	private String nullDo(String s) {
		if (s == null)
			s = "";
		return s;
	}
}