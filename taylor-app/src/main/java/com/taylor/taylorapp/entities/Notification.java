package com.taylor.taylorapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Notification")
public class Notification implements Serializable {
	
private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int i) {
		this.quantity = i;
	}

	@Column(name = "email", length = 50,  nullable = false)
	private String email;
	
	@CreationTimestamp
	private LocalDateTime createdDateTime;


	@UpdateTimestamp
	private LocalDateTime lastupdatedTime;
    
    @Column(name = "amount", length = 50,  nullable = false)
    private double amount;
    @Column(name = "price", length = 50,  nullable = false)
    private double price;
    @Column(name = "quantity", length = 50,  nullable = false)
    private int quantity;
    @Column(name = "productname", length = 50,  nullable = false)
    private String productname;
    public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getProductprice() {
		return productprice;
	}
	public void setProductprice(double d) {
		this.productprice = d;
	}
	public String getFabricsname() {
		return fabricsname;
	}
	public void setFabricsname(String fabricsname) {
		this.fabricsname = fabricsname;
	}

	@Column(name = "productprice", length = 50,  nullable = false)
    private double productprice;
    @Column(name = "fabricsname", length = 50,  nullable = false)
    private String fabricsname;
    @Column(name = "fabricsprice", length = 50,  nullable = false)
    private double fabricsprice;

	public double getFabricsprice() {
		return fabricsprice;
	}
	public void setFabricsprice(double d) {
		this.fabricsprice = d;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public LocalDateTime getLastupdatedTime() {
		return lastupdatedTime;
	}
	public void setLastupdatedTime(LocalDateTime lastupdatedTime) {
		this.lastupdatedTime = lastupdatedTime;
	}
	   

}
