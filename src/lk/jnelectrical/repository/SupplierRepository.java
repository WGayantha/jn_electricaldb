
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;

import lk.jnelectrical.dto.SupplierDto;


public class SupplierRepository {
    
    public int addSupplier(SupplierDto dto)throws SQLException {
        String sql = "INSERT INTO Supplier(supplier_id, sup_lname, "
                + "sup_fname, sup_contact,"
                + " sup_add1, sup_add2)VALUES(?, ?, ?, ?, ?, ?) ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
           
          ps.setString(1, dto.getSupplierId());
          ps.setString(2, dto.getSuplName());
          ps.setString(3, dto.getSupfName());
          ps.setString(4, dto.getSupContact());
          ps.setString(5, dto.getSupAdd1());
          ps.setString(6, dto.getSupAdd2());
         
          return ps.executeUpdate();
          
        }finally {}
    }
    
    public String getMaxSuplierId()throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT MAX(supplier_id)FROM Supplier";
        
        try(Connection con = new DBConnection().getConnection()){
             stmt = con.createStatement();
            
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString("MAX(supplier_id)");
            }
        }finally {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
        }
        return null;
    }
    
    public List<SupplierDto>getAllSupplier()throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        List<SupplierDto>supplierList = new ArrayList<>();
        
        String sql = "SELECT*FROM supplier";
         try(Connection con = new DBConnection().getConnection()){
             stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
      
            while(rs.next()){
                SupplierDto supDto = new SupplierDto();
                
                supDto.setSupplierId(rs.getString("supplier_id"));
                supDto.setSuplName(rs.getString("sup_lname"));
                supDto.setSupfName(rs.getString("sup_fname"));
                supDto.setSupContact(rs.getString("sup_contact"));
                supDto.setSupAdd1(rs.getString("sup_add1"));
                supDto.setSupAdd2(rs.getString("sup_add2"));
                
                supplierList.add(supDto);
            } 
         }finally {
             if(stmt != null) stmt.close();
             if(rs != null) rs.close();
         }
       return  supplierList;
    }
    
    public SupplierDto getSupplierById(String id) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT*FROM Supplier WHERE supplier_id = ?";
        
         try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
             ps.setString(1, id);
             rs = ps.executeQuery();
            if(rs.next()){
                SupplierDto supDto = new SupplierDto();
                
                supDto.setSupplierId(rs.getString("supplier_id"));
                supDto.setSuplName(rs.getString("sup_lname"));
                supDto.setSupfName(rs.getString("sup_fname"));
                supDto.setSupContact(rs.getString("sup_contact"));
                supDto.setSupAdd1(rs.getString("sup_add1"));
                supDto.setSupAdd2(rs.getString("sup_add2"));
                
                return supDto;
            }   
         }finally {
             if(rs!= null) rs.close();
         }
        return null;
    }
    
    public int updateSupplierById(SupplierDto dto)throws SQLException{
        
        String sql = " UPDATE Supplier SET sup_lname = ?, "
                + "sup_fname = ?, sup_contact = ?,"
                + " sup_add1 = ?, sup_add2 = ? WHERE supplier_id = ? ";
        
         try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
             
             ps.setString(1, dto.getSuplName());
             ps.setString(2, dto.getSupfName());
             ps.setString(3, dto.getSupContact());
             ps.setString(4, dto.getSupAdd1());
             ps.setString(5, dto.getSupAdd2());
             ps.setString(6, dto.getSupplierId());
   
            int rowpdate = ps.executeUpdate();
            if(rowpdate==1){
                return  rowpdate;
            }
         }finally {} 
         return 0;
    }
}
