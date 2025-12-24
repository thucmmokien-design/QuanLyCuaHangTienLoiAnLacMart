package Ui;

import javax.swing.*;
import dao.QuanLy;
import dao.NhanVien;
import java.awt.*;

public class Login extends  JFrame {
    private JPanel JPane_Login;
    private JTextField textField_username;
    private JTextField textField_password;
    private JButton button_Dangnhap;
    private JRadioButton RadioButton_nhanvien;
    private JRadioButton RadioButton_quanly;
    private JLabel JLabel_Thongbao;

    public JPanel getjpanel(){
        return JPane_Login;
    }
    public Login(){
        setContentPane(JPane_Login);
        setTitle("Login");
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button_Dangnhap.addActionListener(e->LoginHt());
    }
    public void LoginHt(){
        QuanLy tkquanly = new QuanLy();
        NhanVien tknhanvien = new NhanVien();
        String username = textField_username.getText(); String password = textField_password.getText();
        ButtonGroup group = new ButtonGroup();
        group.add(RadioButton_nhanvien);group.add(RadioButton_quanly);
        boolean checkradio_nhanvien = RadioButton_nhanvien.isSelected();
        boolean checkradioquanly = RadioButton_quanly.isSelected();
        System.out.println(checkradio_nhanvien+" "+checkradioquanly);
        if (checkradioquanly){
            System.out.println("Login Bang Tai Khoan Quan Ly");
            boolean check = tkquanly.LoginAdmin(username, password);
            if(username.isEmpty() || password.isEmpty()){
                JLabel_Thongbao.setText("Bạn nhập thiếu user hoặc pass");
                JLabel_Thongbao.setForeground(Color.RED);
            }
            else {
                if(check){
                    JLabel_Thongbao.setText("Đăng nhâp thành công");
                    JLabel_Thongbao.setForeground(Color.GREEN);
                    new PageQuanLy().setVisible(true);
                    this.dispose();
                }
                else{
                    JLabel_Thongbao.setText("Đăng nhâp thất bại");
                    JLabel_Thongbao.setForeground(Color.RED);
                }
            }
        }
        else if(checkradio_nhanvien){
            System.out.println("Login Bang Tai Khoan Nhan Vien");
            boolean check =  tknhanvien.LoginNhanVien(username, password);
            if(username.isEmpty() || password.isEmpty()){
                JLabel_Thongbao.setText("Bạn nhập thiếu user hoặc pass");
                JLabel_Thongbao.setForeground(Color.RED);
            }
            else {
                if(check){
                    JLabel_Thongbao.setText("Đăng nhâp thành công");
                    JLabel_Thongbao.setForeground(Color.GREEN);
                    new PageNhanVien().setVisible(true);
                    this.dispose();
                }
                else{
                    JLabel_Thongbao.setText("Đăng nhâp thất bại");
                    JLabel_Thongbao.setForeground(Color.RED);
                }
            }
        }
    }
}
