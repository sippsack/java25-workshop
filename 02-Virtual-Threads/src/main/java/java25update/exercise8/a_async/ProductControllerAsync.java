package java25update.exercise8.a_async;

import java25update.exercise8.a_async.services.ProductService;
import java25update.exercise8.a_async.services.SupplierService;
import java25update.exercise8.a_async.services.WarehouseService;
import java25update.exercise8.common.ProductPageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
public class ProductControllerAsync {

  private final ProductService productService;
  private final WarehouseService warehouseService;
  private final SupplierService supplierService;

  public ProductControllerAsync(ProductService productService, WarehouseService warehouseService,
                                SupplierService supplierService) {
    this.productService = productService;
    this.warehouseService = warehouseService;
    this.supplierService = supplierService;
  }

  @GetMapping("product/{productId}")
  public CompletionStage<ResponseEntity<ProductPageResponse>> getProductAsync(
      @PathVariable("productId") String productId) {
    return productService
        .getProductAsync(productId)
        .thenCompose(product ->
            product.map(value ->
                    warehouseService
                        .isAvailableAsync(productId)
                        .thenCompose(available ->
                            available
                                ? CompletableFuture.completedFuture(0)
                                : supplierService.getDeliveryTimeAsync(
                                product.get().supplier(), productId))
                        .thenApply(daysUntilShippable ->
                            ResponseEntity.ok(
                                new ProductPageResponse(value, daysUntilShippable))))
                .orElseGet(
                    () -> CompletableFuture.completedFuture(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build())));
  }
}
