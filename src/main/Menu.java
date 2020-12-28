/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import chucnang.NhanVien;
import chucnang.CBCoiThi;
import chucnang.LichThi;
import chucnang.BaiThi;
import chucnang.DeThi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author c0mrade
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    DeThi dt = new DeThi();
    BaiThi bt = new BaiThi();
    CBCoiThi cb = new CBCoiThi();
    LichThi lt = new LichThi();
    NhanVien nv = new NhanVien();
    public Menu() {
        initComponents();
        addEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jBTN_NV = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jBTN_BT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jBTN_ĐT = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jBTN_CBCT = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jBTN_CTLT = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLB_IMG = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLB_TĐ = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jBTN_NV.setBackground(new java.awt.Color(255, 255, 204));
        jBTN_NV.setText("NHÂN VIÊN");
        jBTN_NV.setFocusPainted(false);
        jBTN_NV.setPreferredSize(new java.awt.Dimension(150, 50));
        jBTN_NV.setRolloverEnabled(false);
        jBTN_NV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTN_NVMouseClicked(evt);
            }
        });
        jPanel4.add(jBTN_NV);

        jPanel1.add(jPanel4);

        jBTN_BT.setBackground(new java.awt.Color(255, 255, 204));
        jBTN_BT.setText("CHẤM  THI");
        jBTN_BT.setPreferredSize(new java.awt.Dimension(150, 50));
        jBTN_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTN_BTActionPerformed(evt);
            }
        });
        jPanel5.add(jBTN_BT);

        jPanel1.add(jPanel5);

        jBTN_ĐT.setBackground(new java.awt.Color(255, 255, 204));
        jBTN_ĐT.setText("RA ĐỀ");
        jBTN_ĐT.setPreferredSize(new java.awt.Dimension(150, 50));
        jBTN_ĐT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTN_ĐTActionPerformed(evt);
            }
        });
        jPanel3.add(jBTN_ĐT);

        jPanel1.add(jPanel3);

        jBTN_CBCT.setBackground(new java.awt.Color(255, 255, 204));
        jBTN_CBCT.setText("CÁN BỘ COI THI");
        jBTN_CBCT.setPreferredSize(new java.awt.Dimension(150, 50));
        jBTN_CBCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTN_CBCTMouseClicked(evt);
            }
        });
        jPanel6.add(jBTN_CBCT);

        jPanel1.add(jPanel6);

        jBTN_CTLT.setBackground(new java.awt.Color(255, 255, 204));
        jBTN_CTLT.setText("CHI TIẾT LỊCH THI");
        jBTN_CTLT.setPreferredSize(new java.awt.Dimension(150, 50));
        jBTN_CTLT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTN_CTLTMouseClicked(evt);
            }
        });
        jPanel7.add(jBTN_CTLT);

        jPanel1.add(jPanel7);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLB_IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.jpg"))); // NOI18N
        jPanel8.add(jLB_IMG, java.awt.BorderLayout.CENTER);

        jLB_TĐ.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLB_TĐ.setForeground(new java.awt.Color(0, 51, 51));
        jLB_TĐ.setText("QUẢN LÝ PHÒNG KHẢO THÍ");
        jPanel2.add(jLB_TĐ);

        jPanel8.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel8, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTN_ĐTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTN_ĐTActionPerformed
        // TODO add your handling code here:
        dt.showWindows();
        dt.load_BangGiangVienRaDe();
        //dt.load();
        bt.setVisible(false);
        cb.setVisible(false);
        lt.setVisible(false);
        nv.setVisible(false);
        //this.setVisible(false);
    }//GEN-LAST:event_jBTN_ĐTActionPerformed

    private void jBTN_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTN_BTActionPerformed
        // TODO add your handling code here:
        dt.setVisible(false);
        bt.setVisible(true);
        bt.load_BangGiangVienChamThi();
        //bt.load();
        cb.setVisible(false);
        lt.setVisible(false);
        nv.setVisible(false);
    }//GEN-LAST:event_jBTN_BTActionPerformed

    private void jBTN_NVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTN_NVMouseClicked
        // TODO add your handling code here:
        dt.setVisible(false);
        bt.setVisible(false);
        cb.setVisible(false);
        lt.setVisible(false);
        nv.setVisible(true);
        nv.loadNV();
        //nv.load();
        
    }//GEN-LAST:event_jBTN_NVMouseClicked

    private void jBTN_CBCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTN_CBCTMouseClicked
        // TODO add your handling code here:
        dt.setVisible(false);
        bt.setVisible(false);
        cb.setVisible(true);
        cb.loadLopTC2();
        cb.loadDuLieu();
        lt.setVisible(false);
        nv.setVisible(false);
        // cb.setVisible(true);
                if(!cb.status)
                {
                    int ret = JOptionPane.showConfirmDialog(cb, "Chưa có lịch thi cho các lớp, bạn có muốn thêm lịch thi trước?");
                    if(ret == JOptionPane.OK_OPTION)
                    {
                         cb.setVisible(false);
                         lt.setVisible(true);
                        // lt.load();
                    }
                    else
                        cb.setVisible(false); 
                }
    }//GEN-LAST:event_jBTN_CBCTMouseClicked

    private void jBTN_CTLTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTN_CTLTMouseClicked
        // TODO add your handling code here:
        LichThi.setHocKy("");
        LichThi.setDot("");
        LichThi.setNienKhoa("");
        lt.loadDuLieu();
        dt.setVisible(false);
        bt.setVisible(false);
        cb.setVisible(false);
        lt.setVisible(true);
        //lt.load();
        nv.setVisible(false);
    }//GEN-LAST:event_jBTN_CTLTMouseClicked
    public void addEvents()
    {
       dt.getBtnQuayLai().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               dt.setVisible(false);
               setVisible(true);
           }
       });
    }
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTN_BT;
    private javax.swing.JButton jBTN_CBCT;
    private javax.swing.JButton jBTN_CTLT;
    private javax.swing.JButton jBTN_NV;
    private javax.swing.JButton jBTN_ĐT;
    private javax.swing.JLabel jLB_IMG;
    private javax.swing.JLabel jLB_TĐ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}