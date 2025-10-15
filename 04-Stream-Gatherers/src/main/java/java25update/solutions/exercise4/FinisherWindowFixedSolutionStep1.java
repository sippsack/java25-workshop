void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "windowFixed" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Write a Finisher.
  // Step 4: Create a Gatherer using the Initializer, the Integrator, and the Finisher.
  // Step 5: Use that Gatherer to create fixed windows of 3 elements.

  int maxSize = 3;

  Supplier<List<String>> initializer = ArrayList::new;

  Gatherer.Integrator<List<String>, String, List<String>> integrator =
      Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
        state.add(element);
        if (state.size() == maxSize) {
          boolean result = downstream.push(List.copyOf(state));
          state.clear();
          return result;
        } else {
          return true;
        }
      });

  BiConsumer<List<String>, Gatherer.Downstream<? super List<String>>> finisher =
      (state, downstream) -> {
        if (!state.isEmpty()) {
          downstream.push(List.copyOf(state));
        }
      };

  Gatherer<String, List<String>, List<String>> gatherer =
      Gatherer.ofSequential(initializer, integrator, finisher);

  List<List<String>> list = words.stream()
      .gather(gatherer)
      .toList();

  IO.println(list);
}
