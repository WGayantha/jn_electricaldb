
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;

import lk.jnelectrical.dto.CategoryDto;


public class CategoryRepository {

    public int addCategory (CategoryDto categoryDto) throws SQLException {
        String sql = "INSERT INTO "
                + "catogery(catogery_name)VALUES(?)";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
              ps.setString(1, categoryDto.getCategoryName());
    
             return ps.executeUpdate();
        } finally{}
    }

    public List<CategoryDto> getAllCategory() throws SQLException {
        ResultSet rs = null;
        List<CategoryDto>categoryList = new ArrayList<>();
        
        String sql = "SELECT*FROM catogery";
        
        try(Connection con = new DBConnection().getConnection();
            Statement ps = con.createStatement()){
            rs = ps.executeQuery(sql);
           while(rs.next()){
               CategoryDto categoryDto = new CategoryDto();
               
               categoryDto.setCategoryID(rs.getInt("catogery_id"));
               categoryDto.setCategoryName(rs.getString("catogery_name"));
       
               categoryList.add(categoryDto);
           } 
        }finally {
            if(rs!= null) rs.close();
        }
       return  categoryList;
    }
 
    public int updateCategerybyId(CategoryDto categoryDto)throws SQLException{
        String sql = "UPDATE catogery "
                + "SET  catogery_name = ? WHERE catogery_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, categoryDto.getCategoryName());
            ps.setInt(2,categoryDto.getCategoryID());
            
            int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return rowUpdate;
            }
        }finally{}
        return 0;
    }
    
    public CategoryDto getCategoryByCateId(int id) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT* FROM catogery WHERE catogery_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                CategoryDto dto = new CategoryDto();
                
                dto.setCategoryID(rs.getInt("catogery_id"));
                dto.setCategoryName(rs.getString("catogery_name"));
                
             return dto;
            }
            
        } finally {
            if(rs!= null) rs.close();
        }
        return null;
    }
    
    public int deleteCategoryById(int id)throws SQLException{
        
        String sql = " DELETE FROM catogery WHERE catogery_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate();
        }finally{}
    }
}
