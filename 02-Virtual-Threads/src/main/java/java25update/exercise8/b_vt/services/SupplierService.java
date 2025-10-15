package java25update.exercise8.b_vt.services;

import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  public int getDeliveryTime(String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    throw new RuntimeException("Something went wrong!");
  }
}
