package Ui;

import javax.swing.*;

public class PageNhanVien extends JFrame {
    private JLabel jpanel_nhanvien;
    private JPanel jpan_nhanvien;

    public PageNhanVien() {
        setContentPane(jpan_nhanvien);
        setTitle("Home_NhanVien");
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
