void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "limiting" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using the Initializer and the Integrator.
  // Step 4: Use that Gatherer to limit a `words` stream to 3 elements.

  int limit = 3;

  Supplier<AtomicInteger> initializer = AtomicInteger::new;

  Gatherer.Integrator<AtomicInteger, String, String> integrator =
      (state, element, downstream) -> {
        if (state.getAndIncrement() < limit) {
          return downstream.push(element);
        } else {
          return false;
        }
      };

  Gatherer<String, AtomicInteger, String> gatherer =
      Gatherer.ofSequential(initializer, integrator);

  List<String> list = words.stream()
      .gather(gatherer)
      .toList();

  IO.println(list);
}
