void main() throws InterruptedException {

  // Task: Remove the `threads` list, don't add the VTs to the list, don't join at the end.
  //       Create an executor with `Executors.newVirtualThreadPerTaskExecutor()`.
  //       Submit the tasks to the executor instead of starting them with `Thread.startVirtualThread`.
  //       Wrap the whole thing in a try-with-resources block.
  //
  // Pattern:
  //
  // try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
  //   executor.submit(() -> { ... });
  // }

  List<Thread> threads = new ArrayList<>();

  for (int j = 0; j < 5; j++) {
    threads.add(Thread.startVirtualThread(() -> {
          for (int i = 0; i < 10; i++) {
            IO.println("Hello, I am " + Thread.currentThread());
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              // Let the thread die
            }
          }
        }
    ));
  }

  for (Thread thread : threads) {
    thread.join();
  }
}
