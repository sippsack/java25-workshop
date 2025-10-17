void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "skipping" Stream Gatherer (skips the first n elements).
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using the Initializer and the Integrator.
  // Step 4: Use that Gatherer to skip the first 3 elements of a `words` stream.

  int n = 3;

  Supplier<AtomicInteger> initializer = AtomicInteger::new;

  Gatherer.Integrator<AtomicInteger, String, String> integrator =
      Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
        if (state.getAndIncrement() >= n) {
          return downstream.push(element);
        } else {
          return true;
        }
      });

  Gatherer<String, AtomicInteger, String> gatherer =
      Gatherer.ofSequential(initializer, integrator);

  List<String> list = words.stream()
      .gather(gatherer)
      .toList();

  IO.println(list);
}
