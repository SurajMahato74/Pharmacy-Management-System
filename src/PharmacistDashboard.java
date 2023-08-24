
// import header file
import common.OpenPdf;
import javax.swing.JOptionPane;
import dao.ConnectionProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.exp;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Su Raj
 */
public class PharmacistDashboard extends javax.swing.JFrame {

    private String username = "";
   

    /**
     * Creates new form PharmacistDashboard
     */
    public PharmacistDashboard() {
        initComponents();
        startTimerToUpdateDateTimeLabels();
        setupSearchFunctionality();

    }

    public PharmacistDashboard(String tempUsername) {
        initComponents();
        username = tempUsername;
        setLocationRelativeTo(null);
    }

   
   
    // to show line Chart
public void showLineChart() {
    try {
        // Establish database connection
        Connection con = ConnectionProvider.getCon();
        String query = "SELECT billDate, SUM(totalPaid) AS totalpaid_sum FROM bill GROUP BY billDate";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (resultSet.next()) {
            String date = resultSet.getString("billDate"); // Use the correct column name
            double totalPaidSum = resultSet.getDouble("totalpaid_sum");
            dataset.addValue(totalPaidSum, "Amount", date);
        }

        resultSet.close();
        st.close();
        con.close();

        // Create chart
        JFreeChart linechart = ChartFactory.createLineChart("Daily Sells", "Date", "Amount",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    //to show bar chart
 public void showBarChart() {
    try {
        Connection con = ConnectionProvider.getCon();
        String query = "SELECT medicine, SUM(quantity) AS total_quantity FROM bill GROUP BY medicine";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (resultSet.next()) {
            String medicine = resultSet.getString("medicine"); // Use the correct column name
            int totalQuantity = resultSet.getInt("total_quantity");
            dataset.setValue(totalQuantity, "Quantity", medicine);
        }

        resultSet.close();
        st.close();
        con.close();

        JFreeChart chart = ChartFactory.createBarChart("Sold Medicine", "Medicine", "Quantity",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color barChartColor = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, barChartColor);

        ChartPanel barChartPanel = new ChartPanel(chart);
        jPanel14.removeAll();
        jPanel14.add(barChartPanel, BorderLayout.CENTER);
        jPanel14.validate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void showHistogram() {

        double[] values = {95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
            77, 44, 58, 91, 10, 67, 57, 19, 88, 84
        };

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);

        JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram", "Data", "Frequency", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        jPanel8.removeAll();
        jPanel8.add(barpChartPanel2, BorderLayout.CENTER);
        jPanel8.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtOutOfStock = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtLessStock = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtExpire = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        NoOfCustomer = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtTranscetion = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        panelLineChart = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 170, 20));

        txtSearch.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 340, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel2.setText("Search");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 80, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Time: ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 190, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_menu_48px_1.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 5, 48));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/male_user_50px.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, -1, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pharmacy Management System");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 12, 12));
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 70, 20));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 12, 12));
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 70, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1368, -1));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Home_26px_2.png"))); // NOI18N
        jLabel8.setText("   Home Page");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 250, 50));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jLabel10.setBackground(new java.awt.Color(240, 240, 240));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Library_26px_1.png"))); // NOI18N
        jLabel10.setText("   PMS Dashboard");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(24, 24, 24))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 250, 50));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Features");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 153, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Conference_26px.png"))); // NOI18N
        jButton1.setText("   Add Medicine");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 190, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(153, 153, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_View_Details_26px.png"))); // NOI18N
        jButton4.setText("   Sell Medicine");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 190, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Sell_26px.png"))); // NOI18N
        jButton2.setText("   View Medicine");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 180, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(153, 153, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Read_Online_26px.png"))); // NOI18N
        jButton5.setText("    View Bill");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 170, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(153, 153, 153));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Library_26px_1.png"))); // NOI18N
        jButton3.setText("   Update Medicine");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 440, 180, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(153, 153, 153));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Book_26px.png"))); // NOI18N
        jButton6.setText("    Profile");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 180, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Exit_26px_1.png"))); // NOI18N
        jLabel17.setText("    Logout");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 180, -1));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel14.setText("    Exit");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 250, 50));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText("Refresh");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 250, 740));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));

        txtOutOfStock.setBackground(new java.awt.Color(102, 102, 102));
        txtOutOfStock.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        txtOutOfStock.setForeground(new java.awt.Color(102, 102, 102));
        txtOutOfStock.setText("10");
        txtOutOfStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOutOfStockMouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtOutOfStock)
                .addGap(22, 22, 22))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtOutOfStock))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 200, 130));

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));

        txtLessStock.setBackground(new java.awt.Color(102, 102, 102));
        txtLessStock.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        txtLessStock.setForeground(new java.awt.Color(102, 102, 102));
        txtLessStock.setText("10");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtLessStock)
                .addGap(22, 22, 22))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtLessStock))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 190, 130));

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));

        txtExpire.setBackground(new java.awt.Color(102, 102, 102));
        txtExpire.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        txtExpire.setForeground(new java.awt.Color(102, 102, 102));
        txtExpire.setText("10");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtExpire)
                .addGap(22, 22, 22))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtExpire))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 190, 130));

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));

        NoOfCustomer.setBackground(new java.awt.Color(102, 102, 102));
        NoOfCustomer.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        NoOfCustomer.setForeground(new java.awt.Color(102, 102, 102));
        NoOfCustomer.setText("10");
        NoOfCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NoOfCustomerMouseClicked(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NoOfCustomer)
                .addGap(22, 22, 22))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(NoOfCustomer))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 190, 130));

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));

        txtTranscetion.setBackground(new java.awt.Color(102, 102, 102));
        txtTranscetion.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        txtTranscetion.setForeground(new java.awt.Color(102, 102, 102));
        txtTranscetion.setText("10");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTranscetion)
                .addGap(22, 22, 22))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTranscetion))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 90, 190, 130));

        jLabel31.setBackground(new java.awt.Color(102, 102, 102));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("No of Sells");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 60, 150, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(102, 102, 102));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Out of Stock ");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 0, -1, -1));

        jLabel22.setBackground(new java.awt.Color(102, 102, 102));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Less in Stock ");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        jLabel23.setBackground(new java.awt.Color(102, 102, 102));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Expire Medicine");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, -1));

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Total Medicine");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, -1, -1));

        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel2.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 350, 260));

        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 370, 260));

        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 330, 260));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Medicine ID", "Name", "Company", "Quantity", "Price", "Expire"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 670, 250));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill ID", "Date", "Total Paid", "Generated By", "name"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 400, 250));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 1120, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        new AddMedicine().setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        new SellMedicine(username).setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        new ViewMedicine().setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        new ViewBill().setVisible(true);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "do you want to Logout", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            setVisible(false);
            new login().setVisible(true);
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "do you want to Close", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void refreshPharmacistDashboard() {

    }

    // to update time and date
    private void startTimerToUpdateDateTimeLabels() {
        Timer timer = new Timer(1000, e -> {
            updateDateTimeLabels();
        });
        timer.start();
    }
