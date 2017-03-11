package com.ysk.report.action;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jframework.eform.CommonDao;

public class CreateOrderBuy {

	private static final String[] titles = { "����ID", "������", "����", "����", "��ʱ��",
			"����", "��������", "��Ǯ", "�ۿ�" };

	public static Date str2Date(String str, String format) {
		if (null != str && !"".equals(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Test
	public void test() throws Exception {
		CreateOrderBuy a = new CreateOrderBuy();
		HSSFWorkbook wb = a.getHSSFWorkbook("1", "", "");
		try {
			FileOutputStream fout = new FileOutputStream("d:/poitest/test1.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HSSFWorkbook getHSSFWorkbook(String id, String begin_, String end_)
			throws Exception {
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("���׼�¼");
		sheet.setColumnWidth(0, 50 * 256);
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // ����һ�����и�ʽ
		HSSFCell cell = row.createCell((short) 0);
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellStyle(style);
			cell.setCellValue(titles[i]);
		}
		ApplicationContext cxt = AppSession.getApplicationContext();
		if (cxt == null) {
			cxt = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		System.out.println("��������___��̨��ʼ��������");
		CommonDao d = (CommonDao) cxt.getBean("commonDao");

		// String sql = "select a.universalid,a1.universalid as a1universalid,"
		// +
		// " a.breed as name,barcode1,a.price_,a.price,a5.warehouseNo as a5warehouseNo,a6.wordvalue as a6wordvalue,"
		// +
		// " a.limited,a.currentinventory,a.safetystock   from T_Product a  left join  T_UPLOADPHOTO a1 on a.universalid=a1.dataid and a1.filetype='0' "
		// +
		// " and a1.tablename='product' left join sellwarehouse a5 on a.warehouse=a5.universalid"
		// +
		// " left join T_InforPubDict a6 on a.trade_type=a6.universalid  where a.validStatus = '1' and warehouse='"
		// + whId + "'";
		StringBuffer str = new StringBuffer();
		String sql = "select a.universalid,a1.ordernu as a1ordernu,a.companyid,a.change,a.createdate,a.operator,a6.itemvalue as a6itemvalue,a.money,"
				+ " a.discount   from fanorderbuy a  left join fanorder a1 on a.pid=a1.universalid"
				+ " left join(select itemcode,itemvalue  from t_form_item   join t_form_item_dict on  t_form_item.universalid=t_form_item_dict.itemid   where sname='fanorderbuy' and  sitem='buytype') a6 on a.buytype=a6.itemcode  where 0=0"
				+ "and(createdate>='"
				+ begin_
				+ "') and (createdate<='"
				+ end_
				+ "') ";
		str.append(sql);
		if (!id.equals("")) {
			str.append(" and (a.companyid='" + id + "')");
		}
		str.append("  order by a.createdate desc");
		// System.out.println(sql);
		// rows++;
		int rows = 1;
		List orderList = d.queryForList(sql);
		for (int j = 0; j < orderList.size(); j++) {
			int cols = 0;
			row = sheet.createRow((int) rows);
			Object key;
			Object value;
			Map map_ = (Map) orderList.get(j);
			Iterator it_ = map_.entrySet().iterator();
			while (it_.hasNext()) {
				Map.Entry entry = (Map.Entry) it_.next();
				key = entry.getKey();
				value = entry.getValue();
				// System.out.println(key + "=" + value);
				try {
					if (value == null)
						value = "-";
					if (value.equals("null"))
						value = "-";
					if (value instanceof java.sql.Timestamp) {
						row.createCell((short) cols)
								.setCellValue(
										OrderExcelUtil
												.timestamp2Str((Timestamp) value));
					} else {
						if (key.equals("change")) {
							row.createCell((short) cols).setCellValue(
									Integer.valueOf((Integer) value));
						} else if (key.equals("money")
								|| key.equals("discount")) {
							row.createCell((short) cols).setCellValue(
									Integer.valueOf((Integer) value));
						} else {
							row.createCell((short) cols).setCellValue(
									(String) value);
						}
					}
				} catch (Exception ex) {
					//ex.printStackTrace();
					row.createCell((short) cols).setCellValue("-");
				}
				// System.out.print("cols" + cols);
				cols++;
			}
			// rows = rows + j+i;
			// System.out.println("rows" + rows);
			// System.out.println("j" + j);
			// row = sheet.createRow((int) rows);
			// Map map_ = (Map) details.get(j);
			// String a = (String) map_.get("a1ordernu");
			// row.createCell((short) 0).setCellValue(a);
			// a = (String) map_.get("companyid");
			// row.createCell((short) 1).setCellValue(a);
			// a = (String) map_.get("change");
			// row.createCell((short) 2).setCellValue(a);
			// a = (String) map_.get("createdate");
			// row.createCell((short) 3).setCellValue(a);
			// a = (String) map_.get("operator");
			// row.createCell((short) 4).setCellValue(a);
			// a = (String) map_.get("a6itemvalue");
			// row.createCell((short) 5).setCellValue(a);
			// a = (String) map_.get("money");
			// row.createCell((short) 6).setCellValue(a);
			// a = (String) map_.get("discount ");
			// row.createCell((short) 7).setCellValue(a);
			rows++;
		}
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		return wb;
	}
}