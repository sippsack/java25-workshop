package java25update.exercise11;

import java25update.exercise11.model.Data;
import java25update.exercise11.model.User;

import java.util.UUID;

class UseCase {
  private final Repository repository = new Repository();

  void invoke(UUID id, User loggedInUser) {
    IO.println("Invoking usecase (logged in user: " + loggedInUser + ")");
    // ...
    Data data = repository.getData(id, loggedInUser);
    // ...
  }
}