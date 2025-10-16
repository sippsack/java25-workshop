package java25update.exercise9;

import java25update.exercise9.model.Customer;
import java25update.exercise9.model.Invoice;
import java25update.exercise9.model.InvoiceTemplate;
import java25update.exercise9.model.Order;
import java25update.exercise9.services.CustomerService;
import java25update.exercise9.services.InvoiceTemplateService;
import java25update.exercise9.services.OrderService;

public class InvoiceGenerator {

  static void main() throws InterruptedException {
    new InvoiceGenerator(new OrderService(), new CustomerService(), new InvoiceTemplateService())
        .createInvoice(10012, 61157, "en");
  }

  private final OrderService orderService;
  private final CustomerService customerService;
  private final InvoiceTemplateService invoiceTemplateService;

  public InvoiceGenerator(
      OrderService orderService,
      CustomerService customerService,
      InvoiceTemplateService invoiceTemplateService) {
    this.orderService = orderService;
    this.customerService = customerService;
    this.invoiceTemplateService = invoiceTemplateService;
  }

  // Task: Use a `StructuredTaskScope` to execute the services concurrently.
  //
  // Example:
  // try (var scope = StructuredTaskScope.open()) {
  //   Subtask<ResultType1> subtask1 = scope.fork(() -> ...);
  //   Subtask<ResultType2> subtask2 = scope.fork(() -> ...);
  //   Subtask<ResultType3> subtask3 = scope.fork(() -> ...);
  //
  //   scope.join();
  //
  //   ResultType1 result1 = subtask1.get();
  //   ResultType2 result2 = subtask2.get();
  //   ResultType3 result3 = subtask3.get();
  //
  //   ...
  // }

  public Invoice createInvoice(int orderId, int customerId, String language) throws InterruptedException {
    Order order = orderService.getOrder(orderId);
    Customer customer = customerService.getCustomer(customerId);
    InvoiceTemplate template = invoiceTemplateService.getTemplate(language);
    return Invoice.generate(order, customer, template);
  }
}
