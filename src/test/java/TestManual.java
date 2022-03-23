import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestManual {

    private ShoppingBasket shopBasket;

    @BeforeEach
    void setUp(){
        shopBasket = new ShoppingBasket();
        shopBasket.setProductDAO(new productManualDAO());
    }

    @Test
    void addByNameProductExists(){
        shopBasket.addByName(1, "Iphone 8");
        assertEquals("[[1*Iphone 8]]", shopBasket.toString());
    }

    @Test
    void productDoesNotExist(){
        assertThrows(IllegalArgumentException.class, () -> shopBasket.addByName(1, "Iphone 11"));
    }

    @Test
    void addByIdProductExists(){
        shopBasket.addById(1, 1);
        assertEquals("[[1*Iphone 8]]", shopBasket.toString());
    }

    @Test
    void byIdProductDoesNotExist(){
        assertThrows(IllegalArgumentException.class, () -> shopBasket.addById(1, 4));
    }

    @Test
    void getTotalCost(){
        shopBasket.addByName(2, "Iphone 8");
        int totalCost = 650*2;
        assertEquals(totalCost, shopBasket.getTotalCost());
    }

    @Test
    void getTotalCostEmptyBasket(){
        assertEquals(0, shopBasket.getTotalCost());
    }

    @Test
    void clearBasket(){
        shopBasket.addById(1, 1);
        shopBasket.clear();
        assertEquals(0, shopBasket.getTotalCost());
    }

    @Test
    void toStringNotEmpty(){
        int count = 4;
        String countString = String.valueOf(count);
        shopBasket.addById(count, 3);

        assertTrue(shopBasket.toString().contains(countString));
        assertTrue(shopBasket.toString().contains("Iphone 10"));
    }

    @Test
    void toStringEmpty(){
        assertEquals("[]", shopBasket.toString());
    }

    static class productManualDAO implements ProductDAO {
        Product productA = new Product(1, "Iphone 8", 650);
        Product productB = new Product(2, "Iphone 9", 875);
        Product productC = new Product(3, "Iphone 10", 1299);

        List<Product> products = new ArrayList<>();

        public productManualDAO(){
            products.add(productA);
            products.add(productB);
            products.add(productC);

        }

        @Override
        public Product findById(int id) {
            for(Product product : products) {
                if (product.getId() == (id))
                    return product;
            }
            return null;
        }

        @Override
        public Product findByName(String name) {
            for(Product product : products){
                if(product.getName().equals(name))
                    return product;
            }
            return null;
        }

        @Override
        public List<Product> findAll() {
            return null;
        }

        @Override
        public List<Product> findCheaperThan(int lowprice) {
            return null;
        }

        @Override
        public boolean isInStock(int id) {
            return false;
        }

        @Override
        public boolean delete(int id) {
            return false;
        }

        @Override
        public void raiseAllPrices(double percent) {

        }
    }
}
