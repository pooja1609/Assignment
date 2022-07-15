package com.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adapty.shopping.entities.CATEGORY;
import com.adapty.shopping.entities.Product;
import com.adapty.shopping.entities.STATUS;
import com.adapty.shopping.repositories.ProductRepository;
import com.adapty.shopping.services.ProductImpl;

@ExtendWith(MockitoExtension.class)
public class TestProductService {

    @InjectMocks
    ProductImpl srvObj;

    @Mock
    ProductRepository repoObj;
    
    @Test
    public void findAllProducts(){
        List<Product> lst = new LinkedList<Product>();

        Product p1 = new Product("E101", "T-shirt", CATEGORY.CLOTHS, "xgfsvdbvn", 199.9f, "XXL", STATUS.ACTIVE);
        Product p2 = new Product("E102", "Mobile", CATEGORY.ELECTRONICS, "dfevnb", 15000.5f, "os:android", STATUS.ACTIVE);

        lst.add(p1);
        lst.add(p2);

        when(repoObj.findAll()).thenReturn(lst);

        List<Product> newList = srvObj.findAllProducts(); 
            
        assertEquals(2, newList.size());
        verify(repoObj, times(1)).findAll();
    }

    @Test
    public void findByProductId(){
    //List<Product> lst = new LinkedList<Product>();
        Product p1 = new Product("E101", "Tshirt", CATEGORY.CLOTHS, "sdxcvhjn", 234.2f, "cdcbj", STATUS.ACTIVE);
        //repoObj.save(p1);
        when(repoObj.findById("E101")).thenReturn(Optional.of(p1));
        
        Optional<Product> p2 = srvObj.findByProductId(p1);
        assertEquals(STATUS.ACTIVE, p2.get().getProductStatus());
        assertEquals(p1.getProductId(),p2.get().getProductId());
        // assertEquals(p1.getProductName(), p2.get().getProductName());
        // assertEquals(p1.getProductCategory(), p2.get().getProductCategory());
        // assertEquals(p1.getProductImage(),p2.get().getProductImage());
        // assertEquals(p1.getProductPrice(), p2.get().getProductPrice());
        // assertEquals(p1.getProductDescription(), p2.get().getProductDescription());
        // assertEquals(p1.getProductStatus(), p2.get().getProductStatus());
        //verify(repoObj, times(1));
    }

    @Test
    public void addProduct(){
        Product p1 = new Product("E101", "Tshirt", CATEGORY.CLOTHS, "sdxcvhjn", 234.2f, "cdcbj", STATUS.ACTIVE);

        //repoObj.save(p1);
        when(repoObj.save(p1)).thenReturn(p1);

        String p2 = srvObj.addProduct(p1);
        assertNotNull(p2, "Object created");
    }

    @Test
    public void updateByProductId(){
        Product p1 = new Product("E101", "Tshirt", CATEGORY.CLOTHS, "trdcdvbjhm", 123.3f, "sxchjg", STATUS.ACTIVE);
        
        //when(repoObj.findById(p1.getProductId())).thenReturn(Optional.of(p1));
        //when(repoObj.save(p1)).thenReturn(p1);

        p1.setProductStatus(STATUS.ACTIVE);
        p1.setProductName("Mobile");
        p1.setProductCategory(CATEGORY.CLOTHS);
        p1.setProductDescription("fsvj");
        p1.setProductImage("vjhbd");
        p1.setProductPrice(342.3f);

        Assertions.assertThat(p1.getProductStatus()).isEqualTo(STATUS.ACTIVE);
        Assertions.assertThat(p1.getProductName()).isEqualTo("Mobile");
        Assertions.assertThat(p1.getProductCategory()).isEqualTo(CATEGORY.CLOTHS);
        Assertions.assertThat(p1.getProductDescription()).isEqualTo("fsvj");
        Assertions.assertThat(p1.getProductImage()).isEqualTo("vjhbd");
        Assertions.assertThat(p1.getProductPrice()).isEqualTo(342.3f);

    }

    

    @Test
    public void deleteByProductIdTest(){
    Product p1 = new Product("E101", "Tshirt", CATEGORY.CLOTHS, "trdcdvbjhm", 123.3f, "sxchjg", STATUS.ACTIVE);

    when(repoObj.findById("E101")).thenReturn(Optional.of(p1));
    String msg=srvObj.deleteByProductId(p1);
    assertEquals("Object Deleted Successfully", msg);
}
}
