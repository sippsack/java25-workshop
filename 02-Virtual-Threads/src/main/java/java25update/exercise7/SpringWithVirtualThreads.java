package java25update.exercise7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWithVirtualThreads {

  public static void main(String[] args) {
    SpringApplication.run(SpringWithVirtualThreads.class, args);
  }

  // Tasks: Start the application.
  //        Call http://localhost:8080/blocking-op multiple times (via `test.http` or `curl` or the web browser).
  //        Observe which / how many threads handle the requests.
  //
  //        Then enable virtual threads by adding this application property: `spring.threads.virtual.enabled=true`,
  //        start the application again, call the endpoints again, observe the handling threads again.

}
