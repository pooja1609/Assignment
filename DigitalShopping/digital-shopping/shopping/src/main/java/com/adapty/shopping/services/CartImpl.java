package com.adapty.shopping.services;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapty.shopping.entities.Cart;
import com.adapty.shopping.entities.Product;
import com.adapty.shopping.entities.STATUS;
import com.adapty.shopping.repositories.CartRepository;
//import com.adapty.shopping.repositories.ProductRepository;
import com.adapty.shopping.repositories.ProductRepository;


@Service
public class CartImpl implements CartInterface {
    @Autowired
    private ProductRepository repoObj;
    
    @Autowired
    private CartRepository cartRepo;

    public ArrayList findAllCarts(){
        ArrayList list = new ArrayList();
        float totalPrice=0.0f;
        List<Cart> c1 = cartRepo.findAll();
        for (int i = 0; i < c1.size(); i++) {
            Optional<Product> p1 = repoObj.findById(c1.get(i).getProductId());
            totalPrice = totalPrice + c1.get(i).getCartItemQty() * p1.get().getProductPrice();
            System.out.println("Total Price = "+totalPrice);
            list.add(Arrays.asList(c1.get(i).getCartItemId(),c1.get(i).getCartItemQty(), p1.get().getProductName(),p1.get().getProductImage(),p1.get().getProductPrice(),p1.get().getProductCategory(),p1.get().getProductDescription(),p1.get().getProductStatus(),totalPrice));  
        
        }
        
        return list;
    }

    public Optional<Cart> findByCartId(String cartId){
        
        return cartRepo.findById(cartId);
    }

    public String addToCart(Cart cartObj){
    
        Optional<Product> obj = repoObj.findById(cartObj.getProductId());
        if(obj.isPresent()){
        if(obj.get().getProductStatus()==STATUS.ACTIVE){
            cartRepo.save(cartObj);
            return "Added Successfully";
        }
        else{
            return "Product no longer exist";
    }
        }else{
            return "Does not exist";
        }
        
}

    public String deleteByCartItemId(String cartItemId){
        cartRepo.deleteById(cartItemId);
        return "object deleted";
    }

    public Cart updateByProductId(Cart cartObj){
        if(cartObj.getCartItemId()== null){
            return null;
        }
        else{
            Optional<Cart> c1 = cartRepo.findById(cartObj.getCartItemId());
            c1.get().setCartItemQty(cartObj.getCartItemQty());
            c1.get().setProductId(cartObj.getProductId());
            cartRepo.deleteById(cartObj.getCartItemId());
            return cartRepo.save(cartObj);
        }

    }

    public String deleteByProductId(String productId){
        cartRepo.deleteByProductId(productId);
        return "Deleted";
}

    // public float findPriceByProductId(){
    //     float totalPrice=0.0f;
    //     List<Cart> c1 = cartRepo.findAll();
    //     for (int i = 0; i < c1.size(); i++) {
    //         Optional<Product> p1 = repoObj.findById(c1.get(i).getProductId());
    //         totalPrice = totalPrice + c1.get(i).getCartItemQty() * p1.get().getProductPrice(); 
              
    //     }
    //     //Optional<List<Cart>> c1 = cartRepo.findById(obj.getCartItemId());
    //     return totalPrice;
    // }


}
