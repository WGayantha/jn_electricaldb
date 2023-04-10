
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.SubCategoryDto;


public class SubCategoryRepository {
   
    public int addSubCategory (SubCategoryDto subCateDto) throws SQLException {
        String sql = "INSERT INTO "
                + "Subcategory(subCategory_name, catogery_id)VALUES(?, ?)";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
              ps.setString(1, subCateDto.getName());
              ps.setInt(2,subCateDto.getCategoryId());
    
             return ps.executeUpdate();
        } finally{}
    }

    public List<SubCategoryDto> getAllSubCategoryById(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SubCategoryDto>subcategoryList = new ArrayList<>();
        
        String sql = "SELECT*FROM Subcategory WHERE catogery_id = ?";
        
        try(Connection con = new DBConnection().getConnection()){
             ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            rs = ps.executeQuery();
            
           while(rs.next()){
               SubCategoryDto subCateDto = new SubCategoryDto();
               
               subCateDto.setSubCategoryId(rs.getInt("subCategory_id"));
               subCateDto.setName(rs.getString("subCategory_name"));
               subCateDto.setCategoryId(rs.getInt("catogery_id"));
       
               subcategoryList.add(subCateDto);
           } 
        }finally {
            if(rs!= null) rs.close();
            if(ps!= null) ps.close();
        }
       return  subcategoryList;
    }
 
    public int updateSubCategerybyId(SubCategoryDto dto)throws SQLException{
        String sql = "UPDATE Subcategory "
                + "SET subCategory_name = ?, "
                + "catogery_id = ? WHERE subCategory_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1,dto.getName());
            ps.setInt(2, dto.getCategoryId());
            ps.setInt(3, dto.getSubCategoryId());
            
            int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return rowUpdate;
            }
        }finally{}
        return 0;
    }
    
    public SubCategoryDto getSubCategoryById(int id) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT* FROM Subcategory WHERE subCategory_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
               SubCategoryDto dto = new SubCategoryDto();
                
                dto.setCategoryId(rs.getInt("catogery_id"));
                dto.setName(rs.getString("subCategory_name"));
                dto.setSubCategoryId(rs.getInt("subCategory_id"));
                
             return dto;
            }
            
        } finally {
            if(rs!= null) rs.close();
        }
        return null;
    }
    
    public int deleteSubCategoryById(int id)throws SQLException{
        
        String sql = " DELETE FROM Subcategory WHERE subCategory_id  = ? ";
        
        try(Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate();
        }finally{}
    }
    
    
}
