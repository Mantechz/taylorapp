package com.taylor.taylorapp.services;

import com.taylor.taylorapp.entities.Userprofile;

public interface userprofiles {
	void saveprofile(Userprofile profile);
	Userprofile updateprofile(Userprofile profile, Long id);
	Userprofile findByUsername(String username);
	Object getAllprofilebyId(Long i);
	Long getId(String username);
	String getFirstname(String username);
	String getLastname(String username);
	String getEmail(String username);
	String getAddress(String username);
	String getPhone(String username);
	String getSex(String username);
	String getStateoforigin(String username);
	String getDateofbirth(String username);
	String getCity(String username);
	String getCountry(String username);
	String getPostalcode(String username);
	String getAboutme(String username);
	String getUsername(String username);

	 
}
