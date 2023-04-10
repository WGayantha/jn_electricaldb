
package lk.jnelectrical.repository;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.CustomerDto;


public class CustomerRepository {

    public String getMaxCustomerId() throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(cus_id) FROM Customer";
        
        try(Connection con = new DBConnection().getConnection()){
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.next()){
               return rs.getString("MAX(cus_id)");
            }
        }finally {
            if(stmt!= null) stmt.close();
            if(rs!= null) rs.close();
        }
        return  null;
    }
    
    public int addCustomer(CustomerDto dto)throws SQLException{
        String sql = "INSERT INTO Customer(cus_id, "
                + "cus_nic, cus_lname, cus_fname, "
                + "addLine_1, addLine_2, cus_contact)VALUES(?, ?, ?, ?, ?, ?, ? )";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setString(1, dto.getCustomerId());
            pstmt.setString(2, dto.getCusNic());
            pstmt.setString(3, dto.getCusLname());
            pstmt.setString(4, dto.getCusFname());
            pstmt.setString(5, dto.getCusAddLine1());
            pstmt.setString(6, dto.getCusAddLine2());
            pstmt.setString(7, dto.getCusContact());
            
            return pstmt.executeUpdate();
  
        }finally {}
    } 
    
    public List<CustomerDto>getAllCustomer()throws SQLException {
       ResultSet rs = null;
       List<CustomerDto> cusList = new ArrayList<>();
       
       String sql = "SELECT*FROM Customer";
       
       try(Connection con = new DBConnection().getConnection();
           Statement ps = con.createStatement()){
           
           rs = ps.executeQuery(sql);
           
           while(rs.next()){
              CustomerDto dto = new CustomerDto();
               
              dto.setCustomerId(rs.getString("cus_id"));
              dto.setCusNic(rs.getString("cus_nic"));
              dto.setCusFname(rs.getString("cus_fname"));
              dto.setCusLname(rs.getString("cus_lname"));
              dto.setCusAddLine1(rs.getString("addLine_1"));
              dto.setCusAddLine2(rs.getString("addLine_2"));
              dto.setCusContact(rs.getString("cus_contact"));
             
              cusList.add(dto);
           }
       }finally {
           if(rs != null) rs.close();
       }
       return cusList;
    }
    
    public CustomerDto getCustomerById(String id)throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT*FROM Customer WHERE cus_id = ?";
        
        try(Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, id); 
            rs = ps.executeQuery();
           
            if(rs.next()){
                CustomerDto dto = new CustomerDto();
                
                dto.setCusNic(rs.getString("cus_nic"));
                dto.setCusFname(rs.getString("cus_fname"));
                dto.setCusLname(rs.getString("cus_lname"));
                dto.setCusAddLine1(rs.getString("addLine_1"));
                dto.setCusAddLine2(rs.getString("addLine_2"));
                dto.setCusContact(rs.getString("cus_contact"));
            
                return dto;
            }
        }finally{
            if(rs!= null) rs.close();
        }
       return null;
    }
    
    public int updateCustomerById(CustomerDto dto) throws SQLException{
        String sql = "UPDATE Customer SET cus_nic = ?, cus_lname = ?, "
                + "cus_fname = ?, addLine_1 = ?, addLine_2 = ?,"
                + " cus_contact = ? WHERE  cus_id = ? ";
     
        try(Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
                ps.setString(1, dto.getCusNic());
                ps.setString(2, dto.getCusLname());
                ps.setString(3, dto.getCusFname());
                ps.setString(4, dto.getCusAddLine1());
                ps.setString(5, dto.getCusAddLine2());
                ps.setString(6, dto.getCusContact());
                ps.setString(7, dto.getCustomerId());
                
            int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return rowUpdate;
            }
        }finally{}
        return  0;
    }
}

   
    