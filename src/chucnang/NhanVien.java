package chucnang;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ketnoi.Conn;
import javax.swing.AbstractButton;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class NhanVien extends javax.swing.JFrame {
    public NhanVien(){
        initComponents();
        this.setLocationRelativeTo(null);
        this.load_LoaiNhanVien();
        this.load_ToBoMon();
        this.loadNV();
    }
    
    public void load_LoaiNhanVien(){
        Connection conn = Conn.conn();
        String sql = "SELECT TENLOAI FROM LOAINV";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet listType = ps.executeQuery();
            while(listType.next())
            {
                jComboBox_TypeNV.addItem(listType.getString("TENLOAI"));
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
    
    public void load_ToBoMon(){
        Connection conn = Conn.conn();
        String sql = "SELECT TENTBM FROM TOBOMON";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet listType = ps.executeQuery();
            while(listType.next())
            {
                jComboBox_ToBoMon.addItem(listType.getString("TENTBM"));
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
    
    public String get_ToBoMon_NhanVien(String id){
        Connection conn = Conn.conn();
        String sql = "SELECT TENTBM FROM TOBOMON, NHANVIEN WHERE NHANVIEN.MATBM = TOBOMON.MATBM AND NHANVIEN.MANV = ?";
        String result = "";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                result = rs.getString("TENTBM");
        }
        catch(SQLException|NullPointerException e)
        {
           result = "";
        }
        return result;
    }
    
    public void loadNV() {
        Vector v ;
        DefaultTableModel tableNV = (DefaultTableModel) jTable_NV.getModel();
        tableNV.setRowCount(0);
        Connection conn = Conn.conn();
        String sql = "SELECT * FROM V_NHANVIEN"; // view V_NHANVIEN
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet listNV = st.executeQuery();
            while(listNV.next())
            {
                v = new Vector();
                v.add(listNV.getString("MANV"));
                v.add(listNV.getString("HOTEN"));
                v.add(listNV.getString("DIACHI"));
                v.add(listNV.getString("EMAIL"));
                v.add(listNV.getString("SEX"));
                v.add(listNV.getString("TENTBM"));
                v.add(listNV.getString("LOAINV"));
                tableNV.addRow(v);
            }
            jTable_NV.setModel(tableNV);
                listNV.close();
                st.close();
                conn.close();
        }
        catch(SQLException|NullPointerException e)
        {
           JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-1");
        }
    }
    
    private int kiemTraTonTaiMaNV(String id) {
        Connection ketNoi = Conn.conn();
        int tonTai = 0;
        String sql = "select MANV from NHANVIEN where MANV ='" + id + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = 1;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-2");
        }
        return tonTai;
    }
    
    private boolean kiemTraEmail(String email){
        Pattern pattern;
        String check = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        pattern = Pattern.compile(check);
        return pattern.matcher(email).matches();
    }
    
    private void luuDS(String name, String id, String sex, String email, String address, String toBoMon, String loaiNV) {
        int temp = 0;
        String ho = " ";
        String ten = " ";
        temp = name.indexOf(" ");
        if(temp != -1){
            ho = name.substring(0, temp);
            ten = name.substring(temp + 1);
        }
        else{
            ho = name;
        }
        Connection ketNoi = Conn.conn();
        String sql = "SELECT MATBM FROM TOBOMON WHERE TOBOMON.TENTBM = N'" + toBoMon + "'";
        toBoMon = "";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                toBoMon = rs.getString("MATBM");
        }
        catch(SQLException|NullPointerException e)
        {
           toBoMon = "";
        }
        //
        sql = "SELECT MALOAI FROM LOAINV WHERE TENLOAI = N'" + loaiNV + "'";
        loaiNV = "";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                loaiNV = rs.getString("MALOAI");
        }
        catch(SQLException|NullPointerException e)
        {
           loaiNV = "";
        }
        
        sql = "insert into NHANVIEN values (?,?,?,?,?,?,?,?)";
        boolean check = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, ho);
            ps.setString(3, ten);
            ps.setString(4, sex);
            ps.setString(5, address);
            ps.setString(6, email);
            ps.setString(7, loaiNV);
            ps.setString(8, toBoMon);
            ps.executeUpdate();
        } catch (SQLException ex) {
            check = false;
            JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại, vui lòng kiểm tra kết nối-3");
        }
        if(check){
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
        }
    }
    
    private void clear(){
        jTextField_Name.setText("");
        jTextField_Id.setText("");
        jTextField_Email.setText("ex@email.com");
        jTextField_Add.setText("");
    }
    
    private void xoaMaNV(String id){
        Connection ketNoi = Conn.conn();
        String sql = "EXEC SP_DELETENV ?";
        boolean check = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            check = false;
            JOptionPane.showMessageDialog(this, "Không thể xóa nhân viên này.");
        }  
        if(check){
            JOptionPane.showMessageDialog(this, "Đã xóa thành công.");
        }
    }
    
    private void luuSuaDoi(String name, String id, String sex, String email, String address, String toBoMon, String loaiNV){
        int temp = 0;
        String ho = " ";
        String ten = " ";
        temp = name.indexOf(" ");
        if(temp != -1){
            ho = name.substring(0, temp);
            ten = name.substring(temp + 1);
        }
        else{
            ho = name;
        }
        //lấy mã tổ/ bộ môn
        Connection ketNoi = Conn.conn();
        String sql = "SELECT MATBM FROM TOBOMON WHERE TOBOMON.TENTBM = N'" + toBoMon + "'";
        toBoMon = "";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                toBoMon = rs.getString("MATBM");
            }
        }
        catch(SQLException|NullPointerException e)
        {
           toBoMon = "";
        }
        //lấy mã loại nhân viên
        sql = "SELECT MALOAI FROM LOAINV WHERE TENLOAI = N'" + loaiNV + "'";
        loaiNV = "";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                loaiNV = rs.getString("MALOAI");
            }
        }
        catch(SQLException|NullPointerException e)
        {
           loaiNV = "";
        }
        sql = "UPDATE NHANVIEN SET HO = N'" +  ho + "', TEN = N'" +  ten + "', GIOITINH = N'" +  sex + "', EMAIL = N'" + email + "', DIACHI = N'" +  address + "', MALOAI = N'" +  loaiNV + "', MATBM = N'" + toBoMon + "' WHERE MANV = '" + id + "'";
        boolean check = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            check = false;
            JOptionPane.showMessageDialog(this, "Chỉnh sửa thất bại");
        }
        if(check){
            this.loadNV();
            JOptionPane.showMessageDialog(this, "Đã hoàn tất chỉnh sửa.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_NVSex = new javax.swing.ButtonGroup();
        jLabel_DanhSachCanBoGiangVien = new javax.swing.JLabel();
        jPanel_Detail = new javax.swing.JPanel();
        jLabel_Name = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel_MaNV = new javax.swing.JLabel();
        jTextField_Id = new javax.swing.JTextField();
        jLabel_Email = new javax.swing.JLabel();
        jTextField_Email = new javax.swing.JTextField();
        jLabel_Add = new javax.swing.JLabel();
        jTextField_Add = new javax.swing.JTextField();
        jLabel_TypeNV = new javax.swing.JLabel();
        jComboBox_TypeNV = new javax.swing.JComboBox<>();
        jLabel_ToBoMon = new javax.swing.JLabel();
        jComboBox_ToBoMon = new javax.swing.JComboBox<>();
        jLabel_Sex = new javax.swing.JLabel();
        jRadioButton_Male = new javax.swing.JRadioButton();
        jRadioButton_Female = new javax.swing.JRadioButton();
        jPanel_ChucNang = new javax.swing.JPanel();
        jButton_Save = new javax.swing.JButton();
        jButton_SaveAs = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jScrollPane_NV = new javax.swing.JScrollPane();
        jTable_NV = new javax.swing.JTable();
        jButton_Search = new javax.swing.JButton();

        setTitle("Nhân viên");
        setMinimumSize(new java.awt.Dimension(568, 452));
        setResizable(false);
        setSize(new java.awt.Dimension(568, 452));

        jLabel_DanhSachCanBoGiangVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel_DanhSachCanBoGiangVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_DanhSachCanBoGiangVien.setText("DANH SÁCH CÁN BỘ GIẢNG VIÊN");

        jPanel_Detail.setLayout(new java.awt.GridLayout(4, 4));

        jLabel_Name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Name.setText("Họ tên");
        jPanel_Detail.add(jLabel_Name);

        jTextField_Name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel_Detail.add(jTextField_Name);

        jLabel_MaNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_MaNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_MaNV.setText("Mã nhân viên");
        jPanel_Detail.add(jLabel_MaNV);

        jTextField_Id.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel_Detail.add(jTextField_Id);

        jLabel_Email.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Email.setText("Email");
        jPanel_Detail.add(jLabel_Email);

        jTextField_Email.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextField_Email.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_Email.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField_Email.setText("ex@email.com");
        jTextField_Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_EmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_EmailFocusLost(evt);
            }
        });
        jTextField_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_EmailActionPerformed(evt);
            }
        });
        jPanel_Detail.add(jTextField_Email);

        jLabel_Add.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Add.setText("Địa chỉ");
        jPanel_Detail.add(jLabel_Add);

        jTextField_Add.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel_Detail.add(jTextField_Add);

        jLabel_TypeNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_TypeNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TypeNV.setText("Loại nhân viên");
        jPanel_Detail.add(jLabel_TypeNV);

        jComboBox_TypeNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TypeNVActionPerformed(evt);
            }
        });
        jPanel_Detail.add(jComboBox_TypeNV);

        jLabel_ToBoMon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_ToBoMon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ToBoMon.setText("Tổ/Bộ môn");
        jPanel_Detail.add(jLabel_ToBoMon);

        jPanel_Detail.add(jComboBox_ToBoMon);

        jLabel_Sex.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel_Sex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Sex.setText("Giới tính");
        jPanel_Detail.add(jLabel_Sex);

        buttonGroup_NVSex.add(jRadioButton_Male);
        jRadioButton_Male.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton_Male.setSelected(true);
        jRadioButton_Male.setText("Nam");
        jPanel_Detail.add(jRadioButton_Male);

        buttonGroup_NVSex.add(jRadioButton_Female);
        jRadioButton_Female.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButton_Female.setText("Nữ");
        jRadioButton_Female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_FemaleActionPerformed(evt);
            }
        });
        jPanel_Detail.add(jRadioButton_Female);

        jButton_Save.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_Save.setText("Thêm mới");
        jButton_Save.setToolTipText("");
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });

        jButton_SaveAs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_SaveAs.setText("Lưu thay đổi");
        jButton_SaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveAsActionPerformed(evt);
            }
        });

        jButton_Delete.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_Delete.setText("Xóa");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ChucNangLayout = new javax.swing.GroupLayout(jPanel_ChucNang);
        jPanel_ChucNang.setLayout(jPanel_ChucNangLayout);
        jPanel_ChucNangLayout.setHorizontalGroup(
            jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChucNangLayout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jButton_Save)
                .addGap(42, 42, 42)
                .addComponent(jButton_Delete)
                .addGap(36, 36, 36)
                .addComponent(jButton_SaveAs)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_ChucNangLayout.setVerticalGroup(
            jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Save)
                    .addComponent(jButton_SaveAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTable_NV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Địa chỉ", "Email", "Giới tính", "Tổ/ Bộ môn", "Loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_NV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_NVMouseClicked(evt);
            }
        });
        jScrollPane_NV.setViewportView(jTable_NV);

        jButton_Search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_Search.setText("Tìm");
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_ChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel_DanhSachCanBoGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Search)))
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane_NV)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_DanhSachCanBoGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_ChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_Search))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_TypeNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TypeNVActionPerformed
       
    }//GEN-LAST:event_jComboBox_TypeNVActionPerformed

    private void jRadioButton_FemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_FemaleActionPerformed
       
    }//GEN-LAST:event_jRadioButton_FemaleActionPerformed

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        String name = jTextField_Name.getText();
        String id = jTextField_Id.getText();
        String email = jTextField_Email.getText();
        String address = jTextField_Add.getText();
        String sex = "";
        String loaiNV = jComboBox_TypeNV.getSelectedItem().toString();
        String toBoMon = jComboBox_ToBoMon.getSelectedItem().toString();
        for (Enumeration<AbstractButton> buttons = buttonGroup_NVSex.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                sex = button.getText();
            }
        }
        if(this.kiemTraTonTaiMaNV(id) == 1){
            JOptionPane.showMessageDialog(this, "Nhân viên đã có trong hệ thống, bạn chỉ được thay đổi.");
            return;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được bỏ trống.");
            return;
        }
        else if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được bỏ trống.");
            return;
        }
        if(email.matches("ex@email.com")){
            email = "";
        }
        
        if(!kiemTraEmail(email) && !email.equals("")){
            JOptionPane.showMessageDialog(this, "Email sai định dạng.");
            return;
        }
        else{
            luuDS(name, id, sex, email, address, toBoMon, loaiNV);              
            this.loadNV();
            clear();
        }
    }//GEN-LAST:event_jButton_SaveActionPerformed
    
    private void jButton_SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveAsActionPerformed
        String name = jTextField_Name.getText();
        String id = jTextField_Id.getText();
        String email = jTextField_Email.getText();
        String address = jTextField_Add.getText();
        String sex = "";
        String loaiNV = jComboBox_TypeNV.getSelectedItem().toString();
        String toBoMon = jComboBox_ToBoMon.getSelectedItem().toString();
        for (Enumeration<AbstractButton> buttons = buttonGroup_NVSex.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                sex = button.getText();
            }
        }
        //Kiểm tra dữ liệu trước khi lưu vào Database. 
        if(this.kiemTraTonTaiMaNV(id) == 0){
            JOptionPane.showMessageDialog(this, "Nhân viên chưa có trong hệ thống. Bạn không thể sửa đổi.");
            return;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được bỏ trống.");
            return;
        }
        else if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được bỏ trống.");
            return;
        }
        if(email.equals("ex@email.com")){
            email = "";
        }
        if(!kiemTraEmail(email) && !email.equals("")){
            JOptionPane.showMessageDialog(this, "Email sai định dạng.");
        }
        else {
            this.luuSuaDoi(name, id, sex, email, address, toBoMon, loaiNV);
            clear();
        }
    }//GEN-LAST:event_jButton_SaveAsActionPerformed

    private void jTable_NVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_NVMouseClicked
        int i = jTable_NV.getSelectedRow();
        DefaultTableModel tableNV = (DefaultTableModel) jTable_NV.getModel();
        jTextField_Id.setText((String) tableNV.getValueAt(i, 0));
        jTextField_Name.setText((String) tableNV.getValueAt(i, 1));
        jTextField_Email.setText((String) tableNV.getValueAt(i, 3));
        jTextField_Add.setText((String) tableNV.getValueAt(i, 2));
        if(tableNV.getValueAt(i, 4).toString().toLowerCase().equals("nam")){
            jRadioButton_Male.setSelected(true);
        }
        else{
            jRadioButton_Female.setSelected(true);
        }
        jComboBox_TypeNV.setSelectedItem(tableNV.getValueAt(i, 6).toString());
        jComboBox_ToBoMon.setSelectedItem(this.get_ToBoMon_NhanVien(tableNV.getValueAt(i, 0).toString()));
    }//GEN-LAST:event_jTable_NVMouseClicked

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        String id = jTextField_Id.getText();
        int check;
        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mã nhân viên bạn muốn xóa");
        }
        else{
            if (this.kiemTraTonTaiMaNV(id) == 1) {
                int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Xác nhận", 0);
                if(ret == JOptionPane.CANCEL_OPTION)
                    return;
                else if(ret == JOptionPane.OK_OPTION)
                {
                    xoaMaNV(id);
                    clear();
                    this.loadNV();
                }
            } 
            else {
                JOptionPane.showMessageDialog(this, "Nhân viên này chưa có trong hệ thống");
            }
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jTextField_EmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_EmailFocusGained
        jTextField_Email.setText(""); 
        jTextField_Email.setForeground(Color.black);
    }//GEN-LAST:event_jTextField_EmailFocusGained

    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SearchActionPerformed
        if(jTextField_Id.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Hãy nhập mã nhân viên");
            return;
        }
        Connection con = Conn.conn();
        String sql = "SELECT * from V_NHANVIEN where MANV = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jTextField_Id.getText());
            ResultSet rs = ps.executeQuery();
            rs.next();
            try{
                jComboBox_TypeNV.setSelectedItem(rs.getString("LOAINV"));
                jComboBox_ToBoMon.setSelectedItem(rs.getString("TENTBM"));
                jTextField_Name.setText(rs.getString("HOTEN"));
                jTextField_Email.setText(rs.getString("EMAIL"));
                jTextField_Add.setText(rs.getString("DIACHI"));
                rs.close();
                ps.close();
                con.close();
            }
            
            catch(NullPointerException e){
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không trùng khớp");
        }  
    }//GEN-LAST:event_jButton_SearchActionPerformed

    private void jTextField_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_EmailActionPerformed
       
    }//GEN-LAST:event_jTextField_EmailActionPerformed

    private void jTextField_EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_EmailFocusLost
        // TODO add your handling code here:
        if(jTextField_Email.getText().equals(""))
        {
            jTextField_Email.setText("ex@email.com");
            jTextField_Email.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jTextField_EmailFocusLost

    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
               
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_NVSex;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Save;
    private javax.swing.JButton jButton_SaveAs;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JComboBox<String> jComboBox_ToBoMon;
    private javax.swing.JComboBox<String> jComboBox_TypeNV;
    private javax.swing.JLabel jLabel_Add;
    private javax.swing.JLabel jLabel_DanhSachCanBoGiangVien;
    private javax.swing.JLabel jLabel_Email;
    private javax.swing.JLabel jLabel_MaNV;
    private javax.swing.JLabel jLabel_Name;
    private javax.swing.JLabel jLabel_Sex;
    private javax.swing.JLabel jLabel_ToBoMon;
    private javax.swing.JLabel jLabel_TypeNV;
    private javax.swing.JPanel jPanel_ChucNang;
    private javax.swing.JPanel jPanel_Detail;
    private javax.swing.JRadioButton jRadioButton_Female;
    private javax.swing.JRadioButton jRadioButton_Male;
    private javax.swing.JScrollPane jScrollPane_NV;
    private javax.swing.JTable jTable_NV;
    private javax.swing.JTextField jTextField_Add;
    private javax.swing.JTextField jTextField_Email;
    private javax.swing.JTextField jTextField_Id;
    private javax.swing.JTextField jTextField_Name;
    // End of variables declaration//GEN-END:variables
}
