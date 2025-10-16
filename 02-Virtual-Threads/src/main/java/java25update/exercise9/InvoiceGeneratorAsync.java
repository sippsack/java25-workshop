package java25update.exercise9;

import java25update.exercise9.model.Customer;
import java25update.exercise9.model.Order;
import java25update.exercise9.model.Invoice;
import java25update.exercise9.model.InvoiceTemplate;
import java25update.exercise9.services.CustomerService;
import java25update.exercise9.services.InvoiceTemplateService;
import java25update.exercise9.services.OrderService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.Future.State.FAILED;
import static java.util.concurrent.Future.State.SUCCESS;

public class InvoiceGeneratorAsync {

  static void main() throws Throwable {
    new InvoiceGeneratorAsync(new OrderService(), new CustomerService(), new InvoiceTemplateService())
        .createInvoice(10012, 61157, "en");
  }

  private final OrderService orderService;
  private final CustomerService customerService;
  private final InvoiceTemplateService invoiceTemplateService;

  public InvoiceGeneratorAsync(
      OrderService orderService,
      CustomerService customerService,
      InvoiceTemplateService invoiceTemplateService) {
    this.orderService = orderService;
    this.customerService = customerService;
    this.invoiceTemplateService = invoiceTemplateService;
  }

  Invoice createInvoice(int orderId, int customerId, String language) throws Throwable {
    Future<Order> orderFuture;
    Future<Customer> customerFuture;
    Future<InvoiceTemplate> invoiceTemplateFuture;

    try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
      orderFuture = executor.submit(() -> { try { return orderService.getOrder(orderId); }
      catch (Exception e) { executor.shutdownNow(); throw e; }});
      customerFuture = executor.submit(() -> { try { return customerService.getCustomer(customerId); }
      catch (Exception e) { executor.shutdownNow(); throw e; }});
      invoiceTemplateFuture = executor.submit(() -> { try { return invoiceTemplateService.getTemplate(language); }
      catch (Exception e) { executor.shutdownNow(); throw e; }});
    }

    if (orderFuture.state() == SUCCESS && customerFuture.state() == SUCCESS && invoiceTemplateFuture.state() == SUCCESS) {
      Order order = orderFuture.get();
      Customer customer = customerFuture.get();
      InvoiceTemplate invoiceTemplate = invoiceTemplateFuture.get();
      return Invoice.generate(order, customer, invoiceTemplate);
    } else if (orderFuture.state() == FAILED && !(orderFuture.exceptionNow() instanceof InterruptedException)) {
      throw orderFuture.exceptionNow();
    } else if (customerFuture.state() == FAILED && !(customerFuture.exceptionNow() instanceof InterruptedException)) {
      throw customerFuture.exceptionNow();
    } else if (invoiceTemplateFuture.state() == FAILED) {
      throw invoiceTemplateFuture.exceptionNow();
    } else if (orderFuture.state() == FAILED) {
      throw orderFuture.exceptionNow();
    } else if (customerFuture.state() == FAILED) {
      throw customerFuture.exceptionNow();
    } else {
      throw new IllegalStateException();
    }
  }


}
