package dao;
import config.datacofig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDao {
    public TaiKhoanDao() {}
    public boolean checkTaiKhoan(String usename, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE username = ? AND password = ?";
        try{
            datacofig connext = new datacofig();
            Connection conn = connext.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usename);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
