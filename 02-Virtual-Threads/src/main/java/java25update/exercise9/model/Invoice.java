package java25update.exercise9.model;

public record Invoice() {
  public static Invoice generate(Order order, Customer customer, InvoiceTemplate template) {
    IO.println("Invoice generated");
    return new Invoice();
  }
}
