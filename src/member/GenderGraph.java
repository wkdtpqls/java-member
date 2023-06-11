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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class GenderGraph extends JDialog {

    public GenderGraph(JFrame parent, String title) {
        super(parent, title, true);

        // Establish a database connection
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaproject", "root", "");
            stmt = conn.createStatement();

            // Execute a query to retrieve gender data
            String sql = "SELECT gender FROM tb_userlist";
            ResultSet rs = stmt.executeQuery(sql);

            // Create the graph dataset and populate with gender statistics
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int maleCount = 0;
            int femaleCount = 0;
            int unknownCount = 0;

            while (rs.next()) {
                String gender = rs.getString("gender");
                if (gender.equalsIgnoreCase("남자")) {
                    maleCount++;
                } else if (gender.equalsIgnoreCase("여자")) {
                    femaleCount++;
                }else {
                    unknownCount++;
                }
            }

            if (maleCount > 0) {
                dataset.addValue(maleCount, "남자", "Gender");
            }
            if (femaleCount > 0) {
                dataset.addValue(femaleCount, "여자", "Gender");
            }
            if (unknownCount > 0) {
                dataset.addValue(unknownCount, "알 수 없음", "Gender");
            }

            // Create the graph
            JFreeChart chart = ChartFactory.createBarChart3D(
                    "성별 통계",
                    "성별",
                    "인원 수",
                    dataset
            );

            NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
            yAxis.setTickUnit(new NumberTickUnit(1));
                       
            chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 25));
            // 범례
            chart.getLegend().setItemFont(new Font("맑은고딕", Font.PLAIN, 18));
            
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

            GenderGraph graph = new GenderGraph(parent, "성별 통계");
            graph.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            graph.pack();
            graph.setSize(900, 600);
            graph.setVisible(true);
        });
    }
}
