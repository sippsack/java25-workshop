package java25update.exercise11;

import java25update.exercise11.model.Request;
import java25update.exercise11.model.User;

class Server {
  private final RestAdapter restAdapter = new RestAdapter();

  // Task: Put the logged-in user into a scoped value
  //       instead of passing it from one method to the next
  //
  // Code pattern:
  //
  // public final static ScopedValue<Type> SCOPED_VALUE = ScopedValue.newInstance();
  // ...
  // ScopedValue.where(SCOPED_VALUE, object).run(() -> doSomethingSmart());
  // ...
  // Type object = SCOPED_VALUE.get();

  void serve(Request request) {
    IO.println("Serving " + request);
    // ...
    User user = authenticateUser(request);
    restAdapter.processRequest(request, user);
    // ...
  }

  private static User authenticateUser(Request request) {
    boolean admin = "sven".equals(request.userId()) || "falk".equals(request.userId());
    return new User(request.userId(), admin);
  }
}