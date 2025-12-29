package Ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import dao.CaLamdao;
import dao.Luongdao;
import dao.NhanVien;
import java.text.NumberFormat;
import java.util.Locale;

public class PageQuanLy extends JFrame {
    private JPanel pnStackNhanVien;
    private JButton button_nhanvien;
    private JButton button_luong;
    private JButton button_thongke;
    private JPanel pnStack;
    private JPanel panel1;
    private JButton button_quanlynhanvien;
    private JButton button_calam;
    private JPanel jpanel_quanlynhanvien;
    private JPanel jpanel_quanlycalam;
    private JPanel jpanel_quanlyluong;
    private JPanel jpanel_thongke;
    private JPanel jpanel_stack;
    private DefaultTableModel modelnhanvien;
    private DefaultTableModel modelCaLam;
    private DefaultTableModel modelLuong;
    private JTable table_nhanvien;
    private JTextField textField_username;
    private JTextField textField_password;
    private JTextField textField_hoten;
    private JTextField textField_sdt;
    private JComboBox comboBox_ngay;
    private JComboBox comboBox_thang;
    private JComboBox comboBox_nam;
    private JButton button_them;
    private JButton button_xoa;
    private JButton button_update;
    private JScrollPane scrollPane;

    private JComboBox comboBox_giotinh;
    private JLabel label_id;
    private JButton button_clear;
    private JTable table_luong;
    private JTextField textField_tongngaylam;
    private JTextField textField_luoncoban;
    private JTextField textField_thuongthem;
    private JLabel label_tongluong;
    private JPanel jpanel_button;
    private JButton button_themluong;
    private JButton button_xoaluong;
    private JButton button_loadluong;
    private JTable table_QuanLyCalam;
    private JTextField textField_tenca;
    private JTextField textField_giobatdau;
    private JTextField textField_gioketthuc;
    private JComboBox comboBox_chonnhanvien;
    private JButton button_themcalama;
    private JButton Button_suacalam;
    private JButton button_xoacalam;
    private JButton button_lammoicalam;
    private JTextField textField_nhapngay;
    private JTextField textField_manhanvien;
    private JLabel jlabel_hotencalam;
    private JLabel id_calam;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton xuấtBáoCáoButton;
    private JLabel jlabel_hetenluong;
    private JLabel label_mnv;
    private JLabel label_tongsohlam1ngay;
    private JPanel panel2;
    public PageQuanLy() {
        setContentPane(pnStackNhanVien);
        NhanVien nv = new  NhanVien();

        CardLayout cl = new CardLayout();
        jpanel_stack.setLayout(cl);
        jpanel_stack.add(jpanel_quanlynhanvien, "nhanvien");
        jpanel_stack.add(jpanel_quanlycalam, "calamviec");
        jpanel_stack.add(jpanel_quanlyluong, "luong");
        jpanel_stack.add(jpanel_thongke, "thongke");
        button_nhanvien.addActionListener(e -> {
            cl.show(jpanel_stack, "nhanvien");
        });
        button_calam.addActionListener(e -> {
            cl.show(jpanel_stack, "calamviec");
            LoadTbale_calam();
        });
        button_luong.addActionListener(e -> {
            cl.show(jpanel_stack, "luong");
            LoadTable_Luong();
        });
        button_thongke.addActionListener(e -> {
            cl.show(jpanel_stack, "thongke");
        });
        button_them.addActionListener(e -> {
            themnhanvien();
        });
        button_themcalama.addActionListener(e -> {
            ThemCalam();
        });
        button_xoa.addActionListener(e -> {
            xoanhanvien();
        });
        button_xoacalam.addActionListener(e -> {
            xoacalam();
        });
        button_clear.addActionListener(e ->{
            clearForm();
        });
        button_lammoicalam.addActionListener(e ->{
            clearForm();
        });
        button_update.addActionListener(e ->{
            suanhavien();
        });
        Button_suacalam.addActionListener(e ->{
            suacalam();
        });
        button_themluong.addActionListener(e ->{
            TongLuongThang();
            SuaLaiLuongLen();
        });
        button_loadluong.addActionListener(e ->{
            LoadTable_Luong_2();
        });
        button_xoaluong.addActionListener(e ->{
            XoaLuong();
        });
        comboBox_chonnhanvien.addActionListener(e -> {
            String maNV = comboBox_chonnhanvien.getSelectedItem().toString();
            String hoten = nv.layTenNhanVienTheoMa(maNV);
            jlabel_hotencalam.setText(hoten);
        });

        textField_manhanvien.setEditable(false);
        table_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                clickTableNhanVien();
            }
        });
        table_QuanLyCalam.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                clickTableCaLam();
            }
        });
        table_luong.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                clickTableLuong();
            }
        });
        ResultSet rs = nv.LayThongTinNhanVien();
        try {
            while(rs.next()) {
                comboBox_chonnhanvien.addItem(rs.getString("maNV"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String maNV = comboBox_chonnhanvien.getSelectedItem().toString();
        String namenhavien = nv.layTenNhanVienTheoMa(maNV);
        jlabel_hotencalam.setText(namenhavien);

        comboBox_giotinh.addItem("Nam");
        comboBox_giotinh.addItem("Nu");

        for (int i = 1; i <= 31; i++) {
            comboBox_ngay.addItem(i);
        }

        for (int i = 1; i <= 12; i++) {
            comboBox_thang.addItem(i);
        }

        for (int i = 1970; i <= 2025; i++) {
            comboBox_nam.addItem(i);
        }
        LoadTable();
        setTitle("Home_QuanLy");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void LoadTable() {
        textField_manhanvien.setEditable(true);
        String[] columnNames = {"MNV", "Username", "Password", "Ho ten", "Gioi Tinh", "SDT", "NgaySinh"};
        modelnhanvien = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_nhanvien.setModel(modelnhanvien);
        table_nhanvien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_nhanvien.getTableHeader().setReorderingAllowed(false);
        NhanVien nv = new  NhanVien();
        ResultSet rs = nv.LayThongTinNhanVien();
        try {
            while (rs != null && rs.next()) {
                modelnhanvien.addRow(new Object[]{
                        rs.getString("maNV"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("hoten"),
                        rs.getString("gioitinh"),
                        rs.getString("sodienthoai"),
                        rs.getDate("ngaysinh")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LoadTbale_calam(){
      String [] colum = {"ID", "TenCa", "Ngay", "Gio Bat Dau", "Gio Ket Thuc", "MNV", "Hoten"};
        modelCaLam = new DefaultTableModel(colum, 0){
          @Override
            public boolean isCellEditable(int row, int column) {
              return false;
          }
      };
      table_QuanLyCalam.setModel(modelCaLam);
      table_QuanLyCalam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      table_QuanLyCalam.getTableHeader().setReorderingAllowed(false);
      CaLamdao cdl = new CaLamdao();
      ResultSet rs = cdl.LayThongTinCaLamViec();
      try{
          while (rs != null && rs.next()) {
              modelCaLam.addRow(new Object[]{
                      rs.getString("id"),
                      rs.getString("tenCa"),
                      rs.getDate("ngay"),
                      rs.getString("giobatdau"),
                      rs.getString("gioketthuc"),
                      rs.getString("manhanvien"),
                      rs.getString("hoten"),
              });
          }
      }catch(Exception e){
          e.printStackTrace();
      }
    }
    private void LoadTable_Luong() {

        String[] column = {
                "MaNV", "HoTen",
                "TongGioLam", "LuongTheoGio",
                "TongNgayLam", "Thuong", "TongLuongThang"
        };

        modelLuong = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table_luong.setModel(modelLuong);
        table_luong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_luong.getTableHeader().setReorderingAllowed(false);

        CaLamdao cl = new CaLamdao();
        Luongdao l = new Luongdao();

        ResultSet rs = cl.LayTongGioLamTheoNhanVien();

        try {
            while (rs != null && rs.next()) {

                String maNV = rs.getString("manhanvien");
                String hoTen = rs.getString("hoten");
                float tongGio = rs.getFloat("tonggio");

                modelLuong.addRow(new Object[]{
                        maNV,
                        hoTen,
                        tongGio,   // ✅ đã có tổng giờ
                        null,
                        null,
                        null,
                        null
                });

                // insert sang bảng Luong (chỉ 1 lần / MaNV)
                l.ThemThongTin(maNV, hoTen, tongGio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LoadTable_Luong_2(){
        String[] column = {
                "MaNV", "HoTen",
                "TongGioLam", "LuongTheoGio",
                "TongNgayLam", "Thuong", "TongLuongThang"
        };
        modelLuong = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table_luong.setModel(modelLuong);
        table_luong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_luong.getTableHeader().setReorderingAllowed(false);
        Luongdao l = new Luongdao();
        ResultSet rs = l.LayThongTinLuong();
        try{
            while (rs != null && rs.next()) {
                modelLuong.addRow(new Object[]{
                        rs.getString("maNv"),
                        rs.getString("hoten"),
                        rs.getFloat("tongGio"),
                        rs.getFloat("LuongNgay"),
                        rs.getInt("songaylam"),
                        rs.getFloat("thuong"),
                        rs.getFloat("tongluong")

                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void clearForm() {
        textField_manhanvien.setEditable(true);
        textField_manhanvien.setText("");
        textField_username.setText("");
        textField_password.setText("");
        textField_hoten.setText("");
        comboBox_ngay.setSelectedIndex(0);
        textField_sdt.setText("");
        comboBox_ngay.setSelectedIndex(0);
        comboBox_thang.setSelectedIndex(0);
        comboBox_nam.setSelectedIndex(0);
        textField_tenca.setText("");
        textField_nhapngay.setText("");
        textField_giobatdau.setText("");
        textField_gioketthuc.setText("");
        jlabel_hotencalam.setText("");
        id_calam.setText("");
    }
    private String getNgaySinhFromComboBox() {
        int ngay  = (int) comboBox_ngay.getSelectedItem();
        int thang = (int) comboBox_thang.getSelectedItem();
        int nam   = (int) comboBox_nam.getSelectedItem();
        return String.format("%04d-%02d-%02d", nam, thang, ngay);
    }
    private void themnhanvien() {
        String maNV = textField_manhanvien.getText().trim();
        String username = textField_username.getText().trim();
        String password = textField_password.getText().trim();
        String hoten    = textField_hoten.getText().trim();
        String gioitinh = comboBox_giotinh.getSelectedItem().toString();
        String sdt      = textField_sdt.getText().trim();
        if (maNV.isEmpty() || username.isEmpty() || password.isEmpty() || hoten.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        String ngaysinh = getNgaySinhFromComboBox();
        NhanVien nv = new NhanVien();
        boolean ok = nv.ThemThongTinNhanVien(maNV, username, password, hoten, gioitinh, sdt, ngaysinh);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            LoadTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
        }
        ResultSet rs = nv.LayThongTinNhanVien();
        try {
            while(rs.next()) {
                comboBox_chonnhanvien.addItem(rs.getString("maNV"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void ThemCalam(){
        String maNV = comboBox_chonnhanvien.getSelectedItem().toString();
        String tenca = textField_tenca.getText().trim();
        String ngay = textField_nhapngay.getText().trim();
        String giobd = textField_giobatdau.getText().trim();
        String giokt = textField_gioketthuc.getText().trim();
        String hoten =jlabel_hotencalam.getText().trim();
        CaLamdao cl  = new CaLamdao();
        boolean ok = cl.ThemCalam(tenca, ngay, giobd, giokt, maNV,hoten);
        if (ok) {
            JOptionPane.showMessageDialog(this,  "Thêm Ca Làm Thành Công");
            LoadTbale_calam();
            clearForm();
        }
        else{
            JOptionPane.showMessageDialog(this, "Thêm Ca Thất Bại");
        }
    }
    private void xoanhanvien() {
        String username = textField_username.getText().trim();
        NhanVien nv = new NhanVien();
        boolean ok = nv.XoaThongTinNhanVien(username);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
            LoadTable();
            clearForm();
        }else{
            JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại");
        }
    }
    private void suanhavien(){
        String msv = textField_manhanvien.getText().trim();
        String username = textField_username.getText().trim();
        String password = textField_password.getText().trim();
        String hoten    = textField_hoten.getText().trim();
        String gioitinh = comboBox_giotinh.getSelectedItem().toString();
        String sdt    = textField_sdt.getText().trim();
        String ngaysinh = getNgaySinhFromComboBox();
        NhanVien nv = new NhanVien();
        boolean ok = nv.SuaThongTinNhanVien(msv,username,password,hoten,gioitinh,sdt,ngaysinh);
        if(ok){
            JOptionPane.showMessageDialog(this,"Sửa Thông Tin Thành Công");
            LoadTable();
            clearForm();
        }else{
            JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
        }
    }
    private void suacalam(){
        int id = Integer.parseInt(id_calam.getText());
        String maNV = comboBox_chonnhanvien.getSelectedItem().toString();
        String tenca = textField_tenca.getText().trim();
        String ngay = textField_nhapngay.getText().trim();
        String giobd = textField_giobatdau.getText().trim();
        String giokt = textField_gioketthuc.getText().trim();
        String hoten = jlabel_hotencalam.getText().trim();
        CaLamdao cl  = new CaLamdao();
        boolean ok = cl.SuaCaLam(id, tenca, ngay, giobd, giokt, maNV,hoten);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Sửa Thông Tin Ca Làm Thành Công");
            LoadTbale_calam();
            clearForm();
        }
        else{
            JOptionPane.showMessageDialog(this, "Sửa ca làm thất bại");
        }
    }
    private void SuaLaiLuongLen() {
        try {
            String mvn = label_mnv.getText().trim();
            String hoten = jlabel_hetenluong.getText().trim();

            float tongGio1Ngay = Float.parseFloat(label_tongsohlam1ngay.getText().trim());
            int tongNgayTrongThang = Integer.parseInt(textField_tongngaylam.getText().trim());
            float luongCoBan = Float.parseFloat(textField_luoncoban.getText().trim());
            float thuongThem = Float.parseFloat(textField_thuongthem.getText().trim());

            // ⚠️ xóa dấu . trước khi parse
            String raw = label_tongluong.getText();
            raw = raw.replaceAll("[^0-9]", "");
            float luongThang = Float.parseFloat(raw);

            Luongdao ld = new Luongdao();
            boolean ok = ld.SuaDuLieuVaoBang(
                    mvn,
                    hoten,
                    tongGio1Ngay,
                    luongCoBan,
                    thuongThem,
                    luongThang,
                    tongNgayTrongThang
            );

            if (ok) {
                JOptionPane.showMessageDialog(this, "Lưu lương thành công");
                LoadTable_Luong_2();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu lương thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void xoacalam() {
        int id = Integer.parseInt(id_calam.getText());
        CaLamdao cl = new CaLamdao();
        boolean ok = cl.Xoacalam(id);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Xóa ca làm thành công!");
            LoadTbale_calam();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa ca làm thất bại!");
        }
    }
    private void TongLuongThang() {
        try {
            float tongGio1Ngay = Float.parseFloat(label_tongsohlam1ngay.getText());
            int tongNgayTrongThang = Integer.parseInt(textField_tongngaylam.getText().trim());
            float luongCoBan = Float.parseFloat(textField_luoncoban.getText().trim());
            float thuongThem = Float.parseFloat(textField_thuongthem.getText().trim());

            double tongLuongThang =
                    tongGio1Ngay * tongNgayTrongThang * luongCoBan + thuongThem;

            // FORMAT TIỀN VN
            NumberFormat vn = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            label_tongluong.setText(vn.format(tongLuongThang));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đầy đủ và đúng định dạng số!",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void XoaLuong(){
        try {
            String manv = label_mnv.getText();
            Luongdao ld = new Luongdao();
            Boolean oke = ld.XoaLuong(manv);
            if (oke) {
                JOptionPane.showMessageDialog(this,"Xóa Lương Thành Công");
                LoadTable_Luong_2();
            }
            else{
                JOptionPane.showMessageDialog(this,"Xóa Lương Thất Bại");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clickTableNhanVien() {
        textField_manhanvien.setEditable(false);
        int row = table_nhanvien.getSelectedRow();
        if (row == -1) return;
        String maNV = modelnhanvien.getValueAt(row, 0).toString();
        String username = modelnhanvien.getValueAt(row, 1).toString();
        String password = modelnhanvien.getValueAt(row, 2).toString();
        String hoten    = modelnhanvien.getValueAt(row, 3).toString();
        String gioitinh = modelnhanvien.getValueAt(row, 4).toString();
        String sdt      = modelnhanvien.getValueAt(row, 5).toString();
        String ngaysinh = modelnhanvien.getValueAt(row, 6).toString();
        textField_manhanvien.setText(maNV);
        textField_username.setText(username);
        textField_password.setText(password);
        textField_hoten.setText(hoten);
        comboBox_ngay.setSelectedItem(gioitinh);
        textField_sdt.setText(sdt);
        String[] parts = ngaysinh.split("-");
        int nam   = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);
        int ngay  = Integer.parseInt(parts[2]);
        comboBox_nam.setSelectedItem(nam);
        comboBox_thang.setSelectedItem(thang);
        comboBox_ngay.setSelectedItem(ngay);
    }
    private void clickTableCaLam(){
        int rows = table_QuanLyCalam.getSelectedRow();
        if (rows == -1) return;
        int id = Integer.parseInt(modelCaLam.getValueAt(rows, 0).toString());
        String tenca = modelCaLam.getValueAt(rows, 1).toString();
        String ngay = modelCaLam.getValueAt(rows, 2).toString();
        String Giobd = modelCaLam.getValueAt(rows, 3).toString();
        String Giokt = modelCaLam.getValueAt(rows, 4).toString();
        String mnv  = modelCaLam.getValueAt(rows, 5).toString();
        String hoten = modelCaLam.getValueAt(rows, 6).toString();
        id_calam.setText(String.valueOf(id));
        comboBox_chonnhanvien.setSelectedItem(mnv);
        textField_tenca.setText(tenca);
        textField_giobatdau.setText(Giobd);
        textField_gioketthuc.setText(Giokt);
        jlabel_hotencalam.setText(hoten);
        textField_nhapngay.setText(ngay);
    }
    private void clickTableLuong() {
        int row = table_luong.getSelectedRow();
        if (row == -1) return;

        String maNV = String.valueOf(modelLuong.getValueAt(row, 0));
        String hoTen = String.valueOf(modelLuong.getValueAt(row, 1));

        Object tg = modelLuong.getValueAt(row, 2);
        Object lg = modelLuong.getValueAt(row, 3);
        Object tn = modelLuong.getValueAt(row, 4);
        Object th = modelLuong.getValueAt(row, 5);
        Object tl = modelLuong.getValueAt(row, 6);

        String tongGio = tg == null ? "" : tg.toString();
        String luongTheoGio = lg == null ? "" : lg.toString();
        String tongNgayLam = tn == null ? "" : tn.toString();
        String thuong = th == null ? "" : th.toString();

        // ✅ LẤY SỐ CHUẨN
        double tongLuongSo = 0;
        if (tl instanceof Number) {
            tongLuongSo = ((Number) tl).doubleValue();
        }

        // ✅ FORMAT VND
        NumberFormat vn = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String tongLuongVND = vn.format(tongLuongSo);

        label_mnv.setText(maNV);
        jlabel_hetenluong.setText(hoTen);
        label_tongsohlam1ngay.setText(tongGio);
        textField_tongngaylam.setText(tongNgayLam);
        textField_luoncoban.setText(luongTheoGio);
        textField_thuongthem.setText(thuong);
        label_tongluong.setText(tongLuongVND);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
