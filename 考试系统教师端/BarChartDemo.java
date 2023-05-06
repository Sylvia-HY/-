package InquireScore;

import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BarChartDemo {

//    private DbConnection dbconn = null;
//    private Connection conn = null;
//    private ResultSet rs = null;
//    private PreparedStatement pstmt = null;
//    private Statement stmt = null;

//    ArrayList<Integer> under60 = new ArrayList<Integer>();
//    ArrayList<Integer> between67 = new ArrayList<Integer>();
//    ArrayList<Integer> between78 = new ArrayList<Integer>();
//    ArrayList<Integer> between89 = new ArrayList<Integer>();
//    ArrayList<Integer> between90 = new ArrayList<Integer>();

    static int one;
    static int two;
    static int three;
    static int four;
    static int five;
    static String examname;


    public static void main(String[] args) {
        new BarChartDemo();
    }
    public BarChartDemo(){
        // 1. 得到数据
        CategoryDataset dataset = getDataSet();
        // 2. 构造chart
        JFreeChart chart = ChartFactory.createBarChart3D(
                "成绩柱状图", // 图表标题
                "满分100分", // 目录轴的显示标签--横轴
                "百分比%", // 数值轴的显示标签--纵轴
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、
                true, // 是否显示图例(对于简单的柱状图必须
                false, // 是否生成工具
                false // 是否生成URL链接
        );
        // 3. 处理chart中文显示问题
        processChart(chart);

        // 4. chart输出图片
        writeChartAsImage(chart);

        // 5. chart 以swing形式输出
        ChartFrame pieFrame = new ChartFrame("查整体成绩", chart);
        pieFrame.pack();
        pieFrame.setVisible(true);
        pieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pieFrame.setExtendedState(JFrame.MAXIMIZED_BOTH  );

    }

    /**
     * 获取一个演示用的组合数据集对象
     *
     * @return
     */
    private static CategoryDataset getDataSet() {
        query();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(one, examname, "低于60");
//        dataset.addValue(120, "试卷二", "低于60");
//        dataset.addValue(160, "试卷三", "低于60");
        dataset.addValue(two, examname, "70~60");
//        dataset.addValue(220, "试卷二", "70~60");
//        dataset.addValue(230, "试卷三", "70~60");
        dataset.addValue(three, examname, "80~70");
//        dataset.addValue(340, "试卷二", "80~70");
//        dataset.addValue(340, "试卷三", "80~70");
        dataset.addValue(four, examname, "90~80");
//        dataset.addValue(430, "试卷二", "90~80");
//        dataset.addValue(400, "试卷三", "90~80");
        dataset.addValue(five, examname, "100~90");
//        dataset.addValue(530, "试卷二", "100~90");
//        dataset.addValue(510, "试卷三", "100~90");
        return dataset;
    }

    public static void query()
    {
        ArrayList<Integer> under60 = new ArrayList<Integer>();
        ArrayList<Integer> between67 = new ArrayList<Integer>();
        ArrayList<Integer> between78 = new ArrayList<Integer>();
        ArrayList<Integer> between89 = new ArrayList<Integer>();
        ArrayList<Integer> between90 = new ArrayList<Integer>();

         DbConnection dbconn;
         Connection conn;
         ResultSet rs = null;
//         PreparedStatement pstmt = null;
         Statement stmt= null;
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select result.*,exam.examname from" +
                " result left join exam on exam.examid="+ChartBefore.examid +" where result.examid="
                +ChartBefore.examid;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next())
            {
                while(rs.next()){
                int iii = rs.getInt("sorce");
                examname = rs.getString("examname");
                if(iii<=100&&iii>90)
                    between90.add(iii);
                else if(iii<=90&&iii>80)
                    between89.add(iii);
                else if(iii<=80&&iii>70)
                    between78.add(iii);
                else if(iii<=70&&iii>60)
                    between67.add(iii);
                else
                    under60.add(iii);
                }
                one=under60.size();
                two=between67.size();
                three=between78.size();
                four=between89.size();
                five=between90.size();
            }
//            else
//            {
//                JOptionPane.showMessageDialog(, "查询失败,您可能没有此信息");
//            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解决图表汉字显示问题
     *
     * @param chart
     */
    private static void processChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        ValueAxis rAxis = plot.getRangeAxis();
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        // renderer.setItemLabelGenerator(new LabelGenerator(0.0));
        // renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));
        // renderer.setItemLabelsVisible(true);
    }

    /**
     * 输出图片
     *
     * @param chart
     */
    private static void writeChartAsImage(JFreeChart chart) {
        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("D:\\test\\fruit.jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {
            }
        }
    }
}