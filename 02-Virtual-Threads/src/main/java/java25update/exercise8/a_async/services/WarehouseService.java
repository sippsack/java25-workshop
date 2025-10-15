package java25update.exercise8.a_async.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class WarehouseService {

  public CompletionStage<Boolean> isAvailableAsync(String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    if (productId.equals("DWK6L7ZG")) {
      return CompletableFuture.completedFuture(false);
    } else {
      return CompletableFuture.completedFuture(true);
    }
  }
}
