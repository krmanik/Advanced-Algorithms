import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class LineChart extends JFrame {
    String title;
    String xAxis;
    String yAxis;
    DefaultCategoryDataset dataset;

    public LineChart(String title, String xAxis, String yAxis, DefaultCategoryDataset dataset) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.dataset = dataset;
    }

    public void plotLineChart() {
        JFreeChart chart = ChartFactory.createLineChart(title, xAxis, yAxis, dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        this.setAlwaysOnTop(true);
        this.setAlwaysOnTop(true);
        this.pack();
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}