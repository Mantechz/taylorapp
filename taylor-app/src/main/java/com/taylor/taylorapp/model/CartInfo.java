package com.taylor.taylorapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

import org.springframework.beans.factory.annotation.Autowired;


import com.taylor.taylorapp.model.StorePFInfo;
 
public class CartInfo {
 
    private int orderNum;
 
    private CustomerInfo customerInfo;
 
    private List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
    
    @Autowired
    private StorePFInfo storeservice;
    
 
    public CartInfo() {
 
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
 
    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }
 
    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProductInfo().getCode().equals(code)) {
                return line;
            }
        }
        
        return null;
    }
    private CartLineInfo findLineByCodef(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getFabricsInfo().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }

    /*
    public void addProduct(ProductInfo productInfo, int quantity) {
        CartLineInfo line = this.findLineByCode(productInfo.getCode());
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProductInfo(productInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
    
    */
    public void addProduct(StorePFInfo productInfo, int quantity) {
        CartLineInfo line = this.findLineByCode(productInfo.getCode());
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProductInfo(productInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
    
    public void addFabrics(FabricsInfo fabricsInfo, int quantity, String code) {
        CartLineInfo line = this.findLineByCode(code);
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantityF(0);
            line.setFabricsInfo(fabricsInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantityF(newQuantity);
        }
    }
    
    public void addFabric(FabricsInfo fabricsInfo, int quantity, StorePFInfo storepf) {
    	
    	StorePFInfo line = storepf;
 
        /*
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantityF(0);
            line.setFabricsInfo(fabricsInfo);
            this.cartLines.add(line);
        } */
         	
          //  line.setQuantityF(0); 
         //   line.setFabricsInfo(fabricsInfo);
        	
        	 
             if (line != null) {
            	 line.setFcode(fabricsInfo.getCode());
             	line.setFname(fabricsInfo.getName());
             	line.setFprice(fabricsInfo.getPrice());
             }
     /*   	
        
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantityF(newQuantity);
        }
        */
    }public void addFabo(FabricsInfo fabricsInfo, int quantity, String code) {
    	
    	CartLineInfo line = this.findLineByCode(code);
 

        	
        	 
             if (line != null) {
            	 line.getProductInfo().setFcode(fabricsInfo.getCode());
             	line.getProductInfo().setFname(fabricsInfo.getName());
             	line.getProductInfo().setFprice(fabricsInfo.getPrice());
             }


    }
    
    public void validate() {
 
    }
 
    public void updateProduct(String code, int quantity) {
        CartLineInfo line = findLineByCode(code);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
    public void updateFabrics(String code, int quantity) {
        CartLineInfo line = findLineByCodef(code);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantityF(quantity);
            }
        }
    }
 
    public void removeProduct(ProductInfo productInfo) {
        CartLineInfo line = this.findLineByCode(productInfo.getCode());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }
    public double getAmountTotalF() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmountF();
        }
        return total;
    }
 
    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
        	System.out.println("sex");
            List<CartLineInfo> lines = cartForm.getCartLines(); 
            System.out.println(lines.size());
            for (CartLineInfo line : lines) { 
            	System.out.println("sex");
            //	System.out.println(lines.get(i));
               this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
            	
            }
        }
    }
        public void updateQuantityF(String code, int quantity) {
  

            CartLineInfo line = findLineByCode(code);
            
            System.out.print("code: ");
            System.out.print(code);
            System.out.println("quntity: ");
            System.out.println(quantity);
            if (line != null) {
                if (quantity <= 0) {
                    this.cartLines.remove(line);
                } else {
                    line.setQuantity(quantity);
                    this.updateProduct(code, quantity);
                }
            }
    }


 
}
