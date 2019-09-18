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


/**
 *
 * @author Lucas
 */
public class BarChart extends JFrame{
    private static final long serialVersionUID = 1L;
    
    public BarChart(String appTitle) {
        super(appTitle);
        

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Example | BORAJI.COM", //Chart Title
            "Year", // Category axis
            "Population in Million", // Value axis
            dataset,
            PlotOrientation.VERTICAL,
            false,false,true
           );
        
        

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Population in 2005
        dataset.addValue(10, "USA", "2005");
        dataset.addValue(15, "India", "2005");
        dataset.addValue(20, "China", "2005");
        dataset.addValue(50, "K", "2005");
        dataset.addValue(20, "d", "2005");
        dataset.addValue(20, "f", "2005");
        dataset.addValue(20, "j", "2005");
        dataset.addValue(20, "i", "2005");
        dataset.addValue(20, "m", "2005");
        dataset.addValue(20, "n", "2005");
        dataset.addValue(20, "z", "2005");
        dataset.addValue(20, "d", "2005");  

        return dataset;
    }
}
