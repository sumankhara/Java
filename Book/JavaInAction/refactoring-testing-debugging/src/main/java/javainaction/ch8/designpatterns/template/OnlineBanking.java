package javainaction.ch8.designpatterns.template;

import java.util.function.Consumer;

public class OnlineBanking {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        System.out.println("Get customer details from DB");
        Customer customer = new Customer("John Doe");
        makeCustomerHappy.accept(customer);
    }
}
