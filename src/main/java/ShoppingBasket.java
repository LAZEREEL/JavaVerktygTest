import java.util.ArrayList;


public class ShoppingBasket {


    private ProductDAO dao;
    private ArrayList<BasketItem> content = new ArrayList<>();

    /** adds count number of products to the shoppingbasket, given by exact name */
    public void addByName(int count, String productName) {
        Product p = dao.findByName(productName);
        if (p==null) throw new IllegalArgumentException();
        content.add(new BasketItem(count,p));
    }

    /** adds count number of products to the shoppingbasket, given by id */
    public void addById(int count, int id) {
        Product p = dao.findById(id);
        if (p==null) throw new IllegalArgumentException();
        content.add(new BasketItem(count,p));
    }

    /** calculates the total price for everything in the basket */
    public int getTotalCost() {
        int total = 0;
        for (BasketItem b : content) {
            total += b.item.getPrice() * b.count;
        }
        return total;
    }

    /** empties the shoppingbasket of all content */
    public void clear() {
        content.clear();
    }

    /** gives a list of all content, with amount and productname for each */
    @Override
    public String toString() {
        return content.toString();
    }

    /** Dependency injection for the PruductDAO */
    public void setProductDAO(ProductDAO dao) {
        this.dao = dao;
    }


    /** class defining the items in the basket */
    class BasketItem {
        private int count;
        private Product item;
        public BasketItem(int count, Product item) {
            this.count = count;
            this.item = item;
        }
        @Override
        public String toString() {
            return "[" + count + "*" + item.getName() + "]";
        }
    }

}
