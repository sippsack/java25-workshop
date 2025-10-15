package java25update.exercise8.a_async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductAppAsync {

  // Tasks: Start the application
  //        Call the endpoint `http://localhost:8080/product/B0FHHSZ8`
  //        Call the endpoint `http://localhost:8080/product/DWK6L7ZG`
  //        Look at the stack trace - what causes the error?
  //        Use the debugger to debug it.

  public static void main(String[] args) {
    SpringApplication.run(ProductAppAsync.class, args);
  }

}
