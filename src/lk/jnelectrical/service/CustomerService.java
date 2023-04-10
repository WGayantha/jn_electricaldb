
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.dto.CustomerDto;
import lk.jnelectrical.repository.CustomerRepository;


public class CustomerService {
    public CustomerRepository customerRepository;
    
    public int addCustomer(CustomerDto dto) {
        customerRepository = new CustomerRepository();
        try {
            return customerRepository.addCustomer(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0 ;
    } 
    
     public List<CustomerDto>getAllCustomer(){
        customerRepository = new CustomerRepository(); 
        try {
            return customerRepository.getAllCustomer();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
         return null;
     }
     
      public CustomerDto getCustomerById(String id){
          customerRepository = new CustomerRepository();
        try {
           return customerRepository.getCustomerById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
      }
      
      public int updateCustomerById(CustomerDto dto) {
          customerRepository =new CustomerRepository();
        try {
           return customerRepository.updateCustomerById(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
      }
}