// To display Date and time

    private void updateDateTimeLabels() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();

        jLabel3.setText("Date: " + dateFormat.format(currentDate));
        jLabel4.setText("Time: " + timeFormat.format(currentDate));
    }


    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        new PharmacistDashboard().setVisible(true);
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        new UpdateMedicine().setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        new Profile(username).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        startTimerToUpdateDateTimeLabels();
        showLineChart();
        showBarChart();
        showHistogram();
        populateMedicineData();
        populateBillTable();
        setupSearchFunctionality();
        setDataToCards();
        displayUserName();

    }//GEN-LAST:event_formComponentShown

    public void setDataToCards() {
        try {
            Connection con = ConnectionProvider.getCon();
            Calendar calendar = Calendar.getInstance();
            java.sql.Date sqlCurrentDate = new java.sql.Date(calendar.getTime().getTime());

            Statement st = con.createStatement();

            // Count Out of Stock Medicine
            ResultSet adminRs = st.executeQuery("SELECT COUNT(*) AS adminCount FROM medicine WHERE quantity = '0'");
            if (adminRs.next()) {
                int adminCount = adminRs.getInt("adminCount");
                txtOutOfStock.setText(Integer.toString(adminCount));
            } else {
                txtOutOfStock.setText("0");
            }

            // Count Less In Stock
            ResultSet pharmacistRs = st.executeQuery("SELECT COUNT(*) AS pharmacistCount FROM medicine WHERE quantity > '0' and  quantity <= '10'");
            if (pharmacistRs.next()) {
                int pharmacistCount = pharmacistRs.getInt("pharmacistCount");
                txtLessStock.setText(Integer.toString(pharmacistCount));
            } else {
                txtLessStock.setText("0");
            }

            // Count Expire Medicine
            String query = "SELECT COUNT(*) AS expiredCount FROM medicine WHERE exp < ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDate(1, sqlCurrentDate);
            ResultSet expiredRs = preparedStatement.executeQuery();

            if (expiredRs.next()) {
                int expiredCount = expiredRs.getInt("expiredCount");
                txtExpire.setText(Integer.toString(expiredCount));
            } else {
                txtExpire.setText("0");

            }

            // Count No of Medicine
            ResultSet UserRs = st.executeQuery("SELECT COUNT(*) AS UserTotal FROM medicine");
            if (UserRs.next()) {
                int UserTotal = UserRs.getInt("UserTotal");
                NoOfCustomer.setText(Integer.toString(UserTotal));
            } else {
                NoOfCustomer.setText("0");
            }

            // Count Total Transection
            ResultSet Trans = st.executeQuery("SELECT COUNT(*) AS TransTotal FROM bill");
            if (Trans.next()) {
                int TransTotal = Trans.getInt("TransTotal");
                txtTranscetion.setText(Integer.toString(TransTotal));
            } else {
                txtTranscetion.setText("0");
            }

            txtExpire.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    filterExpiredMedicines();
                }
            });
            txtLessStock.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    filterLessStockMedicines();
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
// To get welcome admin label when user login;

    private void displayUserName() {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            String query = "SELECT name FROM appuser WHERE username = '" + username + "'";
            ResultSet resultSet = st.executeQuery(query);

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                jLabel5.setText("Welcome, " + name);
            } else {
                // User not found
                jLabel5.setText("Welcome, User");
            }

            resultSet.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // To perform search opertation in admin dashboard
    private void setupSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
                filterTable2();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
                filterTable2();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is not needed for plain text fields
            }
        });
    }

    // To filter Table by Expire
    private void filterExpiredMedicines() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

        int columnIndexOfExpireDate = 6;
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());

        sorter.setRowFilter(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, currentDate, columnIndexOfExpireDate));
        jTable1.setRowSorter(sorter);
    }
