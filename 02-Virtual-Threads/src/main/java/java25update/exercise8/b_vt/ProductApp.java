package java25update.exercise8.b_vt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

import java.util.concurrent.Executors;

@SpringBootApplication
public class ProductApp {

  // Tasks: Start the application
  //        Call the endpoint `http://localhost:8080/product/B0FHHSZ8`
  //        Call the endpoint `http://localhost:8080/product/DWK6L7ZG`
  //        Look at the stack trace - what causes the error?
  //        Use the debugger to debug it.

  public static void main(String[] args) {
    SpringApplication.run(ProductApp.class, args);
  }
}
