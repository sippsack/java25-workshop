package java25update.exercise10.service;

import java25update.exercise10.model.Address;
import java25update.exercise10.model.AddressVerificationResponse;

import java.util.concurrent.ThreadLocalRandom;

public class AddressVerificationService {

  public AddressVerificationResponse verifyViaServiceA(Address address)
      throws InterruptedException {
    return verifyViaService(address, "A");
  }

  public AddressVerificationResponse verifyViaServiceB(Address address)
      throws InterruptedException {
    return verifyViaService(address, "B");
  }

  public AddressVerificationResponse verifyViaServiceC(Address address)
      throws InterruptedException {
    return verifyViaService(address, "C");
  }

  private AddressVerificationResponse verifyViaService(Address address, String service)
      throws InterruptedException {
    IO.println("Address verification via service " + service);
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      IO.println("Address verification via service " + service + " was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() < 0.75) {
      String error = "Error verifying address via service " + service;
      IO.println(error);
      throw new RuntimeException(error);
    }

    IO.println("Finished loading address via service " + service);
    return new AddressVerificationResponse("Address verification response from service " + service);
  }
}
