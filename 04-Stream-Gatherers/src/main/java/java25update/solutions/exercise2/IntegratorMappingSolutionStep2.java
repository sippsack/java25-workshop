void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "mapping" Stream Gatherer.
  // Step 1: Write a mapping `Function`.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using only this Integrator.
  // Step 4: Use that Gatherer to map the words to their lengths.

  List<Integer> list = words.stream()
      .gather(mapping(String::length))
      // = .map(String::length)
      .toList();

  IO.println(list);
}

private static <String, Integer> Gatherer<String, Void, Integer> mapping(Function<String, Integer> mapper) {
  Gatherer.Integrator<Void, String, Integer> integrator =
      Gatherer.Integrator.ofGreedy((_, element, downstream) -> {
        Integer mappedElement = mapper.apply(element);
        return downstream.push(mappedElement);
      });

  return Gatherer.of(integrator);
}
