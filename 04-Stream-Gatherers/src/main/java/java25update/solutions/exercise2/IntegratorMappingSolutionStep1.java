void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "mapping" Stream Gatherer.
  // Step 1: Write a mapping `Function`.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using only this Integrator.
  // Step 4: Use that Gatherer to map the words to their lengths.

  Function<String, Integer> mapper = String::length;

  Gatherer.Integrator<Void, String, Integer> integrator =
      Gatherer.Integrator.ofGreedy((_, element, downstream) -> {
        Integer mappedElement = mapper.apply(element);
        return downstream.push(mappedElement);
      });

  Gatherer<String, Void, Integer> gatherer = Gatherer.of(integrator);

  List<Integer> list = words.stream()
      .gather(gatherer)
      .toList();

  IO.println(list);
}
