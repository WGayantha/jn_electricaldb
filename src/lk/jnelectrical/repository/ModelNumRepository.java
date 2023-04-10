
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;

import lk.jnelectrical.dto.ModelNumDto;


public class ModelNumRepository {
    
   public int addModel(ModelNumDto dto )throws SQLException {
        String sql = "INSERT INTO modelnum  model_id,"
                + " model_no, subCategory_id VALUES (?, ?,) ";
        
        try(Connection con = new DBConnection().getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, dto.getModelNo());
            ps.setInt(3, dto.getModel_subcategoryId());
            
            return ps.executeUpdate();
        } finally{}
    } 
   
    public List<ModelNumDto>getAllModel()throws SQLException{
        Statement st = null;
        ResultSet rs = null;
        List<ModelNumDto>modelList = new ArrayList<>();
        String sql = "SELECT*FROM modelnum ";
        
        try(Connection con = new DBConnection().getConnection()){
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                ModelNumDto modelDto = new ModelNumDto();
              
                modelDto.setModelId(rs.getInt("model_id"));
                modelDto.setModelNo(rs.getString("model_no"));
                modelDto.setModel_subcategoryId(rs.getInt("subCategory_id"));
                
                modelList.add(modelDto);
            }   
        }finally {
                    if(st != null)  st.close();
                    if(rs != null) rs.close();
                    }
        return modelList;
    }
}
