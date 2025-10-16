package java25update.exercise9.services;


import java25update.exercise9.model.Order;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;


public class OrderService {

  public Order getOrder(int orderId) throws InterruptedException {
    IO.println("Loading order");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      IO.println("Order loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() < 0.2) {
      IO.println("Error loading order");
      throw new RuntimeException("Error loading order");
    }

    IO.println("Finished loading order");
    return new Order();
  }

  public CompletableFuture<Order> getOrderAsync(int orderId) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return getOrder(orderId);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
