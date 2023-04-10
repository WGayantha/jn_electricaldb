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
public class CustomerDto {
    
    
   private String customerId;
   private String cusNic;
   private String cusFname;
   private String cusLname;
   private String cusAddLine1;
   private String cusAddLine2;
   private String cusContact;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCusNic() {
        return cusNic;
    }

    public void setCusNic(String cusNic) {
        this.cusNic = cusNic;
    }

    public String getCusFname() {
        return cusFname;
    }

    public void setCusFname(String cusFname) {
        this.cusFname = cusFname;
    }

    public String getCusLname() {
        return cusLname;
    }

    public void setCusLname(String cusLname) {
        this.cusLname = cusLname;
    }

    public String getCusAddLine1() {
        return cusAddLine1;
    }

    public void setCusAddLine1(String cusAddLine1) {
        this.cusAddLine1 = cusAddLine1;
    }

    public String getCusAddLine2() {
        return cusAddLine2;
    }

    public void setCusAddLine2(String cusAddLine2) {
        this.cusAddLine2 = cusAddLine2;
    }

    public String getCusContact() {
        return cusContact;
    }

    public void setCusContact(String cusContact) {
        this.cusContact = cusContact;
    }
    
}
