/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.HocVienDAO;
import java.util.List;
import model.HocVien;

/**
 *
 * @author Hoan
 */
public class HocVienController {
    private HocVienDAO hocVienDAO;
    
    
    public enum Mode {
        ADD, EDIT;
    }
    
    private Mode CurrentMode = Mode.ADD;
    
    public HocVienController(){
        hocVienDAO = new HocVienDAO();
    }
    public void chonchedoTHEM(){
        CurrentMode = Mode.ADD;
    }
      public void chonchedoSUA(){
        CurrentMode = Mode.EDIT;
    }
      
     public void luu(String maHV , String tenHocVien, String lop , double diemTB) throws Exception{
         if(maHV == null || maHV.trim().isEmpty()){
              throw new IllegalArgumentException("Mã học viên không được để trống");
        }
         if (tenHocVien == null || tenHocVien.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên học viên không được để trống");
        }
         if (diemTB < 0 || diemTB > 10) {
            throw new IllegalArgumentException("Điểm trung bình phải từ 0 đến 10");
        }
        HocVien hv = new HocVien(maHV, tenHocVien, lop, diemTB);
        
        
        // QUYẾT ĐỊNH THÊM HAY SỬA
         if (CurrentMode == Mode.ADD) {
            hocVienDAO.insert(hv);
        } else if (CurrentMode == Mode.EDIT) {
            hocVienDAO.update(hv);
        }
     }
     
     public void xoa(String maHV) throws Exception{
         hocVienDAO.delete(maHV);
     }
     
     public List<HocVien> layDanhSach() throws Exception{
         return hocVienDAO.findAll();
     }
    // thực hiện khi lấy 1 học viên 
         public HocVien layHocVienTheoMa(String maHV) throws Exception {
        return hocVienDAO.findByMa(maHV);
    }

}
