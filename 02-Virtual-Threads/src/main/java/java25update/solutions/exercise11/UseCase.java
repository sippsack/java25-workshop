package java25update.solutions.exercise11;

import java25update.exercise11.model.Data;

import java.util.UUID;

class UseCase {
  private final Repository repository = new Repository();

  void invoke(UUID id) {
    IO.println("Invoking usecase");
    // ...
    Data data = repository.getData(id);
    // ...
  }
}