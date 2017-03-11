package com.ysk.admin.staticpage;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class ProductListIndexDaoImpl {

	public ProductListIndexDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public java.util.List load(String type) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select top 10 a.universalid,a1.universalid as a1universalid,a.breed,a.price  from  t_product a"
		// +
		// " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='product'"
		// + " where catalog1='" + type + "'";
		String sql = "select a.universalid,a1.universalid as a1universalid,"
				+ " a.specfiction,a.breed,a4.name as a4name,a.price,a.barcode1,a7.itemvalue,"
				+ " a.pvcid from T_Product a  left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid "
				+ " and a1.filetype='0' and a1.tablename='product' left join sellbrand a4"
				+ " on a.brand=a4.universalid left join (select itemcode,itemvalue  from t_form_item "
				+ " join t_form_item_dict on  t_form_item.universalid=t_form_item_dict.itemid"
				+ " where sname='T_Product' and  sitem='sell_type') a7 on a.sell_type=a7.itemcode "
				+ " where catalog1='" + type + "'";
		;
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List loadByStatus(String type) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select top 10 a.universalid,a1.universalid as a1universalid,a.breed,a.price  from  t_product a"
		// +
		// " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='product'"
		// + " where catalog1='" + type + "'";
		String sql = "select a.universalid,a1.universalid as a1universalid,"
				+ " a.specfiction,a.breed,a4.name as a4name,a.price,a.barcode1,a7.itemvalue,"
				+ " a.pvcid from T_Product a  left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid "
				+ " and a1.filetype='0' and a1.tablename='product' left join sellbrand a4"
				+ " on a.brand=a4.universalid left join (select itemcode,itemvalue  from t_form_item "
				+ " join t_form_item_dict on  t_form_item.universalid=t_form_item_dict.itemid"
				+ " where sname='T_Product' and  sitem='sell_type') a7 on a.sell_type=a7.itemcode "
				+ " where sell_type='" + type + "'";
		;
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List types(String type) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select top 10 a.universalid,a1.universalid as a1universalid,a.breed,a.price  from  t_product a"
		// +
		// " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='product'"
		// + " where catalog1='" + type + "'";
		String sql = "select universalid ,name from sellproducttype  a   where a.pid='"
				+ type + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List brands(String type) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select top 10 a.universalid,a1.universalid as a1universalid,a.breed,a.price  from  t_product a"
		// +
		// " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='product'"
		// + " where catalog1='" + type + "'";
		String sql = "select universalid ,name from sellbrand  a   where a.type='"
				+ type + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	/*
	 * for weixin
	 * 
	 * @date da2015-05-31
	 */
	/**
	 * 
	 * @param type
	 * @return
	 * @throws SQLException
	 */

	public java.util.List listPrdForMarket(String status) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a.universalid,a1.universalid as a1universalid,"
				+ "a.price,a.name,a.companycode,a6.wordvalue as"
				+ " a6wordvalue  from fandish a  left join"
				+ " T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='dish'  left"
				+ " join T_InforPubDict a6 on a.type=a6.universalid  where a.status = '1' and"
				+ " a.marketstatus = '1'";
		;
		java.util.List types = d.queryForList(sql);
		return types;
	}

	/*
	 * for weixin
	 * 
	 * @date da2015-05-31
	 */
	/**
	 * 
	 * @param type
	 * @return
	 * @throws SQLException
	 */

	public java.util.List listShopForMarket() throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select a.universalid,a1.universalid as a1universalid, "
				+ " a.name,a.code,a.address,a.tel,a.district   from fancompany a "
				+ " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and "
				+ " a1.tablename='fancompany'  where  a.marketstatus = '1'";
		java.util.List types = d.queryForList(sql);
		return types;
	}
}
