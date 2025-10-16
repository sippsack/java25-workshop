package java25update.exercise10.model;

public record Address(
    String addressLine1,
    String addressLine2,
    String city,
    String state,
    String postalCode,
    String countryCode) {
}
