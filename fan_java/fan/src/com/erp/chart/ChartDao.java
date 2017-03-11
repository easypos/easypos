package com.erp.chart;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;
import com.f1jframework.eform.CommonboService;

public class ChartDao {

	public String loadShop(String shopId) {
		ApplicationContext cxt = AppSession.getApplicationContext();		
		String sql = "select  status from fancompany where universalid='"
				+ shopId + "'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		LazyDynaBean a;
		String status = "0";
		try {
			a = commonDao.queryForObject(sql);
			status = (String) a.get("status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status.equals("0")) {
			status = "待发布";
		} else {
			status = "已发布";
		}
		return status;
	}

	public HashMap getAllByTop10() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String top = "select top 10 ";
		String sql = top
				+ "a2.name as name,sum(convert(int, DISHCOUNT))  as sum1  from FANORDERITEM a"
				+ " left join fandish a2 on "
				+ "	a.FANORDERITEM=a2.universalid   group by  a2.name "
				+ " order by   sum1 desc";
		sql = "select a.name as name,top_.sum1,top_.id from  fandish  a "
				+ " join (select top 10 prd.universalid as id,sum(convert(int, dishcount))  as sum1  from FANORDERITEM bege_order 	 left join fandish prd on bege_order.dishid=prd.universalid   group by  prd.universalid  order by   sum1 desc) top_ "
				+ " on a.universalid =top_.id";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		List types = commonDao.queryForList(sql);
		// String[][] types = this.getDataList(sql);
		// 查找客户类型
		HashMap hm = new HashMap();
		String results[][] = new String[types.size()][3];
		for (Integer i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i); // 查找客户列表
			// Logger.getLogger(this.getClass().getName()).info(sql);
			results[i][0] = (String) map.get("name");
			Object a = map.get("sum1");
			if (a instanceof Integer) {
				Integer sum1 = (Integer) a;
				results[i][1] = sum1.toString();
			} else {
				Double sum1 = (Double) a;
				results[i][1] = sum1.toString();
			}
			results[i][2] = ((Integer) map.get("id")).toString();
		}
		hm.put("data", results);
		return hm;
	}

	public HashMap getAllByTop10(String shopId) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String top = "select top 10 ";
		String sql = top
				+ "a2.name as name,sum(convert(float, DISHCOUNT))  as sum1  from FANORDERITEM a"
				+ " left join fandish a2 on "
				+ "	a.FANORDERITEM=a2.universalid   group by  a2.name "
				+ " order by   sum1 desc";
		sql = "select a.name as name,top_.sum1,top_.id from  fandish  a "
				+ " join (select top 10 prd.universalid as id,sum(convert(float, dishcount))  as sum1  from FANORDERITEM bege_order   left join fandish prd on bege_order.dishid=prd.universalid  and bege_order.companyid='"
				+ shopId
				+ "'  group by  prd.universalid  order by   sum1 desc) top_ "
				+ " on a.universalid =top_.id";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		List types = commonDao.queryForList(sql);
		// String[][] types = this.getDataList(sql);
		// 查找客户类型
		HashMap hm = new HashMap();
		String results[][] = new String[types.size()][3];
		for (Integer i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i); // 查找客户列表
			// Logger.getLogger(this.getClass().getName()).info(sql);
			results[i][0] = (String) map.get("name");
			Object a = map.get("sum1");
			if (a instanceof Integer) {
				Integer sum1 = (Integer) a;
				results[i][1] = sum1.toString();
			} else {
				Double sum1 = (Double) a;
				results[i][1] = sum1.toString();
			}
			results[i][2] = ((String) map.get("id")).toString();
		}
		hm.put("data", results);
		return hm;
	}

	public HashMap getAllTypeByTop10() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String top = "select top 10 ";
		String sql = top
				+ " a3.wordvalue  as name ,sum(convert(int, dishcount))  as sum1  from FANORDERITEM a   "
				+ "left join fandish a2 on a.dishid=a2.universalid  "
				+ "left join T_INFORPUBDICT a3  on a2.type=a3.universalid   "
				+ "        group by  a3.name " + "order by   sum1 desc";

		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		List types = commonDao.queryForList(sql);
		// String[][] types = this.getDataList(sql);
		// 查找客户类型
		HashMap hm = new HashMap();
		String results[][] = new String[types.size()][2];
		for (Integer i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i); // 查找客户列表
			// Logger.getLogger(this.getClass().getName()).info(sql);
			results[i][0] = (String) map.get("name");
			Object a = map.get("sum1");
			if (a instanceof Integer) {
				Integer sum1 = (Integer) a;
				results[i][1] = sum1.toString();
			} else {
				Double sum1 = (Double) a;
				results[i][1] = sum1.toString();
			}
			// results[i][2] = ((Integer) map.get("id")).toString();
		}
		hm.put("data", results);
		return hm;
	}

	public HashMap getAllTypeByTop10(String shopId) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String top = "select top 10 ";
		String sql = top
				+ " a3.wordvalue  as name ,sum(convert(float, dishcount))  as sum1  from FANORDERITEM a   "
				+ "left join fandish a2 on a.dishid=a2.universalid  and a.companyid='"
				+ shopId + "' "
				+ "left join T_INFORPUBDICT a3  on a2.type=a3.universalid   "
				+ "        group by  a3.wordvalue " + "order by   sum1 desc";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		List types = commonDao.queryForList(sql);
		// String[][] types = this.getDataList(sql);
		// 查找客户类型
		HashMap hm = new HashMap();
		String results[][] = new String[types.size()][2];
		for (Integer i = 0; i < types.size(); i++) {
			Map map = (Map) types.get(i); // 查找客户列表
			// Logger.getLogger(this.getClass().getName()).info(sql);
			results[i][0] = (String) map.get("name");
			Object a = map.get("sum1");
			if (a instanceof Integer) {
				Integer sum1 = (Integer) a;
				results[i][1] = sum1.toString();
			} else {
				Double sum1 = (Double) a;
				results[i][1] = sum1.toString();
			}
			// results[i][2] = ((Integer) map.get("id")).toString();
		}
		hm.put("data", results);
		return hm;
	}

	public List getShopTop10() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String sql = "select  "
				+ "a2.name as name,sum(convert(int, amount)*convert(float, a.price))  as sum1  from beigebulu_orderdetail a"
				// + "  left"
				// + " join BEIGEBULU_ORDER a1 on a.pid=a1.universalid "
				+ " join sellcompany a2 on  "
				+ "	a.shopid=a2.universalid   group by  a2.name "
				+ "order by   sum1 desc";
		return commonDao.queryForList(sql);
	}

	public String getPrdMyShop(String id) {
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String sql = "select count(*) as count1  from T_Product a join uecun_shop_product tmp  on "
				+ " a.universalid=tmp.productid where tmp.shopid = '"
				+ id
				+ "' and  validStatus='1'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getSupplyAll() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		// CommonboService boService = (CommonboService)
		// cxt.getBean("boService");
		String sql = "select  count(*) as count1 from sellsupplier where status='0'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getSupplyAllOK() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		// CommonboService boService = (CommonboService)
		// cxt.getBean("boService");
		String sql = "select  count(*) as count1 from sellsupplier where status='1'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getPrdAll() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		// CommonboService boService = (CommonboService)
		// cxt.getBean("boService");
		String sql = "select  count(*) as count1 from t_product where validStatus='0'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getPrdAllOK() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		// CommonboService boService = (CommonboService)
		// cxt.getBean("boService");
		String sql = "select  count(*) as count1 from t_product where validStatus='1'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getShopAll() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		String sql = "select  count(*) as count1 from sellcompany where status='0'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public String getShopAllOK() {
		ApplicationContext cxt = AppSession.getApplicationContext();
		String sql = "select  count(*) as count1 from sellcompany where status='1'";
		CommonDao commonDao = (CommonDao) cxt.getBean("commonDao");
		String r = "0";
		try {
			LazyDynaBean a = commonDao.queryForObject(sql);
			r = (String) a.get("count1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

}
