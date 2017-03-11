package com.ysk.report.action;

import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f1jeeframework.http.Action;

/**
 * 导出交易记录
 * 
 * @author easyn+
 * 
 */
public class ExportOrderBuyExcel extends Action {
	String begin_ = null;
	String end_ = null;
	String cid = "";

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html;charset=GBK");
		// 第二种是输出到也面中的excel名称
		cid = arg0.getParameter("cid");
		if (cid == null)
			cid = "";
		initDateParam(arg0);
		String title = begin_ + end_ + "（" + cid + "）";	
		arg1.reset();
		arg1.setContentType("application/x-msdownload");
		arg1.setHeader("Content-Disposition", "attachment; filename="
				+ new String(title.getBytes("gb2312"), "ISO-8859-1") + ".xls");
		ServletOutputStream outStream = null;
		try {
			outStream = arg1.getOutputStream();
			(new CreateOrderBuy().getHSSFWorkbook(cid, begin_, end_))
					.write(outStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outStream.close();
		}

	}

	private void initDateParam(HttpServletRequest arg) {
		begin_ = arg.getParameter("begin_");
		end_ = arg.getParameter("end_");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int yy = cal.get(GregorianCalendar.YEAR);
		int mm = cal.get(GregorianCalendar.MONTH);
		mm = mm + 1;
		int dd = cal.get(GregorianCalendar.DAY_OF_MONTH);
		Logger.getLogger(this.getClass().getName()).info("begin_" + begin_);
		Logger.getLogger(this.getClass().getName()).info("end_" + end_);
		// System.out.println(begin_);
		// System.out.println(end_);
		if (begin_ == null) {
			begin_ = yy + "-" + mm + "-" + dd;
			end_ = begin_ + " " + "23:00";
			System.out.println(begin_);
			System.out.println(end_);
		}
		arg.setAttribute("begin_", begin_);
		arg.setAttribute("end_", end_);
	}

}
