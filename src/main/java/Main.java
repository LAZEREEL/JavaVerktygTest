
public class Main {


    /** sample program that would work, if there was a DAO implemented
     *  with a product with id=1, and an "iPhone" in it
     */
    public static void main(String[] args) {
        ShoppingBasket basket = new ShoppingBasket();
        //basket.setProductDAO(new ProductDAOWithJPA); // this class not done yet
        basket.addById(1,1);
        basket.addByName(2,"iPhone");
        System.out.println(basket.getTotalCost());
        System.out.println(basket);


    }

}
