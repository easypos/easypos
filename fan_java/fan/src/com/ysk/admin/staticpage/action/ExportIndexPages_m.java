package com.ysk.admin.staticpage.action;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.tag.ParamBean;
import com.f1jeeframework.view.ViewBuilder;
import com.f1jframework.eform.CommonboService;
import com.f1jframework.eform.common.VelocityUtil;
import com.ysk.admin.staticpage.ProductListIndexDaoImpl;

/**
 * Export static pages for weixin!
 */
public class ExportIndexPages_m extends Action {
	private String viewApp;
	private String catgory;
	//private ViewBuilder builder = new ViewBulider();
	private List<ParamBean> params = new ArrayList<ParamBean>();
	CommonboService boService;

	public void afterExcute(javax.servlet.http.HttpServletRequest arg0,
			javax.servlet.http.HttpServletResponse arg1) throws Exception {
		arg1.setContentType("text/html;charset=GBK");
		// arg0.setCharacterEncoding("utf-8");
		ApplicationContext cxt = AppSession.getApplicationContext();
		boService = (CommonboService) cxt.getBean("boService");
		VelocityContext context = new VelocityContext();
		context.put("req", arg0);
		ServletContext servletContext = arg0.getSession().getServletContext();
		String contextPath = servletContext
				.getInitParameter("f1jeeContextPath");
		String mContextPath = servletContext.getInitParameter("mContextPath");
		String absoluteUploadPath = servletContext
				.getInitParameter("absoluteUploadPath");
		VelocityUtil velocityUtil = new VelocityUtil(context, contextPath);
		ProductListIndexDaoImpl productListIndexDaoImpl = new ProductListIndexDaoImpl();
		// jifen
		// cuxiao

		/*
		 * List<?> list_ = productListIndexDaoImpl.load("1718"); // List<?>
		 * list_food_types = productListIndexDaoImpl.types("1718"); // 食品
		 * context.put("food_middle", getViewBody(velocityUtil, list_,
		 * "rowData_middle", "hot.html")); context.put("foods", list_);
		 * 
		 * list_ = productListIndexDaoImpl.load("1721"); // list_food_types =
		 * productListIndexDaoImpl.types("1721"); context.put("viewName",
		 * "healthproduct"); // 饮料|酒水 context.put("jiu_middle",
		 * getViewBody(velocityUtil, list_, "rowData_middle", "hot.html"));
		 * context.put("jius", list_);
		 * 
		 * list_ = productListIndexDaoImpl.load("1719"); // list_food_types =
		 * productListIndexDaoImpl.types("1719"); // 保健品
		 * 
		 * context.put("baojianpin_middle", getViewBody(velocityUtil, list_,
		 * "rowData_middle", "hot.html")); context.put("baojianpins", list_);
		 * list_ = productListIndexDaoImpl.load("1720"); // list_food_types =
		 * productListIndexDaoImpl.types("1720"); // 个人护理
		 * context.put("gerehuli_middle", getViewBody(velocityUtil, list_,
		 * "rowData_middle", "hot.html")); context.put("gerenhulis", list_);
		 */
		// 买一送一
		List<?> list_ = productListIndexDaoImpl.listShopForMarket();
		context.put("shop",
				getViewBody(velocityUtil, list_, "rowData", "shop_grid_m.html"));
		/*
		 * list_ = productListIndexDaoImpl.listPrdForMarket("2"); context.put(
		 * "buy1reward1", getViewBody(velocityUtil, list_, "rowData",
		 * "product_grid_m.html"));
		 */
		// 首页推荐
		list_ = productListIndexDaoImpl.listPrdForMarket("3");
		context.put(
				"recommend",
				getViewBody(velocityUtil, list_, "rowData",
						"product_grid_m.html"));
		// viewHandler.type();
		// context.put("htmlFile", "/view/listdefault.vm");
		// context.put("fid", fid);
		// context.put("id", id);

		// context.put("workItem", arg0.getAttribute("workItem"));
		// arg0.getRealPath("");
		// ar
		// arg0.getPathTranslated();
		String path = this.getActionBean().getForward();
		// a(arg0, arg1, velocityUtil);
		// arg1.getWriter().print(
		// velocityUtil.code("/layout/index.html", contextPath));
		String content = velocityUtil.code("/layout/index_m.html", contextPath);
		String file = mContextPath + "/index.html";
		Logger.getLogger(this.getClass().getName()).info("save..." + file);
		FileWriter fw = new FileWriter(file);
		// BufferedWriter bw = new BufferedWriter(fw);
		// utf-8
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), Charset.forName("UTF-8")));
		// bw.newLine();
		bw.write(content);
		bw.close();
		fw.close();
		/*
		 * file = "D:/Apache22/htdocs/index.html";
		 * Logger.getLogger(this.getClass().getName()).info("save..." + file);
		 * fw = new FileWriter(file); // BufferedWriter bw = new
		 * BufferedWriter(fw); // utf-8 bw = new BufferedWriter(new
		 * OutputStreamWriter(new FileOutputStream( file),
		 * Charset.forName("UTF-8"))); // bw.newLine(); bw.write(content);
		 * bw.close(); fw.close();
		 */
		this.setForward(false);
		this.callURI(mContextPath + "/index.html", arg0, arg1);
	}

	public String getViewBody(VelocityUtil velocityUtil, List<?> list_,
			String param, String adapter) {
		StringBuffer str_ = new StringBuffer();
		for (int i = 0; i < list_.size(); i++) {
			Map<?, ?> data = (Map<?, ?>) list_.get(i);
			str_.append(getViewForRowByTempl(velocityUtil, data, i, param,
					adapter));
		}
		return str_.toString();
	}

	public String getViewForRowByTempl(VelocityUtil velocityUtil,
			Map<?, ?> data, int i, String param, String adapter) {
		velocityUtil.getContext().put(param, data);
		velocityUtil.getContext().put("rowCount", i);
		return (velocityUtil.code("/layout/adapter/" + adapter,
				velocityUtil.getPath()));

	}

}