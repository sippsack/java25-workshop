void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "groupingBy" Stream Gatherer.
  // Step 1: Write an Initializer to create an object to hold the status.
  // Step 2: Write an Integrator.
  // Step 3: Write a Finisher.
  // Step 4: Create a Gatherer using the Initializer, the Integrator, and the Finisher.
  // Step 5: Use that Gatherer to group the words by lists of words starting with the same character.

  List<List<String>> list = words.stream()
      .gather(groupingBy(word -> word.charAt(0)))
      .toList();

  IO.println(list);
}

private static Gatherer<String, Map<Character, List<String>>, List<String>> groupingBy(
    Function<String, Character> classifier) {
  Supplier<Map<Character, List<String>>> initializer = TreeMap::new;

  Gatherer.Integrator<Map<Character, List<String>>, String, List<String>> integrator =
      Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
        Character classificationKey = classifier.apply(element);
        state.computeIfAbsent(classificationKey, _ -> new ArrayList<>()).add(element);
        return true;
      });

  BiConsumer<Map<Character, List<String>>, Gatherer.Downstream<? super List<String>>> finisher =
      (state, downstream) ->
          state.values().forEach(downstream::push);

  return Gatherer.ofSequential(initializer, integrator, finisher);
}
