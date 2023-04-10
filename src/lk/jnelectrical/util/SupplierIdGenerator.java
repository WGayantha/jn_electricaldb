/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.jnelectrical.util;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.repository.SupplierRepository;

/**
 *
 * @author ishan
 */
public class SupplierIdGenerator {
    private static SupplierRepository supplierRepository;
    private SupplierIdGenerator() {};
    
    public static String getNextSupplierId(){
        String nextSupplierId = null;
        supplierRepository = new SupplierRepository();
        try {
            String maxSuplierId = supplierRepository.getMaxSuplierId();
            if(maxSuplierId != null){
               int id = Integer.parseInt(maxSuplierId.substring(3,maxSuplierId.length()));
            id++;  
            nextSupplierId = "SUP" + String.format("%05d", id);
            } else {
                nextSupplierId = "SUP00001";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     return nextSupplierId;
    }
    
}
