package dao;

import config.datacofig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Luongdao {

    private Connection conn;

    public Luongdao() {
        try {
            datacofig config = new datacofig();
            conn = config.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load lương (chỉ MaNV + HoTen)
    public ResultSet LayThongTinLuong() {
        String sql = "SELECT * FROM Luong";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert MaNV từ CaLamViec sang Luong
    public boolean ThemThongTin(String maNV, String hoTen, float tonggoi) {
        String sql = "INSERT INTO Luong (MaNV, HoTen, tongGio) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, hoTen);
            ps.setFloat(3, tonggoi);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            // tránh insert trùng
            return false;
        }
    }
    public boolean SuaDuLieuVaoBang(
            String maNV,
            String hoTen,
            float tongGio,
            float luongNgay,
            float thuong,
            float tongLuong,
            int songaylam
    ) {
        String sql = "UPDATE Luong SET " +
                "hoTen = ?, " +
                "tongGio = ?, " +
                "luongNgay = ?, " +
                "thuong = ?, " +
                "tongLuong = ?, " +
                "songaylam = ?"+
                "WHERE maNV = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoTen);
            ps.setFloat(2, tongGio);
            ps.setFloat(3, luongNgay);
            ps.setFloat(4, thuong);
            ps.setFloat(5, tongLuong);
            ps.setInt(6, songaylam);
            ps.setString(7, maNV);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean XoaLuong(String maNV){
        String sql = "DELETE FROM Luong WHERE maNV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
