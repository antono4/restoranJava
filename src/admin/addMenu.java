/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import auth.login;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.helperClass;
import models.mysql;
import models.sessionID;

/**
 *
 * @author DELL
 */
public class addMenu extends javax.swing.JFrame {

    private final mysql db;
    private final Statement stm;
    private ResultSet res;
    private final HashMap<String, Integer> idKategori;
    public static int nn = 0;

    /**
     * Creates new form addMenu
     *
     * @throws java.sql.SQLException
     */
    public addMenu() throws SQLException {
        initComponents();
        
        this.db = new mysql();
        this.idKategori = new HashMap<String, Integer>();
        this.stm = mysql.getConnection().createStatement();
        
        setTitle("TAMBAH MENU RESTORAN");
        setPreferredSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
 
        this.fillMenuList();
        this.daftarKategori();

        headerTitle.setVisible(false);
        nameAlert.setVisible(false);
        priceAlert.setVisible(false);
        editBtn.setVisible(false);
        delBtn.setVisible(false);
        cancelBtn.setVisible(false);
        editID.setVisible(false);
        avPanel.setVisible(false);
        
        JTextField tf = inputHarga;
        
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent ke) {
            String value = tf.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ((int) ke.getKeyChar() == 8)) {
               tf.setEditable(true);
               priceAlert.setVisible(false);
               if(addMenu.nn > 0) {
                    priceAlert.setVisible(true);
                    if((int) ke.getKeyChar() != 8) {
                        priceAlert.setText(value +", seperti itu misalnya...");
                    }
                    addMenu.nn = 0;
               }
            } else {
               tf.setEditable(false);
               priceAlert.setVisible(true);
               priceAlert.setText("Anda hanya bisa memasukkan angka dan titik, atau menghapus");
               addMenu.nn = addMenu.nn + 1;
            }
         }
      });
        
        pack();
    }

    private void fillMenuList() {
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();

            String getMenuList = "SELECT m.*, c.categoryName FROM foods m JOIN foodCategory c ON c.categoryID = m.foodCategory ORDER BY m.name ASC";
            this.res = this.stm.executeQuery(getMenuList);

            if (this.res.next()) {
                do {
                    String foodID = this.res.getString("foodID");
                    String name = this.res.getString("name");
                    String cat = this.res.getString("categoryName");
                    Double price = this.res.getDouble("price");
                    boolean Status = this.res.getBoolean("isAvailable");

                    String status = (Status) ? "Tersedia" : "Tidak tersedia";
                    String harga = helperClass.formatRupiah(price);

                    list.addRow(new Object[]{foodID, name, cat, harga, status});
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void daftarKategori() {
        try {
            String getKategori = "SELECT * FROM `foodCategory` ORDER BY `categoryName` ASC";
            this.res = stm.executeQuery(getKategori);
            if (this.res.next()) {
                do {
                    int id = this.res.getInt("categoryID");
                    String item = this.res.getString("categoryName");
                    daftarKategori.addItem(item);
                    //.addItem(item);

                    this.idKategori.put(item, id);
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTable() {
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
            list.setRowCount(0);

            String getMenuList = "SELECT m.*, c.categoryName FROM foods m JOIN foodCategory c ON c.categoryID = m.foodCategory ORDER BY m.name ASC";
            this.res = this.stm.executeQuery(getMenuList);

            if (this.res.next()) {
                do {
                    String foodID = this.res.getString("foodID");
                    String name = this.res.getString("name");
                    String cat = this.res.getString("categoryName");
                    Double price = this.res.getDouble("price");
                    boolean Status = this.res.getBoolean("isAvailable");

                    String status = (Status) ? "Tersedia" : "Tidak tersedia";
                    String harga = helperClass.formatRupiah(price);

                    list.addRow(new Object[]{foodID, name, cat, harga, status});
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
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

        menuPanel = new javax.swing.JScrollPane();
        menuLists = new javax.swing.JTable();
        catBtn = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        headerTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        daftarKategori = new javax.swing.JComboBox();
        inputNama = new javax.swing.JTextField();
        inputHarga = new javax.swing.JTextField();
        priceAlert = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameAlert = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        avPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        isAvailable = new javax.swing.JRadioButton();
        editBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        addBG = new javax.swing.JLabel();
        editID = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tambah Menu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Kategori", "Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuLists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuListsMouseClicked(evt);
            }
        });
        menuPanel.setViewportView(menuLists);

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 560, 510));

        catBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/list.png"))); // NOI18N
        catBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        catBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                catBtnMouseClicked(evt);
            }
        });
        getContentPane().add(catBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back.png"))); // NOI18N
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 12)); // NOI18N
        jLabel1.setText("TAMBAH MENU BARU");
        addPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        headerTitle.setText("{{msg}}");
        addPanel.add(headerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel2.setText("Nama:");
        addPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel7.setText(" Harga:");
        addPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        daftarKategori.setModel(new javax.swing.DefaultComboBoxModel());
        addPanel.add(daftarKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 203, -1));

        inputNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamaActionPerformed(evt);
            }
        });
        addPanel.add(inputNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 200, -1));

        inputHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHargaActionPerformed(evt);
            }
        });
        addPanel.add(inputHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 170, -1));

        priceAlert.setText("{{hargaMsg}}");
        addPanel.add(priceAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, 20));

        jLabel8.setText("Rp.");
        addPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        jLabel6.setText("Kategori:");
        addPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 52, -1));

        nameAlert.setText("{{namaMsg}}");
        addPanel.add(nameAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, 20));

        addBtn.setText("Tambah");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        addPanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        jLabel3.setText("Status:");

        isAvailable.setText("Tersedia");

        javax.swing.GroupLayout avPanelLayout = new javax.swing.GroupLayout(avPanel);
        avPanel.setLayout(avPanelLayout);
        avPanelLayout.setHorizontalGroup(
            avPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avPanelLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(81, 81, 81)
                .addComponent(isAvailable)
                .addGap(0, 127, Short.MAX_VALUE))
        );
        avPanelLayout.setVerticalGroup(
            avPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(avPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(isAvailable))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        addPanel.add(avPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 310, -1));

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        addPanel.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        delBtn.setText("Hapus");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });
        addPanel.add(delBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        cancelBtn.setText("Batal");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        addPanel.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        addBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background/admin/add menu edit.jpg"))); // NOI18N
        addPanel.add(addBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 340));

        editID.setText("{{editID}}");
        addPanel.add(editID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        getContentPane().add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 510, 340));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background/admin/TAMBAH MENU.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try {
            String nama = inputNama.getText();
            String harga = inputHarga.getText();
            Object kategori = daftarKategori.getSelectedItem();
            int id = this.idKategori.get((String) kategori);
            
            int vHarga = Integer.parseInt(harga);

            if (nama.equals("")) {
                inputNama.requestFocus();
                nameAlert.setText("Masukkan nama menu!");
                nameAlert.setVisible(true);
            } else {
                nameAlert.setText("");
                nameAlert.setVisible(false);
            }
            if (harga.equals("")) {
                priceAlert.setText("Masukkan harga!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }
            
            if (vHarga <= 0) {
                priceAlert.setText("MASUKKAN HARGA DENGAN BENAR !!!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }

            if (!nama.equals("") && !harga.equals("") && vHarga > 0) {
                String tambahMenu = "INSERT INTO `foods` VALUES(NULL, '" + nama + "', '" + harga + "', 1, " + id + ")";
                int tambah = this.stm.executeUpdate(tambahMenu);

                if (tambah > 0) {
                    inputNama.setText("");
                    inputHarga.setText("");

                    headerTitle.setText("Menu baru ditambahkan!");
                    this.updateTable();
                    daftarKategori.setSelectedIndex(0);
                } else {
                    headerTitle.setText("Terjadi kesalahan saat menambahkan menu. Silahkan ulangi kembali.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void menuListsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListsMouseClicked
        addMenu.nn = 0;
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
            int sel = menuLists.getSelectedRow();
            String id = list.getValueAt(sel, 0).toString();
            int ID = Integer.parseInt(id);

            addBtn.setVisible(false);
            editBtn.setVisible(true);
            delBtn.setVisible(true);
            cancelBtn.setVisible(true);
            avPanel.setVisible(true);

            String getData = "SELECT f.*, c.categoryName FROM foods f JOIN foodCategory c ON c.categoryID = f.foodCategory WHERE foodID = '" + ID + "'";
            this.res = this.stm.executeQuery(getData);
            if (this.res.next()) {
                String nama = this.res.getString("name");
                String Harga = this.res.getString("price");
                String harga = Harga.substring(0, Harga.length() - 3);
                String kategori = this.res.getString("categoryName");
                boolean isAv = this.res.getBoolean("isAvailable");

                inputNama.setText(nama);
                inputHarga.setText(harga);
                editID.setText(String.valueOf(ID));
                isAvailable.setSelected(isAv);

                daftarKategori.setSelectedItem(kategori);
            }
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuListsMouseClicked

    private void inputHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHargaActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
        int doHapus = JOptionPane.showConfirmDialog(addPanel, (Object) "Yakin ingin menghapus menu ini?", "Hapus menu", 0);

        if (doHapus == 0) {
            try {
                String ID = editID.getText();
                String Hapus = "DELETE FROM `foods` WHERE `foodID` = '" + ID + "'";
                int hapus = this.stm.executeUpdate(Hapus);

                if (hapus > 0) {
                    headerTitle.setText("Menu berhasil dihapus");
                    headerTitle.setVisible(true);

                    inputHarga.setText("");
                    inputNama.setText("");
                    daftarKategori.setSelectedIndex(0);

                    this.updateTable();
                    avPanel.setVisible(false);
                    addBtn.setVisible(true);
                    delBtn.setVisible(false);
                    editBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_delBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
        String ID = editID.getText();

        String nama = inputNama.getText();
        String harga = inputHarga.getText();
        String kategori = daftarKategori.getSelectedItem().toString();
        int idKategori = this.idKategori.get(kategori);
        boolean isAv = isAvailable.isSelected();
        int avv = (isAv) ? 1 : 0;
        int vHarga = Integer.parseInt(harga);
        if (nama.equals("")) {
            inputNama.requestFocus();
            nameAlert.setText("Masukkan nama menu!");
            nameAlert.setVisible(true);
        } else {
            nameAlert.setText("");
            nameAlert.setVisible(false);
        }
        if (harga.equals("")) {
            inputHarga.requestFocus();
            priceAlert.setText("Masukkan harga!");
            priceAlert.setVisible(true);
        } else {
            priceAlert.setText("");
            priceAlert.setVisible(false);
        }
        if (vHarga <= 0) {
                priceAlert.setText("MASUKKAN HARGA DENGAN BENAR !!!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }

        if (!nama.equals("") && !harga.equals("")) {
            try {
                String update = "UPDATE `foods` SET `name` = '" + nama + "', `price` = '" + harga + "', `foodCategory` = '" + idKategori + "', `isAvailable` = '" + avv + "' WHERE `foodID` = '" + ID + "'";
                int updateMenu = this.stm.executeUpdate(update);

                if (updateMenu > 0) {
                    headerTitle.setText("Menu diperbarui!");
                    headerTitle.setVisible(true);

                    this.updateTable();
                } else {
                    headerTitle.setText("Gagal memperbarui menu. Harap ulangi kembali");
                    headerTitle.setVisible(true);
                }

                inputNama.setText("");
                inputHarga.setText("");
                daftarKategori.setSelectedIndex(0);
                avPanel.setVisible(false);
                addBtn.setVisible(true);
                editBtn.setVisible(false);
                delBtn.setVisible(false);
                cancelBtn.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        try {
            new admin().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backBtnMouseClicked

    private void catBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catBtnMouseClicked
        try {
            new addKategori().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_catBtnMouseClicked

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        addBtn.setVisible(true);
        editBtn.setVisible(false);
        delBtn.setVisible(false);
        cancelBtn.setVisible(false);
        avPanel.setVisible(false);
        
        inputHarga.setText("");
        inputNama.setText("");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void inputNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamaActionPerformed

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
            java.util.logging.Logger.getLogger(addMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new addMenu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(addMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBG;
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel avPanel;
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel catBtn;
    private javax.swing.JComboBox daftarKategori;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel editID;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JTextField inputHarga;
    private javax.swing.JTextField inputNama;
    private javax.swing.JRadioButton isAvailable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTable menuLists;
    private javax.swing.JScrollPane menuPanel;
    private javax.swing.JLabel nameAlert;
    private javax.swing.JLabel priceAlert;
    // End of variables declaration//GEN-END:variables
}
