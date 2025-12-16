package Ui;

import javax.swing.*;
import dao.TaiKhoanDao;

import java.awt.*;

public class Login {
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
        button_Dangnhap.addActionListener(e->LoginHt());
    }
    public void LoginHt(){
        TaiKhoanDao tkdao = new TaiKhoanDao();
        String username = textField_username.getText(); String password = textField_password.getText();
        boolean check = tkdao.checkTaiKhoan(username, password);
        if(username.isEmpty() || password.isEmpty()){
            JLabel_Thongbao.setText("Bạn nhập thiếu user hoặc pass");
            JLabel_Thongbao.setForeground(Color.RED);
        }
        else {
            if(check){
                JLabel_Thongbao.setText("Đăng nhâp thành công");
                JLabel_Thongbao.setForeground(Color.GREEN);
            }
            else{
                JLabel_Thongbao.setText("Đăng nhâp thất bại");
                JLabel_Thongbao.setForeground(Color.RED);
            }
        }

    }
}
