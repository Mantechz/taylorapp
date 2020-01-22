package com.taylor.taylorapp.dao;

import java.io.IOException;
import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.form.ProductForm;
import com.taylor.taylorapp.model.ProductInfo;
import com.taylor.taylorapp.pagination.ProductDAORepo;
import com.taylor.taylorapp.repository.ProductRepository;
 
@Transactional
@Repository
public class ProductDAO<E> {
 
	@Autowired
	private ProductRepository productrepo;
	
	@Autowired
	private ProductDAORepo repository;

 
    public Product findProduct(String code) {
        try {
            Product sql = productrepo.findByCode(code);
            return sql;
        } catch (NoResultException e) {
            return null;
        }
    }
 
    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo();
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ProductForm productForm) {
 
        
        String code = productForm.getCode();
 
        Product product = null;
 
        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
 
        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        if (isNew) {
        	productrepo.save(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        
    }
 
    public Page<Product> queryProducts1(Pageable paging) {
        
        Page<Product> pagedResult = repository.findAll(paging);
        
        return pagedResult;
    }
 /*
    public PaginationResultProduct<ProductInfo> queryProducts(Integer pageNo, Integer pageSize,Integer maxNavigationPage, String sortBy) {
        return queryProducts1(pageNo, pageSize,Integer maxNavigationPage, sortBy);
    }
    */
   

}
