package com.taylor.taylorapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.taylor.taylorapp.bmi.BMICalculatorInMetric;
import com.taylor.taylorapp.entities.Userprofile;
import com.taylor.taylorapp.entities.Notification;
import com.taylor.taylorapp.repository.UserNotificationRepo;
import com.taylor.taylorapp.repository.userprofilesrepo;
import com.taylor.taylorapp.services.UserService;


@Controller
public class profilecontroller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Userprofile userprofile;
	
	@Autowired
	private com.taylor.taylorapp.services.userprofiles userprofiles;
	
	@Autowired
	private userprofilesrepo userrepo;
	
	@Autowired
	private BMICalculatorInMetric bmiIndex;
	@Autowired
	private UserNotificationRepo notifi;

	
	


	@RequestMapping("/update-profile")
	public String updateprofile() {
		return "update-profile";
	}
	@RequestMapping("/taylor-profile")
	public String taylorprofile() {
		return "tailor-profile";
	}

	
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
		return "dashboard";
	}
	
	@RequestMapping("/map")
	public String map(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		return "map";
	}
	@RequestMapping("/icons")
	public String icon(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
		return "icons";
	}
	
	@RequestMapping("/notifications")
	public String notifications(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
		String id = logname;
		try {
		String email = userprofiles.getEmail(id);
		
		List<Notification> notifications = notifi.findByEmail(email);
		
		
		model.addAttribute("notifications", notifications);  
		

		
		
		return "notifications";
		}catch(NullPointerException e) {
			
			return "redirect:/user";
		}
		
		
	}
	@RequestMapping("/rtl")
	public String rtl() {
		return "rtl";
	}
	
	@GetMapping("/user")
	public String user(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
			
			model.addAttribute("username", logname);
 

			
		return "user";
	}
	@RequestMapping("/typography")
	public String typography(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		String id = logname;
		try {
		String email = userprofiles.getEmail(id);
		
		List<Notification> notifications = notifi.findByEmail(email); 
		
		model.addAttribute("notifications", notifications);  
		model.addAttribute("username", logname);
		return "typography";
		}catch(NullPointerException e) {
			return "redirect:/user";
		}
	}
	@RequestMapping("/upgrade")
	public String upgrade(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		return "upgrade";
	}
	@RequestMapping("/tables")
	public String tables(Model model) throws Exception {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
        String bmindexing = bmiIndex.getBmifix();
		model.addAttribute("Sizer", bmindexing);

		

		return "tables";
	}

	@RequestMapping(value = { "/bmindex" }, method = RequestMethod.POST)
	public String bmivalues(Model model,  @RequestParam(value = "bmi", defaultValue = "") String bmi){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);

		String bmidex = bmi;
		try {

			bmiIndex.setBmifix(bmidex);

		} catch (Exception e) {
			e.printStackTrace();
		}


		return "redirect:/tables";

	}
	
	@RequestMapping("/user-profile")
	public String userprofile(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		
		
		
		
		
		model.addAttribute("username", logname);
		
		try {
		if(userprofiles.getUsername(logname).equalsIgnoreCase(logname)) {
		
		String id = logname;
	
		String firstname = userprofiles.getFirstname(id);
		String lastname = userprofiles.getLastname(id);
		String email = userprofiles.getEmail(id);
		String sex = userprofiles.getSex(id);
		String address = userprofiles.getAddress(id);
		String phone = userprofiles.getPhone(id);
		String stateoforigin = userprofiles.getStateoforigin(id);
		String dateofbirth = userprofiles.getDateofbirth(id);
		String city = userprofiles.getCity(id);
		String country = userprofiles.getCountry(id);
		String postalcode = userprofiles.getPostalcode(id);
		String aboutme = userprofiles.getAboutme(id);

		model.addAttribute("username", logname);
		model.addAttribute("firstname", firstname);
		model.addAttribute("lastname", lastname);
		model.addAttribute("email", email);
		model.addAttribute("sex", sex);
		model.addAttribute("address", address);
		model.addAttribute("phone", phone);
		model.addAttribute("stateoforigin", stateoforigin);
		model.addAttribute("dateofbirth", dateofbirth);
		model.addAttribute("city", city);
		model.addAttribute("country", country);
		model.addAttribute("postalcode", postalcode);
		model.addAttribute("aboutme", aboutme);
		model.addAttribute("id", userprofiles.getId(logname));
		
		}
		else {
			System.out.print("No user's profile found");
		
		}
	}
		catch(NullPointerException e) {
			System.out.print("Null pointer");
		}

		return "user-profile";
	}
	@PostMapping("/saveprofile")
	public String user(@ModelAttribute("profile") Userprofile profile, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		try {
		if (userprofiles.getUsername(logname) == null){
			userprofiles.saveprofile(profile);
		}
		else if(userprofiles.getUsername(logname).equalsIgnoreCase(logname)){
			Long id = userprofiles.getId(logname);
			
			userprofiles.updateprofile(profile, id);
			
		}
		}catch(NullPointerException e) {
			userprofiles.saveprofile(profile);
			System.out.print("Null pointer");
		}
    	
    	model.addAttribute("profileupdate", "Your profile has been updated successfully.");

        return "redirect:/user";	
		
	}
	
	@RequestMapping("/stores")
	public String stores(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		return "stores";
	}
	
	@RequestMapping("/mencategoriessection")
	public String categoriessection1(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		return "mencategoriessection";
	}
	
	@RequestMapping("/womencategoriessection")
	public String categoriessection2(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		return "womencategoriessection";
	}
	
}
