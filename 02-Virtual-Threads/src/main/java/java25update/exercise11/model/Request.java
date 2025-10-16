package java25update.exercise11.model;

import java.util.UUID;

public record Request(UUID id, String userId) {
  public Request(String userId) {
    this(UUID.randomUUID(), userId);
  }
}
