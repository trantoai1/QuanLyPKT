package chucnang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ketnoi.Conn;

public class DeThi extends javax.swing.JFrame {
    public DeThi(){
        initComponents();
        this.setLocationRelativeTo(null);
        loadKhoa();
        loadToBoMon();
        loadHoTenGV();
        loadLopTC();
        load_BangGiangVienRaDe();
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
        //load_TimToBoMon(0);
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
    
    public void load_BangGiangVienRaDe() {
        Vector v ;
        DefaultTableModel tableNV = (DefaultTableModel) jTable_GVRaDe.getModel();
        tableNV.setRowCount(0);
        Connection conn = Conn.conn();
        String sql = "EXEC SP_GVRADE";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet listNV = st.executeQuery();
            while(listNV.next())
            {
                v = new Vector();
                v.add(listNV.getString("MALOPTC"));
                v.add(listNV.getString("TENMON"));
                v.add(listNV.getString("MANV"));
                v.add(listNV.getString("GV_RADETHI"));
                v.add(listNV.getString("SOLUONGBAITHI"));
                tableNV.addRow(v);
            }
            jTable_GVRaDe.setModel(tableNV);
                listNV.close();
                st.close();
                conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-1");
        }
    } 
    
    private void searchLTC()  {
        String s = jComboBox_LTC.getSelectedItem().toString();
        String result = "";
        Connection conn = Conn.conn();
        String sp[] = s.split("_");
        String sql = "SELECT * FROM V_LTC TC WHERE TC.TENMON = ? AND TC.NIENKHOA = ? AND TC.HOCKY = ? AND TC.NHOM = ?";
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
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-searchloptc");
        }
        jTextField_MaLTC.setText(result);
    }
    
    public void load_TimToBoMon(int i){
        DefaultTableModel dtm = (DefaultTableModel) jTable_GVRaDe.getModel();
        String maltc = dtm.getValueAt(i, 0).toString();
        String mnv = dtm.getValueAt(i, 2).toString();
        jTextField_SoLuongDeThi.setText(dtm.getValueAt(i, 4).toString());
        jTextField_MaLTC.setText(dtm.getValueAt(i, 0).toString());
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
     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_DanhSachDeThi = new javax.swing.JPanel();
        jLabel_DanhSachDeThi = new javax.swing.JLabel();
        jPanel_ThongTin = new javax.swing.JPanel();
        jLabel_ThongTin = new javax.swing.JLabel();
        jPanel_Khoa = new javax.swing.JPanel();
        jLabel_Khoa = new javax.swing.JLabel();
        jComboBox_Khoa = new javax.swing.JComboBox<>();
        jPanel_ToBoMon = new javax.swing.JPanel();
        jLabel_ToBoMon = new javax.swing.JLabel();
        jComboBox_ToBoMon = new javax.swing.JComboBox<>();
        jPanel_HoTen = new javax.swing.JPanel();
        jLabel_HoTen = new javax.swing.JLabel();
        jComboBox_HoTen = new javax.swing.JComboBox<>();
        jPanel_LTC = new javax.swing.JPanel();
        jLabel_LTC = new javax.swing.JLabel();
        jComboBox_LTC = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel_SoLuongDeThi = new javax.swing.JLabel();
        jTextField_SoLuongDeThi = new javax.swing.JTextField();
        jLabel_MaLTC = new javax.swing.JLabel();
        jTextField_MaLTC = new javax.swing.JTextField();
        jPanel_ChucNang = new javax.swing.JPanel();
        jLabel_ChucNang = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton_Save = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        js = new javax.swing.JScrollPane();
        jTable_GVRaDe = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnQuayLai = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 450));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel_DanhSachDeThi.setForeground(new java.awt.Color(255, 0, 0));

        jLabel_DanhSachDeThi.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_DanhSachDeThi.setForeground(new java.awt.Color(0, 51, 255));
        jLabel_DanhSachDeThi.setText("DANH SÁCH ĐỀ THI");
        jLabel_DanhSachDeThi.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel_DanhSachDeThiLayout = new javax.swing.GroupLayout(jPanel_DanhSachDeThi);
        jPanel_DanhSachDeThi.setLayout(jPanel_DanhSachDeThiLayout);
        jPanel_DanhSachDeThiLayout.setHorizontalGroup(
            jPanel_DanhSachDeThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DanhSachDeThiLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel_DanhSachDeThi)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel_DanhSachDeThiLayout.setVerticalGroup(
            jPanel_DanhSachDeThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DanhSachDeThiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_DanhSachDeThi)
                .addContainerGap())
        );

        getContentPane().add(jPanel_DanhSachDeThi);

        jPanel_ThongTin.setPreferredSize(new java.awt.Dimension(592, 30));

        jLabel_ThongTin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_ThongTin.setText("Thông tin");

        javax.swing.GroupLayout jPanel_ThongTinLayout = new javax.swing.GroupLayout(jPanel_ThongTin);
        jPanel_ThongTin.setLayout(jPanel_ThongTinLayout);
        jPanel_ThongTinLayout.setHorizontalGroup(
            jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                    .addGap(0, 279, Short.MAX_VALUE)
                    .addComponent(jLabel_ThongTin)
                    .addGap(0, 280, Short.MAX_VALUE)))
        );
        jPanel_ThongTinLayout.setVerticalGroup(
            jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(jPanel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ThongTinLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_ThongTin)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel_ThongTin);

        jLabel_Khoa.setText("Khoa");
        jLabel_Khoa.setPreferredSize(new java.awt.Dimension(110, 17));
        jPanel_Khoa.add(jLabel_Khoa);

        jComboBox_Khoa.setPreferredSize(new java.awt.Dimension(234, 30));
        jComboBox_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_KhoaActionPerformed(evt);
            }
        });
        jPanel_Khoa.add(jComboBox_Khoa);

        getContentPane().add(jPanel_Khoa);

        jLabel_ToBoMon.setText("Tổ - Bộ môn");
        jLabel_ToBoMon.setPreferredSize(new java.awt.Dimension(110, 17));
        jPanel_ToBoMon.add(jLabel_ToBoMon);

        jComboBox_ToBoMon.setPreferredSize(new java.awt.Dimension(234, 30));
        jComboBox_ToBoMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ToBoMonActionPerformed(evt);
            }
        });
        jPanel_ToBoMon.add(jComboBox_ToBoMon);

        getContentPane().add(jPanel_ToBoMon);

        jLabel_HoTen.setText("Tên giảng viên: ");
        jLabel_HoTen.setPreferredSize(new java.awt.Dimension(110, 17));
        jPanel_HoTen.add(jLabel_HoTen);

        jComboBox_HoTen.setPreferredSize(new java.awt.Dimension(234, 30));
        jPanel_HoTen.add(jComboBox_HoTen);

        getContentPane().add(jPanel_HoTen);

        jLabel_LTC.setText("Lớp tín chỉ: ");
        jLabel_LTC.setPreferredSize(new java.awt.Dimension(110, 17));
        jPanel_LTC.add(jLabel_LTC);

        jComboBox_LTC.setPreferredSize(new java.awt.Dimension(234, 30));
        jComboBox_LTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_LTCActionPerformed(evt);
            }
        });
        jPanel_LTC.add(jComboBox_LTC);

        getContentPane().add(jPanel_LTC);

        jLabel_SoLuongDeThi.setText("Số lượng đề thi:");
        jLabel_SoLuongDeThi.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jTextField_SoLuongDeThi.setColumns(20);
        jTextField_SoLuongDeThi.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextField_SoLuongDeThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SoLuongDeThiActionPerformed(evt);
            }
        });

        jLabel_MaLTC.setText("Mã lớp tín chỉ");

        jTextField_MaLTC.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel_SoLuongDeThi)
                .addGap(55, 55, 55)
                .addComponent(jTextField_SoLuongDeThi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel_MaLTC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_MaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_SoLuongDeThi)
                    .addComponent(jTextField_SoLuongDeThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_MaLTC)
                    .addComponent(jTextField_MaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel4);

        jPanel_ChucNang.setPreferredSize(new java.awt.Dimension(592, 30));

        jLabel_ChucNang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_ChucNang.setText("Chức năng");

        javax.swing.GroupLayout jPanel_ChucNangLayout = new javax.swing.GroupLayout(jPanel_ChucNang);
        jPanel_ChucNang.setLayout(jPanel_ChucNangLayout);
        jPanel_ChucNangLayout.setHorizontalGroup(
            jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ChucNangLayout.createSequentialGroup()
                    .addGap(0, 275, Short.MAX_VALUE)
                    .addComponent(jLabel_ChucNang)
                    .addGap(0, 276, Short.MAX_VALUE)))
        );
        jPanel_ChucNangLayout.setVerticalGroup(
            jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ChucNangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_ChucNang)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel_ChucNang);

        jButton_Save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Save.setForeground(new java.awt.Color(0, 153, 204));
        jButton_Save.setText("THÊM");
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });
        jPanel7.add(jButton_Save);

        jButton_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(0, 153, 204));
        jButton_Delete.setText("XÓA");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        jPanel7.add(jButton_Delete);

        getContentPane().add(jPanel7);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jTable_GVRaDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã lớp tín chỉ", "Môn học", "Mã GV ra đề", "Họ tên", "Số lượng đề thi"
            }
        ));
        jTable_GVRaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_GVRaDeMouseClicked(evt);
            }
        });
        js.setViewportView(jTable_GVRaDe);

        jPanel6.add(js, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6);

        btnQuayLai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(0, 153, 204));
        btnQuayLai.setText("QUAY LẠI");
        jPanel13.add(btnQuayLai);

        getContentPane().add(jPanel13);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa đổi?", "Xác nhận", 0);
        Connection con = Conn.conn();
        String maLTC = jTextField_MaLTC.getText();
        int slbt;
        try{
             slbt = Integer.parseInt( jTextField_SoLuongDeThi.getText());
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Hãy nhập số lượng đề thi");
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
                String sp2[] = tenMaNV.split("_");
                sql = "SELECT * FROM DETHI WHERE MALOPTC = ?";
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
                    sql = "INSERT INTO DETHI (MANV, SOLUONGDE, MALOPTC )VALUES ('" + sp2[1] + "', ?,?)";
                }
                else{
                    sql = "UPDATE DETHI SET MANV = N'" + sp2[1] + "', SOLUONGDE = ? WHERE MALOPTC = ?";
                }
            }
            catch(NullPointerException e){
                sql = "UPDATE DETHI SET MANV = '', SOLUONGDE = ? WHERE MALOPTC = ?";
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
            load_BangGiangVienRaDe();
        }            
    }//GEN-LAST:event_jButton_SaveActionPerformed

    private void jTextField_SoLuongDeThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SoLuongDeThiActionPerformed
        
    }//GEN-LAST:event_jTextField_SoLuongDeThiActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên đã ra đề?", "Xác nhận", 0);
        Connection con = Conn.conn();
        String maLTC = jTextField_MaLTC.getText();
        String sql = "delete from DETHI where MALOPTC = ?";
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
            load_BangGiangVienRaDe();
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jComboBox_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_KhoaActionPerformed
       loadToBoMon();
    }//GEN-LAST:event_jComboBox_KhoaActionPerformed

    private void jComboBox_ToBoMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ToBoMonActionPerformed
        loadHoTenGV();
    }//GEN-LAST:event_jComboBox_ToBoMonActionPerformed

    private void jTable_GVRaDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_GVRaDeMouseClicked
        int i = jTable_GVRaDe.getSelectedRow();
        load_TimToBoMon(i);
    }//GEN-LAST:event_jTable_GVRaDeMouseClicked

    private void jComboBox_LTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_LTCActionPerformed
        searchLTC();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_LTCActionPerformed
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Save;
    private javax.swing.JComboBox<String> jComboBox_HoTen;
    private javax.swing.JComboBox<String> jComboBox_Khoa;
    private javax.swing.JComboBox<String> jComboBox_LTC;
    private javax.swing.JComboBox<String> jComboBox_ToBoMon;
    private javax.swing.JLabel jLabel_ChucNang;
    private javax.swing.JLabel jLabel_DanhSachDeThi;
    private javax.swing.JLabel jLabel_HoTen;
    private javax.swing.JLabel jLabel_Khoa;
    private javax.swing.JLabel jLabel_LTC;
    private javax.swing.JLabel jLabel_MaLTC;
    private javax.swing.JLabel jLabel_SoLuongDeThi;
    private javax.swing.JLabel jLabel_ThongTin;
    private javax.swing.JLabel jLabel_ToBoMon;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_ChucNang;
    private javax.swing.JPanel jPanel_DanhSachDeThi;
    private javax.swing.JPanel jPanel_HoTen;
    private javax.swing.JPanel jPanel_Khoa;
    private javax.swing.JPanel jPanel_LTC;
    private javax.swing.JPanel jPanel_ThongTin;
    private javax.swing.JPanel jPanel_ToBoMon;
    private javax.swing.JTable jTable_GVRaDe;
    private javax.swing.JTextField jTextField_MaLTC;
    private javax.swing.JTextField jTextField_SoLuongDeThi;
    private javax.swing.JScrollPane js;
    // End of variables declaration//GEN-END:variables
    public void showWindows()
    {
        setSize(800,500);
        setTitle("Quản lý đề thi");
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public javax.swing.JButton getBtnQuayLai() {
        return btnQuayLai;
    }

    /**
     * @param btnQuayLai the btnQuayLai to set
     */
    public void setBtnQuayLai(javax.swing.JButton btnQuayLai) {
        this.btnQuayLai = btnQuayLai;
    }
}

