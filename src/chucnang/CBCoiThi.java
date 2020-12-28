/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chucnang;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ketnoi.Conn;

/**
 *
 * @author trantoai
 */
public class CBCoiThi extends javax.swing.JFrame {

    /**
     * Creates new form LichThi
     */
   // public static String hocKy , dot,nienKhoa;
    public boolean status = true;
    public CBCoiThi() {
        initComponents();
        
        this.loadDuLieu();
        this.loadKhoa();
        this.loadLopTC();
        if(jCB_Class.getItemCount() <=0)
        {
            this.status = false;
        }
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
    private String searchMNV(String name,String pbk,String tbm)
    {
        String result = "";
        Connection conn = Conn.conn();
        String sql = "SELECT MANV FROM V_NHANVIEN WHERE HOTEN=? AND TENTBM =? AND TENPBK =?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2, tbm);
            ps.setString(3,pbk);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                result = rs.getString("MANV");
        }
        catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-searchmnv");
        }
        return result;//Tra ve ma lop tin chi
    }
    private String searchLTC(String s) 
    {
        String sp[];
        try{
             sp= s.split("_");
        }
        catch(NullPointerException e)
        {
            return "";
        }
        String result = "";
        Connection conn = Conn.conn();
        
        String sql = "SELECT * FROM V_LOPTC TC WHERE TC.TENMON = ? AND TC.NIENKHOA = ? AND TC.HOCKY = ? AND TC.NHOM = ?";
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
    private void loadTBM(JComboBox<String> jcb,String tenPBK)
    {
        Connection conn = Conn.conn();
        String sql = "SELECT PBKHOA.MAPBK,TENTBM FROM TOBOMON,PBKHOA WHERE TENPBK=? AND TOBOMON.MAPBK = PBKHOA.MAPBK ";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tenPBK);
            ResultSet rs = ps.executeQuery();
            //jCB_T1.removeAllItems();
            while(rs.next())
            {
               
                jcb.addItem(rs.getString("TENTBM"));
            }
            rs.close();
            ps.close();
            conn.close();
            
            
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-searchloptc");
        }
        
    }
    private void loadNV(JComboBox<String> jcb,String tenTBM)
    {
        Connection conn = Conn.conn();
        String sql = "SELECT NV.HO+' '+NV.TEN AS 'HOTEN',NV.MANV FROM TOBOMON T,NHANVIEN NV WHERE TENTBM=? AND T.MATBM = NV.MATBM ";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tenTBM);
            ResultSet rs = ps.executeQuery();
            //jCB_T1.removeAllItems();
            while(rs.next())
            {
               if(jTF_NV1.getText().equals(rs.getString("MANV"))||jTF_NV2.getText().equals(rs.getString("MANV"))||jTF_NV3.getText().equals(rs.getString("MANV")))
                   continue;
                jcb.addItem(rs.getString("HOTEN"));
            }
            rs.close();
            ps.close();
            conn.close();
            
            
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-loadnv");
        }
        
    }
    private ArrayList <String> searchBMK(String hoTen)
    {
        ArrayList <String> result = new ArrayList<>();
        Connection conn = Conn.conn();
        String sql = "SELECT TENTBM,TENPBK FROM V_NHANVIEN WHERE HOTEN=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoTen);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                result.add(0,rs.getString("TENTBM"));
                result.add(1,rs.getString("TENPBK"));
            }
        }
        catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-SEARCHBMK");
        }
        return result;
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
    private void loadKhoa()
    {
        Connection conn = Conn.conn();
        String sql = "SELECT DISTINCT K.TENPBK FROM PBKHOA K,TOBOMON T,NHANVIEN NV WHERE T.MAPBK = K.MAPBK AND NV.MATBM = T.MATBM";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                jCB_K1.addItem(rs.getString("TENPBK"));
                jCB_K2.addItem(rs.getString("TENPBK"));
                jCB_K3.addItem(rs.getString("TENPBK"));
            }
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-pbk");
        }
    }
    private void loadLopTC()
    {   
        Connection conn = Conn.conn();
        String sql = "SELECT * FROM V_LICHTHI ";
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
    public void loadLopTC2()
    {   
        jCB_Class.removeAllItems();
        Connection conn = Conn.conn();
        String sql = "SELECT * FROM V_LICHTHI ";
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
    
    public void loadDuLieu() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_CTLT.getModel();
        dtm.setRowCount(0);
        Connection conn = Conn.conn();
        String sql = "EXEC SP_KHOILUONG2 ";
        Vector vt ;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                vt = new Vector();
                //System.out.println(rs.getString("MALOPTC"));
                vt.add(rs.getString("MALOPTC"));
                vt.add(rs.getString("TENMON"));
                vt.add(rs.getString("GVGIANGDAY"));
                
                vt.add(rs.getString("NHOM"));
                vt.add(rs.getString("NGAYTHI"));
                vt.add(rs.getString("PHONGTHI"));
                vt.add(rs.getString("TIETBATDAU"));
                vt.add(rs.getString("CBCT1"));

                vt.add(rs.getString("CBCT2"));

                vt.add(rs.getString("CBCT3"));

                dtm.addRow(vt);
            }
            jTable_CTLT.setModel(dtm);
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException|NullPointerException e)
        {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-full LT");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Class = new javax.swing.JLabel();
        jCB_Class = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jButton_Del = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CTLT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLB_K1 = new javax.swing.JLabel();
        jCB_K1 = new javax.swing.JComboBox<>();
        jLB_K2 = new javax.swing.JLabel();
        jCB_K2 = new javax.swing.JComboBox<>();
        jLB_K3 = new javax.swing.JLabel();
        jCB_K3 = new javax.swing.JComboBox<>();
        jLB_T1 = new javax.swing.JLabel();
        jCB_T1 = new javax.swing.JComboBox<>();
        jLB_T2 = new javax.swing.JLabel();
        jCB_T2 = new javax.swing.JComboBox<>();
        jLB_T3 = new javax.swing.JLabel();
        jCB_T3 = new javax.swing.JComboBox<>();
        jLB_NV1 = new javax.swing.JLabel();
        jCB_NV1 = new javax.swing.JComboBox<>();
        jLB_NV2 = new javax.swing.JLabel();
        jCB_NV2 = new javax.swing.JComboBox<>();
        jLB_NV3 = new javax.swing.JLabel();
        jCB_NV3 = new javax.swing.JComboBox<>();
        jLB_MNV1 = new javax.swing.JLabel();
        jTF_NV1 = new javax.swing.JTextField();
        jLB_MNV2 = new javax.swing.JLabel();
        jTF_NV2 = new javax.swing.JTextField();
        jLB_MNV3 = new javax.swing.JLabel();
        jTF_NV3 = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1120, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(1120, 650));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHI TIẾT CÁN BỘ COI THI");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(306, 306, 306))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel_Class.setText("Lớp tín chỉ");

        jCB_Class.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_ClassItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel_Class)
                .addGap(40, 40, 40)
                .addComponent(jCB_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Class)
                    .addComponent(jCB_Class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194)
                .addComponent(jButton_Del, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(278, 278, 278))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                "Mã LTC", "Môn học", "Giảng viên", "Nhóm", "Ngày thi", "Phòng thi", "Tiết bắt đầu", "CB coi thi 1", "CB coi thi 2", "CB coi thi 3"
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
            .addGap(0, 1110, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        jPanel7.setLayout(new java.awt.GridLayout(4, 12));

        jLB_K1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_K1.setText("Khoa");
        jPanel7.add(jLB_K1);

        jCB_K1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_K1ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_K1);

        jLB_K2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_K2.setText("Khoa");
        jPanel7.add(jLB_K2);

        jCB_K2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_K2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_K2ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_K2);

        jLB_K3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_K3.setText("Khoa");
        jPanel7.add(jLB_K3);

        jCB_K3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_K3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_K3ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_K3);

        jLB_T1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_T1.setText("Tổ - Bộ môn");
        jPanel7.add(jLB_T1);

        jCB_T1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_T1ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_T1);

        jLB_T2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_T2.setText("Tổ - Bộ môn");
        jPanel7.add(jLB_T2);

        jCB_T2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_T2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_T2ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_T2);

        jLB_T3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_T3.setText("Tổ - Bộ môn");
        jPanel7.add(jLB_T3);

        jCB_T3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_T3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_T3ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_T3);

        jLB_NV1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_NV1.setText("Nhân viên");
        jPanel7.add(jLB_NV1);

        jCB_NV1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_NV1ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_NV1);

        jLB_NV2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_NV2.setText("Nhân viên");
        jPanel7.add(jLB_NV2);

        jCB_NV2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_NV2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_NV2ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_NV2);

        jLB_NV3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_NV3.setText("Nhân viên");
        jPanel7.add(jLB_NV3);

        jCB_NV3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jCB_NV3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_NV3ItemStateChanged(evt);
            }
        });
        jPanel7.add(jCB_NV3);

        jLB_MNV1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_MNV1.setText("Mã nhân viên");
        jPanel7.add(jLB_MNV1);

        jTF_NV1.setEditable(false);
        jTF_NV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_NV1ActionPerformed(evt);
            }
        });
        jPanel7.add(jTF_NV1);

        jLB_MNV2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_MNV2.setText("Mã nhân viên");
        jPanel7.add(jLB_MNV2);

        jTF_NV2.setEditable(false);
        jPanel7.add(jTF_NV2);

        jLB_MNV3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLB_MNV3.setText("Mã nhân viên");
        jPanel7.add(jLB_MNV3);

        jTF_NV3.setEditable(false);
        jPanel7.add(jTF_NV3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCB_ClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_ClassItemStateChanged
        // TODO add your handling code here:
        
        if(searchLTC_LT(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex()))))
        {
            jButton_Add.setText("Sửa");
            int x = searchLTInTable(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex())));
            DefaultTableModel tableLT = (DefaultTableModel) jTable_CTLT.getModel();
            try {
                if(!tableLT.getValueAt(x, 7).toString().equals(""))
                {
                    //System.out.println(tableLT.getValueAt(x, 7).toString());
                    ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 7).toString());
                    //System.out.println(tenBMK.get(1));
                    jCB_K1.setSelectedItem(tenBMK.get(1));
                    jCB_T1.setSelectedItem(tenBMK.get(0));
                    jButton_Add.setText("Sửa");
                }
                else
                    jButton_Add.setText("Thêm");
                if(!tableLT.getValueAt(x, 8).toString().equals(""))
                {
                    //System.out.println(tableLT.getValueAt(x, 8).toString());
                    ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 8).toString());
                    //System.out.println(tenBMK.get(1));
                    jCB_K2.setSelectedItem(tenBMK.get(1));
                    jCB_T2.setSelectedItem(tenBMK.get(0));
                    jButton_Add.setText("Sửa");
                }
                else
                {
                    jCB_K2.setSelectedIndex(0);
                    jCB_T2.setSelectedIndex(0);

                }
                if(!tableLT.getValueAt(x, 9).toString().equals(""))
                {
                    //System.out.println(tableLT.getValueAt(x, 8).toString());
                    ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 9).toString());
                    //System.out.println(tenBMK.get(1));
                    jCB_K3.setSelectedItem(tenBMK.get(1));
                    jCB_T3.setSelectedItem(tenBMK.get(0));
                    jButton_Add.setText("Sửa");
                }
                 else
                {
                    jCB_K3.setSelectedIndex(0);
                    jCB_T3.setSelectedIndex(0);

                }
            }
            catch (NullPointerException|IndexOutOfBoundsException e)
            {
                 jButton_Add.setText("Thêm");
            }
            
        }else
        {
            jButton_Add.setText("Thêm");
        }
    }//GEN-LAST:event_jCB_ClassItemStateChanged

    private void jTable_CTLTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CTLTMouseClicked
        // TODO add your handling code here:
        int x = jTable_CTLT.getSelectedRow();
        
        DefaultTableModel tableLT = (DefaultTableModel) jTable_CTLT.getModel();
        String maLTC;
        try{
            maLTC  = (String) tableLT.getValueAt(x, 0);
        }
        catch(Exception e)
        {
            return;
        }
              
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
                
                break;
            }
        }
        catch (SQLException|NullPointerException e)
        {
             JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-Oneclick");
        }
        
        try {
            if(!tableLT.getValueAt(x, 7).toString().equals(""))
            {
                //System.out.println(tableLT.getValueAt(x, 7).toString());
                ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 7).toString());
                //System.out.println(tenBMK.get(1));
                jCB_K1.setSelectedItem(tenBMK.get(1));
                jCB_T1.setSelectedItem(tenBMK.get(0));
                jButton_Add.setText("Sửa");
            }
            else
                jButton_Add.setText("Thêm");
            if(!tableLT.getValueAt(x, 8).toString().equals(""))
            {
                //System.out.println(tableLT.getValueAt(x, 8).toString());
                ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 8).toString());
                //System.out.println(tenBMK.get(1));
                jCB_K2.setSelectedItem(tenBMK.get(1));
                jCB_T2.setSelectedItem(tenBMK.get(0));
                jButton_Add.setText("Sửa");
            }
            else
            {
                jCB_K2.setSelectedIndex(0);
                jCB_T2.setSelectedIndex(0);
                
            }
            if(!tableLT.getValueAt(x, 9).toString().equals(""))
            {
                //System.out.println(tableLT.getValueAt(x, 8).toString());
                ArrayList<String> tenBMK = searchBMK(tableLT.getValueAt(x, 9).toString());
                //System.out.println(tenBMK.get(1));
                jCB_K3.setSelectedItem(tenBMK.get(1));
                jCB_T3.setSelectedItem(tenBMK.get(0));
                jButton_Add.setText("Sửa");
            }
             else
            {
                jCB_K3.setSelectedIndex(0);
                jCB_T3.setSelectedIndex(0);
                
            }
        }
        catch (NullPointerException|IndexOutOfBoundsException e)
        {
             jButton_Add.setText("Thêm");
        }
    }//GEN-LAST:event_jTable_CTLTMouseClicked
    private int countCBCT(String ngayThi,String tiet,String phong)
    {
        int sum = 0;
        Connection conn = Conn.conn();
        String sql = "SELECT ISNULL(COUNT(MANV),0) AS SL FROM CBCOITHI WHERE NGAYTHI=? AND TIETBATDAU=? AND MAPHONG=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngayThi);
            ps.setString(2, tiet);
            ps.setString(3, phong);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                sum = Integer.parseInt(rs.getString("SL"));
            rs.close();
            ps.close();
            conn.close();
        }
        catch(NullPointerException|SQLException e)
        {
            sum = 0;
        }
        
        return sum;
    }
    private void insertCBCT(String maLTC,String maNV,boolean tb)
    {
       // boolean result = false;
        String ngayThi=searchNTP(maLTC).get(0),tiet=searchNTP(maLTC).get(1),phong=searchNTP(maLTC).get(2);
        if(countCBCT(ngayThi,tiet,phong) >=3)
            return;
        Connection conn = Conn.conn();
        String sql = "INSERT INTO CBCOITHI (MAPHONG,NGAYTHI,TIETBATDAU,MANV) VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phong);
            ps.setString(2, ngayThi);
            ps.setString(3, tiet);
            ps.setString(4, maNV);

             
            //rs.rowInserted()
            if(ps.executeUpdate()==1)
            {
                if(tb)
                JOptionPane.showMessageDialog(this, "Thêm cán bộ coi thi lớp "+maLTC+" thành công");
                
                jButton_Add.setText("Sửa");
            }
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           // result = false;
            if(tb)
            JOptionPane.showMessageDialog(this, "Thêm cán bộ coi cho lớp "+maLTC+" thất bại");
        }
       // return result;
    }
    private ArrayList<String> searchNTP( String maLTC)
    {
        ArrayList<String> result = new ArrayList<>();
        String sql1 = "SELECT NGAYTHI,TIETBATDAU,MAPHONG FROM CTLICHTHI WHERE MALOPTC=?";
        
        Connection conn = Conn.conn();
        try{
            PreparedStatement ps = conn.prepareStatement(sql1);
            
            ps.setString(1, maLTC);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                result.add(0,rs.getString("NGAYTHI"));
                result.add(1,rs.getString("TIETBATDAU"));
                result.add(2,rs.getString("MAPHONG"));
               
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
    private void delCBCT(String maLTC,boolean tb)
    {
       // boolean result = false;
        
        String ngayThi=searchNTP(maLTC).get(0),tiet=searchNTP(maLTC).get(1),phong=searchNTP(maLTC).get(2);
        Connection conn = Conn.conn();
        if(ngayThi.equals(""))
            return;
        String sql = "DELETE FROM CBCOITHI WHERE NGAYTHI=? AND TIETBATDAU=? AND MAPHONG=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, ngayThi);
            ps.setString(2, tiet);
            ps.setString(3, phong);
            //rs.rowInserted()
            if(ps.executeUpdate()==1)
            {
                if (tb)
                JOptionPane.showMessageDialog(this, "Xóa cán bộ coi thi thành công");
                //jButton_Add.setText("Sửa");
            }
            ps.close();
            conn.close();
        }
        catch (SQLException|NullPointerException e)
        {
           // result = false;
            if(tb)
            JOptionPane.showMessageDialog(this, "Xóa cán bộ coi thi thất bại");
        }
       // return result;
    }
    
    private void jButton_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AddMouseClicked
        String maLTC = searchLTC(jCB_Class.getSelectedItem().toString());
        if(jButton_Add.getText().equals("Sửa"))
        {
            delCBCT(maLTC,false);
        }
        if(!jTF_NV1.getText().equals(""))
            insertCBCT(maLTC,jTF_NV1.getText(),true);
        if(!jTF_NV2.getText().equals(""))
            insertCBCT(maLTC,jTF_NV2.getText(),false);
        if(!jTF_NV3.getText().equals(""))
            insertCBCT(maLTC,jTF_NV3.getText(),false);
        loadDuLieu();
    }//GEN-LAST:event_jButton_AddMouseClicked

    private void jButton_DelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_DelMouseClicked
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa toàn bộ cán bộ coi thi của lớp "+jCB_Class.getSelectedItem().toString()+"?","Xác nhận",0);
        if(ret == JOptionPane.OK_OPTION)
        {
          delCBCT(searchLTC(jCB_Class.getItemAt(jCB_Class.getSelectedIndex())),true);
       
        loadDuLieu();  
        }
        else
            return;
        
    }//GEN-LAST:event_jButton_DelMouseClicked

    private void jTF_NV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_NV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_NV1ActionPerformed

    private void jCB_K1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_K1ItemStateChanged
        // TODO add your handling code here:
        jCB_T1.removeAllItems();
        
        this.loadTBM(jCB_T1,jCB_K1.getSelectedItem().toString());
    }//GEN-LAST:event_jCB_K1ItemStateChanged

    private void jCB_K2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_K2ItemStateChanged
        // TODO add your handling code here:
        
        jCB_T2.removeAllItems();
        if(jCB_K2.getSelectedItem().toString().equals("Không"))
            jCB_T2.addItem("Không");
        else
        this.loadTBM(jCB_T2,jCB_K2.getSelectedItem().toString());
    }//GEN-LAST:event_jCB_K2ItemStateChanged

    private void jCB_K3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_K3ItemStateChanged
        // TODO add your handling code here:
        
        jCB_T3.removeAllItems();
        if(jCB_K3.getSelectedItem().toString().equals("Không"))
            jCB_T3.addItem("Không");
        else
        this.loadTBM(jCB_T3,jCB_K3.getSelectedItem().toString());
    }//GEN-LAST:event_jCB_K3ItemStateChanged

    private void jCB_T1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_T1ItemStateChanged
        // TODO add your handling code here:
        jCB_NV1.removeAllItems();
        try{
            if(!jCB_T1.getSelectedItem().toString().trim().equals(""))
            this.loadNV(jCB_NV1, jCB_T1.getSelectedItem().toString());
        }
        catch(NullPointerException e)
        {
            jCB_NV1.addItem("Không");
        }
        
        
    }//GEN-LAST:event_jCB_T1ItemStateChanged

    private void jCB_T2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_T2ItemStateChanged
        // TODO add your handling code here:
        jCB_NV2.removeAllItems();
        try{
            if(!jCB_T2.getSelectedItem().toString().trim().equals(""))
            this.loadNV(jCB_NV2, jCB_T2.getSelectedItem().toString());
        }
        catch(NullPointerException e)
        {
            jCB_NV2.addItem("Không");
        }
    }//GEN-LAST:event_jCB_T2ItemStateChanged

    private void jCB_T3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_T3ItemStateChanged
        // TODO add your handling code here:
        jCB_NV3.removeAllItems();
        try{
            //if(!jCB_T3.getSelectedItem().toString().trim().equals(""))
            this.loadNV(jCB_NV3, jCB_T3.getSelectedItem().toString());
        }
        catch(NullPointerException e)
        {
            jCB_NV3.addItem("Không");
        }
    }//GEN-LAST:event_jCB_T3ItemStateChanged

    private void jCB_NV1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_NV1ItemStateChanged
        // TODO add your handling code here:
        try{
            if(jCB_NV1.getSelectedItem().toString().equals("Không"))
                jTF_NV1.setText("");
             else
                
                jTF_NV1.setText(this.searchMNV(jCB_NV1.getSelectedItem().toString(),jCB_K1.getSelectedItem().toString(),jCB_T1.getSelectedItem().toString()));
        }
        catch(NullPointerException e)
        {
            jTF_NV1.setText("");
        }
        
    }//GEN-LAST:event_jCB_NV1ItemStateChanged

    private void jCB_NV2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_NV2ItemStateChanged
        // TODO add your handling code here:
        
            
        try{
            if(jCB_NV2.getSelectedItem().toString().equals("Không"))
                jTF_NV2.setText("");
             else
                
                jTF_NV2.setText(this.searchMNV(jCB_NV2.getSelectedItem().toString(),jCB_K2.getSelectedItem().toString(),jCB_T2.getSelectedItem().toString()));
        }
        catch(NullPointerException e)
        {
            jTF_NV2.setText("");
        }
    }//GEN-LAST:event_jCB_NV2ItemStateChanged

    private void jCB_NV3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_NV3ItemStateChanged
        // TODO add your handling code here:
        
        try{
            if(jCB_NV3.getSelectedItem().toString().equals("Không"))
                jTF_NV3.setText("");
             else
                
                jTF_NV3.setText(this.searchMNV(jCB_NV3.getSelectedItem().toString(),jCB_K3.getSelectedItem().toString(),jCB_T3.getSelectedItem().toString()));
        }
        catch(NullPointerException e)
        {
            jTF_NV3.setText("");
        }
    }//GEN-LAST:event_jCB_NV3ItemStateChanged

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
//                CBCoiThi cb = new CBCoiThi();
//                cb.setVisible(true);
//                if(!cb.status)
//                {
//                    int ret = JOptionPane.showConfirmDialog(cb, "Chưa có lịch thi cho các lớp, bạn có muốn thêm lịch thi trước?");
//                    if(ret == JOptionPane.OK_OPTION)
//                    {
//                         cb.setVisible(false);
//                         new LichThi().setVisible(true);
//                    }
//                    else
//                        cb.setVisible(false); 
//                }
//                
//                                
//            }
//        });
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Del;
    private javax.swing.JComboBox<String> jCB_Class;
    private javax.swing.JComboBox<String> jCB_K1;
    private javax.swing.JComboBox<String> jCB_K2;
    private javax.swing.JComboBox<String> jCB_K3;
    private javax.swing.JComboBox<String> jCB_NV1;
    private javax.swing.JComboBox<String> jCB_NV2;
    private javax.swing.JComboBox<String> jCB_NV3;
    private javax.swing.JComboBox<String> jCB_T1;
    private javax.swing.JComboBox<String> jCB_T2;
    private javax.swing.JComboBox<String> jCB_T3;
    private javax.swing.JLabel jLB_K1;
    private javax.swing.JLabel jLB_K2;
    private javax.swing.JLabel jLB_K3;
    private javax.swing.JLabel jLB_MNV1;
    private javax.swing.JLabel jLB_MNV2;
    private javax.swing.JLabel jLB_MNV3;
    private javax.swing.JLabel jLB_NV1;
    private javax.swing.JLabel jLB_NV2;
    private javax.swing.JLabel jLB_NV3;
    private javax.swing.JLabel jLB_T1;
    private javax.swing.JLabel jLB_T2;
    private javax.swing.JLabel jLB_T3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Class;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTF_NV1;
    private javax.swing.JTextField jTF_NV2;
    private javax.swing.JTextField jTF_NV3;
    private javax.swing.JTable jTable_CTLT;
    // End of variables declaration//GEN-END:variables
}
