
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.dto.PurchaseOrderDto;
import lk.jnelectrical.repository.PurchaseOderRepository;

/**
 *
 * @author ishan
 */
public class PurchaseOrderService {
    public PurchaseOderRepository purchaseOderRepository;
    
    public int addPurchaseOrder(PurchaseOrderDto dto){
        purchaseOderRepository = new PurchaseOderRepository();
        try {
            return purchaseOderRepository.addPurchaseOrder(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    } 
    
  /* public PurchaseOrderDto getPurchaseOderByPON(String no){
        purchaseOderRepository = new PurchaseOderRepository();
        try {
           return  purchaseOderRepository.getPurchaseOderByPON(no);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
   }
   
    public int updatePurchaseOrderByPON(PurchaseOrderDto dto){
        purchaseOderRepository  = new PurchaseOderRepository();
        try {
           return purchaseOderRepository.updatePurchaseOrderByPON(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return 0;
    }
    
    public List<PurchaseOrderDto> getAllPurchaseOrder(){
        purchaseOderRepository = new PurchaseOderRepository();
        try {
           return purchaseOderRepository.getAllPurchaseOrder();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  null;
    }*/
    
}
