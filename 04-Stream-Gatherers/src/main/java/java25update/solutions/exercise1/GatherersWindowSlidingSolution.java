void main() {
  List<String> words = List.of("the", "be", "two", "of", "and", "a", "in", "that");

  // Task: use `Stream.gather()` and `Gatherers.windowSliding()` to create a list of 3-element windows
  List<List<String>> list = words.stream()
      .gather(Gatherers.windowSliding(3))
      .toList();

  System.out.println("list = " + list);
}