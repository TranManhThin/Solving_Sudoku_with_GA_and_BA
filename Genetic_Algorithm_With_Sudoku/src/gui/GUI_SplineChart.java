package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GUI_SplineChart extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButton;
	private JPanel jPanel1, jPanel2;
	XYSeries series1 = new XYSeries("Đảo 1");
	XYSeries series2 = new XYSeries("Đảo 2");
	XYSeries series3 = new XYSeries("Đảo 3");
	XYSeries series4 = new XYSeries("Đảo 4");
	XYSeries series5 = new XYSeries("Đảo 5");
	XYSeries series6 = new XYSeries("Đảo 6");
	public GUI_SplineChart(ArrayList<ArrayList<Integer>> data) {
		// TODO Auto-generated constructor stub
		super("18019701 - Tran Manh Thin - DHCNTT14");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jPanel1 = spLineChart(data);
		add(jPanel1, BorderLayout.CENTER);
		
		jPanel2 = new JPanel();
		jPanel2.setLayout(null);
		jPanel2.setPreferredSize(new Dimension(0, 70));
		jButton = new RoundedButton("Thoát");
		jButton.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jButton.setBounds(1000,10,100,30);
		jPanel2.add(jButton);
		
		add(jPanel2, BorderLayout.SOUTH);
		
		jButton.addActionListener(this);
		

	}

	@SuppressWarnings("unused")
	private JPanel spLineChart(ArrayList<ArrayList<Integer>> line) {
		

		int lineResult = 0;
		int lineBack = 0;

		for (int i = 0; i < line.size(); i++) {
			if (line.get(i).size() > line.get(lineResult).size()) {
				lineResult = i;
			}
			if (line.get(i).size() < line.get(lineBack).size()) {
				lineBack = i;
			}
		}

		for (int i = 0; i < line.get(lineBack).size(); i += 2) {
			series1.add(line.get(0).get(i), line.get(0).get(i + 1));
			series2.add(line.get(1).get(i), line.get(1).get(i + 1));
			series3.add(line.get(2).get(i), line.get(2).get(i + 1));
			series4.add(line.get(3).get(i), line.get(3).get(i + 1));
			series5.add(line.get(4).get(i), line.get(4).get(i + 1));
			series6.add(line.get(5).get(i), line.get(5).get(i + 1));
		}

		if (lineResult == 0) {
			series1.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		} else if (lineResult == 1) {
			series2.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		} else if (lineResult == 2) {
			series3.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		} else if (lineResult == 3) {
			series4.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		} else if (lineResult == 4) {
			series5.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		} else if (lineResult == 5) {
			series6.add(line.get(lineResult).get(line.get(lineResult).size() - 2), line.get(lineResult).get(line.get(lineResult).size() - 1));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		dataset.addSeries(series4);
		dataset.addSeries(series5);
		dataset.addSeries(series6);

		NumberAxis domain = new NumberAxis("THẾ HỆ");
		NumberAxis range = new NumberAxis("ĐỘ THÍCH NGHI");
		XYSplineRenderer r = new XYSplineRenderer(15);
		XYPlot xyplot = new XYPlot(dataset, domain, range, r);
		JFreeChart chart = new JFreeChart(xyplot);
		ChartPanel chartPanel = new ChartPanel(chart) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -5542570410764136122L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(640, 480);
			}
		};
		return chartPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(jButton)) {
			setVisible(false);
			series1.clear();
			series2.clear();
			series3.clear();
			series4.clear();
			series5.clear();
			series6.clear();
		}
		
	}

}
