package Ui;

import javax.swing.*;
import java.awt.CardLayout;

public class PageQuanLy extends JFrame {
    private JPanel pnStackNhanVien;
    private JPanel pnStack;
    private JPanel panel1;
    private JPanel Jpanel_menu;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel panel2;

    public PageQuanLy() {
        setContentPane(pnStackNhanVien);

        CardLayout cl = new CardLayout();
        pnStack.setLayout(cl);

        pnStack.add(panel1, "nhanvien");
        pnStack.add(panel2, "calamviec");

        button1.addActionListener(e -> {
            cl.show(pnStack, "nhanvien");
        });

        button2.addActionListener(e -> {
            cl.show(pnStack, "calamviec");
        });

        setTitle("Home_QuanLy");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
