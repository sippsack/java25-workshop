package java25update.solutions.exercise11;

import java25update.exercise11.model.Request;

import java.util.UUID;

class RestAdapter {
  private final UseCase useCase = new UseCase();

  void processRequest(Request request) {
    IO.println("Processing request");
    // ...
    UUID id = extractId(request);
    useCase.invoke(id);
    // ...
  }

  private UUID extractId(Request request) {
    return request.id();
  }
}