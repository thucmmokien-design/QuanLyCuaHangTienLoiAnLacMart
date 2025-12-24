package dao;
import config.datacofig;
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
