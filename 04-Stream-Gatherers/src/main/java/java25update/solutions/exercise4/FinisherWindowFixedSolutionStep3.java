void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "windowFixed" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Write a Finisher.
  // Step 4: Create a Gatherer using the Initializer, the Integrator, and the Finisher.
  // Step 5: Use that Gatherer to create fixed windows of 3 elements.

  List<List<String>> list = words.stream()
      .gather(windowFixed(3))
      // = .gather(Gatherers.windowFixed(3))
      .toList();

  IO.println(list);
}

private static <T> Gatherer<T, List<T>, List<T>> windowFixed(int windowSize) {
  Supplier<List<T>> initializer = ArrayList::new;

  Gatherer.Integrator<List<T>, T, List<T>> integrator =
      Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
        state.add(element);
        if (state.size() == windowSize) {
          boolean result = downstream.push(List.copyOf(state));
          state.clear();
          return result;
        } else {
          return true;
        }
      });

  BiConsumer<List<T>, Gatherer.Downstream<? super List<T>>> finisher =
      (state, downstream) -> {
        if (!state.isEmpty()) {
          downstream.push(List.copyOf(state));
        }
      };

  return Gatherer.ofSequential(initializer, integrator, finisher);
}
