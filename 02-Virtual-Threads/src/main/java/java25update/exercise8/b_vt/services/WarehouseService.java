package java25update.exercise8.b_vt.services;

import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

  public boolean isAvailable(String supplierId, String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    if (productId.equals("DWK6L7ZG")) {
      return false;
    } else {
      return true;
    }
  }
}
