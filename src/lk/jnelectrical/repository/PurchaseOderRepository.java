
package lk.jnelectrical.repository;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import lk.jnelectrical.db.DBConnection;
import lk.jnelectrical.dto.POrderDetailDto;
import lk.jnelectrical.dto.PurchaseOrderDto;

public class PurchaseOderRepository {
    
    public String getMaxPurhaseOrderNo()throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(purchaseOrder_no)FROM purchase_order";
       
        try(Connection con = new DBConnection().getConnection()){
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString("MAX(purchaseOrder_no)");
            }
        }finally{
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        }
        return null;
    }
    
    public int addPurchaseOrder(PurchaseOrderDto dto) throws SQLException{
       PreparedStatement ps = null;
        String sql;
        int batchAffected =0;
        sql = "INSERT INTO purchase_order (purchaseOrder_no, "
                + "supplier_id) VALUES(?, ?)";  
        
        try(Connection con = new DBConnection().getConnection()){
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
             
            ps.setString(1,dto.getpOrderNo());
            ps.setString(2,dto.getSupplierIdfk());
             
           int rowAffected = ps.executeUpdate();
            
            if(rowAffected == 1){
                
                sql = " INSERT INTO purchasingorder_detail (purchaseOrder_no, "
                        + "product_code, buying_qty, buying_price, "
                        + "buying_date)VALUES (?, ?, ?, ?, ?)";
                   ps = con.prepareStatement(sql);
                   
                   for(POrderDetailDto pOrderDetailDto : dto.getPOrderList()) {
                       
                        ps.setString(1, pOrderDetailDto.getpOrderNo());
                        ps.setString(2, pOrderDetailDto.getProdCode());
                        ps.setInt(3, pOrderDetailDto.getBuying_qty());
                        ps.setDouble(4, pOrderDetailDto.getBuying_price());
                        ps.setDate(5, Date.valueOf(pOrderDetailDto.getBuying_date()));
                        
                        ps.addBatch();
                   }
                    int [] executeBatch = ps.executeBatch();
                    
                    if(executeBatch.length >= 1) {
                        sql = "UPDATE product SET "
                           + "qtyInHand = qtyInHand + ? WHERE product_code = ?";
                        ps = con.prepareStatement(sql);
                        
                         for(POrderDetailDto pOrderDetailDto : dto.getPOrderList()) {
                            
                             ps.setInt(1, pOrderDetailDto.getBuying_qty());
                             ps.setString(2, pOrderDetailDto.getProdCode());
                             
                             ps.addBatch();
                         }
                            int[] executeBatchQty = ps.executeBatch();
                          
                            if(executeBatchQty.length >= 1){
                                 sql = "INSERT INTO batch "
                                         + "(batch_id, product_code, qty, supplier_id, purchaseOrder_no ) "
                                         + "VALUES (?, ?, ?, ?, ?)";
                                ps = con.prepareStatement(sql);
                                
                                for(POrderDetailDto pOrderDetailDto : dto.getPOrderList()){
                                  ps.setString(1, pOrderDetailDto.getProdCode()
                                          +"-"+ Date.valueOf(pOrderDetailDto
                                                  .getBuying_date()));  
                                  ps.setString(2, pOrderDetailDto.getProdCode());
                                  ps.setInt(3, pOrderDetailDto.getBuying_qty());
                                  ps.setString(4, dto.getSupplierIdfk());
                                  ps.setString(5, dto.getpOrderNo());
                                  
                                  ps.addBatch();
                                }
                                int[] executeBatch1 = ps.executeBatch();
                                batchAffected = executeBatchQty.length;
                                if(executeBatch1.length>=1){
                                    con.commit();
                                    
                                } else {
                                    con.rollback();
                                }
                                
                            } else {
                                con.rollback();
                            }
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
    }  
    
    
    
    
    
    
    
  /* public PurchaseOrderDto getPurchaseOderByPON(String no)throws SQLException{
       ResultSet rs = null;
       String sql = "SELECT*FROM purchase_order WHERE purchaseOrder_no = ?";
       
       try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
           ps.setString(1,no);
           rs = ps.executeQuery();
           
           if(rs.next()){
               PurchaseOrderDto dto = new PurchaseOrderDto();
               dto.setpOrderNo(rs.getString("purchaseOrder_no"));
               dto.setSupplierIdfk(rs.getString("supplier_id"));
              
               return dto;
           }
       }finally{
           if(rs != null)  rs.close();
       }
       return  null;
   }
   
   public int updatePurchaseOrderByPON(PurchaseOrderDto dto) throws SQLException {
       
       String sql = "UPDATE purchase_order SET supplier_id = ?"
               + " WHERE purchaseOrder_no = ?";
       
        try(Connection con = new DBConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
          
            ps.setString(1,dto.getSupplierIdfk());
            ps.setString(8, dto.getpOrderNo());
            
           int rowUpdate = ps.executeUpdate();
            if(rowUpdate == 1){
                return rowUpdate;
            }
        }finally {}
       return 0;
   }
   public List<PurchaseOrderDto> getAllPurchaseOrder()throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        List<PurchaseOrderDto>purchasingOrderList = new ArrayList<>();
        String sql = "SELECT * FROM purchase_order ORDER BY purchaseOrder_no ";
        
        try(Connection con = new DBConnection().getConnection()){
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
       
           while(rs.next()){
               PurchaseOrderDto dto = new PurchaseOrderDto();
               
               dto.setpOrderNo(rs.getString("purchaseOrder_no"));
               dto.setSupplierIdfk(rs.getString("supplier_id"));
               
             purchasingOrderList.add(dto);
           }     
       } finally {
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        }
       return  purchasingOrderList;
   } */
}
