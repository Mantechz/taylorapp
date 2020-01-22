package com.taylor.taylorapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taylor.taylorapp.entities.User;
import com.taylor.taylorapp.services.SecurityService;
import com.taylor.taylorapp.services.UserService;
import com.taylor.taylorapp.services.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserValidator userValidator;
    
    /*
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
		this.userService = userService;
		this.securityService = securityService;
		this.userValidator = userValidator;
	}
    */

	@GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }
	

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        
    	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/success";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
    	if (error != null) 
            model.addAttribute("error", "Your username and password is invalid.");


        if (logout != null) 
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
        
    }

    @GetMapping(value = {"/","/index"})
    public String welcome(Model model) {
        return "index";
    }
    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
    
	@RequestMapping("/category")
	public String category() {
		return "category";
	}
	
	@RequestMapping("/product")
	public String product() {
		return "product";
	}


	@RequestMapping("/cart")
	public String cart() {
		return "cart";
	}

	@RequestMapping("/checkout")
	public String checkouts() {
		return "checkout";
	}
	
}