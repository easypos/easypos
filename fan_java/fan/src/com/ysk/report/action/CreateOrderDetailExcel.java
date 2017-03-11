package com.ysk.report.action;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

public class CreateOrderDetailExcel {
	private static final String[] titles = { "���","��Ʒ", "����", "�۸�",
			"����id", "������","����", "ʱ��", "̨��", "tel", "����", "�ֵ�" };

	@Test
	public void test() throws Exception {
		CreateOrderDetailExcel a = new CreateOrderDetailExcel();
		HSSFWorkbook wb = a.getHSSFWorkbook("1", "", "");
		try {
			FileOutputStream fout = new FileOutputStream("d:/poitest/test.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HSSFWorkbook getHSSFWorkbook(String cid, String begin_, String end_)
			throws Exception {
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("����");
		//sheet.setColumnWidth(1, 50 * 256);
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
		// String sql =
		// "select * from BEIGEBULU_ORDER a where status='1' order by a.created desc";

		String sql = "select a.universalid,a.ordernu,a.operator,a.createddate,a.diningtableid,a.tel,a.usercount,a7.name as a7name   from FANORDER a "
				+ " left join fancompany a7 on a.companyid=a7.universalid  where a.status = '2' "
				+ " and (createddate>='"
				+ begin_
				+ "') and (createddate<='"
				+ end_ + "') and (a.companyid='" + cid + "')";

		Logger.getLogger(this.getClass().getName()).info(sql);
		List list_ = d.queryForList(sql);
		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		int rows = 1;
		for (int i = 0; i < list_.size(); i++) {
			Map map = (Map) list_.get(i);
			String id = (String) map.get("universalid");
			Iterator it = map.entrySet().iterator();
			Object key;
			Object value;
			// sql = "select * from BEIGEBULU_ORDERDETAIL  where pid='" + id +
			// "'";
			/*
			 * sql = "select a1.breed as shangPinForValue, " +
			 * "a.specifications,a.amount,a.price,a.discount,a.totalprice,a.shopid,a1.barcode1,a1.product_code,a2.name  "
			 * +
			 * "from beigebulu_orderdetail a  left join BEIGEBULU_ORDER a0 on "
			 * +
			 * "a.pid=a0.universalid left join t_product a1 on a.shangPin=a1.universalid"
			 * + "  left join sellsupplier a2 on a.shangPin=a2.universalid " +
			 * "  where pid='" + id + "'";
			 */
			sql = "select a15.name as a15name,a.dishcount,a.price"
					+ " from FANORDERITEM a  left join FANORDER a1 on a.pid=a1.universalid left join fandish a15 on a.dishid=a15.universalid "
					+ "  where a.pid='" + id + "'";
			Logger.getLogger(this.getClass().getName()).info(sql);
			// System.out.println(sql);
			// rows++;
			List details = d.queryForList(sql);
			for (int j = 0; j < details.size(); j++) {
				int cols = 0;
				// rows = rows + j+i;
				// System.out.println("rows" + rows);
				// System.out.println("j" + j);
				row = sheet.createRow((int) rows);
				Map map_ = (Map) details.get(j);
				Iterator it_ = map_.entrySet().iterator();
				// ���
				row.createCell((short) cols).setCellValue((int) rows);
				cols++;
				System.out
						.println("____________________________________________________cols");
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
							row.createCell((short) cols).setCellValue(
									OrderExcelUtil
											.timestamp2Str((Timestamp) value));
						} else {
							if (key.equals("dishcount")) {
								row.createCell((short) cols).setCellValue(
										Integer.valueOf((Integer) value));
							} else if (key.equals("price")
									|| key.equals("totalprice")) {
								row.createCell((short) cols).setCellValue(
										Double.valueOf((Integer) value));
							} else {
								row.createCell((short) cols).setCellValue(
										(String) value);
							}
						}
					} catch (Exception ex) {
						row.createCell((short) cols).setCellValue("-");
					}
					cols++;
					// System.out.print("cols" + cols);
				}

				// ��ѭ�����ж����Ʒ��ʱ��ִֻ��һ�Σ�
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					key = entry.getKey();
					value = entry.getValue();
					// System.out.println(key + "=" + value);
					// System.out.print(key + "=" + value);
					try {
						if (value == null)
							value = "-";
						if (value.equals("null"))
							value = "-";
						if (value instanceof java.sql.Timestamp) {
							row.createCell((short) cols).setCellValue(
									OrderExcelUtil
											.timestamp2Str((Timestamp) value));
						} else {
							if (key.equals("sumprice")
									|| key.equals("deliveryfee")
									|| key.equals("taxfee")
									|| key.equals("deliveryfeefordirectmail")
									|| key.equals("totalprice")) {
								row.createCell((short) cols).setCellValue(
										Double.valueOf((String) value));
							} else {
								row.createCell((short) cols).setCellValue(
										(String) value);
							}
						}
					} catch (Exception ex) {
						row.createCell((short) cols).setCellValue("-");
					}
					cols++;
					// System.out.print("cols" + cols);
				}
				System.out.println("");
				rows++;

			}
			// ���ݻ���

			// String name_ = (String) map.get("name");
			// ���Ĳ���������Ԫ�񣬲�����ֵ
			//
			// row.createCell((short) 1).setCellValue(stu.getName());
			// row.createCell((short) 2).setCellValue((double) stu.getAge());
			// cell = row.createCell((short) 3);
			// cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
			// .getBirth()));
			// break;
		}
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // ����һ�����и�ʽ
		int count = rows;

		System.out.println("rows____________" + rows);
		// rows = rows + 1;
		// row = sheet.createRow((int) rows);
		// cell.setCellStyle(style);
		// row.createCell((short) 2).setCellValue("������");
		// cell.setCellStyle(style);
		// row.createCell((short) 3).setCellFormula("SUM(D2:D" + count + ")");
		// cell.setCellStyle(style);
		// row.createCell((short) 4).setCellValue("����������������˰��");
		// cell.setCellStyle(style);
		// row.createCell((short) 5).setCellFormula("SUM(V2:V" + count + ")");
		// cell.setCellStyle(style);
		// row.createCell((short) 6).setCellValue("����");
		// cell.setCellStyle(style);
		// row.createCell((short) 7).setCellFormula("SUM(W2:W" + count + ")");
		// cell.setCellStyle(style);
		// row.createCell((short) 8).setCellValue("��������");
		// cell.setCellStyle(style);
		// row.createCell((short) 9).setCellFormula("SUM(Y2:Y" + count + ")");
		// cell.setCellStyle(style);
		// row.createCell((short) 10).setCellValue("˰");
		// cell.setCellStyle(style);
		// row.createCell((short) 11).setCellFormula("SUM(X2:X" + count + ")");
		// cell.setCellStyle(style);
		// row.createCell((short) 12).setCellValue("������������˰��");
		// cell.setCellStyle(style);
		// row.createCell((short) 13).setCellFormula("SUM(Z2:Z" + count + ")");
		cell.setCellStyle(style);
		// �����������ļ��浽ָ��λ��

		/**
		 * �ڶ����������Ҳ���е�excel���� pName="��Ŀͳ�Ʊ�"; response.reset();
		 * response.setContentType("application/x-msdownload");
		 * response.setHeader("Content-Disposition","attachment; filename="+new
		 * String(pName.getBytes("gb2312"),"ISO-8859-1")+".xls");
		 * ServletOutputStream outStream=null;
		 * 
		 * try{ outStream = response.getOutputStream(); wb.write(outStream);
		 * }catch(Exception e) { e.printStackTrace(); }finally{
		 * outStream.close(); }
		 * */
		return wb;
	}
}