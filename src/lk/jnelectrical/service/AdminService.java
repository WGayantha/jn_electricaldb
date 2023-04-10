
package lk.jnelectrical.service;

import java.sql.SQLException;

import lk.jnelectrical.dto.AdminDto;
import lk.jnelectrical.repository.AdminRepository;


public class AdminService {
    
    AdminRepository adminRepository ;
     
    public AdminDto getLogingdetail(String userName, String password){
        adminRepository = new AdminRepository();
        try {
           return adminRepository.getLogingdetail(userName, password);
        } catch (SQLException ex) {
           ex.printStackTrace();
        } 
        return null;
    }
}
