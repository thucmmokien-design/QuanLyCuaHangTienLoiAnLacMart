package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.datacofig;
public class CaLamdao {
    private Connection con;
    public CaLamdao() {
    try{
        datacofig config = new datacofig();
        con = config.getConnection();
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    public ResultSet LayThongTinCaLamViec(){
        String sql = """
        SELECT c.id, c.tenca, c.ngay, c.giobatdau, c.gioketthuc, c.manhanvien, n.hoten
        FROM CaLamViec c
        JOIN NhanVien n ON c.manhanvien = n.maNV
        """;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null    ;
    }
    public Boolean ThemCalam(String tenca, String ngay, String giobatdau, String gioketthuc, String manhanvien, String hoten) {
        String sql = "INSERT INTO CaLamViec (tenca, ngay,  giobatdau, gioketthuc, manhanvien, hoten) Values (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenca);
            ps.setString(2, ngay);
            ps.setString(3, giobatdau);
            ps.setString(4, gioketthuc);
            ps.setString(5, manhanvien);
            ps.setString(6, hoten);
            return ps.executeUpdate() > 0;
        }catch ( Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean SuaCaLam(
            int id,
            String tenca,
            String ngay,
            String giobatdau,
            String gioketthuc,
            String manhanvien,
            String hoten
    ) {
        String sql =
                "UPDATE CaLamViec SET tenca = ?, ngay = ?, giobatdau = ?, gioketthuc = ?, manhanvien = ?, hoten = ? " +
                        "WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenca);
            ps.setString(2, ngay);
            ps.setString(3, giobatdau);
            ps.setString(4, gioketthuc);
            ps.setString(5, manhanvien);
            ps.setString(6, hoten);
            ps.setInt(7, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean Xoacalam(int id) {
        String sql = "DELETE FROM CaLamViec WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ResultSet LayTongGioLamTheoNhanVien() {
        String sql =
                "SELECT manhanvien, hoten, " +
                        "ROUND( " +
                        "  SUM( " +
                        "    CASE " +
                        "      WHEN gioketthuc >= giobatdau " +
                        "      THEN DATEDIFF(MINUTE, giobatdau, gioketthuc) " +
                        "      ELSE DATEDIFF(MINUTE, giobatdau, CAST('23:59:59' AS TIME)) + " +
                        "           DATEDIFF(MINUTE, CAST('00:00:00' AS TIME), gioketthuc) + 1 " + // +1 để bù 1 giây
                        "    END " +
                        "  ) / 60.0, 2" +
                        ") AS tonggio " +
                        "FROM CaLamViec " +
                        "GROUP BY manhanvien, hoten";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
