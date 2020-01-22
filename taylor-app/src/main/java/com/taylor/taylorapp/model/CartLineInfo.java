package com.taylor.taylorapp.model;


public class CartLineInfo {
 
  // private ProductInfo productInfo;
   private FabricsInfo fabricsInfo;
   private StorePFInfo productInfo;
   
   private int quantity;
 
   public CartLineInfo() {
       this.quantity = 0;
   }
 
  /* public ProductInfo getProductInfo() {
	   if(productInfo.getCode().isEmpty()) {
		   productInfo = new ProductInfo();
		   return productInfo;
	   }else {
       return productInfo; 
	   }
       
   } */
   public FabricsInfo getFabricsInfo() {
	   if(fabricsInfo.getCode().isEmpty()) { 
		   fabricsInfo = new FabricsInfo();
		   return fabricsInfo;
	   }else {
       return fabricsInfo; 
	   }
   }
   public StorePFInfo getProductInfo() {
	   if(productInfo.getCode().isEmpty()) { 
		   productInfo = new StorePFInfo();
		   return productInfo;
	   }else {
       return productInfo; 
	   }
   }
 /*
   public void setProductInfo(ProductInfo productInfo) {
       this.productInfo = productInfo;
   } */
   public void setFabricsInfo(FabricsInfo fabricsInfo) {
       this.fabricsInfo = fabricsInfo;
   }
   public void setProductInfo(StorePFInfo store) {
       this.productInfo = store;
   }
   public int getQuantity() {
       return quantity;
   }
   public int getQuantityF() {
       return quantity;
   }
 
   public void setQuantity(int quantity) {
       this.quantity = quantity;
   }
   public void setQuantityF(int quantity) {
       this.quantity = quantity;
   }
   public double getAmount() {
        double amount = this.productInfo.getPrice() * this.quantity; 
        amount += productInfo.getFprice();
        return amount;
   }
   public double getAmountF() {
       return this.productInfo.getFprice() * this.quantity;
   }
    
}
