
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import lk.jnelectrical.dto.CategoryDto;
import lk.jnelectrical.repository.CategoryRepository;


public class CategoryService {
    public CategoryRepository categoryRepository;
    
    public int addCategory (CategoryDto categoryDto){
        categoryRepository = new CategoryRepository();
        try {
            return categoryRepository.addCategory(categoryDto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    } 
    
    public List<CategoryDto> getAllCategory(){
        categoryRepository = new CategoryRepository();
        try {
            return categoryRepository.getAllCategory();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    } 
    
    public int updateCategerybyId(CategoryDto categoryDto){
        categoryRepository = new CategoryRepository();
        try {
            return categoryRepository.updateCategerybyId(categoryDto);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  0;
    }
    
    public CategoryDto getCategoryByCateId(int id) {
        categoryRepository = new CategoryRepository();
        try {
           return categoryRepository.getCategoryByCateId(id);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  null;
    }
    
     public int deleteCategoryById(int id){
         categoryRepository = new CategoryRepository();
        try {
            return categoryRepository.deleteCategoryById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
     }
}
