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

  System.out.println(list);
}

private static <T> Gatherer<T, ?, List<T>> windowFixed(int windowSize) {
  return Gatherer.ofSequential(
      // Initializer
      () -> new Object() {
        ArrayList<T> list = new ArrayList<>();
      },

      // Integrator
      Gatherer.Integrator.ofGreedy(
          (state, element, downstream) -> {
            state.list.add(element);
            if (state.list.size() == windowSize) {
              boolean result = downstream.push(state.list);
              state.list = new ArrayList<>();
              return result;
            } else {
              return true;
            }
          }),

      // Finisher
      (state, downstream) -> {
        if (!state.list.isEmpty()) {
          downstream.push(state.list);
        }
      });
}
