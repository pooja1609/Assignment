package com.adapty.shopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapty.shopping.entities.CATEGORY;
import com.adapty.shopping.entities.Product;
import com.adapty.shopping.entities.STATUS;
import com.adapty.shopping.repositories.ProductRepository;

@Service
public class ProductImpl implements ProductInterface {
    
    @Autowired
    private ProductRepository repoObj;

    public List<Product> findAllProducts(){
       
        return repoObj.findAll();
    
    }

    public Optional<Product> findByProductId(Product obj){
        //Optional<Product> ret = repoObj.findById(obj.getProductId());
        if(obj.getProductStatus() == STATUS.ACTIVE){
        return repoObj.findById(obj.getProductId());}
        else{
        return null;
    }
    }

    public Optional<List<Product>> findByProductCategory(CATEGORY productCategory){
        return repoObj.findByProductCategory(productCategory);
    }

    public String addProduct(Product productObj){
        repoObj.save(productObj);
        return "Product Added";
    }

    public Product updateByProductId(Product productObj){
        if(productObj.getProductId() == null){
            return productObj;
        }
        else{
            Optional<Product> p1 = repoObj.findById(productObj.getProductId());
    
            p1.get().setProductCategory(productObj.getProductCategory());
            p1.get().setProductDescription(productObj.getProductDescription());
            p1.get().setProductName(productObj.getProductName());
            p1.get().setProductImage(productObj.getProductImage());
            p1.get().setProductPrice(productObj.getProductPrice());
            p1.get().setProductStatus(productObj.getProductStatus());
    
            repoObj.deleteById(productObj.getProductId());
            return repoObj.save(p1.get());
            
        }
    }

    public String deleteByProductId(Product productObj){
        if(productObj.getProductId() == null) {
            return "Enter a Valid Id";
        }
        else{
            Optional<Product> d1 = repoObj.findById(productObj.getProductId());
            if(d1.get().getProductStatus() == STATUS.ACTIVE){
                
                d1.get().setProductStatus(STATUS.INACTIVE);
                //repoObj.deleteById(productObj.getProductId());
                repoObj.save(d1.get());
                return "Object Deleted Successfully";
             
            }
            else{
                return "Object does not exists";
            }
       }
    }

   


}
