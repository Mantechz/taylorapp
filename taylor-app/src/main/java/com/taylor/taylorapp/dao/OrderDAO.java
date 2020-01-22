package com.taylor.taylorapp.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taylor.taylorapp.entities.Fabrics;
import com.taylor.taylorapp.entities.Notification;
import com.taylor.taylorapp.entities.Order;
import com.taylor.taylorapp.entities.OrderDetail;
import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.model.CartInfo;
import com.taylor.taylorapp.model.CartLineInfo;
import com.taylor.taylorapp.model.OrderDetailInfo;
import com.taylor.taylorapp.model.OrderInfo;
import com.taylor.taylorapp.pagination.PaginationResult;
import com.taylor.taylorapp.repository.OrderDetailRepo;
import com.taylor.taylorapp.repository.OrderDetailnfo;
import com.taylor.taylorapp.repository.OrderInfoRepo;
import com.taylor.taylorapp.repository.OrderRepository;
import com.taylor.taylorapp.repository.UserNotificationRepo;

@Transactional
@Repository
public class OrderDAO {


	
	@Autowired
	private OrderDetailnfo orderdetailinfo;
	@Autowired
	private OrderDetailRepo orderrepos;
	@Autowired
	private OrderRepository order;
	@Autowired
	UserNotificationRepo noterepo;
	


   @Autowired
   private ProductDAO productDAO;
   @Autowired
   private FabricDAO fabricDAO;
   @Autowired
   private OrderInfoRepo orderinfo;
   @Autowired
	private com.taylor.taylorapp.services.userprofiles userprofiles;

   
   /*
   private Integer getMaxOrderNum() {
       // check list is empty or not 
	   
	   Iterable<Order> orderno = order.findAll(); 
	   
	   List<Integer> ordernum = order.findTop3ByOrderNum();
       if (ordernum == null || ordernum.size() == 0) { 
           return Integer.MIN_VALUE; 
      } 
 
       // create a new list to avoid modification 
       // in the original list 
       List<Integer> sortedlist = new ArrayList<>(ordernum); 
 
       // sort list in natural order 
       Collections.sort(sortedlist); 
 
       // last element in the sorted list would be maximum 
       return sortedlist.get(sortedlist.size() - 1); 
   }
*/
   @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
   public void saveOrder(CartInfo cartInfo) {
       

       int orderNum = (int) (order.count() + 1);
       Order orders = new Order();

       orders.setId(UUID.randomUUID().toString());
       orders.setOrderNum(orderNum); 
       orders.setOrderDate(new Date());
       orders.setAmount(cartInfo.getAmountTotal()); 

       // CustomerInfo customerInfo = cartInfo.getCustomerInfo();  
       orders.setCustomerName(cartInfo.getCustomerInfo().getName());
       orders.setCustomerEmail(cartInfo.getCustomerInfo().getEmail());
       orders.setCustomerPhone(cartInfo.getCustomerInfo().getPhone());
       orders.setCustomerAddress(cartInfo.getCustomerInfo().getAddress());

       order.save(orders); 

       List<CartLineInfo> lines = cartInfo.getCartLines();
       

       for (CartLineInfo line : lines) {
    	   
           OrderDetail detail = new OrderDetail();
           detail.setId(UUID.randomUUID().toString());
           detail.setOrder(orders); 
           detail.setAmount(line.getAmount());
           detail.setPrice(line.getProductInfo().getPrice());
           detail.setQuanity(line.getQuantity());

           String code = line.getProductInfo().getCode(); 
           Product product = this.productDAO.findProduct(code);
           detail.setProduct(product);
           String coder = line.getProductInfo().getFcode();
           Fabrics fabrics = this.fabricDAO.findProduct(coder);
           detail.setFabrics(fabrics);
           detail.setOrdernum(orderNum);
           
           orderrepos.save(detail);
           
           Notification notes = new Notification();
           
           notes.setAmount(line.getAmount());
           notes.setEmail(cartInfo.getCustomerInfo().getEmail());
           notes.setPrice(line.getProductInfo().getPrice());
           notes.setProductname(line.getProductInfo().getName());
           notes.setProductprice(line.getProductInfo().getPrice());
           notes.setFabricsname(line.getProductInfo().getFname());
           notes.setFabricsprice(line.getProductInfo().getFprice());
           notes.setQuantity(line.getQuantity());
           noterepo.save(notes);
           

           
       }

       // Order Number!
       cartInfo.setOrderNum(orderNum);  
       // Flush 
       
   }

   // @page = 1, 2, ...
   public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
	   Query<OrderInfo> query = (Query<OrderInfo>) orderinfo.findByOrderNumOrderByOrderNumDesc();
       return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
   }

   public Order findOrder(String orderId) {
	   Optional<Order> result =  order.findById(orderId);
	   Order session = result.get();
       return session;
   }

   public OrderInfo getOrderInfo(String orderId) {
       Order order = this.findOrder(orderId);
       if (order == null) {
           return null;
       }
       return new OrderInfo(order.getId(), order.getOrderDate(), //
               order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
               order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
   }

   public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {


       
	   List<OrderDetailInfo> query = orderdetailinfo.findOrder(orderId);


       return query;
   }

}
