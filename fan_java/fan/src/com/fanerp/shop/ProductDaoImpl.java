package com.fanerp.shop;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class ProductDaoImpl {

	public ProductDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LazyDynaBean loadPrdCurrentInventory(String id) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select currentinventory from  t_product where universalid='"
				+ id + "'";
		//
		LazyDynaBean result = d.queryForObject(sql);
		return result;
	}

	/**
	 * 按条码刷新仓库回传库存
	 * 
	 * @param currentInventory
	 * @param barCode1
	 * @throws java.lang.Exception
	 */
	public void updatePrdCurrentInventory(String currentInventory,
			String barCode1) throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE t_product set currentInventory=? WHERE barCode1=?";
		d.getJdbcTemplate().update(sql,
				new Object[] { currentInventory, barCode1 });
	}

	/**
	 * 按条码刷新商品编码
	 * 
	 * @param currentInventory
	 * @param barCode1
	 * @throws java.lang.Exception
	 */
	public void updatePrdCode(String productCode, String barCode1)
			throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE t_product set product_code=? WHERE barCode1=?";
		d.getJdbcTemplate().update(sql, new Object[] { productCode, barCode1 });
	}

	/**
	 * 按条码刷新商品编码
	 * 
	 * @param currentInventory
	 * @param barCode1
	 * @throws java.lang.Exception
	 */
	public void updateSupply(String productCode, String barCode1)
			throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE t_product set sellsupplier=? WHERE barCode1=?";
		d.getJdbcTemplate().update(sql, new Object[] { productCode, barCode1 });
	}

	public String getSupplyId(String a) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		String result = "";
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		try {
			LazyDynaBean o = boService.getDao().queryForObject(
					"select universalid from sellsupplier where customerid='"
							+ a + "'");
			result = (String) o.get("universalid");
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return result;
	}

	/**
	 * 按条码刷新商品编码
	 * 
	 * @param currentInventory
	 * @param barCode1
	 * @throws java.lang.Exception
	 */
	public void updateLimitedStatus(String status, String id)
			throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE t_product set limitedStatus=? WHERE universalid=?";
		d.getJdbcTemplate().update(sql, new Object[] { status, id });
	}

	/**
	 * 
	 * @param status
	 * @param id
	 * @throws java.lang.Exception
	 */
	public void updateStatus(String status, String id)
			throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE fandish set status=? WHERE universalid=?";
		d.getJdbcTemplate().update(sql, new Object[] { status, id });
	}

	/**
	 * 
	 * @param status
	 * @param id
	 * @throws java.lang.Exception
	 */
	public void updateMarketStatus(String status, String id)
			throws java.lang.Exception {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "UPDATE fandish set marketstatus=? WHERE universalid=?";
		d.getJdbcTemplate().update(sql, new Object[] { status, id });
	}

	public LazyDynaBean loadPrd(String id) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select universalid,type from  t_product where universalid='"
				+ id + "'";
		LazyDynaBean result = d.queryForObject(sql);
		return result;
	}

	public java.util.List listPrd() throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select universalid,type from  t_product where validStatus='1'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List listPrdByShop(String shopId) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select productid from  uecun_shop_product where shopid='"
				+ shopId + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public int load(String shopId, String productId) {
		int r = 1;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "select universalid from  uecun_shop_product where shopid='"
					+ shopId + "' and  productid='" + productId + "'";
			java.util.List types = d.queryForList(sql);
			r = types.size();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}

	public boolean remove(String shopId, String productId) {
		boolean r = false;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "delete from  uecun_shop_product where shopid='"
					+ shopId + "' and  productid='" + productId + "'";
			d.update(sql);
			r = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}

	public boolean removeAll(String shopId) {
		boolean r = false;
		try {
			ApplicationContext cxt = AppSession.getApplicationContext();
			if (cxt == null) {
				cxt = new ClassPathXmlApplicationContext(
						"ApplicationContext.xml");
			}
			CommonDao d = (CommonDao) cxt.getBean("commonDao");
			String sql = "delete from  uecun_shop_product where shopid='"
					+ shopId + "'";
			d.update(sql);
			r = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}

	public java.util.List listShops() throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select universalid,name from  sellcompany where status='1'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List listShopsByPrd(String prdId) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select shopid from uecun_shop_product where productid='"
		// + prdId + "'";
		String sql = "select shopid,type from uecun_shop_product a  join t_product  prd  on  a.productid=prd.universalid "
				+ "  where productid='" + prdId + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List listProductsByShop(String shopId) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		String sql = "select universalid as productid from  fandish where companycode='"
				+ shopId + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public java.util.List listProductsByShopTuiJie(String shopId)
			throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		/*
		 * String sql =
		 * "select top 10  a.universalid,a1.universalid as a1universalid,a.breed,a.price_,a.price,a5.warehouseNo as a5warehouseNo,a6.wordvalue as"
		 * +
		 * " a6wordvalue,a.limited,a.currentinventory,a.safetystock ,tmp.shopid  from T_Product a"
		 * +
		 * " left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' and"
		 * +
		 * " a1.tablename='product' left join sellwarehouse a5 on a.warehouse=a5.universalid"
		 * + " left join T_InforPubDict a6 on a.trade_type=a6.universalid  join"
		 * +
		 * " uecun_shop_product tmp  on a.universalid=tmp.productid where tmp.shopid = '"
		 * + shopId + "'";
		 */

		String sql = "select top 10  a.universalid,a1.universalid as a1universalid,"
				+ "a.name,a.price,a.content,a.status,a.recommend,a.companycode as shopid   from FanDish a  left join  fandish_upload "
				+ "a1 on a.universalid=a1.dataid and a1.filetype='0' and a1.tablename='dish' "
				+ "join fancompany f on  a.companycode=f.pid where f.universalid ='"
				+ shopId + "'" + " and a.marketstatus='1' and a.status='1'";
		Logger.getLogger(this.getClass().getName()).info(sql);
		// String sql =
		// " select top 10 universalid,productid from  uecun_shop_product where shopid='"
		// + shopId + "'";
		java.util.List types = d.queryForList(sql);
		return types;
	}

	public void setSellCompany(String id, String status) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		try {
			String sql = "update sellcompany set status='" + status + "'"
					+ " where universalid='" + id + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			d.update(sql);
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).info(
					"店铺:" + id + "_set err!");
		}
		// return products;
	}

	public List loadPrdByOrderId(String id) throws SQLException {
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		CommonDao d = (CommonDao) cxt.getBean("commonDao");
		// String sql =
		// "select currentinventory from  t_product where universalid='"
		// + id + "'";
		String sql = "select prd.specfiction,prd.product_code,weight,barcode1,breed,tax_no,a.* from beigebulu_orderdetail a "
				+ " join t_product  prd on a.shangpin=prd.universalid   where pid='"
				+ id + "'";
		//
		List result = d.queryForList(sql);
		return result;
	}

}
