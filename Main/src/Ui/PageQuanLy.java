package Ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import dao.NhanVien;
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
    private JPanel jpanel_thongbao;
    private JPanel jpanel_stack;
    private DefaultTableModel model;
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
    private JPanel panel2;
    public PageQuanLy() {
        setContentPane(pnStackNhanVien);
        CardLayout cl = new CardLayout();
        jpanel_stack.setLayout(cl);
        jpanel_stack.add(jpanel_quanlynhanvien, "nhanvien");
        jpanel_stack.add(jpanel_quanlycalam, "calamviec");
        jpanel_stack.add(jpanel_quanlyluong, "luong");
        jpanel_stack.add(jpanel_thongbao, "thongke");
        button_nhanvien.addActionListener(e -> {
            cl.show(jpanel_stack, "nhanvien");
        });
        button_calam.addActionListener(e -> {
            cl.show(jpanel_stack, "calamviec");
        });
        button_luong.addActionListener(e -> {
            cl.show(jpanel_stack, "luong");
        });
        button_thongke.addActionListener(e -> {
            cl.show(jpanel_stack, "thongke");
        });
        button_them.addActionListener(e -> {
            themnhanvien();
        });
        button_xoa.addActionListener(e -> {
            xoanhanvien();
        });
        button_clear.addActionListener(e ->{
            clearForm();
        });
        button_update.addActionListener(e ->{
            suanhavien();
        });
        table_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                clickTableNhanVien();
            }
        });
        comboBox_giotinh.addItem("Nam");
        comboBox_giotinh.addItem("Nữ");
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
        String[] columnNames = {"MNV", "Username", "Password", "Ho ten", "Gioi Tinh", "SDT", "NgaySinh"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_nhanvien.setModel(model);
        table_nhanvien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_nhanvien.getTableHeader().setReorderingAllowed(false);
        NhanVien nv = new  NhanVien();
        ResultSet rs = nv.LayThongTinNhanVien();
        try {
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
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
    private void clearForm() {
        label_id.setText("");
        textField_username.setText("");
        textField_password.setText("");
        textField_hoten.setText("");
        comboBox_ngay.setSelectedIndex(0);
        textField_sdt.setText("");
        comboBox_ngay.setSelectedIndex(0);
        comboBox_thang.setSelectedIndex(0);
        comboBox_nam.setSelectedIndex(0);
    }
    private String getNgaySinhFromComboBox() {
        int ngay  = (int) comboBox_ngay.getSelectedItem();
        int thang = (int) comboBox_thang.getSelectedItem();
        int nam   = (int) comboBox_nam.getSelectedItem();
        return String.format("%04d-%02d-%02d", nam, thang, ngay);
    }
    private void themnhanvien() {
        String username = textField_username.getText().trim();
        String password = textField_password.getText().trim();
        String hoten    = textField_hoten.getText().trim();
        String gioitinh = comboBox_giotinh.getSelectedItem().toString();
        String sdt      = textField_sdt.getText().trim();
        if (username.isEmpty() || password.isEmpty() || hoten.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        String ngaysinh = getNgaySinhFromComboBox();
        NhanVien nv = new NhanVien();
        boolean ok = nv.ThemThongTinNhanVien(username, password, hoten, gioitinh, sdt, ngaysinh);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            LoadTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
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
        String id = label_id.getText();
        String username = textField_username.getText().trim();
        String password = textField_password.getText().trim();
        String hoten    = textField_hoten.getText().trim();
        String gioitinh = comboBox_giotinh.getSelectedItem().toString();
        String sdt    = textField_sdt.getText().trim();
        String ngaysinh = getNgaySinhFromComboBox();
        NhanVien nv = new NhanVien();
        boolean ok = nv.SuaThongTinNhanVien(id,username,password,hoten,gioitinh,sdt,ngaysinh);
        if(ok){
            JOptionPane.showMessageDialog(this,"Sửa Thông Tin Thành Công");
            LoadTable();
            clearForm();
        }else{
            JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
        }
    }
    private void clickTableNhanVien() {
        int row = table_nhanvien.getSelectedRow();
        if (row == -1) return;
        String maNV = model.getValueAt(row, 0).toString();
        String username = model.getValueAt(row, 1).toString();
        String password = model.getValueAt(row, 2).toString();
        String hoten    = model.getValueAt(row, 3).toString();
        String gioitinh = model.getValueAt(row, 4).toString();
        String sdt      = model.getValueAt(row, 5).toString();
        String ngaysinh = model.getValueAt(row, 6).toString();
        label_id.setText(maNV);
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
}
