package com.taylor.taylorapp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;

import com.taylor.taylorapp.model.ProductInfo;
import com.taylor.taylorapp.pagination.PaginationResult;

public class PaginationResultProduct<E> {

	   private int totalRecords;
	   private int currentPage;
	   private List<ProductInfo> list;
	   private int maxResult;
	   private int totalPages;
	 
	   private int maxNavigationPage;
	 
	   private List<Integer> navigationPages;
	 
	   // @page: 1, 2, ..
	   public PaginationResultProduct(Page<ProductInfo> pagedResult, int pageNo,Integer maxNavigationPage, int pageSize, String sortBy) {
	      final int pageIndex = pageNo - 1 < 0 ? 0 : pageNo - 1;
	 
	      int fromRecordIndex = pageIndex * pageSize;
	      int maxRecordIndex = fromRecordIndex + pageSize;
	      
	   //   pagedResult.
	    //  ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
	      Iterator<ProductInfo> resultScroll = pagedResult.iterator();
	 
	      List<ProductInfo> results = new ArrayList<>();
	      List<ProductInfo> resultslist = new ArrayList<>();
	      
	 
	      if (pagedResult.isFirst()) {
	         // Scroll to position:

	    	  
	         if (pagedResult.hasNext()) {
	        	 while (pagedResult.hasNext()) {
	        		 ProductInfo record = resultScroll.next();
	        		 
	               results.add(record);
	               
	               
	               
	               
	         }
	 
	         // Go to Last record.
	         pagedResult.isLast();
	      } 
	 
	      // Total Records
	      this.totalRecords = pagedResult.getTotalPages();
	      this.currentPage = pageIndex + 1;
	      this.list = (List<ProductInfo>) results.get(currentPage);  
	      this.maxResult = pageSize;
	      this.totalRecords = pagedResult.getTotalPages();
	      
	 
	 
	      this.maxNavigationPage = maxNavigationPage;
	 
	      if (maxNavigationPage < totalPages) {
	         this.maxNavigationPage = maxNavigationPage;
	      }
	 
	      this.calcNavigationPages();
	      }
	   }
	 
	   private void calcNavigationPages() {
	 
	      this.navigationPages = new ArrayList<Integer>();
	 
	      int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
	 
	      int begin = current - this.maxNavigationPage / 2;
	      int end = current + this.maxNavigationPage / 2;
	 
	      // The first page
	      navigationPages.add(1);
	      if (begin > 2) {
	 
	         // Using for '...'
	         navigationPages.add(-1);
	      }
	 
	      for (int i = begin; i < end; i++) {
	         if (i > 1 && i < this.totalPages) {
	            navigationPages.add(i);
	         }
	      }
	 
	      if (end < this.totalPages - 2) {
	 
	         // Using for '...'
	         navigationPages.add(-1);
	      }
	      // The last page.
	      navigationPages.add(this.totalPages);
	   }
	 
	   public int getTotalPages() {
	      return totalPages;
	   }
	 
	   public int getTotalRecords() {
	      return totalRecords;
	   }
	 
	   public int getCurrentPage() {
	      return currentPage;
	   }
	 
	   public List<ProductInfo> getList() {
	      return list;
	   }
	 
	   public int getMaxResult() {
	      return maxResult;
	   }
	 
	   public List<Integer> getNavigationPages() {
	      return navigationPages;
	   }
	 
}
