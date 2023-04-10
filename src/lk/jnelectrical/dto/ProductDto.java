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
public class ProductDto {
    
    private String productCode;
    private int subCategoryId_fk;
    private String warranty;
    private String status;
    private String product_categoryName;
    private int product_categoryId_fk;
    private String Product_subCategoryName;
    private BrandDto brand;
    private double msrp;
    private int quantity;
    private int damage_qty;
    private String model_no;
    private int subCate_brand_id;
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_categoryName() {
        return product_categoryName;
    }

    public void setProduct_categoryName(String product_categoryName) {
        this.product_categoryName = product_categoryName;
    }

    public int getSubCategoryId_fk() {
        return subCategoryId_fk;
    }

    public void setSubCategoryId_fk(int subCategoryId_fk) {
        this.subCategoryId_fk = subCategoryId_fk;
    }

    public String getProduct_subCategoryName() {
        return Product_subCategoryName;
    }

    public void setProduct_subCategoryName(String Product_subCategoryName) {
        this.Product_subCategoryName = Product_subCategoryName;
    }

    public int getProduct_categoryId_fk() {
        return product_categoryId_fk;
    }

    public void setProduct_categoryId_fk(int product_categoryId_fk) {
        this.product_categoryId_fk = product_categoryId_fk;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDamage_qty() {
        return damage_qty;
    }

    public void setDamage_qty(int damage_qty) {
        this.damage_qty = damage_qty;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public int getSubCate_brand_id() {
        return subCate_brand_id;
    }

    public void setSubCate_brand_id(int subCate_brand_id) {
        this.subCate_brand_id = subCate_brand_id;
    }
   
  
}
