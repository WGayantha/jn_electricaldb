/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.dto.SupplierDto;
import lk.jnelectrical.repository.SupplierRepository;

/**
 *
 * @author ishan
 */
public class SupplierService {
    
   public SupplierRepository supplierRepository ;
    
    public int addSupplier(SupplierDto Dto) {
        supplierRepository = new SupplierRepository();
       try {
           return supplierRepository.addSupplier(Dto);
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
       return 0;
    }
    
    public List<SupplierDto>getAllSupplier(){
        supplierRepository = new SupplierRepository();
        
       try {
          return supplierRepository.getAllSupplier();
       } catch (SQLException ex) {
          ex.printStackTrace();
       }
       return null;
     }
    
    public SupplierDto getSupplierById(String id){
        supplierRepository = new SupplierRepository();
       try {
          return supplierRepository.getSupplierById(id);
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
       return null;
    }
    
     public int updateSupplierById(SupplierDto dto){
         supplierRepository = new SupplierRepository();
         
       try {
           return supplierRepository.updateSupplierById(dto);
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
       return 0;
     }
}
