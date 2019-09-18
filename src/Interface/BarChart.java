/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javafx.scene.chart.CategoryAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.renderer.category.StandardBarPainter;


/**
 *
 * @author Lucas
 */
public class BarChart extends JFrame{
    private static final long serialVersionUID = 1L;
    
    public BarChart(String appTitle) {
        super(appTitle);
        

        String title = "Bar chart Color Example";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                
                /* create chart */
		JFreeChart chart = ChartFactory.createBarChart(title, "heights", "Number Of Persons", dataset);

		/* Get instance of CategoryPlot */
		CategoryPlot plot = chart.getCategoryPlot();

		/* Change Bar colors */
		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		for(int i = 0; i < 256; i++){
                    dataset.addValue(i+1, Integer.toString(i+1), "0 ... 255");
                    renderer.setSeriesPaint(i, Color.blue);
                }

		renderer.setDrawBarOutline(false);
		renderer.setItemMargin(0);

		/* create and display chart on frame */
		ChartFrame frame = new ChartFrame("JFreeChart Demo", chart);
		frame.setVisible(true);
		frame.pack();
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        for(int i = 0; i < 256; i++){
            dataset.addValue(i+1, Integer.toString(i+1), "0 ... 255");
        }

        return dataset;
    }
}
