package com.taylor.taylorapp.form;

import org.springframework.web.multipart.MultipartFile;

import com.taylor.taylorapp.entities.Fabrics;

public class FabricForm {

    private String code;
    private String name;
    private double price;
 
    private boolean newFabric = false;
 
    // Upload file.
    private MultipartFile fileData;
 
    public FabricForm() {
        this.newFabric= true;
    }
 
    public FabricForm(Fabrics fabrics) {
        this.code = fabrics.getCode();
        this.name = fabrics.getName();
        this.price = fabrics.getPrice();
    }
 
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
 
    public MultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewFabric() {
        return newFabric;
    }
 
    public void setNewFabric(boolean newFabric) {
        this.newFabric = newFabric;
    }
}
