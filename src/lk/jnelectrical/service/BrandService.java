
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.jnelectrical.dto.BrandDto;
import lk.jnelectrical.repository.BrandRepository;



public class BrandService {
    BrandRepository brandRepository;
    
    public int addBrand(BrandDto dto){
        brandRepository = new BrandRepository();
        try {
          return brandRepository.addBrand(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
      public List<BrandDto> getAllBrand(){
         brandRepository = new BrandRepository();
        try {
            return brandRepository.getAllBrand();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    } 
    
    public int updateCategerybyId(BrandDto brandDto){
         brandRepository = new BrandRepository();
        try {
            return brandRepository.updateBrnadById(brandDto);
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  0;
    }
    
    public BrandDto getBrandByBrandId(int id) {
        brandRepository = new BrandRepository();
        try {
           return brandRepository.getBrandByBrandId(id);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  null;
    }
    
     public int deleteBrandById(int id){
         brandRepository = new BrandRepository();
        try {
            return brandRepository.deleteBrandById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
     }  
     
}
