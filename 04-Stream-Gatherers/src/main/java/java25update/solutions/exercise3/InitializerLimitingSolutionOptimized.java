void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "limiting" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using the Initializer and the Integrator.
  // Step 4: Use that Gatherer to limit a `words` stream to 3 elements.

  List<String> list = words.stream()
      .gather(limiting(3))
      .toList();

  System.out.println(list);
}

private static <T> Gatherer<T, ?, T> limiting(int maxSize) {
  return Gatherer.ofSequential(
      // Initializer
      () -> new Object() {
        int counter = 0;
      },

      // Integrator
      (state, element, downstream) -> {
        if (state.counter++ < maxSize) {
          return downstream.push(element);
        } else {
          return false;
        }
      });
}
