package java25update.exercise8.b_vt.services;

import java25update.exercise8.common.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

  public Optional<Product> getProduct(String productId) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return Optional.of(new Product(productId, "KaufMirDenKram.de"));
  }
}
