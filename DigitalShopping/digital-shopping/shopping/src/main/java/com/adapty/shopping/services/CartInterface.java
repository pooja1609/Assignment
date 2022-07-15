package com.adapty.shopping.services;
import java.util.List;
import java.util.Optional;
import com.adapty.shopping.entities.Cart;

public interface CartInterface {

    public List findAllCarts();

    public Optional<Cart> findByCartId(String cartId);

    public String addToCart(Cart cartObj);

    public String deleteByCartItemId(String cartItemId);

    public Cart updateByProductId(Cart cartObj);

    public String deleteByProductId(String productId);

}
