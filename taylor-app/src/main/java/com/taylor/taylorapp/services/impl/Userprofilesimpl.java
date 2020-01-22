package com.taylor.taylorapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taylor.taylorapp.entities.Userprofile;
import com.taylor.taylorapp.exceptions.ResourceNotFoundException;
import com.taylor.taylorapp.repository.userprofilesrepo;
import com.taylor.taylorapp.services.userprofiles;





@Service
public class Userprofilesimpl implements userprofiles{

	@Autowired
	private userprofilesrepo userprofilesrepo;
  
    public void saveprofile(Userprofile profile) {
    	userprofilesrepo.save(profile);
    }


	public Object getAllprofilebyId(Long id) {
		return userprofilesrepo.findById(id);
		}
	
	
	
    public  Long getId(String username) {
    	
    	return userprofilesrepo.findByUsername(username).getId();
		
	}
    
	public String getUsername(String username) {
		return userprofilesrepo.findByUsername(username).getUsername();
	}
    
    public String getFirstname(String username) {
    	
    	String firstname = userprofilesrepo.findByUsername(username).getFirstname();
		return firstname;
	}

	public String getLastname(String username) {
		String lastname = userprofilesrepo.findByUsername(username).getLastname();
		return lastname;
	}

	public String getEmail(String username) {
		String email = userprofilesrepo.findByUsername(username).getEmail();
		return email;
	}

	public String getAddress(String username) {
		String address = userprofilesrepo.findByUsername(username).getAddress();
		return address;
	}

	public String getPhone(String username) {
		String phone = userprofilesrepo.findByUsername(username).getPhone();
		return phone;
	}

	public String getSex(String username) {
		String sex = userprofilesrepo.findByUsername(username).getSex();
		return sex;
	}

	public String getStateoforigin(String username) {
		String stateoforigin = userprofilesrepo.findByUsername(username).getStateoforigin();
		return stateoforigin;
	}

	public String getDateofbirth(String username) {
		String dateofbirth = userprofilesrepo.findByUsername(username).getDateofbirth();
		return dateofbirth;
	}

	public String getCity(String username) {
		String city = userprofilesrepo.findByUsername(username).getCity();
		return city;
	}

	public String getCountry(String username) {
		String country = userprofilesrepo.findByUsername(username).getCountry();
		return country;
	}

	public String getPostalcode(String username) {
		String postalcode = userprofilesrepo.findByUsername(username).getPostalcode();
		return postalcode;
	}

	public String getAboutme(String username) {
		String aboutme = userprofilesrepo.findByUsername(username).getAboutme();
		return aboutme;
	}
	
    
    public Userprofile findByUsername(String username) {
        return userprofilesrepo.findByUsername(username);
    }
    
	public Userprofile updateprofile(Userprofile profile, Long id) {
		Userprofile profiles = userprofilesrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("profile","user",id));
		 
		 profiles.setFirstname(profile.getFirstname());
		 profiles.setLastname(profile.getLastname());
		 profiles.setEmail(profile.getEmail());
		 profiles.setAddress(profile.getAddress());
		 profiles.setPhone(profile.getPhone());
		 profiles.setSex(profile.getSex());
		 profiles.setStateoforigin(profile.getStateoforigin());
		 profiles.setDateofbirth(profile.getDateofbirth());
		 profiles.setCity(profile.getCity());
		 profiles.setCountry(profile.getCountry());
		 profiles.setPostalcode(profile.getPostalcode());
		 profiles.setAboutme(profile.getAboutme());
		 
		 return userprofilesrepo.save(profiles);
					
	}

}


