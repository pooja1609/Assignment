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
import com.adapty.shopping.repositories.ProductRepository;


@Service
public class CartImpl implements CartInterface {
    @Autowired
    private ProductRepository repoObj;
    
    @Autowired
    private CartRepository cartRepo;

    public List findAllCarts(){
        List list = new ArrayList();
        float totalPrice=0.0f;
        List<Cart> c1 = cartRepo.findAll();
        for (int i = 0; i < c1.size(); i++) {
            Optional<Product> p1 =repoObj.findById(c1.get(i).getProductId());
            totalPrice = totalPrice + c1.get(i).getCartItemQty() * p1.get().getProductPrice();
            System.out.println("Total Price = "+totalPrice);
            list.add(Arrays.asList("CartItemId: "+c1.get(i).getCartItemId(),"CartItemQty: "+c1.get(i).getCartItemQty(),"CartProductName: "+ p1.get().getProductName(),"ProductImage: "+p1.get().getProductImage(),"ProductPrice: "+p1.get().getProductPrice(),"ProductCategory: "+ p1.get().getProductCategory(),"ProductDiscription: "+ p1.get().getProductDescription(),"ProductStatus: "+ p1.get().getProductStatus(),"TotlaPrice:"+ totalPrice));  
        
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


}
