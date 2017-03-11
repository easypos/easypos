package com.erp.chart;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class Pie {
	public String pie(javax.servlet.http.HttpServletRequest request,
			String a[][]) throws IOException {
		JFreeChart chart;
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Integer i = 0; i < a.length; i++) {
			dataset.setValue(a[i][0], new Double(a[i][1]));
		}
		chart = ChartFactory.createPieChart3D("客户分类图表", dataset, true, true,
				false);
		chart.setBackgroundPaint(java.awt.Color.white); // 可选，设置图片背景色
		chart.setTitle(new TextTitle("-", new Font("黑体", Font.BOLD, 12)));
		LegendTitle legend = chart.getLegend(0);
		// 修改图例字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 12));
		chart.setBackgroundPaint(Color.WHITE);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 图片中显示百分比:默认方式
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.
		// DEFAULT_TOOLTIP_FORMAT));
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));
		// 设置背景色为白色
		chart.setBackgroundPaint(Color.white);
		// 指定图片的透明度(0.0-1.0)
		// plot.setForegroundAlpha(1.0f);
		// 指定显示的饼图上圆形(false)还椭圆形(true)
		plot.setCircular(true);
		// 设置图标题的字体
		Font font = new Font(" 黑体", Font.CENTER_BASELINE, 12);
		TextTitle title = new TextTitle(" 项目状态分布");
		title.setFont(font);
		// chart.setTitle(title);
		String filename = ServletUtilities.saveChartAsPNG(chart, 700, 400,
				null, request.getSession());
		String graphURL = request.getContextPath()
				+ "/servlet/DisplayChart?filename=" + filename;
		request.setAttribute("filename", filename);
		//request.setAttribute("graphURL", graphURL);
		return filename;
	}
}
