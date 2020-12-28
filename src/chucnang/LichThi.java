/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chucnang;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ketnoi.Conn;

/**
 *
 * @author trantoai
 */
public class LichThi extends javax.swing.JFrame {

    /**
     * Creates new form LichThi
     */
    private static String hocKy , dot,nienKhoa;

    public static void setHocKy(String hocKy) {
        LichThi.hocKy = hocKy;
    }

    public static void setDot(String dot) {
        LichThi.dot = dot;
    }

    public static void setNienKhoa(String nienKhoa) {
        LichThi.nienKhoa = nienKhoa;
    }
    
    public LichThi() {
        initComponents();
        LichThi.hocKy = LichThi.dot = LichThi.nienKhoa= "";
        this.loadDuLieu();
        this.loadMY();
        this.loadRoom();
        this.loadTiet();
        this.loadLopTC();
    }
    private int searchLTInTable(String maLTC)
    {
        int pos = -1;
        for(int i=0;i<jTable_CTLT.getRowCount();i++)
        {
            if(jTable_CTLT.getValueAt(i, 0).toString().equals(maLTC))
            {
                 pos = i;
                 break;
            }
               
        }
        return pos;
    }
    private String searchLTC(String s) 
    {
        String result = "";
        Connection conn = Conn.conn();
        String sp[] = s.split("_");
        String sql = "SELECT MALOPTC FROM V_LOPTC TC WHERE TC.TENMON = ? AND TC.NIENKHOA = ? AND TC.HOCKY = ? AND TC.NHOM = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp[0]);
            ps.setString(2, sp[1]);
            ps.setString(3, sp[2]);
            ps.setString(4, sp[3]);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                result = rs.getString("MALOPTC");
                break;
            }
            rs.close();
            ps.close();
            conn.close();
            
            
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-searchloptc");
        }
        return result;//Tra ve ma lop tin chi
    }
    private boolean searchLTC_LT(String s) 
    {
        boolean result = false;
        Connection conn = Conn.conn();
       
        String sql = "SELECT * FROM CTLICHTHI TC WHERE TC.MALOPTC = ? ";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                result = true;
                break;
            }
            rs.close();
            ps.close();
            conn.close();
            
            
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-searchloptc");
        }
        return result;//Trả về kết quả tìm kiếm
    }
    private void loadLopTC()
    {
        Connection conn = Conn.conn();
        String sql = "SELECT LOPTC.*,MON.TENMON FROM LOPTC,MON WHERE LOPTC.MAMON = MON.MAMON order by MON.TENMON,LOPTC.NIENKHOA,LOPTC.HOCKY DESC";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                jCB_Class.addItem(rs.getString("TENMON")+"_"+rs.getString("NIENKHOA").trim()+"_"+rs.getString("HOCKY")+"_"+rs.getString("NHOM"));
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-loptc");
        }
    }
    private void loadTiet()
    {
        for(int i=1;i<=12;i++)
        {
            jCB_Tiet.addItem(Integer.toString(i));
        }
    }
    private void loadRoom()
    {
        Connection conn = Conn.conn();
        String sql = "SELECT MAPHONG FROM PHONG";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                jCB_Room.addItem(rs.getString("MAPHONG"));
            }
            rs.close();
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-MP");
        }
    }
    public void loadDuLieu() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_CTLT.getModel();
        dtm.setRowCount(0);
        Connection conn = Conn.conn();
        String sql ;
                if(LichThi.nienKhoa.equals(""))
                    sql = "EXEC SP_LICHTHI2 ";
                else
                    sql = "EXEC SP_LICHTHI ?,?,? ";
        Vector vt ;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
                if(!LichThi.nienKhoa.equals(""))
                {
                    ps.setString(1, LichThi.dot);
                    ps.setString(2, LichThi.hocKy);
                    ps.setString(3, LichThi.nienKhoa);
                }
                
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                vt = new Vector();
                vt.add(rs.getString("MALOPTC"));
                vt.add(rs.getString("TENMON"));
                vt.add(rs.getString("HOTEN"));
                vt.add(rs.getString("MALOP"));
                vt.add(rs.getString("NHOM"));
                vt.add(rs.getString("NGAYTHI"));
                vt.add(rs.getString("MAPHONG"));
                vt.add(rs.getString("TIETBATDAU"));
                 vt.add(rs.getString("SLDANGKY"));
                 vt.add(rs.getString("DOTTHI"));
                dtm.addRow(vt);
            }
            jTable_CTLT.setModel(dtm);
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-full LT");
        }
    }
    private void loadMY()
    {
        
        for(int i=1;i<=12;i++)
        {
            jCB_M.addItem("Tháng "+ i);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=year-10;i<year+10;i++)
        {
            jCB_Y.addItem(Integer.toString(i));
        }
        jCB_Y.setSelectedItem(Integer.toString(year));
        this.loadD();
    }
    public void loadD()
    {
        int num[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        try{
            //System.out.println(jCB_Y.getSelectedItem());

            int year = Integer.valueOf(jCB_Y.getItemAt(jCB_Y.getSelectedIndex()));
            if(  year%4 ==0)
            {
                num[1] = 29;
            }
        }catch(NumberFormatException e)
        {
            
        }
        jCB_D.removeAllItems();
        for(int i = 0;i<num[jCB_M.getSelectedIndex()];i++)
        {
            jCB_D.addItem(Integer.toString(i+1));
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

        jDialog_Search = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel_NK = new javax.swing.JLabel();
        jLabel_HK = new javax.swing.JLabel();
        jLabel_Dot2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTF_NK = new javax.swing.JTextField();
        jTF_HK = new javax.swing.JTextField();
        jTF_DT = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton_Search = new javax.swing.JButton();
        jButton_Exit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Class = new javax.swing.JLabel();
        jCB_Class = new javax.swing.JComboBox<>();
        jLabel_Room = new javax.swing.JLabel();
        jCB_Room = new javax.swing.JComboBox<>();
        jLabel_M = new javax.swing.JLabel();
        jCB_M = new javax.swing.JComboBox<>();
        jCB_D = new javax.swing.JComboBox<>();
        jLabel_D = new javax.swing.JLabel();
        jLabel_Y = new javax.swing.JLabel();
        jCB_Y = new javax.swing.JComboBox<>();
        jLabel_Dot = new javax.swing.JLabel();
        jCB_Dot = new javax.swing.JComboBox<>();
        jLabel_Tiet = new javax.swing.JLabel();
        jTF_Num = new javax.swing.JTextField();
        jLabel_Num = new javax.swing.JLabel();
        jCB_Tiet = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jButton_Del = new javax.swing.JButton();
        jButton_S = new javax.swing.JButton();
        jButton_ClearSearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CTLT = new javax.swing.JTable();

        jDialog_Search.setTitle("Lọc lịch thi theo đợt");
        jDialog_Search.setMinimumSize(new java.awt.Dimension(467, 286));
        jDialog_Search.setResizable(false);
        jDialog_Search.setSize(new java.awt.Dimension(467, 286));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nhập thông tin sau");
        jLabel2.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setLayout(new java.awt.GridLayout(3, 0));

        jLabel_NK.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_NK.setText("Niên khóa");
        jPanel6.add(jLabel_NK);

        jLabel_HK.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_HK.setText("Học kỳ");
        jPanel6.add(jLabel_HK);

        jLabel_Dot2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Dot2.setText("Đợt thi");
        jPanel6.add(jLabel_Dot2);

        jPanel7.setLayout(new java.awt.GridLayout(3, 0));
        jPanel7.add(jTF_NK);
        jPanel7.add(jTF_HK);

        jTF_DT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_DTActionPerformed(evt);
            }
        });
        jPanel7.add(jTF_DT);

        jPanel8.setLayout(new java.awt.GridLayout(1, 2, 0, 6));

        jButton_Search.setText("Tìm");
        jButton_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SearchMouseClicked(evt);
            }
        });
        jPanel8.add(jButton_Search);

        jButton_Exit.setText("Thoát");
        jButton_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ExitMouseClicked(evt);
            }
        });
        jPanel8.add(jButton_Exit);

        javax.swing.GroupLayout jDialog_SearchLayout = new javax.swing.GroupLayout(jDialog_Search.getContentPane());
        jDialog_Search.getContentPane().setLayout(jDialog_SearchLayout);
        jDialog_SearchLayout.setHorizontalGroup(
            jDialog_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDialog_SearchLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialog_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_SearchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog_SearchLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jDialog_SearchLayout.setVerticalGroup(
            jDialog_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_SearchLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 91, Short.MAX_VALUE))
        );

        setMinimumSize(new java.awt.Dimension(1120, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(1120, 650));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHI TIẾT LỊCH THI");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel_Class.setText("Lớp tín chỉ");

        jCB_Class.setMaximumSize(new java.awt.Dimension(324, 25));
        jCB_Class.setMinimumSize(new java.awt.Dimension(324, 25));
        jCB_Class.setPreferredSize(new java.awt.Dimension(324, 25));
        jCB_Class.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_ClassItemStateChanged(evt);
            }
        });

        jLabel_Room.setText("Phòng thi");

        jCB_Room.setMaximumSize(new java.awt.Dimension(90, 28));
        jCB_Room.setMinimumSize(new java.awt.Dimension(90, 28));
        jCB_Room.setPreferredSize(new java.awt.Dimension(90, 28));

        jLabel_M.setText("Tháng");

        jCB_M.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_MItemStateChanged(evt);
            }
        });

        jLabel_D.setText("Ngày");

        jLabel_Y.setText("Năm");

        jCB_Y.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_YItemStateChanged(evt);
            }
        });

        jLabel_Dot.setText("Đợt thi");

        jCB_Dot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jLabel_Tiet.setText("Tiết bắt đầu");

        jLabel_Num.setText("Số lượng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Class)
                    .addComponent(jLabel_Room)
                    .addComponent(jLabel_D))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCB_D, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCB_Room, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(142, 142, 142)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_Dot)
                            .addComponent(jLabel_M))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCB_Dot, 0, 83, Short.MAX_VALUE)
                            .addComponent(jCB_M, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCB_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Y)
                    .addComponent(jLabel_Num)
                    .addComponent(jLabel_Tiet))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTF_Num)
                    .addComponent(jCB_Tiet, 0, 81, Short.MAX_VALUE)
                    .addComponent(jCB_Y, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(204, 204, 204))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Class)
                        .addComponent(jLabel_Num)
                        .addComponent(jTF_Num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCB_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCB_Dot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Dot)
                    .addComponent(jCB_Room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Room)
                    .addComponent(jLabel_Tiet)
                    .addComponent(jCB_Tiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Y)
                            .addComponent(jCB_Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCB_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_M)
                            .addComponent(jCB_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_D))))
                .addContainerGap())
        );

        jButton_Add.setText("Thêm");
        jButton_Add.setMaximumSize(new java.awt.Dimension(90, 23));
        jButton_Add.setMinimumSize(new java.awt.Dimension(90, 23));
        jButton_Add.setPreferredSize(new java.awt.Dimension(90, 23));
        jButton_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AddMouseClicked(evt);
            }
        });

        jButton_Del.setText("Xóa");
        jButton_Del.setMaximumSize(new java.awt.Dimension(90, 23));
        jButton_Del.setMinimumSize(new java.awt.Dimension(90, 23));
        jButton_Del.setPreferredSize(new java.awt.Dimension(90, 23));
        jButton_Del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_DelMouseClicked(evt);
            }
        });

        jButton_S.setText("Lọc lịch thi");
        jButton_S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SMouseClicked(evt);
            }
        });

        jButton_ClearSearch.setText("Xóa lọc");
        jButton_ClearSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ClearSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton_Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_S)
                .addGap(18, 18, 18)
                .addComponent(jButton_ClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_S)
                    .addComponent(jButton_ClearSearch))
                .addContainerGap())
        );

        jTable_CTLT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã LTC", "Môn học", "Giảng viên", "Lớp", "Nhóm", "Ngày thi", "Phòng thi", "Tiết bắt đầu", "Số lượng", "Đợt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_CTLT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CTLTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_CTLT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCB_MItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_MItemStateChanged
        // TODO add your handling code here:
        this.loadD();
    }//GEN-LAST:event_jCB_MItemStateChanged

    private void jCB_YItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_YItemStateChanged
        // TODO add your handling code here:
        this.loadD();
    }//GEN-LAST:event_jCB_YItemStateChanged
    private ArrayList<String> searchHKD( String maLTC)
    {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT LOPTC.HOCKY,LOPTC.NIENKHOA,LT.DOTTHI FROM CTLICHTHI LT,LOPTC WHERE LOPTC.MALOPTC=? AND LOPTC.MALOPTC=LT.MALOPTC";
        
        Connection conn = Conn.conn();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, maLTC);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                result.add(0,rs.getString("DOTTHI"));
                result.add(1,rs.getString("HOCKY"));
                result.add(2,rs.getString("NIENKHOA"));
               
            }
            
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           result.add(0,"");
        }
        
        return result;
    }
    private void jCB_ClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_ClassItemStateChanged
        // TODO add your handling code here:
        
        if(searchLTC_LT(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))))
        {
            int x = 0;
            jButton_Add.setText("Sửa");
            try{
               x = searchLTInTable(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))); 
               DefaultTableModel tableLT = (DefaultTableModel) jTable_CTLT.getModel();
                String date[] =  tableLT.getValueAt(x, 5).toString().split("-");
                jCB_Room.setSelectedItem(tableLT.getValueAt(x, 6));
                jCB_Tiet.setSelectedItem(tableLT.getValueAt(x, 7));
                jCB_Dot.setSelectedItem(tableLT.getValueAt(x, 9));
                jTF_Num.setText((String)tableLT.getValueAt(x, 8));
                jCB_Y.setSelectedItem(date[0]);
                
                jCB_M.setSelectedIndex(Integer.parseInt(date[1])-1);
                jCB_D.setSelectedItem(date[2]);
            }catch(ArrayIndexOutOfBoundsException e)
            {
                LichThi.hocKy = searchHKD(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))).get(1);
                LichThi.dot = searchHKD(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))).get(0);
                LichThi.nienKhoa = searchHKD(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))).get(2);
                loadDuLieu();
                x = searchLTInTable(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex())));
                DefaultTableModel tableLT = (DefaultTableModel) jTable_CTLT.getModel();
                String date[] =  tableLT.getValueAt(x, 5).toString().split("-");
                jCB_Room.setSelectedItem(tableLT.getValueAt(x, 6));
                jCB_Tiet.setSelectedItem(tableLT.getValueAt(x, 7));
                jCB_Dot.setSelectedItem(tableLT.getValueAt(x, 9));
                jTF_Num.setText((String)tableLT.getValueAt(x, 8));
                jCB_Y.setSelectedItem(date[0]);
                
                jCB_M.setSelectedIndex(Integer.parseInt(date[1])-1);
                jCB_D.setSelectedItem(date[2]);
            }
            //finally{
                
            
            
            
        }else
        {
            jButton_Add.setText("Thêm");
            jTF_Num.setText("");
            jCB_Room.setSelectedIndex(0);
            jCB_Dot.setSelectedIndex(0);
            jCB_Tiet.setSelectedIndex(0);
            jCB_D.setSelectedIndex(0);
            jCB_M.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCB_ClassItemStateChanged

    private void jTable_CTLTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CTLTMouseClicked
        // TODO add your handling code here:
        int x = jTable_CTLT.getSelectedRow();
        
        DefaultTableModel tableLT = (DefaultTableModel) jTable_CTLT.getModel();
        String maLTC = (String) tableLT.getValueAt(x, 0);
        String date[] =  tableLT.getValueAt(x, 5).toString().split("-");
        //System.out.println(maLTC);
        Connection conn = Conn.conn();
        String sql = "SELECT * FROM V_LOPTC LT WHERE LT.MALOPTC = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLTC);
            //ps.setString(2, (String) tableLT.getValueAt(pos, 2));
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                jCB_Class.setSelectedItem(rs.getString("TENMON")+"_"+rs.getString("NIENKHOA").trim()+"_"+rs.getString("HOCKY")+"_"+rs.getString("NHOM"));
                jCB_Room.setSelectedItem(tableLT.getValueAt(x, 6));
                jCB_Tiet.setSelectedItem(tableLT.getValueAt(x, 7));
                jCB_Dot.setSelectedItem(tableLT.getValueAt(x, 9));
                jTF_Num.setText((String)tableLT.getValueAt(x, 8));
                jCB_Y.setSelectedItem(date[0]);
                
                jCB_M.setSelectedIndex(Integer.parseInt(date[1])-1);
                jCB_D.setSelectedItem(date[2]);
                break;
            }
        }
        catch (SQLException|NullPointerException e)
        {
             JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-Oneclick");
        }
        
    }//GEN-LAST:event_jTable_CTLTMouseClicked
    private void insertLTC(String maLTC,String maPhong,String ngay,String tiet,String dot,String sl)
    {
       // boolean result = false;
        Connection conn = Conn.conn();
        String sql = "INSERT INTO CTLICHTHI (MAPHONG,NGAYTHI,TIETBATDAU,MALOPTC,SLDUTHI,DOTTHI) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maPhong);
            ps.setString(2, ngay);
            ps.setString(3, tiet);
            ps.setString(4, maLTC);
            ps.setString(5, sl);
            ps.setString(6, dot);
             
            //rs.rowInserted()
            if(ps.executeUpdate()==1)
            {
                JOptionPane.showMessageDialog(this, "Thêm lịch thi cho lớp "+maLTC+" thành công");
                jButton_Add.setText("Sửa");
            }
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           // result = false;
            JOptionPane.showMessageDialog(this, "Thêm lịch thi cho lớp "+maLTC+" thất bại");
        }
       // return result;
    }
    private void delLTC(String maLTC)
    {
       // boolean result = false;
        Connection conn = Conn.conn();
        String sql = "DELETE FROM CTLICHTHI WHERE MALOPTC=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, maLTC);
           
             
            //rs.rowInserted()
            if(ps.executeUpdate()==1)
            {
                JOptionPane.showMessageDialog(this, "Xóa lịch thi cho lớp "+maLTC+" thành công");
                //jButton_Add.setText("Sửa");
            }
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           // result = false;
            JOptionPane.showMessageDialog(this, "Xóa lịch thi cho lớp "+maLTC+" thất bại");
        }
       // return result;
    }
    private void updateLTC(String maLTC,String maPhong,String ngay,String tiet,String dot,String sl)
    {
       // boolean result = false;
        Connection conn = Conn.conn();
        String sql = "UPDATE CTLICHTHI SET  MAPHONG=?,NGAYTHI=?,TIETBATDAU=?,SLDUTHI=?,DOTTHI=? WHERE MALOPTC=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maPhong);
            ps.setString(2, ngay);
            ps.setString(3, tiet);
            
            ps.setString(4, sl);
            ps.setString(5, dot);
            ps.setString(6, maLTC);
            //rs.rowInserted()
            if(ps.executeUpdate()==1)
            {
                JOptionPane.showMessageDialog(this, "Sửa lịch thi cho lớp "+maLTC+" thành công");
                //jButton_Add.setText("Sửa");
            }
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           // result = false;
            JOptionPane.showMessageDialog(this, "Sửa lịch thi cho lớp "+maLTC+" thất bại");
        }
       // return result;
    }
    private void jButton_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AddMouseClicked
        // TODO add your handling code here:
        String numRegEx = "[0-9]{1,3}";
        if (!jTF_Num.getText().matches(numRegEx))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng thí sinh");
            return;
        }
        String sl = jTF_Num.getText();
        String maPhong = jCB_Room.getSelectedItem().toString();
        String ngay = jCB_Y.getSelectedItem().toString() +"-"+ Integer.toString(jCB_M.getSelectedIndex()+1)+"-"+jCB_D.getSelectedItem().toString();
        String tiet = jCB_Tiet.getSelectedItem().toString();
        String dot = jCB_Dot.getSelectedItem().toString();
        String maLTC = searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()));
        try{
            if(jButton_Add.getText().equals("Thêm"))
            {
                insertLTC( maLTC, maPhong, ngay, tiet, dot, sl);
                
            }
            else
            {
                updateLTC( maLTC, maPhong, ngay, tiet, dot, sl);
            }
            loadDuLieu();
        }
        catch(HeadlessException e)
        {
            JOptionPane.showMessageDialog(this, "Thao tác thất bại, vui lòng thử lại");
        }
        
    }//GEN-LAST:event_jButton_AddMouseClicked

    private void jButton_DelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_DelMouseClicked
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa lịch thi của lớp "+jCB_Class.getSelectedItem().toString()+"?","Xác nhận",0);
        if(ret == JOptionPane.OK_OPTION)
        {
            delLTC(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex())));
        //System.out.println("["+searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))+"]");
        loadDuLieu();
        }
        else
            return;
    }//GEN-LAST:event_jButton_DelMouseClicked

    private void jTF_DTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_DTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_DTActionPerformed

    private void jButton_SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SMouseClicked
        // TODO add your handling code here:
        jDialog_Search.setVisible(true);
    }//GEN-LAST:event_jButton_SMouseClicked

    private void jButton_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ExitMouseClicked
        // TODO add your handling code here:
        jDialog_Search.setVisible(false);
    }//GEN-LAST:event_jButton_ExitMouseClicked

    private void jButton_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SearchMouseClicked
        // TODO add your handling code here:
        jDialog_Search.setVisible(false);
        if(!jTF_NK.getText().matches("\\d{4}"))
        {
            JOptionPane.showMessageDialog(this, "Nhập niên khóa, ví dụ 2019");
            return ;
        }
            
        else if(!jTF_HK.getText().matches("\\d{1}"))
        {
            JOptionPane.showMessageDialog(this, "Nhập học kỳ cần lọc.");
            return ;
        }
        else if(!jTF_DT.getText().matches("\\d{1}"))
        {
            JOptionPane.showMessageDialog(this, "Nhập đợt thi cần lọc.");
            return ;
        }
        LichThi.nienKhoa = jTF_NK.getText();
        LichThi.hocKy = jTF_HK.getText();
        LichThi.dot = jTF_DT.getText();
        loadDuLieu();
    }//GEN-LAST:event_jButton_SearchMouseClicked

    private void jButton_ClearSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ClearSearchMouseClicked
        // TODO add your handling code here:
        LichThi.hocKy = LichThi.dot = LichThi.nienKhoa = "";
        loadDuLieu();
    }//GEN-LAST:event_jButton_ClearSearchMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LichThi().setVisible(true);
