package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    static {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow", true));
    }

    public BarChartDemo(String title, ArrayList<String> data) {
        super(title);
        setContentPane(createDemoPanel(data));
    }
    public JPanel createDemoPanel(ArrayList<String> data) {
        CategoryDataset dataset = createDataset(data);
        JFreeChart chart = createChart(dataset);
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        return chartPanel;
    }

    private CategoryDataset createDataset(ArrayList<String> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String d:data) {
            String[] _d = d.split(",");
            dataset.addValue(Double.parseDouble(_d[3]), "Минимум", _d[1] + '.' + _d[0]);
            dataset.addValue(Double.parseDouble(_d[2]), "Максимум", _d[1] + '.' + _d[0]);
        }
        /*dataset.addValue(52733, "Жена" , "Январь");
        dataset.addValue(64535, "Муж" , "Январь");
        dataset.addValue(51345, "Жена", "Февраль");
        dataset.addValue(66896, "Муж" , "Февраль");*/
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            "Максимум и минимиум",
            null,                    // x-axis label 
            "Величина",                 // y-axis label
            dataset);
        /*chart.addSubtitle(new TextTitle("В доходе включен только заработок "
                                       + "по основной работе"));*/
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    public static void main(ArrayList<String> data) {
        BarChartDemo demo = new BarChartDemo("JFreeChart: BarChartDemo.java", data);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
