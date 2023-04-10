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
import lk.jnelectrical.dto.ProductDto;
import lk.jnelectrical.repository.ProductRepository;

/**
 *
 * @author ishan
 */
public class ProductService {
    public ProductRepository productRepository;
    
    public int addProduct(ProductDto productDto) {
        productRepository = new ProductRepository();
        try {
           return productRepository.addProduct(productDto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     return 0;
    }
    public ProductDto getProductByProdCode(String code){
        productRepository = new ProductRepository();
        try {
           return productRepository.getProductByProdCode(code);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    }
    public int updateProductByProdId(ProductDto dto){
        productRepository = new ProductRepository();
        try {
            return productRepository.updateProductByProdId(dto);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return 0;
    }
    
    public List<ProductDto> getAllProduct(){
        productRepository = new ProductRepository();
        try {
           return productRepository.getAllProduct();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  null;
    }
}
