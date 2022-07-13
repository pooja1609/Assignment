package com.adapty.shopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adapty.shopping.entities.CATEGORY;
import com.adapty.shopping.entities.Product;
import com.adapty.shopping.services.ProductImpl;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductImpl srv;

    @GetMapping(value="")
    public List<Product> findAllProducts(){
        return srv.findAllProducts();
    }

    @GetMapping(value="{productId}")
    public Optional<Product> findByProductId(@PathVariable("productId") Product productId){
        return srv.findByProductId(productId);
    }

    @GetMapping(value="/find/{productCategory}")
    public Optional<List<Product>> findByProductCategory(@PathVariable("productCategory") CATEGORY productCategory){
        return srv.findByProductCategory(productCategory);
    }

    @PostMapping(value="")
    public String addProduct(@RequestBody Product productObj){
        return srv.addProduct(productObj);
    }

    @PutMapping(value = "")
    public Product updateByProductId(@RequestBody Product productObj){
        return srv.updateByProductId(productObj);
    }


    @DeleteMapping(value = "{productId}")
    public String deleteByProductId(@PathVariable("productId") Product productObj){
        return srv.deleteByProductId(productObj);
    }
}
