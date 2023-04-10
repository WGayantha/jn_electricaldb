
package lk.jnelectrical.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lk.jnelectrical.service.BrandService;
import lk.jnelectrical.service.PurchaseOrderService;

import lk.jnelectrical.service.CategoryService;
import lk.jnelectrical.service.CustomerService;
import lk.jnelectrical.service.ModelService;
import lk.jnelectrical.service.POrderDetailService;
import lk.jnelectrical.service.ProductService;
import lk.jnelectrical.service.SubCategoryService;
import lk.jnelectrical.service.SupplierService;

import lk.jnelectrical.util.PurchaseOrderNoGenerator;
import lk.jnelectrical.util.CustomerIdGenerator;
import lk.jnelectrical.util.DateFormatter;
import lk.jnelectrical.util.ProductCodeGenerator;
import lk.jnelectrical.util.SupplierIdGenerator;
import lk.jnelectrical.dto.BrandDto;
import lk.jnelectrical.dto.PurchaseOrderDto;
import lk.jnelectrical.dto.CustomerDto;
import lk.jnelectrical.dto.ProductDto;
import lk.jnelectrical.dto.SupplierDto;
import lk.jnelectrical.dto.CategoryDto;
import lk.jnelectrical.dto.ModelNumDto;
import lk.jnelectrical.dto.POrderDetailDto;
import lk.jnelectrical.dto.SubCategoryDto;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class MainForm extends javax.swing.JFrame {
   
    private BrandForm brandForm;
    private SubCategoryForm subCategoryForm;
    private CategoryForm categoryForm;
    
    private CustomerService customerService;
    private SupplierService supplierService;
    private ProductService productService;
    private PurchaseOrderService purchaseOrderService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private BrandService brandService;
    private ModelService modelService;
    
    private DefaultTableModel tableProduct;
    private DefaultTableModel tableSupplier;
    private DefaultTableModel tableCustomer; 
    private DefaultTableModel tablePurchaseOrder;
    
    private Map<String,Integer>categoryMap;
    private Map<String,Integer>subCategoryMap;
    private Map<String,Integer>brandMap;
    private Map<String,Integer> modelMap;
    private int categoryId;
    private int subCate_brand_id;
  
    
  
    public MainForm() {
        initComponents();
        getMaximize();
        setLogo();
        setLogoName();
        setIconAddCustomer();
        setIconAddProduct();
        setIconAddSupplier();
        setIconPurchasing();
        setIconInvoice();
        setIconCategoryListPopUp();
        setIconBrandPopUp();
        setIconSubCategoryPopUp();
        
        loadNextCustomerId();
        loadNextSupplierId();
        loadNextProductCode();
        loadNextPurchaseOrderNo();
        
        loadCustomerTable();
        loadSupplierTable();
       
        loadCategoryList();
        loadCmboPorductSupplier();
        loadCmboPurchasePorduct();
        loadSubCategoryList();
        loadBrandList();
        loadproductTable();
        AutoCompleteDecorator.decorate(cmboCategory);
        AutoCompleteDecorator.decorate(cmboSubCategory);
      
        
        //loadsupplierId();
   
    }

  public void getMaximize() {
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        Insets scnMax = Toolkit.getDefaultToolkit()
                .getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        this.setSize(xsize, ysize - taskBarSize);
    }
    public void setLogo() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/jnelectrical_logo.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblJnelectrical_logo.getWidth(),
                         lblJnelectrical_logo.getHeight(), Image.SCALE_SMOOTH);
        lblJnelectrical_logo.setIcon(new ImageIcon(img));
    }
    
    public void setLogoName() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/JnElectricalName.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblJnElectrical_Name.getWidth(),
                         lblJnElectrical_Name.getHeight(), Image.SCALE_SMOOTH);
        lblJnElectrical_Name.setIcon(new ImageIcon(img));
    }
    
    public void setIconAddCustomer() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/addCus.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconAddCustomer.getWidth(),
                         lblIconAddCustomer.getHeight(), Image.SCALE_SMOOTH);
        lblIconAddCustomer.setIcon(new ImageIcon(img));
    }
    
    public void setIconAddProduct() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/product.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconProduct.getWidth(),
                         lblIconProduct.getHeight(), Image.SCALE_SMOOTH);
        lblIconProduct.setIcon(new ImageIcon(img));
    }
    
    public void setIconAddSupplier() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/supplier.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconSupplier.getWidth(),
                         lblIconSupplier.getHeight(), Image.SCALE_SMOOTH);
        lblIconSupplier.setIcon(new ImageIcon(img));
    }
    
    public void setIconPurchasing() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/purchase.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconPurchase.getWidth(),
                         lblIconPurchase.getHeight(), Image.SCALE_SMOOTH);
        lblIconPurchase.setIcon(new ImageIcon(img));
    }
    
    public void setIconInvoice() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/invoice.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconInvoice.getWidth(),
                         lblIconInvoice.getHeight(), Image.SCALE_SMOOTH);
        lblIconInvoice.setIcon(new ImageIcon(img));
  
    }
    // PRODUCT TabbedPane Category List Add Icon
    public void setIconCategoryListPopUp() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/add.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconcategoryPopUp.getWidth(),
                         lblIconcategoryPopUp.getHeight(), Image.SCALE_SMOOTH);
        lblIconcategoryPopUp.setIcon(new ImageIcon(img));
    
    }
    public void setIconSubCategoryPopUp() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/add.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconSubcategoryPopUp.getWidth(),
                         lblIconSubcategoryPopUp.getHeight(), Image.SCALE_SMOOTH);
       lblIconSubcategoryPopUp.setIcon(new ImageIcon(img));
    
    }
    
    public void setIconBrandPopUp() {
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/images/add.png"));
        Image img = icon.getImage()
                .getScaledInstance(lblIconBrandPopUp.getWidth(),
                         lblIconBrandPopUp.getHeight(), Image.SCALE_SMOOTH);
       lblIconBrandPopUp.setIcon(new ImageIcon(img));
    
    }
    //CUSTOMER Tabbedpane
    public void loadNextCustomerId(){
        String nextCustomerId = CustomerIdGenerator.getNextCustomerId();
        txtCustomerId.setText(nextCustomerId);
    }
    //SUPPLIER Tabbedpane
    public void loadNextSupplierId(){
        String nextSupplierId = SupplierIdGenerator.getNextSupplierId();
        txtSupId.setText(nextSupplierId);
        
    }
    //PRODUCT Tabbedpane 
    public void loadNextProductCode(){
        String nextProductCode = ProductCodeGenerator.getNextProductCode();
        txtProductCode.setText(nextProductCode);
    }
    //NEW PURCHASING Tabbedpane
    public void loadNextPurchaseOrderNo(){
        String nextPurchaseOrderNo = PurchaseOrderNoGenerator.getNextPurchaseOrderNo();
        txtPOrderNo.setText(nextPurchaseOrderNo);
    }
    
    // CUSTOMER ClearField
    public void customerClearField(){
        txtCustomerNic.setText("");
        txtCusLName.setText("");
        txtCusLName.setText("");
        txtCusFName.setText("");
        txtCusAddLn1.setText("");
        txtCusAddLn2.setText("");
        txtCusCntctNo.setText("");
    }
    // SUPPLIER ClearField
    public void supplierClearField(){
        txtSupfName.setText("");
        txtSuplName.setText("");
        txtSupContact.setText("");
        txtSupAdd1.setText("");
        txtSupAdd2.setText("");
    }
    
    // PRODUCT ClearFied
    public void productClearField(){
       
        txtmodel.setText("");
        txtMsrp.setText("");
        txtQty.setText("");
        txtDamageQty.setText("");
      
    }  
    //CUSTOMER Tabbedpane load customer table
    private void loadCustomerTable(){
        customerService = new CustomerService();
        tableCustomer =(DefaultTableModel)tblCustomer.getModel();
        List<CustomerDto> allCustomer = customerService.getAllCustomer();
        tableCustomer .setRowCount(0);
            for(CustomerDto dto : allCustomer){
                String[]dataArray ={dto.getCustomerId(),dto.getCusNic()     
                ,dto.getCusFname(),dto.getCusLname(),dto.getCusAddLine1()
                        ,dto.getCusAddLine2(),dto.getCusContact()};
               tableCustomer.addRow(dataArray);
            }
    }
    //SUPPLIER Tabbedpane load supplier table
    private void loadSupplierTable(){
        supplierService = new SupplierService();
        tableSupplier = (DefaultTableModel)tblSupplierList.getModel();
        List<SupplierDto>supplierList = supplierService.getAllSupplier();
        tableSupplier.setRowCount(0);
        for(SupplierDto dto : supplierList){
            String [] dateArray ={dto.getSupplierId()
                    ,dto.getSupfName(),dto.getSuplName()
                    ,dto.getSupAdd1(),dto.getSupAdd2(),dto.getSupContact()};
            tableSupplier.addRow(dateArray);
            
        }        
    }
     private void loadCmboPorductSupplier(){
          supplierService = new SupplierService();
          List<SupplierDto>supplierList = supplierService.getAllSupplier();
          cmboPOrderSupplierId.removeAllItems();
            for(SupplierDto dto : supplierList){
            cmboPOrderSupplierId.addItem(dto.getSupplierId());
        }
     }    
    
    //PRODUCT Tabbedpane load product table
    private void loadproductTable(){
        productService = new ProductService();
        tableProduct =(DefaultTableModel)tblProductList.getModel();
        List<ProductDto> productList = productService.getAllProduct();
        tableProduct.setRowCount(0);
        for(ProductDto dto : productList){
            String[] dataArray = {dto.getProductCode()
                    ,dto.getProduct_categoryName()
                    ,dto.getBrand().getBrand_name(),dto.getProduct_subCategoryName(),
                    dto.getModel_no(),dto.getWarranty(),dto.getStatus()};
            tableProduct.addRow(dataArray);           
        }  
    }
    
    private void loadCmboPurchasePorduct(){
        productService = new ProductService();
        List<ProductDto> productList = productService.getAllProduct();
        cmboPOrderProductCode.removeAllItems();
       for(ProductDto dto : productList){
            cmboPOrderProductCode.addItem(dto.getProductCode());
           
       }
      
    }
    
    // PRODUCT Tabbedpane  load Category List for comboCategory
    public void loadCategoryList(){
       categoryMap = new HashMap<>();
       categoryService = new CategoryService();
       cmboCategory.removeAllItems();
        List<CategoryDto>categoryList = categoryService.getAllCategory();
        for(CategoryDto dto : categoryList){
            categoryMap.put(dto.getCategoryName(),dto.getCategoryID());
            cmboCategory.addItem(dto.getCategoryName());
        }  
    }
    public void loadSubCategoryList(){
        subCategoryMap = new HashMap<>();
        subCategoryService = new SubCategoryService();
        cmboSubCategory.removeAllItems();
        categoryId = categoryMap.get(cmboCategory.getSelectedItem());
        List<SubCategoryDto>SubCategoryList = subCategoryService
                .getAllSubCategoryById(categoryId);
         for(SubCategoryDto dto : SubCategoryList ){
             subCategoryMap.put(dto.getName(), dto.getSubCategoryId());
             cmboSubCategory.addItem(dto.getName());
         }  
    }
    
    public void loadBrandList(){
        brandMap = new HashMap<>();
        brandService = new BrandService();
        cmboBrand.removeAllItems();
        List<BrandDto> brandList = brandService.getAllBrand();
        for(BrandDto dto : brandList) {
            brandMap.put(dto.getBrand_name(), dto.getBrandId());
            cmboBrand.addItem(dto.getBrand_name());
        } 
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlInvoice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblIconInvoice = new javax.swing.JLabel();
        pnlCustomer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblIconAddCustomer = new javax.swing.JLabel();
        pnlSuppler = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblIconSupplier = new javax.swing.JLabel();
        pnlProduct = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblIconProduct = new javax.swing.JLabel();
        lblJnelectrical_logo = new javax.swing.JLabel();
        pnlPurchasing = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        lblIconPurchase = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        tbPaneInvoice = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtInvoice_brand = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtInvoice_qty = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtInvoice_item = new javax.swing.JTextField();
        cmboInvoice_product_code = new javax.swing.JComboBox<>();
        btnInvoice_addtoCart = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtInvoice_discount = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtInvoice_category = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPrice_qty = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cmboInvoice_customerId = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtInvoice_customerName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtInvoice_customerNIC = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tbPaneCustomer = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCusFName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCustomerId = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCusLName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCusAddLn1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtCustomerNic = new javax.swing.JTextField();
        txtCusAddLn2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCusCntctNo = new javax.swing.JTextField();
        btnCusEdit = new javax.swing.JButton();
        bntCusAdd = new javax.swing.JButton();
        btnCusSearch = new javax.swing.JButton();
        btnCusClear = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        tbPaneProduct = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtProductCode = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cmboCategory = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        cmboStatus = new javax.swing.JComboBox<>();
        btnProductSearch = new javax.swing.JButton();
        btnProductedit = new javax.swing.JButton();
        btnProductAdd = new javax.swing.JButton();
        cmboWarranty = new javax.swing.JComboBox<>();
        lblIconcategoryPopUp = new javax.swing.JLabel();
        btnProductClear = new javax.swing.JButton();
        cmboSubCategory = new javax.swing.JComboBox<>();
        cmboBrand = new javax.swing.JComboBox<>();
        txtmodel = new javax.swing.JTextField();
        txtDamageQty = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtMsrp = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        lblIconSubcategoryPopUp = new javax.swing.JLabel();
        lblIconBrandPopUp = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProductList = new javax.swing.JTable();
        tbPaneSupplier = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtSupfName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSupId = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtSuplName = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtSupAdd1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtSupAdd2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtSupContact = new javax.swing.JTextField();
        btnSupplierSearch = new javax.swing.JButton();
        btnSupplierEdit = new javax.swing.JButton();
        btnSupplierAdd = new javax.swing.JButton();
        btnSupplierClear = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSupplierList = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtPOrderSupplierfName = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtPOrderSupplierlName = new javax.swing.JTextField();
        cmboPOrderSupplierId = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPurchaseOrder = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtPOrderCapacity = new javax.swing.JTextField();
        cmboPOrderProductCode = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        txtPOrderCategory = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtBuyingQty = new javax.swing.JTextField();
        txtUnitCost = new javax.swing.JTextField();
        btnPOrderAdd = new javax.swing.JButton();
        btnPOrderRemove = new javax.swing.JButton();
        txtP_Brandname = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtPOrderNo = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel19 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        btnPurchasing_search = new javax.swing.JButton();
        btnPurchaseSave = new javax.swing.JButton();
        btnPurchasing_Edit = new javax.swing.JButton();
        btnPurchasing_Close = new javax.swing.JButton();
        lblClose = new javax.swing.JLabel();
        lblJnElectrical_Name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(1149, 649));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        pnlInvoice.setBackground(new java.awt.Color(102, 102, 102));
        pnlInvoice.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlInvoiceMouseMoved(evt);
            }
        });
        pnlInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlInvoiceMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INVOICE");

        lblIconInvoice.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout pnlInvoiceLayout = new javax.swing.GroupLayout(pnlInvoice);
        pnlInvoice.setLayout(pnlInvoiceLayout);
        pnlInvoiceLayout.setHorizontalGroup(
            pnlInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInvoiceLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblIconInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );
        pnlInvoiceLayout.setVerticalGroup(
            pnlInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvoiceLayout.createSequentialGroup()
                .addGroup(pnlInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInvoiceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIconInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInvoiceLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCustomer.setBackground(new java.awt.Color(102, 102, 102));
        pnlCustomer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlCustomerMouseMoved(evt);
            }
        });
        pnlCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCustomerMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADD CUSTOMER");

        lblIconAddCustomer.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout pnlCustomerLayout = new javax.swing.GroupLayout(pnlCustomer);
        pnlCustomer.setLayout(pnlCustomerLayout);
        pnlCustomerLayout.setHorizontalGroup(
            pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCustomerLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(lblIconAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18))
        );
        pnlCustomerLayout.setVerticalGroup(
            pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomerLayout.createSequentialGroup()
                .addGroup(pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCustomerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIconAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCustomerLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSuppler.setBackground(new java.awt.Color(102, 102, 102));
        pnlSuppler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSupplerMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ADD SUPPLIER");

        lblIconSupplier.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout pnlSupplerLayout = new javax.swing.GroupLayout(pnlSuppler);
        pnlSuppler.setLayout(pnlSupplerLayout);
        pnlSupplerLayout.setHorizontalGroup(
            pnlSupplerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIconSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(18, 18, 18))
        );
        pnlSupplerLayout.setVerticalGroup(
            pnlSupplerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplerLayout.createSequentialGroup()
                .addGroup(pnlSupplerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSupplerLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4))
                    .addGroup(pnlSupplerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIconSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlProduct.setBackground(new java.awt.Color(102, 102, 102));
        pnlProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProductMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ADD PRODUCT");

        javax.swing.GroupLayout pnlProductLayout = new javax.swing.GroupLayout(pnlProduct);
        pnlProduct.setLayout(pnlProductLayout);
        pnlProductLayout.setHorizontalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProductLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblIconProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(19, 19, 19))
        );
        pnlProductLayout.setVerticalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductLayout.createSequentialGroup()
                .addGroup(pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIconProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProductLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPurchasing.setBackground(new java.awt.Color(102, 102, 102));
        pnlPurchasing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPurchasingMouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("PURCHASING");

        lblIconPurchase.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout pnlPurchasingLayout = new javax.swing.GroupLayout(pnlPurchasing);
        pnlPurchasing.setLayout(pnlPurchasingLayout);
        pnlPurchasingLayout.setHorizontalGroup(
            pnlPurchasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPurchasingLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblIconPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(21, 21, 21))
        );
        pnlPurchasingLayout.setVerticalGroup(
            pnlPurchasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPurchasingLayout.createSequentialGroup()
                .addGroup(pnlPurchasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPurchasingLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel31))
                    .addGroup(pnlPurchasingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIconPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSuppler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPurchasing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblJnelectrical_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblJnelectrical_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(pnlInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSuppler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPurchasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.setBackground(new java.awt.Color(0, 102, 102));
        jTabbedPane.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane.setPreferredSize(new java.awt.Dimension(1149, 628));
        jTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneMouseClicked(evt);
            }
        });

        tbPaneInvoice.setBackground(new java.awt.Color(204, 204, 204));
        tbPaneInvoice.setPreferredSize(new java.awt.Dimension(1149, 625));
        tbPaneInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPaneInvoiceMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 251, 218));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Product"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Product Code");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Brand Name");

        txtInvoice_brand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtInvoice_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_brandActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Quantity");

        txtInvoice_qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtInvoice_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_qtyActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Available Qty");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Item");

        txtInvoice_item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtInvoice_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_itemActionPerformed(evt);
            }
        });

        cmboInvoice_product_code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmboInvoice_product_code.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnInvoice_addtoCart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInvoice_addtoCart.setText("ADD");
        btnInvoice_addtoCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoice_addtoCartActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Discount");

        txtInvoice_discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtInvoice_discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_discountActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Category");

        txtInvoice_category.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtInvoice_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_categoryActionPerformed(evt);
            }
        });

        tblPrice_qty.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblPrice_qty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblPrice_qty.setForeground(new java.awt.Color(0, 0, 255));
        tblPrice_qty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Purchase Order No", "Quantity", "Unit Price"
            }
        ));
        tblPrice_qty.setRowHeight(30);
        jScrollPane7.setViewportView(tblPrice_qty);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmboInvoice_product_code, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInvoice_category, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtInvoice_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtInvoice_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInvoice_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtInvoice_item, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInvoice_addtoCart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(100, 100, 100)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtInvoice_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtInvoice_item))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmboInvoice_product_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtInvoice_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(txtInvoice_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtInvoice_discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInvoice_addtoCart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 251, 218));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer"));

        cmboInvoice_customerId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmboInvoice_customerId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Customer Name");

        txtInvoice_customerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtInvoice_customerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_customerNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Customer ID");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Customer NIC");

        txtInvoice_customerNIC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtInvoice_customerNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoice_customerNICActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmboInvoice_customerId, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtInvoice_customerName, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInvoice_customerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cmboInvoice_customerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtInvoice_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtInvoice_customerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 251, 218));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Table"));

        tblCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblCart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCart.setForeground(new java.awt.Color(255, 255, 0));
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Catogery", "Item", "Unit Price", "Quantity", "Discount", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblCart);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("REMOVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("CLEAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Grand Total");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("CANCEL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("CHECKOUT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 74, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout tbPaneInvoiceLayout = new javax.swing.GroupLayout(tbPaneInvoice);
        tbPaneInvoice.setLayout(tbPaneInvoiceLayout);
        tbPaneInvoiceLayout.setHorizontalGroup(
            tbPaneInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneInvoiceLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(tbPaneInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tbPaneInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        tbPaneInvoiceLayout.setVerticalGroup(
            tbPaneInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneInvoiceLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        jTabbedPane.addTab("INVOICE", tbPaneInvoice);

        tbPaneCustomer.setBackground(new java.awt.Color(204, 204, 204));

        jPanel6.setBackground(new java.awt.Color(255, 251, 218));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Registration"));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Customer ID");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Last Name");

        txtCusFName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCusFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusFNameActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtCustomerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCustomerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerIdActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("First Name");

        txtCusLName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCusLName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusLNameActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Address Line 1");

        txtCusAddLn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCusAddLn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusAddLn1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Address Line 2");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("NIC");

        txtCustomerNic.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCustomerNic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNicActionPerformed(evt);
            }
        });

        txtCusAddLn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCusAddLn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusAddLn2ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Contact No");

        txtCusCntctNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCusCntctNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusCntctNoActionPerformed(evt);
            }
        });

        btnCusEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCusEdit.setText("EDIT");
        btnCusEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCusEditActionPerformed(evt);
            }
        });

        bntCusAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCusAdd.setText("ADD");
        bntCusAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCusAddActionPerformed(evt);
            }
        });

        btnCusSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCusSearch.setText("SEARCH");
        btnCusSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCusSearchActionPerformed(evt);
            }
        });

        btnCusClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCusClear.setText("CLEAR");
        btnCusClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCusClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(944, 944, 944)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCusLName)
                            .addComponent(txtCusFName)
                            .addComponent(txtCustomerNic, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)
                                    .addComponent(txtCusCntctNo))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)
                                    .addComponent(txtCusAddLn2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCusAddLn1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCusSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCusEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntCusAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCusClear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCustomerId)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntCusAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCusAddLn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCusAddLn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCusCntctNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCustomerNic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCusFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCusLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCusEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCusSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCusClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel7.setBackground(new java.awt.Color(255, 251, 218));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Table"));

        tblCustomer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCustomer.setForeground(new java.awt.Color(0, 51, 102));
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "NIC", "First Name", "Last Name", "Address Line 1", "Address Line 2", "contact No"
            }
        ));
        tblCustomer.setRowHeight(20);
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tbPaneCustomerLayout = new javax.swing.GroupLayout(tbPaneCustomer);
        tbPaneCustomer.setLayout(tbPaneCustomerLayout);
        tbPaneCustomerLayout.setHorizontalGroup(
            tbPaneCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneCustomerLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(tbPaneCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tbPaneCustomerLayout.setVerticalGroup(
            tbPaneCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneCustomerLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("CUSTOMER", tbPaneCustomer);

        tbPaneProduct.setBackground(new java.awt.Color(204, 204, 204));

        jPanel10.setBackground(new java.awt.Color(255, 251, 218));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Product"));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Product Code");

        txtProductCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProductCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductCodeActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Category ");

        cmboCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmboCategory.setToolTipText("");
        cmboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboCategoryActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Brand Name");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Capacity");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Model No");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Warranty");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Status");

        cmboStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));

        btnProductSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductSearch.setText("SEARCH");
        btnProductSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductSearchActionPerformed(evt);
            }
        });

        btnProductedit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductedit.setText("EDIT");
        btnProductedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProducteditActionPerformed(evt);
            }
        });

        btnProductAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductAdd.setText("ADD");
        btnProductAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductAddActionPerformed(evt);
            }
        });

        cmboWarranty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboWarranty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/W", "03 Months", "06 Months", "01 Year", "02 Year" }));

        lblIconcategoryPopUp.setText("jLabel51");
        lblIconcategoryPopUp.setPreferredSize(new java.awt.Dimension(14, 14));
        lblIconcategoryPopUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconcategoryPopUpMouseClicked(evt);
            }
        });

        btnProductClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductClear.setText("CLEAR");
        btnProductClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductClearActionPerformed(evt);
            }
        });

        cmboSubCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboSubCategoryActionPerformed(evt);
            }
        });

        cmboBrand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboBrandActionPerformed(evt);
            }
        });

        txtmodel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodelActionPerformed(evt);
            }
        });

        txtDamageQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDamageQty.setText("0");
        txtDamageQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDamageQtyActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Damagers");

        txtMsrp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMsrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMsrpActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("msrp");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Quantity");

        txtQty.setEditable(false);
        txtQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQty.setText("0");
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        lblIconSubcategoryPopUp.setText("jLabel51");
        lblIconSubcategoryPopUp.setPreferredSize(new java.awt.Dimension(14, 14));
        lblIconSubcategoryPopUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconSubcategoryPopUpMouseClicked(evt);
            }
        });

        lblIconBrandPopUp.setText("jLabel51");
        lblIconBrandPopUp.setPreferredSize(new java.awt.Dimension(14, 14));
        lblIconBrandPopUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconBrandPopUpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmboSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(lblIconcategoryPopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmboWarranty, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIconSubcategoryPopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIconBrandPopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMsrp, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDamageQty, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(110, 110, 110)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnProductSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProductAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnProductedit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProductClear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(cmboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIconcategoryPopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmboSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIconSubcategoryPopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(lblIconBrandPopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductAdd)
                            .addComponent(cmboWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProductSearch)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnProductedit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnProductClear))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMsrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDamageQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62))))
        );

        jPanel11.setBackground(new java.awt.Color(255, 251, 218));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Product List"));

        tblProductList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblProductList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblProductList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Category", "Brand Name", "Capacity", "Model No", "Warranty", "Status"
            }
        ));
        tblProductList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblProductList);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tbPaneProductLayout = new javax.swing.GroupLayout(tbPaneProduct);
        tbPaneProduct.setLayout(tbPaneProductLayout);
        tbPaneProductLayout.setHorizontalGroup(
            tbPaneProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneProductLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(tbPaneProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        tbPaneProductLayout.setVerticalGroup(
            tbPaneProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbPaneProductLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jTabbedPane.addTab("PRODUCT", tbPaneProduct);

        tbPaneSupplier.setBackground(new java.awt.Color(204, 204, 204));

        jPanel8.setBackground(new java.awt.Color(255, 251, 218));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Supplier Registration"));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Supplier ID");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Last Name");

        txtSupfName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupfNameActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtSupId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupIdActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("First Name");

        txtSuplName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSuplName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuplNameActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Address Line 1");

        txtSupAdd1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupAdd1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Address Line 2");

        txtSupAdd2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupAdd2ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Contact No");

        txtSupContact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupContactActionPerformed(evt);
            }
        });

        btnSupplierSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplierSearch.setText("SEARCH");
        btnSupplierSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierSearchActionPerformed(evt);
            }
        });

        btnSupplierEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplierEdit.setText("EDIT");
        btnSupplierEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierEditActionPerformed(evt);
            }
        });

        btnSupplierAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplierAdd.setText("ADD");
        btnSupplierAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierAddActionPerformed(evt);
            }
        });

        btnSupplierClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplierClear.setText("CLEAR");
        btnSupplierClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSupfName, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSuplName, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupId, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSupAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupContact, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSupplierSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupplierEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupplierClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSupId)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSupAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSuplName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jPanel9.setBackground(new java.awt.Color(255, 251, 218));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Table"));

        tblSupplierList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblSupplierList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSupplierList.setForeground(new java.awt.Color(102, 0, 102));
        tblSupplierList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "First Name", "Last Name", "Address Line 1", "Address Line 2", "Contact no"
            }
        ));
        tblSupplierList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSupplierList);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tbPaneSupplierLayout = new javax.swing.GroupLayout(tbPaneSupplier);
        tbPaneSupplier.setLayout(tbPaneSupplierLayout);
        tbPaneSupplierLayout.setHorizontalGroup(
            tbPaneSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbPaneSupplierLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(tbPaneSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        tbPaneSupplierLayout.setVerticalGroup(
            tbPaneSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbPaneSupplierLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("SUPPLIER", tbPaneSupplier);

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jPanel13.setBackground(new java.awt.Color(255, 251, 218));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Supplier Detail"));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Supplier ID");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Last Name");

        txtPOrderSupplierfName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPOrderSupplierfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOrderSupplierfNameActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("First Name");

        txtPOrderSupplierlName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPOrderSupplierlName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOrderSupplierlNameActionPerformed(evt);
            }
        });

        cmboPOrderSupplierId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmboPOrderSupplierId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboPOrderSupplierIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmboPOrderSupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPOrderSupplierfName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(31, 31, 31)
                        .addComponent(txtPOrderSupplierlName, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPOrderSupplierlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmboPOrderSupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPOrderSupplierfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 251, 218));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchasing"));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tblPurchaseOrder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblPurchaseOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPurchaseOrder.setForeground(new java.awt.Color(102, 0, 102));
        tblPurchaseOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Purchase Order No", "Supplier ID", "product Code", "Category", "Capacity", "brand name", "buy Qty", "Unit Cost", "Date"
            }
        ));
        tblPurchaseOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPurchaseOrderMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPurchaseOrder);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(840, 840, 840)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jScrollPane4)
                    .addGap(9, 9, 9)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel55)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        jPanel14.setBackground(new java.awt.Color(255, 251, 218));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(" Product Detail"));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Product Code");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Capacity");

        txtPOrderCapacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPOrderCapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOrderCapacityActionPerformed(evt);
            }
        });

        cmboPOrderProductCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmboPOrderProductCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmboPOrderProductCodeMouseClicked(evt);
            }
        });
        cmboPOrderProductCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboPOrderProductCodeActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Category");

        txtPOrderCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPOrderCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOrderCategoryActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Buy Quantity");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Unit Cost");

        txtBuyingQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuyingQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuyingQtyActionPerformed(evt);
            }
        });

        txtUnitCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUnitCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitCostActionPerformed(evt);
            }
        });

        btnPOrderAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPOrderAdd.setText("ADD");
        btnPOrderAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPOrderAddActionPerformed(evt);
            }
        });

        btnPOrderRemove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPOrderRemove.setText("REMOVE");
        btnPOrderRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPOrderRemoveActionPerformed(evt);
            }
        });

        txtP_Brandname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtP_Brandname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP_BrandnameActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Brand Name");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(cmboPOrderProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtP_Brandname)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtPOrderCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(48, 48, 48)
                        .addComponent(txtPOrderCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuyingQty, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(btnPOrderAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPOrderRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel56)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPOrderCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPOrderCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmboPOrderProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(txtBuyingQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPOrderAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPOrderRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtP_Brandname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 251, 218));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchasing Order"));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 0, 0));
        jLabel62.setText("Purchase Order");

        txtPOrderNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPOrderNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOrderNoActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 0, 0));
        jLabel63.setText("Date");

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 251, 218));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnPurchasing_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPurchasing_search.setText("SEARCH");
        btnPurchasing_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchasing_searchActionPerformed(evt);
            }
        });

        btnPurchaseSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPurchaseSave.setText("Save");
        btnPurchaseSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseSaveActionPerformed(evt);
            }
        });

        btnPurchasing_Edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPurchasing_Edit.setText("EDIT");
        btnPurchasing_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchasing_EditActionPerformed(evt);
            }
        });

        btnPurchasing_Close.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPurchasing_Close.setText("CLOSE");
        btnPurchasing_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchasing_CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(btnPurchasing_search, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPurchaseSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPurchasing_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPurchasing_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPurchasing_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPurchasing_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPurchaseSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPurchasing_search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        jTabbedPane.addTab("NEW PURCHASING", jPanel12);

        lblClose.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblClose.setForeground(new java.awt.Color(204, 0, 0));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("x");
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblJnElectrical_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(368, 368, 368)
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJnElectrical_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtInvoice_customerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_customerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_customerNameActionPerformed

    private void txtInvoice_customerNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_customerNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_customerNICActionPerformed

    private void txtInvoice_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_brandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_brandActionPerformed

    private void txtInvoice_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_qtyActionPerformed

    private void txtInvoice_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_itemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_itemActionPerformed

    private void txtInvoice_discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_discountActionPerformed

    private void btnInvoice_addtoCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoice_addtoCartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInvoice_addtoCartActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
            //close button of Main Form
    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void pnlInvoiceMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInvoiceMouseMoved
       
    }//GEN-LAST:event_pnlInvoiceMouseMoved

    private void setLabelBackgroundColor(){
        pnlInvoice.setBackground(new Color(102, 102, 102));
        pnlCustomer.setBackground(new Color(102, 102, 102));
        pnlProduct.setBackground(new Color(102, 102, 102));
        pnlSuppler.setBackground(new Color(102, 102, 102));
        pnlPurchasing.setBackground(new Color(102, 102, 102));
    }
    
    private void pnlInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInvoiceMouseClicked
        jTabbedPane.setSelectedIndex(0);
        setLabelBackgroundColor();
        pnlInvoice.setBackground(new Color(135, 135, 135));
    }//GEN-LAST:event_pnlInvoiceMouseClicked

    private void pnlCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCustomerMouseClicked
        jTabbedPane.setSelectedIndex(1);
        setLabelBackgroundColor();
        pnlCustomer.setBackground(new Color(135, 135, 135));  
    }//GEN-LAST:event_pnlCustomerMouseClicked

    private void pnlProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProductMouseClicked
        jTabbedPane.setSelectedIndex(2);
        setLabelBackgroundColor();
        pnlProduct.setBackground(new Color(135, 135, 135));
    }//GEN-LAST:event_pnlProductMouseClicked

    private void pnlSupplerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSupplerMouseClicked
        jTabbedPane.setSelectedIndex(3);
        setLabelBackgroundColor();
        pnlSuppler.setBackground(new Color(135, 135, 135));
    }//GEN-LAST:event_pnlSupplerMouseClicked

    private void jTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPaneMouseClicked

    }//GEN-LAST:event_jTabbedPaneMouseClicked

    private void tbPaneInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPaneInvoiceMouseClicked

    }//GEN-LAST:event_tbPaneInvoiceMouseClicked

    private void txtCusFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusFNameActionPerformed

    private void txtCustomerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerIdActionPerformed

    private void txtCusLNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusLNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusLNameActionPerformed

    private void txtCusAddLn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusAddLn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusAddLn1ActionPerformed

    private void txtCusAddLn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusAddLn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusAddLn2ActionPerformed

    private void txtCustomerNicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNicActionPerformed
        // EDIT button of CUSTOMER TabbedPane 
    private void btnCusEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCusEditActionPerformed
        customerService = new CustomerService();
        CustomerDto dto = new CustomerDto();
        if( txtCustomerId.getText().isEmpty()|| txtCustomerNic.getText()
                    .isEmpty()|| txtCusLName.getText()
                        .isEmpty()|| txtCusFName.getText()
                            .isEmpty()|| txtCusAddLn1.getText()
                                .isEmpty()|| txtCusAddLn2.getText()
                                        .isEmpty()|| txtCusCntctNo.getText()
                                                                .isEmpty()){
                        
             JOptionPane.showMessageDialog(this," Missing Information ");          
        } else {
            dto.setCustomerId(txtCustomerId.getText());
            dto.setCusNic(txtCustomerNic.getText());
            dto.setCusLname(txtCusLName.getText());
            dto.setCusFname(txtCusFName.getText());
            dto.setCusAddLine1(txtCusAddLn1.getText());
            dto.setCusAddLine2(txtCusAddLn2.getText());
            dto.setCusContact(txtCusCntctNo.getText());
        
            int rowUpdate = customerService.updateCustomerById(dto);
            if(rowUpdate == 1){
                JOptionPane.showMessageDialog(this,"Successfully Customer Updated ");
                loadCustomerTable();
                customerClearField();
                loadNextCustomerId();
            } else {
                JOptionPane.showMessageDialog(this,"Customer Update Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCusEditActionPerformed
        // ADD button of CUSTOMER TabbedPane 
    private void bntCusAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCusAddActionPerformed
      customerService = new CustomerService();
        CustomerDto dto = new CustomerDto();
       
        if(txtCustomerId.getText()
                .isEmpty()|| txtCustomerNic.getText()
                    .isEmpty()|| txtCusLName.getText()
                        .isEmpty()|| txtCusFName.getText()
                            .isEmpty()|| txtCusAddLn1.getText()
                                .isEmpty()|| txtCusAddLn2.getText()
                                   .isEmpty()|| txtCusCntctNo.getText()
                                        .isEmpty()){
                        
             JOptionPane.showMessageDialog(this," Missing Information ");          
              
        } else {
            dto.setCustomerId(txtCustomerId.getText());
            dto.setCusNic(txtCustomerNic.getText());
            dto.setCusLname(txtCusLName.getText());
            dto.setCusFname(txtCusFName.getText());
            dto.setCusAddLine1(txtCusAddLn1.getText());
            dto.setCusAddLine2(txtCusAddLn2.getText());
            dto.setCusContact(txtCusCntctNo.getText());
        
            int rowAffected = customerService.addCustomer(dto);
            if(rowAffected == 1){
                JOptionPane.showMessageDialog(this
                        ,"Successfully Customer Added ");
                loadNextCustomerId();
                loadCustomerTable();
                customerClearField();
            } else {
                JOptionPane.showMessageDialog(this,"Customer Add Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bntCusAddActionPerformed

    private void txtCusCntctNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusCntctNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusCntctNoActionPerformed
        // SEARCH button of CUSTOMER Tabbedpane
    private void btnCusSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCusSearchActionPerformed
        customerService = new CustomerService();
        CustomerDto dto = customerService
                .getCustomerById(txtCustomerId.getText());
        if(dto != null){
        txtCustomerNic.setText(dto.getCusNic());
        txtCusLName.setText(dto.getCusLname());
        txtCusFName.setText(dto.getCusFname());
        txtCusAddLn1.setText(dto.getCusAddLine1());
        txtCusAddLn2.setText(dto.getCusAddLine2());
        txtCusCntctNo.setText(dto.getCusContact());
        
        } else {
            JOptionPane.showMessageDialog(this
                    ,txtCustomerId.getText()+ " Not found");
        }
    }//GEN-LAST:event_btnCusSearchActionPerformed

    private void pnlCustomerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCustomerMouseMoved
       
    }//GEN-LAST:event_pnlCustomerMouseMoved

    private void txtSupfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupfNameActionPerformed

    private void txtSupIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupIdActionPerformed

    private void txtSuplNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuplNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSuplNameActionPerformed

    private void txtSupAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupAdd1ActionPerformed

    private void txtSupAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupAdd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupAdd2ActionPerformed

    private void txtSupContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupContactActionPerformed

    private void txtInvoice_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoice_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoice_categoryActionPerformed
       // ADD button of SUPPLIER Tabbedpane
    private void btnSupplierAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierAddActionPerformed
       supplierService = new SupplierService();
        SupplierDto dto = new SupplierDto();
        if(txtSupId.getText().isEmpty()||txtSuplName
                .getText().isEmpty()||txtSupfName
                        .getText().isEmpty()||txtSupContact
                                .getText().isEmpty()||txtSupAdd1.getText()
                                        .isEmpty()||txtSupAdd2
                                                .getText().isEmpty()){
            
        JOptionPane.showMessageDialog(this,"Missing information");
        } else {
        dto.setSupplierId(txtSupId.getText());
        dto.setSuplName(txtSuplName.getText());
        dto.setSupfName(txtSupfName.getText());
        dto.setSupContact(txtSupContact.getText());
        dto.setSupAdd1(txtSupAdd1.getText());
        dto.setSupAdd2(txtSupAdd2.getText());  
           
        int rowAffected = supplierService.addSupplier(dto);
            if(rowAffected == 1){
                JOptionPane.showMessageDialog(this,"Successfully Supplier Added ");
                supplierClearField();
                loadNextSupplierId();
                loadSupplierTable();
                loadCmboPorductSupplier();
                
            } else {
                JOptionPane.showMessageDialog(this,"Supplier Add Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSupplierAddActionPerformed
    // SEARCH button of SUPPLIER Tabbedpane
    private void btnSupplierSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierSearchActionPerformed
       supplierService = new SupplierService();
       SupplierDto dto = supplierService.getSupplierById(txtSupId.getText());
       if(dto!= null){
          txtSupId.setText(dto.getSupplierId());
          txtSupfName.setText(dto.getSupfName());
          txtSuplName.setText(dto.getSuplName());
          txtSupAdd1.setText(dto.getSupAdd1());
          txtSupAdd2.setText(dto.getSupAdd2());
          txtSupContact.setText(dto.getSupContact());  
       } else 
           JOptionPane.showMessageDialog(this,txtSupId.getText()+ " Not found");
    }//GEN-LAST:event_btnSupplierSearchActionPerformed
        // EDIT button of SUPPLIER Tabbedpane
    private void btnSupplierEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierEditActionPerformed
       supplierService = new SupplierService();
       SupplierDto dto = new SupplierDto();
        if(txtSupId.getText().isEmpty()||txtSuplName
                .getText().isEmpty()||txtSupfName
                        .getText().isEmpty()||txtSupContact
                                .getText().isEmpty()||txtSupAdd1.getText()
                                        .isEmpty()||txtSupAdd2
                                                .getText().isEmpty()){
            
        JOptionPane.showMessageDialog(this,"Missing information");
        } else {
            dto.setSupplierId(txtSupId.getText());
            dto.setSuplName(txtSuplName.getText());
            dto.setSupfName(txtSupfName.getText());
            dto.setSupContact(txtSupContact.getText());
            dto.setSupAdd1(txtSupAdd1.getText());
            dto.setSupAdd2(txtSupAdd2.getText());  
            
            int updaterow = supplierService.updateSupplierById(dto);
            if(updaterow == 1){
                JOptionPane.showMessageDialog(this
                        ,"Successfully Supplier Updated ");
                loadSupplierTable();
                supplierClearField();
                loadNextSupplierId();
            } else {
                JOptionPane.showMessageDialog(this,"Supplier Update Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
            }
        }   
    }//GEN-LAST:event_btnSupplierEditActionPerformed

    private void pnlPurchasingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPurchasingMouseClicked
        jTabbedPane.setSelectedIndex(4);
        setLabelBackgroundColor();
        pnlPurchasing.setBackground(new Color(135, 135, 135));
    }//GEN-LAST:event_pnlPurchasingMouseClicked
        //CLEAR button of CUSTOMER TabbedPane
    private void btnCusClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCusClearActionPerformed
        customerClearField();
        loadNextCustomerId();
    }//GEN-LAST:event_btnCusClearActionPerformed

    private void txtPOrderSupplierfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOrderSupplierfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOrderSupplierfNameActionPerformed

    private void txtPOrderSupplierlNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOrderSupplierlNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOrderSupplierlNameActionPerformed
        // mouse click CUSTOMER tableList of CUSTOMER tabbedPane
    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        int selectedRow = tblCustomer.getSelectedRow();
        txtCustomerId.setText(tblCustomer.getValueAt(selectedRow, 0).toString());
        txtCustomerNic.setText(tblCustomer.getValueAt(selectedRow, 1).toString());
        txtCusFName.setText(tblCustomer.getValueAt(selectedRow, 2).toString());
        txtCusLName.setText(tblCustomer.getValueAt(selectedRow, 3).toString());
        txtCusAddLn1.setText(tblCustomer.getValueAt(selectedRow, 4).toString());
        txtCusAddLn2.setText(tblCustomer.getValueAt(selectedRow, 5).toString());
        txtCusCntctNo.setText(tblCustomer.getValueAt(selectedRow, 6).toString());  
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnSupplierClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierClearActionPerformed
       supplierClearField();
       loadNextSupplierId();
    }//GEN-LAST:event_btnSupplierClearActionPerformed
        // mouse clik SUPPLIER tableList of SUPPLIER tabbedPane
    private void tblSupplierListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierListMouseClicked
        int selectedRow = tblSupplierList.getSelectedRow();
        txtSupId.setText(tblSupplierList.getValueAt(selectedRow, 0).toString());
        txtSupfName.setText(tblSupplierList.getValueAt(selectedRow,1).toString());
        txtSuplName.setText(tblSupplierList.getValueAt(selectedRow, 2).toString());
        txtSupAdd1.setText(tblSupplierList.getValueAt(selectedRow, 3).toString());
        txtSupAdd2.setText(tblSupplierList.getValueAt(selectedRow, 4).toString());
        txtSupContact.setText(tblSupplierList.getValueAt(selectedRow, 5).toString());
    }//GEN-LAST:event_tblSupplierListMouseClicked

    private void txtPOrderCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOrderCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOrderCategoryActionPerformed

    private void txtPOrderCapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOrderCapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOrderCapacityActionPerformed

    private void txtBuyingQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuyingQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuyingQtyActionPerformed

    private void txtP_BrandnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP_BrandnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP_BrandnameActionPerformed

    private void lblIconcategoryPopUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconcategoryPopUpMouseClicked
        categoryForm = new CategoryForm(this);
        categoryForm.setVisible(true);

    }//GEN-LAST:event_lblIconcategoryPopUpMouseClicked
       //ADD button of PRODUCT Tabbedpane
    private void btnProductAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductAddActionPerformed
        productService = new ProductService();
        
        BrandDto brand = new BrandDto();
        ProductDto productDto = new ProductDto();
        if(txtProductCode.getText()
               .isEmpty()||cmboBrand.getSelectedItem().toString()
                   .isEmpty()||cmboSubCategory.getSelectedItem().toString()
                        .isEmpty()||txtmodel.getText()
                            .isEmpty()||cmboWarranty.getSelectedItem().toString()
                                .isEmpty()||cmboStatus.getSelectedItem()
                                        .toString().isEmpty()||txtMsrp.getText()
                                            .isEmpty()||txtQty.getText()
                                                .isEmpty()||txtDamageQty
                                                        .getText().isEmpty()){
                                            
            
        JOptionPane.showMessageDialog(this,"Missing information");
        
        } else {
            productDto.setProductCode(txtProductCode.getText());
            brand.setBrandId(brandMap.get(cmboBrand.getSelectedItem()));
            productDto.setSubCategoryId_fk(subCategoryMap
                    .get(cmboSubCategory.getSelectedItem()));
            productDto.setModel_no(txtmodel.getText());
            productDto.setWarranty(cmboWarranty.getSelectedItem().toString());
            productDto.setStatus(cmboStatus.getSelectedItem().toString());
            productDto.setMsrp(Double.parseDouble(txtMsrp.getText()));
            productDto.setQuantity(Integer.parseInt(txtQty.getText()));
            productDto.setDamage_qty(Integer.parseInt(txtDamageQty.getText()));
            
            
            productDto.setBrand(brand);
            
            int rowAffected = productService.addProduct(productDto);
                if(rowAffected == 1){
                    JOptionPane.showMessageDialog(this,"Successfully Product Added ");
                 loadproductTable();
                 productClearField();
                 loadCmboPurchasePorduct();
                 
                } else {
                JOptionPane.showMessageDialog(this,"Product Add Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
                }    
                
        }
        
    }//GEN-LAST:event_btnProductAddActionPerformed
        //EDIT button of PRODUCT Tabbedpane
    private void btnProducteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProducteditActionPerformed
       productService = new ProductService();
       ProductDto dto = new ProductDto();
       BrandDto brand = new BrandDto();
      if(txtProductCode.getText()
               .isEmpty()||cmboBrand.getSelectedItem().toString()
                   .isEmpty()||cmboSubCategory.getSelectedItem().toString()
                        .isEmpty()||txtmodel.getText()
                            .isEmpty()||cmboWarranty.getSelectedItem().toString()
                                .isEmpty()||cmboStatus.getSelectedItem()
                                        .toString().isEmpty()||txtMsrp.getText()
                                            .isEmpty()||txtQty.getText()
                                                .isEmpty()||txtDamageQty
                                                        .getText().isEmpty()){
        JOptionPane.showMessageDialog(this,"Missing information");
        
        } else {
            dto.setProductCode(txtProductCode.getText());
            brand.setBrandId(brandMap.get(cmboBrand.getSelectedItem()));
            dto.setSubCategoryId_fk(subCategoryMap
                    .get(cmboSubCategory.getSelectedItem()));
            dto.setModel_no(txtmodel.getText());
            dto.setWarranty(cmboWarranty.getSelectedItem().toString());
            dto.setStatus(cmboStatus.getSelectedItem().toString());
            dto.setMsrp(Double.parseDouble(txtMsrp.getText()));
            dto.setQuantity(Integer.parseInt(txtQty.getText()));
            dto.setDamage_qty(Integer.parseInt(txtDamageQty.getText()));
            dto.setSubCate_brand_id(subCate_brand_id);
            dto.setBrand(brand);
            int rowAffected = productService.updateProductByProdId(dto);
                if(rowAffected == 1){
                    JOptionPane.showMessageDialog(this
                            ,"Successfully Product Updated ");
                 loadproductTable();
                 productClearField();
                 loadNextProductCode();
                 
                } else {
                JOptionPane.showMessageDialog(this,"Product Update Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
                }       
        }
    }//GEN-LAST:event_btnProducteditActionPerformed
        // SEARCH button of PRODUCT Tabbedpane
    private void btnProductSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductSearchActionPerformed
       productService = new ProductService();
       ProductDto dto = productService
               .getProductByProdCode(txtProductCode.getText());
        if(txtProductCode.getText().equals("")){
            JOptionPane.showMessageDialog(this," Missing product id ");
        } else{
            if(dto != null ){
               txtProductCode.setText(dto.getProductCode());
               cmboCategory.setSelectedItem(dto.getProduct_categoryName());
               cmboSubCategory.setSelectedItem(dto.getProduct_subCategoryName());
               cmboBrand.setSelectedItem(dto.getBrand().getBrand_name());
               txtmodel.setText(dto.getModel_no());
               cmboWarranty.setSelectedItem(dto.getWarranty());
               cmboStatus.setSelectedItem(dto.getStatus());
               txtMsrp.setText(String.valueOf(dto.getMsrp()));
               txtQty.setText(String.valueOf(dto.getQuantity()));
               txtDamageQty.setText(String.valueOf(dto.getDamage_qty()));
                
               subCate_brand_id = dto.getSubCate_brand_id();
               
            }else {
            JOptionPane.showMessageDialog(this
                    ,txtProductCode.getText()+ " Not found");
            }  
        } 
    }//GEN-LAST:event_btnProductSearchActionPerformed

    private void txtProductCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductCodeActionPerformed
    //CLEAR PROutton of PRODUCT Tabbedpane 
    private void btnProductClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductClearActionPerformed
       productClearField();
       loadNextProductCode();
       cmboCategory.setSelectedIndex(-1);
    }//GEN-LAST:event_btnProductClearActionPerformed

    private void cmboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboCategoryActionPerformed
       if(cmboCategory.getSelectedItem() != null){
             categoryId = categoryMap.get(cmboCategory.getSelectedItem());
       subCategoryForm = new SubCategoryForm(categoryId,this);
       loadSubCategoryList();
       }
      
    }//GEN-LAST:event_cmboCategoryActionPerformed
    // PRODUCT Tabbedpane - Row selection of PRODUCT table List
    private void tblProductListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductListMouseClicked

        int selectedRow = tblProductList.getSelectedRow();
        txtProductCode.setText(tblProductList.getValueAt(selectedRow,0).toString());
        cmboCategory.setSelectedItem(tblProductList.getValueAt(selectedRow,1));
       // cmboBrand.setSelectedItem(tblProductList.getValueAt(selectedRow,2).toString());
        cmboSubCategory.setSelectedItem(tblProductList.getValueAt(selectedRow,3).toString());
