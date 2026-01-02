package dao;

import config.datacofig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Thongbaodao {
    private Connection conn;

    public Thongbaodao() {
        try {
            datacofig config = new datacofig();
            conn = config.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet LayThongKeLuong() {
        String sql = "SELECT * FROM ThongBaoLuong";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean ThemThongKeLuong(
            String manv,
            String hoten,
            float thuong,
            float tongluong,
            String loinhan) {

        String sql = "INSERT INTO ThongBaoLuong(manv,hoten,thuong,TongLuongThang,loinhan) " +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, manv);
            ps.setString(2, hoten);
            ps.setFloat(3, thuong);
            ps.setFloat(4, tongluong);
            ps.setString(5, loinhan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
