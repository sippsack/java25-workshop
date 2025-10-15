void main() throws InterruptedException {

  // Task: Inside the virtual thread,
  //       repeat the output 10 times and sleep for 1 second in between.

  Thread.startVirtualThread(() ->
      IO.println("Hello, I am " + Thread.currentThread())
  ).join();
}
