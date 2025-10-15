package java25update.exercise7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWithVirtualThreads {

  public static void main(String[] args) {
    SpringApplication.run(SpringWithVirtualThreads.class, args);
  }

  // Task: Enable virtual threads by adding this application property:
  //       `spring.threads.virtual.enabled=true`

}
