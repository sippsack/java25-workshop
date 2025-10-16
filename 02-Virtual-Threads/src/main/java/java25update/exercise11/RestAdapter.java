package java25update.exercise11;

import java25update.exercise11.model.Request;
import java25update.exercise11.model.User;

import java.util.UUID;

class RestAdapter {
  private final UseCase useCase = new UseCase();

  void processRequest(Request request, User loggedInUser) {
    IO.println("Processing request (logged in user: " + loggedInUser + ")");
    // ...
    UUID id = extractId(request);
    useCase.invoke(id, loggedInUser);
    // ...
  }

  private UUID extractId(Request request) {
    return request.id();
  }
}