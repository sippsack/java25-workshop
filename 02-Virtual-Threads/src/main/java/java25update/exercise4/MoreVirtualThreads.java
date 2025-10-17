void main() throws InterruptedException {

    // Task: Start 5 virtual threads (using a loop or a stream).
    //       Move the join out of the loop.

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
