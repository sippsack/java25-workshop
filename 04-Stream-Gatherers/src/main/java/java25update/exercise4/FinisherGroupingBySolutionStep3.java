void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "groupingBy" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Write a Finisher.
  // Step 4: Create a Gatherer using the Initializer, the Integrator, and the Finisher.
  // Step 5: Use that Gatherer to group the words by lists of words starting with the same character.

  var list = words.stream()
      .gather(groupingBy(word -> word.charAt(0)))
      .toList();

  System.out.println(list);
}

private static <T, K> Gatherer<T, Map<K, List<T>>, List<T>> groupingBy(Function<T, K> classifier) {
  Supplier<Map<K, List<T>>> initializer = TreeMap::new;

  Gatherer.Integrator<Map<K, List<T>>, T, List<T>> integrator =
      Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
        K classificationKey = classifier.apply(element);
        state.computeIfAbsent(classificationKey, _ -> new ArrayList<>()).add(element);
        return true;
      });

  BiConsumer<Map<K, List<T>>, Gatherer.Downstream<? super List<T>>> finisher =
      (state, downstream) ->
          state.values().forEach(downstream::push);

  return Gatherer.ofSequential(initializer, integrator, finisher);
}
