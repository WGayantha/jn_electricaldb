
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.BrandDto;


public class BrandRepository {
    
     public int addBrand(BrandDto dto) throws SQLException {
        String sql = "INSERT INTO "
                + "Brand(brand_name)VALUES(?)";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
              ps.setString(1, dto.getBrand_name());
    
             return ps.executeUpdate();
        } finally{}
    }

    public List<BrandDto> getAllBrand() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<BrandDto>brandList = new ArrayList<>();
        
        String sql = "SELECT*FROM Brand ORDER BY brand_id ASC";
        
        try(Connection con = new DBConnection().getConnection()){
            st = con.createStatement();
            rs = st.executeQuery(sql);
           while(rs.next()){
             BrandDto brandDto = new BrandDto();
               
                brandDto.setBrandId(rs.getInt("brand_id"));
                brandDto.setBrand_name(rs.getString("brand_name"));
       
               brandList.add(brandDto);
           } 
        }finally {
            if(rs!= null) rs.close();
            if(st != null) st.close();
        }
       return  brandList;
    }
 
    public int updateBrnadById(BrandDto brandDto)throws SQLException{
        String sql = "UPDATE Brand SET brand_name = ? WHERE brand_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, brandDto.getBrand_name());
            ps.setInt(2, brandDto.getBrandId());
            
            int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return rowUpdate;
            }
        }finally{}
        return 0;
    }
    
    public BrandDto getBrandByBrandId(int id) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT* FROM Brand WHERE brand_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                BrandDto dto = new BrandDto();
                
                dto.setBrandId(rs.getInt("brand_id"));
                dto.setBrand_name(rs.getString("brand_name"));
                
             return dto;
            }
            
        } finally {
            if(rs!= null) rs.close();
        }
        return null;
    }
    
    public int deleteBrandById(int id)throws SQLException{
        
        String sql = " DELETE FROM Brand WHERE brand_id = ? ";
        
        try(Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            return ps.executeUpdate();
        }finally{}
    }  
    
    
}
