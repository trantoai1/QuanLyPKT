/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_5d;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author c0mrade
 */
public class menu extends javax.swing.JFrame {

    /**
     * Creates new form menu
     */
    DETHI dt = new DETHI();
    public menu() {
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
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setText("NHÂN VIÊN");
        jButton2.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton2.setRolloverEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jPanel1.add(jPanel4);

        jButton3.setBackground(new java.awt.Color(255, 255, 204));
        jButton3.setText("BÀI THI");
        jButton3.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jPanel1.add(jPanel5);

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setText("ĐỀ THI");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jPanel1.add(jPanel3);

        jButton4.setBackground(new java.awt.Color(255, 255, 204));
        jButton4.setText("CÁN BỘ COI THI");
        jButton4.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel6.add(jButton4);

        jPanel1.add(jPanel6);

        jButton5.setBackground(new java.awt.Color(255, 255, 204));
        jButton5.setText("CHI TIẾT LỊCH THI");
        jButton5.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel7.add(jButton5);

        jPanel1.add(jPanel7);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.jpg"))); // NOI18N
        jPanel8.add(jLabel2, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("QUẢN LÝ PHÒNG KHẢO THÍ");
        jPanel2.add(jLabel1);

        jPanel8.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel8, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dt.showWindows();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
