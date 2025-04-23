package iuh.screen.jpanel;

import com.toedter.calendar.JDateChooser;
import iuh.dao.QuanLyDAO.DoanhThuDao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class DoanhThuPanel extends JPanel {
    private final DoanhThuDao doanhThuDao;
    private JComboBox<String> timePeriodComboBox;
    private JDateChooser dateChooser;
    private JComboBox<Integer> yearComboBox;
    private ChartPanel chartPanel;
    private JPanel controlPanel;

    public DoanhThuPanel() {
        doanhThuDao = new DoanhThuDao();
        setBackground(new Color(240, 243, 246));
        setLayout(new BorderLayout());

        // Control panel for time period, date, and year selection
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(240, 243, 246));
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Time period selection
        timePeriodComboBox = new JComboBox<>(new String[]{"Theo Năm", "Theo Tháng", "Theo Ngày"});
        timePeriodComboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        timePeriodComboBox.addActionListener(e -> updateControlPanel());
        controlPanel.add(new JLabel("Chọn khoảng thời gian: "));
        controlPanel.add(timePeriodComboBox);

        // Date chooser for daily selection (initially hidden)
        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.addPropertyChangeListener("date", evt -> updateChart());
        dateChooser.setVisible(false);
        controlPanel.add(new JLabel("Chọn ngày: "));
        controlPanel.add(dateChooser);

        // Year selection for monthly revenue (initially hidden)
        yearComboBox = new JComboBox<>();
        populateYearComboBox();
        yearComboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        yearComboBox.addActionListener(e -> updateChart());
        yearComboBox.setVisible(false);
        yearComboBox.setPreferredSize(new Dimension(100, 30));
        controlPanel.add(new JLabel("Chọn năm: "));
        controlPanel.add(yearComboBox);

        add(controlPanel, BorderLayout.NORTH);

        // Chart panel
        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        // Load the initial chart (default: by year)
        updateControlPanel();
    }

    private void populateYearComboBox() {
        // Tu 2023 va nho hon nam hien tai
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 2023; year <= currentYear; year++) {
            yearComboBox.addItem(year);
        }
    }

    private void updateControlPanel() {
        String selectedPeriod = (String) timePeriodComboBox.getSelectedItem();
        dateChooser.setVisible(selectedPeriod.equals("Theo Ngày"));
        yearComboBox.setVisible(selectedPeriod.equals("Theo Tháng"));
        controlPanel.revalidate();
        controlPanel.repaint();
        updateChart();
    }

    private void updateChart() {
        String selectedPeriod = (String) timePeriodComboBox.getSelectedItem();
        String loaiThoiGian;
        Date selectedDate = null;
        Integer selectedYear = null;

        // Determine the time period and parameters
        switch (selectedPeriod) {
            case "Theo Năm":
                loaiThoiGian = "Nam";
                break;
            case "Theo Tháng":
                loaiThoiGian = "Thang";
                selectedYear = (Integer) yearComboBox.getSelectedItem();
                break;
            case "Theo Ngày":
                loaiThoiGian = "Ngay";
                if (dateChooser.getDate() != null) {
                    selectedDate = new Date(dateChooser.getDate().getTime());
                }
                break;
            default:
                loaiThoiGian = "Nam";
        }

        // Fetch data
        List<DoanhThuDao.DoanhThu> doanhThuList = doanhThuDao.getDoanhThuVe(loaiThoiGian, selectedDate, selectedYear);

        // Create dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DoanhThuDao.DoanhThu doanhThu : doanhThuList) {
            dataset.addValue(doanhThu.getDoanhThu(), "Doanh Thu (triệu đồng)", doanhThu.getThoiDiem());
        }

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu Đồ Doanh Thu Theo " + selectedPeriod, // Title
                selectedPeriod.equals("Theo Tháng") ? "Tháng" : (selectedPeriod.equals("Theo Ngày") ? "Ngày" : "Năm"), // X-axis label
                "Doanh Thu (triệu đồng)", // Y-axis label
                dataset, // Data
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Customize chart appearance
        barChart.setBackgroundPaint(new Color(240, 243, 246));
        barChart.getCategoryPlot().setBackgroundPaint(Color.WHITE);
        barChart.getCategoryPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
        barChart.getCategoryPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
        barChart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(255, 0, 0)); // Red bars
        barChart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 16));
        barChart.getTitle().setPaint(new Color(25, 118, 210));

        // Update the chart panel
        chartPanel.setChart(barChart);
    }
}