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

import com.adapty.shopping.entities.Cart;

//import com.adapty.shopping.entities.Product;
import com.adapty.shopping.services.CartImpl;
//import com.adapty.shopping.services.ProductImpl;

//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/cart")
@RestController
public class CartController {
    
    @Autowired
    private CartImpl cartSrv;

    @GetMapping(value = "")
    public List<Cart> findAllCarts(){
        return cartSrv.findAllCarts();

    }

    @GetMapping(value = "{cartId}")
    public Optional<Cart> findByCartId(@PathVariable("cartId") String cartId){
        return cartSrv.findByCartId(cartId);
    }
    
    @PostMapping(value="")
    public String addToCart(@RequestBody Cart cartObj){

        return cartSrv.addToCart(cartObj);
    } 
    
    @DeleteMapping(value = "{cartItemId}")
    public String deleteByCartItemId(@PathVariable("cartItemId") String cartItemId){
        return cartSrv.deleteByCartItemId(cartItemId);
    }

    @PutMapping(value = "")
    public Cart updateByProductId(@RequestBody Cart cartObj){
        return cartSrv.updateByProductId(cartObj);
    }

    @DeleteMapping(value = "/delete/{productId}")
    public String deleteByProductId(@PathVariable("productId") String productId){
        return cartSrv.deleteByProductId(productId);

    }


}
