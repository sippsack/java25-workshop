void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: use `Stream.gather()` and `Gatherers.windowFixed()` to create a list of 3-element windows
  List<List<String>> list = words.stream()
      .gather(Gatherers.windowFixed(3))
      .toList();

  IO.println("list = " + list);
}