void main() throws InterruptedException {

  // Task: Call `join()` on the virtual thread returned by `startVirtualThread()`.

    Thread thread = Thread.startVirtualThread(() ->
            IO.println("Hello, I am " + Thread.currentThread())
    );
    thread.join();
}
