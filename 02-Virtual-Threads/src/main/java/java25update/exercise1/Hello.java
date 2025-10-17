void main() {

  // Task: Start a virtual thread that prints information about the thread itself
  //       (just print something like `"Hello, I am " + Thread.currentThread()`).

    Thread.startVirtualThread(() ->
            IO.println("Hello, I am " + Thread.currentThread())
    );

}
