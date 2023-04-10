/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.jnelectrical.util;

import java.sql.SQLException;

import lk.jnelectrical.repository.CustomerRepository;

/**
 *
 * @author ishan
 */
public class CustomerIdGenerator {
    private static CustomerRepository customerRepository;
   
    private CustomerIdGenerator(){}
    
    public static String getNextCustomerId() {
        String nextCustomerId = null;
        customerRepository = new CustomerRepository();
        try{
            String maxCustomerId = customerRepository.getMaxCustomerId();
                if(maxCustomerId != null){
                    int id = Integer.parseInt(maxCustomerId
                        .substring(2,maxCustomerId.length()));
                    id++;
                    
                    nextCustomerId = "C-" + String.format("%05d", id);
                } else {
                    nextCustomerId = "C-00001";
                }
        }catch(SQLException ex){
            ex.printStackTrace();    
        }
        return nextCustomerId;
    }
}
