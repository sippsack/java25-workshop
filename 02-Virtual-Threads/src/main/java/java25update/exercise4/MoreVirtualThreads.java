void main() throws InterruptedException {

  // Task: Start 5 virtual threads (using a loop or a stream).
  //       Move the join out of the loop.

  Thread.startVirtualThread(() -> {
        for (int i = 0; i < 10; i++) {
          IO.println("Hello, I am " + Thread.currentThread());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // Let the thread die
          }
        }
      }
  ).join();
}
