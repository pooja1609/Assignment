package com.ServiceTest;

//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adapty.shopping.entities.Cart;
import com.adapty.shopping.repositories.CartRepository;
import com.adapty.shopping.services.CartImpl;

@ExtendWith(MockitoExtension.class)
public class TestCartService {

    @InjectMocks
    CartImpl srvObj;

    @Mock
    CartRepository repoObj;


    @Test
    public void findByCartId(){
        Cart c1 = new Cart("C1", 2, "E101");
        when(repoObj.findById("C1")).thenReturn(Optional.of(c1)); 
        Optional<Cart> c2 = srvObj.findByCartId(c1.getCartItemId());
        assertEquals(c1.getCartItemId(), c2.get().getCartItemId());
    }
    
    @Test
    public void addToCart(){
        Cart c1 = new Cart("C1", 2, "E101");
        repoObj.save(c1);
        verify(repoObj,times(1)).save(c1);
        

    }

    @Test
    public void updateByProductId()
    {
        Cart c1 = new Cart("C1", 2, "E101");
        c1.setCartItemQty(3);
        c1.setProductId("E102");
        Assertions.assertThat(c1.getCartItemQty()).isEqualTo(3);
        Assertions.assertThat(c1.getProductId()).isEqualTo("E102");

    }

    @Test
    public void deleteByCartItemId(){
    Cart c1 = new Cart("C1", 2, "E101");

    //when(repoObj.findById("C1")).thenReturn(Optional.of(c1));
    String msg=srvObj.deleteByCartItemId(c1.getCartItemId());
    assertEquals("object deleted", msg);
}

    @Test
    public void deleteByProductId(){
        Cart c1 = new Cart("C1", 2, "E101");
        String msg = srvObj.deleteByProductId(c1.getProductId());
        assertEquals("Deleted",msg);
    }
}
