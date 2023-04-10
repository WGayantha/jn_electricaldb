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
public class ModelNumDto {
    
    private int modelId;
    private String modelNo;
    private int Model_subcategoryId;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public int getModel_subcategoryId() {
        return Model_subcategoryId;
    }

    public void setModel_subcategoryId(int Model_subcategoryId) {
        this.Model_subcategoryId = Model_subcategoryId;
    }
    
    
}
