package com.erp.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class Bar {
	public String bar(HttpServletRequest request,String a[][]) throws Exception {
		JFreeChart chart;		
		double[][] data = new double[1][a.length];
		String[] rowKeys = { "" };
		String[] columnKeys = new String[a.length];
		for (Integer i = 0; i < a.length; i++) {
			data[0][i] = new Double(a[i][1]);
			columnKeys[i] = a[i][0];
		}
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data);
		chart = ChartFactory.createBarChart3D("客户分类图表", null, null, dataset,
				PlotOrientation.VERTICAL, true, false, false);
		// ChartFactory.createPieChart3D
		// createBarChart3D
		chart.setBackgroundPaint(java.awt.Color.orange); // 可选，设置图片背景色
		chart.setTitle(new TextTitle("-", new Font("黑体", Font.BOLD, 12)));
		LegendTitle legend = chart.getLegend(0);
		// 修改图例字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 12));
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.blue);		
		float alpha=(float) 0.5;
		plot.setBackgroundAlpha(alpha);
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
		// CategoryAxis
		// domainAxis.setVerticalCategoryLabels(false);
		plot.setDomainAxis(domainAxis);
		CategoryAxis categoryAxis = plot.getDomainAxis();
		// categoryAxis.setMaximumCategoryLabelLines(1);
		// 取得横轴
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 设置横轴显示标签的字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));
		// 分类标签字体
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		// 取得纵轴
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 设置纵轴显示标签字体
		ValueAxis rangeAxis = plot.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);
		BarRenderer3D renderer = new BarRenderer3D();
		// LineRenderer3D renderer = new LineRenderer3D();
		// renderer.setBaseOutlinePaint(Color.BLACK);
		Shape shape;
		// renderer.setShape(shape);
		// 设置 Wall 的颜色
		renderer.setWallPaint(Color.white);
		// 设置每种水果代表的柱的颜色
		// renderer.setSeriesPaint(0, new Color(0, 0, 255));
		// renderer.setSeriesPaint(1, new Color(0, 100, 255));
		// renderer.setSeriesPaint(2, Color.GREEN);
		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.1);
		// 显示每个柱的数值，并修改该数值的字体属性
		renderer
				.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelsVisible(true);
		plot.setRenderer(renderer);
		// 设置柱的透明度
		// plot.setForegroundAlpha(0.6f);
		// 设置地区、销量的显示位置
		plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		String filename = ServletUtilities.saveChartAsPNG(chart, 700, 400,
				null, request.getSession());
		String graphURL = request.getContextPath()
				+ "/servlet/DisplayChart?filename=" + filename;
		request.setAttribute("filename", filename);
		//request.setAttribute("graphURL", graphURL);
		return filename;

	}

}
