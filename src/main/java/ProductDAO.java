import java.util.List;

public interface ProductDAO {

    /** returns the Product by id, returns null if nonexistent */
    Product findById(int id);

    /** returns the Product with exact string name, returns null if nonexistent */
    Product findByName(String name);

    /** returns all Products */
    List<Product> findAll();

    /** returns all Products cheaper than lowprice */
    List<Product> findCheaperThan(int lowprice);

    /** returns the stock status of product i */
    boolean isInStock(int id);

    /** deletes Product with if, returns deletion success */
    boolean delete(int id);

    /** Raises all prices with percentage given */
    void raiseAllPrices(double percent);
}
