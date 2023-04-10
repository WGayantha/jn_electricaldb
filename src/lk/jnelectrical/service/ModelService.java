
package lk.jnelectrical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lk.jnelectrical.dto.ModelNumDto;
import lk.jnelectrical.repository.ModelNumRepository;


public class ModelService {
        ModelNumRepository modelNumRepository ;
    public int addModel(ModelNumDto dto ){
       modelNumRepository  =new ModelNumRepository();
            try {
              return modelNumRepository.addModel(dto);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return  0;
    }
    public List<ModelNumDto>getAllModel(){
        modelNumRepository = new ModelNumRepository();
            try {
                return modelNumRepository.getAllModel();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
    }
}
