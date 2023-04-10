/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.jnelectrical.dto;

/**
 *
 * @author ishan
 */
public class BrandDto {
    
    private int brandId;
    private String brand_name;
    private int categoryId_fk1;
    private int subCategory_brand_id;
   

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getCategoryId_fk1() {
        return categoryId_fk1;
    }

    public void setCategoryId_fk1(int categoryId_fk1) {
        this.categoryId_fk1 = categoryId_fk1;
    }

    public int getSubCategory_brand_id() {
        return subCategory_brand_id;
    }

    public void setSubCategory_brand_id(int subCategory_brand_id) {
        this.subCategory_brand_id = subCategory_brand_id;
    }

    
    
}
