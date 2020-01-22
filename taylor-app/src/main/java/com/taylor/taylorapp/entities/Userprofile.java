package com.taylor.taylorapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="userprofile")
public class Userprofile {
	


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	
	@Column(table="userprofile")
    private String username;
	
	@Column(table="userprofile")
    private String firstname;
    
    @Column(table="userprofile")
    private String lastname;
    
    @Column(table="userprofile")
    private String email;
    
    @Column(table="userprofile")
    private String address;
    
    @Column(table="userprofile")
    private String phone;
    
    @Column(table="userprofile")
    private String sex;
    
    @Column(table="userprofile")
    private String stateoforigin;
    
    @Column(table="userprofile")
    private String dateofbirth;
    
    @Column(table="userprofile")
    private String city;
    
    @Column(table="userprofile")
    private String country;
    
    @Column(table="userprofile")
    private String postalcode;
    
    @Column(table="userprofile")
    private String aboutme;

    
    public Long getId() {
		return id;
	}

    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStateoforigin() {
		return stateoforigin;
	}

	public void setStateoforigin(String stateoforigin) {
		this.stateoforigin = stateoforigin;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public void setprofiles(String username, String firstname,String lastname,String email,String address,String phone,String sex,String stateoforigin,String dateofbirth,String country,String postalcode,String city,String aboutme) {

		this.username = username;
		this.firstname = firstname;
	    this.lastname = lastname;
	    this.email = email;
	    this.address = address;
	    this.phone = phone;
	    this.sex = sex;
	    this.stateoforigin = stateoforigin;
	    this.dateofbirth = dateofbirth;
	    this.city = city;
	    this.country = country;
	    this.postalcode = postalcode;
		this.aboutme = aboutme;
		
	}
	
	public void getprofiles(String x) {
		
		if(username.equals(x)) {

        getFirstname();
        getLastname();
        getEmail();
        getAddress();
        getPhone();
        getSex();
        getStateoforigin();
        getDateofbirth();
        getCity();
        getCountry();
        getPostalcode();
        getAboutme();
		
		}
		
	}

}
