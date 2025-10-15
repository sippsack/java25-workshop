package java25update.exercise8.a_async.services;

import java25update.exercise8.common.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class ProductService {

  public CompletionStage<Optional<Product>> getProductAsync(String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    Optional<Product> result = Optional.of(new Product(productId, "KaufMirDenKram.de"));
    return CompletableFuture.completedFuture(result);
  }
}
