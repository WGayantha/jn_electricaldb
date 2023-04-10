
package lk.jnelectrical.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.POrderDetailDto;

public class POrderDetailRepository {
    
   /* public int addProdOrderDetail(POrderDetailDto dto1) throws SQLException{
       PreparedStatement ps = null;
        String sql;
        int batchAffected =0;
        sql = "INSERT INTO purchase_order (purchaseOrder_no, "
                + "supplier_id) VALUES(?, ?)";  
        
        try(Connection con = new DBConnection().getConnection()){
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
             
            ps.setString(1,dto1.getPurchaseOrderDto().getpOrderNo());
            ps.setString(2,dto1.getPurchaseOrderDto().getSupplierIdfk());
             
           int rowAffected = ps.executeUpdate();
            
            if(rowAffected == 1){
                
                sql = " INSERT INTO purchasingorder_detail (purchaseOrder_no, "
                        + "product_code, buying_qty, buying_price, "
                        + "buying_date)VALUES (?, ?, ?, ?, ?)";
                   ps = con.prepareStatement(sql);
                   
                   for(POrderDetailDto dto :  dto1.getPOrderList()) {
                       
                        ps.setString(1, dto.getPurchaseOrderDto().getpOrderNo());
                        ps.setString(2, dto.getProductDto().getProductCode());
                        ps.setInt(3, dto.getBuying_qty());
                        ps.setDouble(4, dto.getBuying_price());
                        ps.setDate(5, Date.valueOf(dto.getBuying_date()));
                        
                        ps.addBatch();
                   }
                    int [] executeBatch = ps.executeBatch();
                    batchAffected = executeBatch.length;
                    if(executeBatch.length >= 1) {
                        con.commit();
                        
                    } else {
                        con.rollback();
                    }
                
            } else {
                con.rollback();
            }
           
        }finally{
            if(ps != null) ps.close();
        }
       return batchAffected; 
    }  */
}
