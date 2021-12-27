package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeSeriesChartDemo extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    static {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

    public TimeSeriesChartDemo(String title, ArrayList<String> datas) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel(datas);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(XYDataset dataset)
    {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Максимальный и минимальный перевод за период 2014, 2016 и 2020 год ",  // title
            "",                            // x-axis label
            "Валюта",                      // y-axis label
            dataset,                       // data
            true,                          // create legend
            true,                          // generate tooltips
            false                          // generate URLs
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint    (Color.lightGray);
        plot.setDomainGridlinePaint(Color.white    );
        plot.setRangeGridlinePaint (Color.white    );
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible   (true);
            renderer.setBaseShapesFilled    (true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM.YYYY"));

        return chart;
    }

    private XYDataset createDataset(ArrayList<String> datas)
    {
        TimeSeries s1 = new TimeSeries("Максимум");
        TimeSeries s2 = new TimeSeries("Минимум");
        for (String d:datas) {
            String[] _d = d.split(",");
            s1.add(new Month(Integer.parseInt(_d[1]), Integer.parseInt(_d[0])), Double.parseDouble(_d[2]));
            s2.add(new Month(Integer.parseInt(_d[1]), Integer.parseInt(_d[0])), Double.parseDouble(_d[3]));
        }
        /*s1.add(new Month( 7, 2013), 142.9);
        s1.add(new Month( 8, 2013), 138.7);
        s1.add(new Month( 9, 2013), 137.3);
        s1.add(new Month(10, 2013), 143.9);
        s1.add(new Month(11, 2013), 139.8);
        s1.add(new Month(12, 2013), 137.0);
        s1.add(new Month( 1, 2014), 132.8);
        s1.add(new Month( 2, 2014), 181.8);
        s1.add(new Month( 3, 2014), 167.3);
        s1.add(new Month( 4, 2014), 153.8);
        s1.add(new Month( 5, 2014), 167.6);
        s1.add(new Month( 6, 2014), 158.8);
        s1.add(new Month( 7, 2014), 148.3);
        s1.add(new Month( 8, 2014), 153.9);
        s1.add(new Month( 9, 2014), 142.7);
        s1.add(new Month(10, 2014), 123.2);
        s1.add(new Month(11, 2014), 131.8);
        s1.add(new Month(12, 2014), 139.6);*/

        /*TimeSeries s2 = new TimeSeries("График №2");
        s2.add(new Month( 7, 2013), 111.7);
        s2.add(new Month( 8, 2013), 111.0);
        s2.add(new Month( 9, 2013), 109.6);
        s2.add(new Month(10, 2013), 113.2);
        s2.add(new Month(11, 2013), 111.6);
        s2.add(new Month(12, 2013), 108.8);
        s2.add(new Month( 1, 2014), 101.6);
        s2.add(new Month( 2, 2014), 129.6);
        s2.add(new Month( 3, 2014), 123.2);
        s2.add(new Month( 4, 2014), 117.2);
        s2.add(new Month( 5, 2014), 124.1);
        s2.add(new Month( 6, 2014), 122.6);
        s2.add(new Month( 7, 2014), 119.2);
        s2.add(new Month( 8, 2014), 116.5);
        s2.add(new Month( 9, 2014), 112.7);
        s2.add(new Month(10, 2014), 101.5);
        s2.add(new Month(11, 2014), 106.1);
        s2.add(new Month(12, 2014), 110.3);*/

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;
    }

    public JPanel createDemoPanel(ArrayList<String> datas) {
        JFreeChart chart = createChart(createDataset(datas));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
//      panel.setFillZoomRectangle(true);
//      panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    public static void main(ArrayList<String> datas)
    {
        TimeSeriesChartDemo demo = new TimeSeriesChartDemo("TimeSeriesChart Demo", datas);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}
