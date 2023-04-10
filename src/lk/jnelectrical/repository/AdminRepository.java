
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.AdminDto;

public class AdminRepository {
    
    
    public AdminDto getLogingdetail(String userName, String password)throws SQLException{
         PreparedStatement ps = null;
         ResultSet rs  = null;
        String sql = "SELECT*FROM admin WHERE username = ? OR passwordDb = ?";
        try(Connection con = new DBConnection().getConnection()){
         ps = con.prepareStatement(sql);
         ps.setString(1,userName);
         ps.setString(2, password);
         rs = ps.executeQuery();
         
         if(rs.next()){
             AdminDto dto = new AdminDto();
             dto.setUserName(rs.getString("username"));
             dto.setPassword(rs.getString("passwordDb"));
            
             return dto;
         }
           
        }finally{
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
       return null;
    }  
}
