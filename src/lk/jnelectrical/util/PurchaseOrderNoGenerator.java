
package lk.jnelectrical.util;

import java.sql.SQLException;
import lk.jnelectrical.repository.PurchaseOderRepository;

public class PurchaseOrderNoGenerator {
    private static PurchaseOderRepository purchaseOderRepository;
    
    private PurchaseOrderNoGenerator(){}
    
    public static String getNextPurchaseOrderNo(){
        String nextPurchaseOrderNo = null;
        purchaseOderRepository = new PurchaseOderRepository();
        try {
            String maxPurchaseOrderNo = purchaseOderRepository.getMaxPurhaseOrderNo();
            if(maxPurchaseOrderNo != null){
                int id = Integer.parseInt(maxPurchaseOrderNo
                        .substring(3, maxPurchaseOrderNo.length()));
                id++;
                nextPurchaseOrderNo = "PO-" + String.format("%05d", id);
            } else {
                nextPurchaseOrderNo = "PO-00001";
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nextPurchaseOrderNo;
    }  
}
