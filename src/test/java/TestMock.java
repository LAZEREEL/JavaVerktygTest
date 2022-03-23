import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ShoppingBasketMockTest {

    ProductDAO productDAO;
    ShoppingBasket shopBasket;
    Product productA;
    Product productB;
    Product productC;

    @BeforeEach
    void setUp(){


        productDAO = mock(ProductDAO.class);
        shopBasket = new ShoppingBasket();

        shopBasket.setProductDAO(productDAO);

        productA = new Product(1, "Iphone 8", 650);
        productB = new Product(2, "Iphone 9", 875);
        productC = new Product(3, "Iphone 10", 1299);
    }

    @Test
    void addByNameProductExists(){
        String productName = productA.getName();
        when(productDAO.findByName(productName)).thenReturn(productA);
        shopBasket.addByName(anyInt(), productName);
        assertTrue(shopBasket.toString().contains(productName));
    }

    @Test
    void addByNameProductDoesNotExist(){
        String productName = "Iphone 11";
        when(productDAO.findByName(productName)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> shopBasket.addByName(anyInt(), productName));

    }

    @Test
    void addByIdProductExists(){
        int productId = productB.getId();
        String productName = productB.getName();
        when(productDAO.findById(productId)).thenReturn(productB);
        shopBasket.addById(anyInt(), productId);
        assertTrue(shopBasket.toString().contains(productName));
    }

    @Test
    void byIdProductDoesNotExist(){
        int prodId = 4;
        when(productDAO.findById(prodId)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> shopBasket.addById(anyInt(), prodId));

    }

    @Test
    void getTotalCost(){
        when(productDAO.findById(anyInt())).thenReturn(productA).thenReturn(productB).thenReturn(productC);
        shopBasket.addById(1, productA.getPrice());
        shopBasket.addById(1, productB.getPrice());
        shopBasket.addById(2, productC.getPrice());
        int totalCost = (productA.getPrice()) + (productB.getPrice()) + (2 * productC.getPrice());
        assertEquals(totalCost, shopBasket.getTotalCost());
    }

    @Test
    void getTotalCostEmptyBasket(){
        assertEquals(0, shopBasket.getTotalCost());
    }

    @Test
    void clearBasket(){
        when(productDAO.findById(anyInt())).thenReturn(productA);
        shopBasket.addById(1, productA.getPrice());
        shopBasket.clear();
        assertEquals(0, shopBasket.getTotalCost());
    }

    @Test
    void toStringNotEmpty(){
        int count = 5;
        String countString = String.valueOf(count);
        when(productDAO.findByName(anyString())).thenReturn(productB);
        shopBasket.addByName(count, productB.getName());

        assertTrue(shopBasket.toString().contains(countString));
        assertTrue(shopBasket.toString().contains(productB.getName()));
    }

    @Test
    void toStringEmpty(){
        assertEquals("[]", shopBasket.toString());
    }
}
