package com.taylor.taylorapp.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taylor.taylorapp.entities.Order;
import com.taylor.taylorapp.model.*;
import com.taylor.taylorapp.repository.OrderInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taylor.taylorapp.dao.FabricDAO;
import com.taylor.taylorapp.dao.OrderDAO;
import com.taylor.taylorapp.dao.ProductDAO;
import com.taylor.taylorapp.entities.Fabrics;
import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.form.CustomerForm;
import com.taylor.taylorapp.pagination.ProductDAORepo;
import com.taylor.taylorapp.repository.FabricDAORepo;
import com.taylor.taylorapp.utils.Utils;
import com.taylor.taylorapp.validator.CustomerFormValidator;
 
@Controller
@Transactional
public class MainController<E> {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);
 
   @Autowired
   private OrderDAO orderDAO;
 
   @Autowired
   private ProductDAO<E> productDAO;
   
   @Autowired
   private FabricDAO fabricDAO;
   
   @Autowired
   private FabricDAORepo fabricDAOrepo;
   @Autowired
   private ProductDAORepo productDAOrepo;
   @Autowired
   private CustomerFormValidator customerFormValidator;
   @Autowired
   private StorePFInfo storeservice;
    @Autowired
    private OrderInfo orderInfo;
    @Autowired
    private OrderInfoRepo orderInfoRepo;


   @InitBinder
   public void myInitBinder(WebDataBinder dataBinder) {
      Object target = dataBinder.getTarget();
      if (target == null) {
         return;
      }
      System.out.println("Target=" + target);
 
      // Case update quantity in cart
      // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
      if (target.getClass() == CartInfo.class) {
 
      }
 
      // Case save customer information.
      // (@ModelAttribute @Validated CustomerInfo customerForm)
      else if (target.getClass() == CustomerForm.class) {
         dataBinder.setValidator(customerFormValidator);
      }
 
   }
 
   @RequestMapping("/403")
   public String accessDenied() {
      return "/403";
   }
 
   @RequestMapping("/")
   public String home() {
      return "index";
   }
    @RequestMapping("/admin/orderlist")
    public String orderlist(Model model, //
                            @PageableDefault(page = 0, size = 20)
                            @SortDefaults({
                                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                                    @SortDefault(sort = "code", direction = Sort.Direction.ASC)
                            })
                                    Pageable pageable) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        model.addAttribute("username", logname);


        Page<Order> result = orderInfoRepo.findAll(pageable);
        if(result == null){
            return "/403";
        }
        else {


        }
        model.addAttribute("paginationresultcount", orderInfo.getOrderNum());
        model.addAttribute("paginationResult", result.getContent());
        model.addAttribute("paginationResulttotalPages", result);
        model.addAttribute("paginationResultnavigationPages", result.getNumber());



       return "orderList";
    }
 
   // Product List
   @RequestMapping({ "/productList" })
   public String listProductHandler(Model model, //
                                    @PageableDefault(page = 0, size = 20)
   		 @SortDefaults({
   			 @SortDefault(sort = "name", direction = Sort.Direction.DESC),
   			@SortDefault(sort = "code", direction = Sort.Direction.ASC)
   		 })
   			 Pageable pageable)
   {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
	   Page<Product> result = productDAOrepo.findAll(pageable);
    		/* queryProducts(page, 
            maxResult, maxNavigationPage, likeName); */

      model.addAttribute("paginationProducts", result.getContent());  
      model.addAttribute("paginationProductsres", result); 
      model.addAttribute("paginationProductsnav", result.getNumber()); 
      
      return "productList";
   }
   
   @RequestMapping({ "/fabricsLists" })
   public String listFabricsHandler(Model model, //
         @PageableDefault(page = 0, size = 20)
   		 @SortDefaults({
   			 @SortDefault(sort = "name", direction = Sort.Direction.DESC),
   			@SortDefault(sort = "code", direction = Sort.Direction.ASC)
   		 })
   			 Pageable pageable)
   {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
	   Page<Fabrics> result = fabricDAOrepo.findAll(pageable);

      model.addAttribute("paginationProducts", result.getContent());
      model.addAttribute("paginationProductsres", result); 
      model.addAttribute("paginationProductsnav", result.getNumber()); 
      
      return "fabricsList";
   }
   
   
   @RequestMapping({ "/fabricsList" })
   public String listFabricsHandlerproducts(Model model, @RequestParam(value = "codes") String codes, //
                                            @PageableDefault(page = 0, size = 20)
   		 @SortDefaults({
   			 @SortDefault(sort = "name", direction = Sort.Direction.DESC),
   			@SortDefault(sort = "code", direction = Sort.Direction.ASC)
   		 })
   			 Pageable pageable)
   {
	   String coder = codes;
 
	   Page<Fabrics> result = fabricDAOrepo.findAll(pageable);
    		/* queryProducts(page, 
            maxResult, maxNavigationPage, likeName); */  	
  	
      model.addAttribute("paginationProducts", result.getContent());
      model.addAttribute("paginationProductsres", result); 
      model.addAttribute("paginationProductsnav", result.getNumber()); 
      model.addAttribute("productcode", coder); 
      return "fabricsList";
   }
 
   @RequestMapping({ "/buyProduct" })
   public String listProductHandler(HttpServletRequest request, Model model, //
         @RequestParam(value = "code", defaultValue = "") String code) {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
 
      Product product = null;
      Fabrics fabrics = null;
      String fab = "0";
      if (code != null && code.length() > 0) {
         product = productDAO.findProduct(code);
         fabrics = fabricDAO.findProduct(fab);
         
      }
      if (product != null) {
 
         //
         CartInfo cartInfo = Utils.getCartInSession(request);
 
         StorePFInfo productInfo = new StorePFInfo(product,fabrics);
 
         cartInfo.addProduct(productInfo, 1);
      }
 
      return "redirect:/shoppingCart";
   }
   
   @RequestMapping({ "/buyFabrics" })
   public String listFabricsHandler(HttpServletRequest request, Model model, //
                                    @RequestParam(value = "code", defaultValue = "") String code, @RequestParam(value = "codes") String codes) {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
	 
	      Fabrics fabrics = null;
	      
	      System.out.println(codes);
	     
	      String coder = removelastcharacter(code);
	      
	      if (code != null && code.length() > 0) {
	    	  fabrics = fabricDAO.findProduct(coder);
	      }
	      if (fabrics != null) {
	 
	         //
	         CartInfo cartInfo = Utils.getCartInSession(request);
	       //  StorePFInfo storepf = storeservice.findbycode(codes);
	         
	 
	         FabricsInfo fabricsInfo = new FabricsInfo(fabrics);
	 
	         // cartInfo.addFabric(fabricsInfo, 1,storepf);
	         cartInfo.addFabo(fabricsInfo, 1,codes);
	     //    System.out.println(storepf);
	         
	      }
	      
	 
	      return "redirect:/shoppingCart";
	   }
 
   @RequestMapping({ "/shoppingCartRemoveProduct" })
   public String removeProductHandler(HttpServletRequest request, Model model, //
         @RequestParam(value = "code", defaultValue = "") String code) {
      Product product = null;
      if (code != null && code.length() > 0) {
         product = productDAO.findProduct(code);
      }
      if (product != null) {
 
         CartInfo cartInfo = Utils.getCartInSession(request);
 
         ProductInfo productInfo = new ProductInfo(product);
 
         cartInfo.removeProduct(productInfo);
 
      }
 
      return "redirect:/shoppingCart";
   }

   // POST: Update quantity for product in cart
   @PostMapping("/shoppingCart")
   public String shoppingCartUpdateQty(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("cartForm") CartInfo cartForm) {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);

       CartInfo cartInfo = Utils.getCartInSession(request);
       model.addAttribute("size", cartInfo.getCartLines().size());
       log.info("size " + cartInfo.getCartLines().size());


       try{
           cartInfo.updateQuantity(cartForm);

       }catch (Exception e) {
           return "redirect:/productList";
       }
      return "redirect:/shoppingCart";
   }


    @PostMapping("/updatecart")
   public String shoppingCartUpdateQtyf(HttpServletRequest request, //
         Model model, @RequestParam(value = "code", defaultValue = "") String code,
         @RequestParam(value = "qnty",required = false) String qnty) {

	    log.info("Quantity " + qnty);
	   
     CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        }

	 
      //String codes = removelastcharacter(code);

      int quanty = Integer.parseInt(qnty);
     
      cartInfo.updateQuantityF(code, quanty);
 
      return "redirect:/shoppingCart";
   }
 
   // GET: Show cart.
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
   public String shoppingCartHandler(HttpServletRequest request, Model model) {
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
		model.addAttribute("username", logname);
		
      CartInfo myCart = Utils.getCartInSession(request);
       model.addAttribute("size", myCart.getCartLines().size());
       log.info("size " + myCart.getCartLines().size());
      
      model.addAttribute("cartForm", myCart);  
      return "shoppingCart";
   }
   @RequestMapping(value = { "/shoppingCartfabrics" }, method = RequestMethod.GET)
   public String shoppingCartHandlerF(HttpServletRequest request, Model model) {
      CartInfo myCart = Utils.getCartInSession(request);
      
      
      model.addAttribute("cartForm", myCart); 
      return "shoppingCartfabrics";
   }
 
   // GET: Enter customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
   public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
      CartInfo cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      }
      CustomerInfo customerInfo = cartInfo.getCustomerInfo();
 
      CustomerForm customerForm = new CustomerForm(customerInfo);
 
      model.addAttribute("customerForm", customerForm);
 
      return "shoppingCartCustomer";
   }
 
   // POST: Save customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
   public String shoppingCartCustomerSave(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {
 
      if (result.hasErrors()) {
         customerForm.setValid(false);
         // Forward to reenter customer info.
         return "shoppingCartCustomer";
      }
 
      customerForm.setValid(true);
      CartInfo cartInfo = Utils.getCartInSession(request);
      CustomerInfo customerInfo = new CustomerInfo(customerForm);
      cartInfo.setCustomerInfo(customerInfo);
 
      return "redirect:/shoppingCartConfirmation";
   }
 
   // GET: Show information to confirm.
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
      CartInfo cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo == null || cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      } else if (!cartInfo.isValidCustomer()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      model.addAttribute("myCart", cartInfo); 
 
      	 return "shoppingCartConfirmation";
   }
 
   // POST: Submit Cart (Save)
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
 
   public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
	   
      CartInfo cartInfo = Utils.getCartInSession(request);
      Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String logname = loggedInUser.getName();
 
      if (cartInfo.isEmpty()) {
    	  
 
         return "redirect:/shoppingCart";
      } else if (logname.isEmpty()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      try {
    	  
    	// int ordernum = cartInfo.getOrderNum()+1;
    	// cartInfo.setOrderNum(ordernum);
        orderDAO.saveOrder(cartInfo);  
         
      } catch (Exception e) {
    	 int x = cartInfo.getOrderNum();
    	 System.out.println("Order num is: ");
    	 System.out.println(x);
    	 System.out.println(" ");
    	 return "redirect:/shoppingCartCustomer";
   //      return "shoppingCartConfirmation";
      }
 
      // Remove Cart from Session.
      Utils.removeCartInSession(request);
 
      // Store last cart.
      Utils.storeLastOrderedCartInSession(request, cartInfo);
 
      return "redirect:/shoppingCartFinalize";
   }
 
   @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
   public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
      CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
      if (lastOrderedCart == null) {
         return "redirect:/shoppingCart";
      }
      model.addAttribute("lastOrderedCart", lastOrderedCart);
      return "shoppingCartFinalize";
   }
 
   @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
   public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
         @RequestParam("code") String code) throws IOException {
      Product product = null;
      if (code != null) {
         product = this.productDAO.findProduct(code);
      }
      if (product != null && product.getImage() != null) {
         response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
         response.getOutputStream().write(product.getImage());
      }
      response.getOutputStream().close();
   }
   @RequestMapping(value = { "/productImagef" }, method = RequestMethod.GET)
   public void fabricsImage(HttpServletRequest request, HttpServletResponse response, Model model,
         @RequestParam("code") String code) throws IOException {
      Fabrics product = null;
      if (code != null) {
         product = this.fabricDAO.findProduct(code);
      }
      if (product != null && product.getImage() != null) {
         response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
         response.getOutputStream().write(product.getImage());
      }
      response.getOutputStream().close();
   }
   public static String removelastcharacter(String str) {
	   String result = null;
	   if((str != null) && (str.length() > 0)) {
		   result = str.substring(0, str.length() - 1);
	   }
	   return result;
   }
 
}
