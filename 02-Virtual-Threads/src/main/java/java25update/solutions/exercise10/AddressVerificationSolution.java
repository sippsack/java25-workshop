package java25update.solutions.exercise10;


import java25update.exercise10.model.Address;
import java25update.exercise10.model.AddressVerificationResponse;
import java25update.exercise10.service.AddressVerificationService;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;

public class AddressVerificationSolution {

  static void main() throws InterruptedException {
    AddressVerificationSolution addressVerification =
        new AddressVerificationSolution(new AddressVerificationService());

    AddressVerificationResponse response =
        addressVerification.verifyAddress(
            new Address("1600 Pennsylvania Avenue, N.W.", null, "Washington", "DC", "20500", "US"));

    IO.println("Response: " + response);
  }

  private final AddressVerificationService verificationService;

  public AddressVerificationSolution(AddressVerificationService verificationService) {
    this.verificationService = verificationService;
  }

  // Task: Use a `StructuredTaskScope` to execute the services concurrently.
  //       Return the first result, throw an exception if all service calls failed.
  //
  // Example:
  // try (var scope = StructuredTaskScope.open(Joiner.<ResultType>anySuccessfulResultOrThrow())) {
  //   scope.fork(() -> ...);
  //   scope.fork(() -> ...);
  //   scope.fork(() -> ...);
  //
  //   ResultType result = scope.join();
  //
  //   ...
  // }

  AddressVerificationResponse verifyAddress(Address address) throws InterruptedException {
    try (var scope =
             StructuredTaskScope.open(
                 Joiner.<AddressVerificationResponse>anySuccessfulResultOrThrow())) {

      scope.fork(() -> verificationService.verifyViaServiceA(address));
      scope.fork(() -> verificationService.verifyViaServiceB(address));
      scope.fork(() -> verificationService.verifyViaServiceC(address));

      return scope.join();
    }
  }
}
