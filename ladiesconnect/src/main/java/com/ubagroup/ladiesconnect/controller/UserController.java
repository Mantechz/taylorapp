package com.ubagroup.ladiesconnect.controller;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.slf4j.*;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.client.RestTemplate;
import com.ubagroup.ladiesconnect.entities.Mentee;
import com.ubagroup.ladiesconnect.entities.Mentor;
import com.ubagroup.ladiesconnect.entities.User;
import com.ubagroup.ladiesconnect.entities.Roles;
import com.ubagroup.ladiesconnect.repository.MentorRepository;
import com.ubagroup.ladiesconnect.repository.MenteeRepository;
import com.ubagroup.ladiesconnect.repository.UserRepository;
import com.ubagroup.ladiesconnect.services.SecurityService;
import com.ubagroup.ladiesconnect.services.UserService;
import com.ubagroup.ladiesconnect.services.UserValidator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
    private UserRepository userService;
    
    @Autowired
    private MentorRepository mentor;
    @Autowired
    private MenteeRepository mentee;
	
	@Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthenticationManager authenticationManager;


   

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
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
    @GetMapping("/aunthenticate")
    public String aunthenticate(Model model) {
        model.addAttribute("userForm", new User());
        return "landingpage";
    }
	

    @PostMapping("/mentor")
    public String mentor(@ModelAttribute("mentorForm") Mentor mentorForm, BindingResult bindingResult, Model model) throws FileNotFoundException{
        
        mentor.save(mentorForm);
        String T = "Talent.Management@ubagroup.com";
        String emailMsgTxtANV2 = "Dear " + mentorForm.getFirstname();
        String AB4 = "Thank you for your interest in LadiesConnect.";
        String DB5 = "We have received your application and will get in touch with you.";
        String AB6 = "Best Regards,";
        String AB7 = "Talent Management";
        String from = T;
        String to = mentorForm.getEmail();

        Notification.GetPaymentNotification(emailMsgTxtANV2, "Mentorship Notification", AB4, DB5, AB6, AB7, to, from);

        return "redirect:/success";
    }

    @PostMapping("/mentee")
    public String mentee(@ModelAttribute("menteeForm") Mentee menteeForm, BindingResult bindingResult, Model model) throws FileNotFoundException{
        
        mentee.save(menteeForm);
        String T = "TalentManagement@ubagroup.com";
        String emailMsgTxtANV2 = "Dear " + menteeForm.getFirstname();
        String AB4 = "We confirm receipt of your LadiesConect registration.";
        String DB5 = "Please expect the details of your mentor and other guidelines.";
        String AB6 = "Best Regards,";
        String AB7 = "Talent Management";
        String from = T;
        String to = menteeForm.getEmail();

        Notification.GetPaymentNotification(emailMsgTxtANV2, "Mentorship Notification", AB4, DB5, AB6, AB7,to, from);


        return "redirect:/success";
    }

    @PostMapping("/aunthenticate")
    public String login(@ModelAttribute("userForm") User userForm, Model model, final  HttpServletRequest request) {

        System.out.println("Starting API");
        log.info("Starting API");
         final String username = userForm.getUsername();
         final String password = userForm.getPassword();
         log.info(password);
         log.info(username);

         final String api = "http://10.100.5.195/FormsServiceAPI/api/AD/AuthenticateUser";
         RestTemplate restTemplate = new RestTemplate();
        // String result = restTemplate.getForObject(api, String.class);
         final HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         JSONObject record = new JSONObject();
         record.put(username, username);
         record.put(password, password);
         System.out.println("API set");
         log.info("API Set");

         final HttpEntity<String> entity = new HttpEntity<String>(record.toString(), headers);
         System.out.println("API set");
         log.info("API Set");

         String response = restTemplate.postForObject(api, entity, String.class);

         System.out.println(response);

         log.info(response);
        log.info("Api logiin");
        User user = userService.findByUsernameIgnoreCase(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

         if( response.equals("true") && user != null){


             log.info(user.getUsername());
             log.info(user.getPassword());

             try {
                 String pass = encoder.encode(user.getPassword());
                 System.out.println(encoder.encode(user.getPassword()));
                 authWithoutPassword(user);
                 log.info("got heren 3");
                 request.login(user.getUsername(), user.getPassword());
             } catch (ServletException e) {
                 log.error("Error while login ", e);
             }
             return "landingpage";
         }

         else{
             log.info("Empty User");
//     	if (error != null)
//              model.addAttribute("error", "Your username and password is invalid.");
//
//
//          if (logout != null)
//              model.addAttribute("message", "You have been logged out successfully.");

         }

        return "loginpage";
        
    }
    @GetMapping(value = {"/","/login","loginpage"})
    public String loginin(Model model) {

        return "loginpage";
    }

    @GetMapping(value = {"/index", "landingpage"})
    public String welcome(Model model) {
        return "landingpage";
    }
    @GetMapping(value = {"/mentor"})
    public String mentor(Model model) {
        return "mentor";
    }
    @GetMapping(value = {"/mentee"})
    public String mentee(Model model) {
        return "mentee";
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

    public void authWithoutPassword(User user) {


        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
	
}