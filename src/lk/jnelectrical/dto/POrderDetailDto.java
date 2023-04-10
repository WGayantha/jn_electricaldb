
package lk.jnelectrical.dto;

import java.util.List;


public class POrderDetailDto {
    
     private int pod_id ;
     private int buying_qty;
     private double buying_price;
     private String buying_date;
     private String pOrderNo;
     private String prodCode;
     
    public int getPod_id(){
         return pod_id;
    }
     
    public void setPod_id(int pod_id){
         this.pod_id = pod_id;
    }
     
    public int getBuying_qty() {
        return buying_qty;
    }

    public void setBuying_qty(int buying_qty) {
        this.buying_qty = buying_qty;
    }

    public double getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(double buying_price) {
        this.buying_price = buying_price;
    }

    public String getBuying_date() {
        return buying_date;
    }

    public void setBuying_date(String buying_date) {
        this.buying_date = buying_date;
    }

    public String getpOrderNo() {
        return pOrderNo;
    }

    public void setpOrderNo(String pOrderNo) {
        this.pOrderNo = pOrderNo;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    
    
}
