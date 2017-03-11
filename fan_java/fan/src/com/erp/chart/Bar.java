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
		chart = ChartFactory.createBarChart3D("�ͻ�����ͼ��", null, null, dataset,
				PlotOrientation.VERTICAL, true, false, false);
		// ChartFactory.createPieChart3D
		// createBarChart3D
		chart.setBackgroundPaint(java.awt.Color.orange); // ��ѡ������ͼƬ����ɫ
		chart.setTitle(new TextTitle("-", new Font("����", Font.BOLD, 12)));
		LegendTitle legend = chart.getLegend(0);
		// �޸�ͼ������
		legend.setItemFont(new Font("����", Font.BOLD, 12));
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
		// ȡ�ú���
		categoryAxis.setLabelFont(new Font("����", Font.BOLD, 12));
		// ���ú�����ʾ��ǩ������
		categoryAxis.setTickLabelFont(new Font("����", Font.BOLD, 12));
		// �����ǩ����
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		// ȡ������
		numberAxis.setLabelFont(new Font("����", Font.BOLD, 12));
		// ����������ʾ��ǩ����
		ValueAxis rangeAxis = plot.getRangeAxis();
		// ������ߵ�һ�� Item ��ͼƬ���˵ľ���
		rangeAxis.setUpperMargin(0.15);
		// ������͵�һ�� Item ��ͼƬ�׶˵ľ���
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);
		BarRenderer3D renderer = new BarRenderer3D();
		// LineRenderer3D renderer = new LineRenderer3D();
		// renderer.setBaseOutlinePaint(Color.BLACK);
		Shape shape;
		// renderer.setShape(shape);
		// ���� Wall ����ɫ
		renderer.setWallPaint(Color.white);
		// ����ÿ��ˮ�������������ɫ
		// renderer.setSeriesPaint(0, new Color(0, 0, 255));
		// renderer.setSeriesPaint(1, new Color(0, 100, 255));
		// renderer.setSeriesPaint(2, Color.GREEN);
		// ����ÿ��������������ƽ������֮�����
		renderer.setItemMargin(0.1);
		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		renderer
				.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelsVisible(true);
		plot.setRenderer(renderer);
		// ��������͸����
		// plot.setForegroundAlpha(0.6f);
		// ���õ�������������ʾλ��
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
