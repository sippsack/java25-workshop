void main() throws InterruptedException {

  // Task: Start 1000 instead of 5 virtual threads.
  //       How many carrier threads are used?

  List<Thread> threads = IntStream.range(0, 5).mapToObj(
      _ -> Thread.startVirtualThread(() -> {
            for (int i = 0; i < 10; i++) {
              IO.println("Hello, I am " + Thread.currentThread());
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                // Let the thread die
              }
            }
          }
      )
  ).toList();

  for (Thread thread : threads) {
    thread.join();
  }
}
