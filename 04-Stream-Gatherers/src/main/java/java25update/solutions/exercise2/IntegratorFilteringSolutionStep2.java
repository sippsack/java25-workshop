void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: Write and use a "filtering" Stream Gatherer.
  // Step 1: Write a `Predicate` for the filter.
  // Step 2: Write an Integrator.
  // Step 3: Create a Gatherer using only this Integrator.
  // Step 4: Use that Gatherer to filter words that are at least 3 characters long.

  List<String> list = words.stream()
      .gather(filtering(string -> string.length() >= 3))
      // = . filter(string -> string.length() >= 3)
      .toList();

  IO.println(list);
}

private static <String> Gatherer<String, Void, String> filtering(Predicate<String> predicate) {
  Gatherer.Integrator<Void, String, String> integrator =
      Gatherer.Integrator.ofGreedy(
          (_, element, downstream) -> {
            if (predicate.test(element)) {
              return downstream.push(element);
            } else {
              return true;
            }
          });

  return Gatherer.of(integrator);
}
