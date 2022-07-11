package com.adapty.shopping.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adapty.shopping.entities.CATEGORY;
import com.adapty.shopping.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    public Optional<List<Product>> findByProductCategory(CATEGORY productCategory);

   
    
}
