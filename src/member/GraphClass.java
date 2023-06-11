package member;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphClass extends JDialog {

    public GraphClass(JFrame parent, String title) {
        super(parent, title, true);

        // Establish a database connection
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaproject", "root", "");
            stmt = conn.createStatement();

            // Execute a query to retrieve age data
            String sql = "SELECT age FROM tb_userlist";
            ResultSet rs = stmt.executeQuery(sql);

            // Create the graph dataset and populate with age group statistics
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int ageGroup0_10 = 0;
            int ageGroup11_20 = 0;
            int ageGroup21_30 = 0;
            int ageGroup31_40 = 0;
            int ageGroup41_50 = 0;
            
            while (rs.next()) {
                int age = rs.getInt("age");
                if (age <= 10) {
                    ageGroup0_10++;
                } else if (age <= 20) {
                    ageGroup11_20++;
                } else if (age <= 30) {
                    ageGroup21_30++;
                } else if (age <= 40) {
                    ageGroup31_40++;
                } else if (age <= 50) {
                    ageGroup41_50++;
                }
            }

            dataset.addValue(ageGroup0_10, "나이", "10세 이하");
            dataset.addValue(ageGroup11_20, "나이", "11-20");
            dataset.addValue(ageGroup21_30, "나이", "21-30");
            dataset.addValue(ageGroup31_40, "나이", "31-40");
            dataset.addValue(ageGroup41_50, "나이", "50세 이상");
          

            // Create the graph
         // Create the graph
            JFreeChart chart = ChartFactory.createBarChart(
                    "나이별 통계",
                    "Age Group",
                    "인원 수",
                    dataset,
                    PlotOrientation.VERTICAL, // 막대 그래프 방향
                    true, // legend 표시 여부
                    false, // tooltips 활성화 여부
                    false // URLs 활성화 여부
            );

            NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
            yAxis.setTickUnit(new NumberTickUnit(1));
            
            chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 25));
            // 범례
            chart.getLegend().setItemFont(new Font("맑은고딕", Font.PLAIN, 10));
            
            CategoryPlot plot = chart.getCategoryPlot();
            
            Font font = plot.getDomainAxis().getLabelFont();
            // X축 라벨
            plot.getDomainAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
            // X축 도메인
            plot.getDomainAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 17));
            
            font = plot.getRangeAxis().getLabelFont();
            // Y축 라벨
            plot.getRangeAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
            // Y축 범위
            plot.getRangeAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 15));
            
            // Create the chart panel and set it as the content pane
            ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parent = new JFrame("Parent Frame");
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setSize(900, 600);
            parent.setVisible(true);

            GraphClass graph = new GraphClass(parent, "나이별 통계");
            graph.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            graph.pack();
            graph.setSize(900, 600);
            graph.setVisible(true);
        });
    }
}
