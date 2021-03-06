/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dapur;

import java.awt.Dimension;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import models.mysql;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DELL
 */
public class daftarPesanan extends javax.swing.JFrame {
    private final mysql db;
    private final Statement stm;
    private ResultSet res;
    private final HashMap<String, Integer> idOrder;

    /**
     * Creates new form pesanan
     */
    public daftarPesanan() throws SQLException {
        initComponents();
        
        setTitle("DAFTAR PESANAN DALAM ANTRIAN | DAPUR");
        setPreferredSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);

        this.db = new mysql();
        this.stm = mysql.getConnection().createStatement();
        this.idOrder = new HashMap<String, Integer>();

        this.updateOrderList();
        finishAlert.setVisible(false);
        orderItems.setVisible(false);
        daftarOrder.setEnabled(false);
        
        pack();
    }
    
    private void updateOrderList() {
        try {
            DefaultTableModel orderLists = (DefaultTableModel) daftarPesanan.getModel();
            this.res = this.stm.executeQuery("SELECT * FROM `orders` WHERE `status` = '0' ORDER BY `orderTime` ASC");
            orderLists.setRowCount(0);
            
            if(this.res.next()) {
                daftarOrder.addItem("Pilih order...");
                do {
                    String orderID = this.res.getString("orderID");
                    String orderNumber = this.res.getString("orderNumber");
                    Date orderDate = this.res.getDate("orderTime");
                    Time orderTime = this.res.getTime("orderTime");
                    
                    SimpleDateFormat dateFormater = new SimpleDateFormat("EEEE, dd MMM YYYY");
                    String ordDate = dateFormater.format(orderDate);
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                    String ordTime = timeFormatter.format(orderTime);
                    
                    String orderDateTime = ordDate +" "+ ordTime;
                    
                    orderLists.addRow(new Object[] {orderID, orderNumber, orderDateTime});
                    
                    int ordID = Integer.parseInt(orderID);
                    String item = "Order [" + orderNumber + "]";
                    daftarOrder.addItem(item);
                    this.idOrder.put(item, ordID);
                }
                while(this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        finishAlert = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        daftarOrder = new javax.swing.JComboBox();
        finishOrder = new javax.swing.JButton();
        bgDaftarOrder = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        daftarPesanan = new javax.swing.JTable();
        orderItems = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ordItems = new javax.swing.JTable();
        orderNumber = new javax.swing.JLabel();
        bgOrderItems = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Pesanan");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back.png"))); // NOI18N
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setText("TANDAI ORDER SUDAH SELESAI");

        finishAlert.setText("{{finishAlert}}");

        jLabel3.setText("Pilih Order:");

        daftarOrder.setModel(new javax.swing.DefaultComboBoxModel());
        daftarOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarOrderActionPerformed(evt);
            }
        });

        finishOrder.setText("Selesai");
        finishOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(finishAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(178, 178, 178))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(finishOrder)
                            .addComponent(daftarOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finishAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daftarOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(finishOrder)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 350, 160));
        getContentPane().add(bgDaftarOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        daftarPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "No. Order", "Waktu Order"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        daftarPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarPesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(daftarPesanan);
        if (daftarPesanan.getColumnModel().getColumnCount() > 0) {
            daftarPesanan.getColumnModel().getColumn(0).setMaxWidth(40);
            daftarPesanan.getColumnModel().getColumn(1).setMaxWidth(100);
            daftarPesanan.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 340, -1));

        orderItems.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ordItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nama", "Jumlah", "Kategori"
            }
        ));
        jScrollPane2.setViewportView(ordItems);
        if (ordItems.getColumnModel().getColumnCount() > 0) {
            ordItems.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        orderItems.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 420, 290));

        orderNumber.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderNumber.setForeground(new java.awt.Color(255, 255, 255));
        orderNumber.setText("{{ordNumber}}");
        orderItems.add(orderNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        bgOrderItems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background/dapur/dapur order items.png"))); // NOI18N
        orderItems.add(bgOrderItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(orderItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 440, 420));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background/dapur/dapur orders.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finishOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishOrderActionPerformed
        try {
            Object order = daftarOrder.getSelectedItem();
            int id = this.idOrder.get((String) order);

            String update = "UPDATE `orders` SET `status` = '1' WHERE `orderID` = '" + id + "'";
            int updateOrder = this.stm.executeUpdate(update);
            if (updateOrder > 0) {
                finishAlert.setText(order + " diselesaikan dari dapur");
                finishAlert.setVisible(true);

                daftarOrder.removeItem(order);
                this.updateOrderList();

                int size = daftarOrder.getItemCount();
                if (size > 0) {
                    daftarOrder.setSelectedIndex(0);
                }
                
                orderItems.setVisible(false);
            } else {
                finishAlert.setText("Terjadi kesalahan. Harap ulangi kembali.");
                finishAlert.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_finishOrderActionPerformed

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        new dapur().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnMouseClicked

    private void daftarPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarPesananMouseClicked
        try {
            DefaultTableModel orderItem = (DefaultTableModel) daftarPesanan.getModel();
            DefaultTableModel ordItem = (DefaultTableModel) ordItems.getModel();
            ordItem.setRowCount(0);
            
            int sel = daftarPesanan.getSelectedRow();
            String ID = orderItem.getValueAt(sel, 0).toString();
            
            orderItems.setVisible(true);
            
            String getItem = "SELECT o.orderID, o.orderNumber, i.orderQuantity as qty, f.foodID, f.name, c.categoryName FROM `orders` `o` JOIN `orderItems` `i` ON i.orderID = o.orderID JOIN `foods` f ON f.foodID = i.foodID JOIN foodCategory c ON c.categoryID = f.foodCategory WHERE o.orderID = '"+ ID +"'";
            this.res = this.stm.executeQuery(getItem);
            if(this.res.next()) {
                String ordNumber = this.res.getString("orderNumber");
                do {
                    String id = this.res.getString("foodID");
                    String nama = this.res.getString("name");
                    String jumlah = this.res.getString("qty");
                    String kategori = this.res.getString("categoryName");
                
                    ordItem.addRow(new Object[] {id, nama, jumlah, kategori});
                }
                while(this.res.next());
                
                String selOrder = "Order ["+ ordNumber +"]";
                daftarOrder.setSelectedItem(selOrder);
                daftarOrder.setEnabled(true);
                orderNumber.setText("Order #"+ ordNumber);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_daftarPesananMouseClicked

    private void daftarOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daftarOrderActionPerformed

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
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new daftarPesanan().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bgDaftarOrder;
    private javax.swing.JLabel bgOrderItems;
    private javax.swing.JComboBox daftarOrder;
    private javax.swing.JTable daftarPesanan;
    private javax.swing.JLabel finishAlert;
    private javax.swing.JButton finishOrder;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable ordItems;
    private javax.swing.JPanel orderItems;
    private javax.swing.JLabel orderNumber;
    // End of variables declaration//GEN-END:variables
}
