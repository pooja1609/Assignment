package com.adapty.shopping.services;

import java.util.List;
import java.util.Optional;

import com.adapty.shopping.entities.CATEGORY;
import com.adapty.shopping.entities.Product;

public interface ProductInterface {

    public List<Product> findAllProducts();

    public Optional<Product> findByProductId(Product obj);

    public Optional<List<Product>> findByProductCategory(CATEGORY productCategory);

    //Post
    public String addProduct(Product productObj);

    //Put
    public Product updateByProductId(Product productObj);

    public String deleteByProductId(Product productObj);

   
}
