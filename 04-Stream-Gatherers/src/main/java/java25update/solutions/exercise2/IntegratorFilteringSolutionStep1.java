void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "filtering" Stream Gatherer.
  // Step 1: Write a `Predicate` for the filter.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using only this Integrator.
  // Step 4: Use that Gatherer to filter words that are at least 3 characters long.

  Predicate<String> predicate = string -> string.length() >= 3;

  Gatherer.Integrator<Void, String, String> integrator =
      Gatherer.Integrator.ofGreedy(
          (_, element, downstream) -> {
            if (predicate.test(element)) {
              return downstream.push(element);
            } else {
              return true;
            }
          });

  Gatherer<String, Void, String> gatherer = Gatherer.of(integrator);

  List<String> list = words.stream()
      .gather(gatherer)
      .toList();

  IO.println(list);
}
