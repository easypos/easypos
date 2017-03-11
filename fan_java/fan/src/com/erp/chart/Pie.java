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
		chart = ChartFactory.createPieChart3D("�ͻ�����ͼ��", dataset, true, true,
				false);
		chart.setBackgroundPaint(java.awt.Color.white); // ��ѡ������ͼƬ����ɫ
		chart.setTitle(new TextTitle("-", new Font("����", Font.BOLD, 12)));
		LegendTitle legend = chart.getLegend(0);
		// �޸�ͼ������
		legend.setItemFont(new Font("����", Font.BOLD, 12));
		chart.setBackgroundPaint(Color.WHITE);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("����", Font.BOLD, 12));
		// ͼƬ����ʾ�ٷֱ�:Ĭ�Ϸ�ʽ
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.
		// DEFAULT_TOOLTIP_FORMAT));
		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));
		// ���ñ���ɫΪ��ɫ
		chart.setBackgroundPaint(Color.white);
		// ָ��ͼƬ��͸����(0.0-1.0)
		// plot.setForegroundAlpha(1.0f);
		// ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)
		plot.setCircular(true);
		// ����ͼ���������
		Font font = new Font(" ����", Font.CENTER_BASELINE, 12);
		TextTitle title = new TextTitle(" ��Ŀ״̬�ֲ�");
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
