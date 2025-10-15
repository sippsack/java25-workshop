package java25update.exercise7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SpringRestController {

  private static final AtomicInteger counter = new AtomicInteger();

  @GetMapping("blocking-op")
  public int blockingOp() throws InterruptedException {
    int count = counter.getAndIncrement();
    IO.println("Request %d begins on thread %s".formatted(count, Thread.currentThread()));

    // Simulate blocking operation
    Thread.sleep(
        ThreadLocalRandom.current().nextLong(6_000, 10_000)
    );

    IO.println("Request %d ends on thread %s".formatted(count, Thread.currentThread()));

    return count;
  }
}