// To filter Out of stock medicine

    private void filterLessStockMedicines() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

        int columnIndexOfQuantity = 4;
        int quantityThreshold = 10;

        // Create a RowFilter that checks if the value in the specified column is less than quantityThreshold
        RowFilter<DefaultTableModel, Integer> quantityFilter = RowFilter.numberFilter(
                RowFilter.ComparisonType.BEFORE, quantityThreshold - 1, columnIndexOfQuantity);

        sorter.setRowFilter(quantityFilter);
        jTable1.setRowSorter(sorter);
    }

// tO FILTER AND DISPLAY OUT OF STOCKMEDICINE LIST IN TABLE
    private void filterOutStockMedicines() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

        int columnIndexOfQuantity = 4;
        int quantityThreshold = 1;

        // Create a RowFilter that checks if the value in the specified column is less than or equal to quantityThreshold
        RowFilter<DefaultTableModel, Integer> quantityFilter = RowFilter.numberFilter(
                RowFilter.ComparisonType.BEFORE, quantityThreshold, columnIndexOfQuantity);

        sorter.setRowFilter(quantityFilter);
        jTable1.setRowSorter(sorter);
    }

    // filter the table according to searchperformed
    private void filterTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        String text = txtSearch.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null); // Remove any existing filters
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Case-insensitive filter
        }
    }

    // filter the table according to searchperformed
    private void filterTable2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable2.setRowSorter(sorter);

        String text = txtSearch.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null); // Remove any existing filters
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Case-insensitive filter
        }
    }

//to populate billTable
    private void populateBillTable() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(("select *from bill"));
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("billId"), rs.getString("billDate"), rs.getString("totalPaid"), rs.getString("generatedBy"), rs.getString("medicine")});

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

// To populate the Medicine table in dashboard
    private void populateMedicineData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(null);
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from medicine");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("medicine_pk"),
                    rs.getString("uniqueId"),
                    rs.getString("name"),
                    rs.getString("companyName"),
                    rs.getString("quantity"),
                    rs.getString("price"),
                    rs.getDate("exp") // Make sure "exp" matches the column name in your database
                });

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // To delete the Medicine from table
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        int a;
        a = JOptionPane.showConfirmDialog(null, "Do you want to Delete", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("delete from medicine where medicine_pk=?");
                ps.setString(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medicine Deleted Sucessfully");
                setVisible(false);
                new PharmacistDashboard().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        OpenPdf.openById(id);
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtOutOfStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOutOfStockMouseClicked
        // TODO add your handling code here:
        filterOutStockMedicines();
    }//GEN-LAST:event_txtOutOfStockMouseClicked

    private void NoOfCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NoOfCustomerMouseClicked
        // TODO add your handling code here:
        new PharmacistDashboard().setVisible(true);
    }//GEN-LAST:event_NoOfCustomerMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PharmacistDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PharmacistDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PharmacistDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PharmacistDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PharmacistDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NoOfCustomer;
    private javax.swing.JLabel jButton1;
    private javax.swing.JLabel jButton2;
    private javax.swing.JLabel jButton3;
    private javax.swing.JLabel jButton4;
    private javax.swing.JLabel jButton5;
    private javax.swing.JLabel jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JLabel txtExpire;
    private javax.swing.JLabel txtLessStock;
    private javax.swing.JLabel txtOutOfStock;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JLabel txtTranscetion;
    // End of variables declaration//GEN-END:variables
}
