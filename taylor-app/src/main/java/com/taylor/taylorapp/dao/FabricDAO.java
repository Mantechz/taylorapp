package com.taylor.taylorapp.dao;

import java.io.IOException;
import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taylor.taylorapp.entities.Fabrics;
import com.taylor.taylorapp.form.FabricForm;
import com.taylor.taylorapp.model.ProductInfo;
import com.taylor.taylorapp.pagination.ProductDAORepo;
import com.taylor.taylorapp.repository.FabricRepository; 
import com.taylor.taylorapp.repository.FabricDAORepo;


public class FabricDAO {
	@Autowired
	private FabricRepository fabricrepo;
	
	@Autowired
	private FabricDAORepo repository;

 
    public Fabrics findProduct(String code) {
        try {
        	Fabrics sql = fabricrepo.findByCode(code);
            return sql;
        } catch (NoResultException e) {
            return null;
        }
    }
 
    public ProductInfo findProductInfo(String code) {
    	Fabrics product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo();
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(FabricForm fabricForm) {
 
        
        String code = fabricForm.getCode();
 
        Fabrics fabrics = null;
 
        boolean isNew = false;
        if (code != null) {
        	fabrics = this.findProduct(code);
        }
        if (fabrics == null) {
            isNew = true;
            fabrics = new Fabrics();
            fabrics.setCreateDate(new Date());
        }
        fabrics.setCode(code);
        fabrics.setName(fabricForm.getName());
        fabrics.setPrice(fabricForm.getPrice());
 
        if (fabricForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = fabricForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
            	fabrics.setImage(image);
            }
        }
        if (isNew) {
        	fabricrepo.save(fabrics);
        }
        // If error in DB, Exceptions will be thrown out immediately
        
    }
 
    public Page<Fabrics> queryProducts1(Pageable paging) {
        
        Page<Fabrics> pagedResult = repository.findAll(paging);
        
        return pagedResult;
    }

}
