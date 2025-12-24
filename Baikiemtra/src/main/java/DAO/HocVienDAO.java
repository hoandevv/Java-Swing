/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.HocVien;

/**
 *
 * @author Hoan
 */
public class HocVienDAO {
    public void insert(HocVien hv) throws Exception{
        String sql = "INSERT INTO hoc_vien (mahv,tenhocvien,lop,diem_tb) VALUES(?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1,hv.getMaHV());
        ps.setString(2,hv.getTenHocVien());
        ps.setString(3,hv.getLop());
        ps.setDouble(4,hv.getDiemTB());
        
        ps.executeUpdate();
        ps.close();
        con.close();
    }
     public void update(HocVien hv) throws Exception {
        String sql = "UPDATE hoc_vien SET tenhocvien=?, lop=?, diem_tb=? WHERE mahv=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, hv.getTenHocVien());
        ps.setString(2, hv.getLop());
        ps.setDouble(3, hv.getDiemTB());
        ps.setString(4, hv.getMaHV());

        ps.executeUpdate();
        ps.close();
        con.close();
    }
      public void delete(String maHV) throws Exception {
        String sql = "DELETE FROM hoc_vien WHERE mahv=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maHV);

        ps.executeUpdate();
        ps.close();
        con.close();
    }
     public List<HocVien> findAll() throws Exception{
         List<HocVien> list = new ArrayList<>();
         String sql = "SELECT * FROM hoc_vien";
         
         Connection con = DBConnection.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql);
        
          while (rs.next()) {
            HocVien hv = new HocVien(
                    rs.getString("mahv"),
                    rs.getString("tenhocvien"),
                    rs.getString("lop"),
                    rs.getDouble("diem_tb")
            );
            list.add(hv);
        }

        rs.close();
        st.close();
        con.close();
        return list;
         
     }
     public HocVien findByMa(String maHV) throws Exception {
        String sql = "SELECT * FROM hoc_vien WHERE mahv=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maHV);
        ResultSet rs = ps.executeQuery();

        HocVien hv = null;
        if (rs.next()) {
            hv = new HocVien(
                    rs.getString("mahv"),
                    rs.getString("tenhocvien"),
                    rs.getString("lop"),
                    rs.getDouble("diem_tb")
            );
        }

        rs.close();
        ps.close();
        con.close();
        return hv;
    }
}