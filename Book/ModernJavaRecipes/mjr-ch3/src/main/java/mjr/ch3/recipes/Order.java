package mjr.ch3.recipes;

public class Order {
    private int id;

    public int getId() {
        return id;
    }

    public Order(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{"
                + id +
                '}';
    }
}
