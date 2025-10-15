package java25update.exercise8.b_vt;

import java25update.exercise8.b_vt.services.ProductService;
import java25update.exercise8.b_vt.services.SupplierService;
import java25update.exercise8.b_vt.services.WarehouseService;
import java25update.exercise8.common.Product;
import java25update.exercise8.common.ProductPageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class ProductController {

  private final ProductService productService;
  private final WarehouseService warehouseService;
  private final SupplierService supplierService;

  public ProductController(ProductService productService, WarehouseService warehouseService,
                           SupplierService supplierService) {
    this.productService = productService;
    this.warehouseService = warehouseService;
    this.supplierService = supplierService;
  }

  @GetMapping("product/{productId}")
  public ProductPageResponse getProduct(@PathVariable("productId") String productId) throws InterruptedException {
    Product product = productService.getProduct(productId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

    boolean available = warehouseService.isAvailable(product.supplier(), productId);

    int shipsInDays =
        available ? 0 : supplierService.getDeliveryTime(productId);

    return new ProductPageResponse(product, shipsInDays);
  }
}
