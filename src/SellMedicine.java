
import dao.ConnectionProvider;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import dao.PharmacyUtils;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.OpenPdf;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Su Raj
 */
public class SellMedicine extends javax.swing.JFrame {

    public String numberPattern = "^[0-9]*$";
    private int finalTotalPrice = 0;
    private String billId = "";
    private String username = "";
    private String medicineName = "";
    private int medicinequantity = 0;

    /**
     * Creates new form SellMedicine
     */
    public SellMedicine() {
        initComponents();
    }

    public SellMedicine(String tempUsername) {
        initComponents();
        username = tempUsername;
        setLocationRelativeTo(null);

    }

    private void medicineName(String nameOrUniqueId) {
        DefaultTableModel model = (DefaultTableModel) medicinesTable.getModel();
        model.setRowCount(0);
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from medicine where name like  '" + nameOrUniqueId + "%'or uniqueId like '" + nameOrUniqueId + "%'");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("uniqueId") + "- " + rs.getString("name")});

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void clearMedicineFields() {
        txtUniqueId.setText("");
        txtName.setText("");
        txtCompanyName.setText("");
        txtPricePerUnit.setText("");
        txtNoOfUnits.setText("");
        txtTotalPrice.setText("");
    }

    public String getUniqueId(String prefix) {
        return prefix + System.nanoTime();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        medicinesTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtUniqueId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCompanyName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPricePerUnit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNoOfUnits = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        btnAddToCart = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        lblFianlTotalPrice = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 95, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sell Medicine");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 19, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 1373, 10));

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 121, 302, -1));

        medicinesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine"
            }
        ));
        medicinesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicinesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(medicinesTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 161, 302, 520));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Medicine ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 98, -1, -1));
        getContentPane().add(txtUniqueId, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 121, 301, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 178, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 212, 301, -1));
        getContentPane().add(txtCompanyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 295, 301, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Price Per Unit");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 98, 85, -1));
        getContentPane().add(txtPricePerUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 121, 300, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No. of Units");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 178, -1, -1));

        txtNoOfUnits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoOfUnitsKeyReleased(evt);
            }
        });
        getContentPane().add(txtNoOfUnits, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 212, 300, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Price");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 261, -1, -1));
        getContentPane().add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1013, 295, 300, -1));

        btnAddToCart.setBackground(new java.awt.Color(255, 204, 153));
        btnAddToCart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add to cart.png"))); // NOI18N
        btnAddToCart.setText("Add To cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1195, 335, -1, -1));

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine ID", "Name", "Company Name", "Price Per Unit", "No Of Units", "Total Price"
            }
        ));
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(cartTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 374, 819, 250));

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, 20, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("RS : -");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 650, -1, -1));

        lblFianlTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFianlTotalPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblFianlTotalPrice.setText("00");
        getContentPane().add(lblFianlTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 650, -1, -1));

        jToggleButton1.setBackground(new java.awt.Color(255, 204, 153));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generate bill & print.png"))); // NOI18N
        jToggleButton1.setText("Purchase & Print");
        jToggleButton1.setToolTipText("");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 650, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("customer name: ");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Company Name");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, 170, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        medicineName("");
        txtUniqueId.setEditable(false);
        txtName.setEditable(false);
        txtCompanyName.setEditable(false);
        txtPricePerUnit.setEditable(false);
        txtTotalPrice.setEditable(false);


    }//GEN-LAST:event_formComponentShown

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String search = txtSearch.getText();
        medicineName(search);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void medicinesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicinesTableMouseClicked
        // TODO add your handling code here:
        int index = medicinesTable.getSelectedRow();
        TableModel model = medicinesTable.getModel();
        String nameOrUniqueId = model.getValueAt(index, 0).toString();

        String uniqueId[] = nameOrUniqueId.split("-", 0);
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from medicine where uniqueId=" + uniqueId[0] + "");
            while (rs.next()) {
                txtUniqueId.setText(uniqueId[0]);
                txtName.setText(rs.getString("name"));
                txtCompanyName.setText(rs.getString("companyName"));
                txtPricePerUnit.setText(rs.getString("price"));
                txtNoOfUnits.setText("");
                txtTotalPrice.setText("");

                medicineName = rs.getString("name");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_medicinesTableMouseClicked

    private void txtNoOfUnitsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoOfUnitsKeyReleased
        // TODO add your handling code here:
        String noOfUnits = txtNoOfUnits.getText();

        if (!noOfUnits.equals("")) {
            String price = txtPricePerUnit.getText();
            if (!noOfUnits.matches(numberPattern)) {
                JOptionPane.showMessageDialog(null, "No of unit is invalid.");

            }
            int totalPrice = Integer.parseInt(noOfUnits) * Integer.parseInt(price);
            txtTotalPrice.setText(String.valueOf(totalPrice));

        } else {
            txtTotalPrice.setText("");
        }
    }//GEN-LAST:event_txtNoOfUnitsKeyReleased

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        String noOfUnits = txtNoOfUnits.getText();
        String uniqueId = txtUniqueId.getText();

        if (!noOfUnits.equals("") && !uniqueId.equals("")) {
            String name = txtName.getText();
            String companyName = txtCompanyName.getText();
            String pricePerUnit = txtPricePerUnit.getText();
            String totalPrice = txtTotalPrice.getText();
            int checkStock = 0;
            int checkMedicineAlreadyExistInCart = 0;

            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("select *from medicine where uniqueId=" + uniqueId + "");

                while (rs.next()) {
                    if (rs.getInt("quantity") >= Integer.parseInt(noOfUnits)) {
                        checkStock = 1;
                        medicinequantity = Integer.parseInt(noOfUnits);
                    } else {
                        JOptionPane.showMessageDialog(null, "medicine is out of Stock. Only " + rs.getInt("quantity") + "left");

                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            if (checkStock == 1) {
                DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
                if (cartTable.getRowCount() != 0) {
                    for (int i = 0; i < cartTable.getRowCount(); i++) {
                        if (Integer.parseInt(dtm.getValueAt(i, 0).toString()) == Integer.parseInt(uniqueId)) {
                            checkMedicineAlreadyExistInCart = 1;
                            JOptionPane.showMessageDialog(null, "Medicine already exist in cart");

                        }
                    }
                }
                if (checkMedicineAlreadyExistInCart == 0) {
                    dtm.addRow(new Object[]{uniqueId, name, companyName, pricePerUnit, noOfUnits, totalPrice});
                    finalTotalPrice = finalTotalPrice + Integer.parseInt(totalPrice);
                    lblFianlTotalPrice.setText(String.valueOf(finalTotalPrice));
                    JOptionPane.showMessageDialog(null, "Added Sucessfully");
                }

                clearMedicineFields();
            } else {
                JOptionPane.showMessageDialog(null, "No.of Units and Medicine ID is required.");
            }

        }


    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void cartTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartTableMouseClicked
        // TODO add your handling code here:
        int index = cartTable.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to remove this Medicine", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = cartTable.getModel();
            String total = model.getValueAt(index, 5).toString();
            finalTotalPrice = finalTotalPrice - Integer.parseInt(total);
            lblFianlTotalPrice.setText(String.valueOf(finalTotalPrice));
            ((DefaultTableModel) cartTable.getModel()).removeRow(index);
        }
    }//GEN-LAST:event_cartTableMouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(finalTotalPrice);
        if (finalTotalPrice != 0) {
        billId = getUniqueId("Bill-");
        DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();

        // Check if any medicine is of type "Prescription"
        boolean prescriptionMedicineExists = false;
        String prescriptionMedicineName = ""; // To store the name of prescription medicine
        for (int i = 0; i < cartTable.getRowCount(); i++) {
            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT type, name FROM medicine WHERE uniqueId=" + Integer.valueOf(dtm.getValueAt(i, 0).toString()));
                if (rs.next()) {
                    String medicineType = rs.getString("type");
                    if (medicineType.equals("Prescription")) {
                        prescriptionMedicineExists = true;
                        prescriptionMedicineName = rs.getString("name");
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        String customerName = jTextField1.getText();
        String doctorName = "";
        String refNumber="";

        // If Prescription medicine exists, prompt for doctor name
        if (prescriptionMedicineExists) {
            doctorName = JOptionPane.showInputDialog(this, "Enter the name of the doctor who prescribed " + prescriptionMedicineName + ":");
            refNumber = JOptionPane.showInputDialog(this, "Reference No. Of Prescreption: ");
            if (doctorName == null || doctorName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Doctor name is required for Prescription medicines.");
                return; // Don't proceed further without the doctor's name
            }
        }

            try {
                SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-YYYY");
                Calendar cal = Calendar.getInstance();
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("insert into bill(billId,billDate,totalPaid,generatedBy,medicine,quantity) values(?,?,?,?,?,?)");
                ps.setString(1, billId);
                ps.setString(2, myFormat.format(cal.getTime()));
                ps.setInt(3, finalTotalPrice);
                ps.setString(4, username);
                ps.setString(5, medicineName);
                ps.setInt(6, medicinequantity);

                System.out.println("Executing query...");
                ps.executeUpdate();
                System.out.println("Query executed successfully.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            //create Bill
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(PharmacyUtils.billPath + "" + billId + ".pdf"));
                doc.open();
                Paragraph pharmacyName = new Paragraph("                                                      Pharmacy Management System\n");
                doc.add(pharmacyName);
                
                Paragraph customerParagraph = new Paragraph("Customer: " + customerName);
if (prescriptionMedicineExists) {
                // Add doctor's name to the PDF
                doc.add(new Paragraph("Prescribed by Doctor: " + doctorName));
                doc.add(new Paragraph("Prescription Reference No.: " + refNumber));
            }

                doc.add(customerParagraph);
                Paragraph starline = new Paragraph("***************************************************************************************");
                doc.add(starline);
                Paragraph details = new Paragraph("\tBill ID: " + billId + "\nDate: " + new Date() + "\nTotal paid: " + finalTotalPrice);
                doc.add(details);
                doc.add(starline);
                PdfPTable tbl = new PdfPTable(6);
                tbl.addCell("Medicine ID");
                tbl.addCell("Name");
                tbl.addCell("Company Name");
                tbl.addCell("Price Per Unit");
                tbl.addCell("No. OF Units");
                tbl.addCell("Sub Total Price");
                for (int i = 0; i < cartTable.getRowCount(); i++) {
                    String a = cartTable.getValueAt(i, 0).toString();
                    String b = cartTable.getValueAt(i, 1).toString();
                    String c = cartTable.getValueAt(i, 2).toString();
                    String d = cartTable.getValueAt(i, 3).toString();
                    String e = cartTable.getValueAt(i, 4).toString();
                    String f = cartTable.getValueAt(i, 5).toString();
                    tbl.addCell(a);
                    tbl.addCell(b);
                    tbl.addCell(c);
                    tbl.addCell(d);
                    tbl.addCell(e);
                    tbl.addCell(f);
                }
                doc.add(tbl);
                doc.add(starline);
                Paragraph thanksMsg = new Paragraph("Thankyou, Please Visit again.");
                doc.add(thanksMsg);
                OpenPdf.openById(String.valueOf(billId));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            doc.close();
            setVisible(false);
            new SellMedicine(username).setVisible(true);
        } 
        
        else {
            JOptionPane.showMessageDialog(null, "Please Add some medicine to cart");

        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellMedicine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAddToCart;
    private javax.swing.JTable cartTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel lblFianlTotalPrice;
    private javax.swing.JTable medicinesTable;
    private javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNoOfUnits;
    private javax.swing.JTextField txtPricePerUnit;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtUniqueId;
    // End of variables declaration//GEN-END:variables
}
