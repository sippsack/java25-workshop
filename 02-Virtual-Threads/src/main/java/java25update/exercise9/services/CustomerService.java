package java25update.exercise9.services;

import java25update.exercise9.model.Customer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerService {

  public Customer getCustomer(int customerId) throws InterruptedException {
    IO.println("Loading customer");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      IO.println("Customer loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() < 0.2) {
      IO.println("Error loading customer");
      throw new RuntimeException("Error loading customer");
    }

    IO.println("Finished loading customer");
    return new Customer();
  }

  public CompletableFuture<Customer> getCustomerAsync(int customerId) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return getCustomer(customerId);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
