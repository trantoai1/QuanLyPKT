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
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ketnoi.Conn;
public class BaiThi extends javax.swing.JFrame {
    public BaiThi() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadLopTC();
        load_BangGiangVienChamThi();
        loadKhoa();
        loadHoTenGV();
        searchLTC();
        loadNienKhoa();
    }
    
    public void loadKhoa(){
        Connection conn = Conn.conn();
        String sql = "SELECT TENPBK FROM PBKHOA";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet listType = ps.executeQuery();
            while(listType.next())
            {
                jComboBox_Khoa.addItem(listType.getString("TENPBK"));
            }
            listType.close();
            ps.close();
            conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối");
        }
    }
    
    private void loadToBoMon(){
        jComboBox_ToBoMon.removeAllItems();
        Connection conn = Conn.conn();
        String sql = "SELECT MAPBK FROM PBKHOA WHERE TENPBK = ?";
        String s = "";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jComboBox_Khoa.getSelectedItem().toString());
            ResultSet khoa = ps.executeQuery();
            while(khoa.next())
            {
                s = khoa.getString("MAPBK");
            }
            khoa.close();
            ps.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-TBM");
        }
        
        sql = "SELECT * FROM TOBOMON WHERE MAPBK = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet tbm = ps.executeQuery();
            while(tbm.next())
            {
                jComboBox_ToBoMon.addItem(tbm.getString("TENTBM"));
            }
            tbm.close();
            ps.close();
            conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-TBM");
        }
    }
    
    private void loadHoTenGV(){
        jComboBox_HoTen.removeAllItems();
        Connection conn = Conn.conn();
        boolean check = true;
        try{
            jComboBox_ToBoMon.getSelectedItem().toString();
        }
        catch(NullPointerException e){
            check = false;
        }
        if(check){
            String sql = "SELECT MATBM FROM TOBOMON WHERE TENTBM = ?";
            String s = "";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jComboBox_ToBoMon.getSelectedItem().toString());

                ResultSet tbm = ps.executeQuery();
                while(tbm.next())
                {
                    s = tbm.getString("MATBM");
                }
                tbm.close();
                ps.close();
            }
            catch(SQLException|NullPointerException e)
            {
               JOptionPane.showMessageDialog(this, "Khoa chưa có thông tin về tổ/bộ môn tương ứng");
            }

            sql = "EXEC SP_LOAD_NV_TBM ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, s);
                ResultSet gv = ps.executeQuery();
                while(gv.next())
                {
                    jComboBox_HoTen.addItem(gv.getString("HOTEN") + "_" + gv.getString("MANV"));
                }
                gv.close();
                ps.close();
            }
            catch(SQLException|NullPointerException e)
            {
               JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối");
            }
        }
        return;
    }
    
    private void loadNienKhoa(){
        Connection conn = Conn.conn();
        String sql = "SELECT DISTINCT NIENKHOA FROM LOPTC";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               jTextField_NienKhoa.setText(rs.getString("NIENKHOA"));
            }
            ps.close();
            rs.close();
            conn.close();
        }
        catch(SQLException|NullPointerException e){
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-loadNK");
        }
    }
    
    private void searchLTC()  {
        String s = jComboBox_LTC.getSelectedItem().toString();
        String sp[] = s.split("_");
        jTextField_Mon.setText(sp[0]);
        jTextField_HocKy.setText(sp[2]);
        jTextField_Nhom.setText(sp[3]);
        jTextField_NienKhoa.setText(sp[1]);
    }
    
    private void loadLopTC() {
        Connection conn = Conn.conn();
        String sql = "SELECT LOPTC.*,MON.TENMON FROM LOPTC,MON WHERE LOPTC.MAMON = MON.MAMON order by MON.TENMON,LOPTC.NIENKHOA,LOPTC.HOCKY DESC";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                jComboBox_LTC.addItem(rs.getString("TENMON")+"_"+rs.getString("NIENKHOA").trim()+"_"+rs.getString("HOCKY")+"_"+rs.getString("NHOM"));
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException|NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-loptc");
        }
    }
    
    public void load_BangGiangVienChamThi() {
        Vector v ;
        DefaultTableModel tableNV = (DefaultTableModel) jTable_GVChamThi.getModel();
        tableNV.setRowCount(0);
        Connection conn = Conn.conn();
        String sql = "EXEC SP_GVCT";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet listNV = st.executeQuery();
            while(listNV.next())
            {
                v = new Vector();
                v.add(listNV.getString("MALOPTC"));
                v.add(listNV.getString("TENMON"));
                v.add(listNV.getString("MANV"));
                v.add(listNV.getString("GV_CHAMTHI"));
                v.add(listNV.getString("SOLUONGBAITHI"));
                tableNV.addRow(v);
            }
            jTable_GVChamThi.setModel(tableNV);
                listNV.close();
                st.close();
                conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-1");
        }
    }
    
    public void load_TimToBoMon(int i){
        DefaultTableModel dtm = (DefaultTableModel) jTable_GVChamThi.getModel();
        String maltc = dtm.getValueAt(i, 0).toString();
        String mnv = dtm.getValueAt(i, 2).toString();
        jTextField_SoLuongBaiThi.setText(dtm.getValueAt(i, 4).toString());
        jTextField_MaLTC.setText(dtm.getValueAt(i, 0).toString());
        jTextField_Mon.setText(dtm.getValueAt(i, 1).toString());
        Connection con = Conn.conn();
        String sql;
        if(mnv.equals("          ")){
            jComboBox_HoTen.removeAllItems();
            sql = "SELECT LOPTC.*,MON.TENMON FROM LOPTC,MON WHERE LOPTC.MAMON = MON.MAMON AND LOPTC.MALOPTC = ?";
            try{
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maltc);
                ResultSet rs = ps.executeQuery();
                rs.next();
                jComboBox_LTC.setSelectedItem(rs.getString("TENMON")+"_"+rs.getString("NIENKHOA").trim()+"_"+rs.getString("HOCKY")+"_"+rs.getString("NHOM"));
                
            }
            catch(SQLException|NullPointerException e)
            {
               JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-GVCT");
            }
        }
        else{
            try{
                sql = "EXEC SP_CO_MANV_TIM_TBM ?, ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, mnv);
                ps.setString(2, maltc);
                ResultSet rs = ps.executeQuery();
                rs.next();
                jComboBox_Khoa.setSelectedItem(rs.getString("TENPBK"));
                jComboBox_ToBoMon.setSelectedItem(rs.getString("TENTBM"));
                jComboBox_HoTen.setSelectedItem(rs.getString("HO") + " " + rs.getString("TEN") + "_" + rs.getString("MANV"));
                jComboBox_LTC.setSelectedItem(rs.getString("TENMON")+"_"+rs.getString("NIENKHOA").trim()+"_"+rs.getString("HOCKY")+"_"+rs.getString("NHOM"));
                ps.close();
                rs.close();
                con.close();
            }
            catch(SQLException|NullPointerException e)
            {
               JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-GVCT");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_ThongTin = new javax.swing.JPanel();
        jTextField_MaLTC = new javax.swing.JTextField();
        jLabel_NienKhoa = new javax.swing.JLabel();
        jLabel_HocKy = new javax.swing.JLabel();
        jTextField_Nhom = new javax.swing.JTextField();
        jLabel_Nhom = new javax.swing.JLabel();
        jTextField_HocKy = new javax.swing.JTextField();
        jLabel_MaLTC = new javax.swing.JLabel();
        jScrollPane_GVChamThi = new javax.swing.JScrollPane();
        jTable_GVChamThi = new javax.swing.JTable();
        jButton_Save = new javax.swing.JButton();
        jLabel_Khoa = new javax.swing.JLabel();
        jComboBox_Khoa = new javax.swing.JComboBox<>();
        jLabel_ToBoMon = new javax.swing.JLabel();
        jComboBox_ToBoMon = new javax.swing.JComboBox<>();
        jLabel_HoTen = new javax.swing.JLabel();
        jComboBox_HoTen = new javax.swing.JComboBox<>();
        jLabel_LTC = new javax.swing.JLabel();
        jComboBox_LTC = new javax.swing.JComboBox<>();
        jLabel_Mon = new javax.swing.JLabel();
        jTextField_Mon = new javax.swing.JTextField();
        jLabel_SoLuongBaiThi = new javax.swing.JLabel();
        jTextField_SoLuongBaiThi = new javax.swing.JTextField();
        jButton_DeleteNV = new javax.swing.JButton();
        jTextField_NienKhoa = new javax.swing.JTextField();
        jPanel_BangPhanCongChamThi = new javax.swing.JPanel();
        jLabel_BangPhanCongChamThi = new javax.swing.JLabel();

        setTitle("Phân công giảng viên chấm thi các lớp tín chỉ");
        setMinimumSize(new java.awt.Dimension(700, 550));
        setSize(new java.awt.Dimension(700, 550));

        jPanel_ThongTin.setEnabled(false);

        jTextField_MaLTC.setEditable(false);
        jTextField_MaLTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MaLTCActionPerformed(evt);
            }
        });

        jLabel_NienKhoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_NienKhoa.setText("Niên khóa");

        jLabel_HocKy.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_HocKy.setText("Học kỳ");

        jTextField_Nhom.setEditable(false);
        jTextField_Nhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NhomActionPerformed(evt);
            }
        });

        jLabel_Nhom.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Nhom.setText("Nhóm");

        jTextField_HocKy.setEditable(false);

        jLabel_MaLTC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_MaLTC.setText("Mã lớp tín chỉ");

        jScrollPane_GVChamThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane_GVChamThiMouseClicked(evt);
            }
        });

        jTable_GVChamThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã lớp tín chỉ", "Môn học", "Mã GVCT", "Họ tên", "Số lượng bài thi"
            }
        ));
        jTable_GVChamThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_GVChamThiMouseClicked(evt);
            }
        });
        jScrollPane_GVChamThi.setViewportView(jTable_GVChamThi);

        jButton_Save.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_Save.setText("Lưu ");
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });

        jLabel_Khoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Khoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Khoa.setText("Khoa");

        jComboBox_Khoa.setMaximumSize(new java.awt.Dimension(10, 20));
        jComboBox_Khoa.setMinimumSize(new java.awt.Dimension(10, 20));
        jComboBox_Khoa.setPreferredSize(new java.awt.Dimension(10, 20));
        jComboBox_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_KhoaActionPerformed(evt);
            }
        });

        jLabel_ToBoMon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_ToBoMon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ToBoMon.setText("Tổ/ Bộ môn");

        jComboBox_ToBoMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ToBoMonActionPerformed(evt);
            }
        });

        jLabel_HoTen.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_HoTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_HoTen.setText("Họ Tên");

        jComboBox_HoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_HoTenActionPerformed(evt);
            }
        });

        jLabel_LTC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_LTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_LTC.setText("Lớp tín chỉ");

        jComboBox_LTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_LTCActionPerformed(evt);
            }
        });

        jLabel_Mon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Mon.setText("Môn");

        jTextField_Mon.setEditable(false);
        jTextField_Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MonActionPerformed(evt);
            }
        });

        jLabel_SoLuongBaiThi.setText("Số lượng bài thi");

        jButton_DeleteNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_DeleteNV.setText("Xóa nhân viên");
        jButton_DeleteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteNVActionPerformed(evt);
            }
        });

        jTextField_NienKhoa.setEditable(false);
        jTextField_NienKhoa.setMaximumSize(new java.awt.Dimension(6, 20));
        jTextField_NienKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NienKhoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ThongTinLayout = new javax.swing.GroupLayout(jPanel_ThongTin);
        jPanel_ThongTin.setLayout(jPanel_ThongTinLayout);
        jPanel_ThongTinLayout.setHorizontalGroup(
            jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_GVChamThi, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton_Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_DeleteNV))
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jComboBox_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel_LTC, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox_LTC, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel_ToBoMon)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_ToBoMon, 0, 178, Short.MAX_VALUE))
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_NienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_HoTen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_NienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel_MaLTC)
                .addGap(18, 18, 18)
                .addComponent(jTextField_MaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_SoLuongBaiThi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_SoLuongBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_HocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_HocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_ThongTinLayout.setVerticalGroup(
            jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jLabel_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ThongTinLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jComboBox_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_ToBoMon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_ToBoMon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_LTC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_LTC))
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Mon)))
                    .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_NienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_MaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_MaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_SoLuongBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_SoLuongBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Nhom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_HocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_DeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_GVChamThi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel_BangPhanCongChamThi.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel_BangPhanCongChamThi.setText("BẢNG PHÂN CÔNG CHẤM THI");

        javax.swing.GroupLayout jPanel_BangPhanCongChamThiLayout = new javax.swing.GroupLayout(jPanel_BangPhanCongChamThi);
        jPanel_BangPhanCongChamThi.setLayout(jPanel_BangPhanCongChamThiLayout);
        jPanel_BangPhanCongChamThiLayout.setHorizontalGroup(
            jPanel_BangPhanCongChamThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BangPhanCongChamThiLayout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel_BangPhanCongChamThi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_BangPhanCongChamThiLayout.setVerticalGroup(
            jPanel_BangPhanCongChamThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_BangPhanCongChamThiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel_BangPhanCongChamThi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BangPhanCongChamThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_BangPhanCongChamThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_HoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_HoTenActionPerformed
      
    }//GEN-LAST:event_jComboBox_HoTenActionPerformed

    private void jTextField_NhomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NhomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NhomActionPerformed

    private void jTextField_MonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MonActionPerformed

    private void jComboBox_LTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_LTCActionPerformed
        searchLTC();
        String s = jComboBox_LTC.getSelectedItem().toString();
        String result = "";
        Connection conn = Conn.conn();
        String sp[] = s.split("_");
        String sql = "SELECT TC.MALOPTC FROM V_LTC TC WHERE TC.TENMON = ? AND TC.NIENKHOA = ? AND TC.HOCKY = ? AND TC.NHOM = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp[0]);
            ps.setString(2, sp[1]);
            ps.setString(3, sp[2]);
            ps.setString(4, sp[3]);
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getString("MALOPTC");
            
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException|NullPointerException e){
            JOptionPane.showMessageDialog(this, e);
        }
        jTextField_MaLTC.setText(result);
    }//GEN-LAST:event_jComboBox_LTCActionPerformed

    private void jComboBox_ToBoMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ToBoMonActionPerformed
        loadHoTenGV();
    }//GEN-LAST:event_jComboBox_ToBoMonActionPerformed

    private void jComboBox_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_KhoaActionPerformed
        loadToBoMon();
    }//GEN-LAST:event_jComboBox_KhoaActionPerformed

    private void jScrollPane_GVChamThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane_GVChamThiMouseClicked

    }//GEN-LAST:event_jScrollPane_GVChamThiMouseClicked

    private void jTable_GVChamThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_GVChamThiMouseClicked
        int i = jTable_GVChamThi.getSelectedRow();
        load_TimToBoMon(i);
    }//GEN-LAST:event_jTable_GVChamThiMouseClicked

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa đổi?", "Xác nhận", 0);
        Connection con = Conn.conn();
        String maLTC = jTextField_MaLTC.getText();
        int slbt;
        try{
            slbt = Integer.parseInt( jTextField_SoLuongBaiThi.getText());
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Hãy nhập số lượng");
            return;
        }
        
        String sql;
        if(ret == JOptionPane.CANCEL_OPTION)
            return;
        else if(ret == JOptionPane.OK_OPTION)
        { 
            String tenMaNV = "";
            try{
                tenMaNV = jComboBox_HoTen.getSelectedItem().toString();
                String sp[] = tenMaNV.split("_");
                sql = "SELECT * FROM BAITHI WHERE MALOPTC = ?";
                boolean check = false;
                try{
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, maLTC);
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            if(maLTC.equals(rs.getString("MALOPTC"))){
                                check = true;
                            }
                        }
                    }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thất bại");
                }
                if(!check){
                    sql = "INSERT INTO BAITHI (MANV, SOLUONGBAITHI, MALOPTC )VALUES ('" + sp[1] + "', ?,?)";
                }
                else{
                    sql = "UPDATE BAITHI SET MANV = N'" + sp[1] + "', SOLUONGBAITHI = ? WHERE MALOPTC = ?";
                }
            }
            catch(NullPointerException e){
                sql = "UPDATE BAITHI SET MANV = '', SOLUONGBAITHI = ? WHERE MALOPTC = ?";
            }
            boolean check = true;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, slbt);
                ps.setString(2, maLTC);
                ps.executeUpdate();
            } catch (SQLException ex) {
                check = false;
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thất bại");
            }
            if(check){
                JOptionPane.showMessageDialog(this, "Đã hoàn tất chỉnh sửa.");
            }
            load_BangGiangVienChamThi();
        }
    }//GEN-LAST:event_jButton_SaveActionPerformed

    private void jButton_DeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteNVActionPerformed
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên đã chấm thi?", "Xác nhận", 0);
        Connection con = Conn.conn();
        String maLTC = jTextField_MaLTC.getText();
        String sql = "delete from BAITHI where MALOPTC = ?";
        boolean check = true;
        if(ret == JOptionPane.CANCEL_OPTION)
            return;
        else if(ret == JOptionPane.OK_OPTION)
        { 
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maLTC);
                ps.executeUpdate();
            } catch (SQLException ex) {
                check = false;
                JOptionPane.showMessageDialog(this, "Không thể xóa");
            }
            if(check){
                JOptionPane.showMessageDialog(this, "Đã hoàn tất xóa.");
                jComboBox_HoTen.removeAllItems();
            }
            load_BangGiangVienChamThi();
        }
    }//GEN-LAST:event_jButton_DeleteNVActionPerformed

    private void jTextField_MaLTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MaLTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MaLTCActionPerformed

    private void jTextField_NienKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NienKhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NienKhoaActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BaiThi().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_DeleteNV;
    private javax.swing.JButton jButton_Save;
    private javax.swing.JComboBox<String> jComboBox_HoTen;
    private javax.swing.JComboBox<String> jComboBox_Khoa;
    private javax.swing.JComboBox<String> jComboBox_LTC;
    private javax.swing.JComboBox<String> jComboBox_ToBoMon;
    private javax.swing.JLabel jLabel_BangPhanCongChamThi;
    private javax.swing.JLabel jLabel_HoTen;
    private javax.swing.JLabel jLabel_HocKy;
    private javax.swing.JLabel jLabel_Khoa;
    private javax.swing.JLabel jLabel_LTC;
    private javax.swing.JLabel jLabel_MaLTC;
    private javax.swing.JLabel jLabel_Mon;
    private javax.swing.JLabel jLabel_Nhom;
    private javax.swing.JLabel jLabel_NienKhoa;
    private javax.swing.JLabel jLabel_SoLuongBaiThi;
    private javax.swing.JLabel jLabel_ToBoMon;
    private javax.swing.JPanel jPanel_BangPhanCongChamThi;
    private javax.swing.JPanel jPanel_ThongTin;
    private javax.swing.JScrollPane jScrollPane_GVChamThi;
    private javax.swing.JTable jTable_GVChamThi;
    private javax.swing.JTextField jTextField_HocKy;
    private javax.swing.JTextField jTextField_MaLTC;
    private javax.swing.JTextField jTextField_Mon;
    private javax.swing.JTextField jTextField_Nhom;
    private javax.swing.JTextField jTextField_NienKhoa;
    private javax.swing.JTextField jTextField_SoLuongBaiThi;
    // End of variables declaration//GEN-END:variables
}
