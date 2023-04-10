
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import lk.jnelectrical.dto.BrandDto;
import lk.jnelectrical.dto.SubCategoryDto;
import lk.jnelectrical.repository.BrandRepository;
import lk.jnelectrical.repository.SubCategoryRepository;

/**
 *
 * @author ishan
 */
public class SubCategoryService {
    SubCategoryRepository subCategoryRepository ;
    
    public int addSubCategory(SubCategoryDto subCateDto){
        subCategoryRepository = new SubCategoryRepository();
        try {
          return subCategoryRepository.addSubCategory(subCateDto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
      public List<SubCategoryDto> getAllSubCategoryById(int id){
         subCategoryRepository = new SubCategoryRepository();
        try {
            return subCategoryRepository.getAllSubCategoryById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    } 
    
   public int updateSubCategerybyId(SubCategoryDto dto){
         subCategoryRepository = new SubCategoryRepository();
        try {
            return subCategoryRepository.updateSubCategerybyId(dto);  
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  0;
    }
    
    public SubCategoryDto getSubCategoryById(int id) {
        subCategoryRepository = new SubCategoryRepository();
        try {
           return subCategoryRepository.getSubCategoryById(id);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  null;
    }
    
     public int deleteSubCategoryById(int id){
         subCategoryRepository = new SubCategoryRepository();
        try {
            return subCategoryRepository.deleteSubCategoryById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
     }      
}
