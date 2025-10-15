package java25update.exercise8.a_async.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionStage;

@Service
public class SupplierService {

  public CompletionStage<Integer> getDeliveryTimeAsync(String supplierId, String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    throw new RuntimeException("Something went wrong!");
  }
}
