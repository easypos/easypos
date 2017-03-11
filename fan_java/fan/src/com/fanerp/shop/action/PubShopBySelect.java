package com.fanerp.shop.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.PageFlowConfig;
import com.f1jeeframework.model.FormItemBean;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;
import com.f1jframework.eform.common.VelocityUtil;
import com.fanerp.shop.ProductDaoImpl;

public class PubShopBySelect extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg1.setContentType("text/html;charset=GBK");
		ApplicationContext cxt = AppSession.getApplicationContext();
		ServletContext servletContext = arg0.getSession().getServletContext();
		String contextPath = servletContext
				.getInitParameter("f1jeeContextPath");
		String mContextPath = servletContext
				.getInitParameter("absoluteUploadPath");
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		List<FormItemBean> beans = new ArrayList<FormItemBean>();
		FormItemBean formItemBean = new FormItemBean();
		formItemBean.setItemName("name");
		formItemBean.setType("0");
		beans.add(formItemBean);
		formItemBean = new FormItemBean();
		formItemBean.setItemName("content");
		formItemBean.setType("3");
		beans.add(formItemBean);
		PageFlowConfig read = (PageFlowConfig) cxt.getBean("pageFlowConfig");
		// RequestUtil requestUtil = new RequestUtil();
		// String path = requestUtil.getPath(arg0);
		// ActionBean actionBean = (ActionBean) read.getActionMap().get(path);
		ProductDaoImpl productListDaoImpl = new ProductDaoImpl();
		String ids = arg0.getParameter("id");
		String[] r = ids.split(",");
		// 生成店铺
		for (int i = 0; i < r.length; i++) {
			String shopId = r[i];
			// productListDaoImpl.setSellCompany(shopId, "1");
			String id = r[i];
			Logger.getLogger(this.getClass().getName()).info(
					"save shop..." + shopId + ".html");

			// String fid;
			// String id = arg0.getParameter("id");
			if (id == null)
				id = "";
			String fid = "fancompany";
			boService.init(fid, null, null);
			if (oasession == null) {
				oasession = new OASession();
				oasession.setLoginID(0);
				oasession.setUserName("guest");
			}
			boService.setSession((OASession) oasession);
			String templName = boService.getFormBean().getTemplFile();
			LazyDynaBean bean = null;
			// 获得模
			if (!templName.equals("-")) {
				bean = boService.load("name", templName, "eformtempl", beans);
			}
			VelocityContext context = new VelocityContext();
			// String templName = boService.getFormBean().getTemplFile();
			// HashMap<String,?> Varables=new HashMap();
			// arg0.getp
			boService.setId(id);
			if (boService.loadInterceptor()) {
				boService.load(id, arg0, context, fid);
				context.put("htmlFile", "/eform/"
						+ this.getActionBean().getParam1());
				String layoutAdapter = this.getActionBean().getLayoutAdapter();
				if (layoutAdapter != null) {
					context.put("layoutAdapter", "/eform/layoutadapter/"
							+ this.getActionBean().getLayoutAdapter());
				} else {

				}
				context.put("fid", fid);
				context.put("id", id);
				context.put("req", arg0);
				VelocityUtil velocityUtil = new VelocityUtil(context);
				context.put("androidCode", boService.getMainBeans());
				String title = this.getActionBean().getTitle();
				context.put("title", title);
				String location = this.getActionBean().getLocation();
				context.put("location", location);
				List listProductsByShopTuiJie = productListDaoImpl
						.listProductsByShopTuiJie(shopId);

				context.put("tujieProduct", this.getViewBody(velocityUtil,
						listProductsByShopTuiJie, "rowData", ""));
				String param2 = this.getActionBean().getParam2();
				if (param2 == null)
					param2 = "";
				if (param2.equals("")) {
					arg1.getWriter().print(
							velocityUtil.code("/eform/form.vm", contextPath));
				} else {
					String content = velocityUtil.code("/eform/" + param2,
							contextPath);

					// String file = "D:/uecun/webapps/meilin_m/product/detail/"
					// + id + ".html";
					String file = mContextPath + "/fancompany/detail/" + id
							+ ".html";
					FileWriter fw = new FileWriter(file);
					// BufferedWriter bw = new BufferedWriter(fw);
					// utf-8
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(file),
									Charset.forName("UTF-8")));
					// bw.newLine();
					bw.write(content);
					bw.close();
					fw.close();

				}
				JSONArray jsonArray = JSONArray.fromObject(context
						.get("workItem"));
				// System.out.print(jsonArray.toString());
			} else {
				arg0.setAttribute("actionForward_", null);
				this.callURI("/msg/msgorder.jsp", arg0, arg1);
			}

			// 生成店铺
			/*
			 * List productList = productListDaoImpl.listProductsByShop(shopId);
			 * for (int j = 0; j < productList.size(); j++) { Map map = (Map)
			 * productList.get(j); Integer a = (Integer) map.get("productid");
			 * id=a.toString(); String type = (String) map.get("type"); String
			 * type2Id_ = "-", type1Id_ = "-", type1Name = "-", type2Name = "-",
			 * type3Name = "-"; try { // ProductSetCatalogDaoImpl
			 * productSetCatalogDaoImpl = new // ProductSetCatalogDaoImpl(); //
			 * type2Id_ = productSetCatalogDaoImpl.type2Id(type); // type1Id_ =
			 * productSetCatalogDaoImpl.type1Id(type2Id_); // type1Name =
			 * productSetCatalogDaoImpl.type1Name(type1Id_); // type2Name =
			 * productSetCatalogDaoImpl.type2Name(type2Id_); // type3Name =
			 * productSetCatalogDaoImpl.type3Name(type); } catch (Exception ex)
			 * {
			 * 
			 * } Logger.getLogger(this.getClass().getName()).info(
			 * "save product..." + id + ".html"); // beans = new
			 * ArrayList<FormItemBean>(); // formItemBean = new FormItemBean();
			 * // formItemBean.setItemName("name"); //
			 * formItemBean.setType("0"); // beans.add(formItemBean); //
			 * formItemBean = new FormItemBean(); //
			 * formItemBean.setItemName("content"); //
			 * formItemBean.setType("3"); // beans.add(formItemBean); // String
			 * fid; // String id = arg0.getParameter("id"); if (id == null) id =
			 * ""; fid = "dish"; boService.init("dish", null, null); if
			 * (oasession == null) { oasession = new OASession();
			 * oasession.setLoginID(0); oasession.setUserName("guest"); }
			 * boService.setSession((OASession) oasession); // String templName
			 * = boService.getFormBean().getTemplFile(); // LazyDynaBean bean =
			 * null; // 获得模 if (!templName.equals("-")) { // bean =
			 * boService.load("name", templName, "eformtempl", // Beans); }
			 * context = new VelocityContext(); // context.put("type1Name",
			 * type1Name); // context.put("type2Name", type2Name); //
			 * context.put("type3Name", type3Name); // context.put("type1Id",
			 * type1Id_); // context.put("type2Id", type2Id_); //
			 * context.put("type3Id", type); // String templName =
			 * boService.getFormBean().getTemplFile(); // HashMap<String,?>
			 * Varables=new HashMap(); // arg0.getp boService.setId(id); if
			 * (boService.loadInterceptor()) { boService.load(id, arg0, context,
			 * fid); context.put("htmlFile", "/eform/" + "dishseehi_m.html");
			 * String layoutAdapter = this.getActionBean() .getLayoutAdapter();
			 * if (layoutAdapter != null) { context.put("layoutAdapter",
			 * "/eform/layoutadapter/" +
			 * this.getActionBean().getLayoutAdapter()); } else {
			 * 
			 * } context.put("shopId", shopId); context.put("fid", fid);
			 * context.put("id", id); context.put("req", arg0); //
			 * context.put("workItem", arg0.getAttribute("workItem")); //
			 * arg0.getRealPath(""); // ar // arg0.getPathTranslated();
			 * VelocityUtil velocityUtil = new VelocityUtil(context);
			 * context.put("androidCode", boService.getMainBeans()); String
			 * title = this.getActionBean().getTitle(); context.put("title",
			 * title); String location = this.getActionBean().getLocation();
			 * context.put("location", location); String param2 =
			 * this.getActionBean().getParam2();
			 * 
			 * // "productseehi_m_shop.html" if (param2 == null) param2 = ""; if
			 * (param2.equals("")) { arg1.getWriter().print( velocityUtil
			 * .code("/eform/form.vm", contextPath)); } else { String content =
			 * velocityUtil.code("/eform/" + param2, contextPath); File file_ =
			 * new File(mContextPath + "/fancompany/" + shopId); if
			 * (!file_.exists()) file_.mkdir(); String file = mContextPath +
			 * "/fancompany/" + shopId + "/" + id + ".html"; FileWriter fw = new
			 * FileWriter(file); // BufferedWriter bw = new BufferedWriter(fw);
			 * // utf-8 BufferedWriter bw = new BufferedWriter( new
			 * OutputStreamWriter(new FileOutputStream( file),
			 * Charset.forName("UTF-8"))); // bw.newLine(); bw.write(content);
			 * bw.close(); fw.close(); } JSONArray jsonArray =
			 * JSONArray.fromObject(context .get("workItem")); //
			 * System.out.print(jsonArray.toString()); } else {
			 * arg0.setAttribute("actionForward_", null);
			 * this.callURI("/msg/msgorder.jsp", arg0, arg1); } }
			 */

		}

		// arg1.getWriter().print("ExportIndexPages OK!");
		// this.callURI("a", arg0, arg1);
		// PrintWriter out = arg1.getWriter();
		// out.print("SUCCESS");
	}

	public String getViewBody(VelocityUtil velocityUtil, List<?> list_,
			String param, String adapter) {
		StringBuffer str_ = new StringBuffer();
		for (int i = 0; i < list_.size(); i++) {
			Map<?, ?> data = (Map<?, ?>) list_.get(i);
			str_.append(getViewForRowByTempl(velocityUtil, data, i, param,
					"product_grid_forshop.html"));
		}
		//Logger.getLogger(this.getClass().getName()).info(
		//		"tuijie..." + str_.toString());
		return str_.toString();
	}

	public String getViewForRowByTempl(VelocityUtil velocityUtil,
			Map<?, ?> data, int i, String param, String adapter) {
		velocityUtil.getContext().put(param, data);
		velocityUtil.getContext().put("rowCount", i + 1);
		return (velocityUtil.code("/view/adapter/" + adapter,
				velocityUtil.getPath()));

	}

}
