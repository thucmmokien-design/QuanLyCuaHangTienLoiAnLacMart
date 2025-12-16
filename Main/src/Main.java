import javax.swing.*;
import Ui.Login;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dang Nhap");
        Login login = new Login();
        frame.setContentPane(login.getjpanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}