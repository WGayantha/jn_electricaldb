
package lk.jnelectrical.service;

import java.sql.SQLException;
import lk.jnelectrical.dto.POrderDetailDto;
import lk.jnelectrical.repository.POrderDetailRepository;


public class POrderDetailService {
    POrderDetailRepository pOrderDetailRepository ;
    
    /*public int addProdOrderDetail(POrderDetailDto dto1){
        pOrderDetailRepository = new POrderDetailRepository();
        
        try {
           return pOrderDetailRepository.addProdOrderDetail(dto1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return 0; 
    }  */
}
