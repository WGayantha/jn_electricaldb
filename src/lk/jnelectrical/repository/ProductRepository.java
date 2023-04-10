
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.BrandDto;
import lk.jnelectrical.dto.ProductDto;


public class ProductRepository {
 
    public String getMaxProductCode()throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(product_code) FROM product";
    
            try(Connection con = new DBConnection().getConnection()){
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
        
                if(rs.next()){
                    return rs.getString("MAX(product_code)");
                }
            }finally {
                if(stmt != null) stmt.close();
                if(rs != null) rs.close();
            }
        return null;
    }
    
    public int addProduct(ProductDto productDto)throws SQLException{
        PreparedStatement ps = null;
        String sql ; 
        ResultSet rs = null;
        int  sbRowAffected =0;
        int subcate_brand_id = 0;
        try(Connection con = new DBConnection().getConnection()){
           con.setAutoCommit(false);
         
            sql = "INSERT INTO subcate_brand "
                        + "(subCategory_id, brand_id) VALUES(?, ?)";
                ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, productDto.getSubCategoryId_fk());
                ps.setInt(2, productDto.getBrand().getBrandId());
                
            int rowAffected = ps.executeUpdate();
            
            if(rowAffected == 1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                  subcate_brand_id = rs.getInt(1);
                   
                }
                
                sql= "INSERT INTO Product(product_code, subCategory_id, "
                + " warranty, status, model_no, msrp, qtyInHand, damage_qty,"
                + " subcate_brand_id ) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
            
                ps.setString(1, productDto.getProductCode());
                ps.setInt(2, productDto.getSubCategoryId_fk());
                ps.setString(3, productDto.getWarranty());
                ps.setString(4, productDto.getStatus());          
                ps.setString(5, productDto.getModel_no());
                ps.setDouble(6, productDto.getMsrp());
                ps.setInt(7, productDto.getQuantity());
                ps.setInt(8, productDto.getDamage_qty());
                ps.setInt(9, subcate_brand_id);
                
                sbRowAffected = ps.executeUpdate();
              
                        if(sbRowAffected == 1){
                            con.commit();
                            
                        } else {
                            con.rollback();
                        }
            } else {
                con.rollback();
            }       
        }finally{
            if(ps!= null) ps.close();
            if(rs!= null) rs.close();
        } 
        return sbRowAffected;
    }
    
    public ProductDto getProductByProdCode(String code)throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT c.catogery_id, c.catogery_name , "
                + "sc.subcategory_name," 
                + "sc.subcategory_id, p.model_no, b.brand_name ," 
                + "p.product_code, p.warranty, p.status, p.msrp, p.qtyInHand, "
                + "p.damage_qty, p.subcate_brand_id from catogery c INNER JOIN " 
                + "subcategory sc on c.catogery_id = sc.catogery_id INNER JOIN " 
                + "subcate_brand sb on sc.subcategory_id = sb.subcategory_id " 
                + "INNER JOIN brand b on sb.brand_id = b.brand_id INNER JOIN product p on "
                + "sc.subcategory_id = p.subcategory_id AND "
                + "p.subcate_brand_id = sb.subcategory_brand_id " 
                + "WHERE p.product_code = ? ";
               
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, code);
            rs = ps.executeQuery();
            
                if(rs.next()){
                    ProductDto dto = new ProductDto();
                    BrandDto brand = new BrandDto();
                
                    brand.setBrand_name(rs.getString("b.brand_name"));
                    dto.setModel_no(rs.getString("p.model_no"));
                    dto.setProductCode(rs.getString("p.product_code"));
                    dto.setProduct_categoryName(rs.getString("c.catogery_name"));
                    dto.setProduct_subCategoryName(rs.getString("sc.subCategory_name"));
                    dto.setWarranty(rs.getString("p.warranty"));
                    dto.setStatus(rs.getString("p.status"));
                    dto.setMsrp(rs.getDouble("p.msrp"));
                    dto.setQuantity(rs.getInt("p.qtyInHand"));
                    dto.setDamage_qty(rs.getInt("p.damage_qty"));
                    dto.setSubCate_brand_id(rs.getInt("p.subcate_brand_id"));
                
                    dto.setBrand(brand);
                    return dto;    
            }
        }finally {
            if(rs!= null)  rs.close();
        }
        return null;
    }
    
    public int updateProductByProdId(ProductDto dto)throws SQLException{
       PreparedStatement ps = null;
       String sql ;
       int subCateBrandRowAffected = 0;
       sql =  "UPDATE subcate_brand SET "
                        + "subCategory_id = ?, brand_id = ?  "
                        + "WHERE subcategory_brand_id = ? " ;
        try(Connection con = new DBConnection().getConnection()){
                 ps = con.prepareStatement(sql);
             con.setAutoCommit(false);
                    
                    ps.setInt(1, dto.getSubCategoryId_fk());
                    ps.setInt(2, dto.getBrand().getBrandId());
                    ps.setInt(3, dto.getSubCate_brand_id());
                    
            int rowUpdate = ps.executeUpdate();
                if(rowUpdate ==1){
                    
        sql = "UPDATE Product SET warranty = ?, status = ?, "
                + "subcategory_id = ?, model_no = ?, msrp = ?, "
                + "qtyInHand = ?, damage_qty = ?, subcate_brand_id = ? "
                + " WHERE product_code = ? ";
                 ps = con.prepareStatement(sql);
          
                ps.setString(1, dto.getWarranty());
                ps.setString(2, dto.getStatus());
                ps.setInt(3, dto.getSubCategoryId_fk());
                ps.setString(4, dto.getModel_no());
                ps.setDouble(5, dto.getMsrp());
                ps.setInt(6, dto.getQuantity());
                ps.setInt(7, dto.getDamage_qty());
                ps.setInt(8, dto.getSubCate_brand_id());
                ps.setString(9, dto.getProductCode());
                
                    subCateBrandRowAffected = ps.executeUpdate();
                     if(subCateBrandRowAffected == 1){
                         con.commit();
                         
                     } else {
                         con.rollback();
                     }
                } else{
                con.rollback();
                }
    }finally {
             if(ps!= null) ps.close();
         }
         return subCateBrandRowAffected;
    }
    public List<ProductDto> getAllProduct()throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        List<ProductDto>productList = new ArrayList<>();
        String sql =  "SELECT c.catogery_id, c.catogery_name , "
                + "sc.subcategory_name," 
                + "sc.subcategory_id, p.model_no, b.brand_name ," 
                + "p.product_code, p.warranty, p.status, p.msrp, p.qtyInHand, "
                + "p.damage_qty, p.subcate_brand_id from catogery c INNER JOIN " 
                + "subcategory sc on c.catogery_id = sc.catogery_id INNER JOIN " 
                + "subcate_brand sb on sc.subcategory_id = sb.subcategory_id " 
                + "INNER JOIN brand b on sb.brand_id = b.brand_id INNER JOIN product p on "
                + "sc.subcategory_id = p.subcategory_id AND "
                + "p.subcate_brand_id = sb.subcategory_brand_id ORDER BY p.product_code" ;
                    
        try(Connection con = new DBConnection().getConnection()){
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ProductDto dto = new ProductDto();
                BrandDto brand = new BrandDto();
                
                brand.setBrand_name(rs.getString("b.brand_name"));
                    dto.setModel_no(rs.getString("p.model_no"));
                    dto.setProductCode(rs.getString("p.product_code"));
                    dto.setProduct_categoryName(rs.getString("c.catogery_name"));
                    dto.setProduct_subCategoryName(rs.getString("sc.subCategory_name"));
                    dto.setWarranty(rs.getString("p.warranty"));
                    dto.setStatus(rs.getString("p.status"));
                    dto.setMsrp(rs.getDouble("p.msrp"));
                    dto.setQuantity(rs.getInt("p.qtyInHand"));
                    dto.setDamage_qty(rs.getInt("p.damage_qty"));
                    dto.setSubCate_brand_id(rs.getInt("p.subcate_brand_id"));
                
                    dto.setBrand(brand);
                
                productList.add(dto);
            } 
        }finally {
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        }
        return productList;
    }
}
