package java25update.exercise9.services;

import java25update.exercise9.model.InvoiceTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class InvoiceTemplateService {

  public InvoiceTemplate getTemplate(String language) throws InterruptedException {
    IO.println("Loading template");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      IO.println("Template loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() < 0.2) {
      IO.println("Error loading template");
      throw new RuntimeException("Error loading template");
    }

    IO.println("Finished loading template");
    return new InvoiceTemplate();
  }

  public CompletableFuture<InvoiceTemplate> getTemplateAsync(String language) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return getTemplate(language);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
