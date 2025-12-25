package dao;
import config.datacofig;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class NhanVien {
    private Connection conn;
    public NhanVien(){
        try {
            datacofig config = new datacofig();
            conn = config.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }
    public ResultSet LayThongTinNhanVien() {
        String sql = "SELECT username, password, hoten, sodienthoai, ngaysinh FROM NhanVien";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean ThemThongTinNhanVien(String username, String password, String hoten, String sodienthoai, String ngaysinh) {
        String sql = "INSERT INTO NhanVien (username, password, hoten, sodienthoai, ngaysinh) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2, password);
            pstmt.setString(3, hoten);
            pstmt.setString(4, sodienthoai);
            Date date = Date.valueOf(ngaysinh);
            pstmt.setDate(5, date);
            pstmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean XoaThongTinNhanVien(String username) {
        String sql = "DELETE FROM NhanVien WHERE username = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean LoginNhanVien(String username, String password){
        String sql = "SELECT * FROM NhanVien WHERE username = ? AND password = ?";
        try{
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1,username);
             pstmt.setString(2, password);
             ResultSet rs = pstmt.executeQuery();
             if(rs.next()){
                return true;
             }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