//            }
//        });
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_ClearSearch;
    private javax.swing.JButton jButton_Del;
    private javax.swing.JButton jButton_Exit;
    private javax.swing.JButton jButton_S;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JComboBox<String> jCB_Class;
    private javax.swing.JComboBox<String> jCB_D;
    private javax.swing.JComboBox<String> jCB_Dot;
    private javax.swing.JComboBox<String> jCB_M;
    private javax.swing.JComboBox<String> jCB_Room;
    private javax.swing.JComboBox<String> jCB_Tiet;
    private javax.swing.JComboBox<String> jCB_Y;
    private javax.swing.JDialog jDialog_Search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_Class;
    private javax.swing.JLabel jLabel_D;
    private javax.swing.JLabel jLabel_Dot;
    private javax.swing.JLabel jLabel_Dot2;
    private javax.swing.JLabel jLabel_HK;
    private javax.swing.JLabel jLabel_M;
    private javax.swing.JLabel jLabel_NK;
    private javax.swing.JLabel jLabel_Num;
    private javax.swing.JLabel jLabel_Room;
    private javax.swing.JLabel jLabel_Tiet;
    private javax.swing.JLabel jLabel_Y;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTF_DT;
    private javax.swing.JTextField jTF_HK;
    private javax.swing.JTextField jTF_NK;
    private javax.swing.JTextField jTF_Num;
    private javax.swing.JTable jTable_CTLT;
    // End of variables declaration//GEN-END:variables
}
