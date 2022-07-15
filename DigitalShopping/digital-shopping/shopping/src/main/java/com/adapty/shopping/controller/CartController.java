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
import com.adapty.shopping.services.CartImpl;


@RequestMapping("/api/cart")
@RestController
public class CartController {
    
    @Autowired
    private CartImpl cartSrv;

    //To fetch all the cart data with total price
    @GetMapping(value = "")
    public List findAllCarts(){
        return cartSrv.findAllCarts();

    }

    //To fetch cart data by cartItemId
    @GetMapping(value = "{cartId}")
    public Optional<Cart> findByCartId(@PathVariable("cartId") String cartId){
        return cartSrv.findByCartId(cartId);
    }
    
    //To create new cart object
    @PostMapping(value="")
    public String addToCart(@RequestBody Cart cartObj){

        return cartSrv.addToCart(cartObj);
    } 
    
    //To delete cart data by giving cartItemId
    @DeleteMapping(value = "{cartItemId}")
    public String deleteByCartItemId(@PathVariable("cartItemId") String cartItemId){
        return cartSrv.deleteByCartItemId(cartItemId);
    }

    //To update data of existing cart object
    @PutMapping(value = "")
    public Cart updateByProductId(@RequestBody Cart cartObj){
        return cartSrv.updateByProductId(cartObj);
    }

    //To delete cart data by giving productId
    @DeleteMapping(value = "/delete/{productId}")
    public String deleteByProductId(@PathVariable("productId") String productId){
        return cartSrv.deleteByProductId(productId);

    }


}
