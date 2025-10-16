package java25update.solutions.exercise9;

import java25update.exercise9.model.Customer;
import java25update.exercise9.model.Invoice;
import java25update.exercise9.model.InvoiceTemplate;
import java25update.exercise9.model.Order;
import java25update.exercise9.services.CustomerService;
import java25update.exercise9.services.InvoiceTemplateService;
import java25update.exercise9.services.OrderService;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class InvoiceGeneratorSolution {

  static void main() throws InterruptedException {
    new InvoiceGeneratorSolution(new OrderService(), new CustomerService(), new InvoiceTemplateService())
        .createInvoice(10012, 61157, "en");
  }

  private final OrderService orderService;
  private final CustomerService customerService;
  private final InvoiceTemplateService invoiceTemplateService;

  public InvoiceGeneratorSolution(
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
    try (var scope = StructuredTaskScope.open()) {
      Subtask<Order> orderTask = scope.fork(() -> orderService.getOrder(orderId));
      Subtask<Customer> customerTask = scope.fork(() -> customerService.getCustomer(customerId));
      Subtask<InvoiceTemplate> templateTask = scope.fork(() -> invoiceTemplateService.getTemplate(language));

      scope.join();

      Order order = orderTask.get();
      Customer customer = customerTask.get();
      InvoiceTemplate template = templateTask.get();

      return Invoice.generate(order, customer, template);
    }
  }
}
