package com.taylor.taylorapp.model;



import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.entities.Fabrics;
 
public class StorePFInfo {
    private String code;
    private String name;
    private double price;
    private String fcode;
    private String fname;
    private double fprice;
    private Integer quantityset;
    
    
    private StorePFInfo productInfo;
    
 
    public StorePFInfo() {
    }
 
    public StorePFInfo(Product product, Fabrics fabric) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.fcode = fabric.getCode();
        this.fname = fabric.getName();
        this.fprice = fabric.getPrice();
        
    }
    public StorePFInfo findbycode(String codes) {
    	
    	if(productInfo.getCode().equalsIgnoreCase(codes))
    	{
        return productInfo;
    	}
		return null;
        
    }
 
    // Using in JPA/Hibernate query
    /*
    public StorePFInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
 */
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public double getFprice() {
		return fprice;
	}

	public void setFprice(double fprice) {
		this.fprice = fprice;
	}

    public Integer getQuantityset() {
        return quantityset;
    }

    public void setQuantityset(Integer quantity) {
        this.quantityset = quantity;
    }
	
}

