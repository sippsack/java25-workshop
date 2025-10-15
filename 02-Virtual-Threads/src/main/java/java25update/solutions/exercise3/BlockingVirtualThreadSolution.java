void main() throws InterruptedException {

  // Task: Inside the virtual thread,
  //       repeat the output 10 times and sleep for 1 second in between.

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
