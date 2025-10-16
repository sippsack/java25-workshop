package java25update.exercise10;


import java25update.exercise10.model.Address;
import java25update.exercise10.model.AddressVerificationResponse;
import java25update.exercise10.service.AddressVerificationService;

public class AddressVerification {

  static void main() throws InterruptedException {
    AddressVerification addressVerification =
        new AddressVerification(new AddressVerificationService());

    AddressVerificationResponse response =
        addressVerification.verifyAddress(
            new Address("1600 Pennsylvania Avenue, N.W.", null, "Washington", "DC", "20500", "US"));

    IO.println("Response: " + response);
  }

  private final AddressVerificationService verificationService;

  public AddressVerification(AddressVerificationService verificationService) {
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
    try {
      return verificationService.verifyViaServiceA(address);
    } catch (Exception e) {
      IO.println("Verification via service A failed: " + e.getMessage());
    }

    try {
      return verificationService.verifyViaServiceB(address);
    } catch (Exception e) {
      IO.println("Verification via service B failed: " + e.getMessage());
    }

    try {
      return verificationService.verifyViaServiceC(address);
    } catch (Exception e) {
      IO.println("Verification via service C failed: " + e.getMessage());
      throw e;
    }
  }
}