//        txtModelNo.setText(tblProductList.getValueAt(selectedRow,4).toString());
        cmboWarranty.setSelectedItem(tblProductList.getValueAt(selectedRow,5));
        cmboStatus.setSelectedItem(tblProductList.getValueAt(selectedRow,6));
     
    }//GEN-LAST:event_tblProductListMouseClicked

    private void txtPOrderNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOrderNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOrderNoActionPerformed

    private void txtUnitCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitCostActionPerformed
    //SEARCH button of NEW PURCHASING Tabbedpane
    private void btnPurchasing_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchasing_searchActionPerformed
        
    }//GEN-LAST:event_btnPurchasing_searchActionPerformed
    // ADD button of NEW PURCHASING Tabbedpane
    private void btnPurchaseSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseSaveActionPerformed
        purchaseOrderService = new PurchaseOrderService();
        PurchaseOrderDto dto = new PurchaseOrderDto();
        //ProductDto productDto = new ProductDto();
        List<POrderDetailDto>POrderList = new ArrayList<>();
        
        dto.setSupplierIdfk(cmboPOrderSupplierId
                .getSelectedItem().toString());
        dto.setpOrderNo(txtPOrderNo.getText());
        
            
        for(int count = 0; count<tablePurchaseOrder.getRowCount(); count++){
         
            POrderDetailDto pOrderDetailDto = new POrderDetailDto();
            
            pOrderDetailDto.setpOrderNo(tablePurchaseOrder
                    .getValueAt(count, 0).toString()); 
            pOrderDetailDto.setProdCode(tablePurchaseOrder
                   .getValueAt(count, 2).toString());
            pOrderDetailDto.setBuying_qty(Integer
                    .parseInt(tablePurchaseOrder.getValueAt(count,6).toString()));
            pOrderDetailDto.setBuying_price(Double
                    .parseDouble(tablePurchaseOrder.getValueAt(count, 7).toString()));
            pOrderDetailDto.setBuying_date(tablePurchaseOrder.getValueAt(count, 8).toString());
                        
            POrderList.add(pOrderDetailDto);
            
        } 
        dto.setPOrderList(POrderList);
           int rowAffected = purchaseOrderService.addPurchaseOrder(dto);
                if (rowAffected >= 1){
                     JOptionPane.showMessageDialog(this
                            ,"Successfully new purchasing Added ");
                     loadNextPurchaseOrderNo();
                        
                } else {
                    JOptionPane.showMessageDialog(this," Add Puchasing Fail"
                    ,"Failed", JOptionPane.ERROR_MESSAGE);
                }  
    }//GEN-LAST:event_btnPurchaseSaveActionPerformed

    private void btnPurchasing_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchasing_EditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPurchasing_EditActionPerformed

    private void btnPurchasing_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchasing_CloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPurchasing_CloseActionPerformed

    private void cmboPOrderSupplierIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboPOrderSupplierIdActionPerformed
       supplierService = new SupplierService();
       if(cmboPOrderSupplierId.getSelectedItem()!= null){
           SupplierDto dto = supplierService
                .getSupplierById(cmboPOrderSupplierId.getSelectedItem().toString()); 
           if(dto != null){
            txtPOrderSupplierlName.setText(dto.getSuplName());
            txtPOrderSupplierfName.setText(dto.getSupfName());
           } 
       }  
    }//GEN-LAST:event_cmboPOrderSupplierIdActionPerformed

    private void cmboPOrderProductCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboPOrderProductCodeActionPerformed
        productService = new ProductService();
        if(cmboPOrderProductCode.getSelectedItem()!= null){
             ProductDto dto = productService
                .getProductByProdCode(cmboPOrderProductCode
                        .getSelectedItem().toString());
            if (dto != null) {
                txtP_Brandname.setText(dto.getBrand().getBrand_name());
                txtPOrderCapacity.setText(dto.getProduct_subCategoryName());
                txtPOrderCategory.setText(dto.getProduct_categoryName()); 
            }
        } 
    }//GEN-LAST:event_cmboPOrderProductCodeActionPerformed

    private void cmboPOrderProductCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmboPOrderProductCodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmboPOrderProductCodeMouseClicked

    private void cmboSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboSubCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmboSubCategoryActionPerformed

    private void cmboBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmboBrandActionPerformed

    private void txtmodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmodelActionPerformed

    private void txtDamageQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDamageQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDamageQtyActionPerformed

    private void txtMsrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMsrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMsrpActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void btnPOrderAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPOrderAddActionPerformed
      tablePurchaseOrder = (DefaultTableModel) tblPurchaseOrder.getModel();
        Date date =jDateChooser1.getDate();
        if(txtPOrderNo.getText().isEmpty()||
          cmboPOrderSupplierId.getSelectedItem().toString()
            .isEmpty()||cmboPOrderProductCode
                .getSelectedItem().toString()
                    .isEmpty()||txtBuyingQty.getText()
                            .isEmpty()||txtUnitCost.getText()
                                    .isEmpty()||date == null){
      
            JOptionPane.showMessageDialog(this,"Missing information");
        } else {
            String pOrderNo = txtPOrderNo.getText();
            String supplier = cmboPOrderSupplierId.getSelectedItem().toString();
            String productCode = cmboPOrderProductCode.getSelectedItem().toString();
            String category = txtPOrderCategory.getText();
            String capacity = txtPOrderCapacity.getText();
            String brandName = txtP_Brandname.getText();
            String buyQty = String.valueOf(txtBuyingQty.getText());
            String unitCost = String.valueOf(txtUnitCost.getText());
            String orderDate = DateFormatter.formatDate(date);
            
            String[]dataArray = {pOrderNo, supplier, productCode, category,
                capacity, brandName, buyQty, unitCost, orderDate};
            tablePurchaseOrder.addRow(dataArray);
        }     
    }//GEN-LAST:event_btnPOrderAddActionPerformed

    private void tblPurchaseOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchaseOrderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPurchaseOrderMouseClicked

    private void btnPOrderRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPOrderRemoveActionPerformed
        tablePurchaseOrder = (DefaultTableModel)tblPurchaseOrder.getModel();
        int selectedRow = tblPurchaseOrder.getSelectedRow();
        if(selectedRow  != -1){
            tablePurchaseOrder.removeRow(selectedRow);
    } else {
            JOptionPane.showMessageDialog(this,"Please select a row do you want to remove");
        }
    }//GEN-LAST:event_btnPOrderRemoveActionPerformed

    private void lblIconSubcategoryPopUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconSubcategoryPopUpMouseClicked
        subCategoryForm.loadSubCategoryTable();
        subCategoryForm.setVisible(true);
    }//GEN-LAST:event_lblIconSubcategoryPopUpMouseClicked

    private void lblIconBrandPopUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconBrandPopUpMouseClicked
        brandForm = new BrandForm(this);
        brandForm.setVisible(true);
    }//GEN-LAST:event_lblIconBrandPopUpMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCusAdd;
    private javax.swing.JButton btnCusClear;
    private javax.swing.JButton btnCusEdit;
    private javax.swing.JButton btnCusSearch;
    private javax.swing.JButton btnInvoice_addtoCart;
    private javax.swing.JButton btnPOrderAdd;
    private javax.swing.JButton btnPOrderRemove;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JButton btnProductClear;
    private javax.swing.JButton btnProductSearch;
    private javax.swing.JButton btnProductedit;
    private javax.swing.JButton btnPurchaseSave;
    private javax.swing.JButton btnPurchasing_Close;
    private javax.swing.JButton btnPurchasing_Edit;
    private javax.swing.JButton btnPurchasing_search;
    private javax.swing.JButton btnSupplierAdd;
    private javax.swing.JButton btnSupplierClear;
    private javax.swing.JButton btnSupplierEdit;
    private javax.swing.JButton btnSupplierSearch;
    private javax.swing.JComboBox<String> cmboBrand;
    private javax.swing.JComboBox<String> cmboCategory;
    private javax.swing.JComboBox<String> cmboInvoice_customerId;
    private javax.swing.JComboBox<String> cmboInvoice_product_code;
    private javax.swing.JComboBox<String> cmboPOrderProductCode;
    private javax.swing.JComboBox<String> cmboPOrderSupplierId;
    private javax.swing.JComboBox<String> cmboStatus;
    private javax.swing.JComboBox<String> cmboSubCategory;
    private javax.swing.JComboBox<String> cmboWarranty;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblIconAddCustomer;
    private javax.swing.JLabel lblIconBrandPopUp;
    private javax.swing.JLabel lblIconInvoice;
    private javax.swing.JLabel lblIconProduct;
    private javax.swing.JLabel lblIconPurchase;
    private javax.swing.JLabel lblIconSubcategoryPopUp;
    private javax.swing.JLabel lblIconSupplier;
    private javax.swing.JLabel lblIconcategoryPopUp;
    private javax.swing.JLabel lblJnElectrical_Name;
    private javax.swing.JLabel lblJnelectrical_logo;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlInvoice;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlPurchasing;
    private javax.swing.JPanel pnlSuppler;
    private javax.swing.JPanel tbPaneCustomer;
    private javax.swing.JPanel tbPaneInvoice;
    private javax.swing.JPanel tbPaneProduct;
    private javax.swing.JPanel tbPaneSupplier;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblPrice_qty;
    private javax.swing.JTable tblProductList;
    private javax.swing.JTable tblPurchaseOrder;
    private javax.swing.JTable tblSupplierList;
    private javax.swing.JTextField txtBuyingQty;
    private javax.swing.JTextField txtCusAddLn1;
    private javax.swing.JTextField txtCusAddLn2;
    private javax.swing.JTextField txtCusCntctNo;
    private javax.swing.JTextField txtCusFName;
    private javax.swing.JTextField txtCusLName;
    private javax.swing.JTextField txtCustomerId;
    private javax.swing.JTextField txtCustomerNic;
    private javax.swing.JTextField txtDamageQty;
    private javax.swing.JTextField txtInvoice_brand;
    private javax.swing.JTextField txtInvoice_category;
    private javax.swing.JTextField txtInvoice_customerNIC;
    private javax.swing.JTextField txtInvoice_customerName;
    private javax.swing.JTextField txtInvoice_discount;
    private javax.swing.JTextField txtInvoice_item;
    private javax.swing.JTextField txtInvoice_qty;
    private javax.swing.JTextField txtMsrp;
    private javax.swing.JTextField txtPOrderCapacity;
    private javax.swing.JTextField txtPOrderCategory;
    private javax.swing.JTextField txtPOrderNo;
    private javax.swing.JTextField txtPOrderSupplierfName;
    private javax.swing.JTextField txtPOrderSupplierlName;
    private javax.swing.JTextField txtP_Brandname;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSupAdd1;
    private javax.swing.JTextField txtSupAdd2;
    private javax.swing.JTextField txtSupContact;
    private javax.swing.JTextField txtSupId;
    private javax.swing.JTextField txtSupfName;
    private javax.swing.JTextField txtSuplName;
    private javax.swing.JTextField txtUnitCost;
    private javax.swing.JTextField txtmodel;
    // End of variables declaration//GEN-END:variables
}
