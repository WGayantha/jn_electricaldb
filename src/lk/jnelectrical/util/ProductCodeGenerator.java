/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.jnelectrical.util;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.repository.ProductRepository;

/**
 *
 * @author ishan
 */
public class ProductCodeGenerator {
    
   private static ProductRepository productRepository;
   
   private ProductCodeGenerator(){}
   
   public static String getNextProductCode(){
       String nextProductCode = null;
       productRepository = new ProductRepository();
       
       try {
           String maxProductCode = productRepository.getMaxProductCode();
           if(maxProductCode != null){
              int id = Integer.parseInt(maxProductCode
                      .substring(2, maxProductCode.length()));
              id++;
              nextProductCode = "P"+String.format("%05d", id);
           } else {
               nextProductCode = "P00001";
           }
       } catch (SQLException ex) {
          ex.printStackTrace();
       }
    return nextProductCode;
   }
}
