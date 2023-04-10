
package lk.jnelectrical.dto;

import java.util.List;

public class PurchaseOrderDto {
    
    private String pOrderNo;
    private String supplierIdfk;
    private List<POrderDetailDto>POrderList;

    public String getpOrderNo() {
        return pOrderNo;
    }

    public void setpOrderNo(String pOrderNo) {
        this.pOrderNo = pOrderNo;
    }

    public String getSupplierIdfk() {
        return supplierIdfk;
    }

    public void setSupplierIdfk(String supplierIdfk) {
        this.supplierIdfk = supplierIdfk;
    }

    public List<POrderDetailDto> getPOrderList() {
        return POrderList;
    }

    public void setPOrderList(List<POrderDetailDto> POrderList) {
        this.POrderList = POrderList;
    }
       
}
